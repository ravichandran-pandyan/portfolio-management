package com.mymoney;

import java.util.*;

public class DataHandler {
  public static HashMap<Asset_Type,String> assetMapBuilder(Command command, String inputStr){
    HashMap<Asset_Type,String> assetMap = new HashMap<Asset_Type,String>();
    try
    {
      if(inputStr == null || inputStr.length() == 0){
        return assetMap;
      }
      String[] inputStrArr = inputStr.split(" ");

      if(command.equals(Command.ALLOCATE) || command.equals(Command.SIP) || command.equals(Command.CHANGE)){
        assetMap = new HashMap<Asset_Type,String>();
        assetMap.put(Asset_Type.EQUITY, inputStrArr[1]);
        assetMap.put(Asset_Type.DEBT, inputStrArr[2]);
        assetMap.put(Asset_Type.GOLD, inputStrArr[3]);
      }
    } catch(Exception ex){
      ex.printStackTrace();
    }
    return assetMap;
  }


}
