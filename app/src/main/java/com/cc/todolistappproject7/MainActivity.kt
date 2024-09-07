package com.cc.todolistappproject7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.cc.todolistappproject7.ui.theme.ToDoListAppProject7Theme
import com.cc.todolistappproject7.uix.views.PageTransitions
import com.cc.todolistappproject7.uix.vm.AddToDoPageViewModel
import com.cc.todolistappproject7.uix.vm.HomePageViewModel
import com.cc.todolistappproject7.uix.vm.ViewToDoPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val homePageVM:HomePageViewModel by viewModels()
    val addTODOPageVM:AddToDoPageViewModel by viewModels()
    val viewtodoPageVM:ViewToDoPageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListAppProject7Theme {
                PageTransitions(homePageVM,addTODOPageVM,viewtodoPageVM)

            }
        }
    }
}

