package by.vfdev.photoplantest.LocalModel.Location

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ILocationDao {

    @Query("SELECT * FROM location")
    fun getAllLocation() : List<Location>
}