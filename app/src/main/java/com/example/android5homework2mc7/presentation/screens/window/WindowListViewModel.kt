package com.example.android5homework2mc7.presentation.screens.window

import androidx.lifecycle.ViewModel
import com.example.android5homework2mc7.data.GalereyaData
import com.example.android5homework2mc7.data.models.GalereyaModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WindowListViewModel : ViewModel() {

    private val galereyaData = GalereyaData

    private var _galereyaListState = MutableStateFlow<List<GalereyaModel>>(mutableListOf())
    val galereyaListState = _galereyaListState.asStateFlow()

    init {
        getCoffeeList()
    }

    private fun getCoffeeList() {
        _galereyaListState.value = galereyaData.getGalereyaModelList()
    }
}