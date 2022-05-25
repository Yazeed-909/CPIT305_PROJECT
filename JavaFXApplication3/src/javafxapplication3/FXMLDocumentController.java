/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.jar.Attributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValueBase;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author yzeed
 */
public class FXMLDocumentController implements Initializable {

//new var added
    File f = getFile();
    PrintWriter pen = getPen2Write(f);

    String currentDate = getCurrentDate();
    @FXML
    private Button Buy_BITCOIN, Buy_ETHEREUM, Buy_POLKADOT, Buy_DOGECOIN,
            Sell_BITCOIN, Sell_ETHEREUM, Sell_POLKADOT, Sell_DOGECOIN, logout;
    @FXML
    private Label label, Name = new Label(""), Email = new Label(""), Gender = new Label(""), Birthday = new Label(""), Date_of_join = new Label(""),
            Price_bitcoin, Price_dogecoin, Price_polkadot, Price_ethereum, bitcoin_1h, bitcoin_24h, bitcoin_7d,
            bitcoin_cap, bitcoin_volume, ethereum_1h, ethereum_24h, ethereum_7d,
            ethereum_cap, ethereum_volume, polkadot_1h, polkadot_24h, polkadot_7d,
            polkadot_cap, polkadot_volume, dogecoin_1h, dogecoin_24h, dogecoin_7d,
            dogecoin_cap, dogecoin_volume;
    @FXML
    private TextField TextField_BITCOIN, TextField_ETHEREUM, TextField_POLKADOT, TextField_DOGECOIN;
    @FXML
    private RadioButton BRadio_4sec, BRadio_8sec, BRadio_16sec, BRadio_32sec;
    @FXML
    private ImageView Market_Button, BuyAndSell_Button, Button_Wallet, Button_Account;
    @FXML
    private Label Wallet_balance;
    @FXML
    private Tab Tab_Account, Tab_Wallet, Tab_BuyAndSell, Tab_Market;
    @FXML
    private ListView<String> Transactions_list;
    @FXML
    private TabPane Tab_Pane;
    Coin bitcoin = new Coin("bitcoin");
    Coin ethereum = new Coin("ethereum");
    Coin polkadot = new Coin("polkadot");
    Coin dogecoin = new Coin("dogecoin");
    private NumberFormat Formater = NumberFormat.getCurrencyInstance(Locale.US);
    private final ArrayList<my_task> list_of_tasks = new ArrayList<>();

    @FXML
    public void Set_userInfo(String Name, String Email, String Birthday) {

        this.Name.setText(Name);
        this.Email.setText(Email);
        this.Birthday.setText(Birthday);

    }

    @FXML
    private void logout(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene Scene = new Scene(root);
        stage.setScene(Scene);
        stage.show();

    }

    @FXML
    private void Tap_Nav(MouseEvent event) {

        if (Market_Button.isPressed()) {

            Tab_Pane.getSelectionModel().select(0);

        }
        if (BuyAndSell_Button.isPressed()) {

            Tab_Pane.getSelectionModel().select(1);

        }
        if (Button_Wallet.isPressed()) {

            Tab_Pane.getSelectionModel().select(2);

        }

        if (Button_Account.isPressed()) {

            Tab_Pane.getSelectionModel().select(3);

        }

    }

