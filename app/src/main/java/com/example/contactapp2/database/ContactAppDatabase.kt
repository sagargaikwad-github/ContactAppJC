package com.example.contactapp2.database

import android.content.Context
import android.provider.SyncStateContract.Constants
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactapp2.DB_NAME
import com.example.contactapp2.database.dao.ContactDAO
import com.example.contactapp2.database.tables.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactAppDatabase : RoomDatabase() {
    abstract fun contactDAO(): ContactDAO

    companion object {
        private var dbInstance: ContactAppDatabase? = null
        fun getInstance(context: Context): ContactAppDatabase {
            synchronized(this)
            {
                if (dbInstance == null) {
                    dbInstance =
                        Room.databaseBuilder(context, ContactAppDatabase::class.java, DB_NAME)
                            .build()
                }
                return dbInstance!!
            }
        }
    }
}