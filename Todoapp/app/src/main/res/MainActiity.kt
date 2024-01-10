// MainActivity.kt

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var taskDBHelper: TaskDBHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var addTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskDBHelper = TaskDBHelper(this)
        recyclerView = findViewById(R.id.recyclerViewTasks)
        addTaskButton = findViewById(R.id.btnAddTask)

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // Add Task button click listener
        addTaskButton.setOnClickListener {
            // Add your logic to handle the button click, e.g., open a dialog for adding a new task
        }

        // Populate RecyclerView with tasks
        val tasks = taskDBHelper.getAllTasks()
        val adapter = TaskAdapter(tasks)
        recyclerView.adapter = adapter
    }
}
