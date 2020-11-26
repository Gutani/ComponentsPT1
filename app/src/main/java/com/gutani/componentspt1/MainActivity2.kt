package com.gutani.componentspt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initSpinner()
        initSeekbar()
        initSwitch()
        chkEnabled.isChecked = true
        spnNames.setSelection(2)
        rbOptions.check(R.id.rbOption3)
        btnShowValues.setOnClickListener {
            showValues()
        }
    }

    private fun initSpinner() {
        val names = arrayOf("Gustavo", "Bruno", "Miguel", "Carol")
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, names
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnNames.adapter = adapter
    }

    private fun initSeekbar() {
        skbValue.setOnSeekBarChangeListener(
            object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    txtView.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            }
        )
    }

    private fun initSwitch() {
    swtEnabled.setOnCheckedChangeListener { buttonView, isChecked ->
        chkEnabled.isEnabled = buttonView.isChecked
        tgbEnabled.isEnabled = isChecked
    }
    }

    private fun showValues() {
        val idSelectedRatio = rbOptions.checkedRadioButtonId
        val radio = findViewById<RadioButton>(idSelectedRatio)
        val enabledText = getString(if(chkEnabled.isChecked) R.string.text_enabled else R.string.text_disabled)
        val message = getString(R.string.msg_result, enabledText, skbValue.progress, spnNames.selectedItem, radio.text.toString())
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}