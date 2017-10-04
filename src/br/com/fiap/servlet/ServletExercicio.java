package br.com.fiap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.interfaces.Exercicio;

@WebServlet("/exercicio")
public class ServletExercicio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	Exercicio ex;

	public ServletExercicio() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> nomes = ex.buscarNomes();
        request.setAttribute("nomes", nomes);
        request.getRequestDispatcher("/WEB-INF/exercicio.jsp").forward(request, response);  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		try {
			String nome = request.getParameter("nome");
			ex.incluirNome(nome);			
			
			for(String nm : ex.buscarNomes()){
				out.println(nm);
			}
			
		} catch (Exception e) {
			out.print(e.getMessage());
		}
	}
}