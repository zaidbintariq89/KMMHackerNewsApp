package com.mobilelive.looking4app.data.remote

import com.mobilelive.looking4app.data.model.Article

interface ApiInterface {
    suspend fun getAllArticles() : List<Article>
}