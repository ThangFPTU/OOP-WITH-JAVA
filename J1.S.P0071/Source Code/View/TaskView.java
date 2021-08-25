/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.TaskController;
import Validates.Validate;
import Model.Task;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class TaskView {

    TaskController control = new TaskController();
    public void displayMenu() {
        System.out.println("======== Task Programe ========");
        System.out.println("1. Add Task");
        System.out.println("2. Delete Task");
        System.out.println("3. Display Task");
        System.out.println("4. Exit");
    }

    public void menuView() {
        Validate validate = new Validate();

        while (true) {
            displayMenu();
            int choice = validate.inputIntLimit("Your choice: ",
                    "Input must in range[1-4]!", 1, 4);
            switch (choice) {
                case 1:
                    addTaskView();
                    break;
                case 2:
                    deleteTaskView();
                    break;
                case 3:
                    showTasks();
                    break;
                case 4:
                    return;
            }
        }
    }

    public void addTaskView() {
        System.out.println("\n-------- Add Task --------");
        Validate validate = new Validate();
        String name = validate.inputString("Enter Requirement Name: ",
                "Name is required!", "[a-zA-Z ]+");
        int taskTypeId = validate.inputIntLimit("Enter Task Type: ",
                "Must in range[1-4]", 1, 4);
        String date = validate.inputDate("Enter Date: ", "Invalid format!",
                "dd/MM/yyyy");
        double planFrom = validate.inputDoubleLimit("Enter From: ",
                "Invalid time!",
                8, 17);
        double planTo = validate.inputDoubleLimit("Enter To: ", "Invalid time!",
                planFrom, 17.5);
        String asignee = validate.inputString("Enter Assignee: ",
                "Invalid assignee!", "[a-zA-Z ]+");
        String reviewer = validate.inputString("Enter Reviewer: ",
                "Invalid reviewer!", "[a-zA-Z ]+");
        boolean condition = control.addTask(name, taskTypeId, date, planFrom, planTo, asignee,
                reviewer);
        
        if(condition == true){
            System.out.println("Add successful!");
        }else{
            System.err.println("Add fail!");
        }
    }
    
    public void deleteTaskView(){
        System.out.println("-------- Delete Task --------");
        Validate validate = new Validate();
        ArrayList<Task> listTasks = control.getDataTasks();
        int id = validate.inputIntLimit("Id: ", "Invalid Id!", 1,
                Integer.MAX_VALUE);
           boolean condition = control.deleteTask(id);
           if(condition ==true)
               System.out.println("Delete successful!");
           else
               System.err.println("Not exist Id!");
    }
    
    public void showTasks(){
        ArrayList<Task> listTasks = control.getDataTasks();
        if (listTasks.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.printf("\n%-5s%-15s%-15s%-15s%-15s%-15s%-15s\n",
                "ID", "Name", "Task Type", "Date", "Time", "Assign", "Reviewer");
        for (int i = 0; i < listTasks.size(); i++) {
            Task task = listTasks.get(i);
            System.out.printf("%-5d%-15s%-15s%-15s%-15.1f%-15s%-15s\n",
                    task.getId(),
                    task.getName(),
                    task.getTaskTypeId(),
                    task.getDate(),
                    task.getPlanTo() - task.getPlanFrom(),
                    task.getAsignee(),
                    task.getReviewer()
            );
        }
    }

    public static void main(String[] args) {
        TaskView view = new TaskView();
        view.menuView();
    }
}
