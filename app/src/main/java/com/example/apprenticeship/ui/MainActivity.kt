package com.example.apprenticeship.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apprenticeship.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

private lateinit var binding: ActivityMainBinding
// private lateinit var navController: NavController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // navController = findNavController(R.id.nav_host_fragment)
        // NavigationUI.setupActionBarWithNavController(this, navController)
    }

    // override fun onSupportNavigateUp(): Boolean {
    //    return navController.navigateUp()
    // }
}
