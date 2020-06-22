package com.ng.ngbaselib.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blankj.utilcode.util.Utils
import com.ng.ngbaselib.data.migration.MIGRATION
import com.ng.ngbaselib.show.bean.BannerBean

/**
 *   @auther : Aleyn
 *   time   : 2020/03/12
 */
@Database(entities = [HomeListBean::class, BannerBean::class], version = 2, exportSchema = false)
abstract class LinDatabase : RoomDatabase() {

    abstract fun homeLocaData(): HomeDao


    companion object {
        fun getInstanse() = SingletonHolder.INSTANCE
    }

    private object SingletonHolder {
        val INSTANCE = Room.databaseBuilder(
                Utils.getApp(),
                LinDatabase::class.java,
                "lin_db"
            )
            .addMigrations(MIGRATION.MIGRATION_1_2)
            .build()
    }
}