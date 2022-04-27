package by.vfdev.photoplantest.LocalModel.Location

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocationDBConvertor {

    private val gson = Gson()

    @TypeConverter
    fun toJson(segments: List<Location.Photo>?): String {
        return gson.toJson(segments)
    }

    @TypeConverter
    fun fromJson(json: String?): List<Location.Photo> {
        return gson.fromJson<List<Location.Photo>>(
            json,
            object : TypeToken<List<Location.Photo>>() {}.type
        )
    }

}