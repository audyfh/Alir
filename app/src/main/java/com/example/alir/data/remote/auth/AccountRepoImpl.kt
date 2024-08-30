package com.example.alir.data.remote.auth

import com.example.alir.model.auth.Account
import com.example.alir.model.auth.repository.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun saveUserProfile(userProfile: Account): Resource<Unit> {
        return try {
            val uid = firebaseAuth.currentUser?.uid ?: throw Exception("User not authenticated")
            firestore.collection("userProfiles").document(uid).set(userProfile).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    suspend fun getUserProfile(): Resource<Account> {
        return try {
            val uid = firebaseAuth.currentUser?.uid ?: throw Exception("User not authenticated")
            val document = firestore.collection("userProfiles").document(uid).get().await()
            val userProfile = document.toObject(Account::class.java)
            if (userProfile != null) {
                Resource.Success(userProfile)
            } else {
                Resource.Error("User profile not found")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}