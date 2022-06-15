package com.example.alexpetrov.data.retrofit

import com.example.alexpetrov.data.model.HeroModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("api/all.json")
    fun getDataHeroAsync(): Deferred<List<HeroModel>>
}