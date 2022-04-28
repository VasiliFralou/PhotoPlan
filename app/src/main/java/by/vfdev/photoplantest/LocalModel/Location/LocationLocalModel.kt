package by.vfdev.photoplantest.LocalModel.Location

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = LocationDatabase.getDataBase(context).locationDao()

    suspend fun insertLocation(loc: MutableList<Location>) {
        database.insertLocation(loc)
    }

    suspend fun getAllLocation(): List<Location> {
        return database.getAllLocation()
    }
}