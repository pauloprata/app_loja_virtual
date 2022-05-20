package com.example.lojavirtual.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.lojavirtual.R
import com.example.lojavirtual.adapter.ProductCategoryAdapter.*
import com.example.lojavirtual.model.ProductCategory

class ProductCategoryAdapter(var list :List<ProductCategory>, var context: Context) : RecyclerView.Adapter<ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product_category, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: ProductCategory = list[position]
        holder.title.text = category.title
    }

    override fun getItemCount(): Int = list.size

    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val icon : ImageView = itemView.findViewById(R.id.iv_category)
        val title : TextView = itemView.findViewById(R.id.tv_category_title)
    }

}