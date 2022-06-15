package com.example.alexpetrov.data.repository

interface Repository<T> {
    suspend fun getData(): T
}