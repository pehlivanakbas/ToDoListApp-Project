package com.cc.todolistappproject7.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cc.todolistappproject7.data.entity.ToDoDatas
import com.cc.todolistappproject7.uix.vm.AddToDoPageViewModel
import com.cc.todolistappproject7.uix.vm.HomePageViewModel
import com.cc.todolistappproject7.uix.vm.ViewToDoPageViewModel
import com.google.gson.Gson


@Composable
fun PageTransitions(homePageViewModel: HomePageViewModel,
                    addToDoPageViewModel: AddToDoPageViewModel,
                    viewToDoPageViewModel: ViewToDoPageViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homepage"){
        composable("homepage"){
            HomePage(navController,homePageViewModel)
        }
        composable("addtoDoPage"){
            AddToDoPage(addToDoPageViewModel)
        }
        composable(
            "viewtoDoPage/{todo}",
            arguments = listOf(
                navArgument("todo") { type = NavType.StringType }
            )
        ){
            val json = it.arguments?.getString("todo")
            val nesne = Gson().fromJson(json,ToDoDatas::class.java)
            ViewToDoPage(nesne,viewToDoPageViewModel)
        }
    }
}