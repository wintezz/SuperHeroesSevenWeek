package com.example.alexpetrov.data.retrofit

import com.example.alexpetrov.data.model.HeroModel

sealed class AppState {
    data class Success(val dataModel: List<HeroModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
}