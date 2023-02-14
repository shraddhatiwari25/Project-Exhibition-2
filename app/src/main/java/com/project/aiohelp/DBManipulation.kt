package com.project.aiohelp

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.aiohelp.data.OrderModel
import com.project.aiohelp.data.WorkerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*


class DBManipulation {
    private val db = Firebase.firestore
    var loading = mutableStateOf(true)
    var success = mutableStateOf(false)

    fun addCustomer(name: String, email: String, pass: String) {
        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "pass" to pass,
        )
        db.collection("Users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("Stuff", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Stuff", "Error adding document", e)
            }
    }

    fun addWorker(
        name: String,
        age: String,
        phNo: String,
        email: String,
        pass: String,
        job: String
    ) {
        val worker = WorkerModel(name, age, phNo, email, pass, job, false, 0f, 0)
        db.collection("Workers").document(email).set(worker)
    }

    fun getWorker(job: String): SnapshotStateList<WorkerModel?> {
        val workerList = mutableStateListOf<WorkerModel?>()

        db.collection("Workers").whereEqualTo("job", job).whereEqualTo("busy", false).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: WorkerModel? = d.toObject(WorkerModel::class.java)
                        workerList.add(c)
                    }
                }
                loading.value = false
            }
            .addOnFailureListener {
                // TODO
            }
        return workerList
    }


    fun placeOrder(
        userName: String,
        userEmail: String,
        workerName: String,
        workerEmail: String,
        phoneNo: String,
        address: String,
        paymentMethod: String,
        jobType: String
    ) {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val monthName = calendar.getDisplayName(month, Calendar.SHORT, Locale.US)
        val date = "$day $monthName"
        val order = OrderModel(userName, userEmail, workerName, workerEmail, phoneNo, false, address, paymentMethod, jobType, date)

        db.collection("Workers").document(workerEmail).update("busy", true)
        db.collection("Orders").document("$userEmail$workerEmail$date").set(order)
    }

    fun getOrder(userEmail: String): SnapshotStateList<OrderModel?> {
        val orderList = mutableStateListOf<OrderModel?>()
        db.collection("Orders").whereEqualTo("userEmail", userEmail).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: OrderModel? = d.toObject(OrderModel::class.java)
                        orderList.add(c)
                    }
                }
                loading.value = false
            }.addOnFailureListener { loading.value = false }
        return orderList
    }

    fun getOrderWorker(workerEmail: String): SnapshotStateList<OrderModel?> {
        val orderList = mutableStateListOf<OrderModel?>()
        db.collection("Orders").whereEqualTo("workerEmail", workerEmail).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: OrderModel? = d.toObject(OrderModel::class.java)
                        orderList.add(c)
                    }
                    success.value = true
                }
                loading.value = false
            }.addOnFailureListener {
                success.value = false
                loading.value = false
            }
        return orderList
    }

    fun completeOrder(workerEmail: String, userEmail: String, date: String) {
        db.collection("Orders").document("$userEmail$workerEmail$date").update("completed", true)
        db.collection("Workers").document(workerEmail).update("busy", false)
    }

    fun rating(workerEmail: String, newRating: Double) {
        var ratings = 0.0
        var noOfRatings: Long = 0
        db.collection("Workers").document(workerEmail).get()
            .addOnSuccessListener {
                ratings = (it.get("ratings") as Double)
                noOfRatings = it.get("noOfRatings") as Long
            }
        ratings = ((ratings * noOfRatings) + newRating)/(noOfRatings + 1)
        noOfRatings += 1

        db.collection("Workers").document(workerEmail).update("ratings", ratings)
        db.collection("Workers").document(workerEmail).update("noOfRatings", noOfRatings)
    }
}

class StoreUserEmail(private val context: Context) {
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("UserEmail")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    val getEmail: Flow<String?> =
        context.datastore.data.map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    suspend fun saveEmail(email: String) {
        context.datastore.edit { preference ->
            preference[USER_EMAIL_KEY] = email
        }
    }
}

class StoreUserName(private val context: Context) {
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("UserName")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
    }

    val getName: Flow<String?> =
        context.datastore.data.map { preferences ->
            preferences[USER_NAME_KEY] ?: ""
        }

    suspend fun saveName(name: String) {
        context.datastore.edit { preference ->
            preference[USER_NAME_KEY] = name
        }
    }
}

class StoreWorkerEmail(private val context: Context) {
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("WorkerEmail")
        val WORKER_EMAIL_KEY = stringPreferencesKey("worker_email")
    }

    val getEmail: Flow<String?> =
        context.datastore.data.map { preferences ->
            preferences[WORKER_EMAIL_KEY] ?: ""
        }

    suspend fun saveEmail(email: String) {
        context.datastore.edit { preference ->
            preference[WORKER_EMAIL_KEY] = email
        }
    }
}


class StoreJobType(private val context: Context) {
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("JobType")
        val JOB_TYPE = stringPreferencesKey("job_type")
    }

    val getJobType: Flow<String?> =
        context.datastore.data.map { preferences ->
            preferences[JOB_TYPE] ?: ""
        }

    suspend fun saveJobType(jobType: String) {
        context.datastore.edit { preference ->
            preference[JOB_TYPE] = jobType
        }
    }
}

class LoginInfo(private val context: Context) {
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("LoginInfo")
        val LOGIN_INFO = stringPreferencesKey("login_info")
    }

    val getLoginInfo: Flow<String?> =
        context.datastore.data.map { preferences ->
            preferences[LOGIN_INFO] ?: "false"
        }

    suspend fun saveLoginInfo(login: String) {
        context.datastore.edit { preference ->
            preference[LOGIN_INFO] = login
        }
    }
}