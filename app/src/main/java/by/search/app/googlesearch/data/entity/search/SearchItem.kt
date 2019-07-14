package by.search.app.googlesearch.data.entity.search

import com.google.gson.annotations.SerializedName

data class SearchItem(@SerializedName("link") val descriptionUrl: String,
                      @SerializedName("snippet") var description: String,
                      @SerializedName("pagemap") var pageMap: PageMap?)