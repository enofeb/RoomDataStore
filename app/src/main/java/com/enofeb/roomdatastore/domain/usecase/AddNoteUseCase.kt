package com.enofeb.roomdatastore.domain.usecase

import com.enofeb.roomdatastore.model.NoteEntity
import com.enofeb.roomdatastore.model.Priority
import com.enofeb.roomdatastore.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend fun addNote(title: String, description: String, priority: Priority) {
        val note = NoteEntity(title = title, description = description, priority = priority)
        repository.addNote(note)
    }
} 