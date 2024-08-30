package com.example.alir.model.auth.usecase

import com.example.alir.model.auth.repository.AuthRepo
import com.example.alir.model.auth.repository.Resource
import com.google.firebase.auth.AuthResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LoginUser @Inject constructor(
    private val authRepo: AuthRepo
) {
    operator fun invoke(email:String,password:String) : Flow<Resource<AuthResult>> {
        return authRepo.login(email,password)
    }
}