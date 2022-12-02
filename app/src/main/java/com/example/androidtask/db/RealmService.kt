package com.example.androidtask.db

import androidx.lifecycle.MutableLiveData
import com.example.androidtask.model.NewsResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RealmService @Inject constructor(private val realm: Realm) {

    fun getFromDb(): RealmResults<NewsResponse> =
        realm.where(NewsResponse::class.java).findAll()

    fun saveToDb(resp: NewsResponse) =
        realm.executeTransactionAsync { it.insertOrUpdate(resp)}


}