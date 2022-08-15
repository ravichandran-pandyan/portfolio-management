package com.mymoney;

import java.util.*;
import java.time.Month;

public class Portfolio {

  private boolean isSIPActivated = false;
  private HashMap<Month,Asset> assetsMap ;
  private Asset sipAsset ;
  private Asset primaryAsset;

  private float equityShare = 0.00f;
  private float debtShare = 0.00f;
  private float goldShare = 0.00f;

  public Portfolio(HashMap<Asset_Type,String> assetMap){
      assetsMap = new HashMap<Month,Asset>();
      PortfolioHandler.allocateAsset(this,Month.JANUARY, assetMap);
      Asset asset = assetsMap.get(Month.JANUARY);
      this.primaryAsset = asset;
      this.equityShare = (asset.getEquityValue()*100f/asset.getTotalAssetValue());
      this.debtShare = (asset.getDebtValue()*100f/asset.getTotalAssetValue());
      this.goldShare = (asset.getGoldValue()*100f/asset.getTotalAssetValue());
  }

  public Asset getPrimaryAsset(){
    return this.primaryAsset;
  }

  public boolean activateSIP(){
    this.isSIPActivated = true;
    return true;
  }

  public boolean addSIPAsset(Asset asset){
    this.sipAsset = asset;
    return true;
  }

  public boolean addAsset(Month month,Asset asset){
      this.assetsMap.put(month,asset);
      return false;
  }

  public float getEquityShare(){
    return this.equityShare;
  }

  public float getDebtShare(){
    return this.debtShare;
  }

  public float getGoldShare(){
    return this.goldShare;
  }

  public boolean isSIPActivated(){
    return this.isSIPActivated;
  }

  public Asset getAsset(Month month){
    return this.assetsMap.get(month);
  }

  public Asset getSIPAsset(){
    return this.sipAsset;
  }

  public int getTotalAssetSize(){
    return this.assetsMap.size();
  }

  @Override
  public String toString() {
    return assetsMap.toString();
  }

}
