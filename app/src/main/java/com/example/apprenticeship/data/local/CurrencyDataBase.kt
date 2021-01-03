package com.example.apprenticeship.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apprenticeship.data.local.entities.CurrencyRoomEntity

@Database(entities = arrayOf(CurrencyRoomEntity::class),version = 1)
abstract class CurrencyDataBase: RoomDatabase() {
    abstract fun currencyDao():CurrencyDao

    companion object{
        private var INSTANCE: CurrencyDataBase? = null
        fun getDatabase(context: Context): CurrencyDataBase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context,CurrencyDataBase::class.java,"CurrencyDB").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}