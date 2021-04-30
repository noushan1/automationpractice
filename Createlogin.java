
	
	package com.test.nishmidha;

	import org.openqa.selenium.chrome.ChromeDriver;

	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

import WebElement.WebElement.By;
import automationpractice.com.pageObject.SignInForm;
import utils.WebDriver;

	public class CreateAccountFormTest {

		private ChromeDriver driver;

		private Homepage homepage;
		private CreateAccount createAccount;
		private CreateAccountForm createAccountForm;
		private SignInForm signin;
		private Account account;

		public void setup() {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();

			homepage = new Homepage();
			createAccount = new CreateAccount();
			createAccountForm = new CreateAccountForm();
			signin = new SignInForm();
			account = new Account();

			String baseUrl = "http://automationpractice.com/index.php";
			driver.get(baseUrl);
			
		}

		public void closeAll() throws InterruptedException {
			
			account.getAccountLogout();
		
			driver.wait();
		
		}
		
		@Test
		public void personalInfoFields() {
			// With values
			createAccountForm.setCustomerFirstNameField("Nishi");
			createAccountForm.setCustomerLastNameField("Naush");
			createAccountForm.setCustomerEmailField("nishmithaovr@gmail.com");
			createAccountForm.setCustomerPasswordField("tester");

			createAccountForm.getAccountCreationForm().click();

			Assert.assertTrue(createAccountForm.getFirstNameHighlightedGreen().isDisplayed());
			Assert.assertTrue(createAccountForm.getLastNameHighlightedGreen().isDisplayed());
			Assert.assertTrue(createAccountForm.getEmailHighlightedGreen().isDisplayed());
			Assert.assertTrue(createAccountForm.getPasswordHighlightedGreen().isDisplayed());

			// Without values
			createAccountForm.setCustomerFirstNameField("");
			createAccountForm.setCustomerLastNameField("");
			createAccountForm.setCustomerEmailField("");
			createAccountForm.setCustomerPasswordField("");

			createAccountForm.getAccountCreationForm().click();

			Assert.assertTrue(createAccountForm.getFirstNameHighlightedRed().isDisplayed());
			Assert.assertTrue(createAccountForm.getLastNameHighlightedRed().isDisplayed());
			Assert.assertTrue(createAccountForm.getEmailHighlightedRed().isDisplayed());
			Assert.assertTrue(createAccountForm.getPasswordHighlightedRed().isDisplayed());
		}

		@Test
		public void requiredFieldsEmpty() {
			createAccountForm.getAddressAliasField().clear();
			createAccountForm.setCustomerEmailField("");
			createAccountForm.selectCountry("-");
			createAccountForm.getRegisterBtn().click();

			Assert.assertTrue(createAccountForm.getPhoneNumberError().isDisplayed());
			Assert.assertTrue(createAccountForm.getLastNameError().isDisplayed());
			Assert.assertTrue(createAccountForm.getFirstNameError().isDisplayed());
			Assert.assertTrue(createAccountForm.getEmailRequiredError().isDisplayed());
			Assert.assertTrue(createAccountForm.getPasswordRequiredError().isDisplayed());
			Assert.assertTrue(createAccountForm.getCountryRequiredError().isDisplayed());
			Assert.assertTrue(createAccountForm.getAddressRequiredError().isDisplayed());
			Assert.assertTrue(createAccountForm.getAddressAliasRequiredError().isDisplayed());
			Assert.assertTrue(createAccountForm.getCityRequiredError().isDisplayed());
			Assert.assertTrue(createAccountForm.getCountryUnselectedError().isDisplayed());

			createAccountForm.selectCountry("United States");
			createAccountForm.getRegisterBtn().click();

			Assert.assertTrue(createAccountForm.getStateRequredError().isDisplayed());
			Assert.assertTrue(createAccountForm.getPostalCodeError().isDisplayed());
		}

		
		@Test
		public void createAccountSuccessfully() {
			// Required fields filled
			createAccountForm.setCustomerFirstNameField("Test1");
			createAccountForm.setCustomerLastNameField("Test2");
			createAccountForm.setCustomerPasswordField("test23");
			createAccountForm.selectCustomerDateOfBirthDay("15");
			createAccountForm.selectCustomerDateOfBirthMonth("10");
			createAccountForm.selectCustomerDateOfBirthYear("1988");
			createAccountForm.setAddressField("Cottages");
			createAccountForm.setCityField("Hilling street");
			createAccountForm.selectState("78");
			createAccountForm.setPostalCodeField("98567");
			createAccountForm.setHomePhoneField("07");
			createAccountForm.setMobilePhoneField("077");
			createAccountForm.setAddressAliasField("Test address");
			createAccountForm.getRegisterBtn().click();

			Assert.assertTrue(createAccountForm.getEmailBeenRegistered().isDisplayed());

			createAccountForm.setCustomerEmailField(EmailsGenerator.getNextEmail());
			createAccountForm.setCustomerPasswordField("tester123");
			createAccountForm.getRegisterBtn().click();

			Assert.assertTrue(createAccountForm.successfullyCreatedAccount().isDisplayed());
		}
