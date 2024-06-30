package com.example.a2093.Repository

import com.example.a2093.Model.Colegio

interface ColegioRepository {

    fun obtenerColegio(rs : (List<Colegio>) -> Unit)
    fun agregarColegio(colegio : Colegio, rs : (Boolean) -> Unit)
    fun actualizarColegio(id : String, colegio : Colegio, rs : (Boolean) -> Unit)
    fun eliminarColegio(id : String,  rs : (Boolean) -> Unit)



}