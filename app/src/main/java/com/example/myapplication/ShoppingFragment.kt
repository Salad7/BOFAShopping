package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.api.ShoppingApi
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentShoppingBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create

class ShoppingFragment : Fragment() {

    lateinit var viewModel : ShoppingFragmentViewModel
    lateinit var binding : FragmentShoppingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ShoppingFragmentViewModel()
        var v = inflater.inflate(R.layout.fragment_shopping,container,false)
        binding = FragmentShoppingBinding.bind(v)
        viewLifecycleOwner.lifecycleScope.launch {
            val collector = viewModel.shoppingItems.collect {
                    Log.d("ShoppingFragment",it.toString()
                    )
            }
        }

        return binding.root
    }
}