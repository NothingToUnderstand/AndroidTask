package com.example.androidtask.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

open class NewsResponse() : RealmObject() {
    var articles: RealmList<Article> = RealmList()
    @PrimaryKey
    var date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()
    constructor(
        articles: List<Article>
    ) : this() {
        this.articles.addAll(articles)
    }
    override fun toString(): String {
        return "NewsResponse(articles=${articles.size}, date='$date')"
    }


}