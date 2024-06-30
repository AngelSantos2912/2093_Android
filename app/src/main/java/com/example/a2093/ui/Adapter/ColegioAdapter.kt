package com.example.a2093.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.a2093.Model.Colegio
import com.example.a2093.R
import com.example.a2093.databinding.ItemAlumnoBinding

class ColegioAdapter(private val update : (Colegio) -> Unit,
                     private val delete : (id : String) -> Unit) : Adapter<ColegioVH>(){

                         private val lista : MutableList<Colegio> = mutableListOf()

    fun listar(colegio: List<Colegio>){
        lista.clear()
        lista.addAll(colegio)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColegioVH {

        val binding = ItemAlumnoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColegioVH(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ColegioVH, position: Int) {
        val colegio = lista[position]

        holder.agregarItem(colegio)

        holder.itemView.setOnClickListener{
            update(colegio)
        }
        holder.itemView.findViewById<ImageButton>(R.id.btndelete).setOnClickListener{
            delete(colegio.id)
        }
    }
}