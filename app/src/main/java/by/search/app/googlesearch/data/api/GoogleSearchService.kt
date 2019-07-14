package by.search.app.googlesearch.data.api

import by.search.app.googlesearch.data.entity.search.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSearchService {

    @GET("/customsearch/v1")
    fun search(
        @Query("key") appApiKey: String,
        @Query("cx") searchApiKey: String,
        @Query("num") numberOfSearchResult: Int,
        @Query("q") inputString: String,
        @Query("startIndex") startIndex: Int): Single<SearchResponse>

}