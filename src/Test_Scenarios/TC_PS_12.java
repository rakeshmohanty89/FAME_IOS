package Test_Scenarios;

import Libraries.*;

public class TC_PS_12 extends Lib_SeeTest{
	String[]errormessage;
	String mobiename,username;
	public void Test_TC_PS_12(Lib_Global ogbl) throws Exception {
		try {
			SetGlobalObject(ogbl);
			oAppLib.Verify_AppOpened();
			
			oAppLib.NavigateToSignInScreen();
			Click(OR.SignIn_Link_ForgotPassword);
			WaitForElement(OR.SignIn_Textbox_Email, 60000); 
			if(oGbl.Failureflag == 0)
			{
				ElementSendText(OR.SignIn_Textbox_Email,TD("Uname"));
				CloseKeyboard();
				Click(OR.SignIn_BTN_RequestPassword);
				//Sleep(5000);
				oGbl.SetRecoveryScenarioErrorCheck=false;
				if(oGbl.Device_Under_Test.startsWith("ios_app"))
				{
					errormessage=  GetElementForDynamicText(OR.BAT_TextBox_Origin, "If you have an Air Canada mobile+ account");
				}			
				if(WaitForElement(OR.SignIn_Text_InvalidEmaiId, 60000)||(WaitForElement(errormessage, 60000)))
				{
					oGbl.SetRecoveryScenarioErrorCheck=true;
					Report("PASS", "Sign In Screen", "User is able to validate invalid email id entered for resetting the password.",true);
				}else
				{
					oGbl.SetRecoveryScenarioErrorCheck=true;
					Report("FAIL", "Sign In Screen", "User is not able to validate invalid email id entered for resetting the password.",true);
					oGbl.Failureflag=1;
				}
			}else
			{
				Report("FAIL", "Forget Password Screen", "User not able to navigate to Forget Password Screen.",true);
				oGbl.Failureflag=1;
			}



			
		} catch (Exception e) {
			Report("FAIL","Error Occured",e.toString(), false);
			oGbl.errFlag = 1;
			e.printStackTrace();
		} 

	}


}
