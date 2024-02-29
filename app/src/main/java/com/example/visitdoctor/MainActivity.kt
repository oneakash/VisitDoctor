package com.example.visitdoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.visitdoctor.ui.theme.VisitDoctorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisitDoctorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
        Image(
            painter = painterResource(id = R.drawable.undraw_doctors_p6aq),
            contentDescription = "Medical Specialists",
            modifier = Modifier.size(height = 250.dp, width = 200.dp)
        )
        Text(
            text = "Book Specialists Doctor's " +
                    "Appointment " +
                    "From The Best Hospitals of Bangladesh",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(20.dp)
                .padding(
                    start = 10.dp
                )
        )
        Button(
            onClick = {
                      navHostController.navigate(Screen.First.route)
            },
            colors = ButtonDefaults.buttonColors(
                Color(0xFF2FC09E)
            ),
            enabled = true,
            modifier = Modifier
                .padding(50.dp)
                .padding(top = 90.dp)
        ) {
            Text(
                text = "Get Started",
                fontSize = 25.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VisitDoctorTheme {
        Greeting(navHostController = rememberNavController())
    }
}