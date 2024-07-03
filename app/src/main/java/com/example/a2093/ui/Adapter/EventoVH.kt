package com.example.a2093.ui.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.a2093.Model.Evento
import com.example.a2093.databinding.ItemEventoBinding

class EventoVH(private val binding: ItemEventoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun agregarItem(evento: Evento){
        binding.lblevento.text = "Fecha : ${evento.organizador}"
        binding.lblrazon.text = "Razon : ${evento.razon}"
        binding.lblfecha.text = "Organizador : ${evento.fecha}"
   }
}