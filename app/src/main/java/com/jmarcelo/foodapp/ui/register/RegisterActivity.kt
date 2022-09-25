package com.jmarcelo.foodapp.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.jmarcelo.foodapp.databinding.ActivityRegisterBinding
import com.jmarcelo.foodapp.ui.register.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel.registerUserSucces.observe(this){
            when{
                it.success !=null -> Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                it.error.isNotEmpty() -> Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        binding.buttonRegister.setOnClickListener {
            registerViewModel.registerUser(binding.editEmail.text.toString(),
            binding.editPassword.text.toString())
        }
    }
}