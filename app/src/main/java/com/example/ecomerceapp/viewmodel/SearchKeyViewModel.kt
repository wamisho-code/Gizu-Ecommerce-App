package com.example.ecomerceapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.ecomerceapp.local_database.database_classes.SearchKeyWordsDB
import com.example.ecomerceapp.local_database.entity.SearchKeyWords
class SearchKeyViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = SearchKeyWordsDB
        .get(app)
        .searchKeyWordsDao()

    private val searchQuery = MutableLiveData("")

    val searchSuggestions: LiveData<List<SearchKeyWords>> =
        searchQuery.switchMap { query ->

            if (query.isBlank()) {
                MutableLiveData(emptyList())
            } else {
                dao.getSuggestedKeys(query)
            }
        }

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }
}