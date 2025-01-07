package com.example.firebase.Screens

import Firebase.NoteViewModel
import Firebase.Notes
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebase.Navigaion.Routes
import com.example.firebase.ui.theme.firebaseBlack
import com.example.firebase.ui.theme.firebaseGray
import com.example.firebase.ui.theme.firebaseTitle
import com.example.firebase.ui.theme.firebaserade


@Composable
fun noteScreen(navcantroller: NavHostController, viewmodel: NoteViewModel) {
   // var viewmodel=NoteViewModel()
    LaunchedEffect(key1 = Unit) {
        viewmodel.getAllData()
    }
  //  val addNotes= viewmodel.NoteAll.collectAsState()
    val addNotes by viewmodel.NoteAll.observeAsState(emptyList())

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {navcantroller.navigate(Routes.Insert) }, contentColor =Color.White, containerColor = Color.Red, shape = RoundedCornerShape(corner = CornerSize(100.dp)) ) {
                Icon(imageVector = Icons.Default.Add, contentDescription ="" )
            }
        }
    ){ innerpadding ->
        Box(modifier = Modifier
            .padding(innerpadding)
            .background(color = firebaseBlack)
            .fillMaxSize()
        )
        {


            Column(modifier = Modifier.padding(15.dp)) {
                Text(text = "Create Notes Crud", style = TextStyle(fontSize =32.sp, color = Color.White, fontWeight = FontWeight.Bold ))

                LazyColumn {
                    items(addNotes){ notes->

                    ListItem(notes,viewmodel,navcantroller)


                    }
                }
            }
        }

    }
}



@Composable
fun ListItem(note: Notes, viewmodel: NoteViewModel, navcantroller: NavHostController){
    var contaxt= LocalContext.current

    Box(modifier = Modifier
        .fillMaxWidth()

        .padding(10.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(20.dp)))
        .background(color = firebaseGray)

    ) {
        Icon(imageVector = Icons.Default.Delete,
            contentDescription = "",
            tint = firebaserade,
            modifier = Modifier
                .clickable {
                    val id = note.uniqueId
                    viewmodel.delData(id,
                        onSucess = {
                            Toast
                                .makeText(contaxt, "Delete", Toast.LENGTH_SHORT)
                                .show()
                        },
                        onFailuler = {
                            Toast
                                .makeText(contaxt, "Fail Delete", Toast.LENGTH_SHORT)
                                .show()
                        }
                    )

                }
                .align(alignment = Alignment.TopEnd)
                .padding(10.dp))
        // 2
        Icon(imageVector = Icons.Default.Refresh,
            contentDescription = "",
            tint = Color.Green,
            modifier = Modifier
                .clickable {
                    var id = note.uniqueId
                    var title = note.title
                    var description = note.discription
                    val route = "updateScreen/$id/${Uri.encode(title)}/${Uri.encode(description)}"
                    navcantroller.navigate(route)

                }
                .align(alignment = Alignment.BottomEnd)
                .padding(10.dp))



        Column (modifier = Modifier.padding(20.dp)){
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = firebaserade, fontWeight = FontWeight.Bold)) {
                        append("Title: ")
                    }
                    withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.ExtraBold)) {
                        append(note.title)
                    }
                },
                style = TextStyle(fontSize = 15.sp)
            )

            Divider(modifier = Modifier, color = firebaseGray)
//            Text(text = "Title: $note.title", style = TextStyle(fontSize =15.sp, color = Color.White, fontWeight = FontWeight.Bold ))
            Text(text = "${note.discription}",style = TextStyle(color = Color.White))

           // Text(text = note.uniqueId,style = TextStyle(color = Color.White))
        }
    }

}