    @FXML
    private void Buy_BITCOIN(MouseEvent event) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Buy_BITCOIN.isPressed() && !"".equals(TextField_BITCOIN.getText())) {

            Dilog.setContentText("buy " + TextField_BITCOIN.getText() + " of Bitcoin \n in date: " + currentDate);
            Dilog.showAndWait();
            //insert to transaction list
            Transactions_list.getItems().addAll("buy " + TextField_BITCOIN.getText() + " of Bitcoin \n in date: " + currentDate + "\n---------------------");

            pen.print("buy " + TextField_BITCOIN.getText() + " of Bitcoin \n in date: " + currentDate + "\n---------------------");
            pen.flush();
            TextField_BITCOIN.clear();

        }
    }

    @FXML
    private void Sell_BITCOIN(MouseEvent event) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Sell_BITCOIN.isPressed() && !"".equals(TextField_BITCOIN.getText())) {
            System.out.println("sell");

            Dilog.setContentText("sell " + TextField_BITCOIN.getText() + " of Bitcoin \ndate:" + currentDate);
            Dilog.showAndWait();
            //insert to transaction list
            Transactions_list.getItems().addAll("sell " + TextField_BITCOIN.getText() + " of Bitcoin \ndate: " + currentDate + "\n---------------------");
            pen.print("sell " + TextField_BITCOIN.getText() + " of Bitcoin \ndate: " + currentDate + "\n---------------------");
            pen.flush();
            TextField_BITCOIN.clear();
        }
    }

    @FXML
    private void Buy_ETHEREUM(MouseEvent event) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Buy_ETHEREUM.isPressed() && !"".equals(TextField_ETHEREUM.getText())) {

            Dilog.setContentText("buy " + TextField_ETHEREUM.getText() + " of Ethereum \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("buy " + TextField_ETHEREUM.getText() + " of Ethereum \n in date: " + currentDate + "\n---------------------");
            pen.print("buy " + TextField_ETHEREUM.getText() + " of Ethereum \n in date: " + currentDate + "\n---------------------");
            pen.flush();
            TextField_ETHEREUM.clear();
        }
    }

    @FXML
    private void Sell_ETHEREUM(MouseEvent event) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Sell_ETHEREUM.isPressed() && !"".equals(TextField_ETHEREUM.getText())) {

            Dilog.setContentText("sell " + TextField_ETHEREUM.getText() + " of Ethereum \ndate:" + currentDate);
            Dilog.showAndWait();
            //insert to transaction list
            Transactions_list.getItems().addAll("sell " + TextField_ETHEREUM.getText() + " of Ethereum \ndate: " + currentDate + "\n---------------------");
            pen.print("sell " + TextField_ETHEREUM.getText() + " of Ethereum \ndate: " + currentDate + "\n---------------------");
            pen.flush();
            TextField_ETHEREUM.clear();
        }
    }

    @FXML
    private void Buy_POLKADOT(MouseEvent event) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Buy_POLKADOT.isPressed() && !"".equals(TextField_POLKADOT.getText())) {

            Dilog.setContentText("buy " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("buy " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate + "\n---------------------");
            pen.print("buy " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate + "\n---------------------");
            pen.flush();
            TextField_POLKADOT.clear();

        }

    }

    @FXML
    private void Sell_POLKADOT(MouseEvent event) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Sell_POLKADOT.isPressed() && !"".equals(TextField_POLKADOT.getText())) {
            Dilog.setContentText("sell " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("sell " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate + "\n---------------------");
            pen.print("sell " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate + "\n---------------------");
            pen.flush();
            TextField_ETHEREUM.clear();

        }
    }

    @FXML
    private void Buy_DOGECOIN(MouseEvent event) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Buy_DOGECOIN.isPressed() && !"".equals(TextField_DOGECOIN.getText())) {

            Dilog.setContentText("buy " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("buy " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate + "\n---------------------");
            pen.print("buy " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate + "\n---------------------");
            pen.flush();
            TextField_DOGECOIN.clear();

        }
    }

    @FXML
    private void Sell_DOGECOIN(MouseEvent event) {

        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);

        if (Sell_DOGECOIN.isPressed() && !"".equals(TextField_DOGECOIN.getText())) {

            Dilog.setContentText("sell " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("sell " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate + "\n---------------------");
            pen.print("sell " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate + "\n---------------------");
            pen.flush();
            TextField_DOGECOIN.clear();

        }

    }

    @FXML
    public void Update_speed(ActionEvent e) {
//        int amount=0;
//        if (BRadio_4sec.isSelected()) {
//           amount=4000;
//        }
//        if (BRadio_8sec.isSelected()) {
//            amount=8000;
//        }
//        if (BRadio_16sec.isSelected()) {
//          amount=16000;
//        }
//        if (BRadio_32sec.isSelected()) {
//           amount=32000;
//        }
//         for (my_task list_of_task : list_of_tasks) {
//                list_of_task.setSpeed(amount);
//            }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        my_task bitcoin_thread = new my_task(bitcoin);

        Price_bitcoin.textProperty().bind(bitcoin_thread.messageProperty());
        new Thread(bitcoin_thread).start();

        my_task ethereum_thread = new my_task(ethereum);
        Price_ethereum.textProperty().bind(ethereum_thread.messageProperty());
        new Thread(ethereum_thread).start();

        my_task polkadot_thread = new my_task(polkadot);
        Price_polkadot.textProperty().bind(polkadot_thread.messageProperty());
        new Thread(polkadot_thread).start();

        my_task dogecoin_thread = new my_task(dogecoin);
        Price_dogecoin.textProperty().bind(dogecoin_thread.messageProperty());
        new Thread(dogecoin_thread).start();

        list_of_tasks.add(bitcoin_thread);
        list_of_tasks.add(ethereum_thread);
        list_of_tasks.add(polkadot_thread);
        list_of_tasks.add(dogecoin_thread);

    }

    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        return formatter.format(date);
    }

    public File getFile() {

        try {
            File f = new File("log.txt");
            f.createNewFile();

        } catch (Exception e) {
        }
        return f;
    }

    public PrintWriter getPen2Write(File f) {
        try {
            PrintWriter pen = new PrintWriter(f);
        } catch (Exception e) {
        }

        return pen;
    }
}
