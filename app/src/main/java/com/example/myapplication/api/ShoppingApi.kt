package com.example.myapplication.api

import retrofit2.http.GET

interface ShoppingApi  {
    @GET("/products")
    suspend fun fetchProducts() : String
}