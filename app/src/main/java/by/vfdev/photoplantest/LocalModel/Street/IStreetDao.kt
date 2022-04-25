package by.vfdev.photoplantest.LocalModel.Street

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IStreetDao {

    @Insert
    suspend fun insertStreet(loc: List<Street>)

    @Query("SELECT * FROM street")
    suspend fun getAllStreet() : List<Street>
}