package com.project.aiohelp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { /* TODO */ },
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

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { /* TODO */ },
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
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { /* TODO */ },
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
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { /* TODO */ },
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
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clickable { /* TODO */ },
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AIOHelpTheme {
    }
}