package com.enofeb.roomdatastore.domain.usecase

import com.enofeb.roomdatastore.model.Note
import com.enofeb.roomdatastore.model.NoteEntity
import com.enofeb.roomdatastore.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend fun addNote(title: String, description: String) {
        val note = NoteEntity(title = title, description = description)
        repository.addNote(note)
    }
} 