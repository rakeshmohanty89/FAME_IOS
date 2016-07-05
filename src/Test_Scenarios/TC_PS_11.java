package Test_Scenarios;

import Libraries.*;

public class TC_PS_11 extends Lib_SeeTest{

	String aeroplanname="",mobiename="",username="";
	public void Test_TC_PS_11(Lib_Global ogbl) throws Exception {
		try {
			SetGlobalObject(ogbl);
			oAppLib.Verify_AppOpened();

			oAppLib.NavigateToSignInScreen();
			oAppLib.SignIn("no",TD("Uname"),TD("Pwd"));
			Sleep(5000);
			Click(OR.LP_BTN_Menu);
			SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);

			if(WaitForElement(OR.PS_Text_Username, 60000))
			{
				Click(OR.PS_Text_Username);
			}else
			{
				Report("FAIL", "Profile & Settings", "Not navigated to Mobile+ account screen",true);
			}
			if(WaitForElement(OR.NP_TextBox_Firstname, 60000))
			{
				Type(OR.NP_TextBox_Firstname,TD("Fname"));
				oAppLib.SwipeWith2ElementRef(OR.NP_BTN_save, OR.Blank_Object, "Down", 800, 12, true);
				if(!WaitForElement(OR.PS_Text_Username, 60000))
				{
					Report("FAIL", "Profile & Settings", "Not navigated to Mobile+ account screen",true);
				}
			}else
			{
				Report("FAIL", "Profile & Settings", "Not navigated to Mobile+ account screen",true);
			}

			if(WaitForElement(OR.PS_Title_Header, 60000))
			{


				Report("PASS", "Profile and Setting Page", "Navigated to Profile and Settings page",true);
				if(IsElementFound(OR.PS_BTN_LinkedWithAeroplane)||(IsElementFound(OR.LA_Label_AeroplanNo))){
					Report("INFO", "Aeroplan Linked", "Already Aeroplan number is linked",true);
					oAppLib.aeroplan_unlink();
				}

				if(WaitForElement(OR.PS_BTN_LinkWithAeroplane, 60000)){
					oAppLib.aeroplan_link();
					if(WaitForElement(OR.LA_BTN_UseAreoplan, 60000)){
						aeroplanname=GetTextIn(OR.LA_Text_Namevariation, "Down", 0, 0);
						//mobiename=GetTextIn(OR.LA_Text_Profile_name, "Down", 0, 0);
						oAppLib.Link_name(TD("option"),"yes");
					}
					else{
						if(WaitForElement(OR.LA_Text_AeroplanNo, 60000)){
							Report("PASS", "Aeroplan link", "Aeroplan number linked successfully",true);
							Sleep(2000);

						}else{
							Report("FAIL", "Aeroplan link", "Aeroplan number not linked successfully",true);
						}
					}
//					Click(OR.BackButton);
//					Sleep(1000);
//					Click(OR.LP_BTN_Menu);
//					SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);
					if(WaitForElement(OR.PS_Title_Header, 60000))
					{
						Report("PASS", "Profile and Setting Page", "Navigated to Profile and Settings page",true);
						username=GetTextIn(OR.PS_Text_Username, "Inside", 0, 0).replaceAll("[\\t\\n\\r]"," ").trim();
						String userNameArr[] = username.split(" ");
						int matchCounter = 0;
						for(int i = 0; i<userNameArr.length; i++)
						{
							if((aeroplanname).contains(userNameArr[i]))
							{
								matchCounter = matchCounter +1;
							}
						}


						if(matchCounter >= 2)
						{
							Report("PASS", "Aeroplan name", "Profile remains with the Aeroplan name",true);
						}
						else{
							Report("FAIL", "Aeroplan name", "Profile doesnt remains with the Aeroplan name",true);
						}
						
						oAppLib.aeroplan_unlink();
					}else{
						Report("FAIL", "Profile & Settings", "Not navigated to Mobile+ account screen",true);

					}

					if(oGbl.Device_Under_Test.startsWith("ios_app"))
					{
						Click(OR.LP_BTN_Menu);
						oAppLib.Signout();
						Sleep(5000);
						Click(OR.LP_Text_signin);
					}else{
						Click(OR.PS_Text_Username);
					}
					if(IsElementFound(OR.SignIn_Textbox_Email))
					{
						oAppLib.SignIn("no",TD("Uname"),TD("Pwd"));
						if(WaitForElement(OR.PS_Text_Username, 60000))
						{
							Click(OR.PS_Text_Username);
						}else
						{
							Report("FAIL", "Profile & Settings", "Not navigated to Mobile+ account screen",true);
						}
					}
					
					if(WaitForElement(OR.NP_TextBox_Firstname, 60000))
					{
						Type(OR.NP_TextBox_Firstname,TD("Fname"));
						oAppLib.SwipeWith2ElementRef(OR.NP_BTN_save, OR.Blank_Object, "Down", 800, 12, true);
						if(!WaitForElement(OR.PS_Text_Username, 60000))
						{
							Report("FAIL", "Profile & Settings", "Not navigated to Mobile+ account screen",true);
						}
					}else
					{
						Report("FAIL", "Profile & Settings", "Not navigated to Mobile+ account screen",true);
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
