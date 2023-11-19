package com.example.cse227etp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        auth = FirebaseAuth.getInstance()
        var editTextEmailAddress = findViewById<EditText>(R.id.editTextEmailAddress)
        var editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        var buttonLogin = findViewById<Button>(R.id.buttonLogin)






        buttonLogin.setOnClickListener {
            var email1 = editTextEmailAddress.text.toString()
            var pwd1: String = editTextPassword.text.toString()

            login(email1, pwd1)
        }
    }


    fun login(email1: String, pwd1: String) {
        val email = email1
        val password = pwd1
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("USER_NAME", "")

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, HomePage1::class.java)
                intent.putExtra("USER_NAME", userName)
                startActivity(intent)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                .show()
        }
    }

    fun goToRegister(view: View) {
        val intent = Intent(this, RegisterPage::class.java)
        startActivity(intent)
    }
}