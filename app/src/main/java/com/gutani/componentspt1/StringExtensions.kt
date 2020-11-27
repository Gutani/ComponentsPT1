package com.gutani.componentspt1

import java.util.*
import java.util.regex.Pattern

fun String.removeAccents() :String{
    val replaces = arrayOf("a","e","i","o","u","c")

    val patterns = arrayOf(
        Pattern.compile("[âãáàä]", Pattern.CASE_INSENSITIVE),
        Pattern.compile("[êèéë]", Pattern.CASE_INSENSITIVE),
        Pattern.compile("[íìïî]", Pattern.CASE_INSENSITIVE),
        Pattern.compile("[óòôöõ]", Pattern.CASE_INSENSITIVE),
        Pattern.compile("[úùüû]", Pattern.CASE_INSENSITIVE),
        Pattern.compile("[ç]", Pattern.CASE_INSENSITIVE)
    )
    var result = this
    for ((index, value) in patterns.withIndex()){
        val matcher = value.matcher(result)
        result = matcher.replaceAll(replaces[index])
    }
    return result.toLowerCase(Locale.ROOT)

}