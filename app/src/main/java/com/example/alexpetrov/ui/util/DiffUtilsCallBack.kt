package com.example.alexpetrov.ui.util

import androidx.recyclerview.widget.DiffUtil
import com.example.alexpetrov.data.model.HeroModel

class DiffUtilsCallBack(
    private val oldList: List<HeroModel>,
    private val newList: List<HeroModel>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == oldList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}