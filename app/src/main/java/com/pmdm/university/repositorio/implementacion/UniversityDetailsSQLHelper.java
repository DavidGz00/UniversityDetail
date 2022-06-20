package com.pmdm.university.repositorio.implementacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import com.pmdm.university.entidad.UniversityDetail;
import com.pmdm.university.repositorio.implementacion.interfaz.Repositorio;


public class UniversityDetailsSQLHelper extends SQLiteOpenHelper implements Repositorio {
    public UniversityDetailsSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "University.db";

    public UniversityDetailsSQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    static class UniversityContrato{
        private UniversityContrato() {}
        public static class UniversityRegister implements BaseColumns {
            public static final String TABLE_NAME = "UNIVERSITY_DETAIL";
            public static final String NAME = "NAME";
            public static final String IMAGE_URL = "IMAGE_URL";
            public static final String DESCRIPTION = "DESCRIPTION";
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UniversityContrato.UniversityRegister.TABLE_NAME + " (" +
                UniversityContrato.UniversityRegister.NAME + " TEXT PRIMARY KEY AUTOINCREMENT, "+
                UniversityContrato.UniversityRegister.IMAGE_URL + " TEXT NOT NULL," +
                UniversityContrato.UniversityRegister.DESCRIPTION + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insert(UniversityDetail universityDetail) {

        SQLiteDatabase db = getWritableDatabase();
        if(db == null)
            db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UniversityContrato.UniversityRegister.IMAGE_URL, universityDetail.getImageUrl());
        values.put(UniversityContrato.UniversityRegister.DESCRIPTION, universityDetail.getDescription());
        db.insert(UniversityContrato.UniversityRegister.TABLE_NAME, null, values);
    }

    @Override
    public void update(UniversityDetail universityDetail) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UniversityContrato.UniversityRegister.IMAGE_URL, universityDetail.getImageUrl());
        values.put(UniversityContrato.UniversityRegister.DESCRIPTION, universityDetail.getDescription());
        db.update(UniversityContrato.UniversityRegister.TABLE_NAME,
                values,
                "nombre=?",
                new String[] {universityDetail.getName()});
    }

    @Override
    public void delete(UniversityDetail universityDetail) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(UniversityContrato.UniversityRegister.TABLE_NAME,
                "name=?",
                new String[] {universityDetail.getName()});
    }



}









