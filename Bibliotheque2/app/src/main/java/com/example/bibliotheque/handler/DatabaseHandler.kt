package com.example.bibliotheque.handler

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.example.bibliotheque.classes.livModelClass

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "LivreDatabase"
        private val TABLE_LIVRES = "LivreTable"
        private val KEY_ID = "id"
        private val KEY_ISBN = "isbn"
        private val KEY_TITRE = "titre"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //création d'une tables avec des champs
        val CREATE_LIVRES_TABLE = ("CREATE TABLE " + TABLE_LIVRES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ISBN + " TEXT,"
                + KEY_TITRE + " TEXT" + ")")
        db?.execSQL(CREATE_LIVRES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_LIVRES")
        onCreate(db)
    }


    //méthode pour insérer des données
    fun addLivre(liv: livModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, liv.livreId)
        contentValues.put(KEY_ISBN, liv.livreIsbn) // livModelClass Isbn
        contentValues.put(KEY_TITRE,liv.livreTitre) // livModelClass Titre
        // Insertion d'une rangée
        val success = db.insert(TABLE_LIVRES, null, contentValues)
        //Le second argument est une chaîne de caractères contenant nullColumnHack.
        db.close() // Fermeture de la connexion à la base de données
        return success
    }

    //méthode de lecture des données
    fun viewLivre():List<livModelClass>{
        val livList:ArrayList<livModelClass> = ArrayList<livModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_LIVRES"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var livreId: Int
        var livreIsbn: String
        var livreTitre: String
        if (cursor.moveToFirst()) {
            do {
                livreId = cursor.getInt(cursor.getColumnIndex("id"))
                livreIsbn = cursor.getString(cursor.getColumnIndex("isbn"))
                livreTitre = cursor.getString(cursor.getColumnIndex("titre"))
                val liv= livModelClass(livreId = livreId, livreIsbn = livreIsbn, livreTitre = livreTitre)
                livList.add(liv)
            } while (cursor.moveToNext())
        }
        return livList
    }


    //méthode de mise à jour des données
    fun updateLivre(liv: livModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, liv.livreId)
        contentValues.put(KEY_ISBN, liv.livreIsbn) // livModelClass Isbn
        contentValues.put(KEY_TITRE,liv.livreTitre ) // livModelClass Titre

        // Mise à jour de la rangée
        val success = db.update(TABLE_LIVRES, contentValues,"id="+liv.livreId,null)
        //Le second argument est une chaîne de caractères contenant nullColumnHack.
        db.close() // Fermeture de la connexion à la base de données
        return success
    }

    //méthode pour supprimer les données
    fun deleteLivre(liv: livModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, liv.livreId) // livModelClass livreId
        // Suppression d'une rangée
        val success = db.delete(TABLE_LIVRES,"id="+liv.livreId,null)
        //Le second argument est une chaîne de caractères contenant nullColumnHack.
        db.close() // Fermeture de la connexion à la base de données
        return success
    }
}