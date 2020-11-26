package com.gutani.componentspt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class Components2Activity : AppCompatActivity() {
    private val vehicles = mutableListOf(
        Vehicle("Cooper", 2020, 0, false, true, false, 23),
        Vehicle("Huracán", 2020, 1, true, false, false,14),
        Vehicle("Portofino", 2020, 2, false, false, true,23),
        Vehicle("Taycan", 2020, 3, false, false, true,10),
        Vehicle("M235", 2019, 4, true, false, false,2034),
        Vehicle("Tesla Model X", 2020, 5, false, true, true,940),
        Vehicle("Audi RS3", 2019, 6, false, false, true,3546),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = ListView(this)
        setContentView(listView)
        val adapter = VehicleAdapter(this, vehicles)
        listView.adapter = adapter

    }
}
