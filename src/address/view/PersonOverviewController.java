package address.view;

import org.controlsfx.dialog.Dialogs;

import address.MainApp;
import address.model.Person;
import address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	/*
	 * һЩʵ�����������ʱ��������ͼ�еı�ǩ.��Щ���Ժ�һЩ������һ������� @FXML ע��.
	 * �����fxml�ļ�����˽�����Ժ�˽�з�����˵�Ǳ����.
	 * ����һ�ж���fxml�ļ������ú�֮��, Ӧ�ó������fxml�ļ�������ʱ�Զ��������Щ����
	 */
	//����fxml�ļ���Ҫ���ʵ����Ժͷ���������� @FXML ע��.ʵ����,ֻ����˽�е�����²���Ҫ, ���������Ǳ���˽�в�����ע���ǵķ�ʽ����!
	@FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * initialize() ������fxml�ļ��������ʱ���Զ�����. ��ʱ, ���е�FXML���Զ�Ӧ�ѱ���ʼ��
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	// ��������ʹ��setCellValueFactory(...) ��ȷ��Ϊ�ض���ʹ��Person�����ĳ������
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // �����������.
        showPersonDetails(null);

        // ����ѡ��ĸı䣬��Ӧ��ʾ
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *setMainApp(...) ���뱻 MainApp �����
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * ɾ����ť
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }

    /**
     * �½���ť
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * �༭��ť
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
}