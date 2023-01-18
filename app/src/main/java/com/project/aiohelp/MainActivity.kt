package com.project.aiohelp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.aiohelp.ui.theme.AIOHelpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIOHelpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun UserMain(navController: NavController) {
    TopAppBar(title = { Text(text = "AIO App") }, navigationIcon = {
        Icon(
            Icons.Filled.Home,
            contentDescription = "Home Menu"
        )
    })
    Column(
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .padding(20.dp, 80.dp)
    ) {

        // Carpenter

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.BookingPage.withArgs("Carpenter")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image1),
                    contentDescription = stringResource(
                        id = R.string.Image1
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = stringResource(id = R.string.Image1),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(60.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        // Plumber

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.BookingPage.withArgs("Plumber")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image2),
                    contentDescription = stringResource(
                        id = R.string.Image2
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = stringResource(id = R.string.Image2),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(60.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        // Gardener

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.BookingPage.withArgs("Gardener")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image3),
                    contentDescription = stringResource(
                        id = R.string.Image3
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = stringResource(id = R.string.Image3),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(60.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        // House Cleaner

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.BookingPage.withArgs("House Cleaner")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image4),
                    contentDescription = stringResource(
                        id = R.string.Image4
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = stringResource(id = R.string.Image4),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(30.dp, 0.dp, 10.dp, 0.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        // Laundry

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.BookingPage.withArgs("Laundry")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image5),
                    contentDescription = stringResource(
                        id = R.string.Image5
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = stringResource(id = R.string.Image5),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(60.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}


@Composable
fun BookingPage(navController: NavController, job: String) {
    val dbManipulation = DBManipulation()
    val workerList = dbManipulation.getWorker(job)
    TopAppBar(title = { Text(text = "Available Workers") }, navigationIcon = {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back")
        }
    })

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(20.dp, 80.dp)
    ) {
        itemsIndexed(workerList) { index, _ ->
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .height(100.dp)
                    .fillMaxWidth()
                    .clickable { /* TODO */ },
                elevation = CardDefaults.cardElevation(10.dp, 5.dp)
            ) {
                Column {
                    workerList[index]?.name?.let {
                        Text(text = it)
                    }
                    workerList[index]?.age?.let {
                        Text(text = it)
                    }
                }
            }
        }
    }
}
