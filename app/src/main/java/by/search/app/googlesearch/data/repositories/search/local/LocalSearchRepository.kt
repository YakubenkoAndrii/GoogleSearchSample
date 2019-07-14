package by.search.app.googlesearch.data.repositories.search.local

import by.search.app.googlesearch.data.entity.search.SearchedResultBundle
import by.search.app.googlesearch.data.entity.search.SearchItem

interface LocalSearchRepository {

    fun loadCachedResults(): SearchedResultBundle
    fun insertResults(items: MutableList<SearchItem>, inputString: String)
    fun deleteAll()

}