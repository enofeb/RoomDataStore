package com.enofeb.roomdatastore.domain.usecase

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemePreferencesUseCase @Inject constructor(
    private val app: Application
) {

    fun isDarkTheme(): Flow<Boolean> = app.themeDataStore.data
        .map { prefs -> prefs[DARK_MODE_KEY] ?: false }

    suspend fun setDarkTheme(enabled: Boolean) {
        app.themeDataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = enabled
        }
    }

    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    }
}

private val Application.themeDataStore by preferencesDataStore(name = "theme_prefs")