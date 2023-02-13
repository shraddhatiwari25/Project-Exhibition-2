package com.project.aiohelp.data

data class OrderModel(
    var userName: String? = null,
    var userEmail: String? = null,
    var workerName: String? = null,
    var workerEmail: String? = null,
    var phoneNo: String? = null,
    var completed: Boolean = false,
    var address: String? = null,
    var paymentMethod: String? = null,
    var jobType: String? = null,
    var date: String? = null
)
