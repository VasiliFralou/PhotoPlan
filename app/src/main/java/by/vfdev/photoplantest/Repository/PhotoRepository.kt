package by.vfdev.photoplantest.Repository

import by.vfdev.photoplantest.LocalModel.Photo.Photo
import by.vfdev.photoplantest.LocalModel.Photo.PhotoLocationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoLocationModel: PhotoLocationModel) {

    suspend fun getDataPhoto(): Result<List<Photo>> = withContext(Dispatchers.IO) {

        val photoList = photoLocationModel.getAllPhoto()

        return@withContext Result.success(photoList)
    }
}