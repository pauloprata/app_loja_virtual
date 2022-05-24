package com.example.lojavirtual.model

import java.io.Serializable


data class Product(

     val id: String,
     val title: String,
     val category: ProductCategory,
     val description: String,
     val price: Double,
     val colors: List<ProductColor>,
     val size: List<ProductSize>,
     val images: List<ProductImage>
    ) : Serializable


