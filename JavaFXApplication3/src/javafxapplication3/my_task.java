/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication3;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.concurrent.Task;

/**
 *
 * @author yzeed
 */
public class my_task extends Task<Coin>{
    Coin coin;
    NumberFormat Formater=NumberFormat.getCurrencyInstance(Locale.US);

    public my_task(Coin coin) {
        this.coin=coin;
    }
    @Override
    protected Coin call() throws Exception {
        while(true) {
            coin.UpdateData();         
            updateValue(coin);
            updateMessage(Formater.format(coin.getPrice()));
            coin.print();
            Thread.sleep(4000);
    
        }
        
        
        
        
        
    }


    
   
}