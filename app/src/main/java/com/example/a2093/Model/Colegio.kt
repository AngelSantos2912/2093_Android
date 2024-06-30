package com.example.a2093.Model

import java.io.Serializable

class Colegio (
    var id : String,
    val Apellido : String ?= null,
    val Nombre : String ?= null,
    val Correo : String ?= null,
    val Telefono : Int ?= null,
    val Genero : Int ?= null,
) : Serializable {
    constructor() : this("", "", "", "", 0, 0)
}
