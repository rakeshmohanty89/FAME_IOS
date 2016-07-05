package Libraries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;






import com.experitest.client.MobileListener;

//import jdk.nashorn.internal.runtime.Context.ThrowErrorManager;




import Resources.ObjectRepository.OR;

public class Lib_App extends Lib_SeeTest {


	public Lib_Global oGbl;
	public OR OR;

	public Lib_App(Lib_Global oGlobal) {
		oGbl = oGlobal;
		OR=oGbl.OR;
		SetGlobalObj(oGlobal);		
	}

	public void Verify_AppOpened() throws Exception{	
		Recovery_Scenarios();

		if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
			
			if(IsElementFound(OR.LP_Text_Terms_Condition))
			{
				Click(OR.LP_BTN_Agree_Terms_Condition);
				Sleep(500);
				Click(OR.LP_BTN_Agree);
				Sleep(5000);
			}
			Sleep(5000);
			if(IsElementFound(OR.LP_Text_Skip))
			{
				Click(OR.LP_Text_Skip);
				Sleep(5000);
			}
		}else
		{
			if(WaitForElement(OR.LP_Text_Skip,30000)){
				Click(OR.LP_Text_Skip);
				Sleep(5000);
			}
		}
		
		if(IsElementFound(OR.LP_Text_AllowLocation))
		{
			Click(OR.LP_Text_OK_Allow);
		}

		if(IsElementFound(OR.LP_Text_AllowNotification))
		{
			Click(OR.LP_Text_OK_AllowNotification);
		}
		
		
		
		if(IsElementFound(OR.LP_Text_Update))
		{
			Click(OR.LP_BTN_OK);
		}

		if(IsElementFound(OR.LP_Text_UpgradeCRT))
		{
			Click(OR.LP_BTN_UpgradeCRT);
		}


