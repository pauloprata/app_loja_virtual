package com.example.lojavirtual

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lojavirtual.model.ProductCategory

class CartActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var textTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = "Meu Carrinho"



        val fragment = CartFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_cart, fragment)
            .commit()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

}