package by.vfdev.photoplantest.UI

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.photoplantest.LocalModel.Location.Location
import by.vfdev.photoplantest.R
import by.vfdev.photoplantest.ViewModel.LocationViewModel
import by.vfdev.photoplantest.databinding.FragmentLocationBinding

class LocationFragment : Fragment(R.layout.fragment_location) {

    private val navController by lazy { findNavController() }
    private val locationVM: LocationViewModel by activityViewModels()
    private val binding by viewBinding(FragmentLocationBinding::bind)

    private val resultLauncherImageChoose =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            locationVM.addImage(result)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = LocationAdapter { postition -> locationVM.onOpenGallery(postition) }
        binding.recyclerView.adapter = adapter

        adapter.updateData(listOf(Location(null, listOf())))

        locationVM.locationLive.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        locationVM.onOpenGalleryEvent.observe(viewLifecycleOwner) {
            resultLauncherImageChoose.launch(it)
        }


    }


}