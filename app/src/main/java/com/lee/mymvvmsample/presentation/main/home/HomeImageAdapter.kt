package com.lee.mymvvmsample.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.common.toPixel
import com.lee.mymvvmsample.common.utils.ImageLoader
import com.lee.mymvvmsample.databinding.ItemHomeBinding
import com.lee.mymvvmsample.domain.model.Image
import timber.log.Timber

class HomeImageAdapter(
    private val listener: OnClickHandler,
    private val imageLoader: ImageLoader,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemList = mutableListOf<Image>()

    private var itemWidth = DEFAULT_ITEM_WIDTH

    companion object {
        private const val DEFAULT_ITEM_WIDTH = 150
        private const val ITEM_MARGIN_DP = 40f
        private const val GRID_SPAN_COUNT = 3
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val screenWidth = parent.resources.displayMetrics.widthPixels
        val totalMarginPx = ITEM_MARGIN_DP.toPixel(parent.context)
        itemWidth = (screenWidth - totalMarginPx) / GRID_SPAN_COUNT

        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home,
                parent,
                false,
            ),
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(itemList[position], position)
            }
        }
    }

    fun initItem(list: List<Image>?) {
        list?.let {
            this.itemList.addAll(it)
            Timber.d("itemList.size: ${itemList.size}")
            notifyDataSetChanged()
        }
    }

    fun resetList() {
        itemList.clear()
    }

    inner class ItemViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Image,
            position: Int,
        ) {
            binding.llContents.layoutParams = LinearLayout.LayoutParams(itemWidth, itemWidth)

            imageLoader.imageLoadWithURL(item.previewURL, binding.ivContent)

            binding.llContents.setOnClickListener {
                listener.onClickItem(item.largeImageURL)
            }
        }
    }
}
