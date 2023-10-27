package br.feevale.ameacas_parte2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

public class add_ameaca extends AppCompatActivity {
    AmeacasSQLiteDatabase db;
    EditText txtEndereco, txtDate, txtDescricao;

    // Inicializa a interface e o banco de dados.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ameaca);

        txtDescricao = findViewById(R.id.txtDescricao);
        txtDate = findViewById(R.id.txtDate);
        txtEndereco = findViewById(R.id.txtEndereco);

        db = new AmeacasSQLiteDatabase(getBaseContext());
    }

    // Adiciona uma nova ameaça ao banco de dados e inicia MainActivity.
    public void changeToAdd(View v){
        Ameaca a = new Ameaca();
        a.setEndereco(txtEndereco.getText().toString());
        a.setData(txtDate.getText().toString());
        a.setDescricao(txtDescricao.getText().toString());
        db.addAmeaca(a);

        startActivity(new Intent(this, MainActivity.class));
    }
}
