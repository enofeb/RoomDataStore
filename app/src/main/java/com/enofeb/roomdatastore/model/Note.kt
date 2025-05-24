package com.enofeb.roomdatastore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Basit bir Note veri modeli

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String
) 