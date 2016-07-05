package Test_Scenarios;

import java.text.SimpleDateFormat;
import java.util.Date;

import Libraries.*;


public class TC_End_End_01 extends Lib_SeeTest{
	public void Test_TC_End_End_01(Lib_Global ogbl) throws Exception 
	{
		try {
			SetGlobalObject(ogbl);
			int passenger_count,Earlier_passenger_count;
			oAppLib.Verify_AppOpened();		
			oAppLib.NavigateToSignInScreen();
			oAppLib.SignIn("no",TD("Uname"),TD("Pwd"));
			Sleep(5000);
			Click(OR.LP_BTN_Menu);
			SwipeWhileNotFound(OR.MO_BTN_Home,SwipeDown, 5, true);
			VerifyElementFound(OR.LP_BTN_Menu);
			VerifyElementFound(OR.LP_BTN_Book_flight);
			VerifyElementFound(OR.LP_BTN_FlightStatus);
			VerifyElementFound(OR.LP_BTN_Checkin);
			VerifyElementFound(OR.LP_Text_Tile);
			Click(OR.LP_BTN_Menu);
			SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);
			if(WaitForElement(OR.PS_Title_Header, 60000)){
				oAppLib.ValidateSettings();
				oAppLib.DeleteSavedPassenger();
				if(oGbl.Device_Under_Test.startsWith("ios_app"))
				{
					Click(OR.BackButton);
					Click(OR.PS_BTN_SavePassenger);
				}else{
					Click(OR.PS_BTN_SavePassenger);
				}
				//Add a new passenger
				Earlier_passenger_count=GetElementCount(OR.SP_Text_SavePassenger);
				if(WaitForElement(OR.SP_Title_Header, 60000)){
					Click(OR.SP_BTN_AddnewPassenger);
					if(WaitForElement(OR.NP_Title_Header, 60000)){
						oAppLib.ValidateSavePassenger();
						Click(OR.SP_BTN_AddnewPassenger);
						oAppLib.SavepassengerDetails(TD("Salutation"),TD("Fname"),TD("Mname"),TD("Lname"),TD("AreaCode"),TD("Mnumber"),TD("Email"),TD("Gender"),TD("Date"),TD("Month"),TD("Year"), TD("Redress"),TD("Travelno"),TD("Meal"),TD("SeatPref"),TD("Freqfly"),TD("Residence"),TD("Nationality"),TD("Passportno"),TD("Passportno_Date"),TD("Passportno_Month"),TD("Passportno_Year"),TD("Passportno_country"),TD("Nexusnumber"),TD("Nexusnumber_Date"),TD("Nexusnumber_Month"),TD("Nexusnumber_Year"),TD("Checkin"));
						passenger_count=GetElementCount(OR.SP_Text_SavePassenger);
						if(passenger_count>Earlier_passenger_count){
							Report("PASS", "New Passenger", "New Passenger is  added",true);
							if(oGbl.Device_Under_Test.startsWith("ios_app"))
							{							
								String[]sal=GetElementForDynamicText(OR.AC_Text_Dynamic,TD("Salutation"));
								if(IsElementFound(sal)){
									String[]lname=GetElementForDynamicText(OR.AC_Text_Dynamic,TD("Lname"));
									if(IsElementFound(lname)){
										Report("PASS", "New Passenger", "New Passenger details are added",true);
									
									}else{
										Report("FAIL", "New Passenger", "New Passenger is not added",true);
									}
								}else{
									Report("FAIL", "Salutation Name", "Passenger details are not added",true);
								}
								
							}else{
								String Userdetails=GetTextIn_GivenText("NATIVE","xpath=//*[@id='saved_passenger']",passenger_count-1,"NATIVE","Inside",0,0);
								if(Userdetails.contains((TD("Salutation")))){
									if(Userdetails.contains(TD("Fname"))){
										if(Userdetails.contains(TD("Lname")) && Userdetails.contains(TD("Mname")) && Userdetails.contains(TD("AreaCode")) && Userdetails.contains(TD("Mnumber"))){
											Report("PASS", "New Passenger", "New Passenger details are added",true);
										}else{
											Report("FAIL", "Last Name", "Passenger details are not added",true);
										}
									}else{
										Report("FAIL", "First Name Name", "Passenger details are not added",true);
									}
								}else{
									Report("FAIL", "Salutation Name", "Passenger details are not added",true);
								}
							}
							//delete the new passenger which got added in above scriots
								Click(OR.Home_Btn);
							}else{
								Report("FAIL", "New Passenger", "New Passenger is not added",true);
							}
					}else{
						Report("FAIL", "New Passenger", "Not navigated to New Passenger page",true);
					}
				}else{
					Report("FAIL", "Saved passenger screen", "Not navigated to saved passenger screen",true);
				}
				
			}else
			{
				Report("FAIL","Profile and Settings","Not Navigated to Profile and Settings page", true);
			}

		} catch (Exception e) {
			Report("FAIL","Error Occured",e.toString(), false);
			oGbl.errFlag = 1;
			e.printStackTrace();
		} 

	}


}
