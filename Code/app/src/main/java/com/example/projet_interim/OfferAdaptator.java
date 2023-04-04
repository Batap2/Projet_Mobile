package com.example.projet_interim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class OfferAdaptator extends BaseAdapter {

    private Context context;
    private List<String[]> list;
    private LayoutInflater inflater;

    public OfferAdaptator(Context context, List<String[]> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String[] getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.offre_listview_adaptator,null);
        TextView title = view.findViewById(R.id.offre_title);
        TextView description = view.findViewById(R.id.offre_description);
        TextView coord = view.findViewById(R.id.offre_coord);

        String[] s = getItem(i);
        /*title.setText(s[0]);
        description.setText(s[1]);
        coord.setText(s[2]);*/
        title.setText(s[0] + "\n\n" + s[1] + "\n\n" + s[2]);

        return view;
    }
}
