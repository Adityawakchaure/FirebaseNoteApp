package com.example.firebase.Screens

import Firebase.NoteViewModel
import Firebase.Notes
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebase.ui.theme.firebaseBlack
import com.example.firebase.ui.theme.firebaseGray

@Composable
fun updateScreen(id: String?, title: String?, description: String?, viewmodel: NoteViewModel) {
    var contaxt= LocalContext.current
    //var viewmodel=NoteViewModel()
    var text by remember {
        mutableStateOf("")
    }
    var Discription by remember {
        mutableStateOf("")
    }
    if (title != null) {
        text=title
    }
    if (description != null) {
        Discription=description
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                var updatedData= id?.let {
                    Notes(
                        uniqueId = id,
                        title = text,
                        discription = Discription
                    )
                }
                if (updatedData != null) {
                    viewmodel.updateData(id,updatedData,
                        OnSucess = {Toast.makeText(contaxt,"Update Sucessfully",Toast.LENGTH_SHORT).show()},
                        OnFailuler = {Toast.makeText(contaxt,"Update Fail",Toast.LENGTH_SHORT).show()}
                    )
                }


            }, shape = RoundedCornerShape(100.dp), containerColor = Color.Red, contentColor = Color.White) {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "")
            }
        }
    ) { innerpadding->
        Box(modifier = Modifier
            .padding(innerpadding)
            .fillMaxSize()
            .background(color = firebaseBlack)
        ){
            Column (modifier = Modifier
                .padding(15.dp)
            ){
                Text(text = "Update Screen", style = TextStyle(fontSize =32.sp, color = Color.White, fontWeight = FontWeight.Bold ))

                // State to hold the text value

                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    colors = TextFieldDefaults.colors(focusedContainerColor = firebaseGray, unfocusedContainerColor = firebaseGray, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent)
                    ,  label = { Text(text = "Enter the title", style = TextStyle(fontSize = 18.sp, color = Color.White,fontWeight = FontWeight.Bold)) },
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(5.dp))

                TextField(
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    colors = TextFieldDefaults.colors(focusedContainerColor = firebaseGray, unfocusedContainerColor = firebaseGray, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, focusedTextColor = Color.White)
                    ,  label = { Text(text = "Enter the Discription", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)) },
                    value = Discription,
                    onValueChange = { newText ->
                        Discription = newText
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                )
                Spacer(modifier = Modifier.height(5.dp))



            }

        }

    }
}