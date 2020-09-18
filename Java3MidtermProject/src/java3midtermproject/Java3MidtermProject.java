// Mickie Blair
// Java 3 - Midterm Project
// Northwind Database Query Tool

package java3midtermproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Java3MidtermProject extends Application {
        
    // Connection to db
    private Connection connection;
        
    //controls
    private MenuBar menuBar;
    private Menu fileMenu;
    private MenuItem exitItem;
    private Menu ordersMenu;
    private MenuItem orderTotals;
    private MenuItem orderDetails;
    private Menu customersMenu;
    private MenuItem byState;
    private Menu employeesMenu;
    private MenuItem birthdaysByYear;
    
    private TextField ordTotalsOrdNumber;
    private Button submitTotals;
    private TextField ordDetailsOrdNumber;
    private Button submitDetails;
    private ComboBox stateCombo;
    private Button submitState;
    private TextField year;
    private Button submitYear;
    
    
    // containers
    private BorderPane all;
    private VBox initialBox;
    private VBox queryBox;
    private VBox orderTotalQuery;
    private VBox orderDetailsQuery;
    private VBox customerByStateQuery;
    private VBox employeeBirthdaysQuery;
  
    
    @Override
    public void start(Stage primaryStage) {
        // create Menu
        createMenu(primaryStage);
        
        //create scene
        createScene();
        
        //initialize DB
        initializeDB();
        
        
        all = new BorderPane();
        all.setTop(menuBar);
        all.setLeft(queryBox);
        all.setCenter(initialBox);
        
        Scene scene = new Scene(all, 900, 500);
        scene.getStylesheets().add("style.css");
        
        primaryStage.setTitle("Java 3 Midterm Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Initialize Database
     */
    private void initializeDB(){
        try {
            // Load the JDBC driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver loaded");

            // Establish a connection
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Data/Northwind.mdb");
            System.out.println("Database connected");
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
        
    }

    /**
     * Create Scene
     */
    public void createScene(){
        //Initial Box
        Image logo = new Image("logo.gif");
        ImageView logoView = new ImageView(logo);
        
        initialBox = new VBox();
        initialBox.setId("borderLeft");
        initialBox.getChildren().addAll(logoView);
        initialBox.setAlignment(Pos.CENTER);
        
        //Query Box
        queryBox = new VBox();
        queryBox.setId("queryBox");
        
        Label queryTitleLabel = new Label();
        queryTitleLabel.setId("queryTitle");
        
        queryTitleLabel.setText("Northwind Database Query Tool");
        
        queryBox.getChildren().addAll(queryTitleLabel);
      
    }

    /**
     * Create Menu
     * @param stage primary Stage
     */
    public void createMenu(Stage stage){
        // Menu Bar
        menuBar = new MenuBar();
        
        // File Menu
        fileMenu = new Menu("File");
        exitItem = new MenuItem("Exit");
        fileMenu.getItems().add(exitItem);
        
        exitItem.setOnAction(event ->{
            stage.close();
        });
        
        
        // Orders Menu
        ordersMenu = new Menu("Orders");
        
        orderTotals = new MenuItem("Order Totals");
        orderTotals.setOnAction(event ->{
            displayOrderTotals();
            all.setCenter(initialBox);
        });
        
        orderDetails = new MenuItem("Order Details");
        orderDetails.setOnAction(event ->{
            displayOrderDetails();
            all.setCenter(initialBox);
        });
        
        ordersMenu.getItems().addAll(orderTotals, orderDetails);
        
        // Customers 
        customersMenu = new Menu("Customers");
        byState = new MenuItem("By State");
        byState.setOnAction(event ->{
            displayCustomersByState();
            all.setCenter(initialBox);
        });
                
        customersMenu.getItems().addAll(byState);
        
        // Employees
        employeesMenu = new Menu("Employees");
        birthdaysByYear = new MenuItem("Birthdays By Year");
        birthdaysByYear.setOnAction(event ->{
            displayEmpBirthdaysByYear();
            all.setCenter(initialBox);
        });
                
        employeesMenu.getItems().addAll(birthdaysByYear);
        
        menuBar.getMenus().addAll(fileMenu, ordersMenu, customersMenu, employeesMenu);
    }
    
    /**
     * Display Order Totals
     */
    public void displayOrderTotals(){
        orderTotalQuery = new VBox();
        orderTotalQuery.setId("queryBox");
        Label totalsLabel = new Label();
        totalsLabel.setText("Order Totals By Order Number");
        totalsLabel.setId("queryTitle");
        
        VBox totalsVBox = new VBox();
        Label ordTotalLabel = new Label();
        ordTotalLabel.setText("Order Number :");
        ordTotalLabel.setId("fieldLabel");
        
        HBox totalsQueryInput = new HBox();
        
        ordTotalsOrdNumber = new TextField();
        submitTotals = new Button("Submit");
        
        Label errorLabel = new Label();
        errorLabel.setId("error");
        errorLabel.setPadding(new Insets(5,0,0,0));
        
        submitTotals.setOnAction(event ->{
            try
            {
                int queryInput = Integer.parseInt(ordTotalsOrdNumber.getText());
                getOrderTotal();
                ordTotalsOrdNumber.setText("");
            }
            catch (NumberFormatException ex)
            {
                errorLabel.setText("Valid Order Number Required: Digits Only");
                all.setCenter(initialBox);
            }
            
        });
        
        ordTotalsOrdNumber.setOnKeyReleased((final KeyEvent keyEvent) -> {
            if(!isValidNumber(ordTotalsOrdNumber.getText())) 
            {
                errorLabel.setText("Invalid Order Number: Digits Only");
                submitTotals.setDisable(true);
                all.setCenter(initialBox);
            }
            else
            {
                errorLabel.setText("");
                submitTotals.setDisable(false);
                all.setCenter(initialBox);
            }
        });
        
        
        totalsQueryInput.getChildren().addAll(ordTotalsOrdNumber, submitTotals);
        totalsQueryInput.setSpacing(15);
        
        totalsVBox.getChildren().addAll(ordTotalLabel, totalsQueryInput, errorLabel);
        totalsVBox.setPadding(new Insets(10, 0, 0, 0));
        totalsVBox.setSpacing(5);
        
        orderTotalQuery.getChildren().addAll(totalsLabel, totalsVBox);
        
        all.setLeft(orderTotalQuery);
    }
    
    /**
     * Display Order Details
     */
    public void displayOrderDetails(){
        orderDetailsQuery = new VBox();
        orderDetailsQuery.setId("queryBox");
        Label detailsLabel = new Label();
        detailsLabel.setText("Order Details By Order Number");
        detailsLabel.setId("queryTitle");
        
        VBox detailsVBox = new VBox();
        Label ordDetailsLabel = new Label();
        ordDetailsLabel.setText("Order Number :");
        ordDetailsLabel.setId("fieldLabel");
        
        HBox detailsQueryInput = new HBox();
        
        ordDetailsOrdNumber = new TextField();
        submitDetails = new Button("Submit");
        
        Label errorLabel = new Label();
        errorLabel.setId("error");
        errorLabel.setPadding(new Insets(5,0,0,0));
        
        submitDetails.setOnAction(event ->{
            try
            {
                int queryInput = Integer.parseInt(ordDetailsOrdNumber.getText());
                getOrderDetails();
                ordDetailsOrdNumber.setText("");
            }
            catch (NumberFormatException ex)
            {
                errorLabel.setText("Valid Order Number Required: Digits Only");
                all.setCenter(initialBox);
            }
            
        });
        
        ordDetailsOrdNumber.setOnKeyReleased((final KeyEvent keyEvent) -> {
            if(!isValidNumber(ordDetailsOrdNumber.getText())) 
            {
                errorLabel.setText("Invalid Order Number: Digits Only");
                submitDetails.setDisable(true);
                all.setCenter(initialBox);
            }
            else
            {
                errorLabel.setText("");
                submitDetails.setDisable(false);
                all.setCenter(initialBox);
            }
        });
              
        detailsQueryInput.getChildren().addAll(ordDetailsOrdNumber, submitDetails);
        detailsQueryInput.setSpacing(15);
        
        detailsVBox.getChildren().addAll(ordDetailsLabel, detailsQueryInput);
        detailsVBox.setPadding(new Insets(10, 0, 0, 0));
        detailsVBox.setSpacing(5);
        
        orderDetailsQuery.getChildren().addAll(detailsLabel, detailsVBox, errorLabel);
        
        all.setLeft(orderDetailsQuery);
    }
    
    /**
     * Display Customers By State
     */
    public void displayCustomersByState(){
        customerByStateQuery = new VBox();
        customerByStateQuery.setId("queryBox");
        Label byStateLabel = new Label();
        byStateLabel.setText("Customers By State");
        byStateLabel.setId("queryTitle");
        
        VBox byStateVBox = new VBox();
        Label stateComboLabel = new Label();
        stateComboLabel.setText("State :");
        stateComboLabel.setId("fieldLabel");
        
        HBox stateQueryInput = new HBox();
        
        stateCombo = new ComboBox();
        stateCombo.getItems().addAll("AK", "AL", "AZ", "AR", "CA",
                                    "CO", "CT", "DE", "FL", "GA",
                                    "HI", "ID", "IL", "IN", "IA",
                                    "KS", "KY", "LA", "ME", "MD",
                                    "MA", "MI", "MN", "MS", "MO",
                                    "MT", "NE", "NV", "NH", "NJ",
                                    "NM", "NY", "NC", "ND", "OH",
                                    "OK", "OR", "PA", "RI", "SC",
                                    "SD", "TN", "TX", "UT", "VT",
                                    "VA", "WA", "WV", "WI", "WY");
        stateCombo.setVisibleRowCount(10);
        stateCombo.setValue("AK");
        
        submitState = new Button("Submit");
        submitState.setOnAction(event ->{
            getCities();
        });
        
        stateQueryInput.getChildren().addAll(stateCombo, submitState);
        stateQueryInput.setSpacing(15);
        
        byStateVBox.getChildren().addAll(stateComboLabel, stateQueryInput);
        byStateVBox.setPadding(new Insets(10, 0, 0, 0));
        byStateVBox.setSpacing(5);
        
        
        customerByStateQuery.getChildren().addAll(byStateLabel, byStateVBox );
        
        all.setLeft(customerByStateQuery);
    }
    
    /**
     * Display Employee Birthdays By Year
     */
    public void displayEmpBirthdaysByYear(){
        employeeBirthdaysQuery = new VBox();
        employeeBirthdaysQuery.setId("queryBox");
        Label birthdayTitleLabel = new Label();
        birthdayTitleLabel.setText("Employee Birthdays By Year");
        birthdayTitleLabel.setId("queryTitle");
                
        VBox birthdayVBox = new VBox();
        Label birthdayLabel = new Label();
        birthdayLabel.setText("Year :");
        birthdayLabel.setId("fieldLabel");
        
        HBox birthdayQueryInput = new HBox();
        
        year = new TextField();
        submitYear = new Button("Submit");
        
        Label errorLabel = new Label();
        errorLabel.setId("error");
        errorLabel.setPadding(new Insets(5,0,0,0));
        
        submitYear.setOnAction(event ->{
            try
            {
                int queryInput = Integer.parseInt(year.getText());
                getBirthdays();
                year.setText("");
            }
            catch (NumberFormatException ex)
            {
                errorLabel.setText("Valid Year Required: Digits Only");
                all.setCenter(initialBox);
            }
            
        });
        
        year.setOnKeyReleased((final KeyEvent keyEvent) -> {
            if(!isValidNumber(year.getText())) 
            {
                errorLabel.setText("Valid Year Required: Digits Only");
                submitYear.setDisable(true);
                all.setCenter(initialBox);
            }
            else
            {
                errorLabel.setText("");
                submitYear.setDisable(false);
                all.setCenter(initialBox);
            }
        });
      
        birthdayQueryInput.getChildren().addAll(year, submitYear);
        birthdayQueryInput.setSpacing(15);
        
        birthdayVBox.getChildren().addAll(birthdayLabel, birthdayQueryInput);
        birthdayVBox.setPadding(new Insets(10, 0, 0, 0));
        birthdayVBox.setSpacing(5);
        
                
        employeeBirthdaysQuery.getChildren().addAll(birthdayTitleLabel, birthdayVBox, errorLabel);
        
        all.setLeft(employeeBirthdaysQuery);
    }
    
    /**
     * Get Order Total
     */
    public void getOrderTotal(){
        // create table   
        TableView <OrderTotal> orderTotalTable = new TableView<>();
        ArrayList <OrderTotal> totalList = new ArrayList<>();
        ObservableList <OrderTotal> data;
        
        orderTotalTable.setPlaceholder(new Label("Order Number: " + ordTotalsOrdNumber.getText() + " - Not Found"));
        
        // Create Columns
        TableColumn orderNumberColumn = new TableColumn("Order Number");
        orderNumberColumn.setMinWidth(135);
        orderNumberColumn.setId("alignLeft");
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));

        TableColumn totalColumn = new TableColumn("Order Total");
        totalColumn.setMinWidth(120);
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        orderTotalTable.getColumns().addAll(orderNumberColumn, totalColumn);
        
        try
        {
            // Prepare Statement 
            String queryString = "SELECT OrderID, SUM (quantity * unitPrice) as total "
                    + "FROM [Order Details] "
                    + "WHERE OrderId = ? "
                    + "GROUP BY OrderID ";

            // Create a statement
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);

            //add conditions
            preparedStatement.setString(1, ordTotalsOrdNumber.getText());

            //Get result set
            ResultSet tableResults = preparedStatement.executeQuery();
                        
            //Get table data for textArea
            while(tableResults.next()){
                OrderTotal orderTotal = new OrderTotal();
                orderTotal.setOrderNumber(tableResults.getObject(1).toString());
                orderTotal.setTotal(String.format("$ %.2f" , tableResults.getObject(2))); 
                totalList.add(orderTotal);
            }
            data = FXCollections.observableArrayList(totalList);
            orderTotalTable.setItems(data);
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        catch(Exception ex){
            
        }
       
        all.setCenter(orderTotalTable);
        
    }
    
    /**
     * Get Order Details
     */
    public void getOrderDetails(){
        //create header box
        Label orderNumberLabel = new Label("Order Number:");
        orderNumberLabel.setId("headerLabel");
        Label orderNumberResult = new Label();
        HBox orderNumberBox = new HBox();
        orderNumberBox.getChildren().addAll(orderNumberLabel, orderNumberResult);
        orderNumberBox.setPadding(new Insets(5, 0, 0, 0));
        
        Label orderDateLabel = new Label("Order Date:");
        orderDateLabel.setId("headerLabel");
        Label orderDateResult = new Label();
        HBox dateBox = new HBox();
        dateBox.getChildren().addAll(orderDateLabel, orderDateResult);

        Label freightChargeLabel = new Label("Freight Charge:");
        freightChargeLabel.setId("headerLabel");
        Label freightChargeResult = new Label();
        HBox freightBox = new HBox();
        freightBox.getChildren().addAll(freightChargeLabel, freightChargeResult);
        freightBox.setPadding(new Insets(0, 0, 5, 0));
        
        VBox headerBox = new VBox();
        headerBox.getChildren().addAll(orderNumberBox, dateBox, freightBox);
        headerBox.setId("borderLeft");
        headerBox.setPadding(new Insets(0, 0, 0, 5));
        
        //get header information
        try
            {
                // Prepare Statement for header
                String headerQuery = "SELECT OrderDate, Freight FROM Orders WHERE"
                        + " OrderID = ?";

                 // Create a statement for header
                PreparedStatement preparedStatementHeader = connection.prepareStatement(headerQuery);

                //add conditions for header
                preparedStatementHeader.setString(1, ordDetailsOrdNumber.getText());

                //Get result set for header
                ResultSet headerResults = preparedStatementHeader.executeQuery();
        
                //Get table data for header
                while(headerResults.next()){
                    orderNumberResult.setText(ordDetailsOrdNumber.getText());
                    Date date=headerResults.getDate(1);
                    DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
                    String formattedDate=df.format(date);
                    orderDateResult.setText(formattedDate);
                    freightChargeResult.setText(String.format("$ %.2f" , headerResults.getObject(2)));
                }   
                
                
            }
            catch(SQLException ex)
            {
                 ex.printStackTrace();
            }  
        
        //create table
        TableView <OrderDetail> orderDetailsTable = new TableView<>();
        ArrayList <OrderDetail> detailsList = new ArrayList<>();
        ObservableList <OrderDetail> data;
        
        orderDetailsTable.setPlaceholder(new Label("Order Number: " 
                + ordDetailsOrdNumber.getText() + " - Not Found"));
       
        TableColumn productColumn = new TableColumn("Product");
        productColumn.setId("alignLeft");
        productColumn.setMinWidth(250);
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));

        TableColumn unitPriceColumn = new TableColumn("Unit Price");
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        
        TableColumn quantityColumn = new TableColumn("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn discountColumn = new TableColumn("Discount");
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));

        orderDetailsTable.getColumns().addAll(productColumn, unitPriceColumn, quantityColumn, discountColumn);
        
        //get table information
        try
        {
            // Prepare Statement 
            String queryDetails = "SELECT Products.ProductName, [Order Details].unitPrice, "
                            + "[Order Details].quantity,  [Order Details].discount " 
                            + "FROM [Order Details] " 
                            + "INNER JOIN Products on [Order Details].ProductID=Products.ProductID " 
                            + "WHERE [Order Details].orderID = ?;";

             // Create a statement
            PreparedStatement preparedStatement = connection.prepareStatement(queryDetails);

            //add conditions
            preparedStatement.setString(1, ordDetailsOrdNumber.getText());

            //Get result set
            ResultSet tableResults = preparedStatement.executeQuery();
            
            //Get table data for textArea
            while(tableResults.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(tableResults.getObject(1).toString());
                orderDetail.setUnitPrice(String.format("$ %.2f", tableResults.getObject(2))); 
                orderDetail.setQuantity(tableResults.getObject(3).toString());
                double discount = 100 * Double.parseDouble(tableResults.getObject(4).toString());
                orderDetail.setDiscount(String.format("%.0f %%", discount));
                detailsList.add(orderDetail);
            }
            data = FXCollections.observableArrayList(detailsList);
            orderDetailsTable.setItems(data);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        all.setCenter(orderDetailsTable);
        
        VBox allOrderDetailsBox = new VBox();
        
        allOrderDetailsBox.getChildren().addAll(headerBox, orderDetailsTable);
        
        all.setCenter(allOrderDetailsBox);
    }
    
    /**
     * Get Cities
     */
    public void getCities(){
        TableView <Customer> stateTable = new TableView<>();
        ArrayList <Customer> customerList = new ArrayList<>();
        ObservableList <Customer> data;
        stateTable.setPlaceholder(new Label("No Customers in " + stateCombo.getValue().toString() + " Found"));
        
        // Create Columns
        TableColumn nameColumn = new TableColumn("Customer Name");
        nameColumn.setId("alignLeft");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn cityColumn = new TableColumn("City");
        cityColumn.setId("alignLeft");
        cityColumn.setMinWidth(150);
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        
        stateTable.getColumns().addAll(nameColumn, cityColumn);

        try
        {
            // Prepare Statement 
            String queryString = "SELECT ContactName, City FROM Customers WHERE"
                    + " Region = ?";
            
             // Create a statement
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);

            //add conditions
            preparedStatement.setString(1, stateCombo.getValue().toString());

            //Get result set
            ResultSet tableResults = preparedStatement.executeQuery();
                        
            //Get table data for textArea
            while(tableResults.next()){
                Customer customer = new Customer();
                customer.setName(tableResults.getObject(1).toString());
                customer.setCity(tableResults.getObject(2).toString()); 
                customerList.add(customer);
            }
            data = FXCollections.observableArrayList(customerList);
            stateTable.setItems(data);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        cityColumn.setSortType(TableColumn.SortType.ASCENDING);
        stateTable.getSortOrder().add(cityColumn);
        all.setCenter(stateTable);
    }
    
    /**
     * Get Birthdays
     */
    public void getBirthdays(){
        TableView <Employee> birthdaysTable = new TableView<>();
        ArrayList <Employee> birthdayList = new ArrayList<>();
        ObservableList <Employee> data;
        birthdaysTable.setPlaceholder(new Label("No Employees Found For the Year " + year.getText()));
        
        // Create Columns
        TableColumn lastNameColumn = new TableColumn("Last Name");
        lastNameColumn.setId("alignLeft");
        lastNameColumn.setMinWidth(150);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn firstNameColumn = new TableColumn("First Name");
        firstNameColumn.setId("alignLeft");
        firstNameColumn.setMinWidth(150);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        
        birthdaysTable.getColumns().addAll(lastNameColumn, firstNameColumn);
        
        try
        {
            // Prepare Statement 
            String queryString = "SELECT LastName, FirstName FROM Employees WHERE"
                    + " YEAR(BirthDate) = ?";

            // Create a statement
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);

            //add conditions
            preparedStatement.setString(1, year.getText());

            //Get result set
            ResultSet tableResults = preparedStatement.executeQuery();
            
            //Get table data for textArea
            while(tableResults.next()){
                Employee employee = new Employee();
                employee.setLastName(tableResults.getObject(1).toString());
                employee.setFirstName(tableResults.getObject(2).toString()); 
                birthdayList.add(employee);
            }
            data = FXCollections.observableArrayList(birthdayList);
            birthdaysTable.setItems(data);
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
                
        lastNameColumn.setSortType(TableColumn.SortType.ASCENDING);
        birthdaysTable.getSortOrder().add(lastNameColumn);
        all.setCenter(birthdaysTable);
    }
    
    /**
    * Validation for Zip
    * @param input
    * @return Boolean
    */
   private boolean isValidNumber(String input)
   {       
       for(Character c : input.toCharArray())
       {
           if(!Character.isDigit(c)) return false;
       }
       
       return true;
   }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
