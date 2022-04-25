package by.vfdev.photoplantest.UI

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.photoplantest.R
import by.vfdev.photoplantest.LocalModel.Location.Location
import by.vfdev.photoplantest.databinding.FragmentLocationBinding

class LocationFragment : Fragment(R.layout.fragment_location) {

    private val navController by lazy { findNavController() }

    private val binding by viewBinding(FragmentLocationBinding::bind)

    private val resultLauncherImageChoose =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
                if (data?.clipData != null) {
                    val count = data.clipData!!.itemCount
                    for (i in 0 until count) {
                        val imageUri: Uri = data.clipData!!.getItemAt(i).uri
                    }

                } else if (data?.data != null) {
                    val imageUri: Uri? = data.data
                }

            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = LocationAdapter { openGalleryForImages() }
        binding.recyclerView.adapter = adapter

        adapter.updateData(listOf(Location(null)))
    }


    private fun openGalleryForImages() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        resultLauncherImageChoose.launch(intent)
    }


}