package com.gutani.componentspt1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class Components2Activity : AppCompatActivity() {
    private lateinit var txtFooter:TextView
    private val vehicles = mutableListOf(
        Vehicle("Cooper", 2020, 0, false, true, false, 23),
        Vehicle("Huracán", 2020, 1, true, false, false,14),
        Vehicle("Portofino", 2020, 2, false, false, true,23),
        Vehicle("Taycan", 2020, 3, false, false, true,10),
        Vehicle("M235", 2019, 4, true, false, false,2034),
        Vehicle("Tesla Model X", 2020, 5, false, true, true,940),
        Vehicle("Audi RS3", 2019, 6, false, false, true,3546),
        Vehicle("Cooper", 2020, 0, false, true, false, 23),
        Vehicle("Huracán", 2020, 1, true, false, false,14),
        Vehicle("Portofino", 2020, 2, false, false, true,23),
        Vehicle("Taycan", 2020, 3, false, false, true,10),
        Vehicle("M235", 2019, 4, true, false, false,2034),
        Vehicle("Tesla Model X", 2020, 5, false, true, true,940),
        Vehicle("Audi RS3", 2019, 6, false, false, true,3546),
        Vehicle("Cooper", 2020, 0, false, true, false, 23),
        Vehicle("Huracán", 2020, 1, true, false, false,14),
        Vehicle("Portofino", 2020, 2, false, false, true,23),
        Vehicle("Taycan", 2020, 3, false, false, true,10),
        Vehicle("M235", 2019, 4, true, false, false,2034),
        Vehicle("Tesla Model X", 2020, 5, false, true, true,940),
        Vehicle("Audi RS3", 2019, 5, false, false, true,3546)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = ListView(this)
        setContentView(listView)

        //setContentView(R.layout.empty_layout)
        //listView.emptyView = findViewById(android.R.id.empty)

        val adapter = VehicleAdapter(this, vehicles)
        listView.adapter = adapter
        initHeaderAndFooter(listView, adapter)
        listView.setOnItemClickListener { parent, _, position, _ ->
            val vehicle = parent.getItemAtPosition(position) as? Vehicle
            if(vehicle != null){
                val (model, year) = vehicles[position]
                Toast.makeText(this, "$model, $year", Toast.LENGTH_SHORT).show()

                //Apresentando uma lista vazia
//                vehicles.remove(vehicle)
//                adapter.notifyDataSetChanged()
//                txtFooter.text = resources.getQuantityString(R.plurals.footer_text, adapter.count, adapter.count)
            }
        }
    }

    private fun initHeaderAndFooter(listView: ListView, adapter: VehicleAdapter){
        val padding = 8
        val txtHeader = TextView(this)
        txtHeader.setBackgroundColor(Color.BLUE)
        txtHeader.setTextColor(Color.WHITE)
        txtHeader.setTextColor(R.string.header_text)
        txtHeader.setPadding(padding,padding,0,padding)
        listView.addHeaderView(txtHeader)

        txtFooter = TextView(this)
        txtFooter.text = resources.getQuantityString(R.plurals.footer_text, adapter.count, adapter.count)
        txtFooter.gravity = Gravity.END
        txtFooter.setPadding(0, padding, padding, padding)
        listView.addFooterView(txtFooter)
    }
}
