package Flow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class PayWowSetup {
	WebDriver f;
	WebDriverWait wait;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	
	@BeforeTest
	public void LogIn() throws InterruptedException, IOException
	{
		//System.setProperty("webdriver.chrome.driver", "/Users/spanindia/Documents/chromedriver");
		f=new FirefoxDriver();
		
		
		f.get("http://secure.payrollspan.net/");
		f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Import excel sheet.
				 File src=new File("/Users/spanindia/Desktop/Data.xlsx");
				 
				 // Load the file.
				 FileInputStream finput = new FileInputStream(src);
				 
				 // Load he workbook.
				workbook = new XSSFWorkbook(finput);
				
				// Load the sheet in which data is stored.
				 sheet= workbook.getSheetAt(0);
				 
				 
		
		// Import data for Email.
		Thread.sleep(3500);
				 cell = sheet.getRow(1).getCell(1);
				 cell.setCellType(Cell.CELL_TYPE_STRING);
				 f.findElement(By.id("EmailAddress")).sendKeys(cell.getStringCellValue());
				 
				 // Import data for password.
				 cell = sheet.getRow(1).getCell(2);
				 cell.setCellType(Cell.CELL_TYPE_STRING);
				 f.findElement(By.id("Password")).sendKeys(cell.getStringCellValue());
		
		/*f.findElement(By.id("EmailAddress")).sendKeys("anandkumar.sundar+m14@w3magix.com");
		f.findElement(By.id("Password")).sendKeys("haihai");*/
				 
		f.findElement(By.id("btnsignin")).click();
		System.out.println("************ Log In Sucessfull ************");
		
		//Register
		
		/*f.get("http://secure.payrollspan.net/user/register");
		f.findElement(By.id("ContactName")).sendKeys("William");
		f.findElement(By.id("EmailAddress")).sendKeys("hari+automation1@expressexcise.com");
		f.findElement(By.id("Password")).sendKeys("123456");
		f.findElement(By.id("Phone")).sendKeys("5548948949");
		
		Thread.sleep(100000);
		f.findElement(By.id("btnSave")).click();
		System.out.println("Registration sucessfull");*/
		
	}
	
	@Test(priority=9)
	//(enabled=false)
   public void EmployerSetup() throws InterruptedException, IOException 
    {
		//f.findElement(By.id("btnBegin")).click();
		
	   // Import excel sheet.
		 File src=new File("/Users/spanindia/Desktop/Data.xlsx");
		 
		 // Load the file.
		 FileInputStream finput = new FileInputStream(src);
		 
		 // Load he workbook.
		workbook = new XSSFWorkbook(finput);
		 
	     // Load the sheet in which data is stored.
		 sheet= workbook.getSheetAt(0);
		 
		 cell = sheet.getRow(1).getCell(3);
		 cell.setCellType(Cell.CELL_TYPE_STRING);
		 System.out.println(cell.getStringCellValue());
		// Import data for Business Name.
		 
    		
    		f.findElement(By.id("BusinessName")).sendKeys(cell.getStringCellValue());
    		
    		 // Import data for EIN.
   		 cell = sheet.getRow(1).getCell(4);
   		 cell.setCellType(Cell.CELL_TYPE_STRING);
   		 
   		 
    		f.findElement(By.id("EIN")).sendKeys(cell.getStringCellValue());
    		Thread.sleep(3000);
    		f.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div/form/div/div[2]/table/tbody/tr[14]/td[2]/span/label")).click();
    		f.findElement(By.id("AddressLine1")).sendKeys("1 South Carolina 101");
    		f.findElement(By.id("City")).sendKeys("Greer");
    		f.findElement(By.id("ZipCode")).sendKeys("29651");
    		
    		try {
    			f.findElement(By.xpath(".//*[@id='StateId']/option[42]")).click();
			} catch (Exception e) {
				
				WebElement elem =f.findElement(By.id("StateId"));
				new Select(elem).selectByValue("49");
			}
    		Thread.sleep(3000);
    		try {
    			f.findElement(By.xpath(".//*[@id='btnNextAddBusiness']")).click();
			} catch (Exception e) {
				f.findElement(By.id("btnNextAddBusiness")).click();
			}
    		Thread.sleep(3000);
    		f.findElement(By.cssSelector(".buttonC.pull-left.mTop5")).click();
    		
    		f.findElement(By.xpath("//button[@id='btnNextWorkLocation']")).click();
    		
    		//Company Signatory
	   		Thread.sleep(2000);
    		f.findElement(By.id("LastName")).sendKeys("Krish");
    		WebElement CT = f.findElement(By.id("CompanyTypeId"));
    		new Select(CT).selectByValue("1");
    		Thread.sleep(1000);
    		WebElement Title = f.findElement(By.id("CompanyTitleId"));
    		new Select(Title).selectByValue("1");
    		   		
    		f.findElement(By.id("btnSaveSignatory")).click(); 
	   
    		Thread.sleep(3000);
    		
    		//PaySchedule
    		
    		f.findElement(By.id("apayrollfrequency")).click();
    		Thread.sleep(1500);
    		f.findElement(By.id("PayscheduleName")).sendKeys("Default Pay Schedule");
    		
    		WebElement elem2= f.findElement(By.id("PayRollFrequency"));
    		new Select(elem2).selectByIndex(3);
    		
    		//Check Date
    		Thread.sleep(1000);
    		f.findElement(By.xpath(".//*[@id='Days']/option[2]")).click();
    		Thread.sleep(1000);
    		f.findElement(By.xpath(".//*[@id='PayDayStartOn']/option[2]")).click();
    		Thread.sleep(1000);
    		f.findElement(By.xpath(".//*[@id='WorkDone']/option[2]")).click();
    		
    		
    		
    		
    		Thread.sleep(1500);
    		
    		f.findElement(By.id("btnsavePayrollFrequency")).click();
    		Thread.sleep(3000);
    		
    		f.findElement(By.xpath(".//*[@id='divLoadFederalTaxSetup']/div[2]/table/tbody/tr[1]/td[2]/span[1]/label")).click();
    		f.findElement(By.xpath(".//*[@id='divLoadFederalTaxSetup']/div[2]/table/tbody/tr[3]/td[2]/span[2]/label")).click();
    		
    		f.findElement(By.id("btnNextTaxSetup")).click();
    		Thread.sleep(2000);
    		
    		f.findElement(By.xpath("//a[@id='btnGotoDashboard']")).click();
    		Thread.sleep(2000);
    		
    		System.out.println("************ Employer setup sucessfull ************");
    }
    
	@Test(priority=8)
	//(enabled=false)
   	public void ParallelMode() throws InterruptedException
   	{
   		Thread.sleep(1500);
   		f.findElement(By.id("aDashboard")).click();
   		f.findElement(By.xpath(".//div[@id='divBusinessMode']/a")).click();
   		Thread.sleep(1500);
   		f.findElement(By.id("Password")).sendKeys("123123");
   		f.findElement(By.id("btnUpdateBusinessMode")).click();
   		
   		System.out.println("************ Business sucessfully converted to LIVE mode ************");
   		
   	}
    
	@Test(priority=2)
	//
    public void EmployeeSetup() throws InterruptedException, IOException
    {									
    									//Add Employee 
		
		for(int i=1; i<=sheet.getLastRowNum(); i++)
		 {
			
    		Thread.sleep(6000);
    		f.findElement(By.id("aPeople")).click();
    		Thread.sleep(1000);
    		f.findElement(By.xpath("//a[@id='aEmployee']/span[2]")).click();
    		f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		//Add Employee
    		try {
				f.findElement(By.xpath("//button[@id='btnAddEmployee']")).click();
			} catch (Exception e1) {
				f.findElement(By.xpath("//h1[@id='hActivitylog']/button")).click();
			}
    		
    		try {
    				f.findElement(By.xpath("//div[@id='ModelBody']/div/div/ul/li[1]/label/img")).click();
			} catch (Exception e) {
				f.findElement(By.linkText("                         Add an Employee                     ")).click();
			}
    		
    		
    		//Data Driven
    		// Import excel sheet.
			 File src=new File("/Users/spanindia/Desktop/Data.xlsx");
			 
			 // Load the file.
			 FileInputStream finput = new FileInputStream(src);
			 
			 // Load he workbook.
			workbook = new XSSFWorkbook(finput);
			
			// Load the sheet in which data is stored.
			 sheet= workbook.getSheetAt(0);
			 
			 
			 
			 
			 
			 cell = sheet.getRow(i).getCell(5);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 f.findElement(By.id("FirstName")).sendKeys(cell.getStringCellValue());
    		
    		//Basic Details
    		
    		
    		cell = sheet.getRow(i).getCell(6);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
    		f.findElement(By.id("LastName")).sendKeys(cell.getStringCellValue());
    		
    		
    		f.findElement(By.id("HireDate")).sendKeys("01/01/2016");
    		//f.findElement(By.id("EmailAddress")).sendKeys("hari+sn92@expressexcise.com");
    		//f.findElement(By.id("EmployeeIdentificationNo")).sendKeys("ST-131");
    		f.findElement(By.id("Title")).sendKeys("QA");
    		WebElement elem =f.findElement(By.id("EmploymentStatus"));
    		new Select(elem).selectByValue("FULLTIME");
    		//new Select(elem).selectByValue("PARTTIME20TO29");
    		//new Select(elem).selectByValue("PARTTIME0TO19");
    		//new Select(elem).selectByValue("VARIABLE");
    		//new Select(elem).selectByValue("SEASONAL0TO6");
    		
    		WebElement elem2=f.findElement(By.id("CompensationType"));
    		//new Select(elem2).selectByValue("SALARYNOOVERTIME");
    		new Select(elem2).selectByValue("SALARYELIGIBLEFOROVERTIME");
    		//new Select(elem2).selectByValue("PAIDBYHOUR");
    		//new Select(elem2).selectByValue("OWNERSDRAW");
    		
    		cell = sheet.getRow(i).getCell(8);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
    		f.findElement(By.id("WagesAmount")).sendKeys(cell.getStringCellValue());
    		
    		WebElement elem3=f.findElement(By.id("WagesPaidType"));
    		//new Select(elem3).selectByValue("PERHOUR");
    		//new Select(elem3).selectByValue("PERWEEK");
    		new Select(elem3).selectByValue("PERMONTH");
    		//new Select(elem3).selectByValue("PERYEAR");
    		f.findElement(By.id("btnAddEmployee")).click();
    		
    		//Start
    		//f.findElement(By.linkText("Start")).click();
    		//f.findElement(By.xpath(".//*[@id='divChooseSetup']/div/div[3]/div[2]/div/div[2]/div[5]/a")).click();
    		
    		//Personal Info
    		cell = sheet.getRow(i).getCell(7);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 f.findElement(By.id("SSN")).sendKeys(cell.getStringCellValue());
    		
    		
    		f.findElement(By.id("DateOfBirth")).sendKeys("01/01/1995");
    		f.findElement(By.id("Phone")).sendKeys("7969669696");
    		f.findElement(By.xpath(".//*[@id='tblBusiness']/tbody/tr[10]/td[2]/span[1]/label")).click();
    		f.findElement(By.xpath(".//*[@id='AddrManual']/label/span/i")).click();
    		f.findElement(By.id("AddressLine1")).sendKeys("11 South Carolina 101");
    		f.findElement(By.id("City")).sendKeys("Gray Court");
    		WebElement elem1=f.findElement(By.id("StateId"));
    		new Select(elem1).selectByValue("49");
    		f.findElement(By.id("ZipCode")).sendKeys("29645");
    		f.findElement(By.id("btnNextAddBusiness")).click();
    		//error popup
    		try
    		{
    			f.findElement(By.xpath("html/body/div[8]/div/div/div[3]/button[1]")).click();
    		}catch (Exception e)
    		{
    			f.findElement(By.cssSelector(".buttonC.pull-left.mTop5")).click();
    		}
    		//Fedral Status
    		WebElement elem4=f.findElement(By.id("FilingStatus"));
    		
    		cell = sheet.getRow(i).getCell(9);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 
    		new Select(elem4).selectByValue(cell.getStringCellValue());
    		//new Select(elem4).selectByValue("MARRIED");
    		//new Select(elem4).selectByValue("WITHHOLDASSINGLE");
    		
    		cell = sheet.getRow(i).getCell(10);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 System.out.println(cell.getStringCellValue());
    		
    		f.findElement(By.id("TotalAllowances")).sendKeys(cell.getStringCellValue());
    		
    		f.findElement(By.xpath(".//*[@id='tblEmployeeTaxInfo']/tbody/tr[7]/td[2]/span[2]/label/span/i")).click();
    		f.findElement(By.id("TaxInfoNextbtn")).click();
    		Thread.sleep(2500);
    		
    		//State Allowance

    		WebElement elem5=f.findElement(By.id("FilingStatus"));
    		
    		cell = sheet.getRow(i).getCell(11);
    		new Select(elem5).selectByValue(cell.getStringCellValue());
    		//new Select(elem5).selectByValue("MARRIED");
    		//new Select(elem5).selectByValue("WITHHOLDASSINGLE");
    		f.findElement(By.id("TotalAllowances")).sendKeys("1");
    		f.findElement(By.xpath("//*[@id='tblEmployeeTaxInfo']/tbody/tr[9]/td[2]/span[1]/label")).click();
    		f.findElement(By.id("StateTaxInfoNextbtn")).click();
    		Thread.sleep(2500);
    		
    		//Payment Method
    		WebElement elem6= f.findElement(By.id("PaymentMethod"));
    		//ew Select(elem6).selectByValue("17");
    		new Select(elem6).selectByValue("16");
    		f.findElement(By.id("DisplayName")).sendKeys("Bank Of America");
    		f.findElement(By.id("RoutingNumber")).sendKeys("102000021");
    		f.findElement(By.id("AccountNumber")).sendKeys("457357373");
    		WebElement elem7= f.findElement(By.id("AccountType"));
    		//new Select(elem7).selectByValue("CHECK");
    		new Select(elem7).selectByValue("SAVINGS");
    		Thread.sleep(1500);
    		f.findElement(By.id("TaxInfoNextbtn")).click();
    		
    		//Finish
    		//f.findElement(By.id("btnAddEmployee")).click();   		
		 }
    		System.out.println("************ Employee 1 added sucessfully ************");
    
    }
	
	
    
   
   
   @Test(priority=3)
   public void Settings() throws InterruptedException
   {
	   f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 //BankAccountDetails
	   Thread.sleep(2000);
	   f.findElement(By.xpath("//a[@id='aConfigure']/span[2]")).click();
	   
	   f.findElement(By.xpath("//a[@id='adirectdebit']/span")).click();
	   f.findElement(By.id("DisplayName")).sendKeys("Harry Potter");
	   f.findElement(By.id("RoutingNumber")).sendKeys("102101645");
	   f.findElement(By.id("AccountNumber")).sendKeys("123456");
	   Thread.sleep(1500);
	   WebElement type= f.findElement(By.id("AccountType"));
	   new Select(type).selectByValue("CHECK");
	   //new Select(type).selectByValue("SAVINGS");
	   f.findElement(By.xpath("//button[@id='TaxInfoNextbtn']")).click();
	   Thread.sleep(1500);
	   
	   //StateTaxSetup
	   f.findElement(By.xpath("//a[@id='aConfigure']/span[2]")).click();
	   f.findElement(By.id("spanActivityLog")).click();
	   
	   try {
		f.findElement(By.linkText("State Tax Setup")).click();
	} catch (Exception e) {
		f.findElement(By.xpath("html/body/div[5]/div[1]/div[2]/div/div/div[1]/div[7]/div/a/span")).click();
	}
	   
	   f.findElement(By.xpath(".//*[@id='divLoadConfigure']/div/div/div/div/div[2]/a")).click();
	   f.findElement(By.id("WithholdingFileNumber")).sendKeys("858584569");
	   f.findElement(By.id("EmployerAccountNumber")).sendKeys("123456");
	   f.findElement(By.id("txtUnemploymentInsuranceRate")).sendKeys("1.40");
	   f.findElement(By.id("btnSaveStateTax")).click();
	   
	   System.out.println("************ State Tax Setup details saved sucessfully ************");
	   
	   //CheckPrinting
	   f.findElement(By.xpath("//a[@id='aConfigure']/span[2]")).click();
	   f.findElement(By.xpath("//a[@id='acheckprinting']/span")).click();
	   	
	   try {
		   f.findElement(By.xpath(".//*[@id='formMemoDetail']/table/tbody/tr[2]/td[2]/span[1]/label")).click();
	   } catch (Exception e) {
		   f.findElement(By.xpath("html/body/div[5]/div[1]/div[2]/div/form/div/div/div[4]/div[2]/div[3]/form/table/tbody/tr[2]/td[2]/span[1]/label")).click();
	   }
	   
	   f.findElement(By.id("btnSaveCheckPrintingType")).click();
	   
	   				//Check On Top
	   
	   try {
		   f.findElement(By.xpath(".//*[@id='formCheckFormat']/div[1]/table/tbody/tr[3]/td/div/div[1]/div[1]/span/label")).click();
	   } catch (Exception e) {
		f.findElement(By.xpath("html/body/div[5]/div[1]/div[2]/div/form/div/div/div[4]/div[1]/form/div[1]/table/tbody/tr[3]/td/div/div[1]/div[1]/span/label")).click();
	   }
	   
	   //Check On Bottom
	   /*try {
		   f.findElement(By.xpath(".//*[@id='formCheckFormat']/div[1]/table/tbody/tr[3]/td/div/div[2]/div[1]/span/label")).click();
	   } catch (Exception e) {
		   f.findElement(By.xpath("html/body/div[5]/div[1]/div[2]/div/form/div/div/div[4]/div[1]/form/div[1]/table/tbody/tr[3]/td/div/div[2]/div[1]/span/label")).click();
	   }*/
	   
	   f.findElement(By.id("btnSaveCheckFormatType")).click();
	   
	   System.out.println("************ Check setting saved sucessfully ************");
	   
	   //BankAccountVerification
	   Thread.sleep(2000);
	   f.findElement(By.xpath("//a[@id='aConfigure']/span[2]")).click();
	   
	   f.findElement(By.xpath("//a[@id='adirectdebit']/span")).click();
	   try {
		f.findElement(By.xpath(".//*[@id='formPaymentDetails']/div[1]/div[1]/div[4]/div[2]/div/div[1]/table/tbody/tr[13]/td/button")).click();
	} catch (Exception e) {
		f.findElement(By.xpath("html/body/div[5]/div[1]/div[2]/div/form/div[1]/div[1]/div[4]/div[2]/div/div[1]/table/tbody/tr[13]/td/button")).click();
	}
	   Thread.sleep(50000);
	   f.findElement(By.id("verifyBankAccount")).click();
	   System.out.println("************ Bank verified added sucessfully ************");
   }
   
   
   
   																		//Documents ESIGN
   
   @Test(priority=4)
   public void ESignature() throws InterruptedException
   {
	   Thread.sleep(2000);
	   f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   f.findElement(By.id("aCompliance")).click();
	  
	   	//FORM 8655
	   f.findElement(By.xpath(".//*[@id='divEmployerDocumentList']/div/table/tbody/tr[1]/td[4]/a")).click();
	   Thread.sleep(6000);
	   f.findElement(By.id("aesign")).click();
	   WebElement element = f.findElement(By.xpath("html/body/div[7]/div/div/form/div[2]/div[3]/div[2]/canvas"));
	   Thread.sleep(6000);
	   Actions actionBuilder=new Actions(f);          
	   Action drawOnCanvas=actionBuilder
	                   .contextClick(element)
	                   .moveToElement(element,8,8)
	                   .clickAndHold(element)
	                   .moveByOffset(120, 120)
	                   .moveByOffset(60,70)
	                   .moveByOffset(-140,-140)
	                   .release(element)
	                   .build();
	   drawOnCanvas.perform();
	   
	   
	   Actions action = new Actions(f);
	   Thread.sleep(2000);
	   action.sendKeys(Keys.ESCAPE).build().perform();
	   
	   f.findElement(By.id("btnSave")).click();
	   Thread.sleep(3000);
	   f.findElement(By.xpath("html/body/div[6]/div/div/form/div[2]/button")).click();
	   Thread.sleep(3000);
	   f.findElement(By.id("aDashboard")).click();
	   
	   Thread.sleep(3000);
	   f.findElement(By.id("aCompliance")).click();
	   
	   System.out.println("************ FORM 8655 signed sucessfully ************");
	   
	   
	 //Bank - Direct Deposit Credit/Debit Authorization Form
	   f.findElement(By.xpath(".//*[@id='divEmployerDocumentList']/div/table/tbody/tr[2]/td[4]/a")).click();
	   Thread.sleep(6000);
	   f.findElement(By.id("aesign")).click();
	   WebElement b1 = f.findElement(By.xpath("html/body/div[7]/div/div/form/div[2]/div[3]/div[2]/canvas"));
	   Thread.sleep(6000);
	   Actions actionBuilder0=new Actions(f);          
	   Action drawOnCanvas0=actionBuilder0
	                   .contextClick(b1)
	                   .moveToElement(b1,8,8)
	                   .clickAndHold(b1)
	                   .moveByOffset(120, 120)
	                   .moveByOffset(60,70)
	                   .moveByOffset(-140,-140)
	                   .release(b1)
	                   .build();
	   drawOnCanvas0.perform();
	   
	   
	   Actions action0 = new Actions(f);
	   Thread.sleep(2000);
	   action0.sendKeys(Keys.ESCAPE).build().perform();
	   
	   f.findElement(By.id("btnSave")).click();
	   Thread.sleep(3000);
	   f.findElement(By.xpath("html/body/div[6]/div/div/form/div[2]/button")).click();
	   Thread.sleep(3000);
	   f.findElement(By.id("aDashboard")).click();
	   
	   Thread.sleep(3000);
	   f.findElement(By.id("aCompliance")).click();
	   System.out.println("************ Direct Depost Form signed sucessfully ************");
	   
	    //Form D128
	   f.findElement(By.xpath(".//*[@id='divEmployerDocumentList']/div/table/tbody/tr[4]/td[4]/a")).click();
	   Thread.sleep(6000);
	   f.findElement(By.xpath("//input[@id='aesign']")).click();
	   
	   
	   WebElement sign1 = f.findElement(By.xpath("html/body/div[7]/div/div/form/div[2]/div[3]/div[2]/canvas"));
	   Thread.sleep(6000);
	   Actions actionBuilder1=new Actions(f);          
	   Action drawOnCanvas1=actionBuilder1
	                   .contextClick(sign1)
	                   .moveToElement(sign1,8,8)
	                   .clickAndHold(sign1)
	                   .moveByOffset(120, 120)
	                   .moveByOffset(60,70)
	                   .moveByOffset(-140,-140)
	                   .release(sign1)
	                   .build();
	   drawOnCanvas1.perform();
	   
	   Thread.sleep(2000);
	   action0.sendKeys(Keys.ESCAPE).build().perform();
	   
	   f.findElement(By.id("btnSave")).click();
	   Thread.sleep(3000);
	   f.findElement(By.xpath("html/body/div[6]/div/div/form/div[2]/button")).click();
	   Thread.sleep(3000);
	   f.get("http://secure.payrollspan.net/compliance");
	   Thread.sleep(3000);
	   
	   System.out.println("************ FROM D128 signed sucessfully ************");
	   
	   //Form UCE 1010
	   f.findElement(By.xpath(".//*[@id='divEmployerDocumentList']/div/table/tbody/tr[5]/td[4]/a")).click();
	   Thread.sleep(6000);
	   f.findElement(By.xpath("//input[@id='aesign']")).click();
	   WebElement sign2 = f.findElement(By.xpath("html/body/div[7]/div/div/form/div[2]/div[3]/div[2]/canvas"));
	   Thread.sleep(6000);
	   Actions actionBuilder2=new Actions(f);          
	   Action drawOnCanvas2=actionBuilder2
	                   .contextClick(sign2)
	                   .moveToElement(sign2,8,8)
	                   .clickAndHold(sign2)
	                   .moveByOffset(120, 120)
	                   .moveByOffset(60,70)
	                   .moveByOffset(-140,-140)
	                   .release(sign2)
	                   .build();
	   drawOnCanvas2.perform();
	   Thread.sleep(2000);
	   action0.sendKeys(Keys.ESCAPE).build().perform();
	   
	   f.findElement(By.id("btnSave")).click();
	   Thread.sleep(3000);
	   f.findElement(By.xpath("html/body/div[6]/div/div/form/div[2]/button")).click();
	   Thread.sleep(3000);
	   System.out.println("************ FORM UCE1010 signed sucessfully ************");
	   Thread.sleep(3000);
	   
	   //South Carolina Electronic Funds Transfer Agreement
	   Thread.sleep(6000);
	   f.findElement(By.id("aCompliance")).click();
	   Thread.sleep(2500);
	   f.findElement(By.xpath(".//*[@id='divEmployerDocumentList']/div/table/tbody/tr[6]/td[4]/a")).click();
	   Thread.sleep(6000);
	   f.findElement(By.xpath("//input[@id='aesign']")).click();
	   WebElement sign3 = f.findElement(By.xpath("html/body/div[7]/div/div/form/div[2]/div[3]/div[2]/canvas"));
	   Thread.sleep(6000);
	   Actions actionBuilder3=new Actions(f);          
	   Action drawOnCanvas3=actionBuilder3
	                   .contextClick(sign3)
	                   .moveToElement(sign3,8,8)
	                   .clickAndHold(sign3)
	                   .moveByOffset(120, 120)
	                   .moveByOffset(60,70)
	                   .moveByOffset(-140,-140)
	                   .release(sign3)
	                   .build();
	   drawOnCanvas3.perform();
	   Thread.sleep(2000);
	   
	   action.sendKeys(Keys.ESCAPE).build().perform();
	   
	   f.findElement(By.id("btnSave")).click();
	   Thread.sleep(3000);
	   f.findElement(By.xpath("html/body/div[6]/div/div/form/div[2]/button")).click();
	   Thread.sleep(3000);
	   f.findElement(By.id("aDashboard")).click();
	   System.out.println("************ South Carolina Electronic Funds Transfer Agreement Form signed sucessfully ************");
	   Thread.sleep(3000);
	   
   }
   
    
   @Test(enabled=false)
    public void Payroll() throws InterruptedException
    {
    		
    		f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		f.findElement(By.id("aPayRoll")).click();
    		
    		String PayPeriod = f.findElement(By.xpath(".//*[@id='divLoadPayrollDashboard']/div[2]/div[1]/div[1]/div/div/div[1]/div[1]/div[1]/div[2]/p[1]/span")).getText();
    		
    		System.out.println("Payroll started for the pay period "+ PayPeriod);
    		
    		try {
				f.findElement(By.linkText("Start")).click();
			} catch (Exception e) {
				f.findElement(By.linkText("Continue Payroll")).click();
			}
    		try {
				f.findElement(By.id("btnRunMultiPayroll")).click();
			} catch (Exception e) {
				System.out.println("Error message is : "+e);
			}
    		//Edit Payroll details
    		/*f.findElement(By.xpath("//a[@id='aShowHideHoursAndEarnings1494']/i")).click();
    		f.findElement(By.xpath("//span[@id='AddMoreHoursAndEarnings1494']/i")).click();
    		WebElement elem=f.findElement(By.id("drpEarnings14942"));
    		new Select(elem).selectByValue("OVERTIME");
    		//new Select(elem).selectByValue("DOUBLEOVERTIME");
    		//new Select(elem).selectByValue("PAIDTIMEOFF");
    		//new Select(elem).selectByValue("SICKHOURS");
    		//f.findElement(By.id("btnNextStep1")).click();
    		f.findElement(By.id("txtEarnings14942")).sendKeys("4");*/
    		f.findElement(By.id("btnNextStep1")).click();
    		Thread.sleep(3000);
    		f.findElement(By.id("btnNextStepDeduction")).click();
    		Thread.sleep(1500);
    		f.findElement(By.id("btnApprove")).click();
    		Thread.sleep(1500);
    		f.findElement(By.id("btnPayrollConfirmation")).click();
    		
    		/*String Data=f.findElement(By.xpath(".//*[@id='tablePay']/tbody")).getText();
    		System.out.println(Data);
    		
    		f.findElement(By.id("btnGotoPayrollSummary")).click();
 
    		String payment =f.findElement(By.xpath("//td[contains(.,'546')]")).getText();
    		Assert.assertEquals(payment, 546.1800);*/
    		String gross = f.findElement(By.xpath("html/body/div[5]/div[1]/div/div[3]/h4[2]/b")).getText();
    		System.out.println("Payroll sucessfully submitted for the pay period "+ PayPeriod + ". Total gross amount is "+ gross);
    		
    		
    		Thread.sleep(1500);
    		f.findElement(By.id("btnGoToPayrollDashboard")).click();
    }
   
   
   @Test(enabled=false)
   public void EmployerDirectory() 
    {
    		//Select & view Employee
    		f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		f.findElement(By.xpath("//a[@id='aEmployee']/span[2]")).click();
    		f.findElement(By.xpath("//td[contains(.,'Hari')]")).click();
    }
    
    @Test(enabled=false)
   public void ReimburementGarnishment()
   {
	   f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   f.findElement(By.xpath("//a[@id='aEmployee']/span[2]")).click();
	   f.findElement(By.xpath("//td[contains(.,'Hari')]")).click();
	   f.findElement(By.id("lideductionandreimbursement")).click();
	   //Garnishment 
	   f.findElement(By.xpath("//*[contains(.,'AddGarnishmentOrDeduction')]")).click();
	   //f.findElement(By.xpath("//div[@id='divEmployeeDeduction']/div[1]/a")).click();
	   f.findElement(By.xpath(".//*[@id='tblGarnishment']/tbody/tr[1]/td[2]/span[1]/label/span/i")).click();
	   WebElement elem=f.findElement(By.id("GarnishmentType"));
	   	//Type
	   	//new Select(elem).selectByValue("CHILDSUPPORT");
	   	//new Select(elem).selectByValue("ALIMONY");
	   	//new Select(elem).selectByValue("BANKRUPTCYDEBTS");
	   	new Select(elem).selectByValue("STUDENTLOANPAYMENTS");
	   	/*new Select(elem).selectByValue("CONSUMERDEBTS");
	   	new Select(elem).selectByValue("NONTAXDEBTSGO");
	   	new Select(elem).selectByValue("FEDERALTAX");
	   	new Select(elem).selectByValue("STATETAX");
	   	new Select(elem).selectByValue("OTHERGARNISHMENT");*/
	   	
	   	//One time payment
	   	f.findElement(By.xpath(".//*[@id='tblGarnishment']/tbody/tr[12]/td[2]/span[2]/label/span/i")).click();
	   	//Every payroll
	   	f.findElement(By.xpath(".//*[@id='tblGarnishment']/tbody/tr[12]/td[2]/span[1]/label")).click();
	   	f.findElement(By.id("NumberOfFrequency")).sendKeys("5");
	   	f.findElement(By.xpath(".//*[@id='tblGarnishment']/tbody/tr[16]/td[2]/span[1]/label/span/i")).click();
	   	f.findElement(By.xpath(".//*[@id='tblGarnishment']/tbody/tr[18]/td[2]/span[1]/label/span/i")).click();
	   	f.findElement(By.id("WithholdValue")).sendKeys("100");
	   	f.findElement(By.id("btnSaveGarnishment")).click();
	   	
	  //Reimburesement
	   	try {
			
			f.findElement(By.xpath(".//*[@id='divEmployeeReimbursement']/div/button")).click();
		} catch (Exception e) {
			f.findElement(By.xpath(".//*[@id='divEmployeeReimbursement']/div[1]/a")).click();
		}
		f.findElement(By.id("ReimbursementDescription")).sendKeys("Food");
	   	f.findElement(By.xpath(".//*[@id='frmSaveEmployeeReimbursement']/div/table[1]/tbody/tr[3]/td[2]/span[1]/label/span/i")).click();
	   	f.findElement(By.id("NumberOfFrequency")).sendKeys("5");
	   	f.findElement(By.xpath(".//*[@id='frmSaveEmployeeReimbursement']/div/table[1]/tbody/tr[7]/td[2]/span[1]/label/span/i")).click();
	   	f.findElement(By.id("ReimbursementAmount")).sendKeys("50");
	   	f.findElement(By.id("btnReimbursementSave")).click();
	   	
	   	
	   
	   
   }
   	
   	@Test(enabled=false)
    public void Benefits()
    {
    		f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		f.findElement(By.xpath("//a[@id='aBenefit']/span[2]")).click();
    		try {
				f.findElement(By.id("btnEmployerBenefits")).click();
			} catch (Exception e) {
				f.findElement(By.id("btnLinkEmployerBenefits")).click();
			}
    		//Insurance Type
    		try {
    				f.findElement(By.linkText("Medical Insurance")).click();
			} catch (Exception e) {
				f.findElement(By.xpath(".//*[@id='ModelBody']/div[2]/div/div[1]/ul/li[1]/a")).click();
			}
    		f.findElement(By.id("BenefitName")).sendKeys("US Medicare Care");
    		//Plan Period
    			//Calendar Year
    		//f.findElement(By.xpath(".//*[@id='tblBenefitDetails']/tbody/tr[3]/td[2]/label[1]/span/i")).click();
    			//Fiscal Year
    		f.findElement(By.xpath(".//*[@id='tblBenefitDetails']/tbody/tr[3]/td[2]/label[2]/span/i")).click();
    			f.findElement(By.id("PlanStartDate")).sendKeys("01/01/2016");
    			f.findElement(By.id("PlanEndDate")).sendKeys("01/01/2017");
    		f.findElement(By.id("NewHireWaitingPeriod")).sendKeys("2");
    		//Benefit Type
    			//Medical
    			f.findElement(By.xpath(".//*[@id='tblBenefitDetails']/tbody/tr[9]/td[2]/label[1]/span/i")).click();
    			//Dental
    			//f.findElement(By.xpath(".//*[@id='tblBenefitDetails']/tbody/tr[9]/td[2]/label[2]")).click();
    			//Vision
    			//f.findElement(By.xpath(".//*[@id='tblBenefitDetails']/tbody/tr[9]/td[2]/label[3]")).click();
    		//Premium Type
    			//Fixed
    			f.findElement(By.xpath(".//*[@id='tblBenefitDetails']/tbody/tr[11]/td[2]/label[1]")).click();
    			f.findElement(By.id("EmployeeDeductionperPay")).sendKeys("100");
    			f.findElement(By.id("CompanyContributionperPay")).sendKeys("100");
    			//Varies
    			//f.findElement(By.xpath(".//*[@id='tblBenefitDetails']/tbody/tr[11]/td[2]/label[2]")).click();
    		f.findElement(By.id("btnBenefitDetailsSaveContinue")).sendKeys("btnBenefitDetailsSaveContinue");
    		
    		f.findElement(By.id("btnAddEmployee")).click();
    		
    		f.findElement(By.linkText("Go to Benefits")).click();
    		
    		//No. of employees covered
    		String num=f.findElement(By.id("btnEmployeeSummary")).getText();
    		System.out.println("***********Number of Employees covered =  "+num+"  **************");
    			
    }
    
    
    @AfterTest(enabled=false)
    public void close()
    {
    		f.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		f.findElement(By.xpath(".//*[@id='navbar-collapse1']/div/ul/li[5]/a/span/img")).click();
    		f.findElement(By.xpath(".//*[@id='ulglobal']/li[2]/a")).click();
    }
}