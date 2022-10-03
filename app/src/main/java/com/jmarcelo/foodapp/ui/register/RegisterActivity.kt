package com.jmarcelo.foodapp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.jmarcelo.foodapp.common.ProgressDialogCustom
import com.jmarcelo.foodapp.databinding.ActivityRegisterBinding
import com.jmarcelo.foodapp.domain.model.UserDomain
import com.jmarcelo.foodapp.ui.home.HomeActivity
import com.jmarcelo.foodapp.ui.register.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()
    lateinit var progressDialogCustom: ProgressDialogCustom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialogCustom = ProgressDialogCustom(this)

        registerViewModel.registerUserSucces.observe(this){
            if (it.loadingProgressBar) progressDialogCustom.showLoadingDialog() else progressDialogCustom.hideLoadingDialog()
            when{
                it.success -> startActivity(Intent(this,HomeActivity::class.java))
                it.error.isNotEmpty() -> Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonRegister.setOnClickListener {
            val userDomain=UserDomain(user = binding.editUser.text.toString(), name = binding.editName.text.toString(),
                lastName = binding.editLastName.text.toString(), phone = binding.editPhone.text.toString(),
                address = binding.editAddress.text.toString(), email = binding.editEmail.text.toString(), password = binding.editPassword.text.toString())
            registerViewModel.registerUser(userDomain)
        }
    }

}