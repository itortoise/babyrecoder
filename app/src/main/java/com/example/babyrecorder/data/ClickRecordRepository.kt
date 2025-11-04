package com.example.babyrecorder.data

import kotlinx.coroutines.flow.Flow

class ClickRecordRepository(private val dao: ClickRecordDao) {
    fun getAllRecords(): Flow<List<ClickRecord>> = dao.getAllRecords()
    
    suspend fun getCount(): Int = dao.getCount()
    
    suspend fun insert(record: ClickRecord) = dao.insert(record)
}