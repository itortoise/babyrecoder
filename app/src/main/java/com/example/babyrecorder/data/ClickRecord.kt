package com.example.babyrecorder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "click_records")
data class ClickRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long
)