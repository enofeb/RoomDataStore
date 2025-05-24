package com.enofeb.roomdatastore.domain.usecase

import androidx.compose.ui.graphics.Color
import com.enofeb.roomdatastore.domain.model.Note
import com.enofeb.roomdatastore.model.NoteEntity
import com.enofeb.roomdatastore.model.Priority
import com.enofeb.roomdatastore.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    fun getNotes(): Flow<List<Note>> = repository.getAllNotes().map { list -> list.map { it.toUiModel() } }
}

fun NoteEntity.toUiModel() = Note(
    id = id,
    title = title,
    description = description,
    color = when (priority) {
        Priority.HIGH -> Color.Red
        Priority.MEDIUM -> Color.Yellow
        Priority.LOW -> Color.Green
    }
)