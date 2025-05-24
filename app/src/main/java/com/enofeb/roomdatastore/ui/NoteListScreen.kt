package com.enofeb.roomdatastore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enofeb.roomdatastore.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    notes: List<Note>,
    onAddNoteClick: () -> Unit,
    onDeleteNoteClick: (Int) -> Unit = {},
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    val fabColor = MaterialTheme.colorScheme.primary
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Notes") },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = if (isDarkTheme) "Dark" else "Light", style = MaterialTheme.typography.bodySmall)
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = onThemeToggle
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNoteClick, containerColor = fabColor) {
                Icon(Icons.Default.Add, contentDescription = "Not Ekle")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (notes.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Henüz not yok.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(notes) { note ->
                        NoteListItem(
                            note = note,
                            backgroundColor = fabColor,
                            onDelete = { onDeleteNoteClick(note.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoteListItem(
    note: Note,
    backgroundColor: Color,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor.copy(alpha = 0.12f))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 13.sp,
                    color = Color.Gray
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Sil",
                tint = Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteListItemPreview() {
    MaterialTheme {
        NoteListItem(
            note = Note(id = 1, title = "Başlık", description = "Açıklama örneği burada. Çok uzun bir açıklama olursa tek satırda kalacak şekilde kısalır."),
            backgroundColor = Color(0xFF6650a4),
            onDelete = {}
        )
    }
} 