package com.example.android5homework2mc7.data.models

import androidx.annotation.DrawableRes

data class GalereyaModel(
    @DrawableRes
    val image: Int,
    val name: String,
    val country: String,
)
