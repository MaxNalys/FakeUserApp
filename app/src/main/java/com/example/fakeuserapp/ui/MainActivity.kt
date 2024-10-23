package com.example.fakeuserapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fakeuserapp.databinding.ActivityMainBinding
import com.example.fakeuserapp.viewmodel.UserViewModel
import com.example.fakeuserapp.viewmodel.UserViewModelFactory
import com.example.fakeuserapp.data.repository.UserRepository
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()

        binding.generateUserButton.setOnClickListener {
            userViewModel.refreshUser()
        }

        binding.iconPhone.setOnClickListener {
            binding.preinfoTextView.text = "Phone"
            binding.infoTextView.text = userViewModel.user.value?.phone
        }

        binding.iconMail.setOnClickListener {
            binding.preinfoTextView.text = "Email"
            binding.infoTextView.text = userViewModel.user.value?.email
        }

        binding.iconCustomer.setOnClickListener {
            binding.preinfoTextView.text = "Username"
            binding.infoTextView.text = userViewModel.user.value?.login?.username
        }

        binding.iconBirthday.setOnClickListener {
            binding.preinfoTextView.text = "Date of Birth"
            binding.infoTextView.text = userViewModel.user.value?.dob?.date?.substring(0, 10)
        }

        binding.iconLocation.setOnClickListener {
            binding.preinfoTextView.text = "Location"
            binding.infoTextView.text = "${userViewModel.user.value?.location?.city}, ${userViewModel.user.value?.location?.country}"
        }

        binding.iconPassword.setOnClickListener {
            binding.preinfoTextView.text = "Password"
            binding.infoTextView.text = userViewModel.user.value?.login?.password
        }
    }

    private fun setupObservers() {
        userViewModel.user.observe(this) { user ->
            binding.infoTextView.text = user?.phone ?: "No data"
            binding.preinfoTextView.text = "Phone"
            Picasso.get().load(user?.picture?.large).into(binding.profileImage)
        }
    }
}
