package com.mymoney;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.Month;

public class PortfolioManagementTest{

  public static PortfolioManagement portfolioManagement ;

  @BeforeClass
  public static void testInput() {
  System.out.println("Portfolio test started");
  portfolioManagement = new PortfolioManagement();
  String[] inputStrArr = {"ALLOCATE 6000 3000 1000",
                          "SIP 2000 1000 500",
                          "CHANGE 4.00% 10.00% 2.00% JANUARY",
                          "CHANGE -10.00% 40.00% 0.00% FEBRUARY",
                          "CHANGE 12.50% 12.50% 12.50% MARCH",
                          "CHANGE 8.00% -3.00% 7.00% APRIL",
                          "CHANGE 13.00% 21.00% 10.50% MAY",
                          "CHANGE 10.00% 8.00% -5.00% JUNE"};
    for(int i=0;i<8;i++){
      portfolioManagement.portfolio = CommandHandler.executeCommand(portfolioManagement.portfolio, inputStrArr[i]);
    }
  }

  @Test
  public void testAllocate(){
    System.out.println("Allocation test started");
    Asset primaryAsset = portfolioManagement.portfolio.getPrimaryAsset();
    System.out.println("Inside Allocation Test :::: "+primaryAsset);
    assertEquals((Long)6000L, primaryAsset.getEquityValue());
    assertEquals((Long)3000L, primaryAsset.getDebtValue());
    assertEquals((Long)1000L, primaryAsset.getGoldValue());
    System.out.println("Allocation test completed");
  }

  @Test
  public void testSIP(){
    System.out.println("SIP test started");
    Asset sipAsset = portfolioManagement.portfolio.getSIPAsset();
    System.out.println("Inside SIP Test :::: "+sipAsset);
    assertEquals(true, PortfolioHandler.isSIPActivated(portfolioManagement.portfolio));
    assertEquals((Long)2000L, sipAsset.getEquityValue());
    assertEquals((Long)1000L, sipAsset.getDebtValue());
    assertEquals((Long)500L, sipAsset.getGoldValue());
    System.out.println("SIP test completed");
  }

  @Test
  public void testBalance(){
    System.out.println("Balance test started");
    String outputStr = PortfolioHandler.getBalance(portfolioManagement.portfolio, Month.MARCH);
    System.out.println("Inside Balance Test "+outputStr);
    if(outputStr.length()>0){
      String[] outputStrArr = outputStr.split(" ");
      assertEquals((Long)10593L, Long.valueOf(outputStrArr[0]));
      assertEquals((Long)7897L, Long.valueOf(outputStrArr[1]));
      assertEquals((Long)2272L, Long.valueOf(outputStrArr[2]));
    }
    System.out.println("Balance test completed");
  }

  @Test
  public void testRebalance(){
    System.out.println("Rebalance test started");
    String outputStr = PortfolioHandler.handleRebalance(portfolioManagement.portfolio);
    System.out.println("Inside Rebalance Test "+outputStr);
    if(outputStr.length()>0){
      String[] outputStrArr = outputStr.split(" ");
      assertEquals((Long)23619L, Long.valueOf(outputStrArr[0]));
      assertEquals((Long)11809L, Long.valueOf(outputStrArr[1]));
      assertEquals((Long)3936L, Long.valueOf(outputStrArr[2]));
    }
    System.out.println("Rebalance test completed");
  }

}
