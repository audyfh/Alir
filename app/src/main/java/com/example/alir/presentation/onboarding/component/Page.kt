package com.example.alir.presentation.onboarding.component

import androidx.annotation.DrawableRes
import com.example.alir.R

data class Page(
    val title: String,
    val desc : String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Pastikan Keberadaan Air Bersih",
        desc = "Alir membantumu memastikan ketersediaan air bersih di sekitarmu",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Fitur Komunitas Projek Air",
        desc = "Temukan komunitas yang dapat membantu anda dalam projek air bersih",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Berikan Donasi",
        desc = "Alir membantumu memastikan ketersediaan air bersih di sekitarmu",
        image = R.drawable.onboarding3
    )
)
