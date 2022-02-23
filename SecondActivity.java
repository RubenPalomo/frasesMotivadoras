package es.ifp.frases;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    protected TextView label1;
    protected Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        label1 = (TextView)findViewById(R.id.label1_second);
        button1 = (Button)findViewById(R.id.button1_second);

        BBDDSQLite db;
        db = new BBDDSQLite(this);

        if(db.numeroDeFrases()==0){
            db.crearFrases();
            db.close();
        }

        label1.setText(db.fraseRandom());
        db.close();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label1.setText(db.fraseRandom());
                db.close();
            }
        });
    }
}