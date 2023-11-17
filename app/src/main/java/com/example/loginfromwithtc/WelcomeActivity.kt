package com.example.loginfromwithtc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginfromwithtc.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(SigInActivity.KEY2)
        val mail = intent.getStringExtra(SigInActivity.KEY1)
        val userId = intent.getStringExtra(SigInActivity.KEY3)

        binding.txtWelcome.text = "Welcome $name"
        binding.txtMail.text = "Mail : $mail"
        binding.txtUserID.text = "UserID : $userId"

    }
}