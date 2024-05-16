package firstmavenproject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import firstmavenproject.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	

	@FindBy(css = ".action__submit")
	private WebElement submit;
	@FindBy(css = ".ta-results")
	By waitelement = By.cssSelector(".ta-results");

	public void SelectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitForElementToAppear(waitelement);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() throws InterruptedException {
		Thread.sleep(1000);
		submit.click();
		
		return new ConfirmationPage(driver);
	}
	

}



	
	
	


