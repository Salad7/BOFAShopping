package com.example.myapplication.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShoppingObjects(val shoppingItems: List<ShoppingItem>) {
}