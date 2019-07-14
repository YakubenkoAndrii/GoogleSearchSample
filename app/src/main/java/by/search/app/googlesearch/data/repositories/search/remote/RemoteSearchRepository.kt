package by.search.app.googlesearch.data.repositories.search.remote

import by.search.app.googlesearch.data.entity.search.SearchResponse
import io.reactivex.Single

interface RemoteSearchRepository {

    fun search(inputString: String, startIndex: Int): Single<SearchResponse>

}