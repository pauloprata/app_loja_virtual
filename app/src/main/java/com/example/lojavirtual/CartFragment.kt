package com.example.lojavirtual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojavirtual.adapter.CartAdapter
import com.example.lojavirtual.adapter.ProductAdapter
import com.example.lojavirtual.model.*

class CartFragment: Fragment() {
    lateinit var recycleAdapterCart: RecyclerView
    lateinit var order: Order


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cart, container, false)
        recycleAdapterCart = view.findViewById(R.id.rv_cart)

        val product1: Product = Product(
            "1",
            "Camiseta 88",
            ProductCategory("id", "Camisetas"),
            "Camiseta Super Leve",
            100.00,
            arrayListOf(ProductColor("1", "Branco", "#ffffff"), ProductColor("2","Preto","#000000")),
            arrayListOf(ProductSize("1","P"), ProductSize("2","M")),
            emptyList())

        val product2: Product = Product(
            "1",
            "Calça Jeans 88",
            ProductCategory("id", "Calças", ),
            "Calça Confortavel",
            50.00,
            arrayListOf(ProductColor("1", "Branco", "#ffffff"), ProductColor("2","Preta","#000000")),
            arrayListOf(ProductSize("1","P"), ProductSize("2","M")),
            emptyList())

        val adapterCart = CartAdapter(arrayListOf(OrderedProduct("1",product1,3),OrderedProduct("2",product2,4),
          OrderedProduct( "3",product1,5),OrderedProduct
         ("4",product2,6)),
          requireContext())

        recycleAdapterCart.adapter = adapterCart
        recycleAdapterCart.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)

        return view
    }
}