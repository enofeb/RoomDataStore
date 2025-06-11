package com.enofeb.roomdatastore.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    onAddNoteClick: () -> Unit,
    onDeleteNoteClick: (Int) -> Unit = {},
    onThemeToggle: (Boolean) -> Unit
) {
    val fabColor = MaterialTheme.colorScheme.primary

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Notes") },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (false) "Dark" else "Light",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Switch(
                            checked = false,
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
            if (true) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No notes yet!")
                }
            } else {

            }
        }
    }
}

@Composable
fun NoteListItem(
    backgroundColor: Color,
    onDelete: (Int) -> Unit = {}
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
                text = "",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 13.sp,
                    color = Color.Gray
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(50))
                //.background(Color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { onDelete.invoke(0) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Sil",
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteListItemPreview() {
    MaterialTheme {
        NoteListItem(
            backgroundColor = Color(0xFF6650a4),
            onDelete = {}
        )
    }
} 