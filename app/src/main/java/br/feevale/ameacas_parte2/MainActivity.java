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

        /* Inicia a atividade 'add_ameaca' quando o botão é pressionado, permitindo ao usuário adicionar uma nova ameaça. */
        addAmeacaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the add_ameaca activity
                Intent intent = new Intent(getBaseContext(), add_ameaca.class);
                startActivity(intent);
            }
        });

        /* Configura um listener para cliques em itens da ListView 'listAmeaca' para atualizar a ameaça selecionada. */
        listAmeaca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeToUpdate(id);
            }

            private void changeToUpdate(long id) {
                Intent it = new Intent(getBaseContext(), update_ameaca.class);
                it.putExtra("ID", id);
                startActivity(it);
            }
        });

        /* Remove uma ameaça com um clique longo em um item da ListView 'listAmeaca' e atualiza a lista. */
        listAmeaca.setOnItemLongClickListener((parent, view, position, id) -> {
            Ameaca ameaca = (Ameaca) ameacaAdapter.getItem(position);
            db.removeAmeaca(ameaca);
            ameacaAdapter.notifyDataSetChanged();
            return true;
        });

    }
}
