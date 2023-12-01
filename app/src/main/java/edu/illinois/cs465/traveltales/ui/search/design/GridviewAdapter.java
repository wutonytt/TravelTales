package edu.illinois.cs465.traveltales.ui.search.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.ui.search.model.PlacesModel;

import java.util.ArrayList;

public class GridviewAdapter extends ArrayAdapter<PlacesModel>{

    private ArrayList<PlacesModel> placesModels;

    public GridviewAdapter(@NonNull Context context, ArrayList<PlacesModel> placesModels) {
        super(context, 0 , placesModels);
        this.placesModels = placesModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HolderView holderView;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.grid_view_list_item , parent , false );
            holderView = new HolderView(convertView);
            convertView.setTag(holderView);

        }
        else{
            holderView = (HolderView) convertView.getTag();
        }

        PlacesModel model = getItem(position);
        if(model != null){
            holderView.icons.setImageResource(model.getIconId());
            holderView.tv.setText(model.getLetters());}

        return convertView;
    }

    public void setData(ArrayList<PlacesModel> newData) {
        placesModels.clear();
        placesModels.addAll(newData);
        notifyDataSetChanged();
    }



    private static class HolderView{
        private final ImageView icons;
        private final TextView tv;

        public HolderView(View view){
            icons = view.findViewById(R.id.icon_id);
            tv = view.findViewById(R.id.textview);

        }
    }
}