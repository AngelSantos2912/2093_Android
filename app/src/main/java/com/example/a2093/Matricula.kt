package com.example.a2093

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2093.Model.Colegio
import com.example.a2093.Network.ColegioDataSource
import com.example.a2093.databinding.ActivityMatriculaBinding
import com.example.a2093.ui.Adapter.ColegioAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore

class Matricula : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_matricula)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //------------------------------------------------------//






//---------------------------------------------------------------------------------------------------------------//
        val btncancelar = findViewById<Button>(R.id.btnCancelar)
        btncancelar.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        //----------------------------------------------------------------------------//
        val btnhome = findViewById<ImageButton>(R.id.btnhome)
        btnhome.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        //-------------------------------------------------------------------------//
        val btneventos = findViewById<ImageButton>(R.id.btneventos)
        btneventos.setOnClickListener{
            val i = Intent(this, Eventos::class.java)
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