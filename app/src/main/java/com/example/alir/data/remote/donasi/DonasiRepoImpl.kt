package com.example.alir.data.remote.donasi

import com.example.alir.model.donasi.Donasi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DonasiRepoImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getDonasi() : List<Donasi> {
        val donasiList =  mutableListOf<Donasi>()
        val snapshot = firestore.collection("donasi").get().await()

        for (document in snapshot.documents){
            val donasi = document.toObject(Donasi::class.java)
            donasi?.let {
                donasiList.add(it)
            }
        }
        return donasiList
    }
}