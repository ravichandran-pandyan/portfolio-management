# portfolio-management

Execute below command for compiling the solution 

javac -d "classes" -cp .:{server.path}/com/mymoney/junit-4.13.2.jar:{server.path}/com/mymoney/hamcrest-core-1.3.jar *.java

Execute below command for run the solution

java -cp .:{server.path}/com/mymoney/junit-4.13.2.jar:{server.path}/com/mymoney/hamcrest-core-1.3.jar:classes com/mymoney/PortfolioManagement {server.path}/com/mymoney/input.txt

Excecute below command for test the solution

java -cp .:{server.path}/com/mymoney/junit-4.13.2.jar:{server.path}/com/mymoney/hamcrest-core-1.3.jar:classes com/mymoney/PortfolioManagement test
