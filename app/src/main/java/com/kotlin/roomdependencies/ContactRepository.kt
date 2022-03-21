package com.kotlin.roomdependencies

import androidx.lifecycle.LiveData
import com.kotlin.roomdependencies.DAOs.ContactDAO
import com.kotlin.roomdependencies.Entities.Contact

/*
    This File is made as an API Calling structure for our ViewModel to Handle Data
    This file works as a mediator between our data layer and our viewModel, Our Viewmodel will ask for data from
    this file and this file has repsonsibility to provide data to viewmodel. this is a good practice to use this coz ,
    when we make changes in our database or our API or our interface , this wil handle all the changes and our viewmodel
    will be intact means we dont have to do changes in our view model
*/

class ContactRepository(private val contactDao: ContactDAO) {

    //Getting Contacts From DB
    val allContacts: LiveData<List<Contact>> = contactDao.getContact()

    //Insert Contact
    suspend fun insert(contact: Contact) {
        contactDao.insertContact(contact)
    }

    //Update Contact
    suspend fun update(contact: Contact) {
        contactDao.updateContact(contact)
    }

    //Delete Contact
    suspend fun delete(contact: Contact) {
        contactDao.deleteContact(contact)
    }
}