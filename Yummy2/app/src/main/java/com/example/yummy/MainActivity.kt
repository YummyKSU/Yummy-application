package com.example.yummy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
//import com.example.yummy.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signuppage.setOnClickListener {

            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}
