package by.vfdev.photoplantest.Repository

import by.vfdev.photoplantest.LocalModel.Location.Location
import by.vfdev.photoplantest.LocalModel.Location.LocationLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationLocalModel: LocationLocalModel) {

    suspend fun getDataLocal(): Result<List<Location>> = withContext(Dispatchers.IO) {

        val locationList = locationLocalModel.getAllLocation()

        return@withContext Result.success(locationList)
    }

    suspend fun saveData(loc: MutableList<Location>):
            Result<MutableList<Location>> = withContext(Dispatchers.IO) {
        locationLocalModel.insertLocation(loc)

        return@withContext Result.success(loc)
    }
}