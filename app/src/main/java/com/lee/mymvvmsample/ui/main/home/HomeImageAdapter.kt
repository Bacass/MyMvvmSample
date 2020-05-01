package com.lee.mymvvmsample.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.common.toPixel
import com.lee.mymvvmsample.common.utils.DeviceInfo
import com.lee.mymvvmsample.common.utils.ImageLoader
import com.lee.mymvvmsample.network.model.ImageHits
import com.lee.mymvvmsample.databinding.ItemHomeBinding
import org.koin.core.KoinComponent
import org.koin.core.get

class HomeImageAdapter(private val viewModel: HomeViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), KoinComponent {

    private val imageLoader: ImageLoader = get()

    private var item_list = mutableListOf<ImageHits>()
    private var itemWidth = 150

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        itemWidth = (DeviceInfo.getDeviceWidth(parent.context) - 40f.toPixel(parent.context)) / 3

        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = item_list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(item_list[position], position)
            }
        }
    }

    fun initItem(list: List<ImageHits>?) {
        list?.let {
            this.item_list.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageHits, position: Int) {
            binding.llContents.layoutParams = LinearLayout.LayoutParams(itemWidth, itemWidth)

            imageLoader.imageLoadWithURL(item.previewURL, binding.ivContent)
        }
    }
}
