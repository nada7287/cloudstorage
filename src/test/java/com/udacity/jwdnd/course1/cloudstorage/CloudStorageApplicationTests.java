package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {
	@FindBy(id="add-Notes-Button")
	private WebElement addNoteButton;


	@FindBy(id="note-description")
	private WebElement noteDescription;

	@FindBy(id="noteTitle")
	private WebElement FirstNote;

	@FindBy(id="noteDescription")
	private WebElement FirstDescription;

	@FindBy(id="note-title")
	private WebElement noteTitle;

	@FindBy(id="noteSubmitButton")
	private WebElement addNotesButton;

	@LocalServerPort
	private int port=8080;

	private HomePage home;

	private WebDriver driver;

	private String baseURL = "";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		home = new HomePage(driver);
		this.home=home;
		baseURL = "http://localhost:" + port;

	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	@Test
	@Order(1)
	public void getLoginPage() {

		driver.get(baseURL+ "/login");

		Assertions.assertEquals("Login", driver.getTitle());
	}


	@Test
	@Order(2)
	public void testunauthorizedUserSignup() {

		driver.get(baseURL + "/signup");

		Assertions.assertEquals("Sign Up", driver.getTitle());

	}


	@Test
	@Order(3)
	public void testunauthorizedUserHome() {

		driver.get(baseURL + "/home");

		assertNotEquals("Home",driver.getTitle());

	}


	@Test
	@Order(4)
	public void testunauthorizedUserResult() {

		driver.get(baseURL + "/result");

		assertNotEquals("Result",driver.getTitle());
	}




	@Test
	@Order(5)

	public void testLoginHome(){

		login();

		driver.get(baseURL + "/home");

		assertEquals("Home",driver.getTitle());


	}


	@Test
	@Order(6)
	public void testcreateNewNote() throws InterruptedException {

		login();


		driver.get(baseURL + "/home");

		assertEquals("Home",driver.getTitle());

		String noteTitele = "TO DO";

		String noteDesc = "For Exam";

		HomePage home = new HomePage(driver);

		home.addNewNote(noteTitele, noteDesc);

		assertEquals("http://localhost:" + port + "/result?success", driver.getCurrentUrl());

	}

	@Test
	@Order(7)
	public void testcreateUpdateNote() throws InterruptedException {

		login();

		HomePage home = new HomePage(driver);

		driver.get(baseURL + "/home");

		home.updateNote("TO DO 1", "For Exam 1");

		assertEquals("http://localhost:" + port + "/result?success", driver.getCurrentUrl());

	}

	@Test
	@Order(8)
	public void testcreateDeleteNote() throws InterruptedException {

		login();

		HomePage home = new HomePage(driver);

		driver.get(baseURL + "/home");

		home.deleteNote();

		assertEquals("http://localhost:"+port+"/result?success",driver.getCurrentUrl());

	}
	@Test
	@Order(9)
	public void testcreateCred() throws InterruptedException {

		login();

		HomePage home = new HomePage(driver);

		driver.get(baseURL + "/home");


		String credURL="http://localhost";

		String credPassword="MaxPass";

		home.addNewCred(credURL,"pzastoup",credPassword);

		assertEquals("http://localhost:"+port+"/result?success",driver.getCurrentUrl());

	}
	@Test
	@Order(10)
	public void testUpdateCred() throws InterruptedException {

		login();

		HomePage home = new HomePage(driver);

		driver.get(baseURL + "/home");

		home.updateCred("http://localhost1","Max1","MaxPass1");

		assertEquals("http://localhost:"+port+"/result?success",driver.getCurrentUrl());

	}
	@Test
	@Order(11)
	public void testDeleteCred() throws InterruptedException {

		login();

		HomePage home = new HomePage(driver);

		driver.get(baseURL + "/home");

		home.deleteCred();

		assertEquals("http://localhost:"+port+"/result?success",driver.getCurrentUrl());

	}
	/*@Test
	@Order(9)
	public void testUserSignupLoginCreateModiyDeleteCredential() throws InterruptedException {


		login();

		String credURL="http://localhost";

		String credPassword="MaxPass";

		HomePage home=new HomePage(driver);


		home.addNewCred(credURL,"pzastoup",credPassword);

		assertEquals("http://localhost:"+port+"/result?success",driver.getCurrentUrl());

		driver.get(baseURL + "/home");

		home.updateCred("http://localhost1","Max1","MaxPass1");

		assertEquals("http://localhost:"+port+"/result?success",driver.getCurrentUrl());

		driver.get(baseURL + "/home");

		home.deleteCred();

		assertEquals("http://localhost:"+port+"/result?success",driver.getCurrentUrl());

	}
*/
	@Test
	@Order(12)

	public void testLogoutHome(){
		login();

		HomePage home=new HomePage(driver);

		driver.get(baseURL + "/home");

		home.logOut();

		assertNotEquals("Home",driver.getTitle());

	}
	public void login(){

		String username = "pzastoup";

		String password = "whatabadpassword";

		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);

		signupPage.signup("Peter", "Zastoupil", username, password);

		driver.get(baseURL + "/login");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.login(username, password);

	}

}
