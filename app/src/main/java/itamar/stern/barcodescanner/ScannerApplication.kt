package itamar.stern.barcodescanner

import android.app.Application
import itamar.stern.barcodescanner.room_db.RoomDB

class ScannerApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: ScannerApplication

        val roomDB: RoomDB by lazy {
            RoomDB.create(instance)
        }
    }
}