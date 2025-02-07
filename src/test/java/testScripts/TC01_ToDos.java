package testScripts;

import pages.Todos;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.TestDataReader;


public class TC01_ToDos extends BaseTest {

    String addToDo;
    String editToDo;
    String listOfToDos;
    Todos todosPage;

    @BeforeMethod
    @Parameters("dataPath")
    public void dataReader(String dataPath) {
        TestDataReader.getTestDataFile(dataPath);// Load the test data file
        addToDo = TestDataReader.getTestData("addToDo");
        editToDo = TestDataReader.getTestData("editToDo");
        listOfToDos = TestDataReader.getTestData("listOfToDos");
        todosPage = new Todos(driver);// Initialize the page object
    }

    @Test
    public void addEditCompleteToDo() {
        todosPage.addToDos(addToDo);
        System.out.println("Verified adding Todos");

        todosPage.editToDos(editToDo);
        System.out.println("Verified editing Todos");

        todosPage.completeDoToItem(editToDo);
        System.out.println("Verified completing Todos");
    }

    @Test()
    public void deleteToDo() {
        todosPage.deleteToDoItemUnderEachTab(listOfToDos);
        System.out.println("Verified deleting Todos");
    }

    @Test()
    public void verifyViewToDoList() {
        todosPage.viewToDoList(listOfToDos);
        System.out.println("Verified view ToDo List");
    }

    @Test()
    public void filterToDos() {
        todosPage.filterToDo(listOfToDos);
        System.out.println("Verified filter Tabs");
    }

    @Test()
    public void clearCompleted() {
        todosPage.clearCompletedToDos(listOfToDos);
        System.out.println("Verified clearing completed Todos");
    }

    @Test()
    public void selectUnselectAllToDos() {
        todosPage.selectUnselectAllToDos(listOfToDos);
        System.out.println("Verified select/unselect all Todos");
    }

}
