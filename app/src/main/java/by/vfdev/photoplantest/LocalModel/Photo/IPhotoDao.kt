package by.vfdev.photoplantest.LocalModel.Photo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IPhotoDao {

    @Insert
    suspend fun insertPhoto(ph: List<Photo>)

    @Query("SELECT * FROM photo")
    suspend fun getAllPhoto() : List<Photo>
}