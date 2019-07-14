package by.search.app.googlesearch.data.entity.search

import com.google.gson.annotations.SerializedName

data class PageMap(@SerializedName("cse_thumbnail") var images: MutableList<SingleImage>)