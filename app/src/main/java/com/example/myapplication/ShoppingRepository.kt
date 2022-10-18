package com.example.myapplication

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ShoppingApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class ShoppingRepository {
    lateinit var retrofit: Retrofit
    lateinit var shoppingApi: ShoppingApi


    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
//        Log.d("Shopping Endpoint",retrofit.)
        shoppingApi = retrofit.create<ShoppingApi>()

    }

//    suspend fun fetchProducts(){
//        viewModelScope.launch {
//            _shoppingItems.value = shoppingApi.fetchProducts()
//        }
//    }


}