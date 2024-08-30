package com.example.alir.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alir.data.remote.auth.AccountRepoImpl
import com.example.alir.model.auth.Account
import com.example.alir.model.auth.repository.Resource
import com.example.alir.presentation.navgraph.Route
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repo: AccountRepoImpl,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _userProfileState = MutableStateFlow<Resource<Account>>(Resource.Loading())
    val userProfileState = _userProfileState.asStateFlow()

    private val _isProfileSaved = MutableStateFlow(false)
    val isProfileSaved = _isProfileSaved.asStateFlow()

    init {
        getUser()
    }

    fun saveUser(account: Account) {
        viewModelScope.launch {
            _userProfileState.value = Resource.Loading()
            val result = repo.saveUserProfile(account)
            _userProfileState.value = when (result) {
                is Resource.Success -> {
                    _isProfileSaved.value = true
                    Resource.Success(account)
                }
                is Resource.Error -> Resource.Error(result.message ?: "An unknown error occurred")
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            _userProfileState.value = Resource.Loading()
            _userProfileState.value = repo.getUserProfile()
        }
    }
}