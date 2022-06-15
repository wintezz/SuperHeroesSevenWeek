package com.example.alexpetrov.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroModel(
    val id: Int,
    val name: String,
    val powerstats: PowerStats,
    val images: Img
) : Parcelable {

    @Parcelize
    data class PowerStats(
        val intelligence: Int,
        val strength: Int,
        val speed: Int,
        val durability: Int,
        val power: Int,
        val combat: Int
    ) : Parcelable

    @Parcelize
    data class Img(
        val md: String,
    ) : Parcelable
}