package com.gutani.componentspt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_auto_complete_text_view3.*

class AutoCompleteTextViewActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_text_view3)
        val cities = listOf<String>(
            "Goiânia", "Tokyo", "San Francisco", "New York", "London", "Milan", "Rome", "Paris", "Seattle", "São Paulo"
        )
        //val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)
        val adapter = CitySearchAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)
        actView.setAdapter(adapter)
    }
}