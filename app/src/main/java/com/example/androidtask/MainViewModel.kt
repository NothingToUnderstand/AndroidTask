package com.example.androidtask

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtask.model.Article
import com.example.androidtask.model.NewsResponse
import com.example.androidtask.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.OrderedCollectionChangeSet
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    val news = MutableLiveData<List<NewsResponse>>()
    val article = MutableLiveData<Article>()
    fun getNews(countryCode: String, pageNumber: Int) {
        viewModelScope.launch {
            repository.getNews(countryCode, pageNumber).let { it ->
                if (it.isSuccessful) {
                    it.body()?.let {
                        saveNews(it)
                    }
                } else {
                    Log.e("Check_Data", "Failed to get data, error: ${it.errorBody()}")
                }
            }
        }
    }

    private fun saveNews(resp: NewsResponse) {
        viewModelScope.launch {
            repository.saveNewsToDB(resp)
        }
    }

    fun setChangeListener() {
        val realmListener = RealmChangeListener<RealmResults<NewsResponse>> {
            news.postValue(repository.getNewsFromDB())
        }
        repository.getNewsFromDB().addChangeListener(realmListener)
    }

    fun getNewsFromDB() {
        viewModelScope.launch {
            val fromDb = repository.getNewsFromDB()
            news.postValue(fromDb)

        }
    }

}