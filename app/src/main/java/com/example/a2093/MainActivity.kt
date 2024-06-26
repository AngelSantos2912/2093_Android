package com.example.a2093

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//----------------MATRICULA --------------------------//
        val btnmatricula = findViewById<ImageButton>(R.id.btnmatricula)
        btnmatricula.setOnClickListener{
            val i = Intent(this, Matricula::class.java)
            startActivity(i)
        }
        //------------------------EVENTOS-----------------------------------------//
        val btneventos = findViewById<ImageButton>(R.id.btneventos)
        btneventos.setOnClickListener{
            val i = Intent(this, Eventos::class.java)
            startActivity(i)
        }
//-------------------------------REGISTROS----------------------------------------------//
        val btnregistros = findViewById<ImageButton>(R.id.btnregistro)
        btnregistros.setOnClickListener{
            val i = Intent(this, Registro::class.java)
            startActivity(i)
        }
    }
}