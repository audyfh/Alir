package com.example.alir.presentation.main.lapor

import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alir.model.auth.repository.Resource
import com.example.alir.model.lapor.LaporRepo
import com.example.alir.model.lapor.ProgressUpdate
import com.example.alir.model.lapor.Report
import com.google.firebase.firestore.GeoPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LaporViewModel @Inject constructor(
    private val repository: LaporRepo
) : ViewModel() {
    private val _reports = MutableStateFlow<List<Report>>(emptyList())
    val reports: StateFlow<List<Report>> = _reports.asStateFlow()

    val reportValue = reports.value

    private val _uploadState = MutableStateFlow<Resource<String>>(Resource.Loading())
    val uploadState: StateFlow<Resource<String>> = _uploadState.asStateFlow()

    private val _submitState = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val submitState: StateFlow<Resource<Unit>> = _submitState.asStateFlow()

    private val _currentReport = MutableStateFlow<Report?>(null)
    val currentReport: StateFlow<Report?> = _currentReport.asStateFlow()

    init {
        fetchReports()
    }

    fun fetchReports() {
        viewModelScope.launch {
            val reports = repository.getReports()
            _reports.value  = reports
        }
    }

    fun submitReport(
        reporterName: String,
        date: String,
        location: String,
        photoUri: Uri
    ) {
        viewModelScope.launch {
            val photoUrl = uploadPhoto(photoUri)
            if (photoUrl != null) {
                val report = Report(
                    reporterName = reporterName,
                    date = date,
                    location = location,
                    photoUrl = photoUrl
                )
                repository.submitReport(
                    report,
                    onSuccess = {
                        _submitState.value = Resource.Success(Unit)
                        fetchReports()
                    },
                    onFailure = { e ->
                        _submitState.value = Resource.Error(e.message ?: "Error")
                    }
                )
            } else {
                _submitState.value = Resource.Error("Failed to upload photo")
            }
        }
    }

     fun uploadPhoto(uri: Uri): String? {
        return try {
            repository.uploadPhoto(
                uri,
                onSuccess = { url -> _uploadState.value = Resource.Success(url) },
                onFailure = { e -> _uploadState.value = Resource.Error(e.message ?: "Error") }
            )
            (_uploadState.value as? Resource.Success)?.data
        } catch (e: Exception) {
            null
        }
    }



}