package com.javamaster.dao;



import java.util.List;

import com.javamaster.entity.Article;

public interface ArticleDao {

	int createArticle(Article article);
	
	int editArticle(Article article);
	
	void deleteArticle(int id);
	
	Article getArticleById(int id);
	
	List<Article> getAllArticles();
}
