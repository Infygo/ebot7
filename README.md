### Installation & Execution 
Make sure to have JAVA JDK and Maven installed and configured under Environment variables in your machine to have a successful execution.
*Method1* - execution will make the tests to run in both chrome and firefox browser
- Download / clone the project from Git 
- Import project in IDE of your choice - Code is in the Kollex-automation-master/Automation
- Open testng.xml -> Run as TestNG Suite 
- Open test output folder -> index.html -> to view the Test Results in consolidated manner

*Method2* 
- Download / clone the project from Git 
- Open command prompt - change directory to automation folder
- mvn compile 

(To run the test in chrome use the command below in command prompt)
- mvn test -Dbrowser=chrome
