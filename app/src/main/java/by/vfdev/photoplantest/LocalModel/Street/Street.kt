package by.vfdev.photoplantest.LocalModel.Street

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "street")
class Street (
    val name: String? = null,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)
