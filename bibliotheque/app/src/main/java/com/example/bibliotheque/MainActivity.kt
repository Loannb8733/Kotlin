package com.example.bibliotheque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.example.bibliotheque.adapter.MyListAdapter
import com.example.bibliotheque.classes.livreModelClass
import com.example.bibliotheque.handler.DatabaseHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //method for saving records in database
    fun saveRecord(view: View) {
        val id = u_id.text.toString()
        val isbn = u_isbn.text.toString()
        val titre = u_titre.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (id.trim() != "" && isbn.trim() != "" && titre.trim() != "") {
            val status =
                databaseHandler.addLivre(livreModelClass(Integer.parseInt(id), isbn, titre))
            if (status > -1) {
                Toast.makeText(applicationContext, "record save", Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_isbn.text.clear()
                u_titre.text.clear()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "id or Isbn or Title cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    //method for read records from database in ListView
    fun viewRecord(view: View) {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val livre: List<livreModelClass> = databaseHandler.viewLivre()
        val livreArrayId = Array<String>(livre.size) { "0" }
        val livreArrayIsbn = Array<String>(livre.size) { "null" }
        val livreArrayTitre = Array<String>(livre.size) { "null" }
        var index = 0
        for (e in livre) {
            livreArrayId[index] = e.livreId.toString()
            livreArrayIsbn[index] = e.livreIsbn
            livreArrayTitre[index] = e.livreTitre
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = MyListAdapter(this, livreArrayId, livreArrayIsbn, livreArrayTitre)
        listView.adapter = myListAdapter
    }

    //method for updating records based on user id
    fun updateRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtName = dialogView.findViewById(R.id.updateIsbn) as EditText
        val edtEmail = dialogView.findViewById(R.id.updateTitre) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateIsbn = edtName.text.toString()
            val updateTitre = edtEmail.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (updateId.trim() != "" && updateIsbn.trim() != "" && updateTitre.trim() != "") {
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.updateLivre(
                    livreModelClass(
                        Integer.parseInt(updateId),
                        updateIsbn,
                        updateTitre
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record update", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or Isbn or Title cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }

    //method for deleting records based on id
    fun deleteRecord(view: View) {
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (deleteId.trim() != "") {
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteLivre(
                    livreModelClass(
                        Integer.parseInt(deleteId),
                        "",
                        ""
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record deleted", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or Isbn or Title cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
}