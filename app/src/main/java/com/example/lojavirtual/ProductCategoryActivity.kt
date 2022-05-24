package com.example.lojavirtual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojavirtual.adapter.ProductCategoryAdapter
import com.example.lojavirtual.model.ProductCategory

class ProductCategoryActivity : AppCompatActivity(), ProductCategoryFragment.Callback {
    //lateinit var textTitle: TextView
    //lateinit var toolbar: Toolbar
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var textTitle: TextView
    var isTablet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = "Categoria Produtos"

        isTablet = findViewById<View>(R.id.fragment_product) != null


       }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

    override fun itemSelected(category: ProductCategory) {
        if(isTablet){
            val args = Bundle()
            args.putSerializable("CATEGORY", category)
            val fragment = ProductFragment()
            fragment.arguments = args
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_product, fragment)
                .commit()

        }else{
            val intent = Intent(this, ProductActivity::class.java)
            intent.putExtra("CATEGORY", category)
            startActivity(intent)
        }
    }

}