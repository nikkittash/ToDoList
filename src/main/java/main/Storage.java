package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static AtomicInteger currentId = new AtomicInteger();
    private static Hashtable<Integer, Task> tasks = new Hashtable<Integer, Task>();

    public static List<Task> getAllTasks() {
        ArrayList<Task> tasksList = new ArrayList<Task>();
        tasksList.addAll(tasks.values());
        return tasksList;
    }

    public static int addTask(Task task) {
        int id = currentId.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public static Task getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }

    public static String deleteTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
            return "Task " + taskId + " was deleted!";
        }
        return null;
    }

    public static String deleteAll() {
        if (!tasks.isEmpty()) {
            tasks.clear();
            return "All tasks was deleted!";
        }
        return "You don't have tasks yet!";
    }
}
