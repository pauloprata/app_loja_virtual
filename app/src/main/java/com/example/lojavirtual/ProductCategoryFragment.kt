package com.example.lojavirtual

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojavirtual.adapter.ProductCategoryAdapter
import com.example.lojavirtual.model.ProductCategory

class ProductCategoryFragment : Fragment() {

    lateinit var recycleAdapter: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {

      val view: View = inflater.inflate(R.layout.fragment_product_category, container)
        recycleAdapter = view.findViewById(R.id.rv_product_category)
        val arrayCategory = arrayListOf<ProductCategory>(
            ProductCategory("1", "Camisetas", MainActivity().fillProduct()),
            ProductCategory( "2", "Calças", MainActivity().fillProduct()),
            ProductCategory("3","Calçados", MainActivity().fillProduct()),
            ProductCategory("4","Meias", MainActivity().fillProduct()))
        val adapterCategory = ProductCategoryAdapter(arrayCategory, requireContext())

        recycleAdapter.adapter = adapterCategory
        recycleAdapter.layoutManager = GridLayoutManager(requireContext(), 2)

        return view

    }
    interface Callback  {
        fun itemSelected(category: ProductCategory)
    }
}