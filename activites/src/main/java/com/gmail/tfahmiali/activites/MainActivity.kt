package com.gmail.tfahmiali.activites

import android.content.Intent
import android.os.Bundle
// import android.widget.Button
// import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gmail.tfahmiali.activites.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var clickButton: Button
//    private lateinit var compteur: TextView
//    private lateinit var computeButton: Button
    private lateinit var binding: ActivityMainBinding
    private var nbClick = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
//        compteur = findViewById(R.id.nbr_de_fois)
//        clickButton = findViewById(R.id.btn_click_me)
//        computeButton = findViewById(R.id.btn_compute)
//        clickButton.setOnClickListener {
//            Toast.makeText(baseContext, "Tu m'as cliqué", Toast.LENGTH_LONG).show()
//        }
        binding.btnCompute.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
        binding.btnClickMe.setOnClickListener {
            nbClick++
            if (nbClick != 0) {
                val newText = "Vous avez cliquez $nbClick fois"
                binding.nbrDeFois.text = newText
            }
            if (nbClick == 5) {
//                clickButton.isEnabled = false
                binding.btnClickMe.isEnabled = false
            }
        }
    }
}
