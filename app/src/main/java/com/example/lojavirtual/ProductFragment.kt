package com.example.lojavirtual

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojavirtual.adapter.ProductAdapter
import com.example.lojavirtual.model.Product
import com.example.lojavirtual.model.ProductCategory

class ProductFragment : Fragment() {
    lateinit var recycleAdapter: RecyclerView
    lateinit var arrayProduct: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            arrayProduct = (arguments?.getSerializable("CATEGORY") as ProductCategory).products
        }
    }
        override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?    ): View? {
            val view: View = inflater.inflate(R.layout.fragment_product, container,false)
            recycleAdapter = view.findViewById(R.id.rv_product)

            val adapterProduct = ProductAdapter(arrayProduct, requireContext())

            recycleAdapter.adapter = adapterProduct
            recycleAdapter.layoutManager = GridLayoutManager(requireContext(), 2)

            return view



    }
}