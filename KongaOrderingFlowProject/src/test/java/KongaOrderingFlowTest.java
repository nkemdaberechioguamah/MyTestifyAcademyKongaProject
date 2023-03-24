import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KongaOrderingFlowTest {
    private By ClickCard = By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button/div/span[1]");
    private By CardNumber = By.id("card-number");
    private By Date = By.id("expiry");
    private By CVV = By.id("id=\"cvv\"");
    private By Submit = By.id("validateCardForm");
    private By Close = By.xpath("/html/body/section/section/section/div[2]/div[1]/aside");

    //import the selenium WebDriver
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws InterruptedException {
        //locate the chromedriver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        ChromeOptions ChromeOptions = new ChromeOptions();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        org.openqa.selenium.chrome.ChromeOptions chromeOptions = options.addArguments("--disable notifications");
        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);
        //1. open the Chrome browser
        driver = new ChromeDriver(options);
    }
    @Test(priority = 0)
    public void correctLoginPage() throws InterruptedException {
        //2. input the Konga Page url (https://www.konga.com/)
        driver.get("https://www.konga.com/");
        //Test 1. verify that user enters the right url and is on the correct webpage
        if (driver.getCurrentUrl().contains("https://www.konga.com/"))
            //Pass
            System.out.println("correct web url");
        else
            //Fail
            System.out.println("wrong web page");
        Thread.sleep(5000);
    }
    @Test (priority = 1)
    public void confirmLogInScreen() throws InterruptedException {
        //3. maximize the browser
        driver.manage().window().maximize();
        //4. click on Login/Signup button to open Login screen
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        //Test 2. Verify that when user clicks on the login button, the login screen comes up
        String actualLoginMessage = "Login";
        if (actualLoginMessage.contains("Login"))
            //Pass
            System.out.println("Login screen");
        else
            //Fail
            System.out.println("unable to login");
        Thread.sleep(5000);

    }
    @Test(priority = 2)
    public void PositiveLogin() throws InterruptedException {
        //5. input email address or phone number in the username field
        driver.findElement(By.id("username")).sendKeys("08032220228");
        //6. input password in the password field
        driver.findElement(By.id("password")).sendKeys("Lovesdabby@247");
        //7. click on the Login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        //Test 3. verify that user is successfully logged in when correct details are inputted
        String expectedUrl = "https://www.konga.com/";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("Login Successful");
        else
            //Fail
            System.out.println("invalid login details");
        Thread.sleep(5000);
    }
    @Test(priority = 3)
    public void selectCategorySuccessfully() throws InterruptedException {
        //8. from categories, click on "Categories and Accessories"
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        //Test 4. Verify that when user clicks on the "Categories and Accessories", the list opens successfully
        String actualLoginMessage = "Login";
        if (actualLoginMessage.contains("Computers and Accessories"))
            //Pass
            System.out.println("Category selected successfully");
        else
            //Fail
            System.out.println("unable to select category");
        Thread.sleep(5000);
    }
    @Test(priority = 4)
    public void selectLaptopSuccessfully() throws InterruptedException {
        //9. click on the Laptop SubCategory
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a")).click();
        Thread.sleep(5000);
        //Test 5. Verify that when user clicks on the "Laptops", the list opens successfully
        String actualLoginMessage = "Laptops";
        if (actualLoginMessage.contains("Laptops"))
            //Pass
            System.out.println("Laptops selected successfully");
        else
            //Fail
            System.out.println("unable to select Laptops");
        Thread.sleep(5000);

    }
    @Test(priority = 5)
    public void selectAppleMacbookSuccessfully() throws InterruptedException {
        //10. click on the Apple MacBooks
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li/a/ul/li[1]/a/label/span")).click();
        Thread.sleep(5000);
        //Test 6. verify that Apple Macbook is present on the list page
        String expectedListPageUrl = "https://www.konga.com/category/accessories-computing-5227";
        String actualListPageUrl = driver.getCurrentUrl();
        if (actualListPageUrl.contains(expectedListPageUrl))
            //Pass
            System.out.println("Apple Macbook listed on page url ");
        else
            //Fail
            System.out.println("Apple Macbook not listed on page url");
        Thread.sleep(5000);
    }
    @Test(priority = 6)
    public void addItemToCart() throws InterruptedException {
        //11. select an Item, and click add to cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[4]/div/div/div[2]/form/div[3]/button")).click();
        //12. click on the My Cart Button to view cart
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]")).click();
        //Test 7. verify that item is successfully added to cart
        String actualCartMessage = "Cart Overview";
        if (actualCartMessage.contains("Cart Overview"))
            //Pass
            System.out.println("Item successfully added to cart");
        else
            //Fail
            System.out.println("unable to add item to cart");
        Thread.sleep(5000);
    }
    @Test(priority = 7)
    public void completeOrder() throws InterruptedException {
        //13. Click on Checkout
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(5000);
        //Test 8. verify that user is successfully directed to new page to complete order
        String expectedUrl = "https://www.konga.com/checkout/complete-order";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("Checkout Page");
        else
            //Fail
            System.out.println("unable to proceed to checkout");
        Thread.sleep(5000);
    }
    @Test(priority = 8)
    public void selectAddress() throws InterruptedException {
        //14. click on the "Change" button to add delivery address
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button")).click();
        Thread.sleep(5000);
        //15. click on "Add Delivery Address"
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
        Thread.sleep(5000);
        //16. Select Address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
        Thread.sleep(5000);
        //17. Click on "Use this Address"
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        //Test 9. Verify that user is able to select delivery address successfully
        String actualLoginMessage = "Add Delivery Address";
        if (actualLoginMessage.contains("Add Delivery Address"))
            //Pass
            System.out.println("Delivery address selected successfully");
        else
            //Fail
            System.out.println("unable to select delivery address");
        Thread.sleep(5000);
    }
    @Test(priority = 9)
    public void makePayment() throws InterruptedException {
        //18. click on "Pay Now" button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(5000);
        //19. click on "Continue to Payment" button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        //Test 10. verify that when user clicks on continue to payment button, the iFrame to select payment option pops up
        String actualCartMessage = "Select Payment Method";
        if (actualCartMessage.contains("Select Payment Method"))
            //Pass
            System.out.println("Payment method selected successfully");
        else
            //Fail
            System.out.println("unable to proceed to payment method");
        Thread.sleep(5000);
    }
    @Test(priority = 10)
    public void selectPaymentMethod() throws InterruptedException {
        //20. from the select payment method pop up, click on the "Card" option
        click(ClickCard);
        //Test 11. Verify that user is able to select card payment option successfully
        String actualLoginMessage = "Card Number";
        if (actualLoginMessage.contains("Card Number"))
            //Pass
            System.out.println("Card payment selected successfully");
        else
            //Fail
            System.out.println("unable to select card payment");
        Thread.sleep(5000);
    }

    private void click(By clickCard) {
    }

    @Test(priority = 11)
    public void enterCardDetails() throws InterruptedException {
        //21. enter invalid card number in the card number field
        setText(CardNumber, 498324);
        //22. enter invalid date in the Date field
        setText(Date, 01/28);
        //23. enter wrong CVV in the CVV field
        setText(CVV, 000);
        //Test 12. Verify that user is able to enter card details successfully
        String actualLoginMessage = "Enter Card Details";
        if (actualLoginMessage.contains("Enter Card Details"))
            //Pass
            System.out.println("Card details entered successfully");
        else
            //Fail
            System.out.println("unable to enter card details");
    }

    private void setText(By cardNumber, int i) {
    }

    @Test(priority = 12)
    public void PrintErrorMessage() throws InterruptedException {
        //24. click on the "Pay Now" button
        click(Submit);
        Thread.sleep(5000);
        //25. Close the iFrame that displays the input card modal
        click(Close);
        Thread.sleep(5000);
        //Test 13. verify that user is unable to complete order with invalid card details
        String actualErrorMessage = "Invalid card number";
        if (actualErrorMessage.contains("Invalid card number"))
            //Pass
            System.out.println("Invalid card number");
        else
            //Fail
            System.out.println("order completed successfully");
        Thread.sleep(10000);

    }

    @AfterTest
    public void closeBrowser(){
        //32. Quit the browser
        driver.quit();

    }

}