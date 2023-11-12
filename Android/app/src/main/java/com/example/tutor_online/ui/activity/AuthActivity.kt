package com.example.tutor_online.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tutor_online.R
import com.example.tutor_online.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private var _binding: ActivityAuthBinding? = null
    private val binding: ActivityAuthBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.auth_nav_host_fragment)
        setupActionBarWithNavController(navController)
        val actionBar = actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun createNotification(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }
}