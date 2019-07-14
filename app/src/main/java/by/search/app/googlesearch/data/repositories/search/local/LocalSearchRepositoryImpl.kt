package by.search.app.googlesearch.data.repositories.search.local

import by.search.app.googlesearch.data.db.SearchDb
import by.search.app.googlesearch.data.entity.search.SearchedResultBundle
import by.search.app.googlesearch.data.entity.search.SearchItem
class LocalSearchRepositoryImpl(private val db: SearchDb) : LocalSearchRepository {

    override fun loadCachedResults(): SearchedResultBundle =
        db.searchResultsDao().getCachedResults()

    override fun insertResults(items: MutableList<SearchItem>, inputString: String) =
        db.searchResultsDao().insertResults(
            SearchedResultBundle(
                null,
                inputString,
                items))

    override fun deleteAll() {
        db.searchResultsDao().deleteAll()
    }

}

