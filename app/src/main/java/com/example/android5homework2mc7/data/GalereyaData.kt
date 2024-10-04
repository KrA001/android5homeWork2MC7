package com.example.android5homework2mc7.data

import com.example.android5homework2mc7.R
import com.example.android5homework2mc7.data.models.GalereyaModel

object GalereyaData {


    private var galereyaList = mutableListOf(
        GalereyaModel(R.drawable.mount_fuji,"mount","Chinese"),
        GalereyaModel(R.drawable.mount_fuji,"mount","Chinese"),
        GalereyaModel(R.drawable.anime_person,"аниме","Chinese"),
        GalereyaModel(R.drawable.mount_fuji,"mount","Chinese"),
        GalereyaModel(R.drawable.mount_fuji,"mount","Chinese"),
        GalereyaModel(R.drawable.mount_fuji,"mount","Chinese"),
        GalereyaModel(R.drawable.anime_person,"аниме","Chinese"),
        GalereyaModel(R.drawable.mount_fuji,"mount","Chinese"),
        GalereyaModel(R.drawable.mount_fuji,"mount","Chinese"),
        GalereyaModel(R.drawable.mount_fuji,"аниме","Chinese"),
        GalereyaModel(R.drawable.mount_fuji,"mount","Chinese"),
    )
    fun getGalereyaModelList() = galereyaList
}