package com.example.alir.presentation.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@Composable
fun CustomToast(
    text: String
) {
    val context = LocalContext.current
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}