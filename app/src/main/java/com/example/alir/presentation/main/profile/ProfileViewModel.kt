package com.example.alir.presentation.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alir.data.remote.auth.AccountRepoImpl
import com.example.alir.model.auth.Account
import com.example.alir.model.auth.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val accountRepo: AccountRepoImpl
) : ViewModel() {

    private val _accountState = MutableStateFlow<Resource<Account>>(Resource.Loading())
    val accountState: StateFlow<Resource<Account>> = _accountState

    init {
        fetchUserProfile()
    }


    private fun fetchUserProfile() {
        viewModelScope.launch {
            _accountState.value = accountRepo.getUserProfile()
        }
    }
}