		WaitForElement(OR.LP_BTN_Book_flight,20000);
		Report("PASS", "Verify Application is in Launch screen", "Air Canada application Launch screen is displayed", true);
		//		}else{
		//			Report("WARNING", "Verify Application is in Launch screen", "Air Canada application Launch screen is not displayed", true);
		//		}		
	}


	/*	‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: AccountLogout()
	#Author: Ninad
	#Description: This function will Log out the user from ACM+  
	#Date of creation: 05-May-2016
	#Input Parameters: 
	#Name of person modifying: Tester
	#Date of modification: 05-May-2016
	‘***************************************************/
	public void AccountLogOut() throws Exception
	{
		Click(OR.LP_BTN_Menu);
		SwipeWhileNotFound(OR.MO_BTN_ProfileSetting, SwipeDown, 2, true);
		WaitForElement(OR.PS_Title_Header, 3000);
		if(!IsElementFound(OR.PS_BTN_Login))
		{
			SwipeWhileNotFound(OR.PS_BTN_Logout, SwipeDown, 2, true);
			WaitForElement(OR.PS_BTN_Login, 30);
		}
		Click(OR.LP_BTN_Menu);
		Click(OR.MO_BTN_Home);

	}


	/*	‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: Add_CreditCard()
	#Author: Roshith India
	#Description: This function Add new credit card details of the passenger for payment process 
	#Date of creation: 22-Sep-2015
	#Input Parameters: 
	Cardno: Credit card number , month: Card Expiration month, year:Card Expiration Year, Name: Name on the card,country: Billing address(Country), 
	Province: Billing address(Province), street: Billing address(Street), city: Billing address(City), Postalcode: Billing address(Postal)
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/	


	public void Add_CreditCard(String Cardno,String month,String year,String Name,String country,String Province, String street,String city,String Postalcode,String NickName) throws Exception{
		oGbl.ScreenName = "Add Credit Card";
		if(WaitForElement(OR.CCP_Label_CCDetails,20000)||WaitForElement(OR.PS_TXT_EditCardsNumber,20000)){
			ElementSendText(OR.CCP_Textbox_Ccnumber,"");
			Sleep(200);
			Click(OR.CCP_Textbox_Ccnumber);
			SendText(Cardno);
			CloseKeyboard();
			//Type(OR.CCP_Textbox_Ccnumber,Cardno);			
			Type(OR.CCP_Textbox_ExpirationMonth,month);
			Type(OR.CCP_Textbox_ExpirationYear,year);
			Type(OR.CCP_Textbox_Nameoncard,Name);			
			SwipeWhileNotFound(OR.CCP_Label_Country,SwipeDown, 4, false);
			Click(OR.CCP_Label_Country);			
			Click(OR.CCP_Dropdown_Country);
			SendText(country);
			CloseKeyboard();
			Sleep(1000);
			//Type(OR.CCP_Dropdown_Country,country);			
			if(oGbl.Device_Under_Test.startsWith("ios_app")){
				if(WaitForElement(OR.NP_Text_ListedCountry, 60000)){
					if(ElementGetText(OR.NP_Text_ListedCountry).contains(country)){
						Click(OR.NP_Text_ListedCountry);
					}
				}
			}
			else{
				Click(OR.CCP_Country_Suggestion_item);
			}
			SwipeWhileNotFound(OR.CCP_Label_Province,SwipeDown, 4, false);
			if(WaitForElement(OR.CCP_Label_Province,10000)){
				Click(OR.CCP_Label_Province);	
				SwipeWhileNotFound(GetElementForDynamicText(OR.CCP_List_Province,Province),SwipeDown, 4, true);				
			}else{
				//if(WaitForElement(OR.CCP_Label_State,10000)){
				Click(OR.CCP_Label_State);
				Type(OR.CCP_textbox_state,country);				
				Click(OR.CCP_Country_Suggestion_item);				
			}
			SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);			
			Type(OR.CCP_Textbox_Street,street);
			Type(OR.CCP_Textbox_City,city);
			Type(OR.CCP_Textbox_PostalCode,Postalcode);
			Type(OR.CCP_Textbox_NickName,NickName);
			SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);
			Click(OR.CCP_BTN_Save);	
			Sleep(5000);
			VerifyElementNotFound(OR.CCP_Text_MissingInformationWarning);
			WaitForElement(GetElementForDynamicText(OR.CCP_Text_Dynamic,Name), 5000);				
			VerifyElementFound(GetElementForDynamicText(OR.CCP_Text_Dynamic,Name));
		}

	}


	/*	‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: CreditCardPayment()
	#Author: Roshith India
	#Description: This function Add new credit card details of the passenger for payment process 
	#Date of creation: 3-August-2015
	#Input Parameters: 
	Cardno: Credit card number , month: Card Expiration month, year:Card Expiration Year, Name: Name on the card,country: Billing address(Country), 
	Province: Billing address(Province), street: Billing address(Street), city: Billing address(City), Postalcode: Billing address(Postal)
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/



	public void CreditCardPayment(String Cardno,String month,String year,String Name,String country,String Province, String street,String city,String Postalcode) throws Exception{

		oGbl.ScreenName = "Credit Card Payment";
		if(oGbl.Failureflag == 0){
			if((WaitForElement(OR.CCP_Label_CCDetails, 20000)) ||(WaitForElement(OR.Payment_Title_Heading, 20000))){
				Type(OR.CCP_Textbox_Ccnumber,"");
				Click(OR.CCP_Textbox_Ccnumber);
				SendText(Cardno);
				CloseKeyboard();
				//Type(OR.CCP_Textbox_Ccnumber,Cardno);					
				Type(OR.CCP_Textbox_ExpirationMonth,month);
				Type(OR.CCP_Textbox_ExpirationYear,year);
				Type(OR.CCP_Textbox_Nameoncard,Name);
				SwipeWhileNotFound(OR.CCP_Select_Country,SwipeDown, 4, false);
				Click(OR.CCP_Select_Country);
				Click(OR.CCP_Dropdown_Country);
				Type(OR.CCP_Dropdown_Country,country);
				String[] CCP_Country_Suggestion_item = GetElementForDynamicText(OR.CCP_Country_Suggestion_item, country);
				Click(CCP_Country_Suggestion_item);
				SwipeWhileNotFound(OR.CCP_Label_Province,SwipeDown, 4, false);
				if(WaitForElement(OR.CCP_Label_Province,10000)){
					Click(OR.CCP_Label_Province);					
					SwipeWhileNotFound(GetElementForDynamicText(OR.CCP_List_Province,Province),SwipeDown, 4, true);						
				}else{					
					Click(OR.CCP_Label_State);
					Type(OR.CCP_textbox_state,country);
					Click(OR.CCP_Country_Suggestion_item);
				}
				SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);
				Type(OR.CCP_Textbox_Street,street);
				SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);				
				Type(OR.CCP_Textbox_City,city);
				SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);
				Type(OR.CCP_Textbox_PostalCode,Postalcode);
				if(!(oGbl.Chk_status.toLowerCase().contains("yes"))){
					if(IsElementFound(OR.Passenger_Chkbx_enable)){
						Click(OR.Passenger_Chkbx_Save);
						VerifyElementFound(OR.Passenger_Chkbx_disable);
					}
				}
				SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);
				Click(OR.CCP_BTN_Save);
				Sleep(3000);
				if(IsElementFound(OR.SignIn_Textbox_Password)){
					Type(OR.SignIn_Textbox_Password,TD("Pwd"));	
					CloseKeyboard();
					Sleep(5000);
					Click(OR.SignIn_BTN_Signin);
				}
				if(WaitForElement(OR.BS_Title_Heading,60000)){
					Report("PASS","Credit card details","Credit card details is saved ", true);
				}else{
					Report("FAIL","Credit card details","Credit card details is not saved", true);
					oGbl.Failureflag = 1;
				}
			}else{
				Report("FAIL","Credit card payment screen","Credit card payment screen is not displayed", true);
				oGbl.Failureflag = 1;
			}
		}
	}

	//==============================================================================================================================='
	public void Recovery_Scenarios() throws Exception
	{		
		oGbl.Client.addMobileListener(OR.AC_ScreenLoader[1],OR.AC_ScreenLoader[2],new MobileListener() {
			public boolean recover(String arg0, String arg1){	
				String Name="";
				boolean found=true;

				try {							
					if(!WaitForElement_UntilDissapear(OR.AC_ScreenLoader, oGbl.ScreenLoadWaitingTimeInMinute)){
						if(oGbl.Client.isElementFound(OR.AC_ScreenLoaderName[1],OR.AC_ScreenLoaderName[2],Integer.parseInt(OR.AC_ScreenLoaderName[3]))){
							Name=ElementGetText(OR.AC_ScreenLoaderName);
						}								
						Report("FAIL","Check "+Name+" Screen Loader disappeared in "+oGbl.ScreenLoadWaitingTimeInMinute+" minute",Name+" Screen Loader is not disappeared in "+oGbl.ScreenLoadWaitingTimeInMinute+" minute",true);
						found=false;
						oGbl.RecoveryScenarioErrorMsg=Name+" Screen Loader is not disappeared in "+oGbl.ScreenLoadWaitingTimeInMinute+" minute";
					}
					Sleep(1000);
					WaitForElement_UntilDissapear(OR.AC_ScreenLoader, oGbl.ScreenLoadWaitingTimeInMinute);
				} catch (Exception e){found=false;}

				return found;
			}

		});


		//

		oGbl.Client.addMobileListener(OR.AC_ScreenLoader[1],OR.AC_ScreenLoader[2],new MobileListener() {
			public boolean recover(String arg0, String arg1){	
				String Name="";
				boolean found=true;

				try {							
					if(!WaitForElement_UntilDissapear(OR.AC_ScreenLoader, oGbl.ScreenLoadWaitingTimeInMinute)){
						if(oGbl.Client.isElementFound(OR.AC_ScreenLoaderName[1],OR.AC_ScreenLoaderName[2],Integer.parseInt(OR.AC_ScreenLoaderName[3]))){
							Name=ElementGetText(OR.AC_ScreenLoaderName);
						}								
						Report("FAIL","Check "+Name+" Screen Loader disappeared in "+oGbl.ScreenLoadWaitingTimeInMinute+" minute",Name+" Screen Loader is not disappeared in "+oGbl.ScreenLoadWaitingTimeInMinute+" minute",true);
						found=false;
						oGbl.RecoveryScenarioErrorMsg=Name+" Screen Loader is not disappeared in "+oGbl.ScreenLoadWaitingTimeInMinute+" minute";
					}
					Sleep(1000);
					WaitForElement_UntilDissapear(OR.AC_ScreenLoader, oGbl.ScreenLoadWaitingTimeInMinute);
				} catch (Exception e){found=false;}

				return found;
			}

		});



		oGbl.Client.addMobileListener(OR.AC_NetworkNotAvailableErrorMsg[1],OR.AC_NetworkNotAvailableErrorMsg[2],new MobileListener() {
			public boolean recover(String arg0, String arg1) {	

				try {
					String Name="";							
					if(oGbl.Client.isElementFound(OR.AC_NetworkNotAvailableErrorMsgText[1],OR.AC_NetworkNotAvailableErrorMsgText[2],Integer.parseInt(OR.AC_NetworkNotAvailableErrorMsgText[3]))){
						Name=oGbl.Client.elementGetText(OR.AC_NetworkNotAvailableErrorMsgText[1],OR.AC_NetworkNotAvailableErrorMsgText[2],Integer.parseInt(OR.AC_NetworkNotAvailableErrorMsgText[3]));
					}	
					Report("FAIL", "Error PoP Occured", "Error Message --->"+Name,true);
					oGbl.RecoveryScenarioErrorMsg="Error Message --->"+Name;

				} catch (Exception e){return true;}

				return false;
			}

		});


		if(oGbl.SetRecoveryScenarioErrorCheck){

			oGbl.Client.addMobileListener(OR.AC_ErrorPopUp[1],OR.AC_ErrorPopUp[2],new MobileListener() {
				public boolean recover(String arg0, String arg1) {	

					try {
						String Name="";							
						if(oGbl.Client.isElementFound(OR.AC_ErrorPopUpText[1],OR.AC_ErrorPopUpText[2],Integer.parseInt(OR.AC_ErrorPopUpText[3]))){							
							Name=oGbl.Client.elementGetText(OR.AC_ErrorPopUpText[1],OR.AC_ErrorPopUpText[2],Integer.parseInt(OR.AC_ErrorPopUpText[3]));							
						}	
						Report("FAIL", "Error PoP Occured", "Error Message --->"+Name,true);
						oGbl.RecoveryScenarioErrorMsg="Error Message --->"+Name;

					} catch (Exception e){return true;}

					return false;
				}

			});

			oGbl.Client.addMobileListener(OR.AC_ErrorMsgImg[1],OR.AC_ErrorMsgImg[2],new MobileListener() {
				public boolean recover(String arg0, String arg1) {	

					try {
						String Name="";
						if(oGbl.Client.isElementFound(OR.AC_ErrorMsgText[1],OR.AC_ErrorMsgText[2],Integer.parseInt(OR.AC_ErrorMsgText[3]))){
							Name=oGbl.Client.elementGetText(OR.AC_ErrorMsgText[1],OR.AC_ErrorMsgText[2],Integer.parseInt(OR.AC_ErrorMsgText[3]));
						}	
						Report("FAIL", "Error Occured", "Error Message --->"+Name,true);
						oGbl.RecoveryScenarioErrorMsg="Error Message --->"+Name;

					} catch (Exception e){return false;}

					return false;
				}

			});
		}
	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: Functionality_Select()
	#Author: Roshith India
	#Description: This function will select the provided functionality flow from the home page 
	#Date of creation: 3-August-2015
	#Input Parameters: Func: functionality to proceed
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public boolean Functionality_Select(String Func) throws Exception{

		oGbl.ScreenName = "Home";
		if(oGbl.Failureflag == 0){
			if(WaitForElement(OR.LP_BTN_Book_flight,20000)&&WaitForElement(OR.LP_BTN_FlightStatus,5000)&&WaitForElement(OR.LP_BTN_Checkin,5000)){
				//if(IsElementFound(OR.MO_BTN_ProfileSetting)){SendText("{ESC}");}
				String Functionality = "";
				Report("PASS", "Verify Application is in Launch screen", "Book Flight, Flight Status, and Check-in are displayed in Launch screen", true);
				switch(Func.toLowerCase().trim()){
				case "book flight":
					Functionality = "Book Flight";
					Click(OR.LP_BTN_Book_flight);break;
				case "flight status":
					Functionality = "Flight Status";
					Click(OR.LP_BTN_FlightStatus);break;
				case "check in":
					Functionality = "Check In";
					Click(OR.LP_BTN_Checkin);break;
				}
				//Sleep(10000);
				if(WaitForElement(OR.BAT_Text_title,15000) || WaitForElement(OR.FS_BTN_Search,15000) || WaitForElement(OR.CheckIn_Label_Title,15000)) {					
					Report("PASS","Verify" + Functionality + " screen is displayed",Functionality + " screen is displayed", true);
					return true;
				}else{
					Report("FAIL","Verify" + Functionality + " screen is displayed",Functionality + " screen is not displayed", true);
					oGbl.Failureflag = 1;
					return false;												
				}					
			}else{				
				Report("FAIL", "Verify Application is in Launch screen", "Air Canada application Launch screen is not displayed with all options", true);
				oGbl.Failureflag = 1;
				return false;
			}
		}else{
			return false;
		}
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: SearchFlightRound_Trip()
	#Author: Roshith India
	#Description: This function will search the flight for round trip
	#Date of creation: 1-August-2015
	#Input Parameters: source: Origin airport, destination: Destination Airport, Fromdate: Travel Start date,  Todate: Travel to date, adult: No of adult passenger, child: No of Child passenger, youth: No of youth passenger, promo: use promo code or not
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public void SearchFlightRound_Trip(String source, String destination, String Fromdate, String Todate, String adult, String child , String youth, String promo) throws Exception {
		if(oGbl.Failureflag == 0){
			oGbl.ScreenName = "Book a Flight";
			Click(OR.BAT_BTN_RoundTrip);
			String[] BAT_TextBox_Origin_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Origin, "Select origin");
			Click(BAT_TextBox_Origin_Dyn);
			if(WaitForElement(OR.SA_Text_Heading, 60000)){
				Click_Type(OR.SA_Textbox_Searchbox,source);	

				String[] BAT_List_Option_Dyn = GetElementForDynamicText(OR.BAT_List_Option, source.toUpperCase());
				if(!IsElementFound(OR.BAT_Text_title))
				{
					if(!IsElementFound(BAT_List_Option_Dyn)){Click_Type(OR.SA_Textbox_Searchbox,source);}
					Click(BAT_List_Option_Dyn);
				}
				if(WaitForElement(OR.BAT_Text_title, 60000))
				{
					BAT_TextBox_Origin_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Origin, source.toUpperCase());
					String strSA =ElementGetText(BAT_TextBox_Origin_Dyn);
					Report("INFO",strSA +" airport should be selected",strSA +" airport is selected", true);
					String[] BAT_TextBox_Destination_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Destination, "Select destination");
					Click(BAT_TextBox_Destination_Dyn);
					if(WaitForElement(OR.DA_Text_Heading, 60000)){
						Click_Type(OR.DA_Textbox_Searchbox,destination); 
						BAT_List_Option_Dyn = GetElementForDynamicText(OR.BAT_List_Option, destination.toUpperCase());
						if(!IsElementFound(BAT_List_Option_Dyn)){Click_Type(OR.SA_Textbox_Searchbox,destination);}
						Click(BAT_List_Option_Dyn);	
						if(WaitForElement(OR.BAT_Text_title, 60000)){
							BAT_TextBox_Destination_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Destination, destination.toUpperCase());
							String strDA =ElementGetText(BAT_TextBox_Destination_Dyn);
							Report("INFO",strDA +" airport should be selected",strDA +" airport is selected", true);						
							Click(OR.BAT_TextBox_SelectDate);
							if(WaitForElement(OR.Dates_Title_Header, 60000)){
								SelectTravelDate(Fromdate);
								SelectTravelDate(Todate);								
								Click(OR.Dates_BTN_Confirm); 
								if(WaitForElement(OR.BAT_Text_title, 60000)){
									int Noofadult=Integer.parseInt(adult);
									int Noofchild=Integer.parseInt(child);
									int Noofyouth=Integer.parseInt(youth);
									SelectNoofPassenger(Noofadult,Noofchild,Noofyouth);
									if(promo.toLowerCase().equals("yes")){
										//Type(OR.BAT_TextBox_Promocode,TD("promotion_code")); 
									} 
									WaitForElement(OR.BAT_BTN_Search, 3000);
									Click(OR.BAT_BTN_Search);
									int status = 0;
									if(WaitForElement(OR.DF_BTN_RefineSearch, 120000)){
										Report("PASS", "Search Airport", "Navigated to Search Airport page",true);
										status = 1;
									}
									if(status == 0){
										if(WaitForElement(OR.DF_BTN_RefineSearch, 5000)){
											Report("PASS", "Search Airport", "Navigated to Search Airport page",true);
										}else{
											oGbl.Failureflag = 1;
											Report("FAIL", "Search Airport", "Not navigated to Search Airport page",true);
										} 
									}   
								}else{
									Report("FAIL", "Book a Trip ", "Not navigated back to Book a trip page",true);
								}
							}else{
								Report("FAIL", "Select Dates", "Not navigated to Select dates",true);
							}
						}else{
							Report("FAIL", "Book a Trip ", "Not navigated back to Book a trip page",true);
						}
					}else{
						Report("FAIL", "Destination airpot", "Not navigated to Destination airport search page",true);
					}						
				}else{
					Report("FAIL", "Book a Trip ", "Not navigated to Book a trip page",true);
				}
			}else{
				Report("FAIL", "Source airpot", "Not navigated to Source airport search page",true);
			}
		}
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: SelectNoofPassenger()
	#Author: Vijayalakshmi India
	#Description: This function will select the number of passenger(Adult, Child and adult) for travel
	#Date of creation: 1-August-2015
	#Input Parameters: Adult: No of adult passenger, child: No of Child passenger, youth: No of youth passenger
	#Name of person modifying: Roshith
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public void SelectNoofPassenger(int Adult, int child, int youth) throws Exception {
		oGbl.ScreenName = "Passenger selection";
		int sum = Adult + child + youth;
		if (sum <= 4) 
		{
			Click(OR.BAT_TextBox_NoOfPassenger);
			if(WaitForElement(OR.NOP_Title_Header, 60000)){
				Click(OR.NOP_BTN_Decradultcount);
				Sleep(3);
				for(int i = 0;i < Adult;i++){
					Click(OR.NOP_BTN_Incradultcount);
				}			
				for(int i = 0;i < child ;i++){
					Click(OR.NOP_BTN_IncrChildcount);
				}
				for(int i = 0;i < youth;i++){
					Click(OR.NOP_BTN_Incryouthcount);
				}				
				Report("INFO", "Select Passengers", "Selected "+ Adult +" adult "+ youth +" youth & " + child + " child",true);
				Click(OR.NOP_BTN_confirm);
			}
			else{
				Report("FAIL", "Passenger", "Number of passengers given in parameter is invalid",true);
			}
		}else{
			Report("FAIL", "Passenger", "Total Number of passengers passed from datasheet cant be more tha 4. Please change the data in datasheet.",false);
		}
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: SelectTravelDate()
	#Author: Roshith India
	#Description: This function will select the travel date from the calendar 
	#Date of creation: 1-August-2015
	#Input Parameters: Selectdate: Travel date
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/

	public void SelectTravelDate(String Selectdate) throws Exception {
		oGbl.ScreenName = "Date selection";
		String date, month,year;
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		//get current date time with Date()
		Date Currentdate = new Date();
		Date Stringfromdate = dateFormat.parse(Selectdate);
		if(Stringfromdate.before(Currentdate)){
			Report("INFO", "Select Date", "Please provide future date",true);
			oGbl.Failureflag = 1;
		}else{      
			//String Month_year=ElementGetText(OR.Dates_Text_Monthandyear);
			String fromdate[]=Selectdate.split("-");
			date=fromdate[0];
			date = date.replaceFirst("^0+(?!$)", "");
			month=fromdate[1];
			year=fromdate[2];
			String FromMonth_year=month+" "+year;
			Calendar cal = Calendar.getInstance();
			String mnth = new SimpleDateFormat("MMM").format(cal.getTime());
			String strMonth = getMonth(month);
			if(mnth.equals(month)){				
				Click(GetElementForDynamicText(OR.Dates_Text_Date,date));
			}else {
				SwipeElement_ToTopOfThePage(GetElementForDynamicText(OR.Dates_Text_SelectedMonth,strMonth),SwipeDown,10);				
				Click(GetElementForDynamicText(OR.Dates_Text_Date,date));


				/*for(int i  = 0;i<6;i++){
					int j = 0;					
					try{
						if(j == 0){		
							GetElementForDynamicText(OR.Dates_Text_Date,date);
							int intCount = oGbl.Client.getElementCount(Zone1, Element1);
							GetElementForDynamicText(OR.Dates_Text_SelectedMonth,strMonth);
							int Count = oGbl.Client.getElementCount(Zone1, Element1);
							String Sindex = oGbl.Client.elementGetProperty(Zone1, Element1, Count - 1, "y");
							int Monthindex = Integer.parseInt(Sindex.trim());
							for(int k=0;k<intCount;k++){  									
								GetElementForDynamicText(OR.Dates_Text_Date,date);
								String Dindex = oGbl.Client.elementGetProperty(Zone1, Element1, k, "y");
								int Dateindex = Integer.parseInt(Dindex.trim());
								if(Dateindex >= Monthindex){
									String device = oGbl.Device_Under_Test;
									String[] arrDevice =  device.split(":");
									if(arrDevice[1].toLowerCase().contains("Nexus 4") && Dateindex <= 1100){
										GetElementForDynamicText(OR.Dates_Text_Date,date);
										oGbl.Client.click(Zone1, Element1, k, 1);		
									}
									if(!arrDevice[1].toLowerCase().contains("Nexus 4")){
										GetElementForDynamicText(OR.Dates_Text_Date,date);
										oGbl.Client.click(Zone1, Element1, k, 1);		
									}	
									Sleep(1000);
									GetElementForDynamicText(OR.Dates_Text_SelectedDate,date);									
									if(oGbl.Client.waitForElement(Zone1, Element1, Index1, 10000)){
										j = 1;
										break;
									}
								}
							}	

						}
					}catch(Exception ex){

					}
					if(j == 0){
						oGbl.Client.swipe(SwipeDown,oGbl.gSwipe50pY, 500);
						GetElementForDynamicText(OR.Dates_Text_SelectedMonth,strMonth);
						int Count = oGbl.Client.getElementCount(Zone1, Element1);
						oGbl.Client.swipeWhileNotFound("Up",GetOffset("Selectdateup"),2000, Zone1, Element1,Count - 1, 1000, 10, false);
						oGbl.Client.swipe("Up",oGbl.gSwipe50pY, 500);
					}else{
						break;
					}
				}*/

			}

		}
	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: ValidateBooKSummary()
	#Author: Roshith India
	#Description: This function will validate the booking summary page fields 
	#Date of creation: 1-August-2015
	#Input Parameters: source: Origin airport, destination: Destination Airport, bookingtype: one way or round trip, number of passengers: total no of passengers
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public void ValidateBooKSummary(String Source,String Destination,String bookingtype,String numberofpassengers) throws Exception{

		if(oGbl.Failureflag == 0)
		{
			oGbl.ScreenName = "Booking Summary";
			SwipeWhileNotFound(OR.BS_Text_DepartingFlight,SwipeUp, 4, false);

			if(WaitForElement(OR.BS_Title_Heading,20000)){	
				String[] BS_Label_Airport = GetElementForDynamicText(OR.BS_Label_Airport, Source.trim());
				String strText = ElementGetText(BS_Label_Airport);
				if(strText.trim().toLowerCase().contains(Source.trim().toLowerCase()) && strText.trim().toLowerCase().contains(Destination.trim().toLowerCase())){
					Report("PASS", "Verify Source and Destination Airport", "Source and Destination Airport is displayed correctly",true);
					if(bookingtype.trim().toLowerCase().contains("oneway")){
						VerifyElementFound(OR.BS_Text_DepartingFlight);
						VerifyElementFound(OR.BS_Text_DepartingFlightnumber);
					}else
					{
						VerifyElementFound(OR.BS_Text_DepartingFlight);
						String[] BS_Text_DepartingFlightnumber_dyn = GetElementForDynamicIndex(OR.BS_Text_DepartingFlightnumber, 0);
						VerifyElementFound(BS_Text_DepartingFlightnumber_dyn);
						BS_Text_DepartingFlightnumber_dyn = GetElementForDynamicIndex(OR.BS_Text_DepartingFlightnumber, 1);
						VerifyElementFound(BS_Text_DepartingFlightnumber_dyn);
						SwipeWhileNotFound(OR.BS_Text_ReturnFlight,SwipeDown, 4, false);
						try{
							if(!IsFoundIn(OR.BS_Text_ReturnFlight,SwipeDown,OR.DFS_Text_FlightNo,0,0)){
								Swipe(SwipeDown,800,500,"Swipe Down");
							}
						}catch(Exception e){
							Swipe(SwipeDown,800,500,"Swipe Down");
						}
						//IsFoundIn(OR.BS_Text_ReturnFlight,SwipeDown,OR.BS_Text_ReturnFlightnumber,0,0);
						IsFoundIn(OR.BS_Text_ReturnFlight,SwipeDown,BS_Text_DepartingFlightnumber_dyn,0,0);
					}
					String[] BS_Text_PassengerCount=GetElementForDynamicText(OR.BS_Text_PassengerCount_Dynamic, numberofpassengers+","+numberofpassengers);

					SwipeWhileNotFound(BS_Text_PassengerCount,SwipeDown, 3, false);
					//VerifyElementFound(BS_Text_PassengerCount);
					Report("PASS", "Verify number of passengers", "Number of passengers matches",true);
					SwipeWhileNotFound(OR.BS_Label_Payment,SwipeDown, 2, false);
					//VerifyElementFound(OR.BS_Label_Payment);
					SwipeWhileNotFound(OR.BS_Text_FareSummary,SwipeDown, 2, false);
					//VerifyElementFound(OR.BS_Text_FareSummary);
					SwipeWhileNotFound(OR.BS_Text_GrandTotal,SwipeDown, 2, false);
					//VerifyElementFound(OR.BS_Text_GrandTotal);
					SwipeWhileNotFound(OR.BS_Link_Fare,SwipeDown, 2, false);
					//VerifyElementFound(OR.BS_Link_Fare);
					SwipeWhileNotFound(OR.BS_BTN_Paynow,SwipeDown, 2, false);
					//VerifyElementFound(OR.BS_BTN_Paynow);
					SwipeWhileNotFound(OR.BS_BTN_StartNewSearch,SwipeDown, 4, false);
					//VerifyElementFound(OR.BS_BTN_StartNewSearch);
				}else{
					Report("FAIL", "Verify Source and Destination Airport", "Source and Destination Airport is not displayed correctly",true);
				}
			}else{
				Report("FAIL","Verify Booking Summary screen","Booking Summary screen is not displayed", false);
				oGbl.Failureflag = 1;
			}
			//SwipeWhileNotFound(OR.BS_Text_DepartingFlight,SwipeUp, 4, false);
			Swipe(SwipeUp,800,500,"Search");
			Swipe(SwipeUp,800,500,"Search");
			Swipe(SwipeUp,800,500,"Search");
			
			String device = oGbl.Device_Under_Test;
			if(device.equalsIgnoreCase("adb:MotorolaX"))
			{
				Swipe(SwipeDown,1150,500,"Search");
			}
		}else{
			Report("FAIL","Error Occured","Unexpected error", false);
		}

	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: ValidateConfirmation()
	#Author: Roshith India
	#Description: This function will validate the confirmation page  
	#Date of creation: 1-August-2015
	#Input Parameters: 
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public String ValidateConfirmation() throws Exception
	{
		String PNRno = "";

		oGbl.ScreenName = "Trip Itinery";
		if(WaitForElement(OR.TI_Text_TripItinerary,120000))
		{
			Report("PASS", "Verify Booking confirmation", "Booking confirmation is displayed",true);
			PNRno = ElementGetText(OR.TI_Text_PNRno).trim();
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				PNRno = PNRno.split(":")[1].trim();
			}
			Report("PASS", "PNR number generated", "PNR [" +PNRno+ "] generated successfully",true);
			VerifyElementFound(OR.TI_ConformationCopy);
			VerifyElementFound(OR.TI_Text_ConformationEmail);
			String actEmailId= ElementGetText(OR.TI_Text_ConformationEmail).toLowerCase();
			try
			{
				if(actEmailId.contains(TD("Passenger1_Email").toLowerCase()) || actEmailId.contains(TD("Uname").toLowerCase()))
				{
					Report("PASS", "Trip Itinery Screen", "Expected and actual email id [" + actEmailId + "] matched in Trip Itinery booking confirmation Screen",true);
				}else
				{
					Report("FAIL", "Trip Itinery Screen", "Expected email id [" + TD("Passenger1_Email") + "] and actual email id [" + actEmailId + "] didn't matched in Trip Itinery booking confirmation Screen",true);
				}
			}catch(Exception e)
			{}
			SwipeWhileNotFound(OR.TI_Link_SeatSelection, SwipeDown, 3, false);
			//VerifyElementFound(OR.TI_Link_SeatSelection);
			VerifyElementFound(OR.TI_Link_ViewTrip);
			VerifyElementFound(OR.TI_Text_AddTravelOption);
			Click(OR.Home_Btn);
			//SwipeWhileNotFound(OR.TI_BTN_Done, SwipeDown, 3, true);
		}else{
			Report("FAIL", "Verify Booking confirmation", "Booking confirmation is not displayed",true);
		}

		return PNRno;

	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: SelectFlight()
	#Author: Roshith India
	#Description: This function will select the particular flight from the list using flight number and type 
	#Date of creation: 1-August-2015
	#Input Parameters: source: Origin airport, destination: Destination Airport, Sortby: sort the listed flight, Fighttype: direct or connetion, QuoteShareflag: Quote share or not, FlightNo: flight number to select
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public void SelectFlight(String Source,String Destination,String Sortby,String Fighttype,int QuoteShareflag,String FlightNo) throws Exception{

		oGbl.ScreenName = "Select Flight";
		if(oGbl.Failureflag == 0){
			if(WaitForElement(OR.DF_BTN_RefineSearch, 20000)){
				String DF_Text_Tripdescription_Dyn[] = GetElementForDynamicText(OR.DF_Text_Tripdescription, Source.toUpperCase());
				String strText = ElementGetText(DF_Text_Tripdescription_Dyn);
				if(strText.trim().toLowerCase().contains(Source.trim().toLowerCase()) && strText.trim().toLowerCase().contains(Destination.trim().toLowerCase())){
					Report("PASS", "Verify Source and Destination Airport", "Source and Destination Airport is displayed correctly",true);
					switch(Sortby.toLowerCase().trim()){
					case "time":
						Click(OR.DF_BTN_time);break;
					case "price":
						Click(OR.DF_BTN_Price);break;
					case "duration":
						Click(OR.DF_BTN_Duration);break;
					}

					int type = 0;
					if(Fighttype.toLowerCase().trim().contains("direct")){
						if(!IsElementFound(OR.DF_Text_connectioFlights))
						{
							SwipeWhileNotFound(OR.DF_Text_Directflights,SwipeDown,3, false);
						}
						type = 0;
					}else{
						SwipeWhileNotFound(OR.DF_Text_connectioFlights,SwipeDown,10, false);							
						type = 1;
					}
					int Status = 0;
					if(!FlightNo.isEmpty()){							 
						String[] flightnumber=GetElementForDynamicText(OR.DF_Text_FlightNo,FlightNo);

						if(SwipeWhileNotFound(flightnumber,SwipeDown,5, true))
						{
							Report("PASS", "Verify Selecting flight", FlightNo + " flight is successfully selected",true);
							Status = 1;	
						}else{
							Status = 0;	
							Report("WARNING", "Verify Selecting flight", FlightNo + " flight not found in list",true);
						}

					}
					if(Status == 0)
					{
						Report("WARNING", "Selecting Default flight", FlightNo + " flight is not available. Hence Selecting Default flight",true);
						FlightNo = ElementGetText(OR.DF_Text_FlightNumber);
						if(SwipeWhileNotFound(OR.DF_Text_FlightNumber,SwipeUp,1, false))
						{
							Click(OR.DF_Text_FlightNumber);
							Report("PASS", "Selecting Deafult flight", FlightNo + " flight is successfully selected",true);
							Status = 1;	
						}else{
							Report("FAIL", "Selecting Deafult flight", FlightNo + " flight not found in list",true);
						}

					}
					if(Status == 1){
						Report("PASS", "Verify Selecting flight", "Successfully selected the required flight" + FlightNo,true);
					}else{
						Report("FAIL", "Verify Selecting flight", "Unable to find the required flight " + FlightNo,true);
					}
				}else{
					Report("FAIL", "Verify Source and Destination Airport", "Source and Destination Airport is not displayed correctly",true);
				}
			}else{
				Report("FAIL", "Verify flight selection screen", "flight selection screen is not displayed",true);
				oGbl.Failureflag = 1;
			}
		}		
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: SignIn()
	#Author: Vijayalakshmi India
	#Description: This function will either continue as guest user or sign in with valid credentials 
	#Date of creation: 1-August-2015
	#Input Parameters: guest: Guest user or not, email: User Email id, password: User password
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/

	public void SignIn(String guest,String email, String password) throws Exception {
		oGbl.ScreenName = "Sign In";
		if(WaitForElement(OR.SignIn_Text_signin, 60000))
		{
			Report("PASS", "SignIn Page", "Navigated to SignIn Page",true);
			if((guest.toUpperCase()).contains("YES")){				
				SwipeWhileNotFound(OR.SignIn_BTN_continue_As_Guest,SwipeDown,5,false);
				Click(OR.SignIn_BTN_continue_As_Guest);
			}
			else{
			   
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				ElementSendText(OR.SignIn_Textbox_Email,email);
				CloseKeyboard();
			}else{
 					Type(OR.SignIn_Textbox_Email,email);		
			}
				Type(OR.SignIn_Textbox_Password,password);	
				CloseKeyboard();
				Sleep(5000);
				SwipeWhileNotFound(OR.SignIn_BTN_Signin,SwipeDown,5,false);
				Click(OR.SignIn_BTN_Signin);
			}
		}else{
			Report("FAIL", "SignIn Page", "Not navigated to SignIn Page",true);
			oGbl.Failureflag = 1;
		}
	}


	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: searchFlightoneway()
	#Author: Vijayalakshmi India
	#Description: This function search flight for one way trip 
	#Date of creation: 1-August-2015
	#Input Parameters: source: Origin airport, destination: Destination Airport, selectdate: Travel Start date, adult: No of adult passenger, child: No of Child passenger, youth: No of youth passenger, promo: use promo code or not
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public void searchFlightoneway(String source, String destination, String selectdate, String adult, String child , String youth ,String promo) throws Exception {

		oGbl.ScreenName = "Book a Trip";
		if(oGbl.Failureflag == 0){
			Click(OR.BAT_BTN_OneWay);
			String[] BAT_TextBox_Origin_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Origin, "Select origin");
			Click(BAT_TextBox_Origin_Dyn);
			if(WaitForElement(OR.SA_Text_Heading, 60000)){
				Click_Type(OR.SA_Textbox_Searchbox,source);	
				String[] BAT_List_Option_Dyn = GetElementForDynamicText(OR.BAT_List_Option, source.toUpperCase());
				if(!IsElementFound(OR.BAT_Text_title))
				{
					if(!IsElementFound(BAT_List_Option_Dyn)){Click_Type(OR.SA_Textbox_Searchbox,source);}
					Click(BAT_List_Option_Dyn);
				}
				if(WaitForElement(OR.BAT_Text_title, 60000))
				{
					BAT_TextBox_Origin_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Origin, source.toUpperCase());
					String strSA =ElementGetText(BAT_TextBox_Origin_Dyn);
					Report("INFO",strSA +" airport should be selected",strSA +" airport is selected", true);
					String[] BAT_TextBox_Destination_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Destination, "Select destination");
					Click(BAT_TextBox_Destination_Dyn);
					if(WaitForElement(OR.DA_Text_Heading, 60000)){
						Click_Type(OR.DA_Textbox_Searchbox,destination);    
						BAT_List_Option_Dyn = GetElementForDynamicText(OR.BAT_List_Option, destination.toUpperCase());
						if(!IsElementFound(BAT_List_Option_Dyn)){Click_Type(OR.SA_Textbox_Searchbox,destination);}
						Click(BAT_List_Option_Dyn);						
						if(WaitForElement(OR.BAT_Text_title, 60000)){
							BAT_TextBox_Destination_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Destination, destination.toUpperCase());
							String strDA =ElementGetText(BAT_TextBox_Destination_Dyn);
							Report("INFO",strDA +" airport should be selected",strDA +" airport is selected", true);						
							Click(OR.BAT_TextBox_SelectDate); 
							if(WaitForElement(OR.Dates_Title_Header, 60000)){
								SelectTravelDate(selectdate);
								Click(OR.Dates_BTN_Confirm); 
								if(WaitForElement(OR.BAT_Text_title, 60000)){
									SelectNoofPassenger(Integer.parseInt(adult),Integer.parseInt(child),Integer.parseInt(youth));
									if(promo.toLowerCase().equals("yes")){
										//Type(OR.BAT_TextBox_Promocode,TD("promotion_code"));
									}           
									WaitForElement(OR.BAT_BTN_Search, 3000);
									Click(OR.BAT_BTN_Search);

									if(WaitForElement(OR.DF_BTN_RefineSearch, 60000)){
										Report("PASS", "Search Airport", "Navigated to Search Airport page",true);

									}else{
										oGbl.Failureflag = 1;
										Report("FAIL", "Search Airport", "Not navigated to Search Airport page",true);
									} 

								}else{
									Report("FAIL", "Book a Trip ", "Not navigated back to Book a trip page",true);                     

								}
							}else{
								Report("FAIL", "Select Dates", "Not navigated to Select dates",true);
							}

						}else{
							Report("FAIL", "Book a Trip ", "Not navigated back to Book a trip page",true);
						}
					}else{
						Report("FAIL", "Destination airpot", "Not navigated to Destination airport search page",true);
					}
				}else{
					Report("FAIL", "Book a Trip ", "Not navigated to Book a trip page",true);
				}
			}else{
				Report("FAIL", "Source airpot", "Not navigated to Source airport search page",true);
			}
		}

	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: searchFlightone()
	#Author: Vijayalakshmi India
	#Description: This function search flight for one way trip 
	#Date of creation: 1-August-2015
	#Input Parameters: source: Origin airport, destination: Destination Airport, selectdate: Travel Start date, adult: No of adult passenger, child: No of Child passenger, youth: No of youth passenger, promo: use promo code or not
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public void searchFlight(String source, String destination, String selectdate, String adult, String child , String youth ,String promo) throws Exception 
	{



	}

	/*‘**************************************************
    #Project Name: Air Canada Mobile Automation
    #Function Name: SelectFligthclass()
    #Author: Vijayalakshmi India
    #Description: This function will the class and sub class in which passenger wants to travel
    #Date of creation: 1-August-2015
    #Input Parameters: classname: Class name, classnametype: Sub class name, rountripflag: either one way or round trip
    #Name of person modifying: Vijayalakshmi
    #Date of modification: 21-Sep-2015
    ‘***************************************************/
	public void SelectFligthclass(String classname, String classnametype, int rountripflag) throws Exception {
		oGbl.ScreenName = "Flight Class";
		if(oGbl.Failureflag == 0){			
			if(WaitForElement(OR.RFS_Text_Title, 60000)){
				Report("PASS", "Fligth class Selection", "Navigated to Fligth class selection page",true);
				if(classname.toLowerCase().contains("premium")){                     
					Click(OR.DFS_Text_PEconomytitle);
				}else if(classname.toLowerCase().contains("economy")){
					Click(OR.DFS_Text_Economytitle);
				}else{
					Click(OR.DFS_Text_Businesstitle);
				}

				SwipeWhileNotFound(GetElementForDynamicText(OR.DFS_BTN_ClassType,classnametype), SwipeDown, 2, false);

				if(IsElementFound(GetElementForDynamicText(OR.DFS_BTN_ClassType,classnametype)))
				{
					Click(GetElementForDynamicText(OR.DFS_BTN_ClassType,classnametype));
					String button_text=GetTextIn(OR.DFS_BTN_ClassSelection,"Inside",0,0);
					if(button_text.contains(classnametype)){
						Click(OR.DFS_BTN_ClassSelection);
					}else{
						Report("WARNING", "Class Selection", ""+classnametype+" is not listed",true);
					}
				}else{
					//oGbl.Failureflag = 1;
					Report("WARNING", "Default Class Selection", ""+classnametype+" is not listed. Hence selecting default class.",true);
					Click(OR.DFS_Text_Economytitle);
					if(IsElementFound(OR.DFS_BTN_TangoClassType))
					{
						Click(OR.DFS_BTN_TangoClassType);
					}else if(IsElementFound(OR.DFS_BTN_FlexClassType))
					{
						Click(OR.DFS_BTN_FlexClassType);
					}else if(IsElementFound(OR.DFS_BTN_LatitudeClassType))
					{
						Click(OR.DFS_BTN_LatitudeClassType);
					}
					Click(OR.DFS_BTN_ClassSelection);
					Report("PASS", "Default Class Selection", "Selecting default class.",true);

				}
				Sleep(3000);
				if(!(oGbl.status==1)){
					if(rountripflag == 0)
					{
						if(!WaitForElement(OR.FSRT_Text_FlightSummary, 12000))
						{
							if(WaitForElement(OR.TO_Text_Title, 50000)){
								Report("PASS", "Travel option", "Navigated to Travel option",true);
							}else{
								oGbl.Failureflag = 1;
								Report("WARNING", "Travel option", "Not navigated to Travel option",true);
							}
						}
					}
				}
			}else{
				Report("FAIL", "Fligth class Selection", "Not navigated to Fligth class selection page",true);
			}

		}

	}

	/*‘**************************************************
		#Project Name: Air Canada Mobile Automation
		#Function Name: selectPassenger()
		#Author: Roshith India
		#Description: This function will either select the existing profile as passenger or will add new passenger details
		#Date of creation: 1-August-2015
		#Input Parameters: number: Passenger number, Existing: use existing profile or add new details, 
		#Name of person modifying: Tester
		#Date of modification: 1-Sep-2015
		‘***************************************************/
	public void selectPassenger(String number,String Existing) throws Exception{
		if(oGbl.Failureflag == 0)
		{
			oGbl.ScreenName = "Booking Summary(Passenger)";
			int index = Integer.parseInt(number) - 1;			
			if(oGbl.Device_Under_Test.toLowerCase().contains("nexus4")||oGbl.Device_Under_Test.toLowerCase().contains("nexus 4")){
				OR.BS_Text_NoofPassenger = GetElementForDynamicIndex(OR.BS_Text_NoofPassenger,index);
				SwipeWhileNotFound(OR.BS_Text_NoofPassenger,SwipeDown,10, false);
				String Dindex =ElementGetProperty(OR.BS_Text_NoofPassenger,"y");
				int index1 = Integer.parseInt(Dindex);
				if(index1 <= 1100){
					Click(OR.BS_Text_NoofPassenger);							
				}else{
					SwipeWhileNotFound(OR.BS_Text_NoofPassenger,SwipeDown,10, true);
				}
			}else{
				OR.BS_Text_NoofPassenger = GetElementForDynamicIndex(OR.BS_Text_NoofPassenger,index);		
				SwipeWhileNotFound(OR.BS_Text_NoofPassenger,SwipeDown,10, true);	
			}					
			int Status = 0;
			if(Existing.toLowerCase().contains("yes")){
				if(!number.equals("1")){					
					int Count = GetElementCount(OR.BS_Label_HeaderPassenger);
					if(Count >= index){
						SwipeWhileNotFound(OR.BS_Label_HeaderPassenger,SwipeDown,10, true);
						Click(OR.BS_BTN_Replace);
						Status = 1;
					}
				}else{
					Status = 1;		
					Click(OR.BS_Label_ExistingPassenger);
					Sleep(2000);
				}
			}


		}
	}




	/*‘**************************************************
		#Project Name: Air Canada Mobile Automation
		#Function Name: selectPassenger()
		#Author: Roshith India
		#Description: This function will either select the existing profile as passenger or will add new passenger details
		#Date of creation: 1-August-2015
		#Input Parameters: String number,String Existing, boolean guestUser
		#Name of person modifying: Tester
		#Date of modification: 1-Sep-2015
		‘***************************************************/
	public void selectPassenger(String number,String Existing, boolean guestUser) throws Exception{
		if(oGbl.Failureflag == 0)
		{
			oGbl.ScreenName = "Booking Summary(Passenger)";
			int index = Integer.parseInt(number) - 1;			
			if(oGbl.Device_Under_Test.toLowerCase().contains("Nexus 4")){
				OR.BS_Text_NoofPassenger = GetElementForDynamicIndex(OR.BS_Text_NoofPassenger,index);
				SwipeWhileNotFound(OR.BS_Text_NoofPassenger,SwipeDown,10, false);
				Swipe(SwipeUp,800,500,"Search");
				String Dindex =ElementGetProperty(OR.BS_Text_NoofPassenger,"y");
				int index1 = Integer.parseInt(Dindex);
				if(index1 <= 1100){
					Click(OR.BS_Text_NoofPassenger);							
				}else{
					SwipeWhileNotFound(OR.BS_Text_NoofPassenger,SwipeDown,10, true);
				}
			}else{
				//Swipe(SwipeDown, 800, 500,"SwipeDown");
				String[] BS_Text_NoofPassenger;
				if(oGbl.Device_Under_Test.startsWith("ios_app"))
				{
					if(IsElementFound(OR.BS_Text_NoofPassenger))
					{
						BS_Text_NoofPassenger = GetElementForDynamicIndex(OR.BS_Text_NoofPassengerOnScreen,index);
					}else
					{
						BS_Text_NoofPassenger = GetElementForDynamicText(OR.BS_Text_SelectNoofPassenger,number);
					}					
				}else
				{
					BS_Text_NoofPassenger = GetElementForDynamicIndex(OR.BS_Text_NoofPassenger,index);
				}
				SwipeWhileNotFound(BS_Text_NoofPassenger,SwipeDown,2, false);
				Swipe(SwipeUp,800,500,"Search");
				Sleep(5000);
				oGbl.SetRecoveryScenarioErrorCheck=false;
				if(IsElementFound(BS_Text_NoofPassenger))
				{
					oGbl.SetRecoveryScenarioErrorCheck=true;
					oGbl.SetRecoveryScenarioErrorCheck=false;
					Click(BS_Text_NoofPassenger);
					oGbl.SetRecoveryScenarioErrorCheck=true;
					oGbl.SetRecoveryScenarioErrorCheck=false;
					Sleep(5000);
					if(IsElementFound(BS_Text_NoofPassenger))
					{
						oGbl.SetRecoveryScenarioErrorCheck=true;
						oGbl.SetRecoveryScenarioErrorCheck=false;
						Click(BS_Text_NoofPassenger);
						
						oGbl.SetRecoveryScenarioErrorCheck=true;
					}
					
				}
			}					
			int Status = 0;
			if(Existing.toLowerCase().contains("yes")){
				if(!number.equals("1")){					
					int Count = GetElementCount(OR.BS_Label_HeaderPassenger);
					if(Count >= index){
						SwipeWhileNotFound(OR.BS_Label_HeaderPassenger,SwipeDown,10, true);
						Click(OR.BS_BTN_Replace);
						Status = 1;
					}
				}else{
					Status = 1;
					SendText("{ESC}");
					Sleep(2000);
				}
			}
			if(guestUser == true && index == 0)
			{
				if(!(WaitForElement(OR.Passenger_Label_Passenger, 2000))){
					if((Status == 0) && (SwipeWhileNotFound(OR.SP_BTN_AddnewPassenger,SwipeDown,2, false))){
						//SwipeWhileNotFound(OR.SP_BTN_AddnewPassenger,SwipeDown,10, true);
						Click(OR.SP_BTN_AddnewPassenger);
					}else if((Status == 0) && (!WaitForElement(OR.Passenger_Label_Passenger, 2000)) && SwipeWhileNotFound(OR.SP_BTN_AddnewPassenger,SwipeDown,2, false)){
						//SwipeWhileNotFound(OR.SP_BTN_AddnewPassenger,SwipeDown,10, true);
						Click(OR.SP_BTN_AddnewPassenger);
					}
				}
			}else if(guestUser == false)
			{
				if((Status == 0) && (SwipeWhileNotFound(OR.SP_BTN_AddnewPassenger,SwipeDown,2, false))){
					//SwipeWhileNotFound(OR.SP_BTN_AddnewPassenger,SwipeDown,10, true);
					Click(OR.SP_BTN_AddnewPassenger);
				}else if((Status == 0) && (!WaitForElement(OR.Passenger_Label_Passenger, 2000)) && SwipeWhileNotFound(OR.SP_BTN_AddnewPassenger,SwipeDown,2, false)){
					//SwipeWhileNotFound(OR.SP_BTN_AddnewPassenger,SwipeDown,10, true);
					Click(OR.SP_BTN_AddnewPassenger);
				}
			}

		}
	}

	/*‘**************************************************
    #Project Name: Air Canada Mobile Automation
    #Function Name: TravelOption()
    #Author: Ibrahim India
    #Description: This function select the necessary Addon for the selected flights 
    #Date of creation: 12-Oct-2015
    #Input Parameters: TravelOption: Addon, departurefligth: Add addon in Departure flight, returnflight:Add addon in return flight, Validationflag: Click submit button or not
    #Name of person modifying: Ibrahim
    #Date of modification: 12-Oct-2015
    ‘***************************************************/

	public void TravelOption(String TravelOption,boolean departurefligth,boolean returnflight,int Validationflag) throws Exception{
		String Meal,OnMyWay,MapleLeafLounge,Depature,Return,AddToFlight,Tarvel_Option,AddToTravelOption;
		Meal="Prepaid Air Canada";
		OnMyWay="On My Way";
		MapleLeafLounge="Maple Leaf Lounge";
		Depature="Add to departure";
		Return="Add to return";
		AddToFlight="Add to flight";

		if(WaitForElement(OR.TO_Text_Title, 2000)){ 
			Report("PASS", "Travel option", "Navigated Travel option page",true);

			if(TravelOption.equalsIgnoreCase(Meal)){Tarvel_Option=Meal;}				
			else if(TravelOption.equalsIgnoreCase(OnMyWay)){Tarvel_Option=OnMyWay;}
			else{Tarvel_Option=MapleLeafLounge;}

			if(departurefligth){AddToTravelOption=Depature;}
			else if(returnflight){AddToTravelOption=Return;}
			else{AddToTravelOption=AddToFlight;}

			String[] SelectTarvelOption = null;
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				SelectTarvelOption=GetElementForDynamicText(OR.AC_Text_Dynamic, AddToTravelOption);
			}else
			{
				SelectTarvelOption=GetElementForDynamicText(OR.TO_BTN_DynamicSelection, AddToTravelOption+","+Tarvel_Option);
			}
			if(SwipeWhileNotFound(SelectTarvelOption, SwipeDown, 5, true)){				
				Report("INFO", "Add Otption", "Added for "+TravelOption+ "Option for "+AddToTravelOption,true);				
			}else{Report("FAIL", "Add Otption", "Not Added for "+TravelOption+ "Option for "+AddToTravelOption,true);}

			if(Validationflag == 1){
				Sleep(3000);
				Click(OR.TO_BTN_confirm);
				if(WaitForElement(OR.FSRT_Text_DepartingFlight,120000)){
					Report("PASS", "Flight summary page", "Flight summary page is displayed",true);
				}else{
					oGbl.Failureflag = 1;
					Report("FAIL", "Flight summary page","Flight summary page is not displayed",true);
				}
			}

		}else{
			Report("WARNING", "Travel option", "Not Navigated To Travel option page",true);
		}

	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: AddPassengerDetails()
	#Author: Vijayalakshmi India
	#Description: This function Add new credit card details of the passenger for payment process 
	#Date of creation: 1-August-2015
	#Input Parameters: sal: Passenger Salutation, Fname: Passenger First name, Mname:Passenger Middle name, Lname:Passenger Last name, AreaCode:Passenger Area code, MNo:Passenger mobile number, email:Passenger email id, gender:Passenger gender, date:Passenger Date of birth, mon: Passenger date of birth(Month), year: Passenger birth year,redress: Passenger Redress number ,Travelno: Passenger Travel number ,mealpreference: Passenger Meal preference,  seatpreference: Passenger Seat preference, frequentflyer: Passenger Frequent flyer number
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/

	public void AddPassengerDetails(String No) throws Exception{
		oGbl.ScreenName = "Add Passenger";
		if(oGbl.Failureflag == 0){
			String sal = TD("Passenger"+No+"_Salutation");
			String Fname = TD("Passenger"+No+"_Fname");
			String Mname = TD("Passenger"+No+"_Mname");
			String Lname = TD("Passenger"+No+"_Lname");
			String AreaCode = TD("Passenger"+No+"_Countrycd");
			String MNo = TD("Passenger"+No+"_Mobile");
			String email = TD("Passenger"+No+"_Email");
			String gender = TD("Passenger"+No+"_Gender");
			String date = TD("Passenger"+No+"_Date");
			String mon = TD("Passenger"+No+"_Month");
			String year = TD("Passenger"+No+"_Year");
			String redress = TD("Passenger"+No+"_Redress");
			String Travelno = TD("Passenger"+No+"_TravelNo");
			String mealpreference = TD("Passenger"+No+"_Meal");
			String seatpreference = TD("Passenger"+No+"_SeatPref");
			String frequentflyer = TD("Passenger"+No+"_FreqFly");
			if(WaitForElement(OR.Passenger_Label_Passenger, 60000)){
				Click(OR.Passenger_Textbox_Salutation);
				WaitForElement(OR.Passenger_DropDown_Salutation, 6000);
				Sleep(2000);
				if(!oGbl.Device_Under_Test.startsWith("ios_app"))
				{
					Type(OR.Passenger_DropDown_Salutation,sal);	
				}
				Click(GetElementForDynamicText(OR.Passenger_Text_Salutation,sal));	
				WaitForElement(OR.Passenger_Textbox_FirstName, 3000);
				Type(OR.Passenger_Textbox_FirstName,Fname);				
				if(!oGbl.Device_Under_Test.startsWith("ios_app"))
				{
					SwipeElement_ToTopOfThePage(OR.Passenger_Textbox_FirstName, SwipeDown, 3);
				}
				Type(OR.Passenger_Textbox_MiddleName,Mname);
				Type(OR.Passenger_Textbox_LastName,Lname);
				SwipeWhileNotFound(OR.Passenger_Label_EmailAddress,SwipeDown,1, false);
				Type(OR.Passenger_Textbox_EmailAddress,email);
				Sleep(5000);
				SwipeWhileNotFound(OR.Passenger_Text_SearchCountryCode,SwipeDown,1, true); 
				if(WaitForElement(OR.Passenger_Textbox_AreaCode, 60000)){
					Click(OR.Passenger_Text_ListedCountry);
					SendText(AreaCode);	
					String[] CountryInList;
					if(oGbl.Device_Under_Test.startsWith("ios_app"))
					{
						CountryInList=GetElementForDynamicIndex(GetElementForDynamicText(OR.AC_Text_Dynamic,AreaCode),2);
						Click(CountryInList);
					}else
					{
						CountryInList=GetElementForDynamicIndex(GetElementForDynamicText(OR.AC_Text_Dynamic,AreaCode),1);
						if(!IsElementFound(CountryInList)){SendText(AreaCode);}
						if(ElementGetText(OR.Passenger_Text_ListedCountry).contains(AreaCode)){
							Click(CountryInList);
						}
						else
						{
							Report("FAIL", "Country", "Country is not Listed",true);
						}
					}


				}
				else
					Report("FAIL", "Country", "Country is not Listed",true);
			}
			else
				Report("FAIL", "Country", "Country is not selected please provide the correct name",true);

			/*WaitForElement(OR.Passenger_Textbox_EmailAddress, 6000);
			Type(OR.Passenger_Textbox_EmailAddress,email);*/
			Type(OR.Passenger_Textbox_MobileNumber,MNo);
			SwipeWhileNotFound(OR.Passenger_Label_Gender,SwipeDown,5, false);

			if(((gender).toUpperCase()).contains("FEMALE")){
				Click(OR.Passenger_BTN_Female);
			}
			else{
				Click(OR.Passenger_BTN_Male);
			}
			Type(OR.Passenger_Textbox_DayofDOB,date);

			Type(OR.Passenger_TextBox_MonthofDOB,mon);

			Type(OR.Passenger_Textbox_YearofDOB,year);

			if(!redress.isEmpty()){Type(OR.Passenger_Textbox_RedressName,redress);}

			if(!Travelno.isEmpty()){Type(OR.Passenger_Textbox_KnownTravelnumber,Travelno);}

			SwipeWhileNotFound(OR.Passenger_BTN_Save,SwipeDown,1, false);
			Click(OR.Passenger_Label_MealPreferenceOption);				
			SwipeWhileNotFound(GetElementForDynamicText(OR.Passenger_Text_MealPreferenceOption,mealpreference),SwipeDown,5, true);			
			SwipeWhileNotFound(GetElementForDynamicText(OR.Passenger_Text_SeatPreferenceOption,seatpreference),SwipeDown,5, true);
			//oGbl.Client.click(Zone1, Element1, Index1, 1);
			//WaitForElement(OR.Passenger_Label_Passenger, 3000);
			SwipeWhileNotFound(OR.Passenger_BTN_Save,SwipeDown,1, false);

			if((frequentflyer.contains("None")) || frequentflyer.equalsIgnoreCase(""))
			{
			}else
			{	Click(OR.Passenger_Text_Frequent_Flyer_program);				
			SwipeWhileNotFound(GetElementForDynamicText(OR.Passenger_Text_FrequentFlyer,frequentflyer),SwipeDown,5,true);
			}
			if(!(oGbl.Chk_status.toLowerCase().contains("yes"))){
				if(IsElementFound(OR.Passenger_Chkbx_enable)){
					Click(OR.Passenger_Chkbx_Save);
					VerifyElementFound(OR.Passenger_Chkbx_disable);
				}
			}
			WaitForElement(OR.Passenger_Label_Passenger, 3000);
			SwipeWhileNotFound(OR.Passenger_BTN_Save,SwipeDown,4,false);
			Click(OR.Passenger_BTN_Save);
			Sleep(2000);
			if(IsElementFound(OR.SignIn_Textbox_Password)){
				Type(OR.SignIn_Textbox_Password,TD("Pwd"));	
				CloseKeyboard();
				Sleep(5000);
				Click(OR.SignIn_BTN_Signin);
			}
			if(WaitForElement(OR.BS_Title_Heading, 60000)){
				Report("PASS", "Booking summary", "Navigated to Booking Summary page",true);
			}
			else{
				oGbl.Failureflag = 1;
				Report("FAIL", "Booking Summary", "Not navigated to booking summary page",true);
			}
		}
		else{
			oGbl.Failureflag = 1;
			Report("FAIL", "Passenger Details", "Not Navigated to Passenger details page",true);
		}

	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: SelectPayment()
	#Author: Roshith India
	#Description: This functiona will search for the credit card payment option  
	#Date of creation: 1-August-2015
	#Input Parameters: 
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/

	public void SelectPayment(){
		try {			
			if(oGbl.Failureflag == 0){
				//SwipeWhileNotFound(OR.Result_BTN_FareSummary,SwipeDown,5, false);
				SwipeWhileNotFound(OR.Payment_Label_SelectPayment,SwipeDown,5, false);
				if(IsElementFound(OR.Payment_Label_SelectPayment))
				{
					Click(OR.Payment_Label_SelectPayment);
				}
				if(IsElementFound(OR.SignIn_BTN_Signin)){
					if(!IsElementFound(OR.CCP_Label_CCDetails))
					{
						SignIn("no",TD("Uname"),TD("Pwd"));
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: paymentmethod()
	#Author: vijayalakshmi India
	#Description: This function select the provided the payment method
	#Date of creation: 1-August-2015
	#Input Parameters: payment_option : Select credit card or gift card
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public void paymentmethod(String payment_option) throws Exception{
		oGbl.ScreenName = "Payment Method";
		if(oGbl.Failureflag == 0){
			if(WaitForElement(OR.Payment_Title_Heading, 60000)){
				if(((payment_option).toUpperCase()).contains("GIFT")){
					Click(OR.Payment_Link_GiftCard);
				}
				else if(((payment_option).toUpperCase()).contains("CREDIT")){
					if(oGbl.Device_Under_Test.startsWith("ios_app")){
						if(((WaitForElement(OR.Payment_Link_CreditCard, 6000)))){
							SwipeWhileNotFound(OR.Payment_Link_CreditCard,SwipeDown,5, true);}
					}else{
						if(!((WaitForElement(OR.CCP_Label_CCDetails, 6000)))){
							SwipeWhileNotFound(OR.Payment_Link_CreditCard,SwipeDown,5, true);}
					}
					
					if((WaitForElement(OR.CCP_Label_CCDetails, 60000)) ||(WaitForElement(OR.Payment_Title_Heading, 60000))){
						Report("PASS", "Credit card details Page", "Navigated to Credit card details page",true);
					}
					else{
						Report("FAIL", "Credit card details Page", "Not Navigated to Credit card details page",true);
					}

				}else
					Report("INFO", "Payment Option", "We dont have" +payment_option+"payment  option",true);
			}
			else{
				Report("FAIL", "Payment Page", "Not Navigated to payment page",true);
				oGbl.Failureflag = 1;
			}
		}
	}
	//==============================================================================================================================='
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: apppasscode()
	#Author: Vijayalakshmi India
	#Description: This function enable or disable passcode for app
	#Date of creation: 1-August-2015
	#Input Parameters: option: enable or disable, passcode: passcode to enter
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/
	public void apppasscode(String option,String passcode) throws Exception{
		oGbl.ScreenName = "Application Passcode";
		if(oGbl.Failureflag==0){			
			Click(OR.Preference_BTN_AppPasscode);
			if(WaitForElement(OR.PP_Title_Header, 60000)){
				Report("PASS", "Passcode Setting", "Navigated to Passcode Setting page",true);
				if((option.toUpperCase()).contains("ENABLE")){
					if(IsElementFound(OR.PP_BTN_Enabled)){
						Report("INFO", "Passcode Setting", "Passcode Setting is Already Enabled",true);
					}
					else{
						if(IsElementFound(OR.PP_BTN_Disabled)){
							Click(OR.PP_BTN_Disabled);
							Type(OR.PP_Textbox_Newpasscode,passcode);
							Type(OR.PP_Textbox_confirmpasscode,passcode);
							Click(OR.PP_BTN_SaveButton);
							if(IsElementFound(OR.PP_Title_AlertHeader)){
								Report("PASS", "Passcode Setting", "Passcode Setting is Enabled",true);
								Click(OR.PP_BTN_OkButton);
								if(IsElementFound(OR.Preference_Title_Header)){
									Report("PASS", "Preference Setting", "Navigated to Preference settings page",true);
								}
								else{
									Report("FAIL", "Preference Setting", "Not navigated to Preference settings page",true);
									oGbl.Failureflag=1;  
								}
							}
							else{
								Report("FAIL", "Passcode Setting", "Passcode Setting is not Enabled",true);
								oGbl.Failureflag=1;  
							}
						}
					}
				}
				else{
					if((option.toUpperCase()).contains("DISABLE")){
						if(IsElementFound(OR.PP_BTN_Disabled)){
							Report("INFO", "Passcode Setting", "Passcode Setting is Already Disabled",true);
						}
						else{
							if(IsElementFound(OR.PP_BTN_Enabled)){
							
								
							if(oGbl.Device_Under_Test.startsWith("ios_app"))
							{
								Click(OR.Preference_BTN_passcodesync);
							}else{
								Click(OR.PP_BTN_Enabled);
								Click(OR.PP_BTN_SaveButton);
						}
								if(IsElementFound(OR.PP_Text_passcode)){
									Report("PASS", "Enter Passcode", "Enter Passcode Textbox is displayed",true);
								if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
									for(int eachCharNo= 0; eachCharNo< passcode.length(); eachCharNo++)
									{
										String eachChar = "" + passcode.charAt(eachCharNo);
										SendText(eachChar);
									}
									Sleep(4000);
									}else{
										Type(OR.PP_Textbox_passcode,passcode);
										Click(OR.PP_BTN_Verify);
									}
									
									if(IsElementFound(OR.PP_BTN_Disabled)||IsElementFound(OR.PP_Title_AlertHeader)){
										Report("PASS", "Passcode Setting", "Passcode Setting is disabled",true);
										if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
										Click(OR.BackButton);
}else{
Click(OR.PP_BTN_OkButton);
}

										if(IsElementFound(OR.Preference_Title_Header)){
											Report("PASS", "Preference Setting", "Navigated to Preference settings page",true);
										}
										else{
											Report("PASS", "Passcode Setting", "Passcode Setting is Enabled",true);
										}
									}
									else{
										Report("FAIL", "Passcode Setting", "Passcode Setting is not Enabled",true);
									}
								}
								else{
									Report("FAIL", "Enter Passcode", "Enter Passcode Textbox is not displayed",true);
								}
								if(IsElementFound(OR.Preference_Title_Header)){
									Report("PASS", "Preference Setting", "Navigated to Preference settings page",true);
								}
								else{
									Report("PASS", "Passcode Setting", "Passcode Setting is Enabled",true);
								}
							}
						}
					}
				}
			}
			else{
				Report("FAIL", "Passcode Setting", "Not navigated to Passcode Setting page",true);
			}
		}
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: Link_name()
	#Author: Vijayalakshmi India
	#Description: This function will update the profile with either Aeroplan name or with the Mob+ profile name 
	#Date of creation: 16-Sept-2015
	#Input Parameters: name: to use Aeroplan or Mob+ name, update: Update Aeroplan or mob+ name
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/

	public void Link_name(String name, String update) throws Exception{
		oGbl.ScreenName = "Aeroplane Link";

		if(name.toLowerCase().contains("aeroplan")){
			Click(OR.LA_BTN_UseAreoplan);
			if(WaitForElement(OR.LA_Text_Success_message, 60000)){
				Report("PASS", "Aeroplan link", "Aeroplan name linked successfully",true);
			}else{
				Report("FAIL", "Aeroplan link", "Aeroplan name not linked successfully",true);
			}

		}
		else{
			Click(OR.LA_BTN_Mobile_name);
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
		
			if(WaitForElement(OR.LA_Text_AeroplanNo, 300000)){
					Report("PASS", "Aeroplan link", "Aeroplan name linked successfully",true);
				}else{
					Report("FAIL", "Aeroplan link", "Aeroplan name not linked successfully",true);
				}
}else{
				if(update.toLowerCase().contains("yes")){
					Click(OR.LA_BTN_UpdateName);
					if(WaitForElement(OR.LA_Text_AeroplanNo, 300000)){
						Report("PASS", "Aeroplan link", "Aeroplan name linked successfully",true);
					}else{
						Report("FAIL", "Aeroplan link", "Aeroplan name not linked successfully",true);
					}
				}else{
					Click(OR.LA_BTN_Continuename);
					if(WaitForElement(OR.LA_Text_AeroplanNo, 300000)){
						Report("PASS", "Aeroplan link", "Aeroplan name linked successfully",true);
					}else{
						Report("FAIL", "Aeroplan link", "Aeroplan name not linked successfully",true);
					}

				}
}			
		}

	}




	public boolean SignInScreenVerify() throws Exception
	{
		oGbl.ScreenName = "Sign In";
		boolean passFlag = false;
		if(oGbl.Failureflag == 0)
		{

			VerifyElementFound(OR.SignIn_Textbox_Password);
			VerifyElementFound(OR.SignIn_Link_ForgotPassword);
			VerifyElementFound(OR.SignIn_BTN_Signin);
			passFlag = true;
		}else
		{
			Report("FAIL", "Home Page", "Not navigated to Sign In Page",true);
			oGbl.Failureflag=1;
		}

		return passFlag;

	}


	public boolean NavigateToSignInScreen() throws Exception
	{
		oGbl.ScreenName = "Sign In";
		boolean passFlag = false;

		if(oGbl.Failureflag == 0)
		{
			Click(OR.LP_BTN_Menu);
			passFlag = WaitForElement(OR.LP_Text_signin,2000);
			if(passFlag == true)
			{
				Click(OR.LP_Text_signin);
				passFlag = WaitForElement(OR.SignIn_Textbox_Email,2000);
				if(passFlag == false)
				{
					Report("FAIL", "Home Page", "Not navigated to Sign In Page",true);
					oGbl.Failureflag=1;
				}
			}else
			{
				if(WaitForElement(OR.MO_BTN_Home,2000)){
					Signout();
					Sleep(7000);
					Click(OR.LP_Text_signin);
					passFlag = WaitForElement(OR.SignIn_Textbox_Email,2000);
					if(passFlag == false)
					{
						Report("FAIL", "Home Page", "Not navigated to Sign In Page",true);
						oGbl.Failureflag=1;
					}
				}else{
					Report("FAIL", "Home Page", "Not navigated to Menu options slide screen",true);
					oGbl.Failureflag=1;
				}
				
			}
			return passFlag;
		}else
		{
			return false;
		}


	}


	public int getScreenClick(String Device, String TravelOption,int Flag) throws Exception{
		if(Device.toLowerCase().contains("Nexus 4")){
			for(int i=0;i<5;i++){				
				try{
					Swipe(SwipeDown, 800, 500,"SwipeDown");
					Sleep(1000);
					Swipe("Up", 930, 500,"SwipeUp");
					if(Flag == 1){						
						ClickIn(GetElementForDynamicText(OR.TO_Text_OnwardOption,TravelOption),SwipeDown,OR.TO_BTN_DepartureFlight,0,0);
					}else{						
						ClickIn(GetElementForDynamicText(OR.TO_Text_ReturnOption,TravelOption),SwipeDown,OR.TO_BTN_MealAddtoReturnFlight,0,0);
					}
					break;
				}catch(Exception e){
					Swipe(SwipeDown, 800, 500,"SwipeDown");
					Sleep(1000);
					Swipe("Up", 930, 500,"SwipeUp");
				}			     
			}
			return 1;			
		}else{
			return 0;
		}
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: aeroplan_unlink()
	#Author: Vijayalakshmi India
	#Description: This function will unLink the aeroplan number with the user profile 
	#Date of creation: 16-Sept-2015
	#Input Parameters: Parameters
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/

	public void aeroplan_unlink() throws Exception{
		oGbl.ScreenName = "Aeroplane Unlink";
		Click(OR.LA_Text_AeroplanNo);
		if(WaitForElement(OR.Aeroplan_Link_UnlinkAeroplan, 10000))
		{
			Click(OR.Aeroplan_Link_UnlinkAeroplan);
			if(WaitForElement(OR.UA_BTN_Continue, 10000))
			{
				Click(OR.UA_BTN_Continue);
				if(WaitForElement(OR.PS_BTN_LinkWithAeroplane, 120000))
				{
					Report("PASS", "Un Link Aeroplan", "UNLink Aeroplan number is successfull",true);
				}else{
					Report("FAIL", "Un Link Aeroplan", "UNLink Aeroplan number is not successfull",true);
				}

			}
		}
		else{
			Report("FAIL", "Un Link Aeroplan", "UNLink Aeroplan option is not found",true);
		}
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: aeroplan_link()
	#Author: Vijayalakshmi India
	#Description: This function will Link the aeroplan number with the user profile 
	#Date of creation: 16-Sept-2015
	#Input Parameters: Parameters
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/
	public void aeroplan_link() throws Exception{
		oGbl.ScreenName = "Aeroplane Link";
		Click(OR.PS_BTN_LinkWithAeroplane);
		if(WaitForElement(OR.LA_Title_Header, 60000)){
			Report("PASS", "Link Aeroplan", "Navigated to Link Aeroplan page",true);
			Type(OR.LA_TextBox_Aeroplanno,TD("Aeroplanno"));
			Type(OR.LA_Textbox_Password,TD("AN_password"));
			Click(OR.LA_BTN_Continue);
		}else{
			Report("FAIL", "Link Aeroplan", "Link Aeroplan option is not found",true);
		}
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: CreateUser_Profile()
	#Author: Vijayalakshmi India
	#Description: This function will create the new user profile 
	#Date of creation: 16-Sept-2015
	#Input Parameters: Parameters
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/

	public void CreateUser_Profile() throws Exception{
		oGbl.ScreenName = "Create User Profile";
		if(WaitForElement(OR.PI_Title_header, 60000)){
			Report("PASS", "User Profile", "Navigated to User profile page",true);
			Click(OR.PI_Dropdown_Salutation);
			Sleep(1000);
			String salutation=TD("Salutation");	
			CloseKeyboard();
			String[]sal=GetElementForDynamicText(OR.PI_Text_Salutation,salutation);
			SwipeWhileNotFound(sal,SwipeDown,5, true);
			//oGbl.Client.click(Zone1, Element1, Index1, 1);
			Sleep(2000);
			SwipeWith2ElementRef(OR.PI_Label_Firstname, OR.PI_Label_MiddleName, "Down", 800, 5, false);
			/*Click(OR.PI_Textbox_Firstname);
			//ClickIn(OR.PI_Label_Firstname,"Down",OR.PI_Textbox_Firstname,0,0);
			Sleep(2000);
			if(IsElementFound(OR.PI_BTN_CloseButton)){
				Click(OR.PI_BTN_CloseButton);
			}
			SendText(TD("Fname"));
			CloseKeyboard();*/
			Type(OR.PI_Textbox_Firstname,TD("Fname"));
			SwipeWith2ElementRef(OR.PI_Label_MiddleName, OR.PI_Label_LastName, "Down", 800, 5, false);
			Type(OR.PI_Textbox_MiddleName,TD("Mname"));
			SwipeWith2ElementRef(OR.PI_Label_LastName, OR.PI_Label_Email, "Down", 800, 5, false);
			Type(OR.PI_Textbox_LastName,TD("Lname"));
			SwipeWith2ElementRef(OR.PI_Label_Email, OR.PI_Label_Mobile, "Down", 800, 5, false);
			Type(OR.PI_Textbox_Email,TD("Email"));
			SwipeWith2ElementRef(OR.PI_TextBox_countryAreaCode, OR.PI_TextBox_AeroplanNumber, "Down", 800, 5, true);
			Sleep(1000);
			CloseKeyboard();
			
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
			Click(OR.PI_TextBox_countryAreaCodesearch);
			String country="+"+TD("AreaCode");
			String scountry[]=GetElementForDynamicText(OR.AC_Text_Dynamic, country);
			SendText(TD("AreaCode"));
			Sleep(200);
			Click(scountry);
		}
			else{Click_Type(OR.PI_TextBox_countryAreaCodesearch,TD("AreaCode"));
			Sleep(1000);}

			Sleep(1000);
			Type(OR.PI_TextBox_Phonenumber,TD("Mnumber"));
			SwipeWith2ElementRef(OR.PI_TextBox_AeroplanNumber, OR.PI_Label_PreferrredAirport, "Down", 800, 5, false);
			Type(OR.PI_TextBox_AeroplanNumber,TD("Aeroplaneno"));
			SwipeWith2ElementRef(OR.PI_TextBox_PreferrredAirport, OR.PI_BTN_Update, "Down", 800, 5, true);
			//			Click_Type(OR.PI_TextBox_PreferrredAirport1,TD("Preferred_Airport"));
	
	
			Click(OR.PI_TextBox_PreferrredAirport1);
			oGbl.Client.sleep(1000);
			oGbl.Client.sendText(TD("Preferred_Airport"));
			oGbl.Client.sleep(1000);
			oGbl.Client.closeKeyboard();
			oGbl.Client.sleep(1000);


			String preferred_airport=TD("Preferred_Airport");			
			Click(GetElementForDynamicText(OR.PI_Text_PreferredAirport,"airport_code_"+preferred_airport.toUpperCase()));

			SwipeWith2ElementRef(OR.PI_BTN_Update, OR.Blank_Object, "Down", 800, 5, true);
			if(WaitForElement(OR.PS_Title_Header, 60000)){
				Report("PASS", "User profile", "User profile has been updated",true);

			}else{
				Report("FAIL", "User profile", "User profile has not been updated",true);
				oGbl.Failureflag=1;
			}

		}
	}


	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: profile_validation()
	#Author: Vijayalakshmi India
	#Description: This function will Validate the profile updation 
	#Date of creation: 16-Sept-2015
	#Input Parameters: Parameters
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/

	public void profile_validation() throws Exception{
		oGbl.ScreenName = "Personal Info";
		Click(OR.PI_Text_UserName);
		CloseKeyboard();
		if(WaitForElement(OR.PI_Title_header, 60000))
		{
			CloseKeyboard();
			String title=ElementGetText(OR.PI_Dropdown_Salutation);
			if(title.contains(TD("Salutation"))){
				Report("PASS", "Salutation", "Salutation " +title+ " is updated",true);
			}else{
				Report("FAIL", "Salutation", "Salutation " +title+ " is not updated",true);
			}
			SwipeWith2ElementRef(OR.PI_Textbox_Firstname, OR.PI_Textbox_MiddleName, "Down", 800, 5, false);
			String fname=ElementGetText(OR.PI_Textbox_Firstname);
			if(fname.contains(TD("Fname"))){
				Report("PASS", "First name", "First name " +fname+ " is updated",true);
			}else{
				Report("FAIL", "First name", "First name " +fname+ " is not updated",true);
			}
			SwipeWith2ElementRef(OR.PI_Textbox_LastName, OR.PI_Label_Contact, "Down", 800, 5, false);
			String lname=ElementGetText(OR.PI_Textbox_LastName);
			if(lname.contains(TD("Lname"))){
				Report("PASS", "Last name", "Last name " +lname+ " is updated",true);
			}else{
				Report("FAIL", "Last name", "Last name " +lname+ " is not updated",true);
			}
			SwipeWith2ElementRef(OR.PI_Textbox_Email, OR.PI_TextBox_countryAreaCode, "Down", 800, 5, false);
			String email=ElementGetText(OR.PI_Textbox_Email);
			if(email.contains(TD("Email"))){
				Report("PASS", "Email", "Email " +email+ " is updated",true);
			}else{
				Report("FAIL", "Email", "Email " +email+ " is not updated",true);
			}
			SwipeWith2ElementRef(OR.PI_TextBox_countryAreaCode, OR.PI_TextBox_AeroplanNumber, "Down", 800, 5, false);
			/*String code=ElementGetText(OR.PI_TextBox_countryAreaCode);
			if(code.contains(TD("AreaCode"))){
				Report("PASS", "Aera code", "Aera code " +code+ " is updated",true);
			}else{
				Report("FAIL", "Aera code", "Aera code " +code+ " is not updated",true);
			}*/
			String mnumber=ElementGetText(OR.PI_TextBox_Phonenumber);
			if(mnumber.contains(TD("Mnumber"))){
				Report("PASS", "Mobile number", "mobile number " +mnumber+ " is updated",true);
			}else{
				Report("FAIL", "mobile number", "mobile number " +mnumber+ " is not updated",true);
			}

			SwipeWith2ElementRef(OR.PI_TextBox_AeroplanNumber, OR.PI_TextBox_PreferrredAirport, "Down", 800, 5, false);
			String Aeroplane_no=ElementGetText(OR.PI_TextBox_AeroplanNumber);
			Aeroplane_no=Aeroplane_no.replaceAll("\\s+","");
			if(Aeroplane_no.contains(TD("Aeroplaneno"))){
				Report("PASS", "Aeroplane number", "Aeroplane number " +Aeroplane_no+ " is updated",true);
			}else{
				Report("FAIL", "Aeroplane number", "Aeroplane number " +Aeroplane_no+ " is not updated",true);
			}
			/*SwipeWith2ElementRef(OR.PI_TextBox_PreferrredAirport, OR.PI_BTN_Update, "Down", 800, 5, false);
			String PreferredAirport=ElementGetText(OR.PI_TextBox_PreferrredAirport);
			if(PreferredAirport.contains(TD("Preferred_Airport"))){
				Report("PASS", "Preferred Airport", "Preferred Airport " +PreferredAirport+ " is updated",true);
			}else{
				Report("FAIL", "Preferred Airport", "Preferred Airport " +PreferredAirport+ "  is not updated",true);
			}*/
		}else{
			Report("FAIL", "User profile", "Not navigated to User profile",true);
		}
	}


	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: SavepassengerDetails()
	#Author: Vijayalakshmi India
	#Description: This function Add new passenger to the Mob+ profile
	#Date of creation: 1-August-2015
	#Input Parameters:
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/

	public void SavepassengerDetails(String title,String Fname,String Mname, String Lname,String AreaCode,String Mnumber,String Email,String Gender,String Date,String Month,String Year, String Redress,String Travelno,String Meal,String SeatPref,String Freqfly,String Residence,String Nationality,String Passportno,String Passportno_Date,String Passportno_Month,String Passportno_Year,String Passportno_country,String Nexusnumber,String Nexusnumber_Date,String Nexusnumber_Month,String Nexusnumber_Year,String Checkin) throws Exception{
		oGbl.ScreenName = "New Passenger";
		Click(OR.NP_TextBox_Title);	
		CloseKeyboard();
		if(oGbl.Device_Under_Test.startsWith("ios_app")){
			String[]sal=GetElementForDynamicText(OR.NP_Text_Salutation,title);
			SwipeWhileNotFound(sal,SwipeDown,3, true);
		}
		else{
			Click(GetElementForDynamicText(OR.NP_Text_Salutation,title));
		}
		Sleep(1000);
		Type(OR.NP_TextBox_Firstname,Fname);
		Sleep(1000);
		Click_Type(OR.NP_TextBox_Middlename,Mname);
		Sleep(1000);
		Type(OR.NP_TextBox_Lastname,Lname);
		Sleep(1000);
		SwipeWhileNotFound(OR.NP_Text_Email,SwipeDown,1, false); 
		if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
			Click(OR.NP_Textbox_Aeracode);
			Click(OR.SA_Textbox_Searchbox);
			//Click_Type(OR.Passenger_Textbox_AreaCode,AreaCode);
			String country="+"+AreaCode;
			SendText(AreaCode);
			Sleep(5000);
			String scountry[]=GetElementForDynamicText(OR.AC_Text_Dynamic, country);
			Click(scountry);
		}
		else{
			if(!AreaCode.equalsIgnoreCase(""))
				{
					Click(OR.NP_Textbox_Aeracode);
					Click_Type(OR.Passenger_Textbox_AreaCode,AreaCode);
					if(WaitForElement(OR.NP_Text_Listedareacode, 60000)){
						if(ElementGetText(OR.NP_Text_Listedareacode).contains(AreaCode)){
							Click(OR.NP_Text_Listedareacode);
						}
						else
							Report("FAIL", "Country", "Country is not Listed",true);
					}
					else
					{
						Report("FAIL", "Country", "Country is not selected please provide the correct name",true);
					}
				}
	  }
		WaitForElement(OR.NP_Text_Mobile, 5000);
		Type(OR.NP_Text_Mobile,Mnumber);		
		//Type(OR.NP_Text_Email,Email); - Commented as Email should not be editable as per requirement
		SwipeWhileNotFound(OR.NP_Label_GenderDetails,SwipeDown,5, false);
		VerifyElementFound(OR.NP_Label_GenderDetails);
		Sleep(5000);
		if(((Gender).toUpperCase()).contains("FEMALE")){
			Click(OR.NP_BTN_Female);
		}
		else{
			Click(OR.NP_BTN_Male);
		}
		Type(OR.NP_Textbox_DayofDOB,Date);
		Type(OR.NP_TextBox_MonthofDOB,Month);
		Type(OR.NP_Textbox_YearofDOB,Year);
		if(!Redress.equalsIgnoreCase(""))
		{
			Type(OR.NP_Textbox_RedressName,Redress);
		}
		if(!Travelno.equalsIgnoreCase(""))
		{
			Type(OR.NP_Textbox_KnownTravelnumber,Travelno);
		}
		SwipeWhileNotFound(OR.NP_Label_MealPreferenceOption,SwipeDown,5, false);
		if(!Meal.equalsIgnoreCase(""))
		{
			Click(OR.NP_Label_MealPreferenceOption);

			Sleep(1000);
			SwipeWhileNotFound(GetElementForDynamicText(OR.NP_Text_MealPreferenceOption,Meal),SwipeDown,5, true);
			Sleep(1000);
		}

		if(!SeatPref.equalsIgnoreCase(""))
		{
			SwipeWith2ElementRef(GetElementForDynamicText(OR.NP_Text_SeatPreferenceOption,SeatPref), OR.Passenger_Text_Frequent_Flyer_program, "Down", 800, 10, true);
		}
		//Click(GetElementForDynamicText(OR.NP_Text_SeatPreferenceOption,SeatPref));
		//oGbl.Client.click(Zone1, Element1, Index1, 1);
		SwipeWhileNotFound(OR.Passenger_BTN_Save,SwipeDown,1, false);
		if(!Freqfly.equalsIgnoreCase(""))
		{
			if(!(Freqfly).contains("None")){
				Click(OR.Passenger_Text_Frequent_Flyer_program);
				Sleep(1000);
				SwipeWhileNotFound(GetElementForDynamicText(OR.Passenger_Text_FrequentFlyer,Freqfly),SwipeDown,5, true);
			}
			Sleep(3000);
		}

		if(!Residence.equalsIgnoreCase(""))
		{
			SwipeWith2ElementRef(OR.NP_Text_Nationality_Residence, OR.NP_Text_Passport, "Down", 800, 10, true);
			Sleep(1000);
			//Click(OR.NP_Text_Nationality_Residence);
			enterNationality_details(Residence,Nationality);
			Sleep(3000);
		}
		if(!Passportno.equalsIgnoreCase(""))
		{
			SwipeWith2ElementRef(OR.NP_Text_Passport, OR.Passenger_BTN_Save, "Down", 800, 10, true);
			Sleep(1000);
			//Click(OR.NP_Text_Passport);
			enterPassport_details(Passportno,Passportno_Date,Passportno_Month,Passportno_Year,Passportno_country);
			Sleep(3000);
		}
		if(!Nexusnumber.equalsIgnoreCase(""))
		{
			SwipeWith2ElementRef(OR.NP_Text_Nexus, OR.Passenger_BTN_Save, "Down", 800, 10, true);
			Sleep(1000);
			//Click(OR.NP_Text_Nexus);
			enterNexus_details(Nexusnumber,Nexusnumber_Date,Nexusnumber_Month,Nexusnumber_Year,Checkin);
			Sleep(3000);
		}
		String device=oGbl.Device_Under_Test;

		if(device.toLowerCase().contains("adb:motorola xt1068")){
			Swipe(SwipeDown,500,500,"Search add option");
		}
		SwipeWith2ElementRef(OR.Passenger_BTN_Save, OR.Blank_Object, "Down", 800, 10, false);
		//SwipeElement_ToTopOfThePage(OR.NP_BTN_save,SwipeDown,5);
		Sleep(2000);
		Click(OR.Passenger_BTN_Save);

		Sleep(5000);
		if(WaitForElement(OR.SignIn_Textbox_Password, 5000))
		{
			Type(OR.SignIn_Textbox_Password,TD("Pwd"));	
			CloseKeyboard();
			Sleep(5000);
			SwipeWhileNotFound(OR.SignIn_BTN_Signin,SwipeDown,5,false);
			Click(OR.SignIn_BTN_Signin);				
		}


		//SwipeWhileNotFound(OR.NP_BTN_save, SwipeDown, GetOffset(oGbl.Device_Under_Test), 1000, 1000, 1, true); 
		if(WaitForElement(OR.SP_Title_Header, 20000)||WaitForElement(OR.PS_Title_Header, 20000)){
			Report("PASS", "Passenger Info", "Passenger info is addded",true);
			if(IsElementFound(OR.BackButton)){
				Click(OR.BackButton);
			}
			if(oGbl.thisscenario.contains("yes")){
				if(oGbl.Device_Under_Test.startsWith("ios_app"))
				{
					Sleep(2000);
					if(IsElementFound(OR.BackButton)){
						Click(OR.BackButton);
					}
					
					Click(OR.PS_BTN_SavePassenger);
					if(WaitForElement(OR.SignIn_Textbox_Password, 10000)){
						Type(OR.SignIn_Textbox_Password,TD("Pwd"));	
						CloseKeyboard();
						Sleep(5000);
						SwipeWhileNotFound(OR.SignIn_BTN_Signin,SwipeDown,5,false);
						Click(OR.SignIn_BTN_Signin);
						Sleep(2000);
					}
				}
			}
			
		}
		else{
			oGbl.Failureflag = 1;
			Report("FAIL", "Passenger Info", "Passenger info is not addded",true);
		}

	}




	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: Validatepersonalinfo()
	#Author: Vijayalakshmi India
	#Description: This function will valdiate the personal information that has been saved after login
	#Date of creation: 1-August-2015
	#Input Parameters:
	#Name of person modifying: Tester
	#Date of modification: 1-Sep-2015
	‘***************************************************/

	public void Validatepersonalinfo() throws Exception{
		oGbl.ScreenName = "Profile Settings";
		Click(OR.PS_Text_Username);
		if(WaitForElement(OR.PI_Title_header, 60000)){
			String title=ElementGetText(OR.PI_TextBox_Salutation);
			if(title.contains(TD("Salutation"))){
				Report("PASS", "Salutation", "Salutation " +title+ " is updated",true);
			}else{
				Report("FAIL", "Salutation", "Salutation " +title+ " is not updated",true);
			}
			String fname=ElementGetText(OR.NP_TextBox_Firstname);
			if(fname.contains(TD("Fname"))){
				Report("PASS", "First name", "First name " +fname+ " is updated",true);
			}else{
				Report("FAIL", "First name", "First name " +fname+ " is not updated",true);
			}
			SwipeWhileNotFound(OR.NP_TextBox_Lastname,SwipeDown,5,false);
			String lname=ElementGetText(OR.NP_TextBox_Lastname);
			if(lname.contains(TD("Lname"))){
				Report("PASS", "Last name", "Last name " +lname+ " is updated",true);
			}else{
				Report("FAIL", "Last name", "Last name " +lname+ " is not updated",true);
			}
			SwipeWhileNotFound(OR.PI_Label_Mobile,SwipeDown,5,false);
			/*	String email=ElementGetText(OR.PI_Textbox_Email);
			if(email.contains(TD("Email"))){
				Report("PASS", "Email", "Email " +email+ " is updated",true);
			}else{
				Report("FAIL", "Email", "Email " +email+ " is not updated",true);
			}*/
			SwipeWhileNotFound(OR.NP_Textbox_Aeracode,SwipeDown,5,false);
			String code=ElementGetText(OR.NP_Textbox_Aeracode);
			if(code.contains(TD("AreaCode"))){
				Report("PASS", "Aera code", "Aera code " +code+ " is updated",true);
			}else{
				Report("FAIL", "Aera code", "Aera code " +code+ " is not updated",true);
			}
			SwipeWhileNotFound(OR.PI_TextBox_Phonenumber,SwipeDown,5,false);
			String mnumber=ElementGetText(OR.PI_TextBox_Phonenumber);
			if(mnumber.contains(TD("Mnumber"))){
				Report("PASS", "Mobile number", "mobile number " +mnumber+ " is updated",true);
			}else{
				Report("FAIL", "mobile number", "mobile number " +mnumber+ " is not updated",true);
			}
			if(TD("Aeroplaneno")!=null){
				SwipeWhileNotFound(OR.PI_TextBox_AeroplanNumber,SwipeDown,5,false);
				String Aeroplane_no=ElementGetText(OR.PI_TextBox_AeroplanNumber);
				Aeroplane_no=Aeroplane_no.replaceAll("\\s+","");
				if(Aeroplane_no.contains(TD("Aeroplaneno"))){
					Report("PASS", "Aeroplane number", "Aeroplane number " +Aeroplane_no+ " is updated",true);
				}else{
					Report("FAIL", "Aeroplane number", "Aeroplane number " +Aeroplane_no+ " is not updated",true);
				}
			}
			//SwipeWhileNotFound(OR.PI_BTN_Update,SwipeDown,5,false);


			if(TD("Preferred_Airport")!=null){
				String PreferredAirport=ElementGetText(OR.PI_TextBox_PreferrredAirport);

				if(PreferredAirport.contains(TD("Preferred_Airport"))){
					Report("PASS", "Preferred Airport", "Preferred Airport " +PreferredAirport+ " is updated",true);
				}else{
					Report("FAIL", "Preferred Airport", "Preferred Airport " +PreferredAirport+ "  is not updated",true);
				}
			}
			if(((TD("Gender")).toUpperCase()).contains("FEMALE")){
				VerifyElementFound(OR.NP_BTN_Femaleselected);
			}else{
				VerifyElementFound(OR.NP_BTN_Maleselected);
			}
			SwipeWhileNotFound(OR.NP_Textbox_DayofDOB,SwipeDown,5,false);
			String day=ElementGetText(OR.NP_Textbox_DayofDOB);
			if(day.contains(TD("Date"))){
				Report("PASS", "Day in DOB", "Day " +day+ " is updated",true);
			}else{
				Report("FAIL", "Day in DOB", "Day " +day+ " is not updated",true);
			}
			String month=ElementGetText(OR.NP_TextBox_MonthofDOB);
			if(month.contains(TD("Month"))){
				Report("PASS", "Month in DOB", "Month " +month+ " is updated",true);
			}else{
				Report("FAIL", "Month in DOB", "Month " +month+ " is not updated",true);
			}
			String Year=ElementGetText(OR.NP_Textbox_YearofDOB);
			if(Year.contains(TD("Year"))){
				Report("PASS", "Year in DOB", "Year " +Year+ " is updated",true);
			}else{
				Report("FAIL", "Year in DOB", "Year " +Year+ " is not updated",true);
			}
			SwipeWhileNotFound(OR.NP_Textbox_RedressName,SwipeDown,5,false);
			String Redress=ElementGetText(OR.NP_Textbox_RedressName);
			if(Redress.contains(TD("Redress"))){
				Report("PASS", "Redress Number", "Redress Number " +Redress+ " is updated",true);
			}else{
				Report("FAIL", "Redress Number", "Redress Number " +Redress+ " is not updated",true);
			}
			String Travelno=ElementGetText(OR.NP_Textbox_KnownTravelnumber);
			if(Travelno.contains(TD("Travelno"))){
				Report("PASS", "Travelno", "Travelno " +Travelno+ " is updated",true);
			}else{
				Report("FAIL", "Travelno", "Travelno " +Travelno+ " is not updated",true);
			}
			SwipeWhileNotFound(OR.NP_Label_MealPreferenceOption,SwipeDown,5,false);
			String mealpreference=ElementGetText(OR.NP_Label_MealPreferenceOption);
			if(mealpreference.contains(TD("Meal"))){
				Report("PASS", "Meal Prefernce", "Meal Prefernce " +mealpreference+ " is updated",true);
			}else{
				Report("FAIL", "Meal Prefernce", "Meal Prefernce " +mealpreference+ " is not updated",true);
			}
			if(TD("SeatPref").contains("Window")){
				VerifyElementFound(OR.NP_Text_windowselected);
			}else if(TD("SeatPref").contains("Asile")){
				VerifyElementFound(OR.NP_Text_aisleselected);
			}else if(TD("SeatPref").contains("No")){
				VerifyElementFound(OR.NP_Text_NoPreferenceselected);
			}
			SwipeWhileNotFound(OR.NP_Label_Frequent_Flyer_program,SwipeDown,5,false);
			String frequentflyer=ElementGetText(OR.NP_Text_Frequent_Flyer_program);
			if(frequentflyer.contains(TD("Freqfly"))){
				Report("PASS", "Frequent flyer", "Frequent flyer " +frequentflyer+ " is updated",true);
			}else{
				Report("FAIL", "Frequent flyer", "Frequent flyer " +frequentflyer+ " is not updated",true);
			}		
			Click(OR.BackButton);      
			if(WaitForElement(OR.SP_Title_Header, 60000)||WaitForElement(OR.PS_Title_Header, 60000)){
				Report("PASS", "Passenger Info", "Passenger info is addded",true);
			}
			else{
				oGbl.Failureflag = 1;
				Report("FAIL", "Passenger Info", "Passenger info is not addded",true);
			}



		}else{
			Report("FAIL", "Passenger Info", "Not navigated to Passenger info page",true);
		}

	}



	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: enterNationality_details()
	#Author: Shuvra India
	#Description: This function will enter the Nationality details 
	#Date of creation: 10-Sept-2015
	#Input Parameters: Parameters
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/
	public void enterNationality_details(String Residence,String Nationality) throws Exception{
		oGbl.ScreenName = "Nationality_Residence";

		if(WaitForElement(OR.NR_Title_Header, 60000)){
			Click(OR.NR_Link_Residentcountry);
			Sleep(3000);
			if(oGbl.Device_Under_Test.startsWith("ios_app")){
				Click(OR.NR_Textbox_Residentcountry);
			}
			SendText(Residence);
			if(WaitForElement(OR.NP_Text_ListedCountry, 60000)){
				
				if(ElementGetText(OR.NP_Text_ListedCountry).contains(Residence)){
					Click(OR.NP_Text_ListedCountry);
					Click(OR.NR_Link_Nationality);
					Sleep(3000);
					if(oGbl.Device_Under_Test.startsWith("ios_app")){
						Click(OR.NR_Textbox_Residentcountry);
					}
					SendText(Nationality);
					if(WaitForElement(OR.NP_Text_ListedCountry, 60000)){
						if(ElementGetText(OR.NP_Text_ListedCountry).contains(Nationality)){
							Click(OR.NP_Text_ListedCountry);
							Sleep(2000);
							Click(OR.NR_BTN_Save);
							Sleep(2000);
							if(!((IsElementFound(OR.NP_Title_Header)) || (IsElementFound(OR.EP_Title_Header))||(IsElementFound(OR.PI_Title_header)))){
								Report("FAIL", "Information page", "Not Navigated to Information page",true);
							}
						}
						else
							Report("FAIL", "Country", "Country is not Listed",true);
					}
					else
						Report("FAIL", "Country", "Country is not selected please provide the correct name",true);
				}
				else
					Report("FAIL", "Country", "Country is not Listed",true);
			}
			else
				Report("FAIL", "Country", "Country is not selected please provide the correct name",true);
		}else{
			Report("FAIL", "Nationality and Residence", "Not navigated to Nationality and Residence page",true);
		}
	}


	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: enterPassport_details()
	#Author: Shuvra India
	#Description: This function will enter the Passport details 
	#Date of creation: 10-Sept-2015
	#Input Parameters: Parameters
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/
	public void enterPassport_details(String Passportno,String Passportno_Date,String Passportno_Month,String Passportno_Year,String Passportno_country) throws Exception{
		oGbl.ScreenName = "Passport";
		if(WaitForElement(OR.Passport_Title_Header, 60000)){
			Type(OR.Passport_Textbox_Passport,Passportno);
			Type(OR.Passport_Textbox_Day,Passportno_Date);
			Type(OR.Passport_Textbox_Month,Passportno_Month);
			Type(OR.Passport_Textbox_Year,Passportno_Year);
			Click(OR.Passport_Select_Country);
			Sleep(3000);
			if(oGbl.Device_Under_Test.startsWith("ios_app")){
				Click(OR.NR_Textbox_Residentcountry);
			}
			
			SendText(Passportno_country);
			if(WaitForElement(OR.NP_Text_ListedCountry, 60000)){
				if(ElementGetText(OR.NP_Text_ListedCountry).contains(Passportno_country)){
					Click(OR.NP_Text_ListedCountry);
					Sleep(2000);
					Click(OR.Passport_BTN_Save);
					Sleep(2000);
					if(!((IsElementFound(OR.NP_Title_Header)) || (IsElementFound(OR.EP_Title_Header))||(IsElementFound(OR.PI_Title_header)))){
						Report("FAIL", "Information page", "Not Navigated to Information page",true);
					}
					Sleep(1000);
				}
				else
					Report("FAIL", "Country", "Country is not Listed",true);
			}
			else
				Report("FAIL", "Country", "Country is not selected please provide the correct name",true);
		}else{
			Report("FAIL", "Passport", "Not navigated to Passport details page",true);
		}		
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: enterNexus_details()
	#Author: Shuvra India
	#Description: This function will enter the Nexus details 
	#Date of creation: 10-Sept-2015
	#Input Parameters: Parameters
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/
	public void enterNexus_details(String Nexusnumber,String Nexusnumber_Date,String Nexusnumber_Month,String Nexusnumber_Year,String Checkin) throws Exception{

		oGbl.ScreenName = "Nexus";
		if(WaitForElement(OR.Nexus_Title_Header, 60000)){
			Type(OR.Nexus_Textbox_Nexusnumber,Nexusnumber);
			Type(OR.Passport_Textbox_Day,Nexusnumber_Date);
			Type(OR.Passport_Textbox_Month,Nexusnumber_Month);
			Type(OR.Passport_Textbox_Year,Nexusnumber_Year);
			if(!(oGbl.Device_Under_Test.startsWith("ios_app")))
			{	
				if(Checkin.toLowerCase().contains("yes")){
					if(IsElementFound(OR.Nexus_Toggle_TrueSatus)){
						Report("PASS", "Check in Status", "Checkin Status is ON",true);
					}else{
						Click(OR.Nexus_Toggle_UScheckin);
						Sleep(2000);
						if(IsElementFound(OR.Nexus_Toggle_TrueSatus))
						{
							Report("PASS", "Check in Status", "Checkin Status is ON",true);
						}else
						{
							Report("FAIL", "Check in Status", "Checkin Status is OFF",true);
						}
					}
				}else{
					if(IsElementFound(OR.Nexus_Toggle_FalseSatus)){
						Report("PASS", "Check in Status", "Checkin Status is OFF",true);
					}else{
						Click(OR.Nexus_Toggle_UScheckin);
						Sleep(2000);
						if(IsElementFound(OR.Nexus_Toggle_FalseSatus))
						{
							Report("PASS", "Check in Status", "Checkin Status is OFF",true);
						}else
						{
							Report("FAIL", "Check in Status", "Checkin Status is ON",true);
						}
					}
				}
			}
			Sleep(1000);
			Click(OR.Nexus_BTN_Save);
			Sleep(2000);
			if(!((IsElementFound(OR.NP_Title_Header)) || (IsElementFound(OR.EP_Title_Header)) || (IsElementFound(OR.PI_Title_header)))){
				Report("FAIL", "Information page", "Not Navigated to Information page",true);
			}
			Sleep(1000);

		}else{
			Report("FAIL", "Nexus", "Not navigated to Nexus details page",true);
		}
	}


	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: triggerMainFrameValidation()
	#Author: Shuvra India
	#Description: This function will trigger the mainframe system for validation 
	#Date of creation: 10-Sept-2015
	#Input Parameters: Parameters
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/

	public void triggerMainFrameValidation(String[] Parameters) throws Exception
	{
		/*String ACMainframeScriptPath = oGbl.gUtilitiesPath+"\\ACMainframeScript.vbs";
		String[] comand1 = {"cmd","/c",ACMainframeScriptPath};
		//String[] command = (String[]) ArrayUtils.addAll(comand1, Parameters);

		String[] command = oGbl.oCmn.Array_Merge(comand1,Parameters);
		Process p = Runtime.getRuntime().exec(command);
		p.waitFor();*/

	}




	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: reportMainFrameValidationResults()
	#Author: Shuvra India
	#Description: This function will report the mainframe system validations in html 
	#Date of creation: 10-Sept-2015
	#Input Parameters: 
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/
	public void reportMainFrameValidationResults() throws Exception
	{
		/*String ACMainframeResultPath = oGbl.gUtilitiesPath+"\\MainframeResults.txt";
		String ACMainframeScriptPath = oGbl.gUtilitiesPath+"\\ACMainframeScript.vbs";

		String Temp_DeviceName=oGbl.Device_Under_Test;

		oGbl.Device_Under_Test="Session A - [24 x 80]";

		BufferedReader br = new BufferedReader(new FileReader(ACMainframeResultPath));			
		String sCurrentLine;

		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
			String Values[]=sCurrentLine.split(",");

			if(Values[0].toUpperCase().equals("FAIL")){
				//Report("FAIL", "Capture PCOMM Screen Shot", "Captured PCOMM Screen Shot",false);
				Report("FAIL", Values[1], Values[2],false);
				String[] command2 = {"cmd","/c",ACMainframeScriptPath,"CLOSE_PCOMM"};
				Process p2 = Runtime.getRuntime().exec(command2);
				p2.waitFor();				
				oGbl.Device_Under_Test=Temp_DeviceName;							
			}	

			Report(Values[0],Values[1],Values[2],Boolean.valueOf(Values[3]));
		}

		br.close();



		Report("PASS", "Capture PCOMM Screen Shot", "Captured PCOMM Screen Shot",false);

		String[] command2 = {"cmd","/c",ACMainframeScriptPath,"CLOSE_PCOMM"};
		Process p2 = Runtime.getRuntime().exec(command2);
		p2.waitFor();

		oGbl.Device_Under_Test=Temp_DeviceName;*/
	}







	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: createNewAccount()
	#Author: Shuvra India
	#Description: This function will fill the details to create a new account 
	#Date of creation: 10-Sept-2015
	#Input Parameters: 
	#Name of person modifying: Tester
	#Date of modification: 
	‘***************************************************/
	public void createNewAccount(String email, String password, String title, String Fname, String Mname, String Lname, String state, String Mno, String aeroplanNo) throws Exception
	{


		oGbl.ScreenName = "Create Account";
		ElementSendText(OR.CA_Textbox_Email,email);
		CloseKeyboard();
		//Type(OR.CA_Textbox_Email, email);
		Type(OR.CA_Textbox_Password, password);
		//SwipeWhileNotFound(OR.CA_Dropdown_Salutation, SwipeDown, GetOffset("createaccountdown"), 2000, 1000, 5, true);
		SwipeWith2ElementRef(OR.CA_Dropdown_Salutation, OR.CA_Textbox_Fname, SwipeDown, 800, 8, true);
		//WaitForElement(OR.CCP_Dropdown_Country,20000);
		if(oGbl.Failureflag == 0)
		{
			String[]sal=GetElementForDynamicText(OR.AC_Text_Dynamic, title);
			SwipeWhileNotFound(sal, SwipeDown, 5, true);
			//Type(OR.CCP_Dropdown_Country, title);
			//Click(OR.CCP_Country_Suggestion_item);
			WaitForElement(OR.CA_Dropdown_Salutation,20000);
			if(oGbl.Failureflag == 0)
			{
				SwipeWith2ElementRef(OR.CA_Textbox_Fname, OR.CA_Textbox_Mname, SwipeDown, 800, 8, false);
				Type(OR.CA_Textbox_Fname, Fname);
				SwipeWith2ElementRef(OR.CA_Textbox_Mname, OR.CA_Textbox_Lname, SwipeDown, 800, 8, false);
				Type(OR.CA_Textbox_Mname, Mname);
				SwipeWith2ElementRef(OR.CA_Textbox_Lname, OR.CA_Text_Contact, SwipeDown, 800, 8, false);
				Type(OR.CA_Textbox_Lname, Lname);
				//SwipeWhileNotFound(OR.CA_Textbox_Areacode, SwipeDown, GetOffset("createaccountdown"), 2000, 1000, 5, true);
			if(!(oGbl.Device_Under_Test.startsWith("ios_app"))){
				SwipeWhileNotFound(OR.CA_Textbox_Areacode,SwipeDown,800,false);
				Click(OR.CA_Textbox_Areacode);
}else{
	SwipeWith2ElementRef(OR.CA_Textbox_Areacode, OR.CA_Label_Aeroplane, SwipeDown, 800, 8, true);
}
				WaitForElement(OR.SA_Textbox_Searchbox,20000);
				if(oGbl.Failureflag == 0)
				{
					Click(OR.SA_Textbox_Searchbox);
					String country="+"+state;
					SendText(state);
					String scountry[]=GetElementForDynamicText(OR.AC_Text_Dynamic, country);
					Click(scountry);
					WaitForElement(OR.SignIn_Textbox_Email,20000);
					if(oGbl.Failureflag == 0)
					{
						//SwipeWhileNotFound(OR.CA_Textbox_Areacode, SwipeDown, GetOffset("createaccountdown"), 2000, 1000, 5, false);
						SwipeWith2ElementRef(OR.CA_Textbox_Phonenumber, OR.CA_Textbox_AeroplaneNumber, SwipeDown, 800, 8, false);
						Type(OR.CA_Textbox_Phonenumber, Mno);
						SwipeWith2ElementRef(OR.CA_Textbox_AeroplaneNumber, OR.CA_BTN_CreateAccount, SwipeDown, 800, 8, false);
						Type(OR.CA_Textbox_AeroplaneNumber, aeroplanNo);
						SwipeWith2ElementRef(OR.CA_BTN_CreateAccount, OR.Blank_Object, "Down", 800, 8, true);
						Report("PASS", "Create Account Screen", "All deteails for creating new account are filled successfully and clicked on Create Account Button.",true);
					}
				}
			}
		}


		if(oGbl.Failureflag == 1)
		{
			Report("FAIL", "Create Account Screen", "Not able to fill all deteails for creating new account and Create Account Button is not clicked.",true);
		}		
	}

	public String getMonth(String mnth){
		switch(mnth.toLowerCase()){
		case "jan": return "January";
		case "feb": return "February";
		case "mar": return "March";
		case "apr": return "April";
		case "may": return "May";
		case "jun": return "June";
		case "jul": return "July";
		case "aug": return "August";
		case "sep": return "September";	
		case "oct": return "October";
		case "nov": return "November";
		case "dec": return "December";
		default: return "";

		}
	}


	public void pin_enable() throws Exception{

		oGbl.ScreenName = "Passcode Protection";
		Click(OR.PO_Link_UsePIN);
		if(WaitForElement(OR.CP_Title_Header, 60000)){
			Report("PASS", "Passcode Setting", "Navigated to PIN Setting page",true);
			String pin=TD("PIN");
			if(pin.length()==4)
			{
				for(int eachCharNo= 0; eachCharNo< pin.length(); eachCharNo++)
				{
					String eachChar = "" + pin.charAt(eachCharNo);
					SendText(eachChar);
				}

				if(IsElementFound(OR.RP_Label_RetypePasscode)){
					for(int eachCharNo= 0; eachCharNo< pin.length(); eachCharNo++)
					{
						String eachChar = "" + pin.charAt(eachCharNo);
						SendText(eachChar);
					}
					if(WaitForElement(OR.PP_Title_Header, 60000)){
						Report("PASS", "Passcode Setting", "Navigated to Passcode Setting page",true);
						VerifyElementFound(OR.PP_BTN_Enabled);
						VerifyElementFound(OR.PO_Link_Change_Passcode);
						/*String Passcode_option=GetTextIn(OR.PP_Text_passcodestatus,"Inside",0,0);
						if(Passcode_option.toLowerCase().contains("pin")){
							Report("PASS", "PIN Setting", "PIN option is enabled as passcode",true);
						}else{
							oGbl.Failureflag= 1;
							Report("FAIL", "PIN Setting", "PIN option is not enabled as passcode",true);
						}*/
					}else{
						oGbl.Failureflag= 1;
						Report("FAIL", "Passcode Setting", "Not navigated to Passcode Setting page",true);
					}
				}else{
					oGbl.Failureflag= 1;
					Report("FAIL", "Retype Passcode", "Not navigated to Retype Passcode Page",true);
				}
			}else{
				oGbl.Failureflag= 1;
				Report("FAIL", "PIN Length", "PIN Length should be equal to 4",true);
			}
		}else{
			oGbl.Failureflag= 1;
			Report("FAIL", "Passcode Setting", "Not navigated to Passcode Setting page",true);
		}

	}



	public void change_password(String option) throws Exception{

		oGbl.ScreenName = "Change Passcode";
		Click(OR.PO_Link_Change_Passcode);
		if(WaitForElement(OR.ChangeP_Title_Header, 60000)){
			Report("PASS", "Enter Passcode", "Navigated to Enter Passcode page",true);
			if(option.toLowerCase().contains("pin")){
				String pin=TD("PIN");
				if(pin.length()==4)
				{
					for(int eachCharNo= 0; eachCharNo< pin.length(); eachCharNo++)
					{
						String eachChar = "" + pin.charAt(eachCharNo);
						SendText(eachChar);
					}
				}else{
					Report("FAIL", "PIN length", "Pin length should equal to 4",true);
				}

			}else{
				if(option.toLowerCase().contains("password")){
					Type(OR.CP_Text_NewPassword,TD("Password"));
					Click(OR.ChangeP_BTN_Continue);
				}
			}
			if(WaitForElement(OR.PO_Header_Passcode, 60000)){
				Report("PASS", "Passcode Options", "Navigated to Passcode Option Page",true);
				VerifyElementFound(OR.PO_Link_UsePIN);
				VerifyElementFound(OR.PO_Link_UsePassword);	
			}else{
				Report("FAIL", "Passcode Options", "Not navigated to Passcode Option Page",true);
			}


		}else{
			Report("FAIL", "Enter Passcode", "Not navigated to Enter Passcode page",true);
		}	
	}


	public void password_enable() throws Exception{
		oGbl.ScreenName = "Passcode Protection";
		Click(OR.PO_Link_UsePassword);
		if(WaitForElement(OR.EPass_Label_EnterPassword, 60000)){
			Type(OR.EPass_Textbox_EnterPassword,TD("Password"));
			Click(OR.EPass_BTN_continue);
			if(WaitForElement(OR.ConfirmP_Label_EnterPassword, 60000)){
				Type(OR.ConfirmP_Textbox_EnterPassword,TD("Password"));
				Click(OR.ConfirmP_BTN_continue);
				//	if(WaitForElement(OR.PP_Title_AlertHeader, 60000)){
				//	VerifyElementFound(OR.PP_Text_Alertmessage);
				//	Click(OR.PP_BTN_OkButton);
				if(WaitForElement(OR.PP_Title_Header, 60000)){
					Report("PASS", "Passcode Setting", "Navigated to Passcode Setting page",true);
					VerifyElementFound(OR.PP_BTN_PEnabled);
					VerifyElementFound(OR.PO_Link_Change_Passcode);
					/*String Passcode_option=GetTextIn(OR.PP_Text_passcodestatus,"Inside",0,0);
					if(Passcode_option.toLowerCase().contains("password")){
						Report("PASS", "Password Setting", "Password option is enabled as passcode",true);
					}else{
						oGbl.Failureflag= 1;
						Report("FAIL", "Password Setting", "Password option is not enabled as passcode",true);
					}*/
				}else{
					oGbl.Failureflag= 1;
					Report("FAIL", "Passcode Setting", "Not navigated to Passcode Setting page",true);
				}
				//}
			}else{
				Report("FAIL", "Confirm Password", "Not navigated to Enter Password page",true);
			}
		}else{
			Report("FAIL", "Enter Password", "Not navigated to Enter Password page",true);
		}

	}


	public void AddtoTrackedFligth() throws Exception{
		oGbl.ScreenName = "Flight status";
		
		String GivenFlighno=TD("Flightno");
		Click(OR.LP_BTN_Menu);
		WaitForElement(OR.MO_BTN_Status, 6000);
		Sleep(1000);
		Click(OR.MO_BTN_Status);
		if(WaitForElement(OR.FS_Title_Header, 60000)){
			VerifyElementFound(OR.FS_BTN_FligthNo);
			VerifyElementFound(OR.FS_BTN_City);
			VerifyElementFound(OR.FS_BTN_PNR);
			if(IsElementFound(OR.FS_BTN_FligthNoSelected)){
				Report("PASS", "Flight number tab", "Flight number tab is selected by default",true);
			}
			else{
				Report("FAIL", "Flight number tab", "Flight number tab is not selected by default",true);		
			}
			if(IsElementFound(OR.FSBFN_TextBox_Prefix)){
				Report("PASS", "Keyword AC", "Keyword AC is prefixed",true);
			}
			else{
				Report("FAIL", "Keyword AC", "Keyword AC is not prefixed",true);		
			}
			Type(OR.FSBFN_TextBox_Flightno,TD("Flightno"));
			Click(OR.FSBFN_TextBox_Calendar);
			SelectTravelDate(TD("Date"));
			Click(OR.Dates_BTN_Confirm);
			if(IsElementFound(OR.FS_BTN_FligthNoSelected)){
				Click(OR.FSBFN_BTN_Search);
				if(WaitForElement(OR.Result_Title_Header, 120000)){
					
					if(oGbl.Device_Under_Test.startsWith("ios_app"))
					{
						String ac[]=GetElementForDynamicText(OR.AC_Text_Dynamic,"AC");
						String flightno[]=GetElementForDynamicText(OR.AC_Text_Dynamic,TD("Flightno"));
						if(IsFoundIn(ac,SwipeRight,flightno,0,0)){
							Report("PASS", "Flight List", "Flight "+TD("Flightno")+" is displayed",true);
						}else{
							oGbl.Failureflag= 1;
							Report("FAIL", "Flight List", "Flight "+TD("Flightno")+" is not displayed",true);
						}
						
					}else{
						String Flighno=GetTextIn(OR.Result_Text_FlightNumber,"Inside",0,0);
						if(Flighno.contains(GivenFlighno)){
							Report("PASS", "Flight List", "Flight "+TD("Flightno")+" is displayed",true);
						}else{
							oGbl.Failureflag= 1;
							Report("FAIL", "Flight List", "Flight "+TD("Flightno")+" is not displayed",true);
						}
					}
					
					VerifyElementFound(OR.Result_BTN_AddtoTrackedFlight);
					Click(OR.Result_BTN_AddtoTrackedFlight);
					WaitForElement_UntilDissapear(OR.Result_BTN_AddtoTrackedFlight, 1);
					VerifyElementNotFound(OR.Result_BTN_AddtoTrackedFlight);
					if(WaitForElement(OR.Result_Title_Header, 60000)){
						Report("PASS", "Result page", "User is still in Result page",true);
					}else{
						oGbl.Failureflag= 1;
						Report("FAIL", "Result page", "User navigated from Result page",true);
					}
					Click(OR.BackButton);
					if(WaitForElement(OR.FS_Title_Header, 60000)){
						Click(OR.LP_BTN_Menu);
						SwipeWhileNotFound(OR.MO_BTN_ProfileSetting, SwipeDown, 5, false);
						if(IsElementFound(OR.MO_BTN_ProfileSetting)){
							SwipeWhileNotFound(OR.MO_BTN_YourFlight, SwipeDown, 5, true);
							if(WaitForElement(OR.Flights_Title_Header, 60000)){
								Click(OR.Flights_BTN_Tracked);
								Sleep(2000);
								if(WaitForElement(OR.Flights_Title_Header, 60000)){
									System.out.println(oGbl.Failureflag);
									verifytrackedfligth("AC"+GivenFlighno);
								}else{
									Report("FAIL", "Tracked Flights page", "Not navigated to TrackedFlights page",true);
								}
							}else{
								oGbl.Failureflag= 1;
								Report("FAIL", "Flights page", "Not navigated to Flights page",true);
							}
						}else{
							oGbl.Failureflag= 1;
							Report("FAIL", "Menu", "Menu is not opened",true);
						}
					}else{
						oGbl.Failureflag= 1;
						Report("FAIL", "Flight Status", "Not navigated to Flight status page",true);
					}
				}else{
					oGbl.Failureflag= 1;
					Report("FAIL", "Result", "Result page is not loaded",true);
				}
			}else{
				oGbl.Failureflag= 1;
				Report("FAIL", "Flight Status", "Not navigated to Flight status page",true);
			}
		}else{
			oGbl.Failureflag= 1;
			Report("FAIL", "Flight Status", "Not navigated to Flight status page",true);
		}

	}

	public void verifytrackedfligth(String Flighnumber) throws Exception{
		System.out.println("Verify");
		oGbl.ScreenName = "Tracked Flight";
		if(oGbl.Failureflag==0){
			String[] checkFlight;
			String Selecteddate=TD("Date");
			String fromdate[]=Selecteddate.split("-");
			String date=fromdate[0];
			date = date.replaceFirst("^0+(?!$)", "");
			String month=fromdate[1];
			String Selectdate=date+" "+month;
			String Flight=Flighnumber;
			String or=Selectdate+","+Flight;
			//System.out.println(or);
			System.out.println("verify"+oGbl.Failureflag);
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				String[] checkdate=GetElementForDynamicText(OR.AC_Text_Dynamic,Selectdate);
				boolean chk=false;
				for(int i=0;i<5;i++){
					System.out.println(i);
					SwipeWhileNotFound(checkdate,"Down",5,false);
				//SwipeElement_ToTopOfThePage(checkFlight,"Down",5);
					if(IsElementFound(checkdate)){
						Flighnumber=Flighnumber.replace("AC","");
						String[] checkFlightno=GetElementForDynamicText(OR.AC_Text_Dynamic,Flighnumber);
						if(IsFoundIn(checkdate,SwipeDown,checkFlightno,0,0)){
							chk=true;
							break;
						}
					}
				}
				if(chk){
					Report("PASS", "Flight List", "Flight "+Flight+" is displayed",true);
				}else{
					Report("FAIL", "Flight List", "Flight "+Flight+" is not displayed",true);
				}
				System.out.println("verify1"+oGbl.Failureflag);
			}
			
			else{
				checkFlight=GetElementForDynamicText(OR.Flights_BTN_Flight,or);
			
				SwipeWhileNotFound(checkFlight,"Down",5,false);
				//SwipeElement_ToTopOfThePage(checkFlight,"Down",5);
				if(IsElementFound(checkFlight)){
					Report("PASS", "Flight List", "Flight "+Flight+" is displayed",true);
				}
				else{
					Report("FAIL", "Flight List", "Flight "+Flight+" is not displayed",true);
				}
			}
			System.out.println("verify2"+oGbl.Failureflag);
			//checkFlightNo=GetElementForDynamicText(OR.Flights_Text_FlightNo,Flighno);
			/*for(int j=0;j<10;j++){
			int count=GetElementCount(OR.Flights_BTN_Flight);
			System.out.println(Selectdate);
			for(int i=1;i<count;i++){
				String fligthdetails=GetTextInindex(OR.Flights_BTN_Flight,i,"inside",0,0);
				if(fligthdetails.trim().contains(Selectdate)){
					if(IsFoundIn(checkdate,"Down",checkFlightNo,0,0)){
						Report("PASS", "Flight List", "Flight "+TD("Flightno")+" is displayed",true);
						counter=1;
						break;
					}	
				}

			}
			if(counter==1){
				break;
			}else{
				Swipe("Down",500,500,"SwipDown");
			}
		}
		if(!(counter==1)){
			oGbl.Failureflag= 1;
			Report("FAIL", "Flight List", "Flight "+TD("Flightno")+" is not displayed",true);
		}*/
		}
	}

	public void deleteTrackedflight(String Flighn) throws Exception {
		oGbl.ScreenName = "Tracked Flight";
		String[] checkFlight;
		if(oGbl.Failureflag==0){
			String Flighno=Flighn;
			String[] checkdate,checkFlightNo;
			String[] checkFlightno=GetElementForDynamicText(OR.AC_Text_Dynamic,Flighno);
			String Selecteddate=TD("Date");
			String fromdate[]=Selecteddate.split("-");
			String date=fromdate[0];
			date = date.replaceFirst("^0+(?!$)", "");
			String month=fromdate[1];
			String year=fromdate[2];
			String Selectdate=date+" "+month;
			String Flight="AC"+Flighno;
			String or=Selectdate+","+Flight;
			checkdate = GetElementForDynamicText(OR.Flights_Text_Date,Selectdate);
			checkFlightNo = GetElementForDynamicText(OR.Flights_Text_FlightNo,Flight);
			checkFlight = GetElementForDynamicText(OR.Flights_BTN_Flight,or);
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				String[] sdate=GetElementForDynamicText(OR.AC_Text_Dynamic,Selectdate);
				boolean chk=false;
				for(int i=0;i<5;i++){
					System.out.println(i);
					SwipeWhileNotFound(checkdate,"Down",5,false);
				//SwipeElement_ToTopOfThePage(checkFlight,"Down",5);
					if(IsElementFound(checkdate)){
						if(IsFoundIn(checkdate,SwipeDown,checkFlightno,0,0)){
							Click(checkFlightno);
							chk=true;
							break;
						}
					}
				}
				if(chk){
					
					Report("PASS", "Flight List", "Flight "+Flight+" is displayed",true);
				}else{
					Report("FAIL", "Flight List", "Flight "+Flight+" is not displayed",true);
				}
				System.out.println("verify1"+oGbl.Failureflag);
			}
			
			else{
				if(IsElementFound(checkFlight)){
					Click(checkFlight);
				}
			}
				if(WaitForElement(OR.Details_Title_Header, 60000)){
					if(IsElementFound(checkdate)){
						Click(OR.Details_BTN_Menu);
						if(IsElementFound(OR.Details_BTN_Remove_tracked)){
							Click(OR.Details_BTN_Remove_tracked);
							if(WaitForElement(OR.RTF_Title_Header, 60000)){
								Click(OR.RTF_BTN_Yes);
								SwipeWhileNotFound(checkFlight,"Down",2,false);
								//SwipeElement_ToTopOfThePage(checkFlight,"Down",5);
								String fno[]=GetElementForDynamicText(OR.AC_Text_Dynamictop,Flighno);
								if(!(IsElementFound(checkFlight))&&(!(IsElementFound(fno)))){
									Report("PASS", "Flight List", "Flight "+TD("Flightno")+" is deleted",true);
								}
								else{
									Report("FAIL", "Flight List", "Flight "+TD("Flightno")+" is not deleted",true);
								}
							}else{
								oGbl.Failureflag= 1;
								Report("FAIL", "Remove Tracked option", "Remove Tracked option is not dispalyed",true);
							}
						}else{
							oGbl.Failureflag= 1;
							Report("FAIL", "Remove Tracked", "Remove Tracked button is not dispalyed",true);
						}
					}else{
						oGbl.Failureflag= 1;
						Report("FAIL", "Flight details", "Different flight is selected",true);
					}
				}else{
					oGbl.Failureflag= 1;
					Report("FAIL", "Flight details page", "Not Navigated to Fligth details page",true);
				}

			}
		
	}

	public void swipetodeleteTrackedflight() throws Exception {
		oGbl.ScreenName = "Tracked Flight";
		String[] checkFlight;
		if(oGbl.Failureflag==0){
			String Flighno=TD("Flightno");
			String[] checkdate,checkFlightNo;
			String[] checkFlightno=GetElementForDynamicText(OR.AC_Text_Dynamic,Flighno);
			String Selecteddate=TD("Date");
			String fromdate[]=Selecteddate.split("-");
			String date=fromdate[0];
			date = date.replaceFirst("^0+(?!$)", "");
			String month=fromdate[1];
			String year=fromdate[2];
			String Selectdate=date+" "+month;
			String Flight="AC"+Flighno;
			String or=Selectdate+","+Flight;
			checkdate = GetElementForDynamicText(OR.Flights_Text_Date,Selectdate);
			checkFlightNo = GetElementForDynamicText(OR.Flights_Text_FlightNo,Flight);
			checkFlight = GetElementForDynamicText(OR.Flights_BTN_Flight,or);
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				String[] sdate=GetElementForDynamicText(OR.AC_Text_Dynamic,Selectdate);
				boolean chk=false;
				for(int i=0;i<5;i++){
					System.out.println(i);
					SwipeWhileNotFound(checkdate,"Down",5,false);
				//SwipeElement_ToTopOfThePage(checkFlight,"Down",5);
					if(IsElementFound(checkdate)){
						if(IsFoundIn(checkdate,SwipeDown,checkFlightno,0,0)){
							ElementSwipe(checkFlightno,"Right",0,2000);
							chk=true;
							break;
						}
					}
				}
				if(chk){
					
					Report("PASS", "Flight List", "Flight "+Flight+" is displayed",true);
				}else{
					Report("FAIL", "Flight List", "Flight "+Flight+" is not displayed",true);
				}
				System.out.println("verify1"+oGbl.Failureflag);
			}
			else{
			
				if(IsElementFound(checkFlight)){
					ElementSwipe(OR.Flights_Text_FlightDetails,"Right",0,2000);
				}
			}
				if(IsElementFound(OR.Flights_BTN_Remove)){
					Report("PASS", "Remove option", "Remove option is displayed",true);
					Click(OR.Flights_BTN_Remove);
					if(WaitForElement(OR.RTF_Title_Header, 60000)){
						Click(OR.RTF_BTN_Yes);
						SwipeWhileNotFound(checkFlight,"Down",2,false);
						String fno[]=GetElementForDynamicText(OR.AC_Text_Dynamictop,Flighno);
						if(!(IsElementFound(checkFlight))&&(!(IsElementFound(fno)))){
							Report("PASS", "Flight List", "Flight "+TD("Flightno")+" is deleted",true);
						}
						else{
							Report("FAIL", "Flight List", "Flight "+TD("Flightno")+" is not deleted",true);
						}
					}else{
						oGbl.Failureflag= 1;
						Report("FAIL", "Remove Tracked option", "Remove Tracked option is not dispalyed",true);
					}
				}
				else{
					Report("FAIL", "Remove option", "Remove option is not displayed",true);
				}

			}
		
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: DeleteBookingRef()
	#Author: Roshith India
	#Description: This function will Delete the reference from Reference screen
	#Date of creation: 14-Oct-2015
	#Input Parameters: String PNR
	#Name of person modifying: Tester
	#Date of modification: 14-Oct-2015
	‘***************************************************/

	public void DeleteBookingRef(String PNR) throws Exception{
		if(WaitForElement(OR.Flights_Title_Header, 60000)){
			Report("PASS", "Verify Flights Screen", "Flights Screen is displayed",true);
			Click(OR.Flights_BTN_Booked);
			Sleep(2000);				
			String[] PNRLabel = GetElementForDynamicText(OR.Flights_Label_PNR,PNR);		
			if(SwipeWhileNotFound(PNRLabel,"Down",5,false)){				
				ElementSwipe(PNRLabel,SwipeRight,0,2000);
				//ElementSwipe(PNRLabel,SwipeRight,0,5000);
				if(!IsElementFound(OR.Flights_BTN_Remove)){ElementSwipe(GetElementForDynamicIndex(PNRLabel,1),SwipeRight,0,2000);}
				if(IsElementFound(OR.Flights_BTN_Remove)){
					VerifyElementFound(OR.Flights_BTN_Email);
					VerifyElementFound(OR.Flights_BTN_Remove);
					Report("PASS", "Remove option", "Remove option is displayed",true);
					Click(OR.Flights_BTN_Remove);
					if(WaitForElement(OR.RBR_Title_Header, 60000)){
						Click(OR.RBR_BTN_RemoveTrip);
						Sleep(3000);
						Click(OR.LP_BTN_Menu);
						Click(OR.MO_BTN_Home);
						Sleep(500);
						Click(OR.LP_BTN_Menu);
						Click(OR.MO_BTN_YourFlight);
						Click(OR.Flights_BTN_Booked);
						
						SwipeWhileNotFound(PNRLabel,"Down",2,false);					
						//SwipeElement_ToTopOfThePage(checkFlight,"Down",5);
						if(!(IsElementFound(PNRLabel))){
							Report("PASS", "Verify Booking Reference", "Booking Ref: "+ PNR +" is deleted",true);
						}
						else{
							Report("FAIL", "Verify Booking Reference", "Booking Ref: "+ PNR +" is not deleted",true);
						}
					}else{
						oGbl.Failureflag= 1;
						Report("FAIL", "Remove Tracked option", "Remove Tracked option is not dispalyed",true);
					}
				}
				else{
					Report("FAIL", "Remove option", "Remove option is not displayed",true);
				}			
			}else{
				Report("FAIL", "Verify PNR", "Booked PNR is not displayed",true);
			}
		}else{
			Report("FAIL", "Verify Flights Screen", "Flights Screen is not displayed",true);
			oGbl.Failureflag = 1;	
		}	
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: DeleteBookingRef1()
	#Author: Roshith India
	#Description: This function will Delete the reference from TripItinerary screen
	#Date of creation: 14-Oct-2015
	#Input Parameters: String PNR
	#Name of person modifying: Tester
	#Date of modification: 14-Oct-2015
	‘***************************************************/

	public void DeleteBookingRef1(String PNR) throws Exception{
		if(WaitForElement(OR.Flights_Title_Header, 60000)){
			Report("PASS", "Verify Flights Screen", "Flights Screen is displayed",true);
			Click(OR.Flights_BTN_Booked);
			Sleep(2000);				
			String[] PNRLabel = GetElementForDynamicText(OR.Flights_Label_PNR,PNR);		
			if(SwipeWhileNotFound(PNRLabel,"Down",5,true)){		
				if(WaitForElement(OR.BFTR_Title_Header, 30000)){
					Report("PASS", "TripItinerary Screen", "TripItinerary is displayed",true);
					Click(OR.Details_Menu_Option);
					Sleep(2000);
					VerifyElementFound(OR.TI_Menu_EmailIrtinerary);
					VerifyElementFound(OR.TI_Menu_RemoveFlight);
					VerifyElementFound(OR.TI_Menu_BookSimilarTrip);
					Click(OR.TI_Menu_RemoveFlight);
					if(WaitForElement(OR.TI_BTN_Close, 5000) && WaitForElement(OR.TI_BTN_Remove,5000)){
						Report("PASS", "Verify Confirmation buttons", "Confirmation buttons are displayed",true);
						Sleep(2000);
						Click(OR.TI_BTN_Close);
						Sleep(4000);
						if(WaitForElement(OR.BFTR_Title_Header, 30000)){
							Report("PASS", "Verify Details page", "Screen stays in Details screen",true);
						}else{
							Report("FAIL", "Verify Details page", "Screen does not stay in Details screen",true);
						}
						Click(OR.Details_Menu_Option);
						Sleep(2000);
						Click(OR.TI_Menu_RemoveFlight);
						Sleep(2000);
						if(WaitForElement(OR.TI_BTN_Close, 5000) && WaitForElement(OR.TI_BTN_Remove,5000)){
							Report("PASS", "Verify Confirmation buttons", "Confirmation buttons are displayed",true);
							Click(OR.TI_BTN_Remove);
							if(WaitForElement(OR.Flights_Title_Header, 30000)){
								SwipeWhileNotFound(PNRLabel,"Down",2,false);					
								//SwipeElement_ToTopOfThePage(checkFlight,"Down",5);
								if(!(IsElementFound(PNRLabel))){
									Report("PASS", "Verify Booking Reference", "Booking Ref: "+ PNR +" is deleted",true);
								}
								else{
									Report("FAIL", "Verify Booking Reference", "Booking Ref: "+ PNR +" is not deleted",true);
								}
							}else{
								Report("FAIL", "Verify Flights Screen", "Flights Screen is not displayed",true);
							}
						}else{
							Report("FAIL", "Verify Confirmation buttons", "Confirmation buttons are not displayed",true);
						}
					}else{
						Report("FAIL", "Verify Confirmation buttons", "Confirmation buttons are not displayed",true);
					}
				}else{
					Report("FAIL", "Verify Details Screen", "Details Screen is not displayed",true);
				}
			}else{
				Report("FAIL", "Verify PNR", "Booked PNR is not displayed",true);
			}
		}else{
			Report("FAIL", "Verify Flights Screen", "Flights Screen is not displayed",true);
			oGbl.Failureflag = 1;	
		}	
	}



	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: BookFlight()
	#Author: Roshith India
	#Description: This function will Book a flight with logged in user or guest user and then return the PNR Number booked.
	#Date of creation: 14-Oct-2015
	#Input Parameters: boolean guestUser 
	#Name of person modifying: Tester
	#Date of modification: 14-Oct-2015
	‘***************************************************/
	public String BookFlight(boolean guestUser) throws Exception
	{
		String PNRno = "";
		try
		{
			if(Functionality_Select("Book Flight")){			
				searchFlightoneway(TD("Origin_airport"),TD("Destination_airport"),TD("Departure_dt"),TD("Adult_no"),TD("Child_no") ,TD("Youth_no"),"Yes");
				SelectFlight(TD("Origin_airport"),TD("Destination_airport"),TD("Sort_by"),TD("Flight_type"),0,TD("Flight_no"));
				SelectFligthclass(TD("Class"),TD("Class_type"),0);	
				WaitForElement(OR.TO_Text_Title,10000);
				for(int counter = 1; counter<=60; counter++)
				{
					if(IsElementFound(OR.TO_Text_Title))
					{
						SwipeWith2ElementRef(OR.LP_Text_Skip, OR.Blank_Object, "Down", 800, 8, true);
						WaitForElement(OR.FSRT_Text_FlightSummary, 60000);
						SwipeWith2ElementRef(OR.FSRT_BTN_Continue, OR.Blank_Object, "Down", 800, 8, true);
						break;
					}else if(IsElementFound(OR.FSRT_Text_FlightSummary))
					{
						SwipeWith2ElementRef(OR.FSRT_BTN_Continue, OR.Blank_Object, "Down", 800, 8, true);
						break;
					}
					else
					{
						Sleep(1000);
					}
				}

				if(guestUser==true)
				{
					SignIn("Yes","","");	
				}else
				{
					SignIn("No",TD("Uname"),TD("Pwd"));
				}

				int noOfAdults = Integer.parseInt(TD("Adult_no"));
				int noOfYouths = Integer.parseInt(TD("Youth_no"));
				int noOfChilds = Integer.parseInt(TD("Child_no"));
				int totalPassenger = noOfAdults + noOfYouths + noOfChilds;
				for(int eachPassenger=1; eachPassenger<=totalPassenger; eachPassenger++)
				{
					selectPassenger(""+eachPassenger,"No", guestUser);
					AddPassengerDetails("1");	
				}

				SelectPayment();
				paymentmethod("credit");
				CreditCardPayment(TD("Cardno"),TD("Month"), TD("year"), TD("name"), TD("Country"), TD("province"), TD("street"), TD("city"), TD("postalcode"));
				if(oGbl.Failureflag == 0){
					if(!SwipeWhileNotFound(OR.BS_BTN_Paynow,SwipeDown,5,true)){
						Report("FAIL", "Pay now button", "Pay now button is not displayed",true);
						oGbl.Failureflag = 1;
					}				
					PNRno = ValidateConfirmation();
				}			
			}
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return PNRno;
	}






	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: VerifyTrip()
	#Author: Roshith India
	#Description: This function will verify the recent search result.
	#Date of creation: 20-Oct-2015
	#Input Parameters:  boolean oneway, String source, String destination,  String Fromdate, String Todate, String adult, String child , String youth ,String promo
	#Name of person modifying: Tester
	#Date of modification: 20-Oct-2015
	‘***************************************************/
	public void VerifyTrip(boolean oneway,String source, String destination,  String Fromdate, String Todate, String adult, String child , String youth ,String promo){
		try{
			if(WaitForElement(OR.BAT_Text_title,30000)){
				if(oneway){
					VerifyElementFound(OR.BAT_BTN_OneWaySelected);
				}else{
					VerifyElementFound(OR.BAT_BTN_RoundTripSelected);
				}
				String[] BAT_TextBox_Origin_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Origin, source.toUpperCase());
				
				String strOrigin = ElementGetText(BAT_TextBox_Origin_Dyn);
				String[] BAT_TextBox_Destination_Dyn = GetElementForDynamicText(OR.BAT_TextBox_Destination, destination.toUpperCase());
				String strDestination = ElementGetText(BAT_TextBox_Destination_Dyn);
				if(strOrigin.contains(source) && strDestination.contains(destination)){
					Report("PASS", "Verify Source and destination airport", "Source and destination airport are displayed correctly",true);
				}else{
					Report("FAIL", "Verify Source and destination airport", "Source and destination airport are not displayed correctly",true);
				}
				String strDdate = ElementGetText(OR.BAT_Label_Departuredt);
				String strRDate = "";
				if(!oneway){
					strRDate = ElementGetText(OR.BAT_Label_Returndt);					
				}	
				String[] arr1 = Fromdate.split("-");
				boolean flag = false;
				if(oneway){
					flag = strDdate.toLowerCase().contains(arr1[0]) && strDdate.toLowerCase().contains(arr1[1].toLowerCase()); 
				}else{
					String[] arr2 = Todate.split("-");
					flag = strDdate.toLowerCase().contains(arr1[0]) && strDdate.toLowerCase().contains(arr1[1].toLowerCase()) && strRDate.toLowerCase().contains(arr2[0]) && strRDate.toLowerCase().contains(arr2[1].toLowerCase());
				}
				if(flag){
					Report("PASS", "Verify Selected dates", "Selected dates are displayed correctly",true);
				}else{
					Report("FAIL", "Verify Selected dates", "Selected dates are not displayed correctly",true);
				}
				String strPassengers = ElementGetText(OR.BAT_TextBox_NoOfPassenger);
				String A = "" ,Y = "",C = "";
				if(!(adult.contains("0"))){
					A = adult+" adult";
				}
				if(!(youth.contains("0"))){
					Y = youth+" youth";
				}
				if(!(child.contains("0"))){
					C = child+" child";
				}
				if(strPassengers.toLowerCase().contains(A) && strPassengers.toLowerCase().contains(Y) && strPassengers.toLowerCase().contains(C)){
					Report("PASS", "Verify Passengers", "Passengers count are displayed correctly",true);
				}else{
					Report("FAIL", "Verify Passengers", "Passengers count are not displayed correctly",true);
				}
				/*if(promo.toLowerCase().equals("yes")){
					//	Type(OR.BAT_TextBox_Promocode,TD("promotion_code")); 
					String text = ElementGetText(OR.BAT_TextBox_Promocode);
					if(text.contains("")){
						Report("PASS", "Verify Promo code", "Promo code is displayed correctly as blank",true);
					}else{
						Report("FAIL", "Verify Promo code", "Promo code is not displayed correctly",true);
					}
				}*/
			}else{
				Report("FAIL", "Verify Book a flight screen", "Book a flight screen is not displayed",true);
			}
		}catch(Exception e){
			e.printStackTrace();		
		}
	}





	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: DeleteSavedPassenger()
	#Author: Roshith India
	#Description: This function will delete the saved passengers from the Mobile+ account.
	#Date of creation: 20-Oct-2015
	#Input Parameters: NA
	#Name of person modifying: Tester
	#Date of modification: 14-Oct-2015
	‘***************************************************/
	public void DeleteSavedPassenger() throws Exception
	{
		try
		{
			Click(OR.PS_BTN_SavePassenger);
			if(WaitForElement(OR.SP_Title_Header, 20000))
			{
				while(IsElementFound(OR.SP_Text_PassengerName))
				{
					Click(OR.SP_Text_PassengerName);
					if(WaitForElement(OR.EP_Title_Header, 20000))
					{
						SwipeWith2ElementRef(OR.PS_BTN_RemovePassenger,OR.Blank_Object,"Down", 800, 8, false);
						Click(OR.PS_BTN_RemovePassenger);
						Sleep(2000);
						Click(OR.RBR_BTN_Remove);
						if(!WaitForElement(OR.SP_Title_Header, 60000))
						{
							break;
						}
					}
				}

			}

		}catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}






	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: DeleteSavedCreditCard()
	#Author: Roshith India
	#Description: This function will delete the saved Credit card from the Mobile+ account.
	#Date of creation: 20-Oct-2015
	#Input Parameters: NA
	#Name of person modifying: Tester
	#Date of modification: 14-Oct-2015
	‘***************************************************/
	public void DeleteSavedCreditCard() throws Exception
	{
		try
		{
			Click(OR.PS_BTN_SavedCreditcard);
			if(WaitForElement(OR.PS_TXT_SavedCards, 20000))
			{
				while(IsElementFound(OR.PS_TXT_SavedCardsNumber))
				{
					Click(OR.PS_TXT_SavedCardsNumber);
					if(WaitForElement(OR.PS_TXT_EditCardsNumber, 20000))
					{
						SwipeWith2ElementRef(OR.PS_TXT_RemoveCardsNumber,OR.Blank_Object,"Down", 800, 8, true);
						Click(OR.RBR_BTN_Remove);
						Sleep(5000);
						Click(OR.BackButton);
						Click(OR.PS_BTN_SavedCreditcard);
						if(!WaitForElement(OR.PS_TXT_SavedCards, 60000))
						{
							break;
						}
					}
				}

			}

		}catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: CancelBooking()
	#Author: Roshith India
	#Description: This function will cancel the booking
	#Date of creation: 9-Dec-2015
	#Input Parameters: NA
	#Name of person modifying: Tester
	#Date of modification: 9-Dec-2015
	‘***************************************************/
	public void CancelBooking(String PNRno){
		try{
			Click(OR.LP_BTN_Menu);
			WaitForElement(OR.MO_BTN_YourFlight, 6000);
			Click(OR.MO_BTN_YourFlight);
			WaitForElement(OR.Flights_Title_Header, 20000);
			SwipeWhileNotFound(GetElementForDynamicText(OR.AC_Text_Dynamic, PNRno), SwipeDown, 10, true);				
			WaitForElement(OR.BFTR_Title_Header, 15000);
			SwipeWhileNotFound(OR.BFTR_Text_ManageBooking, SwipeDown, 3, true);
			//WaitForElement(OR.MB_Title_Header, 10000);
			WaitForElement(OR.MB_Text_CancelBooking, 10000);
			Click(OR.MB_Text_CancelBooking);	
			WaitForElement(OR.MB_Text_AuthenticationWarning, 10000);
			if(WaitForElement(OR.MB_TextBox_CreditCardLast4Digit, 5000)){
				String Card = TD("Cardno").substring(TD("Cardno").length() - 4);
				Type(OR.MB_TextBox_CreditCardLast4Digit,Card);
				Type(OR.MB_TextBox_CreditCardMonth,TD("Month"));
				Type(OR.MB_TextBox_CreditCardyear,TD("year"));
				SwipeWhileNotFound(OR.MB_BTN_Continue, SwipeDown, 5, true);
				WaitForElement(OR.MB_Text_CancelationPoilicy, 10000);	
				Click(OR.MB_BTN_CancelEntireBooking);
				Sleep(1000);
				Click(OR.MB_BTN_CancelBooking);
				WaitForElement(OR.MB_Text_CancelationSuccesfullMSG, 80000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: ChangeFlightauthenticate()
	#Author: Vijayalakshmi India
	#Description: This function will Select Change Fligth and authenticate the user 
	#Date of creation: 9-Dec-2015
	#Input Parameters: NA
	#Name of person modifying: Tester
	#Date of modification: 9-Dec-2015
	‘***************************************************/
	public void ChangeFlightauthenticate(String cc, String expirymonth,String expiryYear){
		try{
			SwipeWhileNotFound(OR.BFTR_Text_ManageBooking,"Down",5,true);
			if(WaitForElement(OR.MB_Title_Header, 5000)){
				Click(OR.MB_BTN_ChangeBooking);
				if(WaitForElement(OR.MB_Title_Header, 5000)){
					VerifyElementFound(OR.MB_Text_AuthenticationWarning);
					VerifyElementFound(OR.MB_TextBox_CreditCardLast4Digit);
					VerifyElementFound(OR.MB_TextBox_CreditCardMonth);
					VerifyElementFound(OR.MB_TextBox_CreditCardyear);
					VerifyElementFound(OR.MB_BTN_Continue);
					ElementSendText(OR.MB_TextBox_CreditCardLast4Digit,cc);
					ElementSendText(OR.MB_TextBox_CreditCardMonth,expirymonth);
					ElementSendText(OR.MB_TextBox_CreditCardyear,expiryYear);
					Click(OR.MB_BTN_Continue);
					if(WaitForElement(OR.CF_Title_ChangeFlight, 5000)){

						Report("PASS", "Change flight", "Navigated to Change Flight",true);

					}else{
						Report("FAIL", "Change flight", "Not Navigated to Change Flight",true);
					}
				}
				else{
					Report("FAIL", "Authentication", "Not Not navigated to Authentication page",true);	
				}
			}else{
				Report("FAIL", "Manage Booking", "Not Navigated to manage Booking Page",true);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*‘**************************************************
    #Project Name: Air Canada Mobile Automation
    #Function Name: RetrieveBooking()
    #Author: Ninad India
    #Description: This function will Retrieve booking in the app via Booking reference and Passenger last Name
    #Date of creation: 09-Mar-2016
    #Input Parameters: String PNRno,String lastName
    #Name of person modifying: Tester
    #Date of modification: 09-Mar-2016
    ‘***************************************************/
	public void RetrieveBooking(String PNRno,String lastName){
		try{
			Click(OR.LP_BTN_Menu);
			WaitForElement(OR.MO_BTN_Status, 6000);
			Click(OR.MO_BTN_Status);
			if(WaitForElement(OR.FS_Title_Header, 60000))
			{
				Click(OR.FS_BTN_PNR);                    
				Type(OR.FS_TextBox_PNRNO,PNRno);
				Type(OR.FS_TextBox_PassengerName,lastName);
				Click(OR.FS_BTN_Search);   
				if(WaitForElement(OR.BFTR_Title_Header,10000))
				{             
					Report("PASS", "Trip Itinerary screen", "Application is able to retrieve the PNR by using Booking reference and Last Name",true);          
				}
				else if(WaitForElement(OR.Flights_TXT_NoFlightFound,100))     
				{
					Report("FAIL", "Trip Itinerary screen", "Unable to retrieve the PNR by using Booking reference and Last Name. Either PNR reference or Last name is Incorrect",true);
				}
				else if(WaitForElement(OR.AC_NetworkNotAvailableErrorMsgText,100))
				{
					Report("FAIL", "Trip Itinerary screen", "Unable to retrieve the PNR.No WIFI or Internet Connection  ",true);
				}

			}
			else
			{
				Report("FAIL", "Find a Flight     screen", "Find a Flight screen not displayed",true);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}      

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: ChangeFlight()
	#Author: Vijayalakshmi India
	#Description: This function will Select and change the Travel details 
	#Date of creation: 9-Dec-2015
	#Input Parameters: NA
	#Name of person modifying: Tester
	#Date of modification: 9-Dec-2015
	‘***************************************************/
	public void ChangeFlight(String FlightNo){
		try{
			String[] flightnumber=GetElementForDynamicText(OR.DF_Text_FlightNo,FlightNo);
			SwipeWhileNotFound(flightnumber,"Down",5,true);
			if(WaitForElement(OR.CT_Title_ChangeTrip, 5000)){
				Report("PASS", "Change Trip", "Navigated to Change TRIP",true);
				Click(OR.CT_BTN_Arrow);
				String Flight_no=ElementGetText(OR.CT_Text_flightNO);
				if(Flight_no.contentEquals(FlightNo)){
					Click(OR.CT_BTN_Arrow);
				}else{
					Report("FAIL", "Flight Selected", "Flight selected is not"+FlightNo,true);	
				}
			}
			else{
				Report("FAIL", "Change Trip", "Not Navigated to Change TRIP",true);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	public void SelectChangeflightdetails(String fromdate,String todate,String soruce,String destination,String roundtrip)
	{
		try{

			if(!(fromdate.isEmpty())){
				Click(OR.BAT_TextBox_SelectDate);
				if(roundtrip.toUpperCase().contentEquals("Y")){
					date(fromdate);
					date(todate);
				}
				else{
					date(fromdate);
				}
				Click(OR.Dates_BTN_Confirm); 
			}
			if(!(soruce.isEmpty())){
				sourcechange(soruce);
			}
			if(!(destination.isEmpty())){
				destinationchange(destination);		
			}


		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void SelectChangeflight(String deptFlight,String ReturnFlight){

		try{

			if(!(deptFlight.isEmpty())){
				SelectFlight(TD("Origin_airport"),TD("Destination_airport"),TD("Sort_by"),TD("Flight_type"),0,deptFlight);
				oGbl.status=1;
				SelectFligthclass(TD("Class"),TD("Class_type"),0);	
				oGbl.status=0;
			}
			if(!(ReturnFlight.isEmpty())){
				SelectFlight(TD("Origin_airport"),TD("Destination_airport"),TD("ASort_by"),TD("AFlight_type"),0,ReturnFlight);
				oGbl.status=1;
				SelectFligthclass(TD("AClass"),TD("AClass_type"),0);	
				oGbl.status=0;

			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void sourcechange(String Source){

		try{
			Click(OR.BAT_TextBox_Origin);
			if(WaitForElement(OR.SA_Text_Heading, 60000)){
				Click_Type(OR.SA_Textbox_Searchbox,Source);	
				if(!IsElementFound(OR.BAT_List_Option)){Click_Type(OR.SA_Textbox_Searchbox,Source);}
				Click(OR.BAT_List_Option);
				WaitForElement(OR.SA_Text_Heading, 6000);
				String strSA =ElementGetText(OR.BAT_TextBox_Origin);
				Report("INFO",strSA +" airport should be selected",strSA +" airport is selected", true);

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void destinationchange(String destination){

		try{
			Click(OR.BAT_TextBox_Destination);
			if(WaitForElement(OR.DA_Text_Heading, 60000)){
				Click_Type(OR.DA_Textbox_Searchbox,destination); 
				if(!IsElementFound(OR.BAT_List_Option)){Click_Type(OR.SA_Textbox_Searchbox,destination);}
				Click(OR.BAT_List_Option);
				WaitForElement(OR.SA_Text_Heading, 6000);
				String strDA =ElementGetText(OR.BAT_TextBox_Destination);	
				Report("INFO",strDA +" airport should be selected",strDA +" airport is selected", true);

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	public void date(String date){
		try{
			if(WaitForElement(OR.Dates_Title_Header, 60000)){
				SelectTravelDate(date);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void Changesummary_Verification(){
		try{
			if(WaitForElement(OR.CS_Title_ChangeSummary, 60000)){
				VerifyElementFound(OR.CS_Text_currentBooking);
				VerifyElementFound(OR.CS_Text_PNR);
				VerifyElementFound(OR.CS_Text_FlightNo);
				String FlightNo=ElementGetText(OR.CS_Text_FlightNo);
				if(FlightNo.contentEquals(TD("ChangeDepartureFlight"))){
					Report("PASS", "Flight selected", FlightNo+" is selected",true);
					String[] payment=GetElementForDynamicText(OR.DF_Text_FlightNo,"Payment");
					SwipeWhileNotFound(payment,SwipeDown,5,false);
					String[] additionalCharges=GetElementForDynamicText(OR.DF_Text_FlightNo,"Additional Charges");
					VerifyElementFound(additionalCharges);
					String[] fareDetails=GetElementForDynamicText(OR.DF_Text_FlightNo,"Fare details & taxes");
					SwipeWhileNotFound(fareDetails,SwipeDown,5,false);
					String[] AcceptButton=GetElementForDynamicText(OR.DF_Text_contains,"I accept");
					SwipeWhileNotFound(AcceptButton,SwipeDown,5,true);
					String[] cancel=GetElementForDynamicText(OR.DF_Text_FlightNo,"Cancel changes");
					VerifyElementFound(cancel);
					String[] AcceptButton1=GetElementForDynamicText(OR.DF_Text_contains,"I accept");
					String[] confirmbutton=GetElementForDynamicIndex(AcceptButton1,1);
					VerifyElementFound(confirmbutton);
					AcceptButton1=GetElementForDynamicText(OR.DF_Text_contains,"I accept");
					confirmbutton=GetElementForDynamicIndex(AcceptButton1,1);
					Click(confirmbutton);
					String[] Change_Confirmation=GetElementForDynamicText(OR.DF_Text_FlightNo,"Change confirmation");
					if(WaitForElement(Change_Confirmation, 60000)){
						String[] confirmationmessage=GetElementForDynamicText(OR.DF_Text_FlightNo,"Your flight change is confirmed.");
						VerifyElementFound(confirmationmessage);
						Report("PASS", "Change confirmation", "Change Confirmation page is displayed",true);
					}else{
						Report("FAIL", "Change confirmation", "Change Confirmation page is not displayed",true);
					}
					//
					//

				}
				else{
					Report("FAIL", "Flight not selected", FlightNo+" is not selected",true);
				}


			}else{
				Report("FAIL", "Change Summary", "Not Navigated to Change Summary",true);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//<<<<<<< .mine
	/*	

	public void FeildValidation(String Type,String[]element,String[]clickelement,String Description,String maxlength, String value){
		try{
			if(Type.toLowerCase().contains("email")){
				if(IsElementFound(element)){

					maxlength(element,maxlength,Description);
					ElementSendText(element,"sdfgdf");
					SwipeWhileNotFound(clickelement,SwipeDown,5,true);
					String[] errortext= GetElementForDynamicText(OR.AC_Text_Dynamic,"Email is");
					VerifyElementFound(errortext);
					SwipeWhileNotFound(element,SwipeUp,5,false);

					ElementSendText(element,"sdfgdf@d");
					SwipeWhileNotFound(clickelement,SwipeDown,5,true);
					errortext= GetElementForDynamicText(OR.AC_Text_Dynamic,"Email is");
					VerifyElementFound(errortext);
					SwipeWhileNotFound(element,SwipeUp,5,false);

					ElementSendText(element,"sdfgdf@d.com");
					SwipeWhileNotFound(clickelement,SwipeDown,5,true);
					errortext= GetElementForDynamicText(OR.AC_Text_Dynamic,"Email is");
					VerifyElementNotFound(errortext);
					SwipeWhileNotFound(element,SwipeUp,5,false);
					ElementSendText(element,value);

				}else{
					Report("FAIL", "Email Textbox", Description+" is Not Present",true);
				}

			}else if(Type.toLowerCase().contains("signintextbox")){
				maxlength(element,maxlength,Description);
				ElementSendText(element,value);
			}else if(Type.toLowerCase().contains("textbox")){
				maxlength(element,maxlength,Description);
				ElementSendText(element,value);

			}else if(Type.toLowerCase().contains("password")){
				Click(element);
				maxlength(element,maxlength,Description);
				ElementSendText(element,"4");
				passwordCheck("false","false","false");
				ElementSendText(element,"a");
				passwordCheck("false","false","true");
				ElementSendText(element,"A");
				passwordCheck("false","true","false");
				ElementSendText(element,"Aa");
				passwordCheck("false","true","true");
				ElementSendText(element,"awedfrga");
				passwordCheck("true","false","true");
				ElementSendText(element,value);
			}else if(Type.toLowerCase().contains("phonenumber")){
				SwipeWhileNotFound(element,SwipeDown,5,true);
				maxlength(element,maxlength,Description);
				ElementSendText(element," ");
				Click(element);
				SendText("{BKSP}");
				CloseKeyboard();
				Click(element);
				SendText("Aa");
				CloseKeyboard();
				String text=ElementGetText(element);
				if((text.length()>0)){
					Report("FAIL", "Max Length", Description+" is accepting Alphabets characters",true);
				}else{
					Report("PASS", "Max Length", Description+" is not accepting Alphabets characters",true);
				}
				ElementSendText(element,value);
			}
		}catch(Exception e){

		}
	}


	public void maxlength(String[]element,String maxlength, String Description){
		try{


			if(maxlength.toLowerCase().contains("n")){
				Report("PASS", "Max Length", Description+" is a free textbox",true);
			}else{
				int length=Integer.parseInt(maxlength);
				Click(element);
				SendText("1234567890123456789012345678900453462");
				CloseKeyboard();
				String text=ElementGetText(element);
				if(!(text.length()==length)){
					Report("FAIL", "Max Length", Description+" is accepting more characters",true);
				}else{
					Report("PASS", "Max Length", Description+" is not accepting more characters",true);
				}
			}


		}catch(Exception e){

		}
	}


	public void passwordCheck(String status1,String status2,String status3 ){
		try{
			String status = oGbl.Client.elementGetProperty("NATIVE", "xpath=//*[@id='checkbox']", 0, "checked");

			if(status.toLowerCase().contains(status1)){
				if(status.toLowerCase().contains("false")){
					Report("PASS", "Verify Password hint for eight characters", "Password hint for eight characters is not met for the new password",true);
				}else if(status.toLowerCase().contains("true")){
					Report("PASS", "Verify Password hint for eight characters", "Password hint for eight characters is met for the new password",true);
				}
			}
			else{
				Report("FAIL", "Verify Password hint for eight characters", "Issue in Password hint, value"+status+" is displayed instead of "+status1,true);
			}
			 status = oGbl.Client.elementGetProperty("NATIVE", "xpath=//*[@id='checkbox']", 1, "checked");

			 if(status.toLowerCase().contains(status2)){
					if(status.toLowerCase().contains("false")){
						Report("PASS", "Verify Password hint for 1 Capital letter", "Password hint for 1 Capital letter is not met for new password",true);
					}else if(status.toLowerCase().contains("true")){
						Report("PASS", "Verify Password hint for 1 Capital letter", "Password hint for 1 Capital letter is  met for new password",true);
					}
				}
				else{
					Report("FAIL", "Verify Password hint for 1 Capital letter", "Issue in Password hint for 1 capital letter, value "+status+" is displayed instead of "+status2,true);
				}

			 status = oGbl.Client.elementGetProperty("NATIVE", "xpath=//*[@id='checkbox']", 2, "checked");

			 if(status.toLowerCase().contains(status3)){
					if(status.toLowerCase().contains("false")){
						Report("PASS", "Verify Password hint for 1 small letter", "Password hint for 1 small letter is not met for new password",true);
					}else if(status.toLowerCase().contains("true")){
						Report("PASS", "Verify Password hint for 1 small letter", "Password hint for 1 small letter is  met for new password",true);
					}
				}
				else{
					Report("FAIL", "Verify Password hint for 1 small letter", "Issue in Password hint for 1 small letter, value"+status+" is displayed instead of "+status3,true);
				}



		}catch(Exception e){

		}
	}
}
=======*/

	public void FeildValidation(String Type,String[]element,String[]clickelement,String Description,String maxlength, String value){
		try{
			if(Type.toLowerCase().contains("email")){
				if(IsElementFound(element)){

					maxlength(element,maxlength,Description);
					ElementSendText(element,"sdfgdf");
					SwipeWhileNotFound(clickelement,SwipeDown,5,true);
					String[] errortext= GetElementForDynamicText(OR.AC_Text_Dynamic,"Email is");
					VerifyElementFound(errortext);
					SwipeWhileNotFound(element,SwipeUp,5,false);

					ElementSendText(element,"sdfgdf@d");
					SwipeWhileNotFound(clickelement,SwipeDown,5,true);
					errortext= GetElementForDynamicText(OR.AC_Text_Dynamic,"Email is");
					VerifyElementFound(errortext);
					SwipeWhileNotFound(element,SwipeUp,5,false);

					ElementSendText(element,"sdfgdf@d.com");
					SwipeWhileNotFound(clickelement,SwipeDown,5,true);
					errortext= GetElementForDynamicText(OR.AC_Text_Dynamic,"Email is");
					VerifyElementNotFound(errortext);
					SwipeWhileNotFound(element,SwipeUp,5,false);
					ElementSendText(element,value);

				}else{
					Report("FAIL", "Email Textbox", Description+" is Not Present",true);
				}

			}else if(Type.toLowerCase().contains("signinpassword")){
				maxlength(element,maxlength,Description);
				SwipeWhileNotFound(clickelement,SwipeDown,5,true);
				String[] errortext= GetElementForDynamicText(OR.AC_Text_Dynamic,"Password is missing");
				VerifyElementFound(errortext);
				ElementSendText(element,value);
			}else if(Type.toLowerCase().contains("textbox")){
				maxlength(element,maxlength,Description);
				ElementSendText(element,value);

			}else if(Type.toLowerCase().contains("password")){
				Click(element);
				maxlength(element,maxlength,Description);
				ElementSendText(element,"4");
				passwordCheck("false","false","false");
				ElementSendText(element,"a");
				passwordCheck("false","false","true");
				ElementSendText(element,"A");
				passwordCheck("false","true","false");
				ElementSendText(element,"Aa");
				passwordCheck("false","true","true");
				ElementSendText(element,"awedfrga");
				passwordCheck("true","false","true");
				ElementSendText(element,value);
			}else if(Type.toLowerCase().contains("phonenumber")){
				SwipeWhileNotFound(element,SwipeDown,5,true);
				maxlength(element,maxlength,Description);
				ElementSendText(element," ");
				Click(element);
				SendText("{BKSP}");
				CloseKeyboard();
				Click(element);
				SendText("Aa");
				CloseKeyboard();
				String text=ElementGetText(element);
				if((text.length()>0)){
					Report("FAIL", "Max Length", Description+" is accepting Alphabets characters",true);
				}else{
					Report("PASS", "Max Length", Description+" is not accepting Alphabets characters",true);
				}
				ElementSendText(element,value);
			}
		}catch(Exception e){

		}
	}


	public void maxlength(String[]element,String maxlength, String Description){
		try{


			if(maxlength.toLowerCase().contains("n")){
				Report("PASS", "Max Length", Description+" is a free textbox",true);
			}else{
				int length=Integer.parseInt(maxlength);
				Click(element);
				SendText("123456789555460123456789012345678900453462");
				CloseKeyboard();
				String text=ElementGetText(element);
				if(!(text.length()==length)){
					Report("FAIL", "Max Length", Description+" is accepting more characters",true);
				}else{
					Report("PASS", "Max Length", Description+" is not accepting more characters",true);
				}
			}


		}catch(Exception e){

		}
	}


	public void passwordCheck(String status1,String status2,String status3 ){
		try{
			String status = oGbl.Client.elementGetProperty("NATIVE", "xpath=//*[@id='checkbox']", 0, "checked");

			if(status.toLowerCase().contains(status1)){
				if(status.toLowerCase().contains("false")){
					Report("PASS", "Verify Password hint for eight characters", "Password hint for eight characters is not met for the new password",true);
				}else if(status.toLowerCase().contains("true")){
					Report("PASS", "Verify Password hint for eight characters", "Password hint for eight characters is met for the new password",true);
				}
			}
			else{
				Report("FAIL", "Verify Password hint for eight characters", "Issue in Password hint, value"+status+" is displayed instead of "+status1,true);
			}
			status = oGbl.Client.elementGetProperty("NATIVE", "xpath=//*[@id='checkbox']", 1, "checked");

			if(status.toLowerCase().contains(status2)){
				if(status.toLowerCase().contains("false")){
					Report("PASS", "Verify Password hint for 1 Capital letter", "Password hint for 1 Capital letter is not met for new password",true);
				}else if(status.toLowerCase().contains("true")){
					Report("PASS", "Verify Password hint for 1 Capital letter", "Password hint for 1 Capital letter is  met for new password",true);
				}
			}
			else{
				Report("FAIL", "Verify Password hint for 1 Capital letter", "Issue in Password hint for 1 capital letter, value "+status+" is displayed instead of "+status2,true);
			}

			status = oGbl.Client.elementGetProperty("NATIVE", "xpath=//*[@id='checkbox']", 2, "checked");

			if(status.toLowerCase().contains(status3)){
				if(status.toLowerCase().contains("false")){
					Report("PASS", "Verify Password hint for 1 small letter", "Password hint for 1 small letter is not met for new password",true);
				}else if(status.toLowerCase().contains("true")){
					Report("PASS", "Verify Password hint for 1 small letter", "Password hint for 1 small letter is  met for new password",true);
				}
			}
			else{
				Report("FAIL", "Verify Password hint for 1 small letter", "Issue in Password hint for 1 small letter, value"+status+" is displayed instead of "+status3,true);
			}



		}catch(Exception e){

		}
	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: TravelOptionsauthenticate()
	#Author: Ninad India
	#Description: This function will Click Add Travel options from Trip itinerary screen and authenticate the Booking 
	#Date of creation: 05-Apr-2016
	#Input Parameters: NA
	#Name of person modifying: Tester
	#Date of modification: 05-Apr-2016
	‘***************************************************/	

	public void travelOptionsAuthenticate(String Cardno,String expirymonth,String expiryYear){
		try{
			String cc = TD("Cardno").substring(TD("Cardno").length() - 4);
			SwipeWhileNotFound(OR.BFTR_Text_ManageBooking,SwipeDown,5,true);
			if(WaitForElement(OR.MB_Title_Header,500))
			{
				Click(OR.MB_BTN_TravelOptions);
				if(WaitForElement(OR.MB_Title_Header, 5000)){
					VerifyElementFound(OR.MB_Text_AuthenticationWarning);
					VerifyElementFound(OR.MB_TextBox_CreditCardLast4Digit);
					VerifyElementFound(OR.MB_TextBox_CreditCardMonth);
					VerifyElementFound(OR.MB_TextBox_CreditCardyear);
					VerifyElementFound(OR.MB_BTN_Continue);
					ElementSendText(OR.MB_TextBox_CreditCardLast4Digit,cc);
					ElementSendText(OR.MB_TextBox_CreditCardMonth,expirymonth);
					ElementSendText(OR.MB_TextBox_CreditCardyear,expiryYear);
					Click(OR.MB_BTN_Continue);


					if(WaitForElement(OR.TO_Text_Title, 5000)){

						Report("PASS", "Travel Options", "Navigated to Travel Options",true);

					}else{
						Report("FAIL", "Travel Options", "Not Navigated to Travel Options",true);
					}
				}
				else{
					Report("FAIL", "Authentication", "Not Not navigated to Authentication page",true);	
				}
			}else{
				Report("FAIL", "Manage Booking", "Not Navigated to manage Booking Page",true);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: addTravelOption()
	#Author: Ninad India
	#Description: This function will add Travel option to the flight after PNR is created. 
	#Date of creation: 05-Apr-2016
	#Input Parameters:User type-Guest/MOB+, TravelOption: Addon, departurefligth: Add addon in Departure flight, returnflight:Add addon in return flight, Validationflag: Click submit button or not
	#Name of person modifying: Tester
	#Date of modification: 05-Apr-2016
	‘***************************************************/	

	public void addTravelOption(boolean guestUser, String TravelOption,boolean departurefligth,boolean returnflight,int Validationflag)
	{
		try {

			String Meal,OnMyWay,MapleLeafLounge,Depature,Return,AddToFlight,Tarvel_Option,AddToTravelOption;
			Meal="Prepaid Air Canada";
			OnMyWay="On My Way";
			MapleLeafLounge="Maple Leaf Lounge";
			Depature="Add to departure";
			Return="Add to return";
			AddToFlight="Add to flight";



			if(TravelOption.equalsIgnoreCase(Meal))
			{Tarvel_Option=Meal;}				
			else if(TravelOption.equalsIgnoreCase(OnMyWay))
			{Tarvel_Option=OnMyWay;}
			else{Tarvel_Option=MapleLeafLounge;}
			if(departurefligth)
			{AddToTravelOption=Depature;}
			else if(returnflight)
			{AddToTravelOption=Return;}
			else{AddToTravelOption=AddToFlight;}



			String SelectTarvelOption[]=GetElementForDynamicText(OR.TO_BTN_DynamicSelection, AddToTravelOption+","+Tarvel_Option);

			if(SwipeWhileNotFound(SelectTarvelOption, SwipeDown, 5, true))
			{				
				Report("INFO", "Add Option", "Added for "+TravelOption+ "Option for "+AddToTravelOption,true);				
			}
			else
			{
				Report("FAIL", "Add Option", "Not Added for "+TravelOption+ "Option for "+AddToTravelOption,true);
			}

			if(Validationflag == 1){
				Sleep(3000);
				Click(OR.TO_BTN_confirm);


				if(WaitForElement(OR.SignIn_Text_signin,100))
				{
					if(guestUser==true)
					{
						SignIn("Yes","","");

					}else
					{				
						Type(OR.SignIn_Textbox_Password,TD("Pwd"));
						SwipeWhileNotFound(OR.SignIn_BTN_Signin,SwipeDown,5,true);
					}		
				}
				else
				{	
					VerifyElementFound(OR.TOS_Text_SummaryOfCharges);
					Report("PASS", "Travel Options summary ","Travel Options summary page is displayed",true);
				}
			}

			else
			{
				oGbl.Failureflag = 1;
				Report("FAIL", "Travel Options","Not navigated to Travel option page",true);
			}



		}catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: validateTravelOptionSummary()
	#Author: Ninad India
	#Description: This function will validate travel option summary screen 
	#Date of creation: 05-Apr-2016
	#Input Parameters: Travel Option 1-3 (3 Types of Travel option)
	#Name of person modifying: Tester
	#Date of modification: 05-Apr-2016
	‘**************************************************/
	public void validateTravelOptionSummary(String travelOptn1,String travelOptn2,String travelOptn3) throws Exception
	{

		String noOfPassenger=(String.valueOf(Integer.parseInt(TD("Adult_no"))+Integer.parseInt(TD("Child_no"))+Integer.parseInt(TD("Youth_no"))))+" passenger" ;



		if(oGbl.Failureflag == 0)
		{
			oGbl.ScreenName = "Travel Option Summary";

			VerifyElementFound(OR.TOS_Text_TripAirports);
			VerifyElementFound(OR.TOS_Text_TripDates);
			VerifyElementFound(OR.TOS_Text_SummaryOfCharges);
			VerifyElementFound(OR.TOS_Text_NoOfPassenger);

			if(travelOptn1 != ""){
				if(verifyTraveloptntext(travelOptn1)){
					Report("PASS", "Travel Options summary ",""+travelOptn1+ "is Selected",true);	
				}else{
					Report("FAIL", "Travel Options summary ",""+travelOptn1+ "is not Selected",true);
				}
			}

			if(travelOptn2 != ""){
				if(verifyTraveloptntext(travelOptn2)){
					Report("PASS", "Travel Options summary ",""+travelOptn2+ "is Selected",true);
				}else{
					Report("FAIL", "Travel Options summary ",""+travelOptn2+ "is not Selected",true);
				}
			}

			if(travelOptn3 != ""){
				if(verifyTraveloptntext(travelOptn3)){
					Report("PASS", "Travel Options summary ",""+travelOptn3+ "is Selected",true);
				}else{
					Report("FAIL", "Travel Options summary ",""+travelOptn3+ "is not Selected",true);
				}
			}				


			String passengerCount=ElementGetText(OR.TOS_Text_NoOfPassenger);

			if(noOfPassenger.equals(passengerCount))
			{
				Report("PASS","Verify Passenger Count","Valid number of Passengers are displayed", false);
			}
			else
			{
				Report("FAIL","Verify Passenger Count","Valid number of Passengers are not displayed", true);
			}
			VerifyElementFound(OR.TOS_Text_TravelOptionSelected);
			SwipeWhileNotFound(OR.TOS_BTN_Paynow,SwipeDown, 4, false);
			VerifyElementFound(OR.TOS_BTN_Paynow);
			Click(OR.TOS_BTN_Paynow);

		}else{
			Report("FAIL","Verify Travel Option screen","Travel option Summary screen is not displayed", true);
			oGbl.Failureflag = 1;
		}

	}	

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: validateTravelOptionConfirmation()
	#Author: Ninad India
	#Description: This function will validate Travel option confirmation screen 
	#Date of creation: 05-Apr-2016
	#Input Parameters: NA
	#Name of person modifying: Tester
	#Date of modification: 05-Apr-2016
	‘**************************************************/
	public void validateTravelOptionConfirmation() throws Exception
	{
		if(WaitForElement(OR.TOC_Text_TravelOptionsConfirmationTitle,120000))
		{
			Report("PASS", "Verify Travel Option confirmation", "Travel option confirmation is displayed",true);

			VerifyElementFound(OR.TOC_Text_TravelOptionsConfirmationText);
			VerifyElementFound(OR.TI_Text_ConformationEmail);
			String actEmailId= ElementGetText(OR.TI_Text_ConformationEmail);
			if(actEmailId.equalsIgnoreCase(TD("Passenger1_Email")) || actEmailId.equalsIgnoreCase(TD("Uname")))
			{
				Report("PASS", "Trip Itinery Screen", "Expected and actual email id [" + actEmailId + "] matched in Trip Itinery booking confirmation Screen",true);
			}else
			{
				Report("FAIL", "Trip Itinery Screen", "Expected email id [" + TD("Passenger1_Email") + "] and actual email id [" + actEmailId + "] didn't matched in Trip Itinery booking confirmation Screen",true);
			}
			SwipeWhileNotFound(OR.TOC_Link_SeatSelection, SwipeDown, 3, false);
			VerifyElementFound(OR.TOC_Link_SeatSelection);
			VerifyElementFound(OR.TOC_Link_ViewTrip);
			VerifyElementNotFound(OR.TI_Text_AddTravelOption);

		}else{
			Report("FAIL", "Verify Travel option confirmation", "Travel option confirmation is not displayed",true);
		}

	}
	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: AddTravelOptionCreditCardPayment()
	#Author: Ninad India
	#Description: This function will Add credit card details during Travel option selection 
	#Date of creation: 05-Apr-2016
	#Input Parameters: NA
	#Name of person modifying: Tester
	#Date of modification: 05-Apr-2016
	‘**************************************************/	
	public void AddTravelOptionCreditCardPayment(String Cardno,String month,String year,String Name,String country,String Province, String street,String city,String Postalcode) throws Exception{

		SelectPayment();
		paymentmethod("credit");
		oGbl.ScreenName = "Credit Card Payment";
		if(oGbl.Failureflag == 0){
			if(WaitForElement(OR.CCP_Label_CCDetails,20000)){
				Type(OR.CCP_Textbox_Ccnumber,Cardno);					
				Type(OR.CCP_Textbox_ExpirationMonth,month);
				Type(OR.CCP_Textbox_ExpirationYear,year);
				Type(OR.CCP_Textbox_Nameoncard,Name);
				SwipeWhileNotFound(OR.CCP_Textbox_Street,SwipeDown, 4, false);
				Click(OR.CCP_Select_Country);
				Type(OR.CCP_Dropdown_Country,country);
				Click(OR.CCP_Country_Suggestion_item);
				SwipeWhileNotFound(OR.CCP_Textbox_Street,SwipeDown, 4, false);
				if(WaitForElement(OR.CCP_Label_Province,10000)){
					Click(OR.CCP_Label_Province);					
					SwipeWhileNotFound(GetElementForDynamicText(OR.CCP_List_Province,Province),SwipeDown, 4, true);						
				}else{					
					Click(OR.CCP_Label_State);
					Type(OR.CCP_textbox_state,country);
					Click(OR.CCP_Country_Suggestion_item);
				}
				SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);
				Type(OR.CCP_Textbox_Street,street);
				SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);				
				Type(OR.CCP_Textbox_City,city);
				SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, false);
				Type(OR.CCP_Textbox_PostalCode,Postalcode);
				if(IsElementFound(OR.Passenger_Chkbx_enable)){
					Click(OR.Passenger_Chkbx_Save);
					VerifyElementFound(OR.Passenger_Chkbx_disable);
				}
				SwipeWhileNotFound(OR.CCP_BTN_Save,SwipeDown, 4, true);		
				SwipeWhileNotFound(OR.TOS_Text_TripAirports,SwipeUp, 4, false);
			}else{
				Report("FAIL","Credit card payment screen","Credit card payment screen is not displayed", true);
				oGbl.Failureflag = 1;
			}
		}
	}

	public void Signout() throws Exception {
		
		if(oGbl.Failureflag == 0){
			SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);
			if(WaitForElement(OR.PS_BTN_SavePassenger, 60000))
			{
				Click(OR.PS_BTN_Logout);
				Sleep(5000);
				if(WaitForElement(OR.LP_Text_signin, 60000)){
					Report("PASS","Log out","Screen logged out", true);
				}else{
					Report("FAIL","Log out","Screen not logged out", true);
				}
				
			}
		}
	}

	public void password_disable() throws Exception{
		
			VerifyElementFound(OR.PP_BTN_PEnabled);
			VerifyElementFound(OR.PO_Link_Change_Passcode);
			Click(OR.Preference_BTN_passcodesync);
			if(IsElementFound(OR.PP_Text_passcode)){
				Report("PASS", "Enter Passcode", "Enter Passcode Textbox is displayed",true);
				Type(OR.PP_Textbox_passcode,TD("Password"));
				Click(OR.PP_BTN_SaveButton);
				VerifyElementFound(OR.PP_BTN_Disabled);
			}else{
				Report("FAIL","Enter Passcode","Enter passcode Screen not displayed", true);
			}
		
	}

	public void ValidateSettings() throws Exception{
		VerifyElementFound(OR.CP_BTN_ChangePassword);
		VerifyElementFound(OR.PS_BTN_SavePassenger);
		VerifyElementFound(OR.PS_BTN_SavedCreditcard);
		VerifyElementFound(OR.PS_BTN_Altitudecard);
		VerifyElementFound(OR.PS_BTN_LinkWithAeroplane);
		VerifyElementFound(OR.PS_BTN_Preference);
		SwipeWhileNotFound(OR.PS_BTN_Notification,SwipeDown, 5, false);
		VerifyElementFound(OR.PS_BTN_Notification);
	}
	
	public void ValidateSavePassenger() throws Exception{
		SwipeWhileNotFound(OR.Passenger_BTN_Save,SwipeDown, 4, true);
		oGbl.SetRecoveryScenarioErrorCheck=false;
		VerifyElementFound(OR.Passenger_Error_Text);
		oGbl.SetRecoveryScenarioErrorCheck=true;
		VerifyElementFound(OR.Passenger_Error_Title);
		VerifyElementFound(OR.Passenger_Error_FName);
		VerifyElementFound(OR.Passenger_Error_LName);
		Click(OR.NP_TextBox_Title);	
		if(oGbl.Device_Under_Test.startsWith("ios_app")){
			System.out.println(TD("Salutation"));
			String sal1=TD("Salutation");
			String[]sal=GetElementForDynamicText(OR.NP_Text_Salutation,sal1);
			SwipeWhileNotFound(sal,SwipeDown,3, true);
		}
		else{
			Click(GetElementForDynamicText(OR.NP_Text_Salutation,TD("Salutation")));
		}
		SwipeWhileNotFound(OR.Passenger_BTN_Save,SwipeDown, 4, true);
		oGbl.SetRecoveryScenarioErrorCheck=false;
		VerifyElementFound(OR.Passenger_Error_Text);
		Swipe(SwipeUp,500,500,"Swipe up");
		VerifyElementNotFound(OR.Passenger_Error_Title);
		VerifyElementFound(OR.Passenger_Error_FName);
		VerifyElementFound(OR.Passenger_Error_LName);
		oGbl.SetRecoveryScenarioErrorCheck=true;
		Sleep(1000);
		Type(OR.NP_TextBox_Firstname,TD("Fname"));
		SwipeWhileNotFound(OR.Passenger_BTN_Save,SwipeDown, 4, true);
		oGbl.SetRecoveryScenarioErrorCheck=false;
		VerifyElementFound(OR.Passenger_Error_Text);
		Swipe(SwipeUp,500,500,"Swipe up");
		VerifyElementNotFound(OR.Passenger_Error_Title);
		VerifyElementNotFound(OR.Passenger_Error_FName);
		VerifyElementFound(OR.Passenger_Error_LName);
		oGbl.SetRecoveryScenarioErrorCheck=true;
		Sleep(1000);
		Type(OR.NP_TextBox_Lastname,TD("Lname"));
		Sleep(1000);
		SwipeWhileNotFound(OR.Passenger_BTN_Save,SwipeDown, 4, true);
		if(WaitForElement(OR.SP_Title_Header, 20000)||WaitForElement(OR.PS_Title_Header, 20000)){
			Report("PASS", "Passenger Info", "Passenger info is addded",true);
		}else{
			Report("FAIL", "Passenger Info", "Passenger info is not addded",true);
		}
		
	}

}




