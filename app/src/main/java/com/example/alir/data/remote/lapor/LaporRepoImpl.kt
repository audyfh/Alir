package com.example.alir.data.remote.lapor

import android.net.Uri
import com.example.alir.model.lapor.LaporRepo
import com.example.alir.model.lapor.ProgressUpdate
import com.example.alir.model.lapor.Report
import com.example.alir.model.news.News
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class LaporRepoImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage : FirebaseStorage
) : LaporRepo {
    override suspend fun submitReport(
        report: Report,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("report")
            .add(report)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    override fun uploadPhoto(
        uri: Uri,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val storageRef = storage.reference.child("report_photos/${UUID.randomUUID()}")
        storageRef.putFile(uri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
            }
            .addOnFailureListener(onFailure)
    }

    override suspend fun updateReportProgress(
        reportId: String,
        progressUpdate: ProgressUpdate,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("report").document(reportId)
            .update("progress", FieldValue.arrayUnion(progressUpdate))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    override suspend fun getReports(): List<Report>{
        val reportList = mutableListOf<Report>()
        val snapshot = firestore.collection("report").get().await()
        for (document in snapshot.documents){
            val news = document.toObject(Report::class.java)
            news?.let {
                reportList.add(it)
            }
        }
        return reportList
    }
}