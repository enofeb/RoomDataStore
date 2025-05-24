package com.enofeb.roomdatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enofeb.roomdatastore.domain.usecase.AddNoteUseCase
import com.enofeb.roomdatastore.domain.usecase.DeleteNoteUseCase
import com.enofeb.roomdatastore.domain.usecase.GetNotesUseCase
import com.enofeb.roomdatastore.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    init {
        viewModelScope.launch {
            getNotesUseCase.getNotes().onEach { notes ->
                _notes.value = notes
            }.collect()
        }
    }

    fun addNote(title: String, description: String) {
        viewModelScope.launch {
            addNoteUseCase.addNote(title = title, description = description)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNoteById(id)
        }
    }
} 