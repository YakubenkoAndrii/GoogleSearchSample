package by.search.app.googlesearch.data.entity.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(@SerializedName("items") val items: MutableList<SearchItem>,
                          @SerializedName("queries") val queries: QueryItem)