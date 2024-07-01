package com.example.a2093

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2093.Model.Colegio
import com.example.a2093.Network.ColegioDataSource
import com.example.a2093.databinding.ActivityRegistroBinding
import com.example.a2093.ui.Adapter.ColegioAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    private var dataSource : ColegioDataSource? = null

    private var adapter : ColegioAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dataSource = ColegioDataSource(FirebaseFirestore.getInstance())

        adapter =  ColegioAdapter({c ->
            modificarColegio(c)
        }, {id ->
            eliminarColegio(id)
        })

        binding.rvcolegio.adapter = adapter
        binding.rvcolegio.layoutManager = LinearLayoutManager(this)

        dataSource?.obtenerColegio { c ->
            adapter?.listar(c)
        }


//------------------------------------------//
        val btnmatricula = findViewById<ImageButton>(R.id.btnmatricula)
        btnmatricula.setOnClickListener{
            val i = Intent(this, Matricula::class.java)
            startActivity(i)
        }
//------------------------------------------//
        val btneventos = findViewById<ImageButton>(R.id.btneventos)
        btneventos.setOnClickListener{
            val i = Intent(this, Eventos::class.java)
            startActivity(i)
        }


//---------------------------------------------//
        val btnhome = findViewById<ImageButton>(R.id.btnhome)
        btnhome.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        //-----------------------------------------//
    }

    private fun eliminarColegio(id: String) {

        MaterialAlertDialogBuilder(this)
            .setTitle("Alerta!")
            .setMessage("Deseas Eliminar este registro?")
            .setPositiveButton("Aceptar"){ _,_ -> dataSource?.eliminarColegio(id){
                if(it){
                    mensaje("Se elimino el registro")
                    dataSource?.obtenerColegio { c ->
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

    private fun modificarColegio(c: Colegio) {

        val intent = Intent(this, ActualizarAlumno::class.java)
        intent.putExtra("id", c.id)
        intent.putExtra("nombre", c.nombre)
        intent.putExtra("apellido", c.apellido)
        intent.putExtra("correo", c.correo)
        intent.putExtra("telefono", c.telefono)
        intent.putExtra("genero", c.genero.toString())
        startActivity(intent)
    }
}




