package com.example.test

import com.example.test.vo.ResponseVO


import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BaseViewModel(
) : ViewModel() {
    private val TAG = BaseViewModel::class.java.simpleName

    private val _uiState = MutableStateFlow(BaseUiState())
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()

    private var response: List<ResponseVO>? = null

    init {
        response = fetchProductList()
        _uiState.update { currentState ->
            currentState.copy(
                response = response,
                showLoader = true,
                showError = false,
            )
        }
    }

    private fun fetchProductList(): List<ResponseVO> {
        return listOf(
            ResponseVO(
                id = 1,
                image = "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_01.jpg",
                name = "Tomato",
                price = "121",
                rating = "3"
            ),
            ResponseVO(
                id = 2,
                image = "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_01.jpg",
                name = "Onion",
                price = "48",
                rating = "4"
            ),
            ResponseVO(
                id = 3,
                image = "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_01.jpg",
                name = "Capsicum",
                price = "33",
                rating = "2"
            ),
            ResponseVO(
                id = 4,
                image = "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_01.jpg",
                name = "Carrot",
                price = "34",
                rating = "4"
            ),
            ResponseVO(
                id = 5,
                image = "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_01.jpg",
                name = "Cucumber",
                price = "22",
                rating = "4"
            )
        )
    }

    fun updateItemCount(count: Int, itemId: Int) {
        response?.firstOrNull { it.id == itemId }.also {
            it?.count = count
        }
        _uiState.update { currentState ->
            currentState.copy(
                response = response
            )
        }


    }
}

data class BaseUiState(
    val response: List<ResponseVO>? = null,
    val showLoader: Boolean = false,
    val showError: Boolean = false,
)