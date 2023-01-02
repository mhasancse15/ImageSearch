package com.mahmudul.imagesearch.presentation.search_image

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahmudul.imagesearch.data.model.Hit
import com.mahmudul.imagesearch.databinding.SearchImageItemBinding

class SearchImagePagingDataAdapter(private val context: Context) :
    PagingDataAdapter<Hit, SearchImagePagingDataAdapter.SearchImageViewHolder>(Diff) {


    companion object {
        val Diff = object : DiffUtil.ItemCallback<Hit>() {
            override fun areItemsTheSame(
                oldItem: Hit,
                newItem: Hit
            ): Boolean {
                return oldItem.user_id == newItem.user_id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: Hit,
                newItem: Hit
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return SearchImageViewHolder(
            SearchImageItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    class SearchImageViewHolder(val binding: SearchImageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {

        try {
            val hit = getItem(position)
            if (hit != null) {
                holder.binding.apply {
                    title.text = hit.user

                    Glide.with(context)
                        .load(hit.userImageURL)
                        .into(imageView)

                }

            }
        } catch (ex: Exception) {
            ex.message
        }
    }


}