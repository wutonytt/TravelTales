package edu.illinois.cs465.traveltales.ui.search.model;

import edu.illinois.cs465.traveltales.R;
import java.util.ArrayList;


public class ArrayListModel {

    public ArrayList<PlacesModel> setListData(){

        ArrayList<PlacesModel> arrayList = new ArrayList<>();
        arrayList.add(new PlacesModel(R.drawable.amsterdam1, "Amsterdam"));
        arrayList.add(new PlacesModel(R.drawable.b, "Almere"));
        arrayList.add(new PlacesModel(R.drawable.c, "Europe"));
        arrayList.add(new PlacesModel(R.drawable.p, "Hrleme"));
        arrayList.add(new PlacesModel(R.drawable.l, "Belgium"));
        arrayList.add(new PlacesModel(R.drawable.m, "Brussels"));
        arrayList.add(new PlacesModel(R.drawable.n, "Germany"));
        arrayList.add(new PlacesModel(R.drawable.h, "Netherland"));
        arrayList.add(new PlacesModel(R.drawable.o, "A"));
        arrayList.add(new PlacesModel(R.drawable.t, "Paris"));
        arrayList.add(new PlacesModel(R.drawable.u, "Egypt"));
        arrayList.add(new PlacesModel(R.drawable.q, "Goa"));
        arrayList.add(new PlacesModel(R.drawable.r, "Italy"));
        arrayList.add(new PlacesModel(R.drawable.g, "California"));
        arrayList.add(new PlacesModel(R.drawable.v, "Istanbul"));
        arrayList.add(new PlacesModel(R.drawable.d, "Australia"));
        arrayList.add(new PlacesModel(R.drawable.w, "India"));
        arrayList.add(new PlacesModel(R.drawable.f, "New York"));
        arrayList.add(new PlacesModel(R.drawable.s, "Kashmir"));
        arrayList.add(new PlacesModel(R.drawable.j, "Michigan"));
        arrayList.add(new PlacesModel(R.drawable.k, "Yosemite"));
        arrayList.add(new PlacesModel(R.drawable.i, "Tennessee"));
        arrayList.add(new PlacesModel(R.drawable.e, "Costa Rica"));
        arrayList.add(new PlacesModel(R.drawable.x, "Greenland"));
        arrayList.add(new PlacesModel(R.drawable.y, "Niagara Falls"));
        arrayList.add(new PlacesModel(R.drawable.z, "Canada"));

        return arrayList;

    }
}
