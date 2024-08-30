package com.example.alir.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alir.model.auth.repository.AuthRepo
import com.example.alir.model.auth.repository.Resource
import com.example.alir.presentation.navgraph.Route
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepo,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<AuthResult>>(Resource.Loading())
    val loginState : StateFlow<Resource<AuthResult>> = _loginState.asStateFlow()
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.LoginScreen.Route)
        private set


    init {
        viewModelScope.launch {
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                startDestination = Route.AppNavigator.Route
            } else {
                startDestination = Route.OnBoarding.Route
            }
            delay(300)
            splashCondition = false
        }
    }

    fun login(email: String,password: String){
        viewModelScope.launch {
            if (email.isNotEmpty() && password.isNotEmpty()){
            authRepo.login(email,password).collect { result ->
                _loginState.value = result
            }
            }
        }
    }

    fun loginGoogle(account: GoogleSignInAccount){
        viewModelScope.launch {
            authRepo.loginGoogle(account).collect{result ->
                _loginState.value = result
            }
        }
    }

    fun register(email: String, password: String, checkPass: String){
        viewModelScope.launch {
            if (email.isEmpty() || password.isEmpty() || checkPass.isEmpty()) {
                _loginState.value = Resource.Error("Data masih kosong")
                return@launch
            }

            if (password != checkPass) {
                _loginState.value = Resource.Error("Passwords tidak sama")
                return@launch
            }

            authRepo.register(email, password).collect { result ->
                _loginState.value = result
            }
        }
    }

    fun handleLoginState(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        when (val state = _loginState.value) {
            is Resource.Success -> onSuccess()
            is Resource.Error -> onError(state.message ?: "Terjadi Kesalahan")
            is Resource.Loading -> {

            }
        }
    }

}