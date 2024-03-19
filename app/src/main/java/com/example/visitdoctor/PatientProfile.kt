package com.example.visitdoctor

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.visitdoctor.Screen
import com.example.visitdoctor.ui.theme.VisitDoctorTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientProfile(navHostController: NavHostController) {
    var textName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textAge by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textWeight by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textDisability by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.medrt),
            contentDescription = "App Logo",
            modifier = Modifier.size(height = 50.dp, width = 50.dp),
            tint = Color(0xFF67C1AC)
        )
        Text(
            text = "CONSULT WITH SPECIALISTS",
            color = Color(0xFF019874),
            fontSize = 20.sp
        )
        Card(
            colors = CardDefaults.cardColors(Color(0xFFA3DBCE)),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Patient Profile",
                    color = Color.Black,
                    fontSize = 20.sp
                )
                TextField(
                    value = textName,
                    onValueChange = {
                        textName = it
                    },
                    placeholder = {
                        Text(text = "Enter Patient’s full name")
                    },
                    label = {
                        Text(text = "Enter Patient’s full name")
                    },
                    modifier = Modifier.padding(10.dp).padding(top = 20.dp)
                )
                TextField(
                    value = textAge,
                    onValueChange = {
                        textAge = it
                    },
                    placeholder = {
                        Text(text = "Enter Patient’s Age")
                    },
                    label = {
                        Text(text = "Enter Patient’s Age")
                    },
                    modifier = Modifier.padding(10.dp),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                TextField(
                    value = textWeight,
                    onValueChange = { textWeight = it },
                    placeholder = { Text(text = "Enter Patient’s Wight") },
                    modifier = Modifier.padding(10.dp),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                    visualTransformation = PasswordVisualTransformation()
                )
                val context = LocalContext.current
                Button(
                    onClick = {
//                        signUpWithEmailPassword(textEmail.text, textPass.text, navHostController)
                              navHostController.navigate(Screen.Dashboard.route)
                        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF2FC09E)
                    ),
                    enabled = true,
                    modifier = Modifier.padding(top = 50.dp)
                ) {
                    Text(
                        text = "Update",
                        fontSize = 25.sp
                    )
                }
            }
        }
    }
}

// Initialize Firebase Auth
//val authup = Firebase.auth

//fun signUpWithEmailPassword(email: String, password: String, navController: NavHostController) {
//    authup.createUserWithEmailAndPassword(email, password)
//        .addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Sign up success, navigate to the dashboard screen
//                navController.navigate(Screen.Dashboard.route) // Assuming Screen.Dashboard.route is your dashboard screen destination
//            } else {
//                // Sign up failed, display an error message to the user
//                Log.w(ContentValues.TAG, "createUserWithEmailAndPassword:failure", task.exception)
//                // Show toast message or handle error appropriately
//            }
//        }
//}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    VisitDoctorTheme {
        PatientProfile(navHostController = rememberNavController(),)
    }
}


