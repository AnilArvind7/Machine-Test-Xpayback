package com.xpayback.machinetest.ui.user_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.xpayback.machinetest.R
import com.xpayback.machinetest.databinding.FragmentUserDetailsBinding
import com.xpayback.machinetest.models.network_result.NetworkResult
import com.xpayback.machinetest.models.user_details.UserDetailsResponse
import com.xpayback.machinetest.utils.hide
import com.xpayback.machinetest.utils.setCornerRadius
import com.xpayback.machinetest.utils.show
import com.xpayback.machinetest.utils.showToast

class UserDetailsFragment : Fragment() {
    companion object {
        private const val TAG = "UserDetailsFragment"
    }

    private lateinit var binding: FragmentUserDetailsBinding
    private val viewModel: UserDetailViewModel by viewModels(
        factoryProducer = { UsersDetailsViewModelFactory() }
    )
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUserDetails()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        val id = args.id
        if (id != 0)
            viewModel.fetchUserDetails(id = id)
    }

    private fun initViews() = binding.apply {
        activity?.window?.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.secondary2)
        view.setCornerRadius()
        ivUser.setCornerRadius()
        observeUserDetails()
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            })

        sideMenuButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeUserDetails() {
        viewModel.userDetailsResponseLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    hideProgress()
                    it.data?.let { it1 -> setUserData(it1) }
                }

                is NetworkResult.Error -> {
                    showToast(it.message ?: "")
                    hideProgress()
                }

                is NetworkResult.Loading -> {
                    showProgress()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUserData(item: UserDetailsResponse) = binding.apply {
        tvPageTitle.text = "${item.firstName} ${item.lastName}"
        Glide.with(ivUser.context)
            .load(item.image)
            .centerCrop()
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_place_holder)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivUser)

        layoutPersonalDetails.apply {
            textPersonalDetailsTitle.setCornerRadius(16f)
            textEmailValue.text = item.email ?: "-"
            textPhoneValue.text = item.phone ?: "-"
            textGenderValue.text = item.gender ?: "-"
            textAgeValue.text = (item.age ?: 0).toString()
            textBloodGroupValue.text = (item.bloodGroup ?: "-")
            textDobValue.text = item.birthDate ?: "-"
        }
        layoutAddressDetails.apply {
            textAddressDetailsTitle.setCornerRadius(16f)
            textAddressValue.text = item.address?.address ?: "-"
            textCityValue.text = item.address?.city ?: "-"
            textStateValue.text = item.address?.state ?: "-"
            textCountryValue.text = item.address?.country ?: "-"
            textPostalValue.text = item.address?.postalCode ?: "-"
        }
        layoutCompanyDetails.apply {
            textCompanyDetailsTitle.setCornerRadius(16f)
            textCompanyNameValue.text = item.company?.name ?: "-"
            textDepartmentValue.text = item.company?.department ?: "-"
            textDesignationValue.text = item.company?.title ?: "-"
            textCompanyCountryValue.text = item.company?.address?.country ?: "-"
        }
    }

    private fun showProgress() = binding.apply {
        progressCircular.show()
    }

    private fun hideProgress() = binding.apply {
        progressCircular.hide()
    }
}