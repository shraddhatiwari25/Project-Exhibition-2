package com.project.aiohelp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun Setup(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(60.dp, 80.dp, 10.dp, 0.dp)
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            Button(
                onClick = { navController.navigate(Screen.UserForm.route) },
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
            ) {
                Text(text = "Customer", fontSize = 20.sp)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            Button(
                onClick = { navController.navigate(Screen.WorkerForm.route) },
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
            ) {
                Text(text = "Worker", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun WorkerForm(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = remember { StoreWorkerEmail(context) }
    val dataStoreLogin = remember { LoginInfo(context) }
    val dbManipulation = remember { DBManipulation() }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phNo by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var dropDownWidth by remember { mutableStateOf(0) }
    val jobs = listOf("Carpenter", "Plumber", "Gardener", "House Cleaner", "Laundry")
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Column(
        Modifier
            .fillMaxSize()
            .scrollable(rememberScrollState(), Orientation.Vertical)
            .padding(10.dp, 50.dp), verticalArrangement = Arrangement.Top
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Fill your information", style = MaterialTheme.typography.displayMedium
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = {
                Text(
                    text = "Name"
                )
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ), singleLine = true, modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(value = age, onValueChange = { age = it }, label = {
                Text(
                    text = "Age"
                )
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ), singleLine = true, modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(value = phNo, onValueChange = { if (it.length <= 10) phNo = it }, label = {
                Text(
                    text = "Phone Number"
                )
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ), singleLine = true, modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(value = email, onValueChange = { email = it }, label = {
                Text(
                    text = "Email"
                )
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ), singleLine = true, modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(value = pass, onValueChange = { pass = it }, label = {
                Text(
                    text = "Password"
                )
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ), visualTransformation = PasswordVisualTransformation(),
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = { selectedItem = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { dropDownWidth = it.width },
                label = { Text("Work") },
                trailingIcon = {
                    Icon(icon, "Open menu", Modifier.clickable { expanded = !expanded })
                },
                readOnly = true,
            )
            DropdownMenu(modifier = Modifier
                .width(with(LocalDensity.current) { dropDownWidth.toDp() }),
                expanded = expanded, onDismissRequest = { expanded = false }) {
                jobs.forEach { work ->
                    DropdownMenuItem(text = { Text(text = work) }, onClick = {
                        selectedItem = work
                        expanded = false
                    })
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    if (name.isNotEmpty() and email.isNotEmpty() and age.isNotEmpty() and phNo.isNotEmpty() and pass.isNotEmpty() and selectedItem.isNotEmpty()) {
                        dbManipulation.addWorker(
                            name.trim(), age.trim(), phNo.trim(), email.trim(), pass, selectedItem.trim()
                        )
                        scope.launch {
                            dataStore.saveEmail(email)
                            dataStoreLogin.saveLoginInfo("worker")
                        }
                        navController.popBackStack("SetupScreen", true)
                        navController.navigate(Screen.WorkerMain.route)
                    }
                },
                contentPadding = PaddingValues(15.dp, 10.dp),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.padding(end = 25.dp, top = 60.dp)
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Submit",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = "Submit", fontSize = 17.sp, modifier = Modifier.padding(10.dp, 0.dp))
            }
        }
    }
}

@Composable
fun UserForm(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = remember { StoreUserEmail(context) }
    val dataStoreName = remember { StoreUserName(context) }
    val dataStoreLogin = remember { LoginInfo(context) }
    val dbManipulation = DBManipulation()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp, 50.dp), verticalArrangement = Arrangement.Top
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Fill your information", style = MaterialTheme.typography.displayMedium
            )
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = {
                name = it
                error = false
            }, isError = error, label = {
                Text(
                    text = "Name"
                )
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ), singleLine = true, modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(value = email, onValueChange = {
                email = it
                error = false
            }, isError = error, label = {
                Text(
                    text = "Email"
                )
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                capitalization = KeyboardCapitalization.None
            ), singleLine = true, modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(value = password, onValueChange = {
                password = it
                error = false
            }, isError = error, label = {
                Text(
                    text = "Password"
                )
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                capitalization = KeyboardCapitalization.None
            ), visualTransformation = PasswordVisualTransformation(),
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
                if (name.isNotEmpty() and email.isNotEmpty() and password.isNotEmpty()) {
                    dbManipulation.addCustomer(name.trim(), email.trim(), password.trim())
                    navController.popBackStack("SetupScreen", true)
                    scope.launch {
                        dataStore.saveEmail(email)
                        dataStoreName.saveName(name)
                        dataStoreLogin.saveLoginInfo("customer")
                    }
                    navController.navigate(Screen.UserMain.route)
                } else error = true

            },
            contentPadding = PaddingValues(15.dp, 10.dp),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.padding(25.dp, 80.dp)
        ) {
            Icon(
                Icons.Filled.Check,
                contentDescription = "Submit",
                modifier = Modifier.size(25.dp)
            )
            Text(text = "Submit", fontSize = 17.sp, modifier = Modifier.padding(10.dp, 0.dp))
        }
    }
}
