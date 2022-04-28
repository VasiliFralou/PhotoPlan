package by.vfdev.photoplantest.LocalModel.Street

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IStreetDao {

    @Insert
    fun insertStreet(st: Street)

    @Query("SELECT * FROM street")
    fun getAllStreet() : List<Street>

    @Query("DELETE FROM street")
    fun delete()
}