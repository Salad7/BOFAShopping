package com.example.myapplication.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShoppingItem(val title :String, val id :Int, val price :Float, val image :String, val rating :Rating, val description :String) {

}