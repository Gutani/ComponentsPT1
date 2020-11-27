package com.gutani.componentspt1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        autoCompleteButton.setOnClickListener {
            val intent = Intent(this, AutoCompleteTextViewActivity3::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        buttonComponents2.setOnClickListener {
            val intent = Intent(this, Components2Activity::class.java)
            startActivity(intent)
        }
        edtCpf.addTextChangedListener(object:TextWatcher{
            var isUpdating = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(isUpdating){
                    isUpdating = false
                    return
                }
                val hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') >-1
                var str = s.toString().filterNot { it == '.' || it == '-' }
                if(count>before){
                    if(str.length>9){
                        str = "${str.substring(0,3)}.${str.substring(3,6)}.${str.substring(6,9)}-${str.substring(9)}"
                    }else if(str.length>5){
                        str = "${str.substring(0,3)}.${str.substring(3,6)}.${str.substring(6)}"
                    }else if(str.length>3){
                        str = "${str.substring(0,3)}.${str.substring(3)}"
                    }
                    isUpdating = true
                    edtCpf.setText(str)
                    edtCpf.setSelection(edtCpf.text?.length ?:0)
                }else{
                    isUpdating = true
                    edtCpf.setText(str)
                    edtCpf.setSelection(Math.max(0,Math.min(if(hasMask) start - before else start, str.length)))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        edtCep.addTextChangedListener(object:TextWatcher{
            var isUpdating = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(isUpdating){
                    //Evita a chamada infinita do método
                    isUpdating = false
                    return
                }
                //Posicionamento do cursor precisa saber se havia ou não mascara adicionada
                val hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') > -1
                //Remove o ponto e o hífen
                var str = s.toString().filterNot { it == '.' || it == '-' }
                if(count>before){
                    if (str.length>5){
                        //Coloca o . e o - se houver mais de 5 caracteres de comprimento sem mascara
                        str = "${str.substring(0,2)}.${str.substring(2,5)}-${str.substring(5)}"
                    }else if(str.length>2){
                        //Se houver mais de dois caracteres coloca só o ponto
                        str = "${str.substring(0,2)}.${str.substring(2)}"
                    }
                    isUpdating = true
                    edtCep.setText(str)
                    edtCep.setSelection(edtCep.text?.length ?:0)
                }else{
                    isUpdating = true
                    edtCep.setText(str)
                    edtCep.setSelection(Math.max(0,Math.min(if(hasMask) start - before else start, str.length)))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        edtPassword.setOnEditorActionListener { v, actionId, event ->
            if(v == edtPassword && EditorInfo.IME_ACTION_DONE == actionId){
                registerUser()
            }
            false
        }
    }

    private fun registerUser(){
        val name = edtName.text.toString()
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        var isValid = true

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilEmail.error = getString(R.string.msg_error_email)
            isValid = false
        }else{
            tilEmail.error = null
        }
        if(password != "123"){
            tilPassword.error = getString(R.string.msg_error_password)
            isValid = false
        }else{
            tilPassword.error = null
        }
        if(isValid){
            Toast.makeText(this, getString(R.string.msg_success, name, email), Toast.LENGTH_SHORT).show()
        }
    }

}