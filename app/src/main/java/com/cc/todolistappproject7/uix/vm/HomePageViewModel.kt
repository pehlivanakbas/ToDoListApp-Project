package com.cc.todolistappproject7.uix.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cc.todolistappproject7.data.entity.ToDoDatas
import com.cc.todolistappproject7.data.repository.ToDosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var tdrepo:ToDosRepository) : ViewModel() {
    var todolists = MutableLiveData<List<ToDoDatas>>()

    init {
        loadTODOs()
    }

    fun delete(kisi_id:Int) {
        CoroutineScope(Dispatchers.Main).launch {
            tdrepo.sil(kisi_id)
            loadTODOs()
        }
    }

    fun loadTODOs(){
        CoroutineScope(Dispatchers.Main).launch {
            todolists.value = tdrepo.loadToDos()
        }
    }

    fun search(searchingtext:String){
        CoroutineScope(Dispatchers.Main).launch {
            todolists.value = tdrepo.search(searchingtext)
        }
    }
}