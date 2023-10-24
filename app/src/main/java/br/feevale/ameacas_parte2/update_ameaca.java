package br.feevale.ameacas_parte2;

import androidx.appcompat.app.AppCompatActivity;

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
        db = new AmeacasSQLiteDatabase(
                getBaseContext());
        Long id = getIntent().getLongExtra("ID", 0);
        current = db.getAmeaca(id);

        txtEndereco.setText(current.getEndereco());
        txtDate.setText(current.getData());
        txtDescricao.setText(current.getDescricao());
    }

    public void updateAmeaca(View v){
        current.setEndereco(txtDate.getText().toString());
        current.setData(txtEndereco.getText().toString());
        current.setDescricao(txtDescricao.getText().toString());
        db.updateAmeaca(current);
        finish();
    }

}