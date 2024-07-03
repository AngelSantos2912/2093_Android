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
import com.example.a2093.Model.Evento
import com.example.a2093.Network.ColegioDataSource
import com.example.a2093.Network.EventoDataSource
import com.example.a2093.databinding.ActivityAddEventBinding
import com.example.a2093.databinding.ActivityMatriculaBinding
import com.google.firebase.firestore.FirebaseFirestore

class addEvent : AppCompatActivity() {

    private lateinit var binding: ActivityAddEventBinding
    private var dataSource : EventoDataSource? = null
    private var evento : Evento? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddEventBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dataSource = EventoDataSource(FirebaseFirestore.getInstance())
        registro()





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

    private fun registro() {
        binding.btnRegistroEvento.setOnClickListener{
            binding.txtrazon.error = null
            binding.txtorganizador.error = null
            binding.txtfecha.error = null

            val razon  = binding.txtrazon.text.toString()
            val organizador  = binding.txtorganizador.text.toString()
            val fecha  = binding.txtfecha.text.toString()

            if(razon.isEmpty()){
                binding.txtrazon.error = "Ingrese el motivo del evento"
                return@setOnClickListener
            }
            if(organizador.isEmpty()){
                binding.txtorganizador.error = "Ingrese quien es el organizador del evento"
                return@setOnClickListener
            }
            if(fecha.isEmpty()){
                binding.txtfecha.error = "Ingrese la fecha de publicacion"
                return@setOnClickListener
            }

            evento = Evento("", razon,organizador,fecha)

            dataSource?.agregarEvento(evento!!){
                if (it){
                    mensaje("Registro exitoso")
                    val intent = Intent(this, Eventos::class.java)
                    startActivity(intent)
                }else{
                    mensaje("No se registro el evento")
                }
            }

        }

    }

    private fun mensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}