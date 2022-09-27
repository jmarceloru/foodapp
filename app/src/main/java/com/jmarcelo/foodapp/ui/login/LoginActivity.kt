package com.jmarcelo.foodapp.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.auth.FirebaseAuth
import com.jmarcelo.foodapp.common.ProgressDialogCustom
import com.jmarcelo.foodapp.databinding.ActivityLoginBinding
import com.jmarcelo.foodapp.ui.home.HomeActivity
import com.jmarcelo.foodapp.ui.login.viewmodel.LoginViewModel
import com.jmarcelo.foodapp.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel:LoginViewModel by viewModels()
    @Inject
    lateinit var googleSignInClient: GoogleSignInClient
    @Inject
    lateinit var auth: FirebaseAuth
    @Inject
    lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var launcher: ActivityResultLauncher<Intent>

    lateinit var progressDialogCustom: ProgressDialogCustom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialogCustom = ProgressDialogCustom(this)

        loginViewModel.authSuccesfull.observe(this){
            when{
                it.error.isNotEmpty() -> Toast.makeText(this, "error ${it.error}", Toast.LENGTH_SHORT).show()
                it.success -> startActivity(Intent(this,HomeActivity::class.java))
            }
        }

        loginViewModel.loading.observe(this){
         //   binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
            if (it) progressDialogCustom.showLoadingDialog() else progressDialogCustom.hideLoadingDialog()
        }

        binding.buttonLogin.setOnClickListener {
            loginViewModel.authUser(binding.editEmail.text.toString(),
                binding.editPassword.text.toString())
        }

        binding.labelLinkRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        binding.buttonLoginGoogle.setOnClickListener{
            if(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
                //Google Play Services are available
                val sigInIntent = googleSignInClient.signInIntent
                launcher.launch(sigInIntent)
            } else {
                Toast.makeText(this, "no tienes google play services", Toast.LENGTH_SHORT).show()
                //Google Play Services are not available, or not updated
            }
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                //ejecutar viewmodel
                loginViewModel.authUserGoogle(task)
                //handleResults(task)
            }
        }
    }

    /*private fun handleResults(task: Task<GoogleSignInAccount>) {
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
    }*/
}