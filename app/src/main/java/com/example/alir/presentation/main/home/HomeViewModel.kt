package com.example.alir.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alir.data.remote.auth.AccountRepoImpl
import com.example.alir.data.remote.donasi.DonasiRepoImpl
import com.example.alir.model.auth.Account
import com.example.alir.model.auth.repository.Resource
import com.example.alir.model.donasi.Donasi
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val accountRepo: AccountRepoImpl,
    private val donasiRepo: DonasiRepoImpl
) : ViewModel(){

    private val _accountState = MutableStateFlow<Resource<Account>>(Resource.Loading())
    val accountState: StateFlow<Resource<Account>> = _accountState

    private val _donasi = MutableStateFlow<List<Donasi>>(emptyList())
    val donasi : StateFlow<List<Donasi>> = _donasi

    init {
        fetchUserProfile()
        getDonasi()
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            _accountState.value = accountRepo.getUserProfile()
        }
    }

    private fun getDonasi(){
        viewModelScope.launch {
            _donasi.value = donasiRepo.getDonasi()
        }
    }

}