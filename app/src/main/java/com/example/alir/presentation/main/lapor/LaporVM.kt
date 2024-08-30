package com.example.alir.presentation.main.lapor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alir.model.lapor.LaporRepo
import com.example.alir.model.lapor.Report
import com.mapbox.geojson.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaporVM @Inject constructor(
    private val laporRepo: LaporRepo
) : ViewModel(){
    private val _selectedLocation = MutableStateFlow<Point?>(null)
    val selectedLocation = _selectedLocation.asStateFlow()

}