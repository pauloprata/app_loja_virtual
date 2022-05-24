package com.example.lojavirtual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojavirtual.adapter.ProductAdapter
import com.example.lojavirtual.adapter.ProductCategoryAdapter
import com.example.lojavirtual.model.Product
import com.example.lojavirtual.model.ProductCategory
import com.example.lojavirtual.model.ProductColor
import com.example.lojavirtual.model.ProductSize
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ProductCategoryFragment.Callback{

    lateinit var toolbar: Toolbar
    lateinit var drawlerLayout: DrawerLayout
    lateinit var  navigationView: NavigationView
    lateinit var textTitle: TextView
    lateinit var textLogin: TextView
    lateinit var recycleAdapter: RecyclerView
    lateinit var recyclerViewProduct: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawlerLayout = findViewById(R.id.nav_drawer_layout)

        //configurando o menu com toggle
        val toogle : ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawlerLayout, toolbar, R.string.toggle_open, R.string.toggle_close)
        drawlerLayout.addDrawerListener(toogle)
        toogle.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        supportActionBar?.setDisplayShowTitleEnabled(false)

       textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.app_name)

        textLogin =navigationView.getHeaderView(0). findViewById(R.id.header_prof_name)
        textLogin.setOnClickListener {
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)

        }
        recycleAdapter = findViewById(R.id.main_product_category)

        val arrayCategory = arrayListOf<ProductCategory>(ProductCategory("1", "Camisetas",fillProduct()), ProductCategory( "2", "Calças",fillProduct()))
        val adapterCategory = ProductCategoryAdapter(arrayCategory, this)

        recycleAdapter.adapter = adapterCategory
        recycleAdapter.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        recyclerViewProduct = findViewById(R.id.iv_product)

        val adapterProduct = ProductAdapter(fillProduct(), this)

        recyclerViewProduct.adapter = adapterProduct
        recyclerViewProduct.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)


    }

    fun fillProduct(): List<Product>{
        val product1: Product = Product(
            "1",
            "Camiseta 88",
            ProductCategory("id", "Camisetas"),
            "Camiseta Super Leve",
            100.00,
            arrayListOf(ProductColor("1", "Branco", "#ffffff"),ProductColor("2","Preto","#000000")),
            arrayListOf(ProductSize("1","P"), ProductSize("2","M")),
            emptyList())

        val product2: Product = Product(
            "1",
            "Calça Jeans 88",
            ProductCategory("id", "Calças", ),
            "Calça Confortavel",
            50.00,
            arrayListOf(ProductColor("1", "Branco", "#ffffff"),ProductColor("2","Preta","#000000")),
            arrayListOf(ProductSize("1","P"), ProductSize("2","M")),
            emptyList())

        return arrayListOf(product1,product2)
    }

    override fun onBackPressed() {
        if (drawlerLayout.isDrawerOpen(GravityCompat.START))
            drawlerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_account -> {
                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)

            }
            R.id.nav_category -> {
                val intent = Intent(this, ProductCategoryActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_order -> Toast.makeText(this, "Ordem", Toast.LENGTH_LONG).show()
            R.id.nav_cart -> Toast.makeText(this, "Carrinho", Toast.LENGTH_LONG).show()
            R.id.nav_logout -> Toast.makeText(this, "Sair", Toast.LENGTH_LONG).show()
        }
        drawlerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun itemSelected(category: ProductCategory) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }
}

