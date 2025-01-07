package com.example.firebase.Screens

import Firebase.NoteViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firebase.Navigaion.Routes
import com.example.firebase.R
import com.example.firebase.ui.theme.firebaseBlack
import kotlinx.coroutines.delay

@Composable
fun splashScreen(navcantroller: NavHostController, viewmodel: NoteViewModel) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(color = firebaseBlack))
    {

        Image(
            painter = painterResource(id = R.drawable.fb), contentDescription = "",
            modifier = Modifier
                .size(150.dp)

        )

        LaunchedEffect(key1 = Unit) {
            delay(3000)
            navcantroller.navigate(Routes.ShowData){
                popUpTo("splashScreen"){
                    inclusive=true
                }
            }
        }



    }


}
