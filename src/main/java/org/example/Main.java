package org.example;

import com.structurizr.Workspace;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.util.WorkspaceUtils;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Workspace workspace = new Workspace("Getting Started", "This is a model of my software system.");
        Model model = workspace.getModel();

        Person customer = model.addPerson("Customer", "A customer of a shop.");
        Person artist = model.addPerson("Artist", "A content creator.");
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "My software system.");
        customer.uses(softwareSystem, "Buys products");
        artist.uses(softwareSystem, "Uploads images");

        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext", "An example of a System Context diagram.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        uploadWorkspaceToStructurizr(workspace);
    }

    private static void uploadWorkspaceToStructurizr(Workspace workspace){
        File file = new File("workspace.json");
        try {
            WorkspaceUtils.saveWorkspaceToJson(workspace, file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}