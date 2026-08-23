package com.example.smartpantryassignment.adapters;

import android.view.LayoutInflater; //tells java compiler where to find the layoutinflator class within the Android OS libraries
import android.view.View; // view class which is basic foundation for interface components
import android.view.ViewGroup; //special subclass that acts as invisible container to hold and organise other views
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartpantryassignment.R;
import com.example.smartpantryassignment.models.PantryItem;
import java.util.ArrayList;
import com.example.smartpantryassignment.database.DatabaseHelper;


public class PantryAdapter extends //extend establish inheritence
RecyclerView.Adapter<PantryAdapter.ViewHolder> {
    private ArrayList<PantryItem> pantryList;
    private DatabaseHelper dbHelper;

    public PantryAdapter(ArrayList<PantryItem> pantryList,
                         DatabaseHelper dbHelper) {
        this.pantryList = pantryList;
        this.dbHelper = dbHelper;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIngredient;
        Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView); //superclass/parent whose methods are inherited by other classes
            txtIngredient = itemView.findViewById(R.id.txtIngredient);
            btnDelete =
                    itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    //refers to an object reference that successfully points to an actual instance in memory
    @Override //tells the compiler this method should override a method from superclass
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) //builtin class to convert XML layout file into the corresponding Java view object
                .inflate(R.layout.item_pantry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PantryItem item = pantryList.get(position);
        holder.txtIngredient.setText(
                item.getIngredient() + "-" +
                        item.getQuantity() + " " +
                        item.getUnitMeasure());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int adapterPosition =
                        holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    dbHelper.deleteIngredient(
                            pantryList.get(adapterPosition)
                                    .getPantryId());
                    pantryList.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                }
            }
        });

    }

        @Override
        public int getItemCount() {
        return pantryList.size();
        }

        public interface OnIngredientClickListener {
        void onIngredientClick(PantryItem item);
        }
}
