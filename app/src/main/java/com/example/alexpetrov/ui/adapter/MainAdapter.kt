package com.example.alexpetrov.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.alexpetrov.R
import com.example.alexpetrov.data.model.HeroModel
import com.example.alexpetrov.ui.util.DiffUtilsCallBack
import com.squareup.picasso.Picasso

class MainAdapter(
    private val onListItemClickListener: ItemClickListener,
    private var data: ArrayList<HeroModel> = arrayListOf()
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(newData: List<HeroModel>) {
        val diffUtilsCallBack = DiffUtilsCallBack(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtilsCallBack)
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hero_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: HeroModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.name).text = data.name

                Picasso.with(itemView.context)
                    .load(data.images.md)
                    .error(R.drawable.ic_baseline_error)
                    .into(itemView.findViewById<ImageView>(R.id.image))

                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }

        private fun openInNewWindow(dataModel: HeroModel) {
            onListItemClickListener.onItemClick(dataModel)
        }
    }

    interface ItemClickListener {
        fun onItemClick(dataModel: HeroModel)
    }
}