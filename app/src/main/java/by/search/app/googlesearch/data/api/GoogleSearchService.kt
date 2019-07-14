package by.search.app.googlesearch.data.api

import by.search.app.googlesearch.common.*
import by.search.app.googlesearch.data.entity.search.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSearchService {

    @GET("/customsearch/v1")
    fun search(
        @Query(KEY) appApiKey: String,
        @Query(CX_KEY) searchApiKey: String,
        @Query(NUM) numberOfSearchResult: Int,
        @Query(QUERY) inputString: String,
        @Query(START) startIndex: Int): Single<SearchResponse>

}
