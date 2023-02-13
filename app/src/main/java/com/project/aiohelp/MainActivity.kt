package com.project.aiohelp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.project.aiohelp.ui.theme.AIOHelpTheme
import com.smarttoolfactory.ratingbar.RatingBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = remember { StoreUserEmail(context) }
    val dataStoreWorker = remember { StoreWorkerEmail(context) }
    val dataStoreJobType = StoreJobType(context)
    val dataStoreLoginInfo = remember { LoginInfo(context) }
    val userEmail = dataStore.getEmail.collectAsState(initial = "")
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, topBar = {
        TopAppBar(title = { Text(text = "AIO App") }, navigationIcon = {
            IconButton(onClick = { navController.navigate(Screen.UserMain.route) }) {
                Icon(Icons.Filled.Home, contentDescription = "Home Button")
            }
        },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            actions = {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate(Screen.OrdersPage.route + "/${userEmail.value}") }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Orders Page")
                    }
                    IconButton(onClick = {
                        scope.launch {
                            dataStore.saveEmail("")
                            dataStoreWorker.saveEmail("")
                            dataStoreJobType.saveJobType("")
                            dataStoreLoginInfo.saveLoginInfo("false")
                            snackbarHostState.showSnackbar("Logged Out")
                        }
                        navController.navigate(Screen.SetupScreen.route)
                    }) {
                        Icon(painter = painterResource(id = R.drawable.logout), contentDescription = "Logout", modifier = Modifier.size(30.dp))
                    }
                }
            }
        )
    }, content = {
        Column(Modifier.padding(it)) {
            Column(
                verticalArrangement = Arrangement.Top, modifier = Modifier
                    .padding(20.dp, 30.dp)
                    .scrollable(rememberScrollState(), Orientation.Vertical)
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
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
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
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
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
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
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
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
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
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
    )
}


@Composable
fun WorkerList(navController: NavController, job: String) {
    val context = LocalContext.current
    val dataStore = remember { StoreJobType(context) }
    val dbManipulation = remember { DBManipulation() }
    var loading = dbManipulation.loading.value
    val workerList = remember { dbManipulation.getWorker(job) }
    LaunchedEffect(key1 = true) {
        dataStore.saveJobType(job)
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
                    state = rememberLazyListState(),
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
                                .clickable {
                                    navController.navigate(
                                        Screen.WorkerInfo.withArgs(
                                            workerList[index]?.name.toString(),
                                            workerList[index]?.age.toString(),
                                            workerList[index]?.email.toString(),
                                            workerList[index]?.phNo.toString(),
                                            workerList[index]?.ratings.toString()
                                        )
                                    )
                                },
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
                                        rating = workerList[index]?.ratings!!,
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


@Composable
fun WorkerInfo(
    navController: NavController,
    name: String?,
    age: String?,
    email: String?,
    phoneNo: String?,
    rating: String?
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Worker Info") },
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
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(8.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = name!!,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = rating!!,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Image(
                        painter = painterResource(id = R.drawable.star_full),
                        contentDescription = "Star",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(start = 10.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth(), Arrangement.SpaceEvenly
                ) {
                    Card(
                        modifier = Modifier
                            .padding(start = 0.dp, top = 80.dp)
                            .size(120.dp),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        SelectionContainer {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                Text(
                                    text = "Age",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(top = 10.dp)
                                )
                                Text(
                                    text = age!!,
                                    style = MaterialTheme.typography.headlineLarge,
                                    modifier = Modifier.weight(4f)
                                )
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(start = 0.dp, top = 80.dp)
                            .size(120.dp),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        SelectionContainer {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                Text(
                                    text = "Fee",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(top = 10.dp)
                                )
                                Text(
                                    text = "₹500",
                                    style = MaterialTheme.typography.headlineLarge,
                                    modifier = Modifier.weight(4f)
                                )
                            }
                        }
                    }
                }
            }
            Button(
                onClick = { navController.navigate(Screen.BookingPage.route + "/$name" + "/$email" + "/$phoneNo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(70.dp),
                shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 40.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            ) {
                Text(
                    text = "Hire",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    })
}


@Composable
fun BookingPage(navController: NavController, workerName: String?, workerEmail: String?, phoneNo: String?) {
    val context = LocalContext.current
    val dataStoreEmail = remember { StoreUserEmail(context) }
    val dataStoreUserName = remember { StoreUserName(context) }
    val dataStoreJobType = remember { StoreJobType(context) }
    val userEmail = dataStoreEmail.getEmail.collectAsState(initial = "")
    val userName = dataStoreUserName.getName.collectAsState(initial = "")
    val jobType = dataStoreJobType.getJobType.collectAsState(initial = "")
    val dbManipulation = remember { DBManipulation() }
    var address by remember { mutableStateOf("") }
    var cardNo by remember { mutableStateOf("") }
    var expDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var upi by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var dialogOpen by remember { mutableStateOf(false) }
    val radioOptions = listOf("Cash", "Debit Card", "Credit Card", "UPI")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Fill your details") },
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
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it), verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 40.dp)
            ) {
                Text(
                    text = "Customer Details",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                OutlinedTextField(value = address, onValueChange = { address = it }, label = {
                    Text(
                        text = "Address"
                    )
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words
                ), singleLine = true, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(80.dp))
                Text(
                    text = "Payment Method",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                radioOptions.forEach { text ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text) }
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                modifier = Modifier.padding(8.dp),
                                onClick = { onOptionSelected(text) }
                            )
                            Text(
                                text = text,
                                modifier = Modifier.padding(start = 10.dp),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
                if (selectedOption != "Cash") {
                    if (selectedOption == "Debit Card" || selectedOption == "Credit Card") {
                        Spacer(modifier = Modifier.height(80.dp))
                        Text(
                            text = "Enter $selectedOption Information",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        OutlinedTextField(
                            value = cardNo,
                            onValueChange = { if (it.length <= 16) cardNo = it },
                            label = {
                                Text(
                                    text = "$selectedOption Number"
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = expDate,
                                onValueChange = { expDate = it },
                                label = {
                                    Text(
                                        text = "Expiration Date"
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text
                                ),
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 10.dp)
                                    .weight(4f)
                            )
                            OutlinedTextField(
                                value = cvv,
                                onValueChange = { if (it.length <= 3) cvv = it },
                                label = {
                                    Text(
                                        text = "CVV"
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.NumberPassword
                                ),
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = {
                                Text(
                                    text = "Name on Card"
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else if (selectedOption == "UPI") {
                        Spacer(modifier = Modifier.height(80.dp))
                        Text(
                            text = "Enter UPI Information",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        OutlinedTextField(
                            value = upi,
                            onValueChange = { upi = it },
                            label = {
                                Text(
                                    text = "Enter UPI ID"
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            ),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp), horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            dbManipulation.placeOrder(
                                userName.value!!,
                                userEmail.value!!,
                                workerName!!,
                                workerEmail!!,
                                phoneNo!!,
                                address,
                                selectedOption,
                                jobType.value!!
                            )
                            dialogOpen = true
                        },
                        contentPadding = PaddingValues(15.dp, 10.dp),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "Submit",
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = "Hire",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                }
                if (dialogOpen) {
                    Dialog(onDismissRequest = { }) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            shadowElevation = 6.dp,
                            color = MaterialTheme.colorScheme.surface
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Hired Successfully!",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Divider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = "$workerName will arrive at your place, shortly.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                Button(onClick = {
                                    navController.popBackStack(
                                        Screen.UserMain.route,
                                        false
                                    )
                                }) {
                                    Text(
                                        text = "Go back",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    })
}


@Composable
fun OrdersPage(navController: NavController, userEmail: String?) {
    val dbManipulation = remember { DBManipulation() }
    var loading = dbManipulation.loading.value
    val orderList = remember { dbManipulation.getOrder(userEmail!!) }
    var dialogOpen by remember { mutableStateOf(false) }
    var newRating = 0f
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        delay(1000)
        loading = false
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Workers Hired") },
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
    }, snackbarHost = { SnackbarHost(snackbarHostState) },
        content = {
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
            if (!orderList.isEmpty()) {
                loading = false
                Column(Modifier.padding(it)) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(start = 15.dp, end = 15.dp, top = 25.dp, bottom = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "In Progress", style = MaterialTheme.typography.titleLarge)
                        Divider(
                            thickness = 2.dp,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                    }
                    LazyColumn(
                        state = rememberLazyListState(),
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        itemsIndexed(orderList) { index, _ ->
                            if (!orderList[index]?.completed!!) {
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp, horizontal = 15.dp),
                                    elevation = CardDefaults.cardElevation(2.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Text(
                                                text = orderList[index]?.workerName!!,
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(bottom = 10.dp)
                                            )
                                            SelectionContainer {
                                                Text(
                                                    text = orderList[index]?.phoneNo!!,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                            Text(
                                                text = orderList[index]?.jobType!!,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                        Text(
                                            text = orderList[index]?.date!!,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, top = 25.dp, bottom = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Completed", style = MaterialTheme.typography.titleLarge)
                        Divider(
                            thickness = 2.dp,
                            modifier = Modifier.padding(start = 10.dp, top = 2.dp)
                        )
                    }
                    LazyColumn(
                        state = rememberLazyListState(),
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        itemsIndexed(orderList) { index, _ ->
                            if (orderList[index]?.completed!!) {
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp, horizontal = 15.dp),
                                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.inverseOnSurface)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Text(
                                                text = orderList[index]?.workerName!!,
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(bottom = 10.dp)
                                            )
                                            Text(
                                                text = orderList[index]?.jobType!!,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                        Text(
                                            text = orderList[index]?.date!!,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Button(
                                            onClick = { dialogOpen = true },
                                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                                            modifier = Modifier.padding(end = 7.dp)
                                        ) {
                                            Text(
                                                text = "Rate",
                                                style = MaterialTheme.typography.labelLarge,
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        }
                                    }
                                }
                                if (dialogOpen) {
                                    Dialog(onDismissRequest = { dialogOpen = false }) {
                                        Surface(
                                            shape = RoundedCornerShape(16.dp),
                                            shadowElevation = 6.dp,
                                            color = MaterialTheme.colorScheme.surface
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .padding(20.dp)
                                                    .fillMaxWidth(),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = "Give rating",
                                                    style = MaterialTheme.typography.headlineSmall,
                                                    color = MaterialTheme.colorScheme.onSurface,
                                                    textAlign = TextAlign.Center
                                                )
                                                Spacer(modifier = Modifier.height(10.dp))
                                                Divider(
                                                    thickness = 1.dp,
                                                    color = MaterialTheme.colorScheme.outlineVariant
                                                )
                                                Spacer(modifier = Modifier.height(20.dp))
                                                RatingBar(
                                                    rating = 0f,
                                                    imageVectorEmpty = ImageVector.vectorResource(id = R.drawable.star),
                                                    imageVectorFFilled = ImageVector.vectorResource(
                                                        id = R.drawable.star_full
                                                    ),
                                                    itemSize = 50.dp,
                                                    animationEnabled = false,
                                                    gestureEnabled = true,
                                                    onRatingChange = { newRating = it }
                                                )
                                                Spacer(modifier = Modifier.height(30.dp))
                                                Button(onClick = {
                                                    scope.launch {
                                                        dbManipulation.rating(
                                                            orderList[index]?.workerEmail!!,
                                                            newRating.toDouble()
                                                        )
                                                        snackbarHostState.showSnackbar("Thank you for you rating!")
                                                    }
                                                    dialogOpen = false
                                                }) {
                                                    Text(
                                                        text = "Rate",
                                                        style = MaterialTheme.typography.labelLarge,
                                                        color = MaterialTheme.colorScheme.onPrimary,
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Text(
                        text = "No Orders Placed Yet",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkerMain(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = remember { StoreUserEmail(context) }
    val dataStoreJobType = StoreJobType(context)
    val dataStoreLoginInfo = remember { LoginInfo(context) }
    val dataStoreEmail = remember { StoreWorkerEmail(context) }
    val workerEmail = dataStoreEmail.getEmail.collectAsState(initial = "")
    val dbManipulation = remember { DBManipulation() }
    val loading = dbManipulation.loading.value
    val success = dbManipulation.success.value
    var orderList = dbManipulation.getOrderWorker(workerEmail.value!!)
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    fun refresh() = refreshScope.launch {
        refreshing = true
        orderList = dbManipulation.getOrderWorker(workerEmail.value!!)
        delay(1000)
        refreshing = false
    }
    val state = rememberPullRefreshState(refreshing, ::refresh)
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
        TopAppBar(
            title = { Text(text = "Available Jobs") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            actions = {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        scope.launch {
                            dataStore.saveEmail("")
                            dataStoreEmail.saveEmail("")
                            dataStoreJobType.saveJobType("")
                            dataStoreLoginInfo.saveLoginInfo("false")
                            snackbarHostState.showSnackbar("Logged Out")
                        }
                        navController.navigate(Screen.SetupScreen.route)
                    }) {
                        Icon(painter = painterResource(id = R.drawable.logout), contentDescription = "Logout", modifier = Modifier.size(30.dp))
                    }
                }
            }
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
            if (success) {
                Box(
                    modifier = Modifier
                        .padding(it)
                        .pullRefresh(state)
                ) {
                    Column {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp, top = 25.dp, bottom = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "In Progress", style = MaterialTheme.typography.titleLarge)
                            Divider(
                                thickness = 1.dp,
                                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        LazyColumn(
                            state = rememberLazyListState(),
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            itemsIndexed(orderList) { index, _ ->
                                if (!orderList[index]?.completed!!) {
                                    Card(
                                        shape = RoundedCornerShape(12.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 5.dp, horizontal = 15.dp),
                                        elevation = CardDefaults.cardElevation(2.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(20.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.Start
                                            ) {
                                                Text(
                                                    text = orderList[index]?.userName!!,
                                                    style = MaterialTheme.typography.titleMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(bottom = 10.dp)
                                                )
                                                Text(
                                                    text = orderList[index]?.address!!,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                            Text(
                                                text = orderList[index]?.date!!,
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                        Divider(
                                            thickness = 1.dp,
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                end = 20.dp,
                                                bottom = 10.dp
                                            ),
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            OutlinedButton(
                                                onClick = { /*TODO*/ },
                                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                                                modifier = Modifier.padding(end = 15.dp)
                                            ) {
                                                Text(
                                                    text = "Cancel",
                                                    style = MaterialTheme.typography.labelLarge,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                            }
                                            Button(
                                                onClick = {
                                                    dbManipulation.completeOrder(
                                                        workerEmail.value!!,
                                                        orderList[index]?.userEmail!!,
                                                        orderList[index]?.date!!
                                                    )
                                                    refresh()
                                                },
                                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                                                modifier = Modifier.padding(end = 7.dp)
                                            ) {
                                                Text(
                                                    text = "Mark as Completed",
                                                    style = MaterialTheme.typography.labelLarge,
                                                    color = MaterialTheme.colorScheme.onPrimary
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp, top = 25.dp, bottom = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Completed", style = MaterialTheme.typography.titleLarge)
                            Divider(
                                thickness = 1.dp,
                                modifier = Modifier.padding(start = 10.dp, top = 2.dp),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        LazyColumn(
                            state = rememberLazyListState(),
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            itemsIndexed(orderList) { index, _ ->
                                if (orderList[index]?.completed!!) {
                                    Card(
                                        shape = RoundedCornerShape(12.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 5.dp, horizontal = 15.dp),
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.inverseOnSurface)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(20.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.Start
                                            ) {
                                                Text(
                                                    text = orderList[index]?.userName!!,
                                                    style = MaterialTheme.typography.titleMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(bottom = 10.dp)
                                                )
                                                Text(
                                                    text = orderList[index]?.address!!,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                            Text(
                                                text = orderList[index]?.date!!,
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(it)
                        .pullRefresh(state)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No Jobs Available\nTry Again Later",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                }
            }
        }
    })
}