package by.vfdev.photoplantest.LocalModel.Photo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
class Photo(
    val uri: String? = null,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)