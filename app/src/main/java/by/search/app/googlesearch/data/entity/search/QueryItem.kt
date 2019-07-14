package by.search.app.googlesearch.data.entity.search

import com.google.gson.annotations.SerializedName

data class QueryItem(@SerializedName("request") val requestList: MutableList<RequestItem>)