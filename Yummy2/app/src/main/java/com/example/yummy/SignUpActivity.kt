package com.example.yummy


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.user_registration.*
import com.google.firebase.auth.FirebaseAuth
import com.example.yummy.R


class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_registration)

       signuppage1.setOnClickListener {
            val email = newEmail.text.toString()
            val password = newpassword.text.toString()
            val userName = newusername.text.toString()
            val phoneNumber = newphonenumber.text.toString()

            Log.d("SignUpActivity", "Email is $email")
            Log.d("SignUpActivity", "Password is $password")
            Log.d("SignUpActivity", "User Name is $userName")
           Log.d("SignUpActivity", "Phone Number is $phoneNumber")

            //Firebase authentication
          FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener {
                       if (!it.isSuccessful) return@addOnCompleteListener

                        //else if successful
                        Log.d("Main", "Successfully Created user with uid:${it.result.user.uid}")
                    }

        }

        loginpage22.setOnClickListener {
            finish()
        }


    }
}






