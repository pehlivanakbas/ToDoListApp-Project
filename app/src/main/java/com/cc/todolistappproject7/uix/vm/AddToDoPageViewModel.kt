package com.cc.todolistappproject7.uix.vm

import androidx.lifecycle.ViewModel
import com.cc.todolistappproject7.data.repository.ToDosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoPageViewModel@Inject constructor(var todorepo:ToDosRepository) : ViewModel() {
    fun save(todo_name:String){
        CoroutineScope(Dispatchers.Main).launch {
            todorepo.save(todo_name)
        }
    }
}