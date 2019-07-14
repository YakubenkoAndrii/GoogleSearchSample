package by.search.app.googlesearch.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import by.search.app.googlesearch.data.db.RESULTS_TABLE
import by.search.app.googlesearch.data.entity.search.SearchedResultBundle

@Dao
interface SearchResultsDao {

    @Query("SELECT * FROM $RESULTS_TABLE")
    fun getCachedResults(): SearchedResultBundle

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResults(result: SearchedResultBundle)

    @Query("DELETE FROM $RESULTS_TABLE")
    fun deleteAll()

}