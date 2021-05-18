package com.udacity.jwdnd.course1.cloudstorage;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage {


   @FindBy(id="noteSubmitButton")
    private WebElement addNotesButton;

    @FindBy(id="note-title")
    private WebElement noteTitle;


    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="noteTitle")
    private WebElement FirstNote;

    @FindBy(id="noteDescription")
    private WebElement FirstDescription;

    @FindBy(id="userTable")
    private WebElement userTable;


    @FindBy(id="logout-button")
    private WebElement logoutButton;

    @FindBy(id="add-Notes-Button")
    private WebElement addNoteButton;

    @FindBy(id="nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id="edit-node")
    private WebElement editNote;

    @FindBy(id="here")
    private WebElement here;

    @FindBy(id="delete-node")
    private WebElement deleteNote;



    @FindBy(id="New-Credential")
    private WebElement NewCredential;

    @FindBy(id="credential-url")
    private WebElement credentialUrl;


    @FindBy(id="credential-username")
    private WebElement credentialUsername;

    @FindBy(id="credential-password")
    private WebElement credentialPassword;

    @FindBy(id="addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(id="edit-cred")
    private WebElement editCredButton;

    @FindBy(id="deleteCred")
    private WebElement deleteCredButton;



    private WebDriver driver ;

    public HomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }

    public int getCredentialsSize(){
        List<WebElement> table = driver.findElements(By.id("credentialTable"));

        try{
            List<WebElement> TRCollection = driver.findElement(By.id("credentialTable")).findElements(By.tagName("tr"));
            return TRCollection.size()-1;
        }catch (Exception e){
            return 0;
        }



    }

    public int getNotesSize(){
        List<WebElement> table = driver.findElements(By.id("userTable"));

        try{
            List<WebElement> TRCollection = driver.findElement(By.id("userTable")).findElements(By.tagName("tr"));
            return TRCollection.size()-1;
        }catch (Exception e){
            return 0;
        }



    }
    public void addNewNote(String noteTitle ,String noteDescription) throws InterruptedException {

        clickNavNotes();
        this.addNoteButton.click();
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.sendKeys(noteDescription);
        this.addNotesButton.click();


    }
    public void updateNote(String noteTitle ,String noteDescription) throws InterruptedException {

        clickNavNotes();
        this.editNote.click();
        this.noteTitle.clear();
        this.noteDescription.clear();
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.sendKeys(noteDescription);
        this.addNotesButton.click();


    }

    public void deleteCred(){

        clickNavCredentials();
        this.deleteCredButton.click();
    }
    public void deleteNote(){

        clickNavNotes();
        this.deleteNote.click();
    }

    public void logOut(){

        assertEquals("Home",driver.getTitle());

        this.logoutButton.click();

    }
    public void clickNavNotes()
    {


    assertEquals("Home",driver.getTitle());

    WebDriverWait wait = new WebDriverWait (driver, 30);

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    WebElement ele = driver.findElement(By.id("nav-notes-tab"));

    wait.until(ExpectedConditions.elementToBeClickable(ele)).click();

    JavascriptExecutor executor = (JavascriptExecutor)driver;

    executor.executeScript("arguments[0].click();", ele);
    }
    public void clickNavCredentials()
    {

        assertEquals("Home",driver.getTitle());

        WebDriverWait wait = new WebDriverWait (driver, 30);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement ele = driver.findElement(By.id("nav-credentials-tab"));

        wait.until(ExpectedConditions.elementToBeClickable(ele)).click();

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].click();", ele);



    }


    public void addNewCred(String url,String username,String pass){

        clickNavCredentials();

        WebDriverWait waitNewCred = new WebDriverWait(driver, 30);
        WebElement NewCredential = waitNewCred.until(ExpectedConditions.elementToBeClickable(By.id("New-Credential")));
        NewCredential.click();

        //this.NewCredential.click();
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(pass);
        this.addCredentialButton.click();


    }

    public void updateCred(String url,String username,String pass){


        clickNavCredentials();
        this.editCredButton.click();
        this.credentialUrl.clear();
        this.credentialUsername.clear();
        this.credentialPassword.clear();
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(pass);
        this.addCredentialButton.click();


    }









}
