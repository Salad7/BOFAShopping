package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ShoppingApi
import com.example.myapplication.api.ShoppingItem
import com.example.myapplication.api.ShoppingObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import kotlin.coroutines.EmptyCoroutineContext.get

class ShoppingFragmentViewModel : ViewModel() {

    lateinit var shoppingRepo : ShoppingRepository
    //A mutable state flow which is changeable based on the http call
    var _shoppingItems : MutableStateFlow<List<ShoppingItem>> = MutableStateFlow(listOf())

    //State flow thats read only
    val shoppingItems : StateFlow<List<ShoppingItem>>
        get() =_shoppingItems.asStateFlow()


    init {

        viewModelScope.launch {
            shoppingRepo = ShoppingRepository()
            fetchProducts()


        }
    }

        suspend fun fetchProducts(){
        viewModelScope.launch {
            try {
                _shoppingItems.value = shoppingRepo.shoppingApi.fetchProducts()
            }
            catch (ex: Exception){
                Log.e("ShoppingFragmentViewModel", "Failed to fetch shopping items", ex)

            }

        }
    }




}