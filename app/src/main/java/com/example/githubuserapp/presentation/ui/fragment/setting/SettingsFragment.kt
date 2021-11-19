package com.example.githubuserapp.presentation.ui.fragment.setting

import androidx.appcompat.app.AppCompatDelegate
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseFragment
import com.example.githubuserapp.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel by viewModel<SettingsViewModel>()


    override fun getResLayoutId(): Int = R.layout.fragment_settings

    override fun onViewCreated() {
        //to do code in initiate here
        initView()
        onObserver()
    }


    private fun initView() {
        binding?.data = viewModel
        setUpNavigation()
        binding?.switchTheme?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveThemeSettings(isChecked)
        }
    }

    private fun setUpNavigation() {
        setHasOptionsMenu(true)
    }

    private fun onObserver() {
        viewModel.getThemeSettings().observe(viewLifecycleOwner, { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding?.switchTheme?.isChecked = true
                binding?.ivDarkMode?.setImageResource(R.drawable.ic_baseline_dark_mode_24_white)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding?.switchTheme?.isChecked = false
                binding?.ivDarkMode?.setImageResource(R.drawable.ic_baseline_dark_mode_24_dark_grey)
            }
        })
    }

}