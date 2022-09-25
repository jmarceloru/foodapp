package com.jmarcelo.foodapp.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jmarcelo.foodapp.R
import com.jmarcelo.foodapp.databinding.ActivityLoginBinding
import com.jmarcelo.foodapp.ui.home.HomeActivity
import com.jmarcelo.foodapp.ui.login.viewmodel.LoginViewModel
import com.jmarcelo.foodapp.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel:LoginViewModel by viewModels()

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.authSuccesfull.observe(this){
            when{
                it.error.isNotEmpty() -> Toast.makeText(this, "error ${it.error}", Toast.LENGTH_SHORT).show()
                it.success != null -> Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.loading.observe(this){
            binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
        }

        googleSigIn()
    }

    private fun googleSigIn() {
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

    }

    override fun onResume() {
        super.onResume()

        binding.buttonLogin.setOnClickListener {
            loginViewModel.authUser(binding.editEmail.text.toString(),
            binding.editPassword.text.toString())
        }

        binding.labelLinkRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        binding.buttonLoginGoogle.setOnClickListener{
            val sigInIntent = googleSignInClient.signInIntent
            launcher.launch(sigInIntent)
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
                if (result.resultCode == Activity.RESULT_OK){
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleResults(task)
                }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                auth.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}