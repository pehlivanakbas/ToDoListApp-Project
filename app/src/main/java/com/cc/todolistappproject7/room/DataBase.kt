package com.cc.todolistappproject7.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cc.todolistappproject7.data.entity.ToDoDatas


@Database(entities = [ToDoDatas::class], version = 2)
    abstract class DataBase : RoomDatabase() {
        abstract fun getTODOsDao() : ToDosDao
    }
