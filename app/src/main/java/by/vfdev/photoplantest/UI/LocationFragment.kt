package by.vfdev.photoplantest.UI

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.photoplantest.LocalModel.Location.Location
import by.vfdev.photoplantest.LocalModel.Street.IStreetDao
import by.vfdev.photoplantest.LocalModel.Street.Street
import by.vfdev.photoplantest.LocalModel.Street.StreetDatabase
import by.vfdev.photoplantest.R
import by.vfdev.photoplantest.ViewModel.LocationViewModel
import by.vfdev.photoplantest.databinding.FragmentLocationBinding


class LocationFragment : Fragment(R.layout.fragment_location) {

    private val navController by lazy { findNavController() }
    private val locationVM: LocationViewModel by activityViewModels()
    private val binding by viewBinding(FragmentLocationBinding::bind)
    lateinit var dao: IStreetDao

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

        val dataBase = Room.databaseBuilder(requireContext(),
            StreetDatabase::class.java,"street_db")
            .allowMainThreadQueries()
            .build()
        dao = dataBase.streetDao()

        binding.titleName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val loc = binding.titleName.text.toString()
                dao.delete()
                dao.insertStreet(Street(loc))
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        locationVM.onOpenGalleryEvent.observe(viewLifecycleOwner) {
            resultLauncherImageChoose.launch(it)
        }


    }


}