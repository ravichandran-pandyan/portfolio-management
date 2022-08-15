package com.mymoney;

import java.util.*;
import java.time.Month;

public class TransactionHandler {

  public static boolean addTransaction(Portfolio portfolio,Month month, HashMap<Asset_Type,String> assetMap){

    if(portfolio == null){
      return  false;
    }
    Asset asset = new Asset(assetMap);
    portfolio.addAsset(month, asset);
    return true;
  }

  public static boolean addTransaction(Portfolio portfolio,Month month, Asset asset){

    if(portfolio == null){
      return  false;
    }
    portfolio.addAsset(month, asset);
    return true;
  }

}
