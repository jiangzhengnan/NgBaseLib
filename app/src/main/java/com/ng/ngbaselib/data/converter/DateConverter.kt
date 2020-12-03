package com.ng.ngbaselib.data.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * 描述:
 * @author Jzn
 * @date 2020/12/3
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
