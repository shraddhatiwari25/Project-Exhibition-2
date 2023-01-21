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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.aiohelp.ui.theme.AIOHelpTheme
import com.smarttoolfactory.ratingbar.RatingBar
import kotlinx.coroutines.delay

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
        IconButton(onClick = { navController.navigate(Screen.UserMain.route) }) {
            Icon(Icons.Filled.Home, contentDescription = "Home Button")
        }
    },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
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
                .clickable { navController.navigate(Screen.WorkerList.withArgs("Carpenter")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image1),
                    contentDescription = stringResource(
                        id = R.string.Image1
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.requiredWidth(110.dp)
                )
                Text(
                    text = stringResource(id = R.string.Image1),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
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
                .clickable { navController.navigate(Screen.WorkerList.withArgs("Plumber")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image2),
                    contentDescription = stringResource(
                        id = R.string.Image2
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.requiredWidth(110.dp)
                )
                Text(
                    text = stringResource(id = R.string.Image2),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
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
                .clickable { navController.navigate(Screen.WorkerList.withArgs("Gardener")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image3),
                    contentDescription = stringResource(
                        id = R.string.Image3
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.requiredWidth(110.dp)
                )
                Text(
                    text = stringResource(id = R.string.Image3),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
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
                .clickable { navController.navigate(Screen.WorkerList.withArgs("House Cleaner")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image4),
                    contentDescription = stringResource(
                        id = R.string.Image4
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.requiredWidth(110.dp)
                )
                Text(
                    text = stringResource(id = R.string.Image4),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
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
                .clickable { navController.navigate(Screen.WorkerList.withArgs("Laundry")) },
            elevation = CardDefaults.cardElevation(10.dp, 5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.image5),
                    contentDescription = stringResource(
                        id = R.string.Image5
                    ),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.requiredWidth(110.dp)
                )
                Text(
                    text = stringResource(id = R.string.Image5),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun WorkerList(navController: NavController, job: String) {
    var loading by remember { mutableStateOf(true) }
    val dbManipulation = remember { DBManipulation() }
    val workerList = remember { dbManipulation.getWorker(job) }
    LaunchedEffect(key1 = true) {
        delay(1000)
        loading = false
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Available Workers") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }, content = {
        if (loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (!workerList.isEmpty()) {
                loading = false
                LazyColumn(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth()
                ) {
                    itemsIndexed(workerList) { index, _ ->
                        Card(
                            shape = RectangleShape,
                            modifier = Modifier
                                .height(72.dp)
                                .clickable { /* TODO */ },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Row(
                                modifier = Modifier.fillParentMaxSize(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = "Profile Photo",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                                )
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    workerList[index]?.name?.let {
                                        Text(
                                            text = "Name: $it",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    workerList[index]?.age?.let {
                                        Text(
                                            text = "Age: $it",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    RatingBar(
                                        rating = 4f,
                                        imageVectorEmpty = ImageVector.vectorResource(id = R.drawable.star),
                                        imageVectorFFilled = ImageVector.vectorResource(id = R.drawable.star_full),
                                        itemSize = 20.dp,
                                        animationEnabled = false,
                                        gestureEnabled = false
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                loading = false
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No Workers Available\nTry Again Later",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    })
}

