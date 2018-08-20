package com.soft.bremotlajugo.firebaseconexion;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Firebase mRef;

    Button botonFoggy,botonSunny;
    TextView textCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonFoggy = (Button) findViewById(R.id.buttonFoggy);
        botonSunny = (Button) findViewById(R.id.buttonSunny);
        textCondition = (TextView) findViewById(R.id.textViewCondition);
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://resplendent-torch-1255.firebaseio.com/Llamar%20Mesero/Mesa1");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String newCondition = (String) dataSnapshot.getValue();
                textCondition.setText(newCondition);
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(getApplicationContext());

                builder.setMessage("Llamado de la mesa "+ newCondition + "¿Confirma atencion?")
                        .setTitle("Confirmacion")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Dialogos", "Confirmacion Aceptada.");
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Dialogos", "Confirmacion Cancelada.");
                                dialog.cancel();
                            }
                        });


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        botonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("SUNNY");
            }
        });

        botonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("FOGGY");
            }
        });
    }

}
