package by.vfdev.photoplantest.Utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object LiveDataUtils {

    fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this
}