package com.gutani.componentspt1

import android.content.Context
import android.content.res.TypedArray
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.item_vehicle.view.*

class VehicleAdapter(
    private val ctx:Context,
    private val vehicles:List<Vehicle>) :BaseAdapter(){
    private val logos:TypedArray by lazy {
        ctx.resources.obtainTypedArray(R.array.logos)
    }

    override fun getCount(): Int = vehicles.size

    override fun getItem(position: Int) = vehicles[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vehicle = vehicles[position]
        val holder:ViewHolder
        val row: View
        if(convertView == null){
            row = LayoutInflater.from(ctx).inflate(R.layout.item_vehicle, parent, false)
            holder = ViewHolder(row)
            row.tag = holder
            Log.d("Teste1", "Nova View => position: $position")
        }else{
            row = convertView
            holder = convertView.tag as ViewHolder
            Log.d("Teste2", "View existente => position: $position")
        }

        row.imgLogo.setImageDrawable(logos.getDrawable(vehicle.manufacter))
        row.txtModel.text = vehicle.model
        row.txtYear.text = vehicle.year.toString()
        row.txtFuel.text = ctx.getString(getFuel(vehicle))
        row.txtKm.text = vehicle.km.toString()
        return row

    }
    companion object{
        data class ViewHolder(val view:View){
            val imgLogo:ImageView = view.imgLogo
            val txtModel:TextView = view.txtModel
            val txtYear:TextView = view.txtYear
            val txtFuel:TextView = view.txtFuel
            val txtKm:TextView = view.txtKm
        }
    }


    @StringRes
    private fun getFuel(vehicle: Vehicle):Int =
        if (vehicle.gasoline && vehicle.ethanol) R.string.fuel_flex
        else if(vehicle.gasoline) R.string.fuel_gasoline
        else if(vehicle.electric) R.string.fuel_electric
        else if(vehicle.ethanol) R.string.fuel_ethanol
        else R.string.msg_error


}