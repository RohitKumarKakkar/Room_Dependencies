package com.kotlin.roomdependencies.Databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kotlin.roomdependencies.Converters.Converters
import com.kotlin.roomdependencies.DAOs.ContactDAO
import com.kotlin.roomdependencies.Entities.Contact

/*
    versions are used to define changes we made in database from perivious version of the app
    after app is live and users are using that
*/

@Database(
    entities = [Contact::class],
    version = 2,
    exportSchema = false
) //Version updated during Migration
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {

    //Linking DAO to Database
    abstract fun contactDao(): ContactDAO

    //Createing Singleton Database as We Do in Saperate Class in Shared Preferences

    companion object {

        //Migration Object

        val migration_1_2 = object : Migration(1, 2) { // Versions FROM (1) TO (2)
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }
        }

        /*    Any Value Assigned/Changed to this instance variable - All the Threads got updated and available to
            All Threads Using Volatile      */

        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(this) {  // To Create DB in Background Thread , But Only Once
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext, ContactDatabase::class.java,
                            "Contact DB"
                        ).addMigrations(migration_1_2).build()
                }
            }
            return INSTANCE!!
        }


    }
}