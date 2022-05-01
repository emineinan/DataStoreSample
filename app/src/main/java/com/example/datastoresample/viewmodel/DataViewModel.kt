package com.example.datastoresample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastoresample.model.Book
import com.example.datastoresample.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData("")
    val author: MutableLiveData<String> = MutableLiveData("")
    val book: MutableLiveData<Book> = MutableLiveData()

    fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBook(Book(name.value!!, author.value!!))
        }
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getBook().collect {
                book.postValue(it)
            }
        }
    }
}