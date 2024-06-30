package com.example.a2093.Network

import android.util.Log
import com.example.a2093.Model.Colegio
import com.example.a2093.Repository.ColegioRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class ColegioDataSource(private val db : FirebaseFirestore) : ColegioRepository {

    private val coleccion = "Colegio"

//-----------------------------------------------------------------------//
    override fun obtenerColegio(rs: (List<Colegio>) -> Unit) {
        db.collection(coleccion).get()
            .addOnSuccessListener { doc ->
                val lista = mutableListOf<Colegio>()
                for (d in doc){
                    val objetoColegio = d.toObject(Colegio::class.java)
                    objetoColegio.id = d.id
                    lista.add(objetoColegio)
                }
                rs(lista)
            }
            .addOnFailureListener{
                Log.e("ERROR", "EN OBTENER DATOS : ${it.localizedMessage}")
                rs(emptyList())
            }
    }


    //-------------------------------------------------------------------------//
    override fun agregarColegio(colegio: Colegio, rs: (Boolean) -> Unit) {
        db.collection(coleccion).add(colegio)
            .addOnSuccessListener {
                rs(true)
            }
            .addOnFailureListener {
                Log.e("ERROR", "EN REGISTRAR DATOS : ${it.localizedMessage}")
                rs(false)
            }
    }


    //--------------------------------------------------------------------------//
    override fun actualizarColegio(id: String, colegio: Colegio, rs: (Boolean) -> Unit) {
        db.collection(coleccion).document(id).set(colegio)
            .addOnSuccessListener {
                rs(true)
            }
            .addOnFailureListener {
                Log.e("ERROR", "EN ACTUALIZAR DATOS : ${it.localizedMessage}")
                rs(false)
            }
    }


    //----------------------------------------------------------------------------//
    override fun eliminarColegio(id: String, rs: (Boolean) -> Unit) {
        db.collection(coleccion).document(id).delete()
            .addOnSuccessListener {
                rs(true)
            }
            .addOnFailureListener {
                Log.e("ERROR", "EN ELIMINAR DATOS : ${it.localizedMessage}")
                rs(false)
            }
    }

    //----------------------------------------------------------------------------//
}