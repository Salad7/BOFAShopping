package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ShoppingApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import kotlin.coroutines.EmptyCoroutineContext.get

class ShoppingFragmentViewModel : ViewModel() {
    lateinit var retrofit: Retrofit
    lateinit var shoppingApi: ShoppingApi
    //A mutable state flow which is changeable based on the http call
    var _shoppingItems : MutableStateFlow<String> = MutableStateFlow("")

    //State flow thats read only
    val shoppingItems : StateFlow<String>
        get() =_shoppingItems.asStateFlow()
    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        shoppingApi = retrofit.create<ShoppingApi>()
        viewModelScope.launch {
            fetchProducts()

        }
    }

    suspend fun fetchProducts(){
        viewModelScope.launch {
                _shoppingItems.value = shoppingApi.fetchProducts()
        }
    }
}