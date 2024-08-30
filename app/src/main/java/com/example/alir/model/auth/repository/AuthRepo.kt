package com.example.alir.model.auth.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepo {

    fun login(email: String, password: String) : Flow<Resource<AuthResult>>

    fun loginGoogle(account: GoogleSignInAccount): Flow<Resource<AuthResult>>

    fun register(email: String, password: String) : Flow<Resource<AuthResult>>


}