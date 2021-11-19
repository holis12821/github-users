package com.example.githubuserapp.presentation.ui.fragment.favorite

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseFragment
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.databinding.FragmentFavoriteBinding
import com.example.githubuserapp.external.extension.setUpVerticalLayoutManager
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.activity.main.MainActivity
import com.example.githubuserapp.presentation.ui.adapter.UsersAdapter
import com.example.githubuserapp.presentation.ui.adapter.callback.AdapterClickListener
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel by viewModel<FavoriteFragmentViewModel>()

    //setup adapter
    private val adapter = UsersAdapter(this@FavoriteFragment).apply {
        listener = object : AdapterClickListener<ItemsItem> {
            override fun onItemClickCallback(data: ItemsItem, fragment: Fragment) {
                if (fragment is FavoriteFragment) {
                    fragment.usersDetails(itemItems = data)
                }
            }

            override fun onViewClickCallback(view: View, data: ItemsItem, fragment: Fragment) {

            }

        }
    }

    override fun getResLayoutId(): Int = R.layout.fragment_favorite

    override fun onViewCreated() {
        //to do code in initiate here
        initView()
        onObserverState()
    }

    private fun initView() {
        binding?.data = viewModel
        setUpAdapter()
    }

    private fun setUpAdapter() {
        binding?.rvFavorite?.setUpVerticalLayoutManager()
        binding?.rvFavorite?.adapter = adapter
        binding?.rvFavorite?.setHasFixedSize(true)
    }

    private fun onObserverState() {
        viewModel.stateData.observe(viewLifecycleOwner, { state ->
            handleState(state = state)
        })
    }

    private fun handleState(state: FavoriteViewState) {
        when (state) {
            is FavoriteViewState.Init -> onInitState()
            is FavoriteViewState.Progress -> onProgress(state.isLoading)
            is FavoriteViewState.ShowMessage -> onShowMessage(state.message)
            is FavoriteViewState.ShowDataFavorite -> onShowFavorite(state.list)
        }
    }


    private fun onInitState() {
        binding?.rvFavorite?.viewGone = true
    }

    private fun onProgress(loading: Boolean) {
        if (loading) showDialogProgress() else hideProgress()
    }

    private fun onShowMessage(message: String) {
        showToastDanger(requireActivity()) { message }
    }

    private fun onShowFavorite(list: List<ItemsItem>?) {
        if (list.isNullOrEmpty()) {
            binding?.rvFavorite?.viewGone = true
        } else {
            binding?.rvFavorite?.viewVisible = true
            adapter.setData(list = list)
        }
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.showBottomNavigationView()
        }
    }


    /**
     * A function to navigate to the Users Details Fragment.
     *
     * @param itemItems
     */
    fun usersDetails(itemItems: ItemsItem) {
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavigationView()
        }

        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteUsersToDetailUsers(itemItems)
        )
    }
}