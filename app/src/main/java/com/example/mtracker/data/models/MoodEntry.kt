package com.example.mtracker.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class MoodEntry(
    @PrimaryKey(autoGenerate = true)
    val entryId: Long = 0L,
    val date: Date,
    val moodScale: MoodScale,
    val moodScore: Int,
    val notes: String?
)