package example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        dbHelper = new DatabaseHelper(this);
        ListView taskListView = findViewById(R.id.lv_tasks);

        categoryName = getIntent().getStringExtra("CATEGORY_NAME");
        setTitle(categoryName);

        int categoryId = dbHelper.getCategoryId(categoryName);
        List<String> tasks = dbHelper.getTasksByCategory(categoryId);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        taskListView.setAdapter(adapter);

        taskListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            String taskName = tasks.get(position);
            Intent intent = new Intent(TaskListActivity.this, TaskDetailActivity.class);
            intent.putExtra("TASK_NAME", taskName);
            intent.putExtra("CATEGORY_ID", categoryId);
            startActivity(intent);
        });

        findViewById(R.id.btn_add_task).setOnClickListener(v -> {
            Intent intent = new Intent(TaskListActivity.this, AddTaskActivity.class);
            intent.putExtra("CATEGORY_ID", categoryId);
            startActivity(intent);
        });
    }
}
