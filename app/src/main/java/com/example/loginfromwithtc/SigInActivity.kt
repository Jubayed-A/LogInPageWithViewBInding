package com.example.loginfromwithtc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginfromwithtc.databinding.ActivityMainBinding
import com.example.loginfromwithtc.databinding.ActivitySigInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SigInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySigInBinding
    lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1 = "com.example.loginfromwithtc.email"
        const val KEY2 = "com.example.loginfromwithtc.name"
        const val KEY3 = "com.example.loginfromwithtc.userId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // MainActivity or sigUp activity code here
        binding.txtDontHaveAccount.setOnClickListener {
            val intentSignUp = Intent(this, MainActivity::class.java)
            startActivity(intentSignUp)
        }

        // Sign In Button code here
        binding.btnSignIn.setOnClickListener {
            val uniqueId = binding.etCheckUser.text.toString()
            if (uniqueId.isNotEmpty()){
                readData(uniqueId)
            } else{
                Toast.makeText(this, "Please enter User ID",Toast.LENGTH_SHORT).show()
            }
        }
    }

    // readData method code here
    private fun readData(uniqueId : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            // check user exit or not
            if (it.exists()){
                // go to new intent and assign value
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value
                val intentNew = Intent(this, WelcomeActivity::class.java)
                intentNew.putExtra(KEY1,email.toString())
                intentNew.putExtra(KEY2,name.toString())
                intentNew.putExtra(KEY3,userId.toString())
                startActivity(intentNew)
            } else{
                Toast.makeText(this, "User does not exits, Please Sign Up first", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}