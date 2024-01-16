package com.mobilelive.looking4app.dao

import com.mobilelive.looking4app.databases.DatabaseFactory.dbQuery
import com.mobilelive.looking4app.models.Article
import com.mobilelive.looking4app.models.Articles
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ArticleDAOImpl : ArticleDAO {

    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        body = row[Articles.body],
    )

    override suspend fun allArticles(): List<Article> = dbQuery {
        Articles.selectAll().map(::resultRowToArticle)
    }

    override suspend fun article(id: Int): Article? = dbQuery {
        Articles
            .select { Articles.id eq id }
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun addNewArticle(title: String, body: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun editArticle(id: Int, title: String, body: String): Boolean = dbQuery {
        Articles.update({ Articles.id eq id }) {
            it[Articles.title] = title
            it[Articles.body] = body
        } > 0
    }

    override suspend fun deleteArticle(id: Int): Boolean = dbQuery {
        Articles.deleteWhere { Articles.id eq id } > 0
    }
}