package com.example.lojavirtual

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lojavirtual.databinding.ActivityProductDetailBinding
import com.example.lojavirtual.model.Product
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ProductDetailActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var product: Product
    lateinit var productPrice: TextView
    lateinit var productDesc: TextView
    lateinit var chipGroupColor: ChipGroup
    lateinit var chipGroupSize: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        product = intent.getSerializableExtra("PRODUCT") as Product


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = product.title

        productPrice = findViewById(R.id.tv_product_price)
        productPrice.text = "R$${product.price}"

        productDesc = findViewById(R.id.tv_product_desc)
        productDesc.text = product.description

        chipGroupColor= findViewById(R.id.ch_group_color)
        fillChipColor()
        chipGroupSize = findViewById(R.id.ch_group_size)
        fillChipSize()
       }

     fun fillChipColor(){
         val colors = product.colors

         for (color in colors){
             val chip = Chip(ContextThemeWrapper(chipGroupColor.context, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Choice))
             chip.text = color.name
             chip.isCheckable = true
             chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor(color.code))
             chip.chipStrokeWidth = 1.0F
             chip.chipStrokeColor = ColorStateList.valueOf(Color.GRAY)
             chip.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimaryDark)))
             chipGroupColor.addView(chip)
         }
     }
    fun fillChipSize(){
        val sizes = product.size

        for (size in sizes){
            val chip = Chip(ContextThemeWrapper(chipGroupSize.context, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Choice))
            chip.text = size.size
            chip.isCheckable = true
            chip.chipBackgroundColor = ColorStateList.valueOf(Color.WHITE)
            chip.chipStrokeWidth = 1.0F
            chip.chipStrokeColor = ColorStateList.valueOf(Color.GRAY)
            chip.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimaryDark)))
            chipGroupSize.addView(chip)
        }
    }

    //voltar quando clicar na seta
       override fun onSupportNavigateUp(): Boolean {
           onBackPressed()
           return true

    }
}