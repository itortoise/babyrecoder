package com.example.babyrecorder.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClickRecordDao {
    @Query("SELECT * FROM click_records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<ClickRecord>>

    @Query("SELECT COUNT(*) FROM click_records")
    suspend fun getCount(): Int

    @Insert
    suspend fun insert(record: ClickRecord)
}