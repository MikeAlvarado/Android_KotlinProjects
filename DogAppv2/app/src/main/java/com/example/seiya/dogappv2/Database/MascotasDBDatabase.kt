package com.example.seiya.dogappv2.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(MascotaDB::class), version = 1, exportSchema =  false)


public abstract class MascotasDBDatabase : RoomDatabase(){

    abstract fun MascotaDBDao() : MascotaDBDao

    companion object {
        private val DATABASE_NAME = "MascotasDBDatabase.db"
        private var dbInstance : MascotasDBDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MascotasDBDatabase {
            if (dbInstance == null){
                dbInstance = buildDatabase(context)
            }
            return dbInstance!!
        }

        private fun buildDatabase(context: Context): MascotasDBDatabase {
            return Room.databaseBuilder(context,
                    MascotasDBDatabase::class.java,
                    DATABASE_NAME)
                    .build()
        }
    }

}