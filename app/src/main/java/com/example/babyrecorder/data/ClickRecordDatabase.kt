package com.example.babyrecorder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ClickRecord::class],
    version = 1,
    exportSchema = false
)
abstract class ClickRecordDatabase : RoomDatabase() {
    abstract fun clickRecordDao(): ClickRecordDao

    companion object {
        @Volatile
        private var INSTANCE: ClickRecordDatabase? = null

        fun getDatabase(context: Context): ClickRecordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClickRecordDatabase::class.java,
                    "click_record_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}