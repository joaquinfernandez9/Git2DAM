package com.example.crudjoaquinfernandez.data

import android.content.Context
import androidx.room.Database
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HeadsetEntity::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class) <- para pasar a date y tal
abstract class HeadsetRoomDataBase: RoomDatabase(){

    abstract fun headsetDao(): HeadsetDao

    companion object {
        @Volatile
        private var INSTANCE: HeadsetRoomDataBase? = null

        fun getDatabase(context: Context): HeadsetRoomDataBase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeadsetRoomDataBase::class.java,
                    "headset_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}