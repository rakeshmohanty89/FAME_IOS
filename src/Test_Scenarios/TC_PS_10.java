package Test_Scenarios;

import Libraries.*;

public class TC_PS_10 extends Lib_SeeTest{
	
	
	public void Test_TC_PS_10(Lib_Global ogbl) throws Exception {
		try {
			SetGlobalObject(ogbl);
			oGbl.SetRecoveryScenarioErrorCheck=false;
			oAppLib.Verify_AppOpened();

			oAppLib.NavigateToSignInScreen();
			oAppLib.SignIn("no",TD("Uname"),TD("Pwd"));
			Sleep(5000);
			Click(OR.LP_BTN_Menu);
			SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown,5, true);
			if(WaitForElement(OR.PS_Title_Header, 60000))
			{
				Report("PASS", "Profile and Setting Page", "Navigated to Profile and Settings page",true);

				String aeroplaneNo = "";
				if(IsElementFound(OR.LA_Text_AeroplanNo)||(IsElementFound(OR.LA_Label_AeroplanNo)))
				{
					aeroplaneNo = (ElementGetText(OR.LA_Text_AeroplanNo).trim()).replace(" ", "");
				}
				if(!aeroplaneNo.equalsIgnoreCase(TD("Aeroplanno")))
				{
					if(IsElementFound(OR.LA_Text_AeroplanNo)||(IsElementFound(OR.LA_Label_AeroplanNo)))
					{
						Report("INFO", "Aeroplan Linked", "Already Aeroplan number is linked",true);
						oAppLib.aeroplan_unlink();
					}
					if(WaitForElement(OR.PS_BTN_LinkWithAeroplane, 60000))
					{
						oAppLib.aeroplan_link();
						if(WaitForElement(OR.LA_BTN_UseAreoplan, 5000))
						{
							oAppLib.Link_name(TD("option"),"no");
						}
						WaitForElement(OR.LA_Label_AeroplanNO, 300000);

					}
				}

				SwipeWith2ElementRef(OR.PS_BTN_Logout, OR.Blank_Object, "Down", 800, 8, true);
				WaitForElement(OR.LP_Text_signin,60000);
				Click(OR.LP_Text_signin);
			}


			oAppLib.SignIn("no",TD("Uname2"),TD("Pwd2"));
			if(!(oGbl.Device_Under_Test.startsWith("ios_app")))
			{
				WaitForElement_UntilDissapear(OR.LP_Text_signin, 1);
			}
			if(WaitForElement(OR.PS_Title_Header, 60000)){
				Report("PASS", "Profile and Setting Page", "Navigated to Profile and Settings page",true);
				if(IsElementFound(OR.LA_Text_AeroplanNo)||(IsElementFound(OR.LA_Label_AeroplanNo))){
					Report("INFO", "Aeroplan Linked", "Already Aeroplan number is linked",true);
					oAppLib.aeroplan_unlink();
				}
				if(WaitForElement(OR.PS_BTN_LinkWithAeroplane, 60000)){
					oAppLib.aeroplan_link();
					Sleep(6000);
					if(WaitForElement(OR.LA_BTN_UseAreoplan, 60000)){
						if((TD("option")).toLowerCase().contains("aeroplan")){
							Click(OR.LA_BTN_UseAreoplan);
						}
						else{
							Click(OR.LA_BTN_Mobile_name);
							Sleep(2000);
							if(IsElementFound(OR.LA_BTN_Continuename))
							{
								Click(OR.LA_BTN_Continuename);
							}
						}
					}
					if(WaitForElement(OR.LA_Text_error_message, 300000))
					{
						if(oGbl.Device_Under_Test.startsWith("ios_app"))
						{
							Click(OR.AC_BTN_OKbtn);
						}else{
							Click(OR.TI_BTN_Close);
						}
	
						Report("PASS", "Link Aeroplan", "Aeroplan has been linked with the other profile message is displayed",true);
						//new code to unlink the unlink the Aeroplane no from previous account
						int i =0;
						while(IsElementFound(OR.PS_Title_Header) == false)
						{
							Click(OR.BackButton);
							Sleep(1000);
						}
						SwipeWith2ElementRef(OR.PS_BTN_Logout, OR.Blank_Object, "Down", 800, 8, true);
						WaitForElement(OR.LP_Text_signin,60000);
						Click(OR.LP_Text_signin);
						//oAppLib.NavigateToSignInScreen();
						oAppLib.SignIn("no",TD("Uname"),TD("Pwd"));
	Sleep(1000);	
	if(!(oGbl.Device_Under_Test.startsWith("ios_app")))
	{					
		WaitForElement_UntilDissapear(OR.LP_Text_signin, 1);
	}
//						Click(OR.LP_BTN_Menu);
//						SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);
						if(WaitForElement(OR.PS_Title_Header, 60000))
						{
							oAppLib.aeroplan_unlink();
						}else{
							Report("FAIL", "Profile and Setting Page", "Not navigated to Profile and Settings page",true);
						}
					}else
					{
						Report("FAIL", "Link Aeroplan", "Aeroplan has been linked with the other profile message is not displayed",true);
					}

				}else{
					Report("FAIL", "Link Aeroplan", "Link Aeroplan option is not found",true);
				}

			}	
			else{
				Report("FAIL", "Profile and Setting Page", "Not navigated to Profile and Settings page",true);
			}

		} catch (Exception e) {
			Report("FAIL","Error Occured",e.toString(), false);
			oGbl.errFlag = 1;
			e.printStackTrace();
		} 

	}


}
