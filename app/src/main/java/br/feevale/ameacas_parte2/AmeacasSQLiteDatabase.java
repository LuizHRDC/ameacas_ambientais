package br.feevale.ameacas_parte2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class AmeacasSQLiteDatabase {
    Context ctx;
    public static final String DATABASE_NAME = "ameacas.db";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public AmeacasSQLiteDatabase(Context ctx) {
        this.ctx = ctx;
        db = new AmeacasSQLiteDatabaseHelper().getWritableDatabase();
    }

    public static class AmeacasTable implements BaseColumns {
        public static final String TABLE_NAME = "ameacas";
        public static final String COLUMN_ENDERECO = "endereco";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_DESCRICAO = "descricao";

        public static String getSQL() {
            String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_ENDERECO + " TEXT, " +
                    COLUMN_DATA + " TEXT, " +
                    COLUMN_DESCRICAO + " TEXT)";
            return sql;
        }
    }

    public Long addAmeaca(Ameaca a) {
        ContentValues values = new ContentValues();
        values.put(AmeacasTable.COLUMN_ENDERECO, a.getEndereco());
        values.put(AmeacasTable.COLUMN_DATA, a.getData());
        values.put(AmeacasTable.COLUMN_DESCRICAO, a.getDescricao());
        return db.insert(AmeacasTable.TABLE_NAME, null,values);
    }

    @SuppressLint("Range")
    public Ameaca getAmeaca(Long id) {
        String cols[] = {AmeacasTable._ID, AmeacasTable.COLUMN_ENDERECO, AmeacasTable.COLUMN_DESCRICAO, AmeacasTable.COLUMN_DATA};
        String args[] = {id.toString()};

        Cursor cursor = db.query(AmeacasTable.TABLE_NAME, cols, AmeacasTable._ID + "=?", args, null, null, AmeacasTable._ID);

        if (cursor.getCount() != 1) {
            return null;
        }

        cursor.moveToNext();
        Ameaca a = new Ameaca();
        a.setId(cursor.getLong(cursor.getColumnIndex(AmeacasTable._ID)));
        a.setEndereco(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_ENDERECO)));
        a.setData(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_DATA)));
        a.setDescricao(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_DESCRICAO)));
        return a;
    }

    @SuppressLint("Range")
    public List<Ameaca> getAmeacas() {
        String cols[] = {AmeacasTable._ID, AmeacasTable.COLUMN_ENDERECO,
                AmeacasTable.COLUMN_DATA, AmeacasTable.COLUMN_DESCRICAO};
        Cursor cursor = db.query(AmeacasTable.TABLE_NAME, cols,
                null, null, null, null, AmeacasTable.COLUMN_DATA);
        List<Ameaca> ameacas = new ArrayList<>();
        Ameaca a;
        while(cursor.moveToNext()){
            a = new Ameaca();
            a.setId(((Cursor) cursor).getLong(cursor.getColumnIndex(AmeacasTable._ID)));
            a.setEndereco(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_ENDERECO)));
            a.setData(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_DATA)));
            a.setDescricao(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_DESCRICAO)));
            ameacas.add(a);
        }
        return ameacas;
    }

    public Integer removeAmeaca(Ameaca a){
        String args[] = {a.getId().toString()};
        return db.delete(AmeacasTable.TABLE_NAME, AmeacasTable._ID + "=?", args);
    }

    public Integer updateAmeaca(Ameaca a){
        String args[] = {a.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(AmeacasTable.COLUMN_ENDERECO, a.getEndereco());
        values.put(AmeacasTable.COLUMN_DATA, a.getData());
        values.put(AmeacasTable.COLUMN_DESCRICAO, a.getDescricao());
        return db.update(AmeacasTable.TABLE_NAME, values, AmeacasTable._ID + "=?", args);
    }

    private class AmeacasSQLiteDatabaseHelper extends SQLiteOpenHelper {
        public AmeacasSQLiteDatabaseHelper() {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db) {db.execSQL(AmeacasTable.getSQL());}

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + AmeacasTable.TABLE_NAME);
            onCreate(db);
        }
    }

}
