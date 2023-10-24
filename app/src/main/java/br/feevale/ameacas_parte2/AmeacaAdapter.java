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

    public AmeacaAdapter(Context context, AmeacasSQLiteDatabase database) {
        inflater = LayoutInflater.from(context);
        db = database;
    }

    @Override
    public int getCount() {
        return db.getAmeacas().size();
    }

    @Override
    public Object getItem(int position) {
        return db.getAmeacas().get(position);
    }

    @Override
    public long getItemId(int position) {
        return db.getAmeacas().get(position).getId();
    }

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
