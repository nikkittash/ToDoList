package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskDAO taskDAO;

    @GetMapping(value = "/tasks/")
    public List<Task> list() {
        return taskDAO.list();
    }

    @PostMapping(value = "/tasks/")
    public synchronized int add(Task task) {
        return taskDAO.add(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        return taskDAO.get(id);
    }

    @DeleteMapping("/tasks/{id}")
    public synchronized ResponseEntity delete(@PathVariable int id) {
        return taskDAO.delete(id);
    }

    @DeleteMapping("/tasks/")
    public synchronized String deleteAll() {
        return taskDAO.deleteAll();
    }

    @PutMapping("/tasks/{id}")
    public synchronized ResponseEntity change(Task requestTask, @PathVariable int id) {
          return taskDAO.change(requestTask, id);
    }
}
