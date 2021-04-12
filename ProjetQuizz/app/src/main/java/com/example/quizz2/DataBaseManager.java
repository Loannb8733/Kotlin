package com.example.quizz2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseManager extends SQLiteOpenHelper {

        public static final String dataBase_Name =  "Quizz.db";
        public static final int dataBase_Version = 1;


        public DataBaseManager( Context context) {
            super(context, dataBase_Name, null, dataBase_Version );
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String strSql = " create table questions ("
                    + "     idQuestion integer primary key autoincrement,"
                    + "     image String not null,"
                    + "     question text not null"
                    + ")";

            String strSql2 = " create table reponses ("
                    + "     idReponse integer primary key autoincrement,"
                    + "     reponse text not null,"
                    + "     idQuestion integer,"
                    + "     FOREIGN KEY (idQuestion) REFERENCES questions (idQuestion)"
                    + ");";




            sqLiteDatabase.execSQL( strSql);
            Log.i( "DATEBASE", "onCreate invoked");
            sqLiteDatabase.execSQL( strSql2);
            Log.i( "DATEBASE", "onCreate invoked");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String strSql = " drop table questions ";
            sqLiteDatabase.execSQL( strSql);
            this.onCreate( sqLiteDatabase);
            Log.i( "DATEBASE", "onUpgrade invoked");
            String strSql2 = " drop table questions ";
            sqLiteDatabase.execSQL( strSql2);
            this.onCreate( sqLiteDatabase);
            Log.i( "DATEBASE", "onUpgrade invoked");

        }

        public void insertQuestion( String image, String question ) {
            String strSql = "insert into questions (image, question) values ('"
                    + image + "', " + question + ", " +  ")";
            this.getWritableDatabase().execSQL( strSql );
            Log.i( "DATABASE", "insertQuestion invoked" );
        }

        public void insertReponse( String reponse, int idQuestion ) {
            String strSql = "insert into reponses (reponse, idQuestion) values ('"
                    + reponse + "', " + idQuestion + ", " +  ")";
            this.getWritableDatabase().execSQL( strSql );
            Log.i( "DATABASE", "insertReponse invoked" );
        }

    }


