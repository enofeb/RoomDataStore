package com.enofeb.roomdatastore.di

import android.app.Application
import androidx.room.Room
import com.enofeb.roomdatastore.model.NoteDao
import com.enofeb.roomdatastore.model.NoteDatabase
import com.enofeb.roomdatastore.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NoteDatabase =
        Room.databaseBuilder(app, NoteDatabase::class.java, "note_database").build()

    @Provides
    fun provideNoteDao(db: NoteDatabase): NoteDao = db.noteDao()
} 