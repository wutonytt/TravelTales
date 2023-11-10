package edu.illinois.cs465.traveltales;

import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<Uri> uriArrayList;
    private OnCoverPhotoSelectedListener listener;
    private int coverphotoid = -1;

    public RecyclerAdapter(ArrayList<Uri> uriArrayList){
        this.uriArrayList = uriArrayList;
    }

    // A medium interface to intro the needed vars
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("RecyclerAdapter", "onCreateViewHolder called");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_single_image, parent, false);
        return new ViewHolder(view);
    }

    // place the data into ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        //Log.v("ray", "Assigning ..... " + holder.getAdapterPosition());
        holder.imageView.setImageURI(uriArrayList.get(position));
        holder.imageView.setAlpha(1f);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Cancel selection by clicking the selected cover photo
                if(coverphotoid == holder.getAdapterPosition()){
                    coverphotoid = -1;
                    holder.imageView.setAlpha(1f);
                }
                else{
                    if(coverphotoid != -1){
                        //Log.v("ray", "Reseting old cover photo's alpha = " + holder.getAdapterPosition());
                        notifyItemChanged(coverphotoid);
                    }
                    coverphotoid = holder.getAdapterPosition();
                    holder.imageView.setAlpha(0.5f);
                }
                //Log.v("ray", "Current cover photo is " + holder.getAdapterPosition());

                // Notify the listener with the new cover photo ID.
                if (listener != null) {
                    listener.onCoverPhotoSelected(coverphotoid);
                }
            }
        });
    }

    public interface OnCoverPhotoSelectedListener {
        void onCoverPhotoSelected(int coverPhotoId);
    }

    public void setOnCoverPhotoSelectedListener(OnCoverPhotoSelectedListener listener) {
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

}
