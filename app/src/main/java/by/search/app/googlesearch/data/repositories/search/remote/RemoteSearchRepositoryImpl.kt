package by.search.app.googlesearch.data.repositories.search.remote

import by.search.app.googlesearch.common.APP_API_KEY
import by.search.app.googlesearch.common.GOOGLE_SEARCH_API_KEY
import by.search.app.googlesearch.common.NUMBER_OF_SEARCH_RESULT
import by.search.app.googlesearch.data.api.GoogleSearchService
import by.search.app.googlesearch.data.entity.search.SearchResponse
import io.reactivex.Single

class RemoteSearchRepositoryImpl(private val searchService: GoogleSearchService) : RemoteSearchRepository {

    override fun search(inputString: String, startIndex: Int): Single<SearchResponse> =
        searchService.search(
            APP_API_KEY,
            GOOGLE_SEARCH_API_KEY,
            NUMBER_OF_SEARCH_RESULT,
            inputString,
            startIndex
        )

}