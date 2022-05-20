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
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawlerLayout: DrawerLayout
    lateinit var  navigationView: NavigationView
    lateinit var textTitle: TextView
    lateinit var textLogin: TextView


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

  val productItem: LinearLayout = findViewById(R.id.all_product_item)
      productItem.setOnClickListener{
          val intent: Intent = Intent(this, ProductDetailActivity::class.java)
          startActivity(intent)
      }
        textLogin =navigationView.getHeaderView(0). findViewById(R.id.header_prof_name)
        textLogin.setOnClickListener {
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)

        }
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
            R.id.nav_category -> Toast.makeText(this, "Categoria", Toast.LENGTH_LONG).show()
            R.id.nav_order -> Toast.makeText(this, "Ordem", Toast.LENGTH_LONG).show()
            R.id.nav_cart -> Toast.makeText(this, "Carrinho", Toast.LENGTH_LONG).show()
            R.id.nav_logout -> Toast.makeText(this, "Sair", Toast.LENGTH_LONG).show()
        }
        drawlerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

