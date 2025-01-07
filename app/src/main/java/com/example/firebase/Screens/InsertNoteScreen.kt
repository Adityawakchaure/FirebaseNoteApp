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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import com.example.firebase.Navigaion.Routes
import com.example.firebase.ui.theme.firebaseBlack
import com.example.firebase.ui.theme.firebaseGray

@Composable
fun insertNoteScreen(navcantroller: NavHostController, viewmodel: NoteViewModel)
{
    var contaxt= LocalContext.current
    //var viewmodel=NoteViewModel()
    var text by remember {
        mutableStateOf("")
    }
    var Discription by remember {
        mutableStateOf("")
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //  navcantroller.navigate(Routes.ShowData)//
                var notes = Notes(
                    title = text,
                    discription = Discription
                )
                if (text.isNotEmpty() && Discription.isNotEmpty()) {
                    viewmodel.postData(notes = notes,
                        OnSuccess = {
                            Toast.makeText(contaxt, "Suceess", Toast.LENGTH_SHORT).show()
                        },
                        OnFailuler = {
                            Toast.makeText(contaxt, "Fail to Upload", Toast.LENGTH_SHORT).show()
                        }
                    )

                }

                else{
                    Toast.makeText(contaxt, "Enter data", Toast.LENGTH_SHORT).show()
            }
                //

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
                Text(text = "Insert Data", style = TextStyle(fontSize =32.sp, color = Color.White, fontWeight = FontWeight.Bold ))

              // State to hold the text value

                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    colors = TextFieldDefaults.colors(focusedContainerColor = firebaseGray, unfocusedContainerColor = firebaseGray, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent)
                  ,  label = { Text(text = "Enter the title", style = TextStyle(fontSize = 18.sp, color = Color.White,fontWeight = FontWeight.Bold))},
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
                    ,  label = { Text(text = "Enter the Discription", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White))},
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