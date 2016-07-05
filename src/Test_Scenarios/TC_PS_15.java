package Test_Scenarios;

import Libraries.*;

public class TC_PS_15 extends Lib_SeeTest{


	public void Test_TC_PS_15(Lib_Global ogbl) throws Exception {
		try {
			SetGlobalObject(ogbl);
			oGbl.SetRecoveryScenarioErrorCheck=false;
			oAppLib.Verify_AppOpened();

			Click(OR.LP_BTN_Menu);
			SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);
			
			if(WaitForElement(OR.PS_Title_Header, 60000))
			{
				oAppLib.SwipeWith2ElementRef(OR.PS_BTN_Preference, OR.PS_BTN_Notification, "Down", 800, 8, true);
				if(WaitForElement(OR.Preference_Title_Header, 60000))
				{
					Click(OR.Preference_BTN_AppPasscode);
					if(WaitForElement(OR.PP_Title_Header, 20000))
					{
						VerifyElementFound(OR.Preference_BTN_AppPasscode);
						VerifyElementFound(OR.PP_Text_NotEnabled);
						if(oGbl.Device_Under_Test.startsWith("ios_app"))
						{
								Click(OR.Preference_BTN_passcodesync);
						}else{

							Click(OR.Preference_BTN_CalendarSyncThumb);
					}
						if(WaitForElement(OR.PO_Link_UsePIN, 20000))
						{
							oAppLib.pin_enable();
							if(WaitForElement(OR.PO_Text_PinEnabled, 20000))
							{
								Click(OR.BackButton);
								if(WaitForElement(OR.Preference_Title_Header, 20000))
								{
									Click(OR.Preference_BTN_Label);
									if(WaitForElement(OR.Preference_Text_EditLabel, 20000)||(WaitForElement(OR.Preference_Title_EditLabel, 20000)))
									{
										oAppLib.SwipeWith2ElementRef(OR.Passenger_BTN_Save, OR.Blank_Object, "Down", 800, 8, true);
										if(WaitForElement(OR.Preference_Title_Header, 20000))
										{
											VerifyElementFound(OR.Preference_BTN_CalendarSyncThumb);
											VerifyElementFound(OR.Preference_Label_CalendarSync);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Label_OfflineFlightSchedule, OR.Blank_Object, "Down", 800, 8, false);
											oAppLib.SwipeWith2ElementRef(OR.Preference_BTN_DownloadButton, OR.Blank_Object, "Down", 800, 8, false);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Label_AutomaticUpdate, OR.Preference_BTN_AutomaticUpdateoFF, "Down", 800, 8, false);
											oAppLib.SwipeWith2ElementRef(OR.Preference_BTN_AutomaticUpdateoFF, OR.Preference_Text_Units, "Down", 800, 8, false);
											VerifyElementFound(OR.Preference_BTN_AutomaticUpdateWifi);
											Click(OR.Preference_BTN_AutomaticUpdateoFF);
											VerifyElementFound(OR.Preference_BTN_AutomaticUpdateOffSelected);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Text_Units, OR.Preference_Text_Temp, "Down", 800, 8, false);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Text_Temp, OR.Preference_Text_Celcius, "Down", 800, 8, false);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Text_Celcius, OR.Preference_Text_Weight, "Down", 800, 8, false);
											Click(OR.Preference_Text_Fahren);
											VerifyElementFound(OR.Preference_Text_FahrenSelected);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Text_Distance, OR.Preference_Text_Km, "Down", 800, 8, false);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Text_Km, OR.Preference_Text_Weight, "Down", 800, 8, false);
											Click(OR.Preference_Text_Miles);
											VerifyElementFound(OR.Preference_Text_MilesSeletced);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Text_Weight, OR.Preference_Text_Kg, "Down", 800, 8, false);
											oAppLib.SwipeWith2ElementRef(OR.Preference_Text_Kg, OR.Blank_Object, "Down", 800, 8, false);
											Click(OR.Preference_Text_Pound);
											VerifyElementFound(OR.Preference_Text_PoundSelected);
											Click(OR.BackButton);

											oGbl.Client.applicationClose(TD("ApplicationPackage"));
											Sleep(15000);
											LaunchApp(TD("ApplicationPackage"), true, false);
											
											if(WaitForElement(OR.ChangeP_Label_enter_passcode, 20000))
											{
												String pin=TD("PIN");
												for(int eachCharNo= 0; eachCharNo< pin.length(); eachCharNo++)
												{
													String eachChar = "" + pin.charAt(eachCharNo);
													SendText(eachChar);
												}
												Sleep(4000);
												if(IsElementFound(OR.LP_Text_UpgradeCRT)){
													Click(OR.LP_BTN_UpgradeCRT);
												}
												oAppLib.NavigateToSignInScreen();
												oAppLib.SignIn("No", TD("Uname"), TD("Pwd"));
												if(WaitForElement(OR.LP_BTN_Book_flight, 80000))
												{
													Click(OR.LP_BTN_Menu);
													SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);
													if(WaitForElement(OR.PS_Title_Header, 60000))
													{
														oAppLib.SwipeWith2ElementRef(OR.PS_BTN_Preference, OR.PS_BTN_Notification, "Down", 800, 8, true);
														Click(OR.Preference_BTN_AppPasscode);
														if(WaitForElement(OR.PP_Title_Header, 20000))
														{
															VerifyElementFound(OR.PO_Text_PinEnabled);
															Click(OR.BackButton);
															oAppLib.SwipeWith2ElementRef(OR.Preference_BTN_AutomaticUpdateOffSelected, OR.Preference_Text_Units, "Down", 800, 8, false);
															oAppLib.SwipeWith2ElementRef(OR.Preference_Text_FahrenSelected, OR.Preference_Text_Weight, "Down", 800, 8, false);
															oAppLib.SwipeWith2ElementRef(OR.Preference_Text_MilesSeletced, OR.Preference_Text_Weight, "Down", 800, 8, false);
															oAppLib.SwipeWith2ElementRef(OR.Preference_Text_PoundSelected, OR.Blank_Object, "Down", 800, 8, false);
															SwipeWhileNotFound(OR.Preference_BTN_AppPasscode,SwipeUp, 5, false);
															oAppLib.apppasscode("disable",TD("PIN"));
															
														}else
														{
															Report("FAIL", "Set Passcode Screen", "Not navigated to Set passcode Screen",true);
														}
														
													}else
													{
														Report("FAIL", "Profile and Settings", "Not navigated to Profile and Settings",true);
													}
													
												}else
												{
													Report("FAIL", "Logged In As Screen", "Not navigated to Logged In As Screen",true);
												}
											}else
											{
												Report("FAIL", "Enter Passcode Screen", "Not navigated to Pin Enter Screen",true);
											}



										}else
										{
											Report("FAIL", "Preferences Screen", "Not navigated to Preferences Screen",true);
										}
									}else
									{
										Report("FAIL", "Edit labels Screen", "Not navigated to Edit labels Screen",true);
									}
								}else
								{
									Report("FAIL", "Preferences Screen", "Not navigated to Preferences Screen",true);
								}
							}else
							{
								Report("FAIL", "Set Passcode Screen", "Not able to Set passcode",true);
							}

						}else
						{
							Report("FAIL", "Set Passcode Screen", "Not navigated to Set passcode Screen",true);
						}
					}else
					{
						Report("FAIL", "Passcode Screen", "Not navigated to passcode Screen",true);
					}

				}else
				{
					Report("FAIL", "Preferences Screen", "Not navigated to Preferences Screen",true);
				}

			}else
			{
				Report("FAIL", "Profile and Settings", "Not navigated to Profile and Settings",true);
			}


		} catch (Exception e) {
			Report("FAIL","Error Occured",e.toString(), false);
			oGbl.errFlag = 1;
			e.printStackTrace();
		} 

	}


}
