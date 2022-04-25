package by.vfdev.photoplantest.LocalModel.Street

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Street::class], version = 1)
abstract class StreetDatabase : RoomDatabase() {
    abstract fun streetDao() : IStreetDao
}