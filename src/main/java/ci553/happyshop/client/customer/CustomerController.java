package ci553.happyshop.client.customer;

import ci553.happyshop.catalogue.Product;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerController {
    public CustomerModel cusModel;

    public void doAction(String action) throws SQLException, IOException {
        switch (action) {
            case "Search":
                cusModel.search();
                break;
            case "Cancel":
                cusModel.cancel();
                break;
            case "Check Out":
                cusModel.checkOut();
                break;
            case "OK & Close":
                cusModel.closeReceipt();
                break;
        }
    }
    // add the product selected by the customer into the trolley
    // the selected Product object is passed from the CustomerView to the Model
    public void addProductToTrolley(Product product) throws SQLException, IOException {
        cusModel.addToTrolley(product);
    }
    // Increase quantity
    public void increaseQuantity(Product product) {
        cusModel.increaseQuantity(product);
    }

    // Decrease quantity
    public void decreaseQuantity(Product product) {
        cusModel.decreaseQuantity(product);
    }

    // Remove item completely
    public void removeProduct(Product product) {
        cusModel.removeProduct(product);
    }
}
