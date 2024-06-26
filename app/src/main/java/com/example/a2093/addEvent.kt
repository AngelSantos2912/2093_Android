package com.example.a2093

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class addEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_event)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnhome = findViewById<ImageButton>(R.id.btnhomeevent)
        btnhome.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        val btncancelarevento = findViewById<Button>(R.id.btnCancelarEvento)
        btncancelarevento.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }
}