// Import necessary packages
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

// Define a Task data class
data class Task(
        var id: Long = -1,
        var title: String,
        var description: String,
        var dueDate: Long,
        var priority: String,
        var category: String,
        var status: String
)

// Create a DBHelper class for managing the SQLite database
class TaskDBHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Create the tasks table
        db.execSQL(
                "CREATE TABLE $TABLE_NAME (" +
                        "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$COLUMN_TITLE TEXT," +
                        "$COLUMN_DESCRIPTION TEXT," +
                        "$COLUMN_DUE_DATE INTEGER," +
                        "$COLUMN_PRIORITY TEXT," +
                        "$COLUMN_CATEGORY TEXT," +
                        "$COLUMN_STATUS TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop the tasks table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert a new task into the database
    fun addTask(task: Task): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TITLE, task.title)
        values.put(COLUMN_DESCRIPTION, task.description)
        values.put(COLUMN_DUE_DATE, task.dueDate)
        values.put(COLUMN_PRIORITY, task.priority)
        values.put(COLUMN_CATEGORY, task.category)
        values.put(COLUMN_STATUS, task.status)

        // Inserting Row
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    // Retrieve all tasks from the database
    fun getAllTasks(): List<Task> {
        val taskList = mutableListOf<Task>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val task = Task(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                )
                taskList.add(task)
            } while (cursor.moveToNext())
        }

        // close the cursor
        cursor.close()
        return taskList
    }
}

// Usage example in an Activity or Fragment
val taskDBHelper = TaskDBHelper(this)

// Add a new task
val newTask = Task(
        title = "Complete Project",
        description = "Finish the task management app project",
        dueDate = SimpleDateFormat("yyyy-MM-dd").parse("2024-12-31").time,
        priority = "High",
        category = "Development",
        status = "New"
)
val taskId = taskDBHelper.addTask(newTask)

// Retrieve all tasks
val allTasks = taskDBHelper.getAllTasks()
