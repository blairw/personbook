package wang.blair.Personbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class PrimaryController {

    @FXML
    public TreeView<Object> myTreeView;

    public void initialize() {
        TreeItem rootItem = new TreeItem("Items");
        myTreeView.setRoot(rootItem);
        myTreeView.setShowRoot(false);
        
        // add 2 groups
        TreeItem group1 = new TreeItem(new PersonGroup("Group 1"));
        TreeItem group2 = new TreeItem(new PersonGroup("Group 2"));
        rootItem.getChildren().add(group1);
        rootItem.getChildren().add(group2);
        
        // add all the things
        group1.getChildren().add(new TreeItem(new Person("Andrew Bob")));
        group1.getChildren().add(new TreeItem(new Person("Charlie Dennis")));
        group1.getChildren().add(new TreeItem(new Person("Evan Foucault")));
        group2.getChildren().add(new TreeItem(new Person("Gary Habermas")));
        group2.getChildren().add(new TreeItem(new Person("Ivan Jung")));
        group2.getChildren().add(new TreeItem(new Person("Kevin Larry")));
        group2.getChildren().add(new TreeItem(new Person("Michael Noland")));
        
        myTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> userDidSelectTreeItem(newValue));
    }

    // adapted from https://stackoverflow.com/questions/37531533/javafx-treeview-return-selected-items
    private void userDidSelectTreeItem(TreeItem<Object> treeItemObject) {
        Object treeItem = treeItemObject.getValue();
        
        // Respond differently depending on Object class
        if (treeItem.getClass().equals(Person.class)) {
            Person treeItemPerson = (Person) treeItem;
            System.out.println(treeItemPerson.getFullName());
        }
        
        if (treeItem.getClass().equals(PersonGroup.class)) {
            System.out.println("This is a group");
        }
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
