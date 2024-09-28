package com.example.contactapp2

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp2.database.ContactAppDatabase
import com.example.contactapp2.database.tables.Contact
import kotlinx.coroutines.launch

class ContactAppViewModel(context: Context) : ViewModel() {
    val db: ContactAppDatabase

    init {
        db = ContactAppDatabase.getInstance(context)
    }

    fun addUpdateContact(contact: Contact) {
        viewModelScope.launch {
            db.contactDAO().addContact(contact)
        }
    }

    fun deleteContact(contact: List<Contact>) {
        viewModelScope.launch {
            db.contactDAO().deleteContact(contact)
        }
    }
}