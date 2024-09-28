package com.example.contactapp2.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactapp2.database.tables.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDAO {
    @Upsert
    suspend fun addContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: List<Contact>)

    @Query("SELECT * FROM contact_table")
    fun getAllContacts(): Flow<List<Contact>>


}