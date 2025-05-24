package com.enofeb.roomdatastore.repository

import com.enofeb.roomdatastore.model.NoteEntity
import com.enofeb.roomdatastore.model.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun addNote(note: NoteEntity) = noteDao.insertNote(note)

    suspend fun deleteNoteById(id: Int) = noteDao.deleteNoteById(id)
} 