/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Validates.Validate;
import Model.Task;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class TaskController {

    private ArrayList<Task> listTasks = new ArrayList<>();

    public TaskController() {
    }

    public boolean addTask(String name, int taskTypeId, String date, double planFrom, double planTo, String asignee, String reviewer) {
        int id = 0;
        if (listTasks.isEmpty()) {
            id = 1;
        } else {
            id = listTasks.get(listTasks.size() - 1).getId() + 1;
        }
        listTasks.add(new Task(id, name, taskTypeId, date, planFrom, planTo, asignee, reviewer));
        return true;
    }

    public boolean deleteTask(int id) {
        for (Task task : listTasks) {
            if (task.getId() == id) {
                listTasks.remove(task);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Task> getDataTasks() {
        return listTasks;
    }
}
