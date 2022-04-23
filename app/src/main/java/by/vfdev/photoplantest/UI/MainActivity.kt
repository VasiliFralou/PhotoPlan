package by.vfdev.photoplantest.UI

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import by.vfdev.photoplantest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

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