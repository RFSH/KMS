package controllers;

import javang.Scope;
import knowledgebase.Project;
import knowledgebase.ProjectCatalog;

import java.util.List;

public class ProjectListController {

    @Scope
    public List<Project> searchProjects(String title){
        return ProjectCatalog.getInstance().findProjects(title);
    }
}
