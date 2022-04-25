package by.vfdev.photoplantest.LocalModel.Photo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
class Photo(
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0,
    val uri: String? = null)