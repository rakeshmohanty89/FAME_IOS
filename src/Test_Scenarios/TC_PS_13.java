package Test_Scenarios;

import Libraries.*;

public class TC_PS_13 extends Lib_SeeTest{
	
	
	public void Test_TC_PS_13(Lib_Global ogbl) throws Exception {
		try {
			SetGlobalObject(ogbl);
			oAppLib.Verify_AppOpened();

			Click(OR.LP_BTN_Menu);
			SwipeWhileNotFound(OR.MO_BTN_ProfileSetting,SwipeDown, 5, true);
			
			if(WaitForElement(OR.PS_Title_Header, 60000)){
				Report("PASS", "Profile and Setting Page", "Navigated to Profile and Settings Screen",true);
				//user profile
				oAppLib.SwipeWith2ElementRef(OR.PS_BTN_Profile, OR.PS_BTN_LinkWithAeroplane, "Down", 800, 5, true);
				oAppLib.CreateUser_Profile();
				oAppLib.profile_validation();
				
			}else
			{
				Report("FAIL", "Profile and Setting Page", "Not Navigated to Profile and Settings Screen",true);
			}

			
		} catch (Exception e) {
			Report("FAIL","Error Occured",e.toString(), false);
			oGbl.errFlag = 1;
			e.printStackTrace();
		} 
		 
	}


}
