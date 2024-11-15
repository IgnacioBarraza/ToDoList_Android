package example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ToDoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        // Retrieve the To-Do item passed from MainActivity
        String todoItem = getIntent().getStringExtra("TODO_ITEM");

        // Display the To-Do item in a TextView
        TextView textView = findViewById(R.id.textViewDetail);
        textView.setText(todoItem);

        // Set up Back Button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity and return to MainActivity
            }
        });
    }
}
