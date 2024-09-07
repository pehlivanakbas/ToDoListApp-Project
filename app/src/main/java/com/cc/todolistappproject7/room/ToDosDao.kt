package com.cc.todolistappproject7.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cc.todolistappproject7.data.entity.ToDoDatas

@Dao
interface ToDosDao {
    @Query("SELECT * FROM toDoss")
    suspend fun loadToDos() : List<ToDoDatas>

    @Insert
    suspend fun save(todos:ToDoDatas)

    @Update
    suspend fun update(todos:ToDoDatas)

    @Delete
    suspend fun delete(todos:ToDoDatas)

    @Query("SELECT * FROM toDoss WHERE todo_name like '%' || :searchtext || '%'")
    suspend fun ara(searchtext:String) : List<ToDoDatas>
}
