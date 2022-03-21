package com.kotlin.roomdependencies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.roomdependencies.Databases.ContactDatabase
import com.kotlin.roomdependencies.Entities.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repository: ContactRepository
    val allContact: LiveData<List<Contact>> //getting data from ContactRepository

    init {
        val dao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(dao)
        allContact = repository.allContacts
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(contact)
    }

    fun insertContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(contact)
    }

    fun updateContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(contact)
    }

}