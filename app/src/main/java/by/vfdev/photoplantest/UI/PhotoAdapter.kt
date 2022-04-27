package by.vfdev.photoplantest.UI

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.photoplantest.LocalModel.Location.Location
import by.vfdev.photoplantest.R
import by.vfdev.photoplantest.databinding.ItemImageBinding


class PhotoAdapter(
    private val onClick: (photo: Location.Photo) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private val list: MutableList<Location.Photo> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Location.Photo>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemV: View) : RecyclerView.ViewHolder(itemV) {
        val binding by viewBinding(ItemImageBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_image, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.itemImage.setImageURI(item.uri.toUri())
    }

    override fun getItemCount(): Int {
        return list.size
    }
}