package com.mymoney;

import java.util.*;
import java.time.Month;

public class PortfolioHandler {

  public static Portfolio createPortfolio(Portfolio portfolio,HashMap<Asset_Type,String> assetMap){
      portfolio = new Portfolio(assetMap);
      return  portfolio;
  }

  public static boolean allocateAsset(Portfolio portfolio,Month month, HashMap<Asset_Type,String> assetMap){
    if(portfolio == null){
      return false;
    }
    TransactionHandler.addTransaction(portfolio,month,assetMap);
    return true;
  }

  public static boolean allocateAsset(Portfolio portfolio, Month month, Asset asset){
    if(portfolio == null){
      return false;
    }
    TransactionHandler.addTransaction(portfolio,month,asset);
    return true;
  }

  public static boolean activateSIP(Portfolio portfolio,HashMap<Asset_Type,String> assetMap){
    if(portfolio == null){
      return false;
    }
    portfolio.activateSIP();
    portfolio.addSIPAsset(new Asset(assetMap));
    return true;
  }

  public static boolean isSIPActivated(Portfolio portfolio){
    return portfolio.isSIPActivated();
  }


  public static boolean handleMarketChange(Portfolio portfolio,Month month, HashMap<Asset_Type,String> assetMap){
      try
      {
        if(assetMap.size() == 0){
          return false;
        }
        float equityShare = Float.parseFloat(assetMap.get(Asset_Type.EQUITY).substring(0, assetMap.get(Asset_Type.EQUITY).length()-1));
        float debtShare = Float.parseFloat(assetMap.get(Asset_Type.DEBT).substring(0, assetMap.get(Asset_Type.DEBT).length()-1));
        float goldShare = Float.parseFloat(assetMap.get(Asset_Type.GOLD).substring(0, assetMap.get(Asset_Type.GOLD).length()-1));
        Month previousMonth = month;

        if(!month.equals(Month.JANUARY)){
          previousMonth = Month.of(month.getValue()-1);
        }
        Asset asset = portfolio.getAsset(previousMonth);
        Long equityValue = asset.getEquityValue();
        Long debtValue = asset.getDebtValue();
        Long goldValue = asset.getGoldValue();

        if(!month.equals(Month.JANUARY) && portfolio.isSIPActivated()){
          Asset sipAsset = portfolio.getSIPAsset();
          equityValue += sipAsset.getEquityValue();
          debtValue += sipAsset.getDebtValue();
          goldValue += sipAsset.getGoldValue();

          System.out.println(" After SIP "+month+" "+equityValue+" "+debtValue+" "+goldValue);
        }

        equityValue = (long)(Math.floor(((double)equityValue/100)*(equityShare+100)));
        debtValue = (long)(Math.floor(((double)debtValue/100)*(debtShare+100)));
        goldValue = (long)(Math.floor(((double)goldValue/100)*(goldShare+100)));

        System.out.println(" After CHANGE "+month+" "+equityValue+" "+debtValue+" "+goldValue);

        PortfolioHandler.allocateAsset(portfolio,month,new Asset(equityValue, debtValue, goldValue));
      } catch(Exception ex){
        ex.printStackTrace();
      }
      return true;
  }

  public static String getBalance(Portfolio portfolio, Month month){
      if(portfolio == null){
        return "";
      }
      Asset asset = portfolio.getAsset(month);
      if(asset != null){
        return asset.getEquityValue()+" "+asset.getDebtValue()+" "+asset.getGoldValue();
      }
      return "";
  }

  public static String handleRebalance(Portfolio portfolio){

    Long equityValue = -1L;
    Long debtValue = -1L;
    Long goldValue = -1L;
    try
    {
      if(portfolio == null){
        return "";
      }

      int size = portfolio.getTotalAssetSize();
      Month month;
      if(size >= 12){
        month = Month.DECEMBER;
      } else if(size >= 6){
        month = Month.JUNE;
      } else {
        return "CANNOT_REBALANCE";
      }

      Asset asset = portfolio.getAsset(month);
      Long totalAssetValue = asset.getTotalAssetValue();
      equityValue = (long)(Math.floor(((double)totalAssetValue/100)*(portfolio.getEquityShare())));
      debtValue = (long)(Math.floor(((double)totalAssetValue/100)*(portfolio.getDebtShare())));
      goldValue = (long)(Math.floor(((double)totalAssetValue/100)*(portfolio.getGoldShare())));

      PortfolioHandler.allocateAsset(portfolio,month,new Asset(equityValue, debtValue, goldValue));

    } catch(Exception ex){
      ex.printStackTrace();
    }

    return equityValue+" "+debtValue+" "+goldValue;
  }

}
