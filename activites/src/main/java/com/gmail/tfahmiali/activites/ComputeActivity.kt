package com.gmail.tfahmiali.activites

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
// import android.widget.Button
// import android.widget.EditText
// import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
// import com.gmail.tfahmiali.activites.databinding.ActivityMainBinding//
import com.gmail.tfahmiali.activites.databinding.ComputeActivityBinding

class ComputeActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ComputeActivityBinding
//    private lateinit var nombre1: EditText
//    private lateinit var nombre2: EditText
//    private lateinit var resultat: TextView
//    private lateinit var calculbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.compute_activity)
        binding = ComputeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        nombre1 = findViewById(R.id.field_1)
//        nombre2 = findViewById(R.id.field_2)
//        resultat = findViewById(R.id.resultat)
//        calculbutton = findViewById(R.id.btn_calculer)
//        nombre1.addTextChangedListener(this)
//        nombre2.addTextChangedListener(this)
        binding.field1.addTextChangedListener(this)
        binding.field2.addTextChangedListener(this)
//        calculbutton.setOnClickListener {
//            val resultatcalcul =
//                nombre1.text.toString().toDouble() + nombre2.text.toString().toDouble()
//            resultat.text = resultatcalcul.toString()
//        }
        binding.btnCalculer.setOnClickListener {
            val resultatcalcul =
                binding.field1.text.toString().toDouble() + binding.field2.text.toString()
                    .toDouble()
            binding.resultat.text = resultatcalcul.toString()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
//        calculbutton.isEnabled = nombre1.text.isNotBlank() && nombre2.text.isNotBlank()
        binding.btnCalculer.isEnabled =
            binding.field1.text.isNotBlank() && binding.field2.text.isNotBlank()
    }
}
