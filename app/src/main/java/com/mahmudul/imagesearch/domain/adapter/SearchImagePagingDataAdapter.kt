package com.mahmudul.imagesearch.domain.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.mahmudul.imagesearch.R
import com.mahmudul.imagesearch.data.model.Hit
import com.mahmudul.imagesearch.databinding.SearchImageItemBinding

class SearchImagePagingDataAdapter() :
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
                with(holder.binding) {
                    title.text = hit.tags
                    title.isSelected = true

                    if (hit.previewURL.isNotBlank()) {
                        imageView.load(hit.previewURL) {
                            placeholder(R.drawable.ic_default_image)

                            listener(
                                onSuccess = { _, _ ->
                                    Log.d(
                                        "imageIssue",
                                        "Success image Url = " + hit.previewURL
                                    )
                                },
                                onError = { request: ImageRequest, error: ErrorResult ->
                                    request.error
                                    imageView.load(R.drawable.ic_default_image)
                                    Log.d(
                                        "imageIssue",
                                        "Exception image Url = " + hit.previewURL + " Error $error"
                                    )
                                }
                            )
                        }
                    } else {
                        imageView.load(R.drawable.ic_default_image)
                    }
                }
            }
        } catch (ex: Exception) {
            ex.message
        }
    }
}