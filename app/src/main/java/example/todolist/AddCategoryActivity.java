package example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCategoryActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        dbHelper = new DatabaseHelper(this);

        EditText categoryNameInput = findViewById(R.id.et_category_name);
        Button addCategoryButton = findViewById(R.id.btn_add_category);

        addCategoryButton.setOnClickListener(v -> {
            String categoryName = categoryNameInput.getText().toString().trim();

            if (categoryName.isEmpty()) {
                Toast.makeText(this, "Category name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isInserted = dbHelper.insertCategory(categoryName);

            if (isInserted) {
                Toast.makeText(this, "Category added successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity and return to MainActivity
            } else {
                Toast.makeText(this, "Failed to add category", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
