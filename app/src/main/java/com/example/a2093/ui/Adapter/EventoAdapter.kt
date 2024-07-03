package com.example.a2093.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.a2093.Model.Colegio
import com.example.a2093.Model.Evento
import com.example.a2093.R
import com.example.a2093.databinding.ItemAlumnoBinding
import com.example.a2093.databinding.ItemEventoBinding

class EventoAdapter(private val update : (Evento) -> Unit,
                             private val delete : (id : String) -> Unit) : RecyclerView.Adapter<EventoVH>() {
    private val lista : MutableList<Evento> = mutableListOf()

    fun listar(evento: List<Evento>){
        lista.clear()
        lista.addAll(evento)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoVH {
        val binding = ItemEventoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventoVH(binding)
    }

    override fun getItemCount(): Int {

        return lista.size
    }

    override fun onBindViewHolder(holder: EventoVH, position: Int) {
        val evento = lista[position]

        holder.agregarItem(evento)

        holder.itemView.setOnClickListener{
            update(evento)
        }
        holder.itemView.findViewById<ImageButton>(R.id.btndeleteevento).setOnClickListener{
            delete(evento.id)
        }
    }
}