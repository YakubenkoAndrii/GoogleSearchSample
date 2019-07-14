package by.search.app.googlesearch.data.db.converters

import android.arch.persistence.room.TypeConverter
import by.search.app.googlesearch.data.entity.search.SearchItem
import com.google.gson.Gson

class SearchItemsConverter {

    @TypeConverter
    fun listToJson(value: MutableList<SearchItem>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): MutableList<SearchItem> {
        val objects = Gson().fromJson(value, Array<SearchItem>::class.java) as Array<SearchItem>
        return objects.toMutableList()
    }

}