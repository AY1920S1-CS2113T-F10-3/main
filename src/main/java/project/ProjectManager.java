package project;

import payment.Payee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProjectManager {
    public Project currentProject;
    public LinkedHashMap<String, Project> projectmap;

    /**
     * Initialises a project manager instance to manage the projects.
     */
    public ProjectManager() {
        this.currentProject = null;
        this.projectmap = new LinkedHashMap<>();
    }

    /**
     * Adds a new project to the project map.
     * @param projectname Name of the project to add.
     * @return Returns the project object of the added project.
     */
    public Project addProject(String projectname) {
        Project newProject = new Project(projectname);
        projectmap.put(projectname, newProject);
        if (currentProject == null) {
            currentProject = newProject;
        }
        return newProject;
    }

    /**
     * Deletes a project in the project map.
     * @param projectname Name of the project to delete.
     * @return Returns the project object of the deleted project.
     */
    public Project deleteProject(String projectname) {
        Project deletedProject = projectmap.get(projectname);//TODO check if project exists
        if (currentProject == deletedProject) {
            currentProject = null;
        }
        projectmap.remove(projectname); //TODO assert projectname does not exist
        return deletedProject;
    }

    /**
     * Method to go to the project in the projectmap.
     * @param projectname Name of project to go to.
     * @return Returns the project object of the project to go to.
     */
    public Project gotoProject(String projectname) {
        currentProject = projectmap.get(projectname);
        return currentProject;
    }

    /**
     * Lists all projects in the projectmap.
     * @return Returns an ArrayList of projects.
     */
    public ArrayList<Project> listProjects() {
        ArrayList<Project> projectslist = new ArrayList<>();
        for (Project project: projectmap.values()){
            projectslist.add(project);
        }
        return projectslist;
    }

    //TODO --> adds spending for project when adding payments
    public void addSpending() {

    }

    //TODO --> subtracts spending for project when adding payments
    public static void subtractSpending() {

    }

    //TODO --> assign budget
    public void assignBudget() {

    }

    /**
     * Allocates budget to a project.
     * @param projectname Name of the project.
     * @param budget Budget allocated to the project.
     * @return Returns the Project object the budget is allocated to.
     */
    public Project allocateBudget(String projectname, double budget) {
        Project projectallocated = projectmap.get(projectname);
        projectmap.get(projectname).budget = budget;
        return projectallocated; //TODO --> allocates budget to a project
    }

    /**
     * Returns the current project being edited.
     * @return Returns current project.
     */
    public Project getCurrentProject() {
        return currentProject;
    }

    public HashMap<String, Payee> getCurrentProjectManagerMap(){
        return currentProject.managermap;
    }
}
