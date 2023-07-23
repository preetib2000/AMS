package com.gatech.ams;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;

public class AmsGUI extends Application {

    private Stage primaryStage;
    private double x = 800;
    private double y = 600;

    AirlineManagementSystem ams = null;

    //BorderPane borderPane = new BorderPane();


    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setScene(scene);

        ams = new AirlineManagementSystem();

        this.primaryStage = stage;
        stage.setResizable(true);
        stage.setX(x);
        stage.setY(y);
        primaryStage.setTitle("Airlines Management System!");

        //show home screen
        Scene homeScene = homeScreen();
        primaryStage.setScene(homeScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    private Scene homeScreen() {

        BorderPane borderPane = getLayout();
        VBox vbox = new VBox();
        borderPane.setCenter(vbox);
        Scene homeScene = new Scene(borderPane, x, y);

//        Button airlineButton = new Button("Airlines");
//        Button airportButton = new Button("Airports");
//        Button flightButton = new Button("Flights");
//        Button passengerButton = new Button("Passengers");
//        Button pilotButton = new Button("Pilots");
//        Button jetButton = new Button("Jets");
//        Button propButton = new Button("Props");
//        Button routeButton = new Button("Routes");
//        Button legButton = new Button("Legs");

//        airlineButton.setOnAction(event -> showAirlineScene());
//        airportButton.setOnAction(event -> showAirportScene());
//        flightButton.setOnAction(event -> showFlightScene());
//        passengerButton.setOnAction(event -> showPassengerScene());
//        pilotButton.setOnAction(event -> showPilotScene());
//        jetButton.setOnAction(event -> showJetScene());
//        propButton.setOnAction(event -> showPropScene());
//        routeButton.setOnAction(event -> showRouteScene());
//        legButton.setOnAction(event -> showLegScene());


//        vbox.getChildren().add(airlineButton);
//        vbox.getChildren().add(flightButton);
//        vbox.getChildren().add(airportButton);
//        vbox.getChildren().add(passengerButton);
//        vbox.getChildren().add(pilotButton);
//        vbox.getChildren().add(jetButton);
//        vbox.getChildren().add(propButton);
//        vbox.getChildren().add(routeButton);
//        vbox.getChildren().add(legButton);

        return homeScene;

    }

    private void creatShowScreen(VBox vBox){
        BorderPane borderPane = getLayout();
        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane, x, y);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private BorderPane getLayout() {
        BorderPane borderPane = new BorderPane();
        MenuBar mb = createMenu();
        borderPane.setTop(mb);
        return borderPane;
    }


    private MenuBar createMenu(){

        // create a menubar
        MenuBar mb = new MenuBar();

        Menu airlineMenu = new Menu("Airlines");
        Menu airportMenu = new Menu("Airports");
        Menu flightMenu = new Menu("Flights");
        Menu peopleMenu = new Menu("People");
        Menu aircraftMenu = new Menu("Aircrafts");
        Menu routeMenu = new Menu("Route Mgmt");


        // create menuitems for Airlines
        MenuItem m1 = new MenuItem("Show Airlines");
        MenuItem m2 = new MenuItem("Search Airlines");
        // add menu items to menu
        airlineMenu.getItems().add(m1);
        airlineMenu.getItems().add(m2);

        //create menu item for Airport
        MenuItem mAirport1 = new MenuItem("Show Airport");
        airportMenu.getItems().add(mAirport1);

        // create menuitems for Aircrafts
        MenuItem mAircraft1 = new MenuItem("Jets");
        MenuItem mAircraft2 = new MenuItem("Props");
        // add menu items to menu
        aircraftMenu.getItems().add(mAircraft1);
        aircraftMenu.getItems().add(mAircraft2);

        // create menuitems for People
        MenuItem mPeople1 = new MenuItem("Pilots");
        MenuItem mPeople2 = new MenuItem("Passengers");
        // add menu items to menu
        peopleMenu.getItems().add(mPeople1);
        peopleMenu.getItems().add(mPeople2);

        // create menuitems for Route Management
        MenuItem mRoute1 = new MenuItem("Routes");
        MenuItem mRoute2 = new MenuItem("Legs");
        // add menu items to menu
        routeMenu.getItems().add(mRoute1);
        routeMenu.getItems().add(mRoute2);


        // add menu to menubar
        mb.getMenus().add(airlineMenu);
        mb.getMenus().add(airportMenu);
        mb.getMenus().add(flightMenu);
        mb.getMenus().add(peopleMenu);
        mb.getMenus().add(aircraftMenu);
        mb.getMenus().add(routeMenu);

       airlineMenu.setOnAction(event -> showAirlineScene());

       airportMenu.setOnAction(event -> showAirportScene());

       mPeople1.setOnAction(actionEvent -> showPilotScene());
       mPeople2.setOnAction(actionEvent -> showPassengerScene());


       mAircraft1.setOnAction(actionEvent -> showJetScene());
       mAircraft2.setOnAction(actionEvent -> showPropScene());

        return  mb;

    }

    private void showHomeScreen(){
        Scene scene = homeScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //region Airline Section
    //Show airlines
    private void showAirlineScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Airline");
        Button delete = new Button("Remove Airline");
        Button show = new Button("Show Airlines");
        add.setOnAction(event -> addAirlineInfo());
        delete.setOnAction(event -> showHomeScreen());
        show.setOnAction(event -> showAirlineInfo());
        vbox.getChildren().addAll(add,delete,show);
        creatShowScreen(vbox);
    }
    //add airline screen
    private void addAirlineInfo(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        TextField name = new TextField();
        name.setPromptText("Enter Airline Name: ");
        TextField revenue = new TextField();
        revenue.setPromptText("Enter Airline Revenue: ");
        Button addAirline = new Button("Add Airline");
        Button backButton = new Button("Back to Airline scene");
        addAirline.setOnAction(event -> ams.createAirline(new Airline(name.getText(),Double.parseDouble(revenue.getText()))));
        backButton.setOnAction(event -> showAirlineScene());
        vbox.getChildren().addAll(name,revenue,addAirline,backButton);
        creatShowScreen(vbox);
    }
    //show airlines 
    private void showAirlineInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Label l = new Label("AirlineView");
        String s = ams.showAirlinesDetail();
        l.setText(s);
        Button backButton = new Button("Back to Airline scene");
        backButton.setOnAction(event -> showAirlineScene());
        vbox.getChildren().addAll(l,backButton);
        creatShowScreen(vbox);
    }
    //endregion

