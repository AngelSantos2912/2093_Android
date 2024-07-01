package com.example.a2093

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

    private lateinit var binding: ActivityMatriculaBinding
    private var dataSource : ColegioDataSource? = null
    private var colegio : Colegio ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMatriculaBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //------------------------------------------------------//

        dataSource = ColegioDataSource(FirebaseFirestore.getInstance())
        registro()
        


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

    private fun registro() {
        binding.btnRegistro.setOnClickListener{
            binding.txtnombre.error = null
            binding.txtapellido.error = null
            binding.txtcorreo.error = null
            binding.txttelefono.error = null

            val nombre  = binding.txtnombre.text.toString()
            val apellido  = binding.txtapellido.text.toString()
            val correo  = binding.txtcorreo.text.toString()
            val fono  = binding.txttelefono.text.toString()
            val genero = binding.genero.selectedItemPosition

            if(nombre.isEmpty()){
                binding.txtnombre.error = "Ingrese su nombre"
                return@setOnClickListener
            }
            if(apellido.isEmpty()){
                binding.txtapellido.error = "Ingrese su apellido"
                return@setOnClickListener
            }
            if(correo.isEmpty()){
                binding.txtcorreo.error = "Ingrese su correo"
                return@setOnClickListener
            }
            if(fono.isEmpty()){
                binding.txttelefono.error = "Ingrese su telefono"
                return@setOnClickListener
            }
            if (genero == -1){
                mensaje("Seleccione un genero")
                return@setOnClickListener
            }
            if (genero == 0){
                colegio = Colegio("",  nombre, apellido, correo, fono,0)
            }else{
                colegio = Colegio("", nombre, apellido, correo,fono,1)
            }
            dataSource?.agregarColegio(colegio!!){
                if (it){
                    mensaje("Registro exitoso")
                    val intent = Intent(this, Registro::class.java)
                    startActivity(intent)
                }else{
                    mensaje("No se registro el alumno")
                }
            }


        }
    }
    private fun mensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }


}