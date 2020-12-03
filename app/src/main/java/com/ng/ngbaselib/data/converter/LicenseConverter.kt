package com.ng.ngbaselib.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ng.ngbaselib.http.bean.License
import java.lang.reflect.Type

/**
 * 描述:
 * @author Jzn
 * @date 2020/12/3
 */
class LicenseConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObject(data: String?): License? {
        if (data.isNullOrEmpty()) {
            return null
        }
        val listType: Type = object : TypeToken<License?>() {}.type
        return gson.fromJson<License>(data, listType)
    }

    @TypeConverter
    fun someObjectToString(someObjects: License?): String? {
        return gson.toJson(someObjects)
    }
}