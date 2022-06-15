package com.example.alexpetrov.data

interface DataSource<T> {
    suspend fun getData(): T
}