package be.bxl.formation.exo_03_recyclerview.Adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.bxl.formation.exo_03_recyclerview.R;
import be.bxl.formation.exo_03_recyclerview.models.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<Food> foodList;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvCalory;
        private CardView cvCategory;

        public ViewHolder(View v) {
            super(v);

            //Liaison avec layout
            tvName = v.findViewById(R.id.item_food_name);
            tvCalory = v.findViewById(R.id.item_food_calory);
            cvCategory = v.findViewById(R.id.item_cv_color);
        }
        //region Getters
        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvCalory() {
            return tvCalory;
        }

        public CardView getCvCategory() {
            return cvCategory;
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

        holder.getTvName().setText(target.getName());
        holder.getTvCalory().setText(String.valueOf(target.getCalory()));
        // Comment gérer le spinner dans l'adapteur ? holder.getSpCategory()
        if(target.getCategory()== Food.Category.FRUIT){
            holder.getCvCategory().setBackgroundTintList(ColorStateList.valueOf(R.color.purple_200));
        } else if (target.getCategory() == Food.Category.MEAT){
            holder.getCvCategory().setBackgroundTintList(ColorStateList.valueOf(R.color.design_default_color_primary));
        } else {holder.getCvCategory().setBackgroundTintList(ColorStateList.valueOf(R.color.design_default_color_secondary));
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
