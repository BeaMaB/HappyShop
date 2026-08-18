package ci553.happyshop.client.customer;

import ci553.happyshop.catalogue.Product;
import ci553.happyshop.utility.StorageLocation;
import ci553.happyshop.utility.UIStyle;
import ci553.happyshop.utility.WinPosManager;
import ci553.happyshop.utility.WindowBounds;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The CustomerView is separated into two sections by a line :
 *
 * 1. Search Page – Always visible, allowing customers to browse and search for products.
 * 2. the second page – display either the Trolley Page or the Receipt Page
 *    depending on the current context. Only one of these is shown at a time.
 */

public class CustomerView  {
    public CustomerController cusController;

    private final int WIDTH = UIStyle.customerWinWidth;
    private final int HEIGHT = UIStyle.customerWinHeight;
    private final int COLUMN_WIDTH = WIDTH / 2 - 10;

    private HBox hbRoot; // Top-level layout manager
    private VBox vbTrolleyPage;  //vbTrolleyPage and vbReceiptPage will swap with each other when need
    private VBox vbReceiptPage;
    private ListView<Product> obrLvProducts;
    private ObservableList<Product> obeProductList;
    private VBox vbSearchResult; // shows number of products found

    TextField tfSearchKeyword; // user typing in it (Search for ID and product name)

    //four controllers needs updating when program going on
    private ImageView ivProduct; //image area in searchPage
    private Label lbProductInfo;//product text info in searchPage
    private Label laSearchSummary; // shows number of products found
    private ListView<Product> lvTrolley;
    private ObservableList<Product> trolleyList;
    private Label lbTrolleyTotal;
    private TextArea taReceipt;//in receipt page

    // Holds a reference to this CustomerView window for future access and management
    // (e.g., positioning the removeProductNotifier when needed).
    private Stage viewWindow;

    public void start(Stage window) {
        VBox vbSearchPage = createSearchPage();
        vbTrolleyPage = CreateTrolleyPage();
        vbReceiptPage = createReceiptPage();

        // Create a divider line
        Line line = new Line(0, 0, 0, HEIGHT);
        line.setStrokeWidth(4);
        line.setStroke(Color.PINK);
        VBox lineContainer = new VBox(line);
        lineContainer.setPrefWidth(4); // Give it some space
        lineContainer.setAlignment(Pos.CENTER);

        hbRoot = new HBox(10, vbSearchPage, lineContainer, vbTrolleyPage); //initialize to show trolleyPage
        hbRoot.setAlignment(Pos.CENTER);
        hbRoot.setStyle(UIStyle.rootStyle);

        Scene scene = new Scene(hbRoot, WIDTH, HEIGHT);
        window.setScene(scene);
        window.setTitle("🛒 HappyShop Customer Client");
        WinPosManager.registerWindow(window,WIDTH,HEIGHT); //calculate position x and y for this window
        window.show();
        viewWindow=window;// Sets viewWindow to this window for future reference and management.
    }

