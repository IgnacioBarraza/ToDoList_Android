package example.todolist;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> todoList = new ArrayList<>();
        todoList.add("Estudiar para PEP");
        todoList.add("Avanzar codigo proyecto");
        todoList.add("Ir al supermercado");
        todoList.add("Hacer Ejercicio");
        todoList.add("Ordenar escritorio");
        todoList.add("Entrevista técnica");

        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = todoList.get(position);
            Intent intent = new Intent(MainActivity.this, ToDoDetailActivity.class);
            intent.putExtra("TODO_ITEM", selectedItem);
            startActivity(intent);
        });
    }
}