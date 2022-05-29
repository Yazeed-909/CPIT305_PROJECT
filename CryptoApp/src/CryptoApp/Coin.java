/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CryptoApp;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinFullData;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Coin extends Thread{
    private final String Name;
    private final String Short_Name;
    private double Price;
    private double Change_in_1h;
    private double Change_in_24h;
    private double Change_in_7d;
    private double Low_24h;
    private double High_24h;
    private long Total_Volume;
    private long Market_Cap;
    private static CoinGeckoApiClient Client=new CoinGeckoApiClientImpl();
    private CoinFullData Coin_Data;
    private int Speed;
    private NumberFormat Formater=NumberFormat.getCurrencyInstance(Locale.US);
   
    public Coin(String Name) {
        this.Name = Name.toLowerCase();
        Coin_Data= Client.getCoinById(Name);
        this.Short_Name=Client.getCoinById(Name).getSymbol();
        
    }

    
    

    public void setSpeed(int Speed) {
        this.Speed = Speed;
    }
  
    
    
    
    
    
    
    
  public  synchronized void print(){





        System.out.println("* "+Name+" *");
        System.out.println("Price: "+Price);
        System.out.println("Low_24h: "+Low_24h);
        System.out.println("High_24h: "+High_24h);
        System.out.println("Change_in_1h: "+Change_in_1h);
        System.out.println("Change_in_24h: "+Change_in_24h);
        System.out.println("Change_in_7d: "+Change_in_7d);
        System.out.println("Total_Volume: "+Total_Volume);
        System.out.println("Market_Cap: "+Market_Cap);
        System.out.println("-------------------------");

    }
    
    
    
    
  public void  UpdateData(){
             
                  
              this.Price=Coin.Client.getPrice(Name,Currency.USD).get(Name).values().iterator().next();
              this.Low_24h=Coin_Data.getMarketData().getLow24h().get(Currency.USD);
              this.High_24h=Coin_Data.getMarketData().getHigh24h().get(Currency.USD);
              this.Change_in_1h=Coin_Data.getMarketData().getPriceChangePercentage1hInCurrency().get(Currency.USD);
              this.Change_in_24h=Coin_Data.getMarketData().getPriceChangePercentage24hInCurrency().get(Currency.USD);
              this.Change_in_7d=Coin_Data.getMarketData().getPriceChangePercentage7dInCurrency().get(Currency.USD);
              this.Total_Volume=Coin_Data.getMarketData().getTotalVolume().get(Currency.USD).longValue();
              this.Market_Cap=Coin_Data.getMarketData().getMarketCap().get(Currency.USD).longValue();
                  
                  
         
             
          
              
      
             
            
      
  }

   

    public String getShort_Name() {
        return Short_Name;
    }

    public double getPrice() {
        return Price;
    }

    public double getChange_in_1h() {
        return Change_in_1h;
    }

    public double getChange_in_24h() {
        return Change_in_24h;
    }

    public Double getChange_in_7d() {
        return Change_in_7d;
    }

    public double getLow_24h() {
        return Low_24h;
    }

    public double getHigh_24h() {
        return High_24h;
    }

    public long getTotal_Volume() {
        return Total_Volume;
    }

    public long getMarket_Cap() {
        return Market_Cap;
    }

    public static CoinGeckoApiClient getClient() {
        return Client;
    }

    public CoinFullData getCoin_Data() {
        return Coin_Data;
    }

    public int getSpeed() {
        return Speed;
    }

    public NumberFormat getFormater() {
        return Formater;
    }
    
}
