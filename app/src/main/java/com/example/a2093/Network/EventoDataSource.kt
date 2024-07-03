package com.example.a2093.Network

import android.util.Log
import com.example.a2093.Model.Evento
import com.example.a2093.Repository.EventoRepository
import com.google.firebase.firestore.FirebaseFirestore

class EventoDataSource(private val db : FirebaseFirestore):EventoRepository {

    private val coleccion = "Evento"

    //--------------------------------------------//
    override fun obtenerEvento(rs: (List<Evento>) -> Unit) {
        db.collection(coleccion).get()
            .addOnSuccessListener { doc ->
                val lista = mutableListOf<Evento>()
                for (d in doc){
                    val objetoEvento = d.toObject(Evento::class.java)
                    objetoEvento.id = d.id
                    lista.add(objetoEvento)
                }
                rs(lista)
            }
            .addOnFailureListener{
                Log.e("ERROR", "EN OBTENER DATOS : ${it.localizedMessage}")
                rs(emptyList())
            }
    }

    //-----------------------------------------------------------//

    override fun agregarEvento(evento: Evento, rs: (Boolean) -> Unit) {

        db.collection(coleccion).add(evento)
            .addOnSuccessListener {
                rs(true)
            }
            .addOnFailureListener {
                Log.e("ERROR", "EN REGISTRAR DATOS : ${it.localizedMessage}")
                rs(false)
            }
    }

    //----------------------------------------------------------//

    override fun actualizarEvento(id: String, evento: Evento, rs: (Boolean) -> Unit) {
        db.collection(coleccion).document(id).set(evento)
            .addOnSuccessListener {
                rs(true)
            }
            .addOnFailureListener {
                Log.e("ERROR", "EN ACTUALIZAR DATOS : ${it.localizedMessage}")
                rs(false)
            }
    }

    //------------------------------------------------------------//

    override fun eliminarEvento(id: String, rs: (Boolean) -> Unit) {
        db.collection(coleccion).document(id).delete()
            .addOnSuccessListener {
                rs(true)
            }
            .addOnFailureListener {
                Log.e("ERROR", "EN ELIMINAR DATOS : ${it.localizedMessage}")
                rs(false)
            }
    }

    //----------------------------------------------------------------//

}