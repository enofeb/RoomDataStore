package com.enofeb.roomdatastore.domain.usecase

import com.enofeb.roomdatastore.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend fun deleteNoteById(id: Int) {
        repository.deleteNoteById(id)
    }
} 