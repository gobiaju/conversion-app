package ca.gobiaju.george_conversion_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private val displayConversion = findViewById<TextView>(R.id.text_view)
    private val spinner: Spinner = findViewById(R.id.spinner)
    private val convertFrom = findViewById<EditText>(R.id.edit_from)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val convertBtn = findViewById<Button>(R.id.convert_btn)
        val conversions = resources.getStringArray(R.array.conversions)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, conversions)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedConversion = conversions[position]
                convert()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        //convertFrom.addTextChangedListener(hold())
        convertBtn.setOnClickListener { convert() }
    }

    /*private fun hold(): TextWatcher? {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               convert()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

     */

    fun convert(){
        val input: String = convertFrom.text.toString()
        val conversionOption: String = spinner.selectedItem.toString()

        val output: String = when (conversionOption) {
            "Inches to Centimeters" -> convertInToCm(input.toDouble()).toString()
            "Centimeters to Inches" -> convertCmToIn(input.toDouble()).toString()
            "Feet to Inches" -> convertFtToIn(input.toDouble()).toString()
            "Inches to Feet" -> convertInToFt(input.toDouble()).toString()
            "Feet to Meters" -> convertFtToMt(input.toDouble()).toString()
            "Meters to Feet" -> convertMtToFt(input.toDouble()).toString()
            else -> "Invalid conversion type"
        }

        displayConversion.text = output
    }
    private fun convertCmToIn(cm: Double): Double {
        return cm / 2.54
    }

    private fun convertInToCm(inch: Double): Double {
        return inch * 2.54
    }

    private fun convertFtToIn(feet: Double): Double {
        return feet * 0.621371
    }

    private fun convertInToFt(inch: Double): Double {
        return inch / 0.621371
    }

    private fun convertFtToMt(feet: Double): Double {
        return feet * 2.20462
    }

    private fun convertMtToFt(meters: Double): Double {
        return meters / 2.20462
    }


}