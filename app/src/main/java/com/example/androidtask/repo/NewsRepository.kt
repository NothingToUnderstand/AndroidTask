package com.example.androidtask.repo

import com.example.androidtask.api.NewsService
import com.example.androidtask.db.RealmService
import com.example.androidtask.model.NewsResponse
import io.realm.annotations.PrimaryKey
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val realmService: RealmService
) {
    suspend fun getNews(
        countryCode: String,
        pageNumber: Int
    ) = newsService.getTop(countryCode, pageNumber)

    fun saveNewsToDB(resp: NewsResponse) = realmService.saveToDb(resp)
    fun getNewsFromDB() = realmService.getFromDb()

}