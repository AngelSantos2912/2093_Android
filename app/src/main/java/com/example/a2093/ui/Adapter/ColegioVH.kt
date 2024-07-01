package com.example.a2093.ui.Adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a2093.Model.Colegio
import com.example.a2093.databinding.ItemAlumnoBinding

class ColegioVH(private val binding: ItemAlumnoBinding) : ViewHolder(binding.root){

    fun agregarItem(colegio : Colegio){
        binding.lblalumno.text = "Apellidos : ${colegio.nombre}"
        binding.lblpadres.text = "Nombre : ${colegio.apellido}"
    }
}