    private VBox createSearchPage() {
        Label laPageTitle = new Label("Search by Product ID/Name");
        laPageTitle.setStyle(UIStyle.labelTitleStyle);

        // search Input (can take ID or Name)
        tfSearchKeyword = new TextField();
        tfSearchKeyword.setStyle(UIStyle.textFiledStyle);
        tfSearchKeyword.setPromptText("Enter ID or Product Name");
        tfSearchKeyword.setOnAction(actionEvent -> {
            try {
                cusController.doAction("Search");  //pressing enter can also do search
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Search button
        Button btnSearch = new Button("Search");
        btnSearch.setTooltip(new Tooltip("Search"));
        btnSearch.setStyle(UIStyle.buttonStyle);
        btnSearch.setOnAction(this::buttonClicked);

        // Search bar and button together
        HBox searchBox = new HBox(8, tfSearchKeyword, btnSearch);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        // Product found summary (below search bar)
        laSearchSummary = new Label("Search Summary");
        laSearchSummary.setStyle(UIStyle.labelStyle);

        HBox hbSummary = new HBox(laSearchSummary);
        hbSummary.setAlignment(Pos.CENTER);

        // Add to Trolley button
        Button btnAddToTrolley = new Button("Add to Trolley");
        btnAddToTrolley.setStyle(UIStyle.buttonStyle);
        btnAddToTrolley.setOnAction(this::buttonClicked);

        HBox hbBtn = new HBox(10, btnAddToTrolley);

        ivProduct = new ImageView("imageHolder.jpg");
        ivProduct.setFitHeight(60);
        ivProduct.setFitWidth(60);
        ivProduct.setPreserveRatio(true); // Image keeps its original shape and fits inside 60×60
        ivProduct.setSmooth(true); //make it smooth and nice-looking

        lbProductInfo = new Label("Thank you for shopping with us.");
        lbProductInfo.setWrapText(true);
        lbProductInfo.setMinHeight(Label.USE_PREF_SIZE);  // Allow auto-resize
        lbProductInfo.setStyle(UIStyle.labelMulLineStyle);

        // Create the default search result card
        HBox defaultCard = new HBox(5, ivProduct, lbProductInfo);
        defaultCard.setAlignment(Pos.CENTER_LEFT);
        defaultCard.setPrefHeight(150);
        vbSearchResult = new VBox(defaultCard);
        vbSearchResult.setAlignment(Pos.CENTER);

        vbSearchResult.setPrefHeight(150); // Slightly increased to fit the structured product data nicely
        vbSearchResult.setMinHeight(150);
        vbSearchResult.setMaxHeight(150);
        vbSearchResult.setStyle(UIStyle.labelMulLineStyle);

        obeProductList = FXCollections.observableArrayList();
        obrLvProducts = new ListView<>(obeProductList);
        obrLvProducts.setPrefHeight(HEIGHT - 100);
        obrLvProducts.setFixedCellSize(50);
        obrLvProducts.setStyle(UIStyle.listViewStyle);
        // start with message card only
        obrLvProducts.setVisible(false);
        obrLvProducts.setManaged(false);
        obrLvProducts.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);

                if (empty || product == null) {
                    setGraphic(null);
                    System.out.println("setCellFactory - empty item");
                } else {
                    String imageName = product.getProductImageName(); // Get image name (e.g. "0001.jpg")
                    String relativeImageUrl = StorageLocation.imageFolder + imageName;
                    // Get the full absolute path to the image
                    Path imageFullPath = Paths.get(relativeImageUrl).toAbsolutePath();
                    String imageFullUri = imageFullPath.toUri().toString();// Build the full image Url

                    ImageView ivPro;
                    try {
                        ivPro = new ImageView(new Image(imageFullUri, 50, 45, true, true)); // Attempt to load the product image
                    } catch (Exception e) {
                        // If loading fails, use a default image directly from the resources folder
                        ivPro = new ImageView(new Image("imageHolder.jpg", 50, 45, true, true)); // Directly load from resources
                    }

                    // basket button for adding the selected product directly to the trolley
                    Button btnBasket = new Button("🛒");
                    // add the selected product to the trolley when the basket button is clicked
                    btnBasket.setOnAction(e -> {
                        try {
                            cusController.addProductToTrolley(product);
                        } catch (SQLException | IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    // Product description
                    Label name = new Label(product.getProductDescription());
                    // Product price
                    Label price = new Label(String.format("£%.2f", product.getUnitPrice()));
                    // Name and price on the same row
                    HBox topRow = new HBox(15, name, price);
                    topRow.setAlignment(Pos.CENTER_LEFT);

                    // Stock availability
                    Label stock = new Label();

                    if (product.getStockQuantity() == 0) {
                        btnBasket.setDisable(true);
                        stock.setText("⛔ Out of Stock");
                        stock.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                    } else if (product.getStockQuantity() < 20) {
                        stock.setText("Only " + product.getStockQuantity() + " left");
                        stock.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                    } else {
                        stock.setText(product.getStockQuantity() + " left");
                        stock.setStyle("-fx-text-fill: green;");
                    }

                    // arrange all product details horizontally
                    // product information
                    VBox productInfo = new VBox(3, topRow, stock);
                    productInfo.setAlignment(Pos.CENTER_LEFT);
                    // arrange the image, product information and basket button in one row
                    HBox hbox = new HBox(10, ivPro, productInfo, btnBasket);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    // push the basket button to the far right
                    HBox.setHgrow(productInfo, Priority.ALWAYS);
                    setGraphic(hbox);  // Set the whole row content
                }
            }
        });

        // Combine everything into the VBox
        VBox vbSearchPage = new VBox(15, laPageTitle, searchBox, hbBtn, hbSummary, vbSearchResult, obrLvProducts);
        vbSearchPage.setPrefWidth(COLUMN_WIDTH);
        vbSearchPage.setAlignment(Pos.TOP_CENTER);
        vbSearchPage.setStyle("-fx-padding: 15px;");

        return vbSearchPage;
    }

    private VBox CreateTrolleyPage() {
        Label laPageTitle = new Label("🛒🛒  Trolley 🛒🛒");
        laPageTitle.setStyle(UIStyle.labelTitleStyle);
        trolleyList = FXCollections.observableArrayList();
        lvTrolley = new ListView<>(trolleyList);
        lbTrolleyTotal = new Label("Total: £0.00");
        lbTrolleyTotal.setStyle(UIStyle.labelStyle);
        lvTrolley.setPrefHeight(350);
        lvTrolley.setPrefWidth(COLUMN_WIDTH + 20);
        lvTrolley.setStyle(UIStyle.listViewStyle);

        lvTrolley.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);

                if (empty || product == null) {
                    setGraphic(null);
                    return;
                }

                Label id = new Label(product.getProductId()
                );
                Label name = new Label(
                        product.getProductDescription()
                );

                Label quantity = new Label(
                        "Qty: " + product.getOrderedQuantity()
                );

                Label itemTotal = new Label(
                        String.format("£%.2f", product.getUnitPrice() * product.getOrderedQuantity())
                );

                Button btnPlus = new Button("+");
                Button btnMinus = new Button("-");
                Button btnRemove = new Button("\uD83D\uDDD1\uFE0F");

                btnPlus.setOnAction(e -> {
                    cusController.increaseQuantity(product);
                });

                btnMinus.setOnAction(e -> {
                    cusController.decreaseQuantity(product);
                });

                btnRemove.setOnAction(e -> {
                    cusController.removeProduct(product);
                });

                HBox row = new HBox(5, id, name, quantity, btnMinus, btnPlus, btnRemove, itemTotal);
                row.setAlignment(Pos.CENTER_LEFT);
                HBox.setHgrow(name, Priority.ALWAYS);
                setGraphic(row);
            }
        });


        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(this::buttonClicked);
        btnCancel.setStyle(UIStyle.buttonStyle);

        Button btnCheckout = new Button("Check Out");
        btnCheckout.setOnAction(this::buttonClicked);
        btnCheckout.setStyle(UIStyle.buttonStyle);

        HBox hbBtns = new HBox(10, btnCancel,btnCheckout);
        hbBtns.setStyle("-fx-padding: 15px;");
        hbBtns.setAlignment(Pos.CENTER);

        vbTrolleyPage = new VBox(15, laPageTitle, lvTrolley, hbBtns);
        vbTrolleyPage.setPrefWidth(COLUMN_WIDTH);
        vbTrolleyPage.setAlignment(Pos.TOP_CENTER);
        vbTrolleyPage.setStyle("-fx-padding: 15px;");
        return vbTrolleyPage;
    }

    private VBox createReceiptPage() {
        Label laPageTitle = new Label("Receipt");
        laPageTitle.setStyle(UIStyle.labelTitleStyle);

        taReceipt = new TextArea();
        taReceipt.setEditable(false);
        taReceipt.setPrefSize(WIDTH/2, HEIGHT-50);

        Button btnCloseReceipt = new Button("OK & Close"); //btn for closing receipt and showing trolley page
        btnCloseReceipt.setStyle(UIStyle.buttonStyle);

        btnCloseReceipt.setOnAction(this::buttonClicked);

        vbReceiptPage = new VBox(15, laPageTitle, taReceipt, btnCloseReceipt);
        vbReceiptPage.setPrefWidth(COLUMN_WIDTH);
        vbReceiptPage.setAlignment(Pos.TOP_CENTER);
        vbReceiptPage.setStyle(UIStyle.rootStyleYellow);
        return vbReceiptPage;
    }


    private void buttonClicked(ActionEvent event) {
        try{
            Button btn = (Button)event.getSource();
            String action = btn.getText();
            if(action.equals("Add to Trolley")){
                // Get the product selected from the search results
                Product selectedProduct =
                        obrLvProducts.getSelectionModel().getSelectedItem();

                if (selectedProduct != null) {

                    // Show trolley page
                    showTrolleyOrReceiptPage(vbTrolleyPage);

                    // Add the selected product to the trolley
                    cusController.addProductToTrolley(selectedProduct);

                } else {
                    // No product has been selected
                    laSearchSummary.setText("Please select a product first");
                }
                return;
            }
            if(action.equals("OK & Close")){
                showTrolleyOrReceiptPage(vbTrolleyPage);
            }
            cusController.doAction(action);
        }
        catch(SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Clears the current product list and displays the default message card
    // The product list is hidden until the customer performs a search
    public void showDefaultSearchMessage() {

        obeProductList.clear();
        HBox defaultCard = new HBox(5, ivProduct, lbProductInfo);
        defaultCard.setAlignment(Pos.CENTER_LEFT);
        vbSearchResult.getChildren().setAll(defaultCard);

        // show message card
        vbSearchResult.setVisible(true);
        vbSearchResult.setManaged(true);

        // hide product list
        obrLvProducts.setVisible(false);
        obrLvProducts.setManaged(false);
    }

    // Displays the products returned from a search
    // The message card is hidden and the ListView is shown with the search results
    private void showProductList(ArrayList<Product> products) {
        obeProductList.clear();
        obeProductList.addAll(products);
        // hide message card
        vbSearchResult.setVisible(false);
        vbSearchResult.setManaged(false);

        // show product list
        obrLvProducts.setVisible(true);
        obrLvProducts.setManaged(true);
    }

    public void update(String imageName, String searchResult, ArrayList<Product> products, String trolley, String receipt) {

        // Update search summary text
        if (products != null && !products.isEmpty()) {
            laSearchSummary.setText(products.size() + " products found");
            showProductList(products);
        } else {
            laSearchSummary.setText("0 products found");
            ivProduct.setImage(new Image(imageName));
            lbProductInfo.setText(searchResult);
            showDefaultSearchMessage();
        }

        // Update trolley display
        taTrolley.setText(trolley);
        if (!receipt.equals("")) {
            showTrolleyOrReceiptPage(vbReceiptPage);
            taReceipt.setText(receipt);
        }
    }

    // Replaces the last child of hbRoot with the specified page.
    // the last child is either vbTrolleyPage or vbReceiptPage.
    private void showTrolleyOrReceiptPage(Node pageToShow) {
        int lastIndex = hbRoot.getChildren().size() - 1;
        if (lastIndex >= 0) {
            hbRoot.getChildren().set(lastIndex, pageToShow);
        }
    }

    WindowBounds getWindowBounds() {
        return new WindowBounds(viewWindow.getX(), viewWindow.getY(),
                  viewWindow.getWidth(), viewWindow.getHeight());
    }
}
