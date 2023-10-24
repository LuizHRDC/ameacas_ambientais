package br.feevale.ameacas_parte2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    ListView listAmeaca;
    AmeacaAdapter ameacaAdapter;
    AmeacasSQLiteDatabase db;
    Button addAmeacaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new AmeacasSQLiteDatabase(getBaseContext());
        ameacaAdapter = new AmeacaAdapter(getBaseContext(), db);
        listAmeaca = findViewById(R.id.listAmeaca); // Initialize listAmeaca here
        listAmeaca.setAdapter(ameacaAdapter);
        addAmeacaButton = findViewById(R.id.addAmeaca);

        addAmeacaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the add_ameaca activity
                Intent intent = new Intent(getBaseContext(), add_ameaca.class);
                startActivity(intent);
            }
        });


        listAmeaca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeToUpdate(id);
            }

            private void changeToAdd(View v) {
                Intent intent = new Intent(getBaseContext(), add_ameaca.class);
                startActivity(intent);
            }


            private void changeToUpdate(long id) {
                Intent it = new Intent(getBaseContext(), update_ameaca.class);
                it.putExtra("ID", id);
                startActivity(it);
            }

        });
    }
}
