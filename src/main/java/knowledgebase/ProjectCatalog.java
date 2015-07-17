package knowledgebase;

import java.util.List;

public class ProjectCatalog {
    private static ProjectCatalog instance;


    public List<Project> findProjects(String title){
        if(title.isEmpty()){
            return new ProjectDAO().getObjects();
        }
        return new ProjectDAO().getObjects("title", title);
    }


    public Project findProjectById(String id){
        return new ProjectDAO().getObject("id", id);
    }

    public static ProjectCatalog getInstance() {
        if (instance == null) {
            instance = new ProjectCatalog();
        }
        return instance;
    }
}
