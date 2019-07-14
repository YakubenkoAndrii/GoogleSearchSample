package by.search.app.googlesearch.data.entity.search

import com.google.gson.annotations.SerializedName

data class RequestItem(@SerializedName("searchTerms") val searchedString: String)