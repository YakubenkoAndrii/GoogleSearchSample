package by.search.app.googlesearch.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import by.search.app.googlesearch.data.db.converters.SearchItemsConverter
import by.search.app.googlesearch.data.db.dao.SearchResultsDao
import by.search.app.googlesearch.data.entity.search.SearchedResultBundle

@Database(entities = [SearchedResultBundle::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(SearchItemsConverter::class)
abstract class SearchDb : RoomDatabase() {

    abstract fun searchResultsDao(): SearchResultsDao

}