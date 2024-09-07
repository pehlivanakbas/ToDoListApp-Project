package com.cc.todolistappproject7.data.datasource

import com.cc.todolistappproject7.data.entity.ToDoDatas
import com.cc.todolistappproject7.room.ToDosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDoDataSource(var tddao: ToDosDao) {
    suspend fun save(todo_name: String) {
        val newtodo = ToDoDatas(0, todo_name)
        tddao.save(newtodo)
    }

    suspend fun update(todoId: Int, todoName: String) {
        val updatedToDo = ToDoDatas(todoId, todoName)
        tddao.update(updatedToDo)
    }

    suspend fun delete(todo_id: Int) {
        val deleteTodo = ToDoDatas(todo_id, "")
        tddao.delete(deleteTodo)
    }

    suspend fun LoadToDo(): List<ToDoDatas> = withContext(Dispatchers.IO) {
        return@withContext tddao.loadToDos()
    }

    suspend fun search(searchableText: String): List<ToDoDatas> = withContext(Dispatchers.IO) {
        return@withContext tddao.ara(searchableText)
    }
}