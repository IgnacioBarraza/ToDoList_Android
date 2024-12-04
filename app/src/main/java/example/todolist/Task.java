package example.todolist;

public class Task {
    private int id;
    private String title;
    private String description;
    private String deadline;
    private boolean isDone;

    public Task(int id, String title, String description, String deadline, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    // Optionally, override toString() if you want a default string representation
    @Override
    public String toString() {
        return title;  // This will be used by ArrayAdapter if we don't override getView()
    }
}
