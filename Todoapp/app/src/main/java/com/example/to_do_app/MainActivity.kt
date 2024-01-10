import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskDBHelper: TaskDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskDBHelper = TaskDBHelper(this)

        // Set up RecyclerView
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(taskDBHelper.getAllTasks())
        recyclerViewTasks.adapter = taskAdapter

        // Set up FloatingActionButton click listener
        fabAddTask.setOnClickListener {
            // Open a new activity or dialog to add a new task
            // You can use an Intent or any other method to navigate to the add task screen
        }
    }
}
