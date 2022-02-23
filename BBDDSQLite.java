package es.ifp.frases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BBDDSQLite extends SQLiteOpenHelper{
    protected SQLiteDatabase db;


    public BBDDSQLite(@Nullable Context context) {
        super(context, "frasesBBDD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE frasesMotivadoras (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, frase TEXT)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS frasesMotivadoras");
    }

    public void crearFrases() {
        db = this.getReadableDatabase();
        String createFrases = "INSERT INTO frasesMotivadoras (frase) VALUES" +
                "('No dejes para mañana lo que puedas hacer hoy.'),"+
                "('Al pan, pan y al vino, vino.'),"+
                "('El agua deja un surco en la roca no por la fuerza, sino por la constancia.'),"+
                "('Cría cuervos y tendrás muchos.'),"+
                "('Cree en ti mismo y serás imparable.'),"+
                "('Rendirse no es una opción.'),"+
                "('Como no sabía que era imposible fue y lo hizo.'),"+
                "('Vas a sacar un 10 en programación.'),"+
                "('Quien tropieza y no cae avanza más rápido.'),"+
                "('¡Antes morir que retroceder!'),"+
                "('Conserva tus sueños, nunca sabes cuando te harán falta.')";
        db.execSQL(createFrases);
    }

    public int numeroDeFrases(){
        int num;
        db = this.getReadableDatabase();
        num = (int) DatabaseUtils.queryNumEntries(db, "frasesMotivadoras");
        return num;
    }

    @SuppressLint("Range")
    public String fraseRandom(){
        Cursor res;
        String frase;
        int n = numeroDeFrases()+1;
        int numeroRandom = (int) (Math.random() * (n - 1)) + 1;
        db = this.getReadableDatabase();
        res = db.rawQuery("SELECT * FROM frasesMotivadoras WHERE id ="+numeroRandom, null);

        res.moveToFirst();
        while(res.isAfterLast()==false) {
            frase = res.getString(res.getColumnIndex("frase"));
            return frase;
        }
        return "No se ha podido acceder a la base de datos.";
    }

    public void close(){
        db.close();
    }
}