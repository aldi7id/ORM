package com.ajgroup.orm

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object{
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context):StudentDatabase? {
            if(INSTANCE == null){
                synchronized(StudentDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    StudentDatabase::class.java,"Student.db").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}