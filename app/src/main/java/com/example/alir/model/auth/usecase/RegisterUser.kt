package com.example.alir.model.auth.usecase

import com.example.alir.model.auth.repository.AuthRepo
import com.example.alir.model.auth.repository.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUser @Inject constructor(
    private val authRepo: AuthRepo
) {
    operator fun invoke(email:String,password:String) : Flow<Resource<AuthResult>> {
        return authRepo.register(email,password)
    }
}