package com.example.smartpantryassignment.adapters;

import android.view.LayoutInflater; //tells java compiler where to find the layoutinflator class within the Android OS libraries
import android.view.View; // view class which is basic foundation for interface components
import android.view.ViewGroup; //special subclass that acts as invisible container to hold and organise other views
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartpantryassignment.R;
import com.example.smartpantryassignment.models.PantryItem;
import java.util.ArrayList;

public class PantryAdapter extends //extend establish inheritence
RecyclerView.Adapter<PantryAdapter.ViewHolder> {
    private ArrayList<PantryItem> pantryList;
    public PantryAdapter (ArrayList<PantryItem> pantryList) {
        this.pantryList = pantryList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIngredient;

        public ViewHolder(View itemView) {
            super(itemView); //superclass/parent whose methods are inherited by other classes
            txtIngredient = itemView.findViewById(R.id.txtIngredient);
        }
    }
    @NonNull //refers to an object reference that successfully points to an actual instance in memory
    @Override //tells the compiler this method should override a method from superclass
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
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
                            item.getUnitMeasure()
            );
        }
        @Override
        public int getItemCount() {
        return pantryList.size();
        }
}
