package com.javamaster.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamaster.dao.ArticleDao;
import com.javamaster.entity.Article;
import com.javamaster.entity.Category;
import com.javamaster.entity.Users;

public class ArticleDaoImpl implements ArticleDao {
	
	
	public int createArticle(Article article) {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement pr = con.prepareStatement("insert into "
					+ "article(title, body, category_id, users_id) values(?,?,?,?)");
			pr.setString(1, article.getTitle());
			pr.setString(2, article.getBody());
			pr.setInt(3, article.getCategory().getId());
			pr.setInt(4, 1);
			pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return 0;
			
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public int editArticle(Article article) {
		int result = 0;
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement pr = con.prepareStatement("update article set "
					+ "title=?, body=?, category_id=?");
			pr.setString(1, article.getTitle());
			pr.setString(2, article.getBody());
			pr.setInt(3, article.getCategory().getId());
			result = pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void deleteArticle(int id) {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement pr = con.prepareStatement("delete from article where id=?");
			pr.setInt(1, id);
			pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public Article getArticleById(int id) {
		Article article = null;
		Connection connection = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement pr = connection.prepareStatement("select id, title, body, "
					+ "category_id, users_id from article where id=?");
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()){
				article = new Article();
				article.setId(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setBody(rs.getString(3));
				Category category = new Category();
				category.setId(rs.getInt(4));
				article.setCategory(category);
				Users user = new Users();
				user.setId(rs.getInt(5));
				article.setUsers(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}

	public List<Article> getAllArticles() {
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement pr = con.prepareStatement("select id, title, body, category_id, users_id from article");
			ResultSet rs = pr.executeQuery();
			while (rs.next()){
				article = new Article();
				article.setId(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setBody(rs.getString(3));
				Category category = new Category();
				category.setId(rs.getInt(4));
				article.setCategory(category);
				Users user = new Users();
				user.setId(rs.getInt(5));
				article.setUsers(user);
				articles.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}

}
