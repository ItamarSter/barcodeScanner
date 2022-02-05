package itamar.stern.barcodescanner.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import itamar.stern.barcodescanner.models.HistoryItem

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addHistory(history: HistoryItem)

    @Query("SELECT * FROM History")
    fun getHistory(): LiveData<List<HistoryItem>>

    @Query("DELETE FROM History")
    fun clearHistory()
}