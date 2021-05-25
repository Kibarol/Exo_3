package be.bxl.formation.exo_03_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import be.bxl.formation.exo_03_recyclerview.Adapters.FoodAdapter;
import be.bxl.formation.exo_03_recyclerview.models.Food;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private List<Food> foods;

    private EditText etName, etCalory;
    private Spinner spCategory;
    private Button btnAdd;
    private RecyclerView rvFoods;
    private FoodAdapter foodAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de la liste
        foods = new ArrayList<>();
        Food tomate = new Food("tomate", 15, Food.Category.VEGETABLE);
        Food jambon = new Food("jambon", 145, Food.Category.MEAT);
        Food salade = new Food("salade", 15, Food.Category.VEGETABLE);
        foods.add(tomate);
        foods.add(jambon);
        foods.add(salade);


        // Liaison avec le layout
        etName = findViewById(R.id.et_main_food_name);
        etCalory = findViewById(R.id.et_main_food_calory);
        spCategory = findViewById(R.id.sp_main_food_type);
        btnAdd = findViewById(R.id.btn_main_add_food);
        rvFoods = findViewById(R.id.rv_main_foods);

        // Définition du Spinner
        List<String> categoryChoices = new ArrayList<>();
        categoryChoices.add(getString(R.string.choice_category_vegetable));
        categoryChoices.add(getString(R.string.choice_category_meat));
        categoryChoices.add(getString(R.string.choice_category_fruit));

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1,
                categoryChoices
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCategory.setAdapter(spinnerAdapter);

        // DONE Ajouter le comportement du bouton (-> Ajouter un aliment dans la liste)
        /*btnAdd.setOnClickListener(v -> {
            Food.Category typeAlim;
            switch (spCategory.toString()){
                case "Légume" : typeAlim = Food.Category.VEGETABLE; // problème si l'app est traduite oO
                break;
                case "Viande" : typeAlim = Food.Category.MEAT;
                break;
                case "Fruit" : typeAlim = Food.Category.FRUIT;
                break;
                default: return;
            }*/

        btnAdd.setOnClickListener(this::addFood);
    }

        private void addFood(View v){
            String foodName = etName.getText().toString();
            String foodCalory = etCalory.getText().toString();



            Food.Category foodCatEnum = getSelectedFoodCategory();


            Food f = new Food(foodName,
                    Double.parseDouble(foodCalory),
                    foodCatEnum);

            Log.d("DEBUG_FOOD", foodName);
            Log.d("DEBUG_FOOD", foodCalory);
            Log.d("DEBUG_FOOD", foodCatEnum.toString());
            foods.add(0,f);
/*            this.configureRecycView();
            foodAdapter.notifyDataSetChanged();*/
        }
        private Food.Category getSelectedFoodCategory(){
            String foodCat = spCategory.getSelectedItem().toString();
            Food.Category foodCatEnum;
            if(foodCat.equals(getString(R.string.choice_category_vegetable))){
                foodCatEnum = Food.Category.VEGETABLE;
            }
            else if (foodCat.equals(getString(R.string.choice_category_meat))){
                foodCatEnum = Food.Category.MEAT;
            }
            else if (foodCat.equals(getString(R.string.choice_category_fruit))){
                foodCatEnum = Food.Category.FRUIT;
            }
            else {throw new RuntimeException("Unrecognized category");}
            return foodCatEnum;
        }

        /* TODO Configurer le RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvFoods.setLayoutManager(layoutManager);
        // TODO Créer l'adapter customisé (Aliment avec la CardView)*/


    private void configureRecycView(){
        this.foodAdapter = new FoodAdapter((ArrayList<Food>) foods);
        this.rvFoods.setAdapter(this.foodAdapter);
        this.rvFoods.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

}