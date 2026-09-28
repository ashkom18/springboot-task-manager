package com.example;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
@RestController @RequestMapping("/api/tasks")
public class TaskController {
 private final Map<Long,Task> tasks=new LinkedHashMap<>(); private final AtomicLong ids=new AtomicLong();
 @GetMapping public Collection<Task> all(){return tasks.values();}
 @PostMapping public Task create(@RequestBody TaskRequest r){long id=ids.incrementAndGet(); Task t=new Task(id,r.title(),r.description(),false);tasks.put(id,t);return t;}
 @PutMapping("/{id}") public Task update(@PathVariable long id,@RequestBody TaskRequest r){if(!tasks.containsKey(id))throw new NoSuchElementException();Task t=new Task(id,r.title(),r.description(),r.completed());tasks.put(id,t);return t;}
 @DeleteMapping("/{id}") public void delete(@PathVariable long id){tasks.remove(id);}
 record TaskRequest(String title,String description,boolean completed){}
}