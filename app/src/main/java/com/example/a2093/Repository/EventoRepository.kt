package com.example.a2093.Repository

import com.example.a2093.Model.Evento

interface EventoRepository {

    fun obtenerEvento(rs : (List<Evento>) -> Unit)
    fun agregarEvento(evento : Evento, rs : (Boolean) -> Unit)
    fun actualizarEvento(id : String, evento: Evento, rs : (Boolean) -> Unit)
    fun eliminarEvento(id : String,  rs : (Boolean) -> Unit)

}