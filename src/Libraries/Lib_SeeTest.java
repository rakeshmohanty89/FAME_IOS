package Libraries;

import Resources.ObjectRepository.OR;

import com.experitest.client.Client;

public class Lib_SeeTest {

	public Lib_App oAppLib;
	public Lib_Global oGbl;		
	public OR OR;
	public String Error_Description;

	public String SwipeUp="Up";
	public String SwipeDown="Down";
	public String SwipeRight="Right";
	public String SwipeLeft="Left";

	protected boolean DynamicText1;
	protected String Object_Description1;
	protected String Zone1;
	String Element1="";
	protected String Zone2;
	protected String Element2="";
	String TestData1;
	String Object_Description2,TestData2;

	protected int Index1;
	protected int Index2;

	public void SetGlobalObj(Lib_Global oGlobal) {
		oGbl = oGlobal;	
		OR=oGbl.OR;
	}

	public void SetGlobalObject(Lib_Global oGlobal)
	{
		SetGlobalObj(oGlobal);
		oAppLib=new Lib_App(oGlobal);

	}

	public String TD(String tdKey)
	{
		return oGbl.TD.get(tdKey);
	}

	public Client CreateSeeTestClient(int Port,String projectBaseDirectory)
	{
		Client client=null;
		try {
			client = new Client("localhost",Port,true);			
			client.setProjectBaseDirectory(projectBaseDirectory);
			//client.setReporter("xml", "reports");	
			client.setWebAutoScroll(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}


	public void SetLocatorsValues1(String[] Key) throws Exception{

		if(oGbl.RecoveryScenarioErrorMsg.isEmpty()){
			Object_Description1=Key[0];       
			Zone1=Key[1]; 
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				if(Key.length>=5 && !Key[4].equalsIgnoreCase(""))
				{
					Element1=Key[4];   
				}else
				{
					Element1=Key[2];
				}
			}else
			{
				Element1=Key[2];
			}   

			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				if(Key.length>=6 && !Key[5].equalsIgnoreCase(""))
				{
					Index1=Integer.parseInt(Key[5]);
				}else
				{
					if(Key.length>=4 && !Key[3].equalsIgnoreCase(""))
					{
						Index1=Integer.parseInt(Key[3]);
					}
				}
			}else
			{
				if(Key.length>=4 && !Key[3].equalsIgnoreCase(""))
				{
					Index1=Integer.parseInt(Key[3]);
				}
			}
			//			if(Key.length>=5 && !Key[4].equalsIgnoreCase(""))
			//			{
			//				TestData1=TD(Key[4]);
			//			}
		}else{Throw_Exception(oGbl.RecoveryScenarioErrorMsg);}
		if(oGbl.SetRecoveryScenarioErrorCheck==true)
		{
			MakeObjectVisble(Zone1,Element1,Index1);
		}
	}

	public String GetLocalValue(String Value) throws Exception{
		String LocalVal1=Value;

		if(oGbl.ghtLocalValues.containsKey(Value)){
			LocalVal1=oGbl.ghtLocalValues.get(Value);
		}

		return 	LocalVal1;
	}

