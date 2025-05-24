package com.enofeb.roomdatastore.domain.usecase

import com.enofeb.roomdatastore.model.Note
import com.enofeb.roomdatastore.model.NoteEntity
import com.enofeb.roomdatastore.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    fun getNotes(): Flow<List<Note>> =
        repository.getAllNotes().map { list -> list.map { it.toUiModel() } }
}

fun NoteEntity.toUiModel() = Note(id, title, description) 