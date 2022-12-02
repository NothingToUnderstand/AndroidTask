package com.example.androidtask.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.UUID

open class Article() : RealmObject(),java.io.Serializable {
    @PrimaryKey
    var id=UUID.randomUUID()
    var author: String?=null
    var content: String?=null
    var description: String?=null
    var publishedAt: String?=null
    var title: String?=null
    var url: String?=null
    var urlToImage: String?=null

    constructor(
        author: String?,
        content: String?,
        description: String?,
        publishedAt: String?,
        title: String?,
        url: String?,
        urlToImage: String?
    ) : this() {
        this.author = author
        this.content = content
        this.description = description
        this.publishedAt = publishedAt
        this.title = title
        this.url = url
        this.urlToImage = urlToImage
    }

    override fun toString(): String {
        return "Article(author='$author', content='$content', description='$description', publishedAt='$publishedAt', title='$title', url='$url', urlToImage='$urlToImage')"
    }

}