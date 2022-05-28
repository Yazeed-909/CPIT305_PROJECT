/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author yzeed
 */
public class FXMLDocumentController implements Initializable {

    //new var added
    File f = getFile();
    PrintWriter pen = getPen2Write(f);
    my_task bitcoin_thread, ethereum_thread, polkadot_thread, dogecoin_thread;
    String currentDate;
    protected UserINFO userinfo;
    private ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);
    @FXML
    private Button Buy_BITCOIN, Buy_ETHEREUM, Buy_POLKADOT, Buy_DOGECOIN,
            Sell_BITCOIN, Sell_ETHEREUM, Sell_POLKADOT, Sell_DOGECOIN, logout, Add_balance;
    @FXML
    private Label label, Name = new Label(""), Email = new Label(""), Gender = new Label(""), Birthday = new Label(""), Date_of_join = new Label(""),
            Price_bitcoin, Price_dogecoin, Price_polkadot, Price_ethereum, bitcoin_1h, bitcoin_24h, bitcoin_7d,
            bitcoin_cap, bitcoin_volume, ethereum_1h, ethereum_24h, ethereum_7d,
            ethereum_cap, ethereum_volume, polkadot_1h, polkadot_24h, polkadot_7d,
            polkadot_cap, polkadot_volume, dogecoin_1h, dogecoin_24h, dogecoin_7d,
            dogecoin_cap, dogecoin_volume, Price_bitcoin_tab2, Price_ethereum_tab2, Price_polkadot_tab2, Price_doge_tab2;
    @FXML
    private TextField TextField_BITCOIN, TextField_ETHEREUM, TextField_POLKADOT, TextField_DOGECOIN;

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
    private final NumberFormat Formater = NumberFormat.getCurrencyInstance(Locale.US);
    private final ArrayList<my_task> list_of_tasks = new ArrayList<>();
    DecimalFormat dec = new DecimalFormat("#0.00");

    @FXML
    private void Add_balance(ActionEvent event) {
        
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setContentText("Balance amount: ");
            dialog.setTitle("");
            dialog.setHeaderText("Please enter your Balance");
            dialog.showAndWait();
            if (isNumeric(dialog.getResult())) {
                Wallet_balance.setText(dialog.getResult());
                DB db=DB.getInstance();
                db.UpdateBalance(Double.parseDouble(dialog.getResult()), userinfo.getUserid());
                
            }else{
                Alert a=new Alert(Alert.AlertType.WARNING,"enter a numeric value");
                a.showAndWait();
            }
        

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void logout(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene Scene = new Scene(root);
        stage.setScene(Scene);
        stage.show();
        bitcoin_thread.cancel();
        ethereum_thread.cancel();
        polkadot_thread.cancel();
        dogecoin_thread.cancel();

    }

    @FXML
    private void Tap_Nav(MouseEvent event
    ) {

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
            this.Name.setText(userinfo.getName());
            this.Email.setText(userinfo.getEmail());
            this.Birthday.setText(userinfo.getDOB().toString());
            this.Gender.setText(userinfo.getGender());
            Tab_Pane.getSelectionModel().select(3);

        }

    }

    @FXML
    private void Buy_BITCOIN(MouseEvent event
    ) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Buy_BITCOIN.isPressed() && !"".equals(TextField_BITCOIN.getText())) {

            Dilog.setContentText("buy " + TextField_BITCOIN.getText() + " of Bitcoin \n in date: " + currentDate);
            Dilog.showAndWait();
            //insert to transaction list
            Transactions_list.getItems().addAll("buy " + TextField_BITCOIN.getText() + " of Bitcoin \n in date: " + currentDate + "\n---------------------");

            pen.print("buy " + TextField_BITCOIN.getText() + " of Bitcoin \n in date: " + currentDate + "\n---------------------\n");
            pen.flush();
            TextField_BITCOIN.clear();

        }
    }

    @FXML
    private void Sell_BITCOIN(MouseEvent event
    ) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Sell_BITCOIN.isPressed() && !"".equals(TextField_BITCOIN.getText())) {
            System.out.println("sell");

            Dilog.setContentText("sell " + TextField_BITCOIN.getText() + " of Bitcoin \ndate:" + currentDate);
            Dilog.showAndWait();
            //insert to transaction list
            Transactions_list.getItems().addAll("sell " + TextField_BITCOIN.getText() + " of Bitcoin \ndate: " + currentDate + "\n---------------------");
            pen.print("sell " + TextField_BITCOIN.getText() + " of Bitcoin \ndate: " + currentDate + "\n---------------------\n");
            pen.flush();
            TextField_BITCOIN.clear();
        }
    }

    @FXML
    private void Buy_ETHEREUM(MouseEvent event
    ) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Buy_ETHEREUM.isPressed() && !"".equals(TextField_ETHEREUM.getText())) {

            Dilog.setContentText("buy " + TextField_ETHEREUM.getText() + " of Ethereum \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("buy " + TextField_ETHEREUM.getText() + " of Ethereum \n in date: " + currentDate + "\n---------------------");
            pen.print("buy " + TextField_ETHEREUM.getText() + " of Ethereum \n in date: " + currentDate + "\n---------------------\n");
            pen.flush();
            TextField_ETHEREUM.clear();
        }
    }

    @FXML
    private void Sell_ETHEREUM(MouseEvent event
    ) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Sell_ETHEREUM.isPressed() && !"".equals(TextField_ETHEREUM.getText())) {

            Dilog.setContentText("sell " + TextField_ETHEREUM.getText() + " of Ethereum \ndate:" + currentDate);
            Dilog.showAndWait();
            //insert to transaction list
            Transactions_list.getItems().addAll("sell " + TextField_ETHEREUM.getText() + " of Ethereum \ndate: " + currentDate + "\n---------------------");
            pen.print("sell " + TextField_ETHEREUM.getText() + " of Ethereum \ndate: " + currentDate + "\n---------------------\n");
            pen.flush();
            TextField_ETHEREUM.clear();
        }
    }

    @FXML
    private void Buy_POLKADOT(MouseEvent event
    ) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Buy_POLKADOT.isPressed() && !"".equals(TextField_POLKADOT.getText())) {

            Dilog.setContentText("buy " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("buy " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate + "\n---------------------");
            pen.print("buy " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate + "\n---------------------\n");
            pen.flush();
            TextField_POLKADOT.clear();

        }

    }

    @FXML
    private void Sell_POLKADOT(MouseEvent event
    ) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Sell_POLKADOT.isPressed() && !"".equals(TextField_POLKADOT.getText())) {
            Dilog.setContentText("sell " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("sell " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate + "\n---------------------");
            pen.print("sell " + TextField_POLKADOT.getText() + " of Polkadot \n in date: " + currentDate + "\n---------------------\n");
            pen.flush();
            TextField_ETHEREUM.clear();

        }
    }

    @FXML
    private void Buy_DOGECOIN(MouseEvent event
    ) {
        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);
        if (Buy_DOGECOIN.isPressed() && !"".equals(TextField_DOGECOIN.getText())) {

            Dilog.setContentText("buy " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("buy " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate + "\n---------------------");
            pen.print("buy " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate + "\n---------------------\n");
            pen.flush();
            TextField_DOGECOIN.clear();

        }
    }

    @FXML
    private void Sell_DOGECOIN(MouseEvent event
    ) {

        Alert Dilog = new Alert(Alert.AlertType.INFORMATION);
        Dilog.setHeaderText(null);

        if (Sell_DOGECOIN.isPressed() && !"".equals(TextField_DOGECOIN.getText())) {

            Dilog.setContentText("sell " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate);
            Dilog.showAndWait();

            //insert to transaction list
            Transactions_list.getItems().addAll("sell " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate + "\n---------------------");
            pen.print("sell " + TextField_DOGECOIN.getText() + " of Dogecoin \n in date: " + currentDate + "\n---------------------\n");
            pen.flush();
            TextField_DOGECOIN.clear();

        }

    }

    public void refreshBitcoin() {

        bitcoin_thread = new my_task(bitcoin);
        Price_bitcoin.textProperty().bind(bitcoin_thread.messageProperty());
        Price_bitcoin_tab2.textProperty().bind(bitcoin_thread.messageProperty());

        bitcoin_thread.valueProperty().addListener((ObservableValue<? extends Coin> observable, Coin oldValue, Coin newValue) -> {

            //Price_bitcoin.setText(Formater.format(newValue.getPrice()));
            ChangeColorText(bitcoin_1h, newValue.getChange_in_1h());

            bitcoin_1h.setText(dec.format(newValue.getChange_in_1h()));

            ChangeColorText(bitcoin_24h, newValue.getChange_in_24h());

            bitcoin_24h.setText(dec.format(newValue.getChange_in_24h()));

            ChangeColorText(bitcoin_7d, newValue.getChange_in_7d());

            bitcoin_7d.setText(dec.format(newValue.getChange_in_7d()));

            bitcoin_cap.setText(Formater.format(newValue.getMarket_Cap()));

            bitcoin_volume.setText(Formater.format(newValue.getTotal_Volume()));
        });

        new Thread(bitcoin_thread).start();
    }

    public void refreshEthereum() {
        ethereum_thread = new my_task(ethereum);
        Price_ethereum.textProperty().bind(ethereum_thread.messageProperty());
        Price_ethereum_tab2.textProperty().bind(ethereum_thread.messageProperty());

        ethereum_thread.valueProperty().addListener((ObservableValue<? extends Coin> observable, Coin oldValue, Coin newValue) -> {

            // Price_ethereum.setText(Formater.format(newValue.getPrice()));
            ChangeColorText(ethereum_1h, newValue.getChange_in_1h());

            ethereum_1h.setText(dec.format(newValue.getChange_in_1h()));

            ChangeColorText(ethereum_24h, newValue.getChange_in_24h());

            ethereum_24h.setText(dec.format(newValue.getChange_in_24h()));

            ChangeColorText(ethereum_7d, newValue.getChange_in_7d());

            ethereum_7d.setText(dec.format(newValue.getChange_in_7d()));

            ethereum_cap.setText(Formater.format(newValue.getMarket_Cap()));

            ethereum_volume.setText(Formater.format(newValue.getTotal_Volume()));
        });

        new Thread(ethereum_thread).start();
    }

    public void refreshPolkadot() {
        polkadot_thread = new my_task(polkadot);
        Price_polkadot.textProperty().bind(polkadot_thread.messageProperty());
        Price_polkadot_tab2.textProperty().bind(polkadot_thread.messageProperty());

        polkadot_thread.valueProperty().addListener((ObservableValue<? extends Coin> observable, Coin oldValue, Coin newValue) -> {
            //Price_polkadot.setText(Formater.format(newValue.getPrice()));

            ChangeColorText(polkadot_1h, newValue.getChange_in_1h());

            polkadot_1h.setText(dec.format(newValue.getChange_in_1h()));

            ChangeColorText(polkadot_24h, newValue.getChange_in_24h());

            polkadot_24h.setText(dec.format(newValue.getChange_in_24h()));

            ChangeColorText(polkadot_7d, newValue.getChange_in_7d());

            polkadot_7d.setText(dec.format(newValue.getChange_in_7d()));

            polkadot_cap.setText(Formater.format(newValue.getMarket_Cap()));

            polkadot_volume.setText(Formater.format(newValue.getTotal_Volume()));
        });

        new Thread(polkadot_thread).start();
    }

    public void refreshDogecoin() {
        dogecoin_thread = new my_task(dogecoin);
        Price_dogecoin.textProperty().bind(dogecoin_thread.messageProperty());
        Price_doge_tab2.textProperty().bind(dogecoin_thread.messageProperty());

        dogecoin_thread.valueProperty().addListener((ObservableValue<? extends Coin> observable, Coin oldValue, Coin newValue) -> {
            // Price_dogecoin.setText(Formater.format(newValue.getPrice()));

            ChangeColorText(dogecoin_1h, newValue.getChange_in_1h());

            dogecoin_1h.setText(dec.format(newValue.getChange_in_1h()));

            ChangeColorText(dogecoin_24h, newValue.getChange_in_24h());

            dogecoin_24h.setText(dec.format(newValue.getChange_in_24h()));

            ChangeColorText(dogecoin_7d, newValue.getChange_in_7d());

            dogecoin_7d.setText(dec.format(newValue.getChange_in_7d()));

            dogecoin_cap.setText(Formater.format(newValue.getMarket_Cap()));

            dogecoin_volume.setText(Formater.format(newValue.getTotal_Volume()));
        });

        new Thread(dogecoin_thread).start();
    }

    public void ChangeColorText(Label lable, double value) {

        if (value >= 0) {
            lable.setTextFill(Paint.valueOf("00AF31"));
        } else {
            lable.setTextFill(Paint.valueOf("B70D0D"));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        refreshBitcoin();
        refreshDogecoin();
        refreshEthereum();
        refreshPolkadot();

    }

    public File getFile() {

        try {
            f = new File("log.txt");
            f.createNewFile();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return f;
    }

    public PrintWriter getPen2Write(File f) {
        try {
            pen = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return pen;
    }

}
