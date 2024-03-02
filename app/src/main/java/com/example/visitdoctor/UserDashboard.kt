package com.example.visitdoctor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.visitdoctor.ui.theme.VisitDoctorTheme
import kotlinx.coroutines.launch

data class Doctor(val name: String, val specialization: String, val schedule: String)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun UserDashboard(navHostController: NavHostController) {
    var text by remember {
        mutableStateOf("")
    }
    val dialogShown = remember { mutableStateOf(false) }
    var selectedDoctor by remember { mutableStateOf<Doctor?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bars_solid),
                contentDescription = "Menu",
                modifier = Modifier.size(width = 45.dp, height = 35.dp)
            )
            Text(
                text = "Hello, Asif Hassan",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.search_glass),
                        contentDescription = "Search",
                        Modifier.size(25.dp)
                    )
                    Text(
                        text = "Search Doctor",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Doctors", fontSize = 20.sp)
        val tabItems = listOf("Cardiology", "Endocrinology", "Neurology", "Urology")
        val specialistDoctors = listOf(
            listOf(
                Doctor("Dr. John Doe", "Cardiologist", "Sunday, 12 March : 11.00 - 12.00 AM"),
                Doctor("Dr. Alice Smith", "Cardiologist", "Friday, 10 March : 12.00 - 9.00 PM"),
                Doctor("Dr. Emma Johnson", "Cardiologist", "Friday, 10 March : 12.00 - 9.00 PM")
            ),
            listOf(
                Doctor("Dr. Michael Brown", "Endocrinologist", "Friday, 10 March : 12.00 - 9.00 PM"),
                Doctor("Dr. Sarah Lee", "Endocrinologist", "Friday, 10 March : 12.00 - 9.00 PM")
            ),
            listOf(
                Doctor("Dr. David Clark", "Neurologist", "Friday, 10 March : 12.00 - 9.00 PM"),
                Doctor("Dr. Jennifer White", "Neurologist", "Friday, 10 March : 12.00 - 9.00 PM"),
                Doctor("Dr. James Anderson", "Neurologist", "Friday, 10 March : 12.00 - 9.00 PM")
            ),
            listOf(
                Doctor("Dr. Robert Taylor", "Urologist", "Friday, 10 March : 12.00 - 9.00 PM"),
                Doctor("Dr. Olivia Wilson", "Urologist", "Friday, 10 March : 12.00 - 9.00 PM"),
                Doctor("Dr. Daniel Martinez", "Urologist", "Friday, 10 March : 12.00 - 9.00 PM"),
                Doctor("Dr. Emily Garcia", "Urologist", "Friday, 10 March : 12.00 - 9.00 PM")
            )
        )
        var selectedTabIndex by remember {
            mutableStateOf(0)
        }
        val coroutineScope = rememberCoroutineScope()
        var pagerState = rememberPagerState {
            tabItems.size
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed { index, title ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(selectedTabIndex)
                            }
                        },
                        text = { Text(text = title) },
                        selectedContentColor = Color(0xFFBB8906),
                        unselectedContentColor = Color(0xFF019874)
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { index ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(specialistDoctors[index]) { doctor ->
                        Button(
                            onClick = {
                                selectedDoctor = doctor
                                dialogShown.value = true
                            },
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {
                            Text(
                                text = "${doctor.name}, ${doctor.specialization}",
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    selectedTabIndex = pagerState.currentPage
                }
            }
        }

        if (dialogShown.value && selectedDoctor != null) {
            AlertDialog(
                onDismissRequest = {
                    dialogShown.value = false
                },
                title = {
                    Text(text = selectedDoctor!!.name)
                },
                text = {
                    Column {
                        Text(text = "Specialization: ${selectedDoctor!!.specialization}")
                        Text(text = "Schedule: ${selectedDoctor!!.schedule}")
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            dialogShown.value = false
                        }
                    ) {
                        Text(text = "Book")
                    }
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun UserDashboardPreview(){
    VisitDoctorTheme {
        UserDashboard(navHostController = rememberNavController())
    }
}