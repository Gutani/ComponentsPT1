package com.gutani.componentspt1

data class Vehicle(
    var model:String,
    var year: Int,
    var manufacter:Int, //0=Mini, 1=Lamborghini, 2=Ferrari, 3=Tesla, 4=Porsche, 5=BMW, 6=Audi
    var gasoline:Boolean,
    var ethanol:Boolean,
    var electric:Boolean,
    var km:Int
) {
}