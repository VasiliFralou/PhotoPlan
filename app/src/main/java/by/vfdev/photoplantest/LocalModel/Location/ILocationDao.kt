package by.vfdev.photoplantest.LocalModel.Location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ILocationDao {

    @Insert
    fun insertLocation(loc: MutableList<Location>)

    @Query("SELECT * FROM location")
    fun getAllLocation() : List<Location>
}