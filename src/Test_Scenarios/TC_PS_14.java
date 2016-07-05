package Test_Scenarios;

import Libraries.*;

public class TC_PS_14 extends Lib_SeeTest{


	public void Test_TC_PS_14(Lib_Global ogbl) throws Exception {
		try {
			SetGlobalObject(ogbl);
			oAppLib.Verify_AppOpened();

			oAppLib.NavigateToSignInScreen();
			oAppLib.SignIn("no",TD("Uname"),TD("Pwd"));
			Sleep(2000);
			Click(OR.LP_BTN_Menu);
			SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);
			if(WaitForElement(OR.PS_Title_Header, 60000))
				{
					oAppLib.SwipeWith2ElementRef(OR.PS_BTN_Logout, OR.Blank_Object, "Down", 800, 8, true);
					if(WaitForElement(OR.CA_Title_header, 60000)){
						oAppLib.SwipeWith2ElementRef(OR.PI_Text_UserName, OR.PS_BTN_LinkWithAeroplane, "Down", 800, 8, true);
						CloseKeyboard();
						oAppLib.CreateUser_Profile();
						oAppLib.profile_validation();
					}else{
						Report("FAIL", "Profile and Settings", "Not navigated to Profile and Settings",true);
					}
				}
				else
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
