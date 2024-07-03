package com.example.a2093

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2093.Model.Evento
import com.example.a2093.Network.ColegioDataSource
import com.example.a2093.Network.EventoDataSource
import com.example.a2093.databinding.ActivityEventosBinding
import com.example.a2093.databinding.ActivityRegistroBinding
import com.example.a2093.ui.Adapter.ColegioAdapter
import com.example.a2093.ui.Adapter.EventoAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore

class Eventos : AppCompatActivity() {

    private lateinit var binding: ActivityEventosBinding

    private var dataSource : EventoDataSource? = null

    private var adapter : EventoAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dataSource = EventoDataSource(FirebaseFirestore.getInstance())

        adapter =  EventoAdapter({c ->
            modificarEvento(c)
        }, {id ->
            eliminarEvento(id)
        })


        binding.rveventos.adapter = adapter
        binding.rveventos.layoutManager = LinearLayoutManager(this)

        dataSource?.obtenerEvento { c ->
            adapter?.listar(c)
        }





//-------------------------------------------------------------------------------------------------------------//
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

    private fun eliminarEvento(id: String) {

        MaterialAlertDialogBuilder(this)
            .setTitle("Alerta!")
            .setMessage("Deseas Eliminar este registro?")
            .setPositiveButton("Aceptar"){ _,_ -> dataSource?.eliminarEvento(id){
                if(it){
                    mensaje("Se elimino el registro")
                    dataSource?.obtenerEvento { c ->
                        adapter?.listar(c)
                    }
                }else{
                    mensaje("No se pudo eliminar")
                }
            }
            }
            .setNegativeButton("Cancelar", null)
            .show()

    }

    private fun mensaje(s: String) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun modificarEvento(c: Evento) {

        val intent = Intent(this, ActualizarEvento::class.java)
        intent.putExtra("id", c.id)
        intent.putExtra("razon", c.razon)
        intent.putExtra("organizador", c.organizador)
        intent.putExtra("fecha", c.fecha)
        startActivity(intent)
    }
}