package pl.wimiip.interfaceTests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;

/**
 * Created by nishi on 2016-09-02.
 */
public class BmiCalcPageOperations extends ITConfigurationForChromeBrowser {

    // Make the page's elements private for better encapsulation
    
    // Define elements from the BMI Calculator Application as instance variables
    // Using the FindBy annotation, we can locate the elements within the PageFactory class
    @FindBy(xpath = "//*[@id='nTWeightKg']")
    // Im applications where we know that the element is always going to be there and stay
    // the same without any change, it would be handy if we could cache the element once we find it
    // We can use for this purpose the @CacheLookUp annotation
    @CacheLookup
    private WebElement weight;

    @FindBy(xpath = "//*[@id='nHM']")
    @CacheLookup
    private WebElement heightMeter;

    @FindBy(xpath = "//*[@id='nHCm']")
    @CacheLookup
    private WebElement heightCm;

    // Add constructor which call the PageFactory.initElements() method to initialize the elements in the class
    // In other words, map the elements to the variables in the class
    public BmiCalcPageOperations(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getWeight() {
        return weight;
    }

    public WebElement getHeightMeter() {
        return heightMeter;
    }

    public WebElement getHeightCm() {
        return heightCm;
    }

    // Add the calculate BMI() method
    public void calculateBmi(String weight, String heightMt, String heightCm) {

        // Get the Weight element using BMI Calculator page's elements from BmiCalcPageElements class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[1]/span/span/input[1]"))).click();
        getWeight().clear();
        getWeight().sendKeys(weight);

        // Get the HeightMeter element using BMI Calculator page's elements from BmiCalcPageElements class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[2]/span/span/input[1]"))).click();
        getHeightMeter().clear();
        getHeightMeter().sendKeys(heightMt);

        // Get the HeightCm element using BMI Calculator page's elements from BmiCalcPageElements class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[2]/span[2]/span/input[1]"))).click();
        getHeightCm().clear();
        getHeightCm().sendKeys(heightCm);
    }

    // Add the getBmi() and getBmiCategory() methods
    public String getBmi() {
        // Get the BMI element and verify its value using parameterized bmi variable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bmiDisplayKg']/div[2]/div[2]")));
        return driver.findElement(By.xpath("//*[@id='bmiDisplayKg']/div[2]/div[2]")).getText().substring(4);
    }

    public String getBmiCategory(String bmiCategory) {
        // Get the BMI Category element and verify its value using parameterized bmiCategory variable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bmiDisplayKg']/div[1]/div[@class='fit" + bmiCategory + "']")));
        return driver.findElement(By.xpath("//*[@id='bmiDisplayKg']/div[1]/div[@class='fit" + bmiCategory + "']")).getText();
    }
}
