package com.example.firebase

import Firebase.NoteViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase.Navigaion.Routes
import com.example.firebase.Screens.insertNoteScreen
import com.example.firebase.Screens.noteScreen
import com.example.firebase.Screens.splashScreen
import com.example.firebase.Screens.updateScreen
import com.example.firebase.ui.theme.FirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            FirebaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
             // splashScreen()

                  // noteScreen(navcantroller =navcantroller)
                   // insertNoteScreen()
                    Nav()
                }
            }
        }
    }
}

@Composable
fun Nav(){
    var viewmodel=NoteViewModel()
    var navcantroller= rememberNavController()
   NavHost(navController = navcantroller, startDestination = Routes.SplashScreen) {
       composable(Routes.Insert){
           insertNoteScreen(navcantroller,viewmodel)
       }
       composable(Routes.ShowData){

           noteScreen(navcantroller,viewmodel)
       }
       composable(Routes.SplashScreen){
           splashScreen(navcantroller,viewmodel)
       }
       composable(Routes.UpdateScreen){
           val navBackStackEntry = navcantroller.currentBackStackEntry
           val id = navBackStackEntry?.arguments?.getString("id")
           val title = navBackStackEntry?.arguments?.getString("title")
           val description = navBackStackEntry?.arguments?.getString("description")
           updateScreen(id,title,description,viewmodel)
       }


   }
}