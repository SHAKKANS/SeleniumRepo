package firstmavenproject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import firstmavenproject.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cart h3")
	private List<WebElement> cartProducts;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> ProductNames;

	public OrderPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean VerifyOrderedProducts(String productName) {
		Boolean match = ProductNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckOut() {
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
}
