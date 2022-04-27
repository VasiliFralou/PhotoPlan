package by.vfdev.photoplantest.LocalModel.Photo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.vfdev.photoplantest.LocalModel.Location.LocationDatabase

@Database(entities = [Photo::class], version = 1)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao() : IPhotoDao

    companion object {
        private var photoDB : PhotoDatabase? = null
        fun getDataBase(contextApplication: Context) : PhotoDatabase {
            if (photoDB == null) {
                photoDB = Room.databaseBuilder(
                    contextApplication,
                    PhotoDatabase::class.java, "photo_db"
                ).build()
            }
            return photoDB!!
        }
    }
}