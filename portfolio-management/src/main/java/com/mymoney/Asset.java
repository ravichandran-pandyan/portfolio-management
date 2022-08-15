package com.mymoney;

import java.util.*;

public class Asset {
  private Long equityValue = 0L;
  private Long debtValue = 0L;
  private Long goldValue = 0L;

  public Asset(HashMap<Asset_Type,String> assetMap){
    this.equityValue = Long.valueOf(assetMap.get(Asset_Type.EQUITY));
    this.debtValue = Long.valueOf(assetMap.get(Asset_Type.DEBT));
    this.goldValue = Long.valueOf(assetMap.get(Asset_Type.GOLD));
  }

  public Asset(Long equityValue, Long debtValue, Long goldValue){
    this.equityValue = equityValue;
    this.debtValue = debtValue;
    this.goldValue = goldValue;
  }

  public Long getTotalAssetValue(){
    return this.equityValue+this.debtValue+this.goldValue;
  }

  public Long getEquityValue(){
    return this.equityValue;
  }

  public Long getDebtValue(){
    return this.debtValue;
  }

  public Long getGoldValue(){
    return this.goldValue;
  }

  @Override
  public String toString() {
    return this.equityValue+" "+this.debtValue+" "+this.goldValue;
  }

}
