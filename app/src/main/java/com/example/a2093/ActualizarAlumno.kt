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
import com.example.a2093.databinding.ActivityActualizarAlumnoBinding
import com.example.a2093.databinding.ActivityRegistroBinding
import com.example.a2093.ui.Adapter.ColegioAdapter
import com.google.firebase.firestore.FirebaseFirestore

class ActualizarAlumno : AppCompatActivity() {
    private var colegio : Colegio ? = null
    private lateinit var binding: ActivityActualizarAlumnoBinding

    private var dataSource : ColegioDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityActualizarAlumnoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dataSource = ColegioDataSource(FirebaseFirestore.getInstance())

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



        val obid = intent?.getStringExtra("id")
        val obnombre = intent?.getStringExtra("nombre")
        val obapellido = intent?.getStringExtra("apellido")
        val obcorreo = intent?.getStringExtra("correo")
        val obtelefono = intent?.getStringExtra("telefono")
        val obgenero = intent?.getStringExtra("genero")


        val valueGenero = obgenero?.toInt()
        binding.txtid.setText(obid)
        binding.txtnombre.setText(obnombre)
        binding.txtapellido.setText(obapellido)
        binding.txtcorreo.setText(obcorreo)
        binding.txttelefono.setText(obtelefono)

        if(valueGenero == 0){
            binding.genero.setSelection(0)
        }else{
            binding.genero.setSelection(1)
        }
        actualizar()
    }
    private fun mensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun actualizar(){
        binding.btnActualizar.setOnClickListener{
            binding.txtnombre.error = null
            binding.txtapellido.error = null
            binding.txtcorreo.error = null
            binding.txttelefono.error = null

            val id = binding.txtid.text.toString()
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
                colegio = Colegio(id,  nombre, apellido, correo, fono,0)
            }else{
                colegio = Colegio(id, nombre, apellido, correo,fono,1)
            }

            dataSource?.actualizarColegio(id, colegio!!){
                if (it){
                    mensaje("Se actualizo el registro")
                    val intent = Intent(this, Registro::class.java)
                    startActivity(intent)
                }else{
                    mensaje("No se actualizo el registro")
                }
            }

        }

    }

}