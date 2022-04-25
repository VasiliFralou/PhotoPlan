package by.vfdev.photoplantest.LocalModel.Location

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Location::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun locationDao() : ILocationDao

    companion object {
        private var locationDatabase : LocationDatabase? = null
        fun getDataBase(contextApplication: Context): LocationDatabase {
            if (locationDatabase == null) {
                locationDatabase = Room.databaseBuilder(
                    contextApplication,
                    LocationDatabase::class.java, "location_db"
                ).build()
            }
            return locationDatabase!!
        }
    }
}