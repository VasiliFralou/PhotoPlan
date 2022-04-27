package by.vfdev.photoplantest.ViewModel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.photoplantest.Utils.LiveDataUtils.asLiveData
import by.vfdev.photoplantest.LocalModel.Location.Location
import by.vfdev.photoplantest.Repository.LocationRepository
import by.vfdev.photoplantest.Utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository) : ViewModel() {

    private var choosePosition: Int? = null

    private val _locationLive: MutableLiveData<List<Location>> = MutableLiveData<List<Location>>()
    val locationLive = _locationLive.asLiveData()


    private val _onOpenGalleryEvent = SingleLiveEvent<Intent>()
    val onOpenGalleryEvent = _onOpenGalleryEvent.asLiveData()
    fun onOpenGallery(position: Int) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        choosePosition = position
        _onOpenGalleryEvent.value = intent
    }


    fun addImage(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data?.clipData != null) {
                val count = data.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri: Uri = data.clipData!!.getItemAt(i).uri

                }

            } else if (data?.data != null) {
                val imageUri: Uri? = data.data

                _locationLive.value = _locationLive.value?.toMutableList().apply {
                    val name = this?.getOrNull(choosePosition!!)?.NameLocation
                    val photoList: MutableList<Location.Photo> =
                        this?.getOrNull(choosePosition!!)?.photoList?.toMutableList()
                            ?: mutableListOf()
                    photoList.add(Location.Photo(imageUri.toString()))
                    val editLocation = Location(
                        NameLocation = name,
                        photoList = photoList
                    )

                    this?.removeAt(choosePosition!!)
                    this?.add(choosePosition!!, editLocation)
                }
            }
        }
    }


    private fun getAllLocation() {
        viewModelScope.launch {
            val data = locationRepository.getDataLocal()

            data
                .onSuccess {
                    val newRes: MutableList<Location> = it.toMutableList()
                    val photo = Location("sdf", listOf())
                    newRes.add(photo)
                    _locationLive.value = newRes
                }.onFailure {
                    _locationLive.value = mutableListOf()
                    Log.e("!!!ErrorListLocation", it.stackTraceToString())
                }
        }
    }

    init {
        getAllLocation()
    }


}