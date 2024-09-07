package com.cc.todolistappproject7.uix.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cc.todolistappproject7.uix.vm.AddToDoPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDoPage(addToDoPageViewModel: AddToDoPageViewModel){
    val tfToDo = remember { mutableStateOf("") }

    fun kaydet(todo_name:String){
        Log.e("Yaplılacaklar Ekle","$todo_name ")
    }

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Yeni Kayıt Ekle") }) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                minLines = 3,
                value = tfToDo.value,
                onValueChange = { tfToDo.value = it },
                label = { Text(text = "Yapılacaklar Ekle") }
            )


            Button(
                modifier = Modifier.size(250.dp,50.dp),
                onClick = {
                    addToDoPageViewModel.save(tfToDo.value)
                }) { Text(text = "KAYDET") }
        }
    }
}