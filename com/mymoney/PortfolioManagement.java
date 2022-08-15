package com.mymoney;

import java.util.*;
import java.time.Month;
import java.io.File;
import java.io.IOException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*
*/

public class PortfolioManagement{
Portfolio portfolio;
public static void main(String[] args){
  PortfolioManagement portfolioManagement = new PortfolioManagement();
  try {
          if(args.length == 0){
            System.out.println("File URL is not found");
            return;
          }

          String inputStr = args[0];

          if(inputStr.equals("test")){
              Result result = JUnitCore.runClasses(PortfolioManagementTest.class);

              for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
              }

              System.out.println(result.wasSuccessful());
              return;
          }

          Scanner scanner = new Scanner(new File(args[0]));
          while (scanner.hasNextLine()) {
              inputStr = scanner.nextLine();
              portfolioManagement.portfolio = CommandHandler.executeCommand(portfolioManagement.portfolio, inputStr);
          }


      } catch (IOException e) {
          System.out.println(e.getMessage());
          e.printStackTrace();
      }
  }
}
