package com.javamaster.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javamaster.dao.ArticleDao;
import com.javamaster.dao.impl.ArticleDaoImpl;
import com.javamaster.entity.Article;
import com.javamaster.entity.Category;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleDao articleDao = new ArticleDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/")){
			List<Article> articles = articleDao.getAllArticles();
			request.setAttribute("title", "Title of our application");
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		}
		
		else if (path.equals("/welcome")){
			request.getRequestDispatcher("/WEB-INF/view/welcome.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
