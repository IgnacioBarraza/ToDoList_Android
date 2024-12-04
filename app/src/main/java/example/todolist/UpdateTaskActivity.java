package example.todolist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateTaskActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int taskId;

    private EditText titleInput;
    private EditText descriptionInput;
    private EditText deadlineInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        dbHelper = new DatabaseHelper(this);

        taskId = getIntent().getIntExtra("TASK_ID", -1);

        titleInput = findViewById(R.id.et_task_title);
        descriptionInput = findViewById(R.id.et_task_description);
        deadlineInput = findViewById(R.id.et_task_deadline);

        // Load current task details
        Task task = dbHelper.getTask(taskId);
        if (task != null) {
            titleInput.setText(task.getTitle());
            descriptionInput.setText(task.getDescription());
            deadlineInput.setText(task.getDeadline());
        }

        // Save updated task
        Button saveButton = findViewById(R.id.btn_save_task);
        saveButton.setOnClickListener(v -> {
            String updatedTitle = titleInput.getText().toString();
            String updatedDescription = descriptionInput.getText().toString();
            String updatedDeadline = deadlineInput.getText().toString();

            dbHelper.updateTask(taskId, updatedTitle, updatedDescription, updatedDeadline);

            finish(); // Go back to TaskDetailActivity
        });
    }
}
