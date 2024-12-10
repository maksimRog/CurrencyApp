package com.test.currency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.test.currency.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            binding.navHostFragmentActivityMain.findNavController().popBackStack()
        }
    }

    override fun onStart() {
        super.onStart()
        NavigationUI.setupWithNavController(binding.navView,binding.navHostFragmentActivityMain.findNavController())

    }
    fun setTitle(title: String) {
        binding.title.text = title
    }

    fun setBottomBarVisibility(visible: Boolean) {
        binding.navView.isVisible = visible
    }

    fun backButtonVisibility(visible: Boolean) {
        binding.backButton.isVisible = visible
    }
}






