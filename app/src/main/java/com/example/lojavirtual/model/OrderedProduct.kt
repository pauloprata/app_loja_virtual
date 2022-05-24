package com.example.lojavirtual.model

import java.io.Serializable

data class OrderedProduct(val id: String, val product: Product, val quality: Int): Serializable
