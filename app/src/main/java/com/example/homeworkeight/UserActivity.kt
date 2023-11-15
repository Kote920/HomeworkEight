package com.example.homeworkeight

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import com.example.homeworkeight.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private var equipment: Equipment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()

    }

    private fun setUp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            equipment = intent.getParcelableExtra("user", Equipment::class.java)
        } else {
            equipment = intent.getParcelableExtra<Equipment>("user")
        }
        val firstName = equipment?.firstName ?: ""
        val lastName = equipment?.lastName ?: ""
        val email = equipment?.email ?: ""

        binding.etFirstName.setText(firstName)
        binding.etLastName.setText(lastName)
        binding.etEmail.setText(email)


        binding.btnSaveChanges.setOnClickListener {


            equipment?.firstName = binding.etFirstName.text.toString()
            equipment?.lastName = binding.etLastName.text.toString()
            equipment?.email = binding.etEmail.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("edited_item", equipment)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()


        }


    }

}