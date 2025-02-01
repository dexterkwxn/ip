import java.io.File; 
import java.io.BufferedWriter; 
import java.io.BufferedReader; 
import java.io.FileWriter; 
import java.io.FileReader; 

public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveList(TaskList taskList) {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();  
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public TaskList loadList() {
        File file = new File(this.filePath);
        TaskList taskList = new TaskList();
        
        if (!file.exists()) {
            System.out.println("No existing task file found.");
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    public Task parseTask(String s) throws Exception {
        try {
            String[] parts = s.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String taskName = parts[2];

            Task task;
            if (type.equals("T")) {
                task = new Todo(taskName);
            } else if (type.equals("D")) {
                task = new Deadline(taskName, parts[3]);
            } else if (type.equals("E")) {
                task = new Event(taskName, parts[3], parts[4]);
            } else {
                return null;
            }

            if (isDone) {
                task.mark();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupted task entry: " + s);
            return null;
        }
    }
}
