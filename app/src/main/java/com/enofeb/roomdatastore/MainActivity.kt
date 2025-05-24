package com.enofeb.roomdatastore

import android.os.Bundle
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
            RoomDataStoreTheme {
                val navController = rememberNavController()
                val viewModel: NoteViewModel = hiltViewModel()
                NavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: NoteViewModel) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NoteListScreen(
                notes = notes,
                onAddNoteClick = { navController.navigate("add") }
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