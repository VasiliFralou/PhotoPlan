package by.vfdev.photoplantest.LocalModel.Street

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "street")
class Street (
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0,
    val name: String? = null)
