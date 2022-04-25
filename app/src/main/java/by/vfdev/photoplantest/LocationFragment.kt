package by.vfdev.photoplantest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.system.Os.bind
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.photoplantest.databinding.FragmentLocationBinding

class LocationFragment : Fragment(R.layout.fragment_location) {

    private lateinit var navController: NavController
    private val REQUEST_CODE = 0

    private val binding by viewBinding(FragmentLocationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        binding.btnAdd.setOnClickListener {
            openGalleryForImages()
        }
    }

    private fun openGalleryForImages() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)

        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){

            if (data?.clipData != null) {

                val count = data.clipData!!.itemCount

                for (i in 0 until count) {

                    val imageUri: Uri = data.clipData!!.getItemAt(i).uri

                    binding.imv.setImageURI(imageUri)
                    binding.textView.text = imageUri.toString()
                }

            } else if (data?.data != null) {

                val imageUri: Uri? = data.data

                binding.imv.setImageURI(imageUri)
                binding.textView.text = imageUri.toString()
            }
        }
    }
}