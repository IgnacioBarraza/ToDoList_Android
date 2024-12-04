package example.todolist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        dbHelper = new DatabaseHelper(this);
        categoryId = getIntent().getIntExtra("CATEGORY_ID", -1);

        EditText taskTitle = findViewById(R.id.et_task_title);
        EditText taskDescription = findViewById(R.id.et_task_description);
        EditText taskDeadline = findViewById(R.id.et_task_deadline);

        Button addTaskButton = findViewById(R.id.btn_save_task);
        addTaskButton.setOnClickListener(v -> {
            String title = taskTitle.getText().toString();
            String description = taskDescription.getText().toString();
            String deadline = taskDeadline.getText().toString();

            dbHelper.addTask(title, description, deadline, categoryId);
            finish();
        });
    }
}
