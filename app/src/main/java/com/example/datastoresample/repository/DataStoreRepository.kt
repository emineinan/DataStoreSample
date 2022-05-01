package com.example.datastoresample.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastoresample.model.Book
import kotlinx.coroutines.flow.map

const val DATASTORE_NAME = "BOOK"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)

class DataStoreRepository(private val context: Context) {

    companion object {
        val NAME = stringPreferencesKey("NAME")
        val AUTHOR = stringPreferencesKey("AUTHOR")
    }

    suspend fun saveBook(book: Book) {
        context.dataStore.edit {
            it[NAME] = book.name
            it[AUTHOR] = book.author
        }
    }

    fun getBook() = context.dataStore.data.map {
        Book(it[NAME]!!, it[AUTHOR]!!)
    }
}