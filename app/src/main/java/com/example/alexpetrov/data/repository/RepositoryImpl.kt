package com.example.alexpetrov.data.repository

import com.example.alexpetrov.data.DataSource
import com.example.alexpetrov.data.model.HeroModel
import com.example.alexpetrov.data.retrofit.RetrofitFactory

class RepositoryImpl(
    private val dataSource: DataSource<List<HeroModel>> = RetrofitFactory()
) : Repository<List<HeroModel>> {
    override suspend fun getData(): List<HeroModel> {
        return dataSource.getData()
    }
}