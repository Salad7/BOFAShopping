package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.api.ShoppingApi
import com.example.myapplication.api.ShoppingItem
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentShoppingBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create

class ShoppingFragment : Fragment() {

    lateinit var viewModel : ShoppingFragmentViewModel
    lateinit var binding : FragmentShoppingBinding
    lateinit var adapter: ShoppingAdapter
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
                Log.d("ShoppingFragment",it.toString())
                adapter = ShoppingAdapter(it,this@ShoppingFragment.requireContext())
                binding.rvShopping.layoutManager = LinearLayoutManager(this@ShoppingFragment.requireContext())
                binding.rvShopping.adapter = adapter
                adapter.notifyDataSetChanged()

            }
        }

        return binding.root
    }


    class ShoppingViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {
        lateinit var icon : ImageView
        lateinit var title : TextView
        lateinit var desc :TextView
        lateinit var price :TextView
        lateinit var rating :RatingBar

        init {
            icon = itemView.findViewById(R.id.iv_icon)
            title = itemView.findViewById(R.id.tv_title)
            desc = itemView.findViewById(R.id.tv_description)
            price = itemView.findViewById(R.id.tv_price)
            rating = itemView.findViewById(R.id.rb_rating)
            rating.numStars = 5
        }
    }

    class ShoppingAdapter(itemsList :List<ShoppingItem>, ctx : Context) : RecyclerView.Adapter<ShoppingViewHolder>(){
        var items = itemsList
        var context = ctx
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
            var v = LayoutInflater.from(context).inflate(R.layout.custom_shopping_item,parent,false)
            return ShoppingViewHolder(v)
        }

        override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
            holder.title.setText(items.get(position).title)
            holder.desc.setText(items.get(position).description)
            holder.price.setText(items.get(position).price.toString())
            holder.icon.load(items.get(position).image) {
                placeholder(R.drawable.ic_launcher_foreground)
            }
            holder.rating.rating = items.get(position).rating.rate
        }

        override fun getItemCount(): Int {
            return items.size
        }

    }
}