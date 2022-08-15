# portfolio-management

Compile the solution 

javac -d "classes" -cp .:{server.path}/com/mymoney/junit-4.13.2.jar:{server.path}/com/mymoney/hamcrest-core-1.3.jar *.java

Run the solution

java -cp .:{server.path}/com/mymoney/junit-4.13.2.jar:{server.path}/com/mymoney/hamcrest-core-1.3.jar:classes com/mymoney/PortfolioManagement {server.path}/com/mymoney/input.txt
