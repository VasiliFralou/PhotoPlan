package by.vfdev.photoplantest.LocalModel.Location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ILocationDao {

    @Insert
    suspend fun insertLocation(loc: List<Location>)

    @Query("SELECT * FROM location")
    suspend fun getAllLocation() : List<Location>
}