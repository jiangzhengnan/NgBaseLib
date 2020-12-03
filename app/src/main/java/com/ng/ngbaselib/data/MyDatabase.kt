package com.ng.ngbaselib.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blankj.utilcode.util.Utils
import com.ng.ngbaselib.data.converter.DateConverter
import com.ng.ngbaselib.data.converter.LicenseConverter
import com.ng.ngbaselib.data.converter.OwnerConverter
import com.ng.ngbaselib.data.converter.SearchItemConverter
import com.ng.ngbaselib.data.dao.SearchDao
import com.ng.ngbaselib.http.bean.License
import com.ng.ngbaselib.http.bean.Owner
import com.ng.ngbaselib.http.bean.SearchItem

@Database(
    entities = [SearchItem::class, Owner::class, License::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    SearchItemConverter::class,
    OwnerConverter::class,
    DateConverter::class,
    LicenseConverter::class
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun searchLocalData(): SearchDao

    companion object {
        fun getInstanse() = SingletonHolder.INSTANCE
    }

    private object SingletonHolder {
        val INSTANCE = Room.databaseBuilder(
            Utils.getApp(),
            MyDatabase::class.java,
            "ng_base_db"
        )
            .allowMainThreadQueries()//允许在主线程查询数据
            // .addMigrations(MIGRATION.MIGRATION_1_2)
            .build()
    }
}