package com.mahmudul.imagesearch.presentation.search_image

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmudul.imagesearch.R
import com.mahmudul.imagesearch.common.Constants
import com.mahmudul.imagesearch.common.Resource
import com.mahmudul.imagesearch.databinding.FragmentSearchImageBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class SearchImageFragment : Fragment(R.layout.fragment_search_image) {

    private val viewModel: ImageSearchViewModel by viewModels()
    private val binding by viewBinding(FragmentSearchImageBinding::bind)
    lateinit var searchImagePagingDataAdapter: SearchImagePagingDataAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //RecyclerView
        searchImagePagingDataAdapter = SearchImagePagingDataAdapter(requireContext())
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerView.apply {
            layoutManager
            adapter = searchImagePagingDataAdapter
        }
        binding.recyclerView.layoutManager

        initViewCollect()
    }

    private fun initViewCollect() {
        with(viewModel) {
            queryImage("tiger", Constants.TOKEN, "photo")
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                state.collect { response ->
                    when (response) {
                        is Resource.Loading -> {
                            Log.e("Response", "Loading")
                        }
                        is Resource.Success -> {
                            Log.e("Response", response.data.totalHits.toString())
                            // binding.result.text = response.data.toString()

                            searchImagePagingDataAdapter.submitData(lifecycle, PagingData.from(response.data.hits))

                        }
                        is Resource.Error -> {

                            MotionToast.createColorToast(
                                requireActivity(),
                                getString(R.string.error),
                                response.throwable.localizedMessage ?: "Error",
                                MotionToastStyle.ERROR,
                                MotionToast.GRAVITY_TOP or MotionToast.GRAVITY_CENTER,
                                MotionToast.LONG_DURATION,
                                null
                            )

                            Log.e("Response", response.throwable.localizedMessage ?: "Error")
                        }
                        else -> {
                            Log.e("Response", "Unknown Error")
                        }
                    }
                }
            }
        }
    }
}

