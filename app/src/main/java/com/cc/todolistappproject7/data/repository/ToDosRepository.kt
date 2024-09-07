package com.cc.todolistappproject7.data.repository

import android.util.Log
import com.cc.todolistappproject7.data.datasource.ToDoDataSource
import com.cc.todolistappproject7.data.entity.ToDoDatas

class ToDosRepository(var todods:ToDoDataSource){
    suspend fun save(todo_name:String)=todods.save(todo_name)

    suspend fun update(todo_id:Int,todo_name:String) = todods.update(todo_id,todo_name)

    suspend fun sil(todo_id:Int) = todods.delete(todo_id)

    suspend fun loadToDos() : List<ToDoDatas> = todods.LoadToDo()

    suspend fun search(searchableText:String) : List<ToDoDatas> = todods.search(searchableText)
    }
