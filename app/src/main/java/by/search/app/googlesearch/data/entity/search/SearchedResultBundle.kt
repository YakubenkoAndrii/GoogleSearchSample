package by.search.app.googlesearch.data.entity.search

import android.arch.persistence.room.*
import by.search.app.googlesearch.data.db.RESULTS_TABLE

@Entity(tableName = RESULTS_TABLE)
class SearchedResultBundle(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "inputString") var inputString: String,
    @ColumnInfo(name = "items") var items: MutableList<SearchItem>
)
