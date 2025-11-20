package com.example.bossguer.features.carrito.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    @DrawableRes val image: Int
): Parcelable
