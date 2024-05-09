package com.example.diploma

import android.app.Application
import com.example.diploma.viewmodels.Graph
import com.example.diploma.viewmodels.Utils

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)

    }
}