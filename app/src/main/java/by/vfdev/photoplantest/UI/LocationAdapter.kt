package by.vfdev.photoplantest.UI

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.photoplantest.R
import by.vfdev.photoplantest.LocalModel.Location.Location
import by.vfdev.photoplantest.databinding.ItemLocationBinding

class LocationAdapter(
    private val onAddClick: () -> Unit
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    private val dataList: MutableList<Location> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Location>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemV: View) : RecyclerView.ViewHolder(itemV) {
        val binding by viewBinding(ItemLocationBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_location, parent, false)
        val holder = ViewHolder(itemView)

        holder.binding.btnAddPhoto.setOnClickListener {
            onAddClick.invoke()
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}