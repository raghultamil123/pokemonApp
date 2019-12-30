package com.example.pokemonapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pokemonapp.DTO.DropDown;
import com.example.pokemonapp.R;

import java.util.ArrayList;
import java.util.List;

public class DropDownAdapter extends BaseAdapter {
    Context context;
    List<DropDown> entity = new ArrayList<>();
    LayoutInflater inflater;

   public DropDownAdapter(Context applicationContext, List<DropDown> data){
        this.context = applicationContext;
        this.entity = data;
        this.inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return entity.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.drop_down_list,null);
        DropDown current = entity.get(position);
        TextView nameView = view.findViewById(R.id.name);
        nameView.setText(current.getName());
        TextView typeView = view.findViewById(R.id.type);
        typeView.setText(current.getCategory());

        return view;
    }
}
