package com.example.alir.model.lapor

import android.net.Uri

interface LaporRepo {
    suspend fun submitReport(
        report: Report,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun uploadPhoto(
        uri: Uri,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    )

    suspend fun updateReportProgress(
        reportId: String,
        progressUpdate: ProgressUpdate,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    suspend fun getReports() : List<Report>
}