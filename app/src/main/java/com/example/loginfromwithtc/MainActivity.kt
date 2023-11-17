package com.example.loginfromwithtc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginfromwithtc.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // have an account Button code here
        binding.txtHaveAccount.setOnClickListener {
            val signUpIntent = Intent(this, SigInActivity::class.java)
            startActivity(signUpIntent)
        }

        // sign Up button code here
        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text.toString()
            val unique = binding.etUserId.text.toString()
            val mail = binding.etMail.text.toString()
            val password = binding.etPassword.text.toString()
            val check = binding.checkBox.isChecked

            if (name.isNotEmpty() && unique.isNotEmpty() && mail.isNotEmpty() && password.isNotEmpty() && check){
                val user = User(name, mail, password, unique)
                database = FirebaseDatabase.getInstance().getReference("Users")
                database.child(unique).setValue(user).addOnSuccessListener {
                    binding.etName.text?.clear()
                    binding.etMail.text?.clear()
                    binding.etPassword.text?.clear()
                    binding.etUserId.text?.clear()
                    binding.checkBox.isChecked = false
                    Toast.makeText(this, "User Registered Successfully",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "User Registered Failed",Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, "Please Fill all The Field",Toast.LENGTH_SHORT).show()
            }

        }

    }
}