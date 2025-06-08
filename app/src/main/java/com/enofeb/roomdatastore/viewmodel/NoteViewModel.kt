package com.enofeb.roomdatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enofeb.roomdatastore.domain.usecase.AddNoteUseCase
import com.enofeb.roomdatastore.domain.usecase.DeleteNoteUseCase
import com.enofeb.roomdatastore.domain.usecase.GetNotesUseCase
import com.enofeb.roomdatastore.domain.usecase.ThemePreferencesUseCase
import com.enofeb.roomdatastore.domain.usecase.SetReminderNotificationUseCase
import com.enofeb.roomdatastore.domain.model.Note
import com.enofeb.roomdatastore.model.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val themePreferencesUseCase: ThemePreferencesUseCase,
    private val setReminderNotificationUseCase: SetReminderNotificationUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    val isDarkTheme: StateFlow<Boolean> = themePreferencesUseCase.isDarkTheme()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)

    init {
        viewModelScope.launch {
            getNotesUseCase.getNotes().onEach { notes ->
                _notes.value = notes
            }.collect()
        }
    }

    fun addNote(title: String, description: String, priority: Priority) {
        viewModelScope.launch {
            addNoteUseCase.addNote(title = title, description = description, priority = priority)
            if (_notes.value.isEmpty()) {
                setReminderNotificationUseCase()
            }
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNoteById(id)
        }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            themePreferencesUseCase.setDarkTheme(enabled)
        }
    }
} 