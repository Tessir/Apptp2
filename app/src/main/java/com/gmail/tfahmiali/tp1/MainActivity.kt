package com.gmail.tfahmiali.tp1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image_view)
        button = findViewById(R.id.my_button)
        button.setOnClickListener(this)
        loadImage("https://cdn.pixabay.com/photo/2021/08/25/20/42/field-6574455__480.jpg")
    }

    override fun onClick(view: View?) {
        val links = listOf(
            "https://cdn.futura-sciences.com/buildsv6/images/wide1920/6/5/2/652a7adb1b_98148_01-intro-773.jpg",
            "https://img.ev.mu/images/attractions/879/960x640/10487.jpg",
            "https://cdn.pixabay.com/photo/2021/08/25/20/42/field-6574455__480.jpg",
            "https://cdn.futura-sciences.com/buildsv6/images/wide1920/6/5/2/652a7adb1b_98148_01-intro-773.jpg"
        )
//        val link =
//            "https://cdn.futura-sciences.com/buildsv6/images/wide1920/6/5/2/652a7adb1b_98148_01-intro-773.jpg"
        loadImage(links.random())
        Toast.makeText(this, "You click me", Toast.LENGTH_LONG).show()
    }

    private fun loadImage(url: String) {
//        ImageView imageView = (ImageView) findViewById(R.id.my_image_view);
        Glide.with(this)
            .load(url)
            .fallback(R.drawable.ic_launcher_background)
            .override(300, 300)
            .into(imageView)
//        Picasso.get()
//            .load("https://cdn.pixabay.com/photo/2021/08/25/20/42/field-6574455__480.jpg")
//            .resize(500, 500)
//            .centerCrop()
//            .into(imageView)
    }
}
