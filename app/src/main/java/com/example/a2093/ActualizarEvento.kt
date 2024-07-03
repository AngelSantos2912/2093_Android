package com.example.a2093

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2093.Model.Evento
import com.example.a2093.Network.EventoDataSource
import com.example.a2093.databinding.ActivityActualizarAlumnoBinding
import com.example.a2093.databinding.ActivityActualizarEventoBinding

class ActualizarEvento : AppCompatActivity() {

    private var evento : Evento? = null
    private lateinit var binding: ActivityActualizarEventoBinding
    private var dataSource : EventoDataSource? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityActualizarEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //----------------------------------------------//
        val btnhome = findViewById<ImageButton>(R.id.btnhome)
        btnhome.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        val btncancelar = findViewById<Button>(R.id.btnCancelar)
        btncancelar.setOnClickListener{
            val i = Intent(this, Registro::class.java)
            startActivity(i)
        }

        //------------------------------------------------//




    }
}