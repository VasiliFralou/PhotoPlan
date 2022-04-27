package by.vfdev.photoplantest.LocalModel.Location

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
class Location(
    val NameLocation: String? = null,
    val photoList : List<Photo>,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0
) {

    data class Photo(
        val uri: String)
}