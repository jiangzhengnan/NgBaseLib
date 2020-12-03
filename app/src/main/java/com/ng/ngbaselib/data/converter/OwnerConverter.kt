package com.ng.ngbaselib.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ng.ngbaselib.http.bean.Owner
import com.ng.ngbaselib.http.bean.SearchItem
import java.lang.reflect.Type


/**
 * 描述:
 * @author Jzn
 * @date 2020/8/4
 */
class OwnerConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObject(data: String?): Owner? {
        if (data.isNullOrEmpty()) {
            return null
        }
        val listType: Type = object : TypeToken<Owner?>() {}.type
        return gson.fromJson<Owner>(data, listType)
    }

    @TypeConverter
    fun someObjectToString(someObjects: Owner?): String? {
        return gson.toJson(someObjects)
    }

}