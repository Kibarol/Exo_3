package be.bxl.formation.exo_03_recyclerview.Adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.bxl.formation.exo_03_recyclerview.R;
import be.bxl.formation.exo_03_recyclerview.models.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<Food> foodList;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private EditText etName, etCalory;
        private Spinner spCategory;

        public ViewHolder(View v) {
            super(v);

            //Liaison avec layout
            etName = v.findViewById(R.id.et_main_food_name);
            etCalory = v.findViewById(R.id.et_main_food_calory);
            spCategory = v.findViewById(R.id.sp_main_food_type);
        }
        //region Getters

        public EditText getEtName() {
            return etName;
        }

        public EditText getEtCalory() {
            return etCalory;
        }

        public Spinner getSpCategory() {
            return spCategory;
        }
        //endregion

    }
    //region Constructeur de l'adapteur
    public FoodAdapter(ArrayList<Food> foods){
        this.foodList = foods;
    }
    //endregion
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Food target = foodList.get(position);

        holder.getEtName().setText(target.getName());
        holder.getEtCalory().setText(String.valueOf(target.getCalory()));
        // Comment gérer le spinner dans l'adapteur ? holder.getSpCategory()
        if(target.getCategory()== Food.Category.FRUIT){
            holder.getSpCategory().setBackgroundTintList(ColorStateList.valueOf(R.color.purple_200));
        } else if (target.getCategory() == Food.Category.MEAT){
            holder.getSpCategory().setBackgroundTintList(ColorStateList.valueOf(R.color.design_default_color_primary));
        } else {holder.getSpCategory().setBackgroundTintList(ColorStateList.valueOf(R.color.design_default_color_secondary));
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
