package com.mymoney;

import java.util.*;
import java.time.Month;

public class CommandHandler {
  public static Portfolio executeCommand(Portfolio portfolio, String inputStr){

    try
    {
      if(inputStr == null || inputStr.length() == 0){
        return portfolio;
      }

      String[] inputStrArr = inputStr.split(" ");
      String commandStr = inputStrArr[0];
      Command command = Command.valueOf(commandStr.toUpperCase());

      switch(command){
        case ALLOCATE :
            portfolio = PortfolioHandler.createPortfolio(portfolio,DataHandler.assetMapBuilder(Command.ALLOCATE, inputStr));
            if(portfolio == null){
              throw new Exception("Portfolio creation process failed");
            }
            break;
        case SIP :
            boolean sipStatus = PortfolioHandler.activateSIP(portfolio,DataHandler.assetMapBuilder(Command.SIP, inputStr));
            if(!sipStatus){
              throw new Exception("SIP Activation failed");
            }
            break;
        case CHANGE :
            boolean changeUpdateStatus = PortfolioHandler.handleMarketChange(portfolio,Month.valueOf((inputStrArr[4]).toUpperCase()),
                                                                              DataHandler.assetMapBuilder(Command.CHANGE, inputStr));
            if(!changeUpdateStatus){
              throw new Exception("Market change updation failed");
            }

            break;
        case BALANCE :
            String balance = PortfolioHandler.getBalance(portfolio, Month.valueOf((inputStrArr[1]).toUpperCase()));
            if(balance.length() == 0){
              System.out.println("ASSET IS NOT FOUND FOR THIS MONTH");
            }
            System.out.println(balance);
            break;
        case REBALANCE :
            String outputStr = PortfolioHandler.handleRebalance(portfolio);
            System.out.println(outputStr);
            break;
        default:
          System.out.println("The Command not supported");
        }
    } catch(Exception ex){
      ex.printStackTrace();
    }
    return portfolio;
  }
}
