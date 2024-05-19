package firstmavenproject.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import firstmavenproject.pageobjects.CartPage;
import firstmavenproject.pageobjects.CheckOutPage;
import firstmavenproject.pageobjects.ConfirmationPage;
import firstmavenproject.pageobjects.OrderPage;
import firstmavenproject.pageobjects.ProductCatalogue;
import firstmavenproject.testcomponents.BaseTest;

public class SubmitOrderCopyTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL1";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		// Productpage-adding item
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.VerifyProductsDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.SelectCountry("India");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistory() throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication("Shakkila.blr@gmail.com", "Aahi@2008");
		OrderPage orderPage = productCatalogue.goToOrder();
		Assert.assertTrue(orderPage.VerifyOrderedProducts(productName));
	}

	// (dataProvider = "getData", groups = "Purchase")
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//datapackage//user.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//DataProvider using getData()
	/*
	 * public Object[][] getData() { return new Object[][] {{
	 * "Shakkila.blr@gmail.com","Aahi@2008","ADIDAS ORIGINAL"},
	 * {"Shakkila.blr@gmail.com","Aahi@2008","ZARA COAT 3"}}; }
	 */
//HashMap
//HashMap<String,String>map=new HashMap<String,String>();
//map.put("email", "Shakkila.blr@gmail.com");
//map.put("password", "Aahi@2008");
//map.put("product", "ADIDAS ORIGINAL");
//
//HashMap<String,String>map1=new HashMap<String,String>();
//map1.put("email", "aahilihs253@gmail.com");
//map1.put("password", "Aahi@2008");
//map1.put("product", "ZARA COAT 3");
//return new Object[][] {{map},{map1}};

}