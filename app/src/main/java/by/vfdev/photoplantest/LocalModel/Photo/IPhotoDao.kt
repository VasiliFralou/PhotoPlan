package by.vfdev.photoplantest.LocalModel.Photo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IPhotoDao {

    @Insert
    fun insertPhoto(ph: Photo)

    @Query("SELECT * FROM photo")
    fun getAllPhoto() : List<Photo>
}