package by.vfdev.photoplantest.LocalModel.Photo

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PhotoLocationModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = PhotoDatabase.getDataBase(context).photoDao()

    suspend fun getAllPhoto(): List<Photo> {
        return database.getAllPhoto()
    }

}