    //region Airport Section
    private void showAirportScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Airport");
        Button delete = new Button("Remove Airport");
        Button show = new Button("Show Airports");
        add.setOnAction(event -> addAirportInfo());
        delete.setOnAction(event -> showHomeScreen());
        show.setOnAction(event -> showAirportInfo());
        vbox.getChildren().addAll(add,delete,show);
        creatShowScreen(vbox);
    }
    private void addAirportInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        TextField code = new TextField();
        code.setPromptText("Enter Airport/IATA Code/: ");
        TextField name = new TextField();
        name.setPromptText("Enter Airport Name: ");
        TextField city = new TextField();
        city.setPromptText("Enter Airport City: ");
        TextField state = new TextField();
        state.setPromptText("Enter Airport State: ");
        TextField country = new TextField();
        country.setPromptText("Enter Airport Country: ");
        Button addAirport = new Button("Add Airport");
        Button backButton = new Button("Back to Airport scene");
        addAirport.setOnAction(event -> ams.createAirport(new Airport(code.getText(),name.getText(),city.getText(),state.getText(),country.getText())));
        backButton.setOnAction(event -> showAirportScene());
        vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }
    private void showAirportInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Label l = new Label("AirPortView");
        String s = ""; //ams.showAirportDetails(); Todo
        l.setText(s);
        Button backButton = new Button("Back to Airport scene");
        backButton.setOnAction(event -> showAirportScene());
        vbox.getChildren().addAll(l,backButton);
        creatShowScreen(vbox);
    }
    //endregion

    //region Passenger Section
    private void showPassengerScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Passenger");
        Button delete = new Button("Remove Passenger");
        Button show = new Button("Show Passengers");
        add.setOnAction(event -> addPassengerInfo());
        delete.setOnAction(event -> showHomeScreen());
        show.setOnAction(event -> showPassengerInfo());
        vbox.getChildren().addAll(add,delete,show);
        creatShowScreen(vbox);
    }
    private void showPassengerInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Label l = new Label("PassengerView");
        String s = ""; //ams.showPassengerDetails(); Todo
        l.setText(s);
        Button backButton = new Button("Back to Passenger scene");
        backButton.setOnAction(event -> showPassengerScene());
        vbox.getChildren().addAll(l,backButton);
        creatShowScreen(vbox);
    }
    private void addPassengerInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        TextField id = new TextField();
        id.setPromptText("Enter Identifier/: ");
        TextField fname = new TextField();
        fname.setPromptText("Enter First Name/: ");
        TextField lname = new TextField();
        lname.setPromptText("Enter Last Name: ");
        TextField ffmiles = new TextField();
        ffmiles.setPromptText("Enter Frequent Flier Miles: ");
        TextField pfunds = new TextField();
        pfunds.setPromptText("Enter Passenger funds: ");
        TextField location = new TextField();
        location.setPromptText("Enter Location: ");
        Button addPassenger = new Button("Add Passenger");
        Button backButton = new Button("Back to Passenger scene");
        //addPassenger.setOnAction(event -> ams.createPassenger(new Passenger(id.getText(),fname.getText(),lname.getText(),Integer.parseInt(ffmiles.getText()),Integer.parseInt(pfunds.getText()),location.getText()))));
        backButton.setOnAction(event -> showPassengerScene());
        vbox.getChildren().addAll(id,fname,lname,ffmiles,pfunds,location,addPassenger,backButton);
        creatShowScreen(vbox);
    }
    //endregion

    //region Flight Section
    private void showFlightScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Flight");
        Button delete = new Button("Remove Flight");
        Button show = new Button("Show Flights");
        add.setOnAction(event -> addflightInfo());
        delete.setOnAction(event -> showHomeScreen());
        show.setOnAction(event -> showFlightInfo());
        vbox.getChildren().addAll(add,delete,show);
        creatShowScreen(vbox);
    }
    private void addflightInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        TextField airlineName = new TextField();
        airlineName.setPromptText("Enter airline name: ");
        TextField flightNum = new TextField();
        flightNum.setPromptText("Enter flight number: ");
        TextField cost = new TextField();
        cost.setPromptText("Enter flight cost: ");
        TextField progress = new TextField();
        progress.setPromptText("Enter route progress: ");
        TextField routeName = new TextField();
        routeName.setPromptText("Enter route name: ");
        TextField nextTime = new TextField();
        nextTime.setPromptText("Enter next_status_change: ");
        TextField tailNum = new TextField();
        tailNum.setPromptText("Enter aircraft tail number: ");
        Button addFlight = new Button("Add Flight");
        Button backButton = new Button("Back to Flight scene");
        //addFlight.setOnAction(Event -> ams.createFlight(new Flight(flightNum.getText(),airlineName.getText(),)));
        backButton.setOnAction(event -> showFlightScene());
        vbox.getChildren().addAll(airlineName,flightNum,cost,progress,routeName,nextTime,tailNum,addFlight,backButton);
        creatShowScreen(vbox);
    }
    private void showFlightInfo(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Label l = new Label("FlightView");
        String s = ""; // ams.showFlightDetals(); Todo
        l.setText(s);
        Button backButton = new Button("Back to Flight scene");
        backButton.setOnAction(event -> showFlightScene());
        vbox.getChildren().addAll(l,backButton);
        creatShowScreen(vbox);
    }
    //endregion


    //pilot
    private void showPilotScene() {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Pilot");
        Button delete = new Button("Remove Pilot");
        Button show = new Button("Show Pilots");
        add.setOnAction(event -> addPilotInfo());
        delete.setOnAction(event -> showHomeScreen());
        show.setOnAction(event -> showPilotInfo());
        vbox.getChildren().addAll(add,delete,show);
        creatShowScreen(vbox);
    }

    private void showPilotInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }

    private void addPilotInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

        Button backButton = new Button("Back to Pilot scene");
        backButton.setOnAction(event -> showPilotScene());

        TextField identifier = new TextField();
        identifier.setPromptText("Enter personID: ");

        TextField fName = new TextField();
        fName.setPromptText("Enter first name: ");

        TextField lName = new TextField();
        lName.setPromptText("Enter last name: ");

        TextField taxID = new TextField();
        taxID.setPromptText("Enter taxID: ");

        TextField experience = new TextField();
        experience.setPromptText("Enter pilot experience: ");

        TextField location = new TextField();
        location.setPromptText("Enter location: ");

       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }


   
    //Aircraft screen for Jet and Prop
    private void showJetScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Jet");
        Button delete = new Button("Remove Jet");
        Button show = new Button("Show Jets");
        add.setOnAction(event -> addJetInfo());
        delete.setOnAction(event -> homeScreen());
        show.setOnAction(event -> showJetInfo());
        vbox.getChildren().addAll(add,delete,show);
        creatShowScreen(vbox);
    }
    private void addJetInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        TextField tailNum = new TextField();
        tailNum.setPromptText("Enter tail number: ");
        TextField seatCap = new TextField();
        seatCap.setPromptText("Enter seating capacity: ");
        TextField speed = new TextField();
        speed.setPromptText("Enter speed: ");
        TextField engines = new TextField();
        engines.setPromptText("Enter number of engines: ");
        Button addJet = new Button("Add Jet");
        Button backButton = new Button("Back to Airline scene");
        //addJet.setOnAction(event -> ams.createJet(new Airline(name.getText(),Double.parseDouble(revenue.getText()))));
        backButton.setOnAction(event -> showJetScene());
        vbox.getChildren().addAll(tailNum,seatCap,speed,engines, addJet,backButton);
        creatShowScreen(vbox);
    }

    private void showJetInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }

    
    private void showPropScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Prop");
        Button delete = new Button("Remove Prop");
        Button show = new Button("Show Props");

        add.setOnAction(event -> addPropInfo());
        delete.setOnAction(event -> homeScreen());
        show.setOnAction(event -> showPropInfo());

       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }

    private void showPropInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

     //   vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }

    private void addPropInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

        Button backButton = new Button("Back to Prop scene");
        backButton.setOnAction(event -> showPropScene());

        TextField tailNum = new TextField();
        tailNum.setPromptText("Enter tail number: ");

        TextField seatCap = new TextField();
        seatCap.setPromptText("Enter seating capacity: ");

        TextField speed = new TextField();
        speed.setPromptText("Enter speed: ");

        TextField engines = new TextField();
        engines.setPromptText("Enter number of engines: ");

        Label skids = new Label("Landing Skids: ");

        ComboBox<String> landingSkids = new ComboBox<String>();
        landingSkids.getItems().addAll("True", "False");


       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }


    
    //route and leg
    private void showRouteScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Route");
        Button delete = new Button("Remove Route");
        Button show = new Button("Show Routes");

        add.setOnAction(event -> addRouteInfo());
        delete.setOnAction(event -> homeScreen());
        show.setOnAction(event -> showRouteInfo());

       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }

    private void showRouteInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }

    private void addRouteInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

        Button backButton = new Button("Back to Route scene");
        backButton.setOnAction(event -> showRouteScene());

        TextField name = new TextField();
        name.setPromptText("Enter route name: ");

        TextField departure = new TextField();
        departure.setPromptText("Enter departure airport name: ");

        TextField hop = new TextField();
        hop.setPromptText("Enter first hop: ");


       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }
    

    private void showLegScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);
        Button add = new Button("Add Leg");
        Button delete = new Button("Remove Leg");
        Button show = new Button("Show Legs");

        add.setOnAction(event -> addLegInfo());
        delete.setOnAction(event -> homeScreen());
        show.setOnAction(event -> showLegInfo());

       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }

    private void showLegInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }
    private void addLegInfo() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(10);

        Button backButton = new Button("Back to Leg scene");
        backButton.setOnAction(event -> showLegScene());

        TextField departure = new TextField();
        departure.setPromptText("Enter departure airport name: ");

        TextField distance = new TextField();
        distance.setPromptText("Enter distance: ");

        TextField arrival = new TextField();
        arrival.setPromptText("Enter arrival airport name: ");


       // vbox.getChildren().addAll(code,name,city,state,country,addAirport,backButton);
        creatShowScreen(vbox);
    }

}