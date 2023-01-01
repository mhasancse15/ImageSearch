package com.mahmudul.imagesearch.presentation.search_image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmudul.imagesearch.common.Resource
import com.mahmudul.imagesearch.data.model.PixabayResponse
import com.mahmudul.imagesearch.domain.use_case.ImageSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(private val imageSearchUseCase: ImageSearchUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<Resource<PixabayResponse>?>(null)
    val state = _state.asStateFlow()

    fun queryImage(query: String, apiKey: String, imageType: String) = viewModelScope.launch {

        imageSearchUseCase(query, apiKey, imageType).collect {
            _state.emit(it)
        }
    }

}