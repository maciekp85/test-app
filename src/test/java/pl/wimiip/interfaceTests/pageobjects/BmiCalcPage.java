package pl.wimiip.interfaceTests.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by nishi on 2016-09-02.
 */
public class BmiCalcPage {

    // Define elements from the BMI Calculator Application as instance variables
    // Using the FindBy annotation, we can locate the elements within the PageFactory class
    @FindBy(xpath = "//*[@id='nTWeightKg']")
    // Im applications where we know that the element is always going to be there and stay
    // the same without any change, it would be handy if we could cache the element once we find it
    // We can use for this purpose the @CacheLookUp annotation
    @CacheLookup
    public WebElement weight;

    @FindBy(xpath = "//*[@id='nHM']")
    @CacheLookup
    public WebElement heightMeter;

    @FindBy(xpath = "//*[@id='nHCm']")
    @CacheLookup
    public WebElement heightCm;

    // Add constructor which call the PageFactory.initElements() method to initialize the elements in the class
    // In other words, map the elements to the variables in the class
    public BmiCalcPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
