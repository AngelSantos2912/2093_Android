package com.example.a2093.Model

import java.io.Serializable

class Evento (
    var id : String,
    val razon : String ? = null,
    val fecha : String? = null,
    val organizador : String ? =  null,
) : Serializable{
    constructor():this("", "", "", "")
}
