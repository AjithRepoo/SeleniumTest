package comtest;








import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

 

public class CSVLoginExample {

 
@Test
    public static void test() throws InterruptedException{

        // Specify the path to your CSV file

        String csvFilePath = "C:\\Users\\1000066986\\Downloads\\integration.csv";

        // Initialize the WebDriver

       WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        Thread.sleep(10000);
        
        driver.manage().window().maximize();

        WebElement usernameField = driver.findElement(By.name("username"));

        WebElement passwordField = driver.findElement(By.name("password"));

 
        // Enter the username and password

        usernameField.sendKeys("Admin");

        passwordField.sendKeys("admin123");

 

        // Perform login

        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        Thread.sleep(5000);

        driver.findElement(By.xpath("//span[text()='PIM']")).click();

 

        try {

            // Open the CSV file

            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));

 

         // Skip the header row
            br.readLine();

 

            // Read the CSV file line by line
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                // Split the line into username and password
                String[] data = line.split(",");

 

                // Get the username and password

             //   String username = data[0];

              //  String password = data[1];

                String firstname = data[0];

                String middlename = data[1];

                String Lastname = data[2];
                
                String id = data[3];
 

                // Open the Orange HRM login page


                Thread.sleep(5000);

                // Find the username and password fields



                String currentUrl = driver.getCurrentUrl();

                System.out.println(currentUrl);



                    String currentUrl2 = driver.getCurrentUrl();

                    if (currentUrl2.equals("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList")) {

                        driver.findElement(By.xpath("//button[@type='button']//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

                        Thread.sleep(5000);

                         String currentUrl3 = driver.getCurrentUrl();

                        if (currentUrl3.equals("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee")) {


                            driver.findElement(By.name("firstName")).sendKeys(firstname);

                            driver.findElement(By.name("middleName")).sendKeys(middlename);

                            driver.findElement(By.name("lastName")).sendKeys(Lastname);
                            
                            driver.findElement(By.xpath("(//label[text()='Employee Id']//following::input[@class='oxd-input oxd-input--active'])[1]")).clear();
                            Thread.sleep(5000);
                            driver.findElement(By.xpath("(//label[text()='Employee Id']//following::input[@class='oxd-input oxd-input--active'])[1]")).sendKeys(id);
                            driver.findElement(By.xpath("//button[text()=' Save ']")).click();

                            Thread.sleep(10000);

                            String currentUrl4 = driver.getCurrentUrl();

                            if (currentUrl4.contains("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber")) {

                                driver.findElement(By.xpath("//span[text()='PIM']")).click();    

                            }



                    }



 

                // Count the number of entries


                }

                count++;

            }

 

            // Close the CSV file

            br.close();

 

            // Print the number of entries

            System.out.println("Total data entries: " + count);

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            // Quit the WebDriver

            //driver.quit();

        }

    }

}
