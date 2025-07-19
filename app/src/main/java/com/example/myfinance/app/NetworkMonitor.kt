package com.example.myfinance.app

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.myfinance.data.workmanager.SyncOperationsWorker
import com.example.myfinance.data.workmanager.SyncDbWorker
import javax.inject.Inject

/**
 * При появлении интернета синхронизирует данные с базы дыннх и с сервера
 */

class NetworkMonitor @Inject constructor(
    private val context: Context
) {

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            startSync()
        }
    }

    fun startMonitoring() {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.registerDefaultNetworkCallback(networkCallback)
    }

    private fun startSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncOperationsWorkRequest = OneTimeWorkRequest.Builder(SyncOperationsWorker::class)
            .setConstraints(constraints)
            .build()

        val syncDbWorkRequest = OneTimeWorkRequest.Builder(SyncDbWorker::class)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context)
            .beginUniqueWork(
                "sync_chain",
                ExistingWorkPolicy.KEEP,
                syncOperationsWorkRequest)
            .then(syncDbWorkRequest)
            .enqueue()
    }
}