package firstmavenproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import firstmavenproject.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		//Initialize
		super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
	}
	
	//Pagefactory	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordele;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	
	public ProductCatalogue loginApplication(String username,String password)
	{
		userEmail.sendKeys(username);
		passwordele.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String loginError() {
		
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
		
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	

}
