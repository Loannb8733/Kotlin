package com.example.bibliotheque

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bibliotheque.adapter.MyListAdapter
import com.example.bibliotheque.classes.livModelClass
import com.example.bibliotheque.handler.DatabaseHandler



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //méthode pour sauvegarder les valeurs saisies dans une base de données
    fun saveRecord(view: View){
        val id = u_id.text.toString()
        val isbn = u_isbn.text.toString()
        val titre = u_titre.text.toString()
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        if(id.trim()!="" && isbn.trim()!="" && titre.trim()!=""){
            val status = databaseHandler.addLivre(livModelClass(Integer.parseInt(id),isbn, titre))
            if(status > -1){
                Toast.makeText(applicationContext,"valeurs enregistrées",
                        Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_isbn.text.clear()
                u_titre.text.clear()
            }
        } else {
            Toast.makeText(applicationContext,"id ou isbn ou titre ne peut être vide",
                    Toast.LENGTH_LONG).show()
        }

    }

    //Méthode pour lire les valeurs enregistrés de la base de données dans le ListView
    fun viewRecord(view: View){

        //création de l'instance de la classe DatabaseHandler
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //appel de la méthode viewLivre contenu dans la classe DatabaseHandler pour lire les valeurs
        val liv: List<livModelClass> = databaseHandler.viewLivre()
        val livArrayId = Array<String>(liv.size){"0"}
        val livArrayIsbn = Array<String>(liv.size){"null"}
        val livArrayTitre = Array<String>(liv.size){"null"}
        var index = 0
        for(e in liv){
            livArrayId[index] = e.livreId.toString()
            livArrayIsbn[index] = e.livreIsbn
            livArrayTitre[index] = e.livreTitre
            index++
        }
        //création d'un ArrayAdapter personnalisé
        val myListAdapter = MyListAdapter(this,livArrayId,livArrayIsbn,livArrayTitre)
        listView.adapter = myListAdapter
    }


    //méthode de mise à jour des valeurs enregistrées
    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtIsbn = dialogView.findViewById(R.id.updateIsbn) as EditText
        val edtTitre = dialogView.findViewById(R.id.updateTitre) as EditText

        dialogBuilder.setTitle("Mise à jour des valeurs")
        dialogBuilder.setMessage("Entrez les données ci-dessous")
        dialogBuilder.setPositiveButton("Mise à jour", DialogInterface.OnClickListener { _, _
            ->
            val updateId = edtId.text.toString()
            val updateIsbn = edtIsbn.text.toString()
            val updateTitre = edtTitre.text.toString()
            //création de l'instance de la classe DatabaseHandler
            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            if(updateId.trim()!="" && updateIsbn.trim()!="" && updateTitre.trim()!=""){
                //appel de la méthode updateLivre contenu dans la classe DatabaseHandler
                    // pour lire les valeurs
                val status = databaseHandler.updateLivre(livModelClass(Integer.parseInt(updateId),
                        updateIsbn, updateTitre))
                if(status > -1){
                    Toast.makeText(applicationContext,"valeurs mises à jour",
                            Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"id ou isbn ou titre ne peut pas être vide",
                        Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Annuler", DialogInterface.OnClickListener { dialog,
                                                                                    which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }

    //méthode de suppression d'enregistrements basée sur l'identification
    fun deleteRecord(view: View){
        //création d'un AlertDialog pour prendre l'identifiant de l'utilisateur
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Supression du livre Record")
        dialogBuilder.setMessage("Entrez l'id ci-dessous")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            if(deleteId.trim()!=""){
                //calling the deleteLivre method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteLivre(livModelClass(Integer.parseInt(deleteId),
                        "",""))
                if(status > -1){
                    Toast.makeText(applicationContext,"Livre suprimmé",
                            Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"id ou isbn ou titre ne doit pas être vide",
                        Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Annuler", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
}

