package com.enofeb.roomdatastore.repository

import com.enofeb.roomdatastore.model.Note
import com.enofeb.roomdatastore.model.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()
    suspend fun addNote(note: Note) = noteDao.insertNote(note)
} 