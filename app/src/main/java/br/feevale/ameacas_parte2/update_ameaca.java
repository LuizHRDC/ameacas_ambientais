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

        txtEndereco = findViewById(R.id.txtEndereco);
        txtDate = findViewById(R.id.txtDate);
        txtDescricao = findViewById(R.id.txtDescricao);
        db = new AmeacasSQLiteDatabase(getBaseContext());

        long id = getIntent().getLongExtra("ID", 0);
        current = db.getAmeaca(id);

        txtEndereco.setText(current.getEndereco());
        txtDate.setText(current.getData());
        txtDescricao.setText(current.getDescricao());
    }

    public void updateAmeaca(View v) {
        current.setEndereco(txtEndereco.getText().toString());
        current.setData(txtDate.getText().toString());
        current.setDescricao(txtDescricao.getText().toString());

        // Update the ameaca in the database
        db.updateAmeaca(current);

        // Go back to the MainActivity to refresh the ListView
        startActivity(new Intent(this, MainActivity.class));
    }
}
