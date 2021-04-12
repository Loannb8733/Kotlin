package com.example.quizz2

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ActivityPhotoPartageFlag : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_partage_flag)

        val intent = intent
        var n = 0
        if (intent.hasExtra("nombreFinalPointFlag")){ // vérifie qu'une valeur est associée à la clé “edittext”
            n = intent.getIntExtra("nombreFinalPointFlag", 0); // on récupère la valeur associée à la clé
        }
        val nbPoint = intent.getIntExtra("nombreFinalPointFlag", 0)
        val textView = findViewById<TextView>(R.id.scoreFinalFlag)
        textView.setText("Votre score est $nbPoint")


        //verification que les permissions sont donées
        if (askForPermissions()) isPermissionAllowed()
        else showPermissionDeniedDialog()

        //Recupération de l'intent contenant l'URI
        //Mettre à jour l'image
        //Afficher la photo
        val uri = intent.getParcelableExtra<Uri>(Intent.EXTRA_TEXT)
        val imageView = findViewById<ImageView>(R.id.imgAjoutPhoto)
        if (uri != null) imageView.setImageURI(uri)

        val backHome = findViewById<ImageView>(R.id.retourAccueil3)
        backHome.setOnClickListener {
            finish()
        }
    }

    //Renvoie un booléen si les permissions sont accordées par l'utilisateur
    fun isPermissionAllowed(): Boolean {
        Boolean
        return !(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
    }

    //Renvoie un booleen indiquant s'il faut demander les permissions
    fun askForPermissions(): Boolean {
        if (!isPermissionAllowed()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this as Activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this as Activity, android.Manifest.permission.CAMERA)) {
                showPermissionDeniedDialog()
            } else { //demande de permission à l'utilisateur
                ActivityCompat.requestPermissions(this as Activity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.CAMERA), REQUEST_CODE)
            }
            return false
        }
        return true
    }

    //utilisé si les permissions ont déjà été refusées avant le démarrage de l'appli
    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this).setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permission from app Settings.")
            .setPositiveButton("App Settings", DialogInterface.OnClickListener { dialogInterface, i ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", getPackageName(), null)
                intent.data = uri
                startActivity(intent)
            })
            .setNegativeButton("Cancel", null)
            .show()
    }

    //Recuperer le resultat obtenu (acces accepté ou non par l'utilisateur)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(this@ActivityPhotoPartageFlag, ActivityPhotoPartageFlag::class.java)
                    startActivity(intent)
                } else {
                    askForPermissions()
                }
                return
            }
        }
    }

    //utilisation de l'appli de prise de photo par defaut
    private fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    //recuperer le resultat
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            val imageView = findViewById<ImageView>(R.id.imgAjoutPhoto)
            imageView.setImageBitmap(data.extras!!.get("data") as Bitmap)
        }
    }

    fun onClickImage(view : View) {
        //bouton permettant de choisir une photo dans la gallerie
        val btnChoisirDansGallerie = findViewById<ImageView>(R.id.imgAjoutPhoto)
        btnChoisirDansGallerie.setOnClickListener {
            val intent = Intent(this, GallerieActivity::class.java)
            startActivity(intent)
        }
    }

    fun onClickBtnValider(view: View) {
        val btnValider = findViewById<Button>(R.id.btnValider)
        btnValider.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}