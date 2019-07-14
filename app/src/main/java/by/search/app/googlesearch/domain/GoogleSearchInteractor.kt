package by.search.app.googlesearch.domain

import by.search.app.googlesearch.data.entity.search.SearchedResultBundle
import by.search.app.googlesearch.data.entity.search.SearchItem
import io.reactivex.Single

interface GoogleSearchInteractor {

    fun search(inputString: String): Single<MutableList<SearchItem>>
    fun loadCachedResults(): Single<SearchedResultBundle>

}