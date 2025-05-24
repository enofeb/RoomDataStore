package com.enofeb.roomdatastore.domain.model

import androidx.compose.ui.graphics.Color

data class Note(
    val id: Int = 0,
    val title: String,
    val description: String,
    val color: Color
) 