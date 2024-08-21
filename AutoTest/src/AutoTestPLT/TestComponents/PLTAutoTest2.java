package AutoTestPLT.TestComponents;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutoTestPLT.allpageobjetcs.NavBar;
import AutoTestPLT.pageobjects.CartPage;
import AutoTestPLT.pageobjects.CheckoutLogin;
import AutoTestPLT.pageobjects.CheckoutPaymentInfo;
import AutoTestPLT.pageobjects.HomePage;
import AutoTestPLT.pageobjects.PDP;
import AutoTestPLT.pageobjects.PLP;

public class PLTAutoTest2 extends BaseTest {

	String selectedSize;
	String selectedItem;

	@Test(priority = 1)
	public void oneTrust() throws InterruptedException, IOException {

		HomePage HomePage = new HomePage(driver);
		HomePage.oneTrustClick();
	}
	
	@Test(priority = 2)
	public void navBar() {
		NavBar NavBar = new NavBar(driver);
		NavBar.navClick();

	}
	// PLP and check that its showing by if products are present on page
	@Test(priority = 3)
	public void PLP() throws InterruptedException, IOException {
		PLP PLP = new PLP(driver);
		Boolean PDPCheck = PLP.pageCheck();
		Assert.assertTrue(PDPCheck);
		PLP.ProductClick();

	}

	// couldnt figure out how to split this up as the 
	// selectedItem and selectedSize values wouldnt
	// pass between test methods. I assume I need a class per page
	// and to store the values outside the methods to assert :( need help here
	@Test(priority = 4)
	public void PDP() throws InterruptedException, IOException {
		PDP PDP = new PDP(driver);
		int randomSize = PDP.clickSize();
		String selectedSize = PDP.grabProductSize(randomSize);
		String selectedItem = PDP.grabProductName();
		PDP.addToBag();
		PDP.goToBag();

		//cart page and checks against stored values
		CartPage CartPage = new CartPage(driver);
		String cartSizeCut = CartPage.cartSize(selectedSize);
		String cartItem = CartPage.cartItemName(selectedItem);
		String cartSubTotal = CartPage.cartSubtotal();
		Assert.assertEquals(selectedSize, cartSizeCut);
		Assert.assertEquals(selectedItem, cartItem);
		CartPage.proceedToCheckout();
		
		//checkout login page
		CheckoutLogin CheckoutLogin = new CheckoutLogin(driver);
		CheckoutLogin.loginDetails();
		CheckoutLogin.continueLogin();

		//final checkout page and checks of stored values
		CheckoutPaymentInfo CheckoutPaymentInfo = new CheckoutPaymentInfo(driver);
		String miniBagItem = CheckoutPaymentInfo.miniItem(selectedItem);
		String miniBagSize = CheckoutPaymentInfo.miniSize(selectedSize);
		String miniBagSubTotal = CheckoutPaymentInfo.miniSub(cartSubTotal);
		
		Assert.assertEquals(miniBagItem, cartItem);
		Assert.assertEquals(miniBagSize, cartSizeCut);
		Assert.assertEquals(miniBagSubTotal, cartSubTotal);
		
		Boolean delivery = CheckoutPaymentInfo.minibagDelivery();
		Boolean grandTotal = CheckoutPaymentInfo.minibagGT();
		
		Assert.assertTrue(delivery);
		Assert.assertTrue(grandTotal);
		
		CheckoutPaymentInfo.scrollToPayment();
		Boolean cardShown = CheckoutPaymentInfo.selectCard();
		Assert.assertTrue(cardShown);
	}
}
