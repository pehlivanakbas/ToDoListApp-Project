package com.cc.todolistappproject7.uix.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cc.todolistappproject7.R
import com.cc.todolistappproject7.uix.vm.HomePageViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, homePageVM: HomePageViewModel) {
    val isSearching = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val todoList = homePageVM.todolists.observeAsState(listOf())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }



    LaunchedEffect(key1 = true) {
        homePageVM.loadTODOs()
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching.value) {
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                homePageVM.search(it)
                            },
                            label = { Text(text = "Ara") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedLabelColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedLabelColor = Color.Black,
                                unfocusedIndicatorColor = Color.White
                            )
                        )
                    } else {
                        Text(text = "Yapılacaklar")
                    }
                },
                actions = {
                    if (isSearching.value) {
                        IconButton(onClick = {
                            isSearching.value = false
                            tf.value = ""
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.kapat_resim),
                                contentDescription = ""
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            isSearching.value = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ara_resim),
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addtoDoPage") },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ekle_resim),
                        contentDescription = ""
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                count = todoList.value.count(),
                itemContent = {//0,1,2
                    val todo = todoList.value[it]
                    Card(modifier = Modifier.padding(all = 10.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                /*.background(
                                Color(Random.nextFloat(),Random.nextFloat(),Random.nextFloat())
                            )

                                 */
                                .clickable {
                                    val toDoJson = Gson().toJson(todo)
                                    navController.navigate("viewtoDoPage/$toDoJson")
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(all = 10.dp)
                                    .width(250.dp)
                            ) {
                                Text(
                                    text = todo.todo_name,
                                    fontSize = 20.sp,
                                    color = Color.Black,
                                    fontFamily = FontFamily.SansSerif
                                )
                            }
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        val sb = snackbarHostState.showSnackbar(
                                            message = "${todo.todo_name} silinsin mi?",
                                            actionLabel = "EVET"
                                        )

                                        if (sb == SnackbarResult.ActionPerformed) {
                                homePageVM.delete(todo.todo_id)
                                                        }
                                    }
                                }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.kapat_resim),
                                    contentDescription = "", tint = Color.Gray
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}