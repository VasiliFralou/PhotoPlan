package by.vfdev.photoplantest.LocalModel.Location

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Location::class], version = 1)
@TypeConverters(LocationDBConvertor::class)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun locationDao(): ILocationDao

    companion object {
        private var locationDB: LocationDatabase? = null
        fun getDataBase(contextApplication: Context): LocationDatabase {
            if (locationDB == null) {
                locationDB = Room.databaseBuilder(
                    contextApplication,
                    LocationDatabase::class.java, "location_db"
                ).build()
            }
            return locationDB!!
        }
    }
}