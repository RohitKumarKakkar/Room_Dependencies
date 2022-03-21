package com.kotlin.roomdependencies.DAOs

import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.roomdependencies.Entities.Contact

@Dao
interface ContactDAO {

    //Insert Data in Contact Table/Entity
    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore any error/modification if data is similar to existing data
    suspend fun insertContact(contact: Contact)


    //Update Data in Contact Table/Entity
    @Update
    suspend fun updateContact(contact: Contact)


    //Delete Data in Contact Table/Entity
    @Delete()
    suspend fun deleteContact(contact: Contact)


    //Select/Read Data from Contact Table/Entity
    @Query("SELECT * FROM CONTACT order by id ASC")
    fun getContact(): LiveData<List<Contact>> //we make it live data to tell the app that there are changes in data

}