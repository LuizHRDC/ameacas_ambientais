package br.feevale.ameacas_parte2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class update_ameaca extends AppCompatActivity {
    AmeacasSQLiteDatabase db;
    EditText txtEndereco, txtDate, txtDescricao;
    Ameaca current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ameaca);

        // Inicializa os campos de texto e a instância do banco de dados
        txtEndereco = findViewById(R.id.txtEndereco);
        txtDate = findViewById(R.id.txtDate);
        txtDescricao = findViewById(R.id.txtDescricao);
        db = new AmeacasSQLiteDatabase(getBaseContext());

        // Obtém o ID da ameaça a ser atualizada da Intent
        long id = getIntent().getLongExtra("ID", 0);

        // Recupera os detalhes da ameaça atual do banco de dados
        current = db.getAmeaca(id);

        // Preenche os campos de texto com os detalhes da ameaça atual
        txtEndereco.setText(current.getEndereco());
        txtDate.setText(current.getData());
        txtDescricao.setText(current.getDescricao());
    }

    public void updateAmeaca(View v) {
        // Atualiza os dados da ameaça com as informações dos campos de texto
        current.setEndereco(txtEndereco.getText().toString());
        current.setData(txtDate.getText().toString());
        current.setDescricao(txtDescricao.getText().toString());

        // Atualiza a ameaça no banco de dados
        db.updateAmeaca(current);

        // Retorna para a MainActivity para atualizar a ListView
        startActivity(new Intent(this, MainActivity.class));
    }
}
