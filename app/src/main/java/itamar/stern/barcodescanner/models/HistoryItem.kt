package itamar.stern.barcodescanner.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "History")
data class HistoryItem(
    @PrimaryKey
    val url: String
)