package edu.uc.jonesbr.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.uc.jonesbr.myapplication.dto.Food;
import edu.uc.jonesbr.myapplication.dto.FoodType;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView actType;
    private EditText edtCalories;
    private EditText edtCost;
    private EditText edtName;
    private EditText edtPrepTime;
    private Spinner spnFood;
    private Food food;
    private List<Food> allFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtCalories = findViewById(R.id.edtCalories);
        edtCost = findViewById(R.id.edtCost);
        edtName = findViewById(R.id.edtName);
        edtPrepTime = findViewById(R.id.edtPrepTime);
        spnFood = findViewById(R.id.spnFood);
        actType = findViewById(R.id.actType);

        food = new Food();
        allFoods = new ArrayList<Food>();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFood();
            }
        });

        actType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodType type = (FoodType) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, type + " Very nutritious!", Toast.LENGTH_LONG).show();
            }
        });
        spnFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                food = (Food) parent.getItemAtPosition(position);
                actType.setText(food.getType());
                String textCalories = Integer.toString(food.getCalories());
                edtCalories.setText(textCalories);
                edtCost.setText(food.getCost());
                String properName = properCase(food.getName());
                edtName.setText(properName);
                edtPrepTime.setText(food.getPrepTime());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        prepopulateFoods();
        updateSpinner();
        fetchFoodTypes();

    }

    private String properCase(String name) {
        String formatted = "";
        if (name.length() > 0) {
          formatted = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        }
        return formatted;
    }

    private void prepopulateFoods() {
        Food mustard = new Food("Mustard", 0, "1.29", "0");
        allFoods.add(mustard);
        Food pickles = new Food("Pickles");
        allFoods.add(pickles);
        Food pawpaw = new Food("Paw Paw", 100, "10.00");
        allFoods.add(pawpaw);
        Food fujiApple = new Food();
        fujiApple.setName("Fuji Apple");
        fujiApple.setCost("1.99");
        allFoods.add(fujiApple);
    }

    private void fetchFoodTypes() {
    }

    private void updateSpinner() {
        ArrayAdapter<Food> adapter = new ArrayAdapter<Food>(this, R.layout.support_simple_spinner_dropdown_item, allFoods);
        spnFood.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void save(View v) {
        food.setName(edtName.getText().toString());
        food.setCost(edtCost.getText().toString());
        food.setPrepTime(edtPrepTime.getText().toString());
        food.setType(actType.getText().toString());
        allFoods.add(food);
        food = new Food();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void newFood() {
        food = new Food();
        actType.setText("");
        edtCalories.setText("");
        edtCost.setText("");
        edtName.setText("");
        edtPrepTime.setText("");

    }

}
