package com.example.laptrinhandroid_roomdatabase_customlistview;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptrinhandroid_roomdatabase_customlistview.room.dao.LocationDAO;
import com.example.laptrinhandroid_roomdatabase_customlistview.room.entity.Location;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.NameViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<Location> locations;
    LocationDAO locationDAO;

    public LocationAdapter(Context context, List<Location> locations, LocationDAO locationDAO) {
        this.context = context;
        this.locations = locations;
        this.inflater = LayoutInflater.from(context);
        this.locationDAO =locationDAO;
    }

    @NonNull
    @Override
    public LocationAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.line_item,parent,false);
        return new NameViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.NameViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.txt_li_location.setText(position+". "+location.getName());
        holder.ibt_edit.setBackgroundResource(R.drawable.ic_baseline_edit_location_alt_24);
        holder.ibt_delete.setBackgroundResource(R.drawable.ic_baseline_cancel_24);

        holder.ibt_delete.setOnClickListener(v->{
            locationDAO.delete(location);
            dataChange();
        });
        holder.ibt_edit.setOnClickListener(v->showDialogUpdate(context,position));
    }

    private void dataChange() {
        locations = locationDAO.getAll();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {
        LocationAdapter adapter;
        TextView txt_li_location;
        ImageButton ibt_edit, ibt_delete;
        public NameViewHolder(@NonNull View itemView, LocationAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            txt_li_location = itemView.findViewById(R.id.txt_li_location);
            ibt_edit = itemView.findViewById(R.id.ibt_edit);
            ibt_delete = itemView.findViewById(R.id.ibt_delete);
        }
    }

    private void showDialogUpdate(Context c, int position) {
        final EditText taskEditText = new EditText(c);
        taskEditText.setText(locations.get(position).getName());
        Toast.makeText(context,locations.get(position).toString(),Toast.LENGTH_SHORT).show();
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Edit Location?")
                .setMessage("Enter new Location")
                .setView(taskEditText)
                .setPositiveButton("Update", (dialog1, which) -> {
                    Location location = new Location();
                    location.setId(locations.get(position).getId());
                    location.setName(taskEditText.getText().toString());
                    Toast.makeText(context,location.toString(),Toast.LENGTH_SHORT).show();
                    locationDAO.update(location);
                    dataChange();
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}
