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
import com.example.a2093.Network.EventoDataSource
import com.example.a2093.databinding.ActivityActualizarEventoBinding
import com.google.firebase.firestore.FirebaseFirestore

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
        val btnhomeevent = findViewById<ImageButton>(R.id.btnhomeevent)
        btnhomeevent.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        val btncancelarevento = findViewById<Button>(R.id.btnCancelarEvento)
        btncancelarevento.setOnClickListener{
            val i = Intent(this, Registro::class.java)
            startActivity(i)
        }

        //------------------------------------------------//

        dataSource = EventoDataSource(FirebaseFirestore.getInstance())


        val obidevento = intent?.getStringExtra("id")
        val obrazon = intent?.getStringExtra("razon")
        val oborganizador = intent?.getStringExtra("organizador")
        val obfecha = intent?.getStringExtra("fecha")


        binding.txtidevento.setText(obidevento)
        binding.txtrazon.setText(obrazon)
        binding.txtorganizador.setText(oborganizador)
        binding.txtfecha.setText(obfecha)

        actualizar()
    }

    private fun actualizar() {
        binding.btnActualizarEvento.setOnClickListener{
            binding.txtrazon.error = null
            binding.txtorganizador.error = null
            binding.txtfecha.error = null

            val idevento = binding.txtidevento.text.toString()
            val razon  = binding.txtrazon.text.toString()
            val organizador  = binding.txtorganizador.text.toString()
            val fecha  = binding.txtfecha.text.toString()


            if(razon.isEmpty()){
                binding.txtrazon.error = "Ingrese el motivo del evento"
                return@setOnClickListener
            }
            if(organizador.isEmpty()){
                binding.txtorganizador.error = "Ingrese quien organiza el evento"
                return@setOnClickListener
            }
            if(fecha.isEmpty()){
                binding.txtfecha.error = "Ingrese la fecha de publicacion"
                return@setOnClickListener
            }
            evento = Evento(idevento, razon,organizador,fecha)

            dataSource?.actualizarEvento(idevento, evento!!){
                if (it){
                    mensaje("Se actualizo el evento")
                    val intent = Intent(this, Eventos::class.java)
                    startActivity(intent)
                }else{
                    mensaje("No se actualizo el evento")
                }
            }

        }
    }

    private fun mensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}