package com.cc.todolistappproject7.di

import android.content.Context
import androidx.room.Room
import com.cc.todolistappproject7.data.datasource.ToDoDataSource
import com.cc.todolistappproject7.data.repository.ToDosRepository
import com.cc.todolistappproject7.room.DataBase
import com.cc.todolistappproject7.room.ToDosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTODORepository(todods:ToDoDataSource) : ToDosRepository {
        return ToDosRepository(todods)
    }

    @Provides
    @Singleton
    fun provideTODODataSource(tdldao:ToDosDao) : ToDoDataSource {
        return ToDoDataSource(tdldao)
    }
    @Provides
    @Singleton
    fun provideTodosDao(@ApplicationContext context: Context) : ToDosDao {
        val db = Room.databaseBuilder(context,DataBase::class.java,"toDoss.sqlite")
            .createFromAsset("toDoss.sqlite").build()
        return db.getTODOsDao()
    }
}