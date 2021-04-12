package com.example.bibliotheque.handler

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.bibliotheque.classes.livreModelClass

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "LivreDatabase"
        private val TABLE_CONTACTS = "LivreTable"
        private val KEY_ID = "id"
        private val KEY_ISBN = "isbn"
        private val KEY_TITRE = "titre"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ISBN + " TEXT,"
                + KEY_TITRE + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addLivre(livre: livreModelClass): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, livre.livreId)
        contentValues.put(KEY_ISBN, livre.livreIsbn) // EmpModelClass Name
        contentValues.put(KEY_TITRE, livre.livreTitre) // EmpModelClass Phone
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to read data
    fun viewLivre(): List<livreModelClass> {
        val livreList: ArrayList<livreModelClass> = ArrayList<livreModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
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
                val livre = livreModelClass(livreId = livreId, livreIsbn = livreIsbn, livreTitre = livreTitre)
                livreList.add(livre)
            } while (cursor.moveToNext())
        }
        return livreList
    }

    //method to update data
    fun updateLivre(livre: livreModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, livre.livreId)
        contentValues.put(KEY_ISBN, livre.livreIsbn) // EmpModelClass Name
        contentValues.put(KEY_TITRE, livre.livreTitre) // EmpModelClass Email

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues, "id=" + livre.livreId, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to delete data
    fun deleteLivre(livre: livreModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, livre.livreId) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS, "id=" + livre.livreId, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}