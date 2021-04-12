package com.example.quizz2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class GallerieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallerie)
        openGalleryForImage()
    }

    //utilisation par defaut de l appli de gestion de la galerie
    private fun openGalleryForImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            var imageView : ImageView = findViewById(R.id.imageView2)
            imageView?.setImageURI(data?.data)
            val intent = Intent(this@GallerieActivity, ActivityPhotoPartageFoot::class.java)
            intent.putExtra(Intent.EXTRA_TEXT, data?.data)
            startActivity(intent)
        }
    }
}