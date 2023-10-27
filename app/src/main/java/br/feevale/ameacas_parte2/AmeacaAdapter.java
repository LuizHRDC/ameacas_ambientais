package br.feevale.ameacas_parte2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AmeacaAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private AmeacasSQLiteDatabase db;

    // O construtor inicializa o adapter com o contexto da aplicação e o objeto do banco de dados.
    public AmeacaAdapter(Context context, AmeacasSQLiteDatabase database) {
        inflater = LayoutInflater.from(context);
        db = database;
    }

    // Retorna o número de itens na lista de ameaças obtido do banco de dados.
    @Override
    public int getCount() {
        return db.getAmeacas().size();
    }

    // Retorna o item da lista de ameaças na posição especificada.
    @Override
    public Object getItem(int position) {
        return db.getAmeacas().get(position);
    }

    // Retorna o ID do item da lista de ameaças na posição especificada.
    @Override
    public long getItemId(int position) {
        return db.getAmeacas().get(position).getId();
    }

    // Infla o layout do item, se necessário, e preenche os TextViews com dados do objeto Ameaca.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.ameaca_list_item, null);
        }

        TextView txtAmeaca = convertView.findViewById(R.id.txtAmeaca);
        TextView txtData = convertView.findViewById(R.id.txtData);

        txtAmeaca.setText(db.getAmeacas().get(position).getEndereco());
        txtData.setText(db.getAmeacas().get(position).getData());

        return convertView;
    }
}
