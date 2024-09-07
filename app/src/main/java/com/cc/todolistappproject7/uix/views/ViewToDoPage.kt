package com.cc.todolistappproject7.uix.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cc.todolistappproject7.data.entity.ToDoDatas
import com.cc.todolistappproject7.uix.vm.ViewToDoPageViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewToDoPage(getTodos: ToDoDatas, viewToDoPageVM: ViewToDoPageViewModel) {
    val toDoName = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        toDoName.value = getTodos.todo_name
    }

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Yapılacakları Görüntüle") }) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TextField(
                minLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                maxLines = 4,
                value = toDoName.value,
                onValueChange = { toDoName.value = it },
                label = { Text(text = "Yapılacaklar") }
            )



            Button(
                modifier = Modifier.size(250.dp, 50.dp),
                onClick = {
                    viewToDoPageVM.update(getTodos.todo_id, toDoName.value)
                }) { Text(text = "GÜNCELLE") }
        }
    }
}