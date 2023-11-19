package com.example.cse227etp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cse227etp.databinding.ActivityHomePage1Binding
import com.google.android.material.navigation.NavigationView

class HomePage1 : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener{

    private lateinit var fragmentManager:FragmentManager
    private lateinit var binding: ActivityHomePage1Binding
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageViewProfile: ImageView
    private lateinit var navHeaderUserName: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomePage1Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val navigationView = findViewById<NavigationView>(R.id.navigation_drawer)
        val headerView = navigationView.getHeaderView(0)
        imageViewProfile = headerView.findViewById(R.id.imageViewProfile)
        navHeaderUserName = headerView.findViewById(R.id.textViewUserName)



        imageViewProfile.setOnClickListener {
            openFileChooser()
        }

        val userName = intent.getStringExtra("USER_NAME")
        userName?.let {
            navHeaderUserName.text = it
        }
        //val textViewUserName = headerView.findViewById<TextView>(R.id.textViewUserName)
        //textViewUserName.text = userName

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background=null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.bottom_dashboard -> openFragment(DashBoardFragment())
                R.id.bottom_location -> openFragment(LocationFragment())
                R.id.bottom_canvas -> openFragment(CanvasFragment())
            }
            true
        }
        fragmentManager=supportFragmentManager
        openFragment(HomeFragment())



    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri = data.data!!
            imageViewProfile.setImageURI(imageUri)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> openFragment(HomeFragment())
            R.id.nav_dashboard ->openFragment(DashBoardFragment())
            R.id.nav_location -> openFragment(LocationFragment())
            R.id.nav_setting -> openFragment(SettingsFragment())
            R.id.nav_feedback ->openFragment(FeedbackFragment())

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }
    private fun openFragment(fragment:Fragment){
        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }
}