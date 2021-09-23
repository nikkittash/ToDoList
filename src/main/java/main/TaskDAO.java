package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskDAO {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<Task>();
        for(Task item : taskIterable) {
            tasks.add(item);
        }
        return tasks;
    }

    public synchronized int add(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    public ResponseEntity get(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    public synchronized ResponseEntity delete(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    public synchronized String deleteAll() {
        taskRepository.deleteAll();
        return "To-do list is empty!";
    }

    public synchronized ResponseEntity change(Task requestTask, int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        optionalTask.get().setName(requestTask.getName());
        optionalTask.get().setDate(requestTask.getDate());
        taskRepository.save(optionalTask.get());
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }
}
