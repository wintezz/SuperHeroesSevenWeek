package com.example.alexpetrov.data.retrofit

import com.example.alexpetrov.data.DataSource
import com.example.alexpetrov.data.model.HeroModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory : DataSource<List<HeroModel>> {

    override suspend fun getData(): List<HeroModel> {
        return getService().getDataHeroAsync().await()
    }

    private fun getService(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    companion object {
        private const val BASE_URL = "https://akabab.github.io/superhero-api/"
    }
}