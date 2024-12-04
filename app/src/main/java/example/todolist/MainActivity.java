package example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView categoryListView;
    private ArrayAdapter<String> adapter;
    private List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        categoryListView = findViewById(R.id.lv_categories);

        // Load categories
        loadCategories();

        // Set up the "Add Category" button
        Button addCategoryButton = findViewById(R.id.btn_add_category);
        addCategoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddCategoryActivity.class);
            startActivity(intent);
        });

        // Handle item clicks for categories
        categoryListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            String category = categories.get(position);
            Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
            intent.putExtra("CATEGORY_NAME", category);
            startActivity(intent);
        });
    }

    // Method to load categories from the database
    private void loadCategories() {
        categories = dbHelper.getAllCategories();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        categoryListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh categories list when coming back from AddCategoryActivity
        loadCategories();
    }
}
