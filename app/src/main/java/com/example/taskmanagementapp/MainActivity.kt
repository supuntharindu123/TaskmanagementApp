package com.example.taskmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getstared:Button=findViewById(R.id.startedbtn)

        getstared.setOnClickListener {
            val intent=Intent(this,TaskPage::class.java)
            startActivity(intent)
        }
    }
}