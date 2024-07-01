package com.example.a2093.Model

import java.io.Serializable

class Colegio (
    var id : String,
    val apellido : String ?= null,
    val nombre : String ?= null,
    val correo : String ?= null,
    val telefono : String ?= null,
    val genero : Int ?= null,
) : Serializable {
    constructor() : this("", "", "", "", "", 0)
}
