package com.mobilelive.looking4app.dao

import com.mobilelive.looking4app.models.Article

interface ArticleDAO {
    suspend fun allArticles(): List<Article>
    suspend fun article(id: Int): Article?
    suspend fun addNewArticle(title: String, body: String): Article?
    suspend fun editArticle(id: Int, title: String, body: String): Boolean
    suspend fun deleteArticle(id: Int): Boolean
}