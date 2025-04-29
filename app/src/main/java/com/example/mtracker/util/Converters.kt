package com.example.mtracker.util

import androidx.room.TypeConverter
import com.example.mtracker.data.models.MoodScale
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromMoodScale(moodScale: MoodScale): String {
        return moodScale.name
    }

    @TypeConverter
    fun toMoodScale(moodScale: String): MoodScale {
        return MoodScale.valueOf(moodScale)
    }
}