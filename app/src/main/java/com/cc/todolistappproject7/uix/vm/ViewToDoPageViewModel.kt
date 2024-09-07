package com.cc.todolistappproject7.uix.vm

import androidx.lifecycle.ViewModel
import com.cc.todolistappproject7.data.repository.ToDosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewToDoPageViewModel @Inject constructor(var todorepo: ToDosRepository) : ViewModel() {
    fun update(todo_id:Int,toDo_name:String) {
        CoroutineScope(Dispatchers.Main).launch {
            todorepo.update(todo_id, toDo_name)
        }
    }

}