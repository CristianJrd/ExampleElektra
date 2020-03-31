package com.example.exampleelektra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.exampleelektra.fragments.ProductsFragment
import com.example.exampleelektra.fragments.MyListener
import com.sento.fragments.utils.FragmentUtil
import kotlinx.android.synthetic.main.toolbar_nav_drawer.*

class MainActivity : AppCompatActivity(), MyListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toolbar_nav_drawer)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        Glide.with(this)
            .load(R.drawable.img_elektra)
            .placeholder(R.drawable.img_elektra)//defect image
            .into(iv_logo_elektra_toolbar)

        Glide.with(this)
            .load(R.drawable.img_cedit)
            .placeholder(R.drawable.img_cedit)//defect image
            .into(iv_logo_credito_elektra_toolbar)

        Glide.with(this)
            .load(R.drawable.ic_account_circle_black_24dp)
            .placeholder(R.drawable.ic_account_circle_black_24dp)//defect image
            .into(icon_profile_toolbar)

        Glide.with(this)
            .load(R.drawable.ic_shopping_cart)
            .placeholder(R.drawable.ic_shopping_cart)//defect image
            .into(icon_shopping_cart_toolbar)

        miInterface()
    }

    override fun miInterface() {
        val productsFragment = ProductsFragment.newInstance()
        productsFragment.setCallback(this)
        FragmentUtil.replaceFragment(this, R.id.nav_host_fragment_main, productsFragment)
    }
}
