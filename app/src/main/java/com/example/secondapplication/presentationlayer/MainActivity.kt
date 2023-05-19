package com.example.secondapplication.presentationlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secondapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SimpleListFragment.newInstance(getApplicationContext()))
            .commit()
    }
}