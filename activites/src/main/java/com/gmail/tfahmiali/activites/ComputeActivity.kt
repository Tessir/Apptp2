package com.gmail.tfahmiali.activites

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ComputeActivity : AppCompatActivity(), TextWatcher {
    private lateinit var nombre1: EditText
    private lateinit var nombre2: EditText
    private lateinit var resultat: TextView
    private lateinit var calculbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)
        nombre1 = findViewById(R.id.field_1)
        nombre2 = findViewById(R.id.field_2)
        resultat = findViewById(R.id.resultat)
        calculbutton = findViewById(R.id.btn_calculer)
        calculbutton.setOnClickListener {
            var resultatcalcul = 0.0
            resultatcalcul = nombre1.text.toString().toDouble() + nombre2.text.toString().toDouble()
            resultat.text = resultatcalcul.toString()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if (nombre1.text.isEmpty() && nombre2.text.isEmpty()) {
            calculbutton.isEnabled = false
        }
    }
}
