package com.example.babyrecorder.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.babyrecorder.data.ClickRecord
import com.example.babyrecorder.data.ClickRecordRepository
import com.example.babyrecorder.data.ClickRecordDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ClickRecordRepository
    
    val allRecords = ClickRecordDatabase.getDatabase(application).clickRecordDao().getAllRecords()
        .asLiveData()
    
    private val _recordCount = MutableLiveData<Int>()
    val recordCount: LiveData<Int> = _recordCount
    
    init {
        val database = ClickRecordDatabase.getDatabase(application)
        repository = ClickRecordRepository(database.clickRecordDao())
        refreshCount()
    }
    
    fun addClickRecord() {
        viewModelScope.launch {
            val record = ClickRecord(timestamp = System.currentTimeMillis())
            repository.insert(record)
            
            // 更新计数
            refreshCount()
        }
    }
    
    private fun refreshCount() {
        viewModelScope.launch {
            val database = ClickRecordDatabase.getDatabase(getApplication())
            val count = database.clickRecordDao().getCount()
            _recordCount.postValue(count)
        }
    }
}