	public void SetLocatorsValues2(String[] Key) throws Exception{

		Object_Description2=Key[0];       
		Zone2=Key[1]; 
		if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
			if(Key.length>=5 && !Key[4].equalsIgnoreCase(""))
			{
				Element2=Key[4];   
			}else
			{
				Element2=Key[2];
			}
		}else
		{
			Element2=Key[2];
		}   
		if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
			if(Key.length>=6 && !Key[5].equalsIgnoreCase(""))
			{
				Index2=Integer.parseInt(Key[5]);
			}else
			{
				if(Key.length>=4 && !Key[3].equalsIgnoreCase(""))
				{
					Index2=Integer.parseInt(Key[3]);
				}
			}
		}else
		{
			if(Key.length>=4 && !Key[3].equalsIgnoreCase(""))
			{
				Index2=Integer.parseInt(Key[3]);
			}
		}
		//		if(Key.length>=5 && !Key[4].equalsIgnoreCase(""))
		//		{
		//			TestData2=TD(Key[4]);
		//		}	
		if(oGbl.SetRecoveryScenarioErrorCheck==true)
		{
			MakeObjectVisble(Zone2,Element2,Index2);
		}
	}

	public String[] GetElementForDynamicText(String[] Key,String DynamicTexts) throws Exception{

		String[] Temp=Key.clone();

		String TempElement = "";
		String TempElement1 = "";

		int ElementKeyIndex = 2;

		if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
			if(Key.length>=5 && !Key[4].equalsIgnoreCase(""))
			{
				ElementKeyIndex = 4;   
			}else
			{
				ElementKeyIndex = 2;
			}
		}else
		{
			ElementKeyIndex = 2;
		}


		String TempText[]=DynamicTexts.split(",");	
		String Text[]=new String[TempText.length];

		for(int i=0;i<TempText.length;i++){
			if(oGbl.ghtLocalValues.containsKey(TempText[i])){
				Text[i]=oGbl.ghtLocalValues.get(TempText[i]);
			}else{Text[i]=TempText[i];}			
		}

		String data[]=Key[ElementKeyIndex].split("\\?\\?\\?\\?");
		if(data.length>1)
		{
			for(int i=0;i<Text.length;i++){
				TempElement=TempElement+data[i]+Text[i];
				TempElement1=TempElement1+Text[i];
				if(!(i==Text.length-1)){TempElement1=TempElement1+" and ";}			
			}

			TempElement=TempElement+data[data.length-1];		
			Temp[ElementKeyIndex]=TempElement;
		}

		Report("INFO", "Set Dynamic Text" , "Dynamic Text set["+TempElement1+"]", false);

		return Temp;

	}


	public String[] GetElementForDynamicIndex(String[] Key,int INDEX){
		String[] Temp=Key.clone();	


		int IndexKeyIndex = 3;

		if(oGbl.Device_Under_Test.startsWith("ios_app"))
		{
			if(Key.length>=6 && !Key[5].equalsIgnoreCase(""))
			{
				IndexKeyIndex = 5;
			}else
			{
				if(Key.length>=4 && !Key[3].equalsIgnoreCase(""))
				{
					IndexKeyIndex = 3;
				}
			}
		}else
		{
			if(Key.length>=4 && !Key[3].equalsIgnoreCase(""))
			{
				IndexKeyIndex = 3;
			}
		}


		Temp[IndexKeyIndex]=String.valueOf(INDEX);
		return Temp;
	}	

	public void MakeObjectVisble(String ZONE, String ELEMENT, int INDEX) throws Exception{

		int Y;

		if(oGbl.Client.isElementFound(Zone1, Element1, Index1)){
			//Y = Integer.parseInt(oGbl.Client.elementGetProperty(ZONE, ELEMENT, INDEX, "y"));					
			//if(Y<oGbl.gSwipe25pY){Swipe(SwipeUp,oGbl.gSwipe75pY,500,"");}		

			Y = Integer.parseInt(oGbl.Client.elementGetProperty(ZONE, ELEMENT, INDEX, "y"));
			if(Y>oGbl.gSwipe75pY){Swipe(SwipeDown,oGbl.gSwipe75pY,500,"");}	
		}
	}

	public void SendText(String Text)throws Exception{

		try {
			//Text=TD(Text);
			oGbl.Client.sendText(Text);

			Report("INFO", "Enter the text " + Text , Text + " entered successfully", false);
		} catch (Exception e) {
			Report("FAIL",  "Enter the text " + Text , Text + " not entered", true);
			ErrorCheck();
			throw e;
		}
	}

	public void Type(String[] key) throws Exception{
		try {

			SetLocatorsValues1(key);
			oGbl.Client.click(Zone1, Element1, Index1, 1);
			oGbl.Client.elementSendText(Zone1, Element1, Index1,TestData1);
			oGbl.Client.closeKeyboard();
			oGbl.Client.sleep(1000);

			Report("INFO", "Enter "+TestData1+" in " + Object_Description1, "Entered "+TestData1+" into "+Object_Description1 +" successfully",  false);

		} catch (Exception e) {

			Report("FAIL", "Enter "+TestData1+" in " + Object_Description1,"Not Entered "+TestData1+" into "+Object_Description1, true);
			ErrorCheck();
			throw e;
		}
	}

	public void Type(String[] key,String Val) throws Exception{
		try {

			SetLocatorsValues1(key);
			oGbl.Client.click(Zone1, Element1, Index1, 1);
			oGbl.Client.elementSendText(Zone1, Element1, Index1,Val);
			oGbl.Client.closeKeyboard();

			//oGbl.Client.sleep(100);

			Report("INFO", "Enter "+Val+" in " + Object_Description1, "Entered "+Val+" into "+Object_Description1 +" successfully",  true);

		} catch (Exception e) {

			Report("FAIL", "Enter "+Val+" in " + Object_Description1,"Not Entered "+Val+" into "+Object_Description1, true);
			ErrorCheck();
			throw e;
		}
	}

	public void ElementSendText(String[] key,String Val) throws Exception{
		try {

			SetLocatorsValues1(key);			
			oGbl.Client.elementSendText(Zone1, Element1, Index1,Val);
			oGbl.Client.closeKeyboard();

			//oGbl.Client.sleep(100);

			Report("INFO", "Enter "+Val+" in " + Object_Description1, "Entered "+Val+" into "+Object_Description1 +" successfully",  true);

		} catch (Exception e) {

			Report("FAIL", "Enter "+Val+" in " + Object_Description1,"Not Entered "+Val+" into "+Object_Description1, true);
			ErrorCheck();
			throw e;
		}
	}


	public void Click_Type(String[] key,String Val) throws Exception{
		try {

			SetLocatorsValues1(key);
			oGbl.Client.click(Zone1, Element1, Index1, 1);
			oGbl.Client.sleep(1000);					
			oGbl.Client.sendText(Val);
			oGbl.Client.closeKeyboard();
			oGbl.Client.sleep(1000);

			Report("INFO", "Enter "+Val+" in " + Object_Description1, "Entered "+Val+" into "+Object_Description1 +" successfully",  true);

		} catch (Exception e) {

			Report("FAIL", "Enter "+Val+" in " + Object_Description1,"Not Entered "+Val+" into "+Object_Description1, true);
			ErrorCheck();
			throw e;
		}
	}

	public void CloseKeyboard() throws Exception{
		try {
			oGbl.Client.closeKeyboard();
		} catch (Exception e) {

			ErrorCheck();
			throw e;

		}
	}

	public void Click(String[] key) throws Exception{
		try {
			SetLocatorsValues1(key);
			oGbl.Client.click(Zone1, Element1, Index1, 1);

			Report("INFO", "Click on the "+Object_Description1,"Clicked On "+Object_Description1, true);
		} catch (Exception e) {

			Report("FAIL", "Click on the "+Object_Description1,"Not Clicked On "+Object_Description1, true);

			ErrorCheck();
			throw e;

		}
	}	

	public void Click_TEXT(String text,int Index1) throws Exception{
		try {			 
			oGbl.Client.click("TEXT",text, Index1, 1);

			Report("INFO", "Click on the Element with TEXT :"+text,"Clicked", true);
		} catch (Exception e) {

			Report("FAIL", "Click on the Element with TEXT :" +
					text , "Not Clicked", true);

			ErrorCheck();
			throw e;

		}
	}

	public void ClickXY(String[] key,int XCo,int YCo)throws Exception{
		try {
			SetLocatorsValues1(key);
			oGbl.Client.click(Zone1, Element1, Index1, 1,XCo, YCo);

			Report("INFO", "Clicking on the [" +
					Object_Description1 + "]", "Clicked  On "+Object_Description1, true);

		} catch (Exception e) {

			Report("FAIL", "Clicking on the [" +
					Object_Description1 + "]", "Not Clicked  On "+Object_Description1, true);

			ErrorCheck();
			throw e;

		}
	}

	public void ClickIn(String[] key1,String direction,String[] key2,int Width,int Height) throws Exception{

		try {
			SetLocatorsValues1(key1);
			SetLocatorsValues2(key2);
			oGbl.Client.clickIn(Zone1, Element1, Index1, direction,
					Zone2, Element2, Index2,Width,Height,1);

			Report("INFO", "Click on the [" +
					Object_Description1 + "]", "Clicked On "+Object_Description1, true);

		} catch (Exception e) {

			Report("FAIL", "Click on the [" +
					Object_Description1 + "]", "Not Clicked On "+Object_Description1, true);

			ErrorCheck();
			throw e;
		}
	}

	public void LongClick(String[] key,int XCo,int YCo)throws Exception{
		try {
			SetLocatorsValues1(key);
			oGbl.Client.longClick(Zone1, Element1, Index1, 1,XCo, YCo);

			Report("INFO", "LongClick on " + Object_Description1, "LongClicked on "
					+ Object_Description1, true);
		} catch (Exception e) {

			Report("FAIL", "LongClick on " + Object_Description1,
					" Not LongClick on " + Object_Description1
					+ " - Execution Stopped Due to Error", true);
			ErrorCheck();
			throw e;
		}
	}

	public void ClickCoordinate(int XCo,int YCo)throws Exception{
		try {

			oGbl.Client.clickCoordinate(XCo,YCo, 1);

			Report("INFO", "Click on Coordinate for ["+Object_Description1+"]", "Clicked On "+Object_Description1, true);

		} catch (Exception e) {

			Report("FAIL", "Click on Coordinate for ["+Object_Description1+"]", "Not Clicked On "+Object_Description1, true);

			ErrorCheck();
			throw e;
		}

	}

	public boolean IsElementFound(String[] key){
		boolean Element1Presence = false;
		try {
			SetLocatorsValues1(key);
			Element1Presence = oGbl.Client.isElementFound(Zone1, Element1, Index1);		

			if (Element1Presence) {Report("INFO",
					"Is Element Found[" + Object_Description1 + "]", "Element "+Object_Description1+" Is Found", false);
			}

		} catch (Exception e) {
			e.printStackTrace();	
			Element1Presence = false;
		}
		return Element1Presence;
	}

	public boolean IsElementBlank(String[] key,int ColorGroups)throws Exception{
		boolean boolElement1Blank = false;
		try {
			SetLocatorsValues1(key);
			boolElement1Blank = oGbl.Client.isElementBlank(Zone1, Element1, Index1, ColorGroups);
			Report("PASS", " Is Element Blank " +Object_Description1, "Element "+Object_Description1+" Is Blank", true);
		} catch (Exception e) {
			Report("FAIL", "Is Element Blank" +Object_Description1, "Element Is Not Blank", true);
			throw e;	
		}
		return boolElement1Blank;
	}	

	public void LaunchApp(String activity, Boolean instrument,
			Boolean stopIfRunning) throws Exception {

		try {			
			ApplicationClose(activity);
			oGbl.Client.launch(activity, instrument, stopIfRunning);
			oGbl.Client.sleep(10);
			Sleep(15000);

			/*	if(IsElementFound(OR.AC_ErrorPopText))
			{
				Click(OR.AC_ErrorPopUpSkipButton);
			}*/
			Report("INFO", "Launch " + oGbl.AppName, "Launched " +  oGbl.AppName+" Application", true);
		} catch (Exception e) {
			throw e;
		}

	}

	public void ApplicationClose(String activity) throws Exception {

		try {
			oGbl.Client.applicationClose(activity);

			Report("INFO", "Close Application  " + oGbl.AppName, "Closed " + oGbl.AppName+" Application", true);

		} catch (Exception e) {
			throw e;
		}

	}

	public void Uninstall(String App,String Appname) throws Exception {

		boolean actionPerformed = false;

		try {
			actionPerformed = oGbl.Client.uninstall(App);

			if (actionPerformed == true) {
				Report("INFO", "Uninstall Application  " + Appname, "Uninstalled "
						+ Appname, true);
			} else {
				Report("FAIL", "Uninstall Application  " + Appname,
						"Uninstall error in " + Appname, true);
			}
		} catch (Exception e) {
			Report("FAIL", "Uninstall Application  " + Appname,
					"Uninstall error in " + Appname, true);
			ErrorCheck();
			e.printStackTrace();

			throw e;
		}

	}

	public int GetElementCount(String[] key) throws Exception{

		int ResultValue = 0;
		try {			
			SetLocatorsValues1(key);
			ResultValue = oGbl.Client.getElementCount(Zone1, Element1);
			if (ResultValue >= 0) {

				Report("INFO", "Get the count of the Element [" + Object_Description1
						+ "]", "count is [" + ResultValue + "]", true);
			}
		} catch (Exception e) {

			Report("FAIL", "Get the count of the Element[" + Object_Description1
					+ "]", "Error Getting count", true);
			ErrorCheck();
			throw e;

		}
		return ResultValue;
	}

	public String ElementGetProperty(String[] key,String property)throws Exception{

		String ResultValue = "";
		try {
			SetLocatorsValues1(key);
			ResultValue = oGbl.Client.elementGetProperty(Zone1, Element1, Index1,
					property);
			if (!ResultValue.isEmpty()) {

				Report("INFO", "Get the property of the Element[" + Object_Description1
						+ "]", "property value is [" + ResultValue + "]", true);
			}
		} catch (Exception e) {

			Report("FAIL", "Get the property of the Element[" + Object_Description1 + "]",
					"Error getting the proverty value", true);
			ErrorCheck();
			throw e;

		}
		return ResultValue;
	}

	public String ElementGetText(String[] key) throws Exception{
		String ResultValue = "";
		try {
			SetLocatorsValues1(key);
			ResultValue = oGbl.Client.elementGetText(Zone1, Element1, Index1);
			if (ResultValue.length() >= 0) {
				/*Report("INFO",
				"Get the text of the element[" + Object_Description + "]",
				"Got the text[" + ResultValue + "] from [" + Object_Description + "]",
				true);*/
				System.out.println(ResultValue);
			}

		} catch (Exception e) {

			Report("FAIL", "Getting the text of the Element[" + Object_Description1 + "]",
					"Not getting", true);
			ErrorCheck();
			throw e;
		}
		return ResultValue;
	}

	public String ElementGetText_GivenText(String Zone1,String Element1,int Index1) throws Exception{
		String ResultValue = "";
		try {

			ResultValue = oGbl.Client.elementGetText(Zone1, Element1, Index1);
			if (ResultValue.length() >= 0) {

				Report("INFO",
						"Get the text of the Element [" + Object_Description1 + "]",
						"Element Text value is [" + ResultValue + "] from [" + Element1 + "]",
						true);
			}

		} catch (Exception e) {

			Report("FAIL", "Get the text of the Element [" + Element1 + "]",
					"Error getting text", true);
			ErrorCheck();
			throw e;
		}
		return ResultValue;
	}

	public boolean IsFoundIn(String[] key1,String Direction,String[] key2,int Width,int Height) throws Exception{
		boolean actionPerformed;

		try {
			SetLocatorsValues1(key1);
			SetLocatorsValues2(key2);
			actionPerformed = oGbl.Client.isFoundIn(Zone1, Element1, Index1,
					Direction,Zone2, Element2,Width, Height);

			Report("INFO", "Is Found In " + Object_Description1 + "]", "It Is Found In", true);
		} catch (Exception e) {

			Report("INFO", "Is Found In " + Object_Description1 + "]", "It Is Not Found In", true);
			actionPerformed = false;
			throw e;
		}
		return actionPerformed;
	}

	public String GetTextIn(String[] key,String Direction,int Width,int Height) throws Exception{
		String ResultValue = "";
		try {
			SetLocatorsValues1(key);
			ResultValue = oGbl.Client.getTextIn(Zone1, Element1, Index1,
					Zone1,Direction,Width,Height);

			if (ResultValue.length() >= 0) {
				Report("INFO",
						"Get the text of the Element [" + Object_Description1 + "]",
						"The text value is [" + ResultValue + "]", true); }

		} catch (Exception e) {

			Report("FAIL",
					"Get the text of the Element[" + Object_Description1 + "]",
					"Error getting text", true);

			ErrorCheck();
			throw e;

		}
		return ResultValue;
	}


	public String GetTextInindex(String[] key,int index,String Direction,int Width,int Height) throws Exception{
		String ResultValue = "";
		try {
			SetLocatorsValues1(key);
			ResultValue = oGbl.Client.getTextIn(Zone1, Element1, index,
					Zone1,Direction,Width,Height);

			if (ResultValue.length() >= 0) {
				Report("INFO",
						"Get the text of the Element [" + Object_Description1 + "]",
						"The text value is [" + ResultValue + "]", true); }

		} catch (Exception e) {

			Report("FAIL",
					"Get the text of the Element[" + Object_Description1 + "]",
					"Error getting text", true);

			ErrorCheck();
			throw e;

		}
		return ResultValue;
	}
	public String GetTextIn_GivenText(String Zone1,String Element1,int Index1,String Zone2,String Dir,int width,int height) throws Exception{
		String ResultValue = "";
		try {			
			ResultValue = oGbl.Client.getTextIn(Zone1, Element1, Index1,
					Zone2,Dir,width,height);

			if (ResultValue.length() >= 0) {
				Report("INFO",
						"Getting the text of the Element[" + Object_Description1 + "]",
						"Got the text[" + ResultValue + "]", true); }

		} catch (Exception e) {

			Report("FAIL",
					"Getting the text of the Element[" + Object_Description1 + "]",
					"Not getting", true);

			ErrorCheck();
			throw e;

		}
		return ResultValue;
	}

	public void VerifyIn(String[] key1,String Direction,String[] key2,int width,int height) throws Exception{

		try {
			SetLocatorsValues1(key1);
			SetLocatorsValues2(key2);
			oGbl.Client.verifyIn(Zone1, Element1, Index1,
					Direction, Zone2, Element2,width,height);

			Report("PASS", "Verify " + Object_Description1, "Verified " + Object_Description1, true);
		} catch (Exception e) {
			Report("FAIL", "Verify " + Object_Description1, "Failed to Verify " + Object_Description1, true);
			ErrorCheck();
			throw e;
		}

	}

	public String GetDeviceModel(String DeviceName) throws Exception{
		String Model = "";
		try {
			oGbl.Client.setApplicationTitle(DeviceName);
			Model = (String) oGbl.Client.getLastCommandResultMap().get("text");
			int Index1 = Model.indexOf("Version:");
			Model = Model.substring(0, Index1);
			int Index2 = Model.indexOf("Model:");
			Model = Model.substring(Index2).trim();

			Report("INFO", "Getting model name of the device",
					"Got the model name[" + Model + "]", true);

		} catch (Exception e) {

			Report("INFO", "Getting model name of the device",
					"Not able to the model name", true);
			ErrorCheck();
			throw e;
		}

		return Model;
	}

	public void SetDevice(String DeviceName) throws Exception {
		try {
			oGbl.Device_Under_Test=TD(DeviceName);
			if(oGbl.Device_Under_Test == null)
			{
				oGbl.Device_Under_Test = DeviceName;
			}
			if (!DeviceName.toLowerCase().contains("desktop")) {
				oGbl.Client.setDevice(oGbl.Device_Under_Test);				
			} else {
				oGbl.Client.setDevice("desktop");
			}

			Report("INFO", "Set the Device "+ oGbl.Device_Under_Test, "Set Device " + oGbl.Device_Under_Test + " was successful", true);
		} catch (Exception e) {

			Report("FAIL", "Set the Device", "Failed to set device",
					false);
			ErrorCheck();
			throw e;
		}
	}

	public void Home() throws Exception{
		try {
			oGbl.Client.sendText("{Home}");
			oGbl.Client.sleep(10);
			Report("INFO", "Click on Home", "Home button is selected", true);
		} catch (Exception e) {

			Report("FAIL", "Click on Home", "Home button is not selected",
					true);
			ErrorCheck();
			throw e;
		}
	}

	public void Menu() throws Exception{
		try {
			oGbl.Client.sendText("{F2}");

			Report("INFO", "Click on Menu", "Clicked on Menu", true);
		} catch (Exception e) {

			Report("FAIL", "Click on Menu", "Not Clicked on Menu", true);
			ErrorCheck();
			throw e;
		}
	}

	public void ErrorCheck() throws Exception{

		try {
			if (GetLastCommandResultMap()) {
				oGbl.errFlag = oGbl.errFlag + 1;				
			}

		} catch (Exception e1) {
			throw e1;
		}

	}

	public boolean GetLastCommandResultMap() throws Exception{
		boolean flag = false;
		try {
			flag = oGbl.Client.getLastCommandResultMap().get("status")
					.equals(false);
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	public void ApplicationClearData(String PackageName, String Desc) throws Exception{
		try {
			oGbl.Client.applicationClearData(PackageName);

			Report("INFO", "Clear Application Data for "+Desc, "Cleared",
					true);
		} catch (Exception e) {

			Report("FAIL", "Clear Application Data for "+Desc, "Not Cleared",
					true);
			ErrorCheck();
			throw e;
		}

	}

	public String GetDeviceSerialNumber(String DeviceName) throws Exception {
		String SN = null;
		try {
			SetDevice("adb:" + DeviceName);
			SN = (String) oGbl.Client.getLastCommandResultMap().get("text");
			int Index1 = SN.indexOf("OS: Android");
			SN = SN.substring(0, Index1);
			int Index2 = SN.indexOf("S/N");
			SN = SN.substring(Index2).trim();
			SN = SN.replaceAll("S/N: ", "");

			Report("PASS", "Getting device serial number[" + SN + "]", "Got",
					true);

		} catch (Exception e) {

			Report("FAIL", "Getting device serial number[" + SN + "]",
					"Not Getting", true);
			throw e;
		}
		return SN;
	}

	public String Run(String cmd, String Desc) throws Exception {
		String str = "";
		try {
			str = oGbl.Client.run(cmd);			
			Report("INFO", "Executing Run CMD "+Desc+" " + cmd, "Executed " + cmd
					+ " Successfully", true);
		} catch (Exception e) {
			Report("INFO", "Execute Run CMD "+Desc+" " + cmd, "Executed " + cmd
					+ " Successfully", true);
			ErrorCheck();
			throw e;
		}
		return str;
	}

	public void SyncElement1s(int silent, int Timeout) throws Exception {

		try {
			oGbl.Client.syncElements(silent, Timeout);
			Report("INFO", "SyncElement1s", "Synched", true);

		} catch (Exception e) {

			Report("INFO", "SyncElement1s", "Not Synched", true);
			ErrorCheck();
			throw e;
		}

	}

	public void Reboot(String DeviceName,int sec) throws Exception {
		try {
			SetDevice(DeviceName);
			oGbl.Client.reboot(sec);
			Sleep(5000);
			SetDevice(DeviceName);
			Sleep(3000);
			oGbl.Client.sendText("{Unlock}");
			oGbl.Client.sendText("{Home}");

			Report("INFO", "Device Restart", "Device Restarted", true);
		} catch (Exception e) {

			Report("FAIL", "Device Restart", "Device Not Restarted", true);
			ErrorCheck();

			throw e;
		}
	}

	public String GetDeviceLog() throws Exception{
		String temp = "";
		try {
			temp = oGbl.Client.getDeviceLog();
		} catch (Exception e) {

			Report("FAIL", "GetDeviceLog", "Did Not Take GetDeviceLog", true);
			ErrorCheck();
			throw e;
		}
		return temp;
	}

	@SuppressWarnings("deprecation")
	public void MaximizeDevice() throws Exception{
		try {
			oGbl.Client.maximize();
		} catch (Exception e) {

			Report("FAIL", "MaximizeDevice", "Did Not MaximizeDevice", true);
			ErrorCheck();
			throw e;
		}
	}

	public void SetDragStartDelay(int delay) throws Exception{

		try {
			oGbl.Client.setDragStartDelay(delay);
		} catch (Exception e) {

			Report("FAIL", "MaximizeDevice", "Did Not Do SetDragStartDelay", true);
			ErrorCheck();
			throw e;

		}

	}

	public void Drag(String[] key,int XCo,int YCo) throws Exception{

		try {

			SetLocatorsValues1(key);
			oGbl.Client.drag(Zone1, Element1, Index1, XCo, YCo);

			Report("INFO", "Drag [" + Object_Description1 + "]",
					"Draged ", true);

		} catch (Exception e) {

			Report("FAIL", "Drag  [" + Object_Description1 + "]",
					"Not Draged", true);

			ErrorCheck();
			throw e;

		}
	}

	public void DragCoordinates(int x1, int y1, int x2, int y2, int time) throws Exception{
		try {
			oGbl.Client.dragCoordinates(x1, y1, x2, y2, time);

			Report("INFO", " Drag Coordinates",
					"Draged", true);

		} catch (Exception e) {

			Report("FAIL", "Drag Coordinates",
					"Not Draged", true);

			ErrorCheck();
			throw e;

		}
	}

	public void ElementSwipe(String[] key,String direction, int offset, int time) throws Exception{

		try {
			SetLocatorsValues1(key);
			oGbl.Client.elementSwipe(Zone1, Element1, Index1, direction,
					offset, time);

			Report("INFO", "Swipe the Element" + Object_Description1 + " ", Object_Description1, true);
		} catch (Exception e) {

			Report("FAIL", "Swipe " + Object_Description1 + " ", Object_Description1,
					true);
			ErrorCheck();

		}
	}

	public void Swipe(String Direction, int offset, int Delay, String Des) throws Exception{

		try {
			oGbl.Client.swipe(Direction, offset, Delay);

			Report("INFO", "Swipe " + Direction + " ", "Swiped for " + Des,
					true);
		} catch (Exception e) {

			Report("FAIL", "Swipe " + Direction + " ", "Not Swiped " + Des,
					true);
			ErrorCheck();
			throw e;

		}
	}

	/*‘**************************************************
	#Project Name: Air Canada Mobile Automation
	#Function Name: SwipeWith2ElementRef()
	#Author: Roshith India
	#Description: This function will swipe on screen with reference of two elements to find it on screen.
	#Date of creation: 1-August-2015
	#Input Parameters: String[] elementOne, String[] elementTwo, String direction, int offSet, int roundsOfSwipe, boolean clickElementOne 
	#Name of person modifying: Tester
	#Date of modification: 23-Sep-2015
	‘***************************************************/
	public void SwipeWith2ElementRef(String[] elementOne, String[] elementTwo, String direction, int offSet, int roundsOfSwipe, boolean clickElementOne)
	{
		try
		{
			if(oGbl.Device_Under_Test.startsWith("ios_app"))
			{
				offSet = 700;
			}
			boolean elementFound = false; 
			String element2Direction = "";
			switch(direction.toLowerCase())
			{
			case "up":
				element2Direction = "Down";
				break;
			case "down":
				element2Direction = "Up";
				break;
			case "left":
				element2Direction = "Right";
				break;
			case "right":
				element2Direction = "Left";
				break;
			}

			SetLocatorsValues1(elementOne);
			oGbl.SetRecoveryScenarioErrorCheck= false;
			SetLocatorsValues2(elementTwo);
			oGbl.SetRecoveryScenarioErrorCheck= true;

			for(int swipeNo =1; swipeNo <= roundsOfSwipe; swipeNo++)
			{
				boolean Element1Present = oGbl.Client.isElementFound(Zone1, Element1, Index1);	
				boolean Element2Present = false;
				if(!Element2.equalsIgnoreCase(""))
				{
					Element2Present = oGbl.Client.isElementFound(Zone2, Element2, Index2);	
				}

				if(Element1Present == false && Element2Present == false)
				{
					oGbl.Client.swipe(direction, offSet, 500);
				}else if(Element1Present == false && Element2Present == true)
				{
					if(oGbl.Device_Under_Test.startsWith("ios_app"))
					{
						oGbl.Client.swipe(element2Direction, 750, 500);
					}else
					{
						oGbl.Client.swipe(element2Direction, 1100, 500);
					}
				}else if(Element1Present == true && Element2Present == false)
				{
					if(oGbl.Device_Under_Test.startsWith("ios_app"))
					{
						oGbl.Client.swipe(direction, 750, 500);
					}else
					{
						oGbl.Client.swipe(direction, 1100, 500);
					}
					if(Element2.equalsIgnoreCase(""))
					{
						elementFound = true;
						if(clickElementOne == true)
						{
							oGbl.Client.click(Zone1, Element1, Index1, 1);
						}
						Report("INFO", "Swipe  " + direction + " ", "Found the element " + Object_Description1 + " on screen.", true);
						break;
					}
				}else if(Element1Present == true && Element2Present == true)
				{
					elementFound = true;
					if(clickElementOne == true)
					{
						oGbl.Client.click(Zone1, Element1, Index1, 1);
					}
					Report("INFO", "Swipe  " + direction + " ", "Found the element " + Object_Description1 + " on screen.", true);
					break;
				}
			}

			if(elementFound == false)
			{
				Report("INFO", "Swipe  " + direction + " ", "Not able to find the element " + Object_Description1 + " on screen.", true);
			}



		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}


	public boolean SwipeWhileNotFound(String[] key,String Direction,int Rounds, boolean Click) throws Exception{


		boolean actionPerformed=false;
		int OFFSET = 0;

		try {
			SetLocatorsValues1(key);

			String direction=Direction.toLowerCase();
			if(direction.equals("up") || direction.equals("down")){OFFSET=oGbl.gSwipe50pY;}
			else{OFFSET=oGbl.gSwipe50pX;}

			actionPerformed = oGbl.Client.swipeWhileNotFound(Direction, OFFSET,500, Zone1, Element1, Index1, 500,8, false);

			if(actionPerformed){
				int Y,X;
				boolean onScreen=false;

				onScreen = Boolean.valueOf(oGbl.Client.elementGetProperty(Zone1, Element1, Index1, "onScreen"));					
				for(int i=1;i<=10;i++){
					if(!onScreen){
						Swipe(Direction,OFFSET,500,"");
						onScreen = Boolean.valueOf(oGbl.Client.elementGetProperty(Zone1, Element1, Index1, "onScreen"));
					}else{break;}
				}

				switch(Direction.toLowerCase())
				{
				case "up":					
					Y = Integer.parseInt(oGbl.Client.elementGetProperty(Zone1, Element1, Index1, "y"));					
					if(Y<oGbl.gSwipe25pY){Swipe(Direction,oGbl.gSwipe75pY,500,"");}				
					break;
				case "down":
					Y = Integer.parseInt(oGbl.Client.elementGetProperty(Zone1, Element1, Index1, "y"));
					if(Y>oGbl.gSwipe75pY){Swipe(Direction,oGbl.gSwipe75pY,500,"");}									
					break;
				case "left":
					X = Integer.parseInt(oGbl.Client.elementGetProperty(Zone1, Element1, Index1, "x"));					
					if(X<oGbl.gSwipe25pX){Swipe(Direction,oGbl.gSwipe25pX,500,"");}					
					break;
				case "right":
					X = Integer.parseInt(oGbl.Client.elementGetProperty(Zone1, Element1, Index1, "x"));
					if(X>oGbl.gSwipe75pX){Swipe(Direction,oGbl.gSwipe75pX,500,"");}
					break;					
				}
			}

			if(Click){Sleep(1000);oGbl.Client.click(Zone1, Element1, Index1, 1);}

			Report("INFO", "Swipe to find the Element "+Object_Description1 , "Swiped and found the "+Object_Description1+" in the Direction " + Direction, true);

		} catch (Exception e) {

			Report("FAIL", "Swipe to find the Element "+Object_Description1, "Swiped and not found the "+Object_Description1+" in the Direction " + Direction,
					true);
			actionPerformed = false;
			ErrorCheck();
			throw e;
		}

		return actionPerformed;
	}

	public void SwipeElement_ToTopOfThePage(String[] key,String Direction,int Rounds) throws Exception{
		SwipeWhileNotFound(key, Direction, Rounds, false);

		int Y = Integer.parseInt(oGbl.Client.elementGetProperty(Zone1, Element1, Index1, "y"));

		oGbl.Client.setDragStartDelay(250);
		for(int i =1; i <= 8; i++)
		{
			if(Y>oGbl.gSwipe25pY){			
				oGbl.Client.drag(Zone1,Element1,Index1,0,-250);
				Y = Integer.parseInt(oGbl.Client.elementGetProperty(Zone1, Element1, Index1, "y"));
			}else{break;}
		}
	}



	public boolean WaitForElement(String[] key,int Timeout) throws Exception{

		boolean actionPerformed;
		try {			
			SetLocatorsValues1(key);
			actionPerformed = oGbl.Client.waitForElement(Zone1, Element1, Index1,
					Timeout);
			Sleep(500);
		} catch (Exception e) {
			actionPerformed = false;
		}
		return actionPerformed;
	}


	public boolean WaitForElement_UntilDissapear(String[] key,int min) throws Exception{
		boolean Notfound=false;
		try {
			int i= 1;
			while(i<min*120){				
				if(oGbl.Client.waitForElement(key[1], key[2],Integer.parseInt(key[3]),0)){
					Sleep(500);
					i++;
				}else{
					Notfound=true;
					break;
				}
			}

		} catch (Exception e) {
			ErrorCheck();
			throw e;
		}
		return Notfound;

	}

	public void VerifyElementFound(String[] key) throws Exception{
		try {
			SetLocatorsValues1(key);
			oGbl.Client.verifyElementFound(Zone1, Element1, Index1);
			Report("PASS", "Verify Element [" + Object_Description1 + "] is found",
					"Element " + Object_Description1 +"["+Element1+ "] is Found", true);
		} catch (Exception e) {

			Report("FAIL", "Verify Element [" + Object_Description1 + "] is found",
					"Element " + Object_Description1 +"["+Element1+ "] is not Found", true);
			ErrorCheck();
			throw e;
		}


	}

	public void VerifyElementNotFound(String[] key) throws Exception{
		try {
			SetLocatorsValues1(key);
			oGbl.Client.verifyElementNotFound(Zone1, Element1, Index1);
			Report("PASS", "Verify Element Not Found  " + Object_Description1 +"["+Element1+ "]", "Verified Element Not Found "
					+ Object_Description1, true);

		} catch (Exception e) {

			Report("FAIL", "Verify Element Not Found " + Object_Description1 +"["+Element1+ "]", "Verified Element1 is Found "
					+ Object_Description1, true);
			ErrorCheck();
			throw e;

		}
	}

	public void TextFilter(String color, int sensitivity) throws Exception{
		try {
			oGbl.Client.textFilter(color, sensitivity);
		} catch (Exception e) {
			Report("FAIL", "Verify TextFilter", "Not Verified TextFilter", true);
			ErrorCheck();
			throw e;
		}

	}

	public void Sleep(int interval) throws Exception{
		try {
			oGbl.Client.sleep(interval);
		} catch (Exception e) {
			Report("FAIL", "Sleep ", "Sleep did not work", true);
			ErrorCheck();
			throw e;
		}

	}

	public void Capture(int interval) throws Exception{
		try {
			oGbl.Client.capture();

			Report("INFO", "Capture", "Capture is Done", true);
		} catch (Exception e) {

			Report("INFO", "Capture", "Capture is not Done", true);
			ErrorCheck();
			throw e;
		}

	}

	public void Report(String STEP_SUMMARY, String STEP_DESCRIPTION,
			String STEP_RESULT, boolean Screenshot) throws Exception{
		try {


			if(oGbl.RecoveryScenarioErrorMsg.isEmpty()){

				if (Screenshot && (STEP_SUMMARY.equalsIgnoreCase("FAIL") || STEP_SUMMARY.equalsIgnoreCase("PASS")) || STEP_SUMMARY.equalsIgnoreCase("WARNING")) {
					oGbl.oRep.Add_TestStep_InDetailedReport(STEP_SUMMARY, STEP_DESCRIPTION,
							STEP_RESULT,oGbl.GetCounter(STEP_DESCRIPTION, oGbl),oGbl.GetCounterMemory(STEP_DESCRIPTION, oGbl), CaptureScreenShot(), oGbl);

				} else {

					oGbl.oRep.Add_TestStep_InDetailedReport(STEP_SUMMARY, STEP_DESCRIPTION,
							STEP_RESULT,oGbl.GetCounter(STEP_DESCRIPTION, oGbl),oGbl.GetCounterMemory(STEP_DESCRIPTION, oGbl), "", oGbl);

				}			

			}

			if (STEP_SUMMARY.equalsIgnoreCase("FAIL")) {
				oGbl.errFlag = oGbl.errFlag + 1;
				if(oGbl.errFlag<=1){
					Throw_Exception(STEP_RESULT);
				}
			}

		} catch (Exception e) {

			throw e;
		}
	}

	public void CloseOpenSeeTest(){
		try{
			//String ServiceName="Sentinel LDK License Manager";
			Runtime.getRuntime().exec("taskkill /F /IM studio.exe");			
			//StopStartService(ServiceName,"stop");
			Thread.sleep(10000);
			//StopStartService(ServiceName,"start");			
			Runtime.getRuntime().exec("C:\\Program Files\\Experitest\\SeeTest\\studio.exe");			
			Thread.sleep(20000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void AddOpenDevicesFromSeeTest(String[] aDeviceInfo){
		try{	

			for(String device:aDeviceInfo){				
				oGbl.Client.addDevice(oGbl.TD.get(device+"_SN"),oGbl.TD.get(device+"_Name").replace("ios_app:", ""));
				Thread.sleep(2000);
				oGbl.Client.setDevice(oGbl.TD.get(device+"_Name"));
				Thread.sleep(2000);
				oGbl.Client.openDevice();	
				Thread.sleep(5000);
			}


		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String CaptureScreenShot() throws Exception {
		String ImageFileName = "";		
		//String outFile;		
		try {



			ImageFileName=oGbl.gTestCaseName+"_Iteration"+oGbl.gTestCaseIterationCount+"_"+oGbl.gDeviceName+"_"+oGbl.oCmn.RandomString(5)+".png";			
			String[] command = {"cmd","/c", oGbl.gUtilitiesPath+"\\Capture_Screenshot.exe",oGbl.Device_Under_Test,oGbl.gReportDetailsFolderPath+"\\"+ImageFileName};
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();

			return ImageFileName;

			/*oGbl.Client.sleep(1);
			outFile = (String) oGbl.Client.getLastCommandResultMap().get("outFile");
			String reportPath = oGbl.ReportPath;
			if(reportPath == null || reportPath.equalsIgnoreCase(""))
			{
				reportPath =oGbl.prop.getProperty("ReportPath");
				oGbl.ReportPath = reportPath;
			}
			String ImageFolderPath =oGbl.SetSlash(oGbl.ReportPath+ "\\Details\\");

			File sourceFile = new File(outFile);
			String name = sourceFile.getName();
			File targetFile = new File(ImageFolderPath +oGbl.TC_Detail_For_Report+name);

			ImageFileName=oGbl.TC_Detail_For_Report+name;

			boolean bFound1=false;
			int i=0;
			for(i=1;i<=10;i++){

				if (!bFound1){
					try
					{						
						Thread.sleep(1000);
						FileUtils.copyFile(sourceFile, targetFile);
						bFound1=true;
						//System.out.println("Screenshot Captured in : " + i + " iteration"); 
						break;
					}catch (Exception e) 
					{
						if(i==10)
						{
							System.out.println("Error in taking Screenshot happend - " + e.toString());
						}
					}					
				}
			}

			if (bFound1){
				oGbl.oCmn.ReduceImageSize(targetFile.getAbsolutePath());
			}else{System.out.println("Not able to get seetest image after waiting 60 sec");}

			return ImageFileName;*/

		} catch (Exception e) {
			throw e;
		}

	}

	public boolean SwipeWhileNotFound_Text(String DynamixTexts,String Direction, int Offset,
			int SwipeTime,int Delay, int Rounds, boolean Click) throws Exception{

		boolean actionPerformed;

		try {


			actionPerformed = oGbl.Client.swipeWhileNotFound(Direction, Offset,
					SwipeTime, "Text", DynamixTexts, Index1, Delay,
					Rounds, Click);


			Report("INFO", "Swipe for the Element "+DynamixTexts+" in the Direction " + Direction, "Swiped  "+DynamixTexts+" in the Direction " + Direction, true);

		} catch (Exception e) {

			Report("FAIL", "Swipe for the Element "+DynamixTexts+" in the Direction " + Direction, "Not Swiped  "+DynamixTexts+" in the Direction " + Direction,
					true);
			actionPerformed = false;
			ErrorCheck();
			throw e;
		}

		return actionPerformed;
	}

	public void DragText(String Element1,int Index1,int XCo,int Yco) throws Exception{

		try {


			oGbl.Client.drag("NATIVE", "xpath=//*[@text='"+Element1+"']", Index1, XCo, Yco);

			Report("INFO", "Drag [" + Object_Description1 + "]",
					"Draged ", true);

		} catch (Exception e) {

			Report("FAIL", "Drag  [" + Object_Description1 + "]",
					"Not Draged", true);

			ErrorCheck();
			throw e;

		}
	}	

	public void ElementSwipetext(String Element1,int Index1,String direction, int offset, int time) throws Exception{

		try {
			oGbl.Client.elementSwipe("NAIVE", "xpath=//*[@text='"+Element1+"']", Index1, direction,
					offset, time);

			Report("INFO", "Swipe the Element" + Object_Description1 + " ", Object_Description1, true);
		} catch (Exception e) {

			Report("FAIL", "Swipe " + Object_Description1 + " ", Object_Description1,
					true);
			ErrorCheck();

		}
	}

	public Exception Throw_Exception(String msg) throws MyException{
		throw new MyException(msg);		
	}

	public boolean verifyTraveloptntext(String optn){

		if(optn.equalsIgnoreCase("Maple Leaf Lounge"))
		{
			optn="Lounge Access";
		}
		else{}	
		if(oGbl.Client.waitForElement("NATIVE", "xpath=//*[contains(@text,'"+ optn +"') and @id='title_text_view']", 0, 5000)){
			return true;
		}else{
			return false;
		}
	}


}



@SuppressWarnings("serial")
class MyException extends Exception{
	String str1;
	MyException(String str2) {
		str1=str2;
	}
	public String toString(){ 
		return ("User Defined Exception thrown for :"+str1) ;
	}
}
