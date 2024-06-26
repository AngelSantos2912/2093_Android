package com.example.a2093

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Eventos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eventos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnaddevent = findViewById<ImageButton>(R.id.btnaddEvent)
        btnaddevent.setOnClickListener{
            val i = Intent(this, addEvent::class.java)
            startActivity(i)
        }


        //----------------MATRICULA --------------------------//
        val btnmatricula = findViewById<ImageButton>(R.id.btnmatricula)
        btnmatricula.setOnClickListener{
            val i = Intent(this, Matricula::class.java)
            startActivity(i)
        }

        //----------------------------------------------------------------------------//
        val btnhome = findViewById<ImageButton>(R.id.btnhome)
        btnhome.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        //--------------------------------------------------------------------------//
        val btnregistros = findViewById<ImageButton>(R.id.btnregistro)
        btnregistros.setOnClickListener{
            val i = Intent(this, Registro::class.java)
            startActivity(i)
        }
        //-----------------------------------------------------------------------//
    }
}