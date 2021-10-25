package com.example.githubuserapp.presentation.ui.activity.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.databinding.ActivitySettingsBinding
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity: BaseActivity<ActivitySettingsBinding>() {

    private val viewModel by viewModel<SettingsViewModel>()

    private lateinit var navigationView: NavigationView

    override fun getResLayoutId(): Int = R.layout.activity_settings

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        onObserver()
    }

    private fun initView() {
        binding.data = viewModel
        setUpNavigation()
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveThemeSettings(isChecked)
        }
    }

    private fun setUpNavigation() {
        navigationView = NavigationView(this)
            .setOnBackPressedIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            .setupTitle(resources.getString(R.string.settings))
            .setNavigation {
                when(it.id) {
                    R.id.navigation_back -> {
                        onBackPressed()
                    }
                }
            }
    }

    private fun onObserver() {
        viewModel.getThemeSettings().observe(this, { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        })
    }
}