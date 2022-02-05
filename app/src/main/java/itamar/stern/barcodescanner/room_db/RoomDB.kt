package itamar.stern.barcodescanner.room_db

import android.app.Dialog
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import itamar.stern.barcodescanner.models.HistoryItem


const val DB_VERSION = 1
const val DB_NAME = "HistoryDatabase"

@Database(entities = [HistoryItem::class], version = DB_VERSION)
abstract class RoomDB : RoomDatabase() {
    companion object {
        fun create(context: Context): RoomDB {
            return Room.databaseBuilder(context, RoomDB::class.java, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    abstract fun historyDao(): HistoryDao

}