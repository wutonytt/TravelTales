package edu.illinois.cs465.traveltales.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;


import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.ActivityMainBinding;
import edu.illinois.cs465.traveltales.databinding.FragmentAddBinding;
import edu.illinois.cs465.traveltales.ui.search.design.GridviewAdapter;
import edu.illinois.cs465.traveltales.ui.search.model.ArrayListModel;
import edu.illinois.cs465.traveltales.ui.search.model.PlacesModel;


public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        GridView gridView = root.findViewById(R.id.my_grid_view);
        GridviewAdapter gridviewAdapter = new GridviewAdapter(this.getContext(), new ArrayListModel().setListData());
        gridView.setAdapter(gridviewAdapter);
        gridView.setOnItemClickListener(this);

        return root;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        PlacesModel model = (PlacesModel) parent.getItemAtPosition(position);
        Toast.makeText(requireContext(), "Clicked By: " + model.getLetters(), Toast.LENGTH_SHORT).show();
    }





}
