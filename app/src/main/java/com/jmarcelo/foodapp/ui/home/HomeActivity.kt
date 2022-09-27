package com.jmarcelo.foodapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.jmarcelo.foodapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding

    @Inject
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butonLogout.setOnClickListener{
            auth.signOut()
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        (auth.currentUser?.email+" "+auth.currentUser?.uid).also { binding.labelUser.text = it }
    }
}