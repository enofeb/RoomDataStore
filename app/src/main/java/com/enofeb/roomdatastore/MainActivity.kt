package com.enofeb.roomdatastore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.enofeb.roomdatastore.ui.theme.RoomDataStoreTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.enofeb.roomdatastore.ui.NoteAddScreen
import com.enofeb.roomdatastore.ui.NoteListScreen
import com.enofeb.roomdatastore.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: NoteViewModel = hiltViewModel()
            val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()
            Log.e("ECCO",isDarkTheme.toString())
            RoomDataStoreTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                NavGraph(navController = navController, viewModel = viewModel, isDarkTheme = isDarkTheme)
            }
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: NoteViewModel, isDarkTheme: Boolean) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NoteListScreen(
                notes = notes,
                onAddNoteClick = { navController.navigate("add") },
                onDeleteNoteClick = { id -> viewModel.deleteNote(id) },
                isDarkTheme = isDarkTheme,
                onThemeToggle = { enabled -> viewModel.setDarkTheme(enabled) }
            )
        }
        composable("add") {
            NoteAddScreen(
                onSave = { title, desc ->
                    viewModel.addNote(title, desc)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDataStoreTheme {
        Greeting("Android")
    }
}