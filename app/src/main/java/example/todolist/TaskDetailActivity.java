package example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        dbHelper = new DatabaseHelper(this);

        taskId = getIntent().getIntExtra("TASK_ID", -1);

        TextView taskTitle = findViewById(R.id.tv_task_title);
        TextView taskDescription = findViewById(R.id.tv_task_description);
        TextView taskDeadline = findViewById(R.id.tv_task_deadline);

        // Load task details
        Task task = dbHelper.getTask(taskId);
        taskTitle.setText(task.getTitle());
        taskDescription.setText(task.getDescription());
        taskDeadline.setText(task.getDeadline());

        // Update Button
        findViewById(R.id.btn_update_task).setOnClickListener(v -> {
            Intent intent = new Intent(TaskDetailActivity.this, UpdateTaskActivity.class);
            intent.putExtra("TASK_ID", taskId); // Pass the task ID to UpdateTaskActivity
            startActivity(intent);
        });

        // Delete Button
        findViewById(R.id.btn_delete_task).setOnClickListener(v -> {
            dbHelper.deleteTask(taskId);
            finish();
        });

        // Mark as Done
        findViewById(R.id.btn_mark_done).setOnClickListener(v -> {
            dbHelper.markTaskAsDone(taskId);
            finish();
        });
    }
}
