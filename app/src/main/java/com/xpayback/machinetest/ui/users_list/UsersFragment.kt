package com.xpayback.machinetest.ui.users_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xpayback.machinetest.R
import com.xpayback.machinetest.databinding.FragmentUsersBinding
import com.xpayback.machinetest.models.network_result.NetworkResult
import com.xpayback.machinetest.ui.users_list.adapter.UsersAdapter
import com.xpayback.machinetest.utils.hide
import com.xpayback.machinetest.utils.setCornerRadius
import com.xpayback.machinetest.utils.show
import com.xpayback.machinetest.utils.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UsersFragment : Fragment() {
    companion object {
        private const val TAG = "UsersFragment"
    }

    private lateinit var binding: FragmentUsersBinding
    private val viewModel: UsersListViewModel by viewModels(
        factoryProducer = { UsersLIstViewModelFactory() }
    )
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = binding.apply {
        activity?.window?.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.secondary)
        ivBack.setCornerRadius(8f)
        usersAdapter = UsersAdapter(list = mutableListOf()) {
            findNavController().navigate(
                UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(
                    id = it.id ?: 0
                )
            )
        }
        recyclerviewUsers.adapter = usersAdapter
        observeUsers()
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (v.getChildAt(0).bottom <= nestedScrollView.height + scrollY) {
                if (!viewModel.isLockPaging) {
                    if (viewModel.currentPage <= viewModel.totalPage) {
                        viewModel.currentPage++
                        viewModel.isLockPaging = true
                        viewModel.fetchUsers()
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchUsers()
    }

    private fun observeUsers() {
        viewModel.userResponseLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    lifecycleScope.launch {
                        delay(2100L)
                        hideProgress()
                        it.data?.users?.let { it1 ->
                            viewModel.list.addAll(it1)
                            usersAdapter.updateList(viewModel.list)
                            if (viewModel.currentPage == 1) binding.recyclerviewUsers.scheduleLayoutAnimation()
                        }
                        viewModel.totalPage = (it.data?.total ?: 0) / viewModel.perPage
                        viewModel.isLockPaging = false
                    }
                }

                is NetworkResult.Error -> {
                    hideProgress()
                    showToast(it.message ?: "")
                    viewModel.currentPage--
                    viewModel.isLockPaging = false
                }

                is NetworkResult.Loading -> {
                    showProgress()
                }
            }
        }
    }

    private fun showProgress() = binding.apply { progressCircular.show() }
    private fun hideProgress() = binding.apply { progressCircular.hide() }
}