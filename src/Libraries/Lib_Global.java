package Libraries;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import com.experitest.client.Client;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import Resources.ObjectRepository.OR;

public class Lib_Global implements Cloneable{
 
	Client Client_Temp;
	public Client Client;
	public String gTestCaseName,gTestDescription;
	public String gTC_Status = "PASS";
	public int gTS_Count=0;
	public int errFlag=0;
	public String AppName="AIR CANADA";
	public String Local="";
	public String thisscenario="yes";
	public String chksave="yes";
	//public String gAirCanadaActivity="cloud:com.aircanada/.activity.MainActivity";
	//public String gAirCanadaActivity="com.aircanada/.activity.SplashScreen";
	//public String gAirCanadaActivity="com.aircanada.acapp";
	public String gAirCanadaActivity ="";
	String[] Devices;
	public String Device_Under_Test;
	public String gDeviceName;
	public int gDeviceCount=1;
	public String Port_Under_Test;
	public String ScreenName = "NA";
	public int Failureflag = 0;
	public int status=0;
	protected boolean Pflag = false;

	public int 
	gSwipe50pY,gSwipe10pY,gSwipe25pY,gSwipe75pY,gSwipe90pY,
	gSwipe50pX,gSwipe10pX,gSwipe25pX,gSwipe75pX,gSwipe90pX;


	public String gTestStepStartTime;
	public String gTestStepEndTime;
	public String gTestCaseStartTime;
	public String gTestCaseEndTime;
	public String gTestSuiteStartTime;
	public String gTestSuiteEndTime;
	public String gCarrosalData="Temp";

	public int ScreenLoadWaitingTimeInMinute=3;
	public String RecoveryScenarioErrorMsg = "";
	public boolean SetRecoveryScenarioErrorCheck = true;
	
	//Path Variables
	public boolean gJAR_Execution=false;
	public String gRootPath=System.getProperty("user.dir");
	public String gResourcesFolderPath=gRootPath+"/src/Resources/";
	public String gOrganizerExcelPath=System.getProperty("user.dir")+File.separator+"Organizer.xls";
        //public String gOrganizerExcelPath=System.getProperty ("user.dir")+"/"+"Organizer.xls";
	public String gUtilitiesPath;	
	
	public String gDateStamp = new SimpleDateFormat("MMMddyyyy").format(Calendar.getInstance().getTime());
	public String gTimeStamp=new SimpleDateFormat("hh_mm_ss_a").format(Calendar.getInstance().getTime());	
	public String gReportPath;
	public String gReportDetailsFolderPath;
	public String gTestCaseDetailedReportPath;
	public String gTestCaseChartPath;
	public String gTestCaseMChartPath;

	public int gTotalTestCasesCount;
	public int gPassedTestCaseCount=0;
	public int gFailedTestCaseCount=0;
	public ArrayList<String> alTestForExecution= new ArrayList<String>();

	public int gTestCaseIterationCount;

	public Lib_Common oCmn;
	public Lib_Report oRep;
	public Lib_SeeTest oSeeTest;
	public OR OR;
	public Hashtable<String, String> TD;
	public Hashtable<String, String> TD_Cmn;

	

	Hashtable<String, Integer> DevicePassedTests= new Hashtable<String, Integer>();
	Hashtable<String, Integer> DeviceFailedTests= new Hashtable<String, Integer>();
	Hashtable<String, Integer> DeviceXYoffsets= new Hashtable<String, Integer>();
	Hashtable<String, String> ghtLocalValues= new Hashtable<String, String>();
	public String Chk_status="no";
	
	private void SetPaths(){
		System.out.println(gOrganizerExcelPath);
		if(new File(gRootPath+"/src").exists()){ 
			gReportPath=new File(gRootPath).getParent() +"Results/"+gDateStamp+ "/ExecutionSet_" + gTimeStamp;
			gUtilitiesPath=gResourcesFolderPath+"Utilities";
		}else{
			gJAR_Execution=true;
			gReportPath=gRootPath+"/Results/"+gDateStamp+ "/ExecutionSet_" + gTimeStamp;
			gUtilitiesPath=gRootPath+"/Utilities";
		}
		gReportDetailsFolderPath =gReportPath+"/Details";
		
	}

	public static void main(String[] args) {new Lib_Global().Executor();}		

	Lib_Global() {
		
		SetPaths();		
		oCmn = new Lib_Common();
		oRep = new Lib_Report();
		oRep.SetReportPaths(gReportDetailsFolderPath);
                System.out.println(gReportDetailsFolderPath);
		oSeeTest = new Lib_SeeTest();
		OR=new OR();
               
	}
	
	void Executor() {		
		try {						
		
			ArrayList<Thread> alTestThread = new ArrayList<Thread>();
                        System.out.println(gOrganizerExcelPath);
			ArrayList<String> alTestClasesForExecution= oCmn.GetClassesForExecution(gOrganizerExcelPath,
"Config");          
			Hashtable<String, String>[] td;
			gTotalTestCasesCount=alTestClasesForExecution.size();
			TD_Cmn=oCmn.GetCommonTD(gOrganizerExcelPath);
			ghtLocalValues=oCmn.GetLocalTD(gOrganizerExcelPath,TD_Cmn.get("Local"));

			int PORT;
			BeforeTestSuite();

			for(int k=0;k<alTestClasesForExecution.size();k++)		
			{
				String[] Temp=alTestClasesForExecution.get(k).split("=>");
				gTestCaseName=Temp[0];
				String TDName=Temp[1];
				gTestDescription=Temp[2];
				td=oCmn.GetTestDataHashTableArray(gOrganizerExcelPath, TDName, gTestCaseName);
				Class<?> TestClass= Class.forName("Test_Scenarios."+gTestCaseName);	
				
				

				for(int l=0;l<td.length;l++){

					TD=td[l];		
					TD.putAll(TD_Cmn);
					alTestForExecution.add(gTestCaseName);
					gTestCaseIterationCount=GetItreationCount(alTestForExecution,gTestCaseName);
					if(TD.get("Performance").equalsIgnoreCase("yes")){
						Pflag = true;
					}
					
					if(TD.get("Parallel").equalsIgnoreCase("yes")){
						for(int j=0;j<Devices.length;j++){
							PORT=Client_Temp.getAvailableAgentPort();
							while(PORT==-1)
							{
								Thread.sleep(10000);
								PORT=Client_Temp.getAvailableAgentPort();
							}
							Device_Under_Test=Devices[j];							
							Client=oSeeTest.CreateSeeTestClient(PORT,gRootPath);								
							alTestThread.add(Parallel_Executor(TestClass,(Lib_Global)this.clone()));
						}						
					}else{
						PORT=Client_Temp.getAvailableAgentPort();
						Device_Under_Test=Devices[0];
						Client=oSeeTest.CreateSeeTestClient(PORT,gRootPath);		

						Lib_Global oGbl=(Lib_Global)this.clone();
						BeforTest(oGbl);	
						try{
							TestClass.getMethod("Test_"+TestClass.getSimpleName(),Lib_Global.class).invoke(TestClass.newInstance(),oGbl);
						} catch (Exception e) {	
							if(oGbl.errFlag==0){
								oGbl.oRep.Add_TestStep_InDetailedReport("FAIL", "Unexpected Error Occured",e.getLocalizedMessage(),"","","",oGbl);
							}
							e.printStackTrace();
						}
						AfterTest(oGbl);
					}
				}				

			}

			WaitForAllTestThreadCompletion(alTestThread);			
			AfterTestSuite();

		} catch (Exception e) {					
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	void BeforeTestSuite() throws Exception{
		
		if(gJAR_Execution){
			oCmn.DeleteFileOrFolder(gUtilitiesPath);
			new File(gUtilitiesPath).mkdirs();

			oCmn.InputStreamToFile(this.getClass().getResourceAsStream("/Resources/Utilities/ACMainframeScript.vbs"), gUtilitiesPath+"/ACMainframeScript.vbs");
			oCmn.InputStreamToFile(this.getClass().getResourceAsStream("/Resources/Utilities/ACMainframeTerminal.WS"), gUtilitiesPath+"/ACMainframeTerminal.WS");
			oCmn.InputStreamToFile(this.getClass().getResourceAsStream("/Resources/Utilities/canvasjs.min.js"), gUtilitiesPath+"/canvasjs.min.js");
			oCmn.InputStreamToFile(this.getClass().getResourceAsStream("/Resources/Utilities/amcharts.js"), gUtilitiesPath+"/amcharts.js");
			oCmn.InputStreamToFile(this.getClass().getResourceAsStream("/Resources/Utilities/serial.js"), gUtilitiesPath+"/serial.js");
			oCmn.InputStreamToFile(this.getClass().getResourceAsStream("/Resources/Utilities/Capture_Screenshot.exe"), gUtilitiesPath+"/Capture_Screenshot.exe");
			oCmn.InputStreamToFile(this.getClass().getResourceAsStream("/Resources/Utilities/ClientLogo.jpg"), gUtilitiesPath+"/ClientLogo.jpg");
		}
				
		gTestSuiteStartTime= new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

		oRep.CreateFinalReport();		
		oRep.CreateHomePageWithLogo(this);
		oRep.CreateDevices_SummaryReport();	

		Client_Temp=new Client(){};	
		
		Client cClient=new Client("localhost",Client_Temp.getAvailableAgentPort(),false);
			
		
		String[] TempDevice =Client_Temp.getConnectedDevices().split("\\r?\\n");
		Devices=new String[TempDevice.length];
		String DevInfo,Name,Model,SerialNumber,DeviceName,Temp;
		
		for(int i=0;i<TempDevice.length;i++)
		{
			gDeviceName=TempDevice[i].replace("adb:", "").replace("ios_app:","").replace(" ","");
			oRep.CreateSuiteReport(gDeviceName);
			oRep.Add_TestDetails_InDevicesSummaryReport(i+1,gDeviceName,gTotalTestCasesCount);
			DevicePassedTests.put(gDeviceName,0);
			DeviceFailedTests.put(gDeviceName,0);
			cClient.setDevice(TempDevice[i]);
			
			DevInfo=cClient.getDevicesInformation();
			
			String[] TempVar=DevInfo.split("\n");
			
			for(String Line: TempVar){
				if(Line.contains(TempDevice[i].replace("adb:", "").replace("ios_app:",""))){
					DevInfo=Line;	
				}
			}
			
			Temp=DevInfo.split("manufacture=\"")[1];
			Name= Temp.substring(0, Temp.indexOf("\""));	
			Name = (Character.toString(Name.charAt(0)).toUpperCase()+Name.substring(1)).replace(" ", "_");
			
			Temp=DevInfo.split("model=\"")[1];
			Model=Temp.substring(0, Temp.indexOf("\"")).toUpperCase().replace(" ", "-");
			Temp=DevInfo.split("serialnumber=\"")[1];
			SerialNumber=Temp.substring(0, Temp.indexOf("\""));
			
			String name="";
			Temp=DevInfo.split("name=\"")[1];
			name= Temp.substring(0, Temp.indexOf("\""));	
						
			//DeviceName= Name+"_"+Model;
			DeviceName= name;
			
			try{
				if(TempDevice[i].contains("adb:")){
					cClient.setDevice("adb:"+DeviceName);
				}else if(TempDevice[i].contains("ios_app:")){
					//cClient.setDevice("ios_app:"+DeviceName);
					cClient.setDevice(TempDevice[i]);
					
				}
				
			}catch(Exception e){
				cClient.releaseDevice(TempDevice[i],true, true, false);
				cClient.addDevice(SerialNumber,DeviceName);
				if(TempDevice[i].contains("adb:")){
					cClient.setDevice("adb:"+DeviceName);
				}else if(TempDevice[i].contains("ios_app:")){
					//cClient.setDevice("ios_app:"+DeviceName);
					cClient.setDevice(TempDevice[i]);
				}
			}
			
			if(TempDevice[i].contains("adb:")){DeviceName="adb:"+DeviceName;}
			//else if(TempDevice[i].contains("ios_app:")){DeviceName="ios_app:"+DeviceName;}
			else if(TempDevice[i].contains("ios_app:")){DeviceName=TempDevice[i];}
			
			
			Devices[i]=DeviceName;	
					
			//Devices[i]=TempDevice[i];
			cClient.openDevice();
			
		}
		
		cClient.releaseClient();
		Client_Temp.releaseClient();
		
		
	}

	void AfterTestSuite() throws Exception{
		gTestSuiteEndTime= new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

		oRep.End_SuiteReport(this);
		oRep.EndDevicesSummaryReport(DevicePassedTests,DeviceFailedTests);
		oRep.Create_SummaryReport(this);
		Client_Temp.generateReport(false);
		Client_Temp.releaseClient();
		//oCmn.zipFolder(new File(GetGlobalProp("SummaryReportPath")).getParent(),new File(GetGlobalProp("SummaryReportPath")).getParent());

		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " +gReportPath+"\\FinalReport.html");
		Runtime.getRuntime().freeMemory();

		new File("default.xml").delete();
		if(gJAR_Execution){oCmn.DeleteFileOrFolder(gUtilitiesPath);}
	}

	void BeforTest(Lib_Global oGbl) throws Exception
	{				
		oGbl.gTestCaseStartTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());			
		oGbl.gDeviceName=oGbl.Device_Under_Test.replace("adb:", "").replace("ios_app:","").replace(" ","");
		oGbl.gTestCaseDetailedReportPath=oGbl.gReportDetailsFolderPath+"\\"+oGbl.gTestCaseName+"_Iteration"+oGbl.gTestCaseIterationCount+"_"+oGbl.gDeviceName+".html";
		oGbl.gTestCaseChartPath= oGbl.gReportDetailsFolderPath+"\\"+oGbl.gTestCaseName+"_Iteration"+oGbl.gTestCaseIterationCount+"_"+oGbl.gDeviceName+"_Chart.html";
		oGbl.gTestCaseMChartPath = oGbl.gReportDetailsFolderPath+"\\"+oGbl.gTestCaseName+"_Iteration"+oGbl.gTestCaseIterationCount+"_"+oGbl.gDeviceName+"_MChart.html";
		oGbl.oRep.Begin_DetailedReport(oGbl);
		
		oGbl.gTestStepStartTime= new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		oGbl.oRep.Add_TestStep_InDetailedReport("TESTDATA_INFO", "Iteration Starts for "+ oGbl.TD.get("DataSet"), "Started", "NA", "NA","", oGbl);

		oGbl.Client.setSpeed("Fast");
		//oGbl.Client.setShowImageInReport(false);
		//oGbl.Client.setShowPassImageInReport(false);
		//oGbl.Client.setShowReport(false);
		
		oGbl.Client.setDevice(oGbl.Device_Under_Test);
		oGbl.oRep.Add_TestStep_InDetailedReport("INFO", "Set the Device "+ oGbl.Device_Under_Test, "Set Device " + oGbl.Device_Under_Test + " was successful","NA", "NA","",oGbl);
		StartCounter(oGbl);
		if(oGbl.Pflag){
			oGbl.oRep.Begin_Chart(oGbl);
			oGbl.oRep.Begin_MChart(oGbl);
		}
		gAirCanadaActivity = TD.get("ApplicationPackage");
		oGbl.Client.applicationClearData(gAirCanadaActivity); 
		oGbl.Client.launch(gAirCanadaActivity, true, true);		
		
		oGbl.SetRecoveryScenarioErrorCheck = true;		
				
		oGbl.oRep.Add_TestStep_InDetailedReport("INFO", "Launch the application", "Application was successfully launched",GetCounter("Launch the Application",oGbl),GetCounterMemory("Launch the Application",oGbl),"",oGbl);
		if(DevicePassedTests.get(gDeviceName)<=0 || DeviceFailedTests.get(gDeviceName)<=0)
		{
			
			DeviceXYoffsets.put(gDeviceName+"_gSwipe50pY", oGbl.Client.p2cy(50));
			DeviceXYoffsets.put(gDeviceName+"_gSwipe10pY", oGbl.Client.p2cy(10));
			DeviceXYoffsets.put(gDeviceName+"_gSwipe25pY", oGbl.Client.p2cy(28));
			DeviceXYoffsets.put(gDeviceName+"_gSwipe75pY", oGbl.Client.p2cy(71));			
			DeviceXYoffsets.put(gDeviceName+"_gSwipe90pY", oGbl.Client.p2cy(90));
			
			DeviceXYoffsets.put(gDeviceName+"_gSwipe50pX", oGbl.Client.p2cx(50));
			DeviceXYoffsets.put(gDeviceName+"_gSwipe10pX", oGbl.Client.p2cx(10));
			DeviceXYoffsets.put(gDeviceName+"_gSwipe25pX", oGbl.Client.p2cx(28));
			DeviceXYoffsets.put(gDeviceName+"_gSwipe75pX", oGbl.Client.p2cx(71));
			DeviceXYoffsets.put(gDeviceName+"_gSwipe90pX", oGbl.Client.p2cx(90));
		}
		
		
		SetSwipeLocations(oGbl);
	}

	void AfterTest(Lib_Global oGbl)throws Exception
	{					
		oGbl.oRep.Add_TestStep_InDetailedReport("INFO", "Iteration End","Iteration End",GetCounter("End Iteration",oGbl),GetCounterMemory("End Iteration",oGbl),"",oGbl);
		if(oGbl.Pflag){
			oGbl.oRep.End_Chart(oGbl);
			oGbl.oRep.End_MChart(oGbl);
		}
		oGbl.gTC_Status=oGbl.oCmn.CheckResult(oGbl);			
		if(oGbl.gTC_Status.equalsIgnoreCase("PASS")){
			oGbl.gPassedTestCaseCount=oGbl.gPassedTestCaseCount+1;
			DevicePassedTests.put(oGbl.gDeviceName, DevicePassedTests.get(oGbl.gDeviceName)+1);
		}else{
			oGbl.gFailedTestCaseCount=oGbl.gFailedTestCaseCount+1;
			DeviceFailedTests.put(oGbl.gDeviceName, DeviceFailedTests.get(oGbl.gDeviceName)+1);
		}			
		oGbl.oRep.Add_TestCase_InSuiteReport(DevicePassedTests.get(oGbl.gDeviceName)+DeviceFailedTests.get(oGbl.gDeviceName),oGbl);
		PReport(oGbl,gTestCaseDetailedReportPath+"\\Performance.csv");
		oGbl.oRep.End_DetailedReport(oGbl);	
		oGbl.Client.generateReport(false);
		oGbl.Client.releaseClient();

	}

	void SetSwipeLocations(Lib_Global oGbl){
		
		oGbl.gSwipe50pY=DeviceXYoffsets.get(gDeviceName+"_gSwipe50pY");
		oGbl.gSwipe10pY=DeviceXYoffsets.get(gDeviceName+"_gSwipe10pY");
		oGbl.gSwipe25pY=DeviceXYoffsets.get(gDeviceName+"_gSwipe25pY");
		oGbl.gSwipe75pY=DeviceXYoffsets.get(gDeviceName+"_gSwipe75pY");
		oGbl.gSwipe90pY=DeviceXYoffsets.get(gDeviceName+"_gSwipe90pY");
		
		oGbl.gSwipe50pX=DeviceXYoffsets.get(gDeviceName+"_gSwipe50pX");
		oGbl.gSwipe10pX=DeviceXYoffsets.get(gDeviceName+"_gSwipe10pX");
		oGbl.gSwipe25pX=DeviceXYoffsets.get(gDeviceName+"_gSwipe25pX");
		oGbl.gSwipe75pX=DeviceXYoffsets.get(gDeviceName+"_gSwipe75pX");
		oGbl.gSwipe90pX=DeviceXYoffsets.get(gDeviceName+"_gSwipe90pX");
	}

	void WaitForAllTestThreadCompletion(ArrayList<Thread> TestsThread) throws Exception{
		for(int i=0; i<TestsThread.size(); i++) {
			TestsThread.get(i).join();			
		}
	}

	class MyThread implements Runnable {		
		Class<?> TestClass;
		Lib_Global oGbl;
		public MyThread(Class<?> testclass,Lib_Global ogbl) {

			TestClass=testclass;
			oGbl=ogbl;

		}
		public void run() {
			try {
				BeforTest(oGbl);	
				try{
					TestClass.getMethod("Test_"+TestClass.getSimpleName(),Lib_Global.class).invoke(TestClass.newInstance(),oGbl);
				}catch(Exception e){					
					oGbl.oRep.Add_TestStep_InDetailedReport("FAIL","Error Occured", e.toString(),"NA","NA","",oGbl);
					e.printStackTrace();
				}
								
				AfterTest(oGbl);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	Thread Parallel_Executor(Class<?> TestClass,Lib_Global oGbl) throws Exception{

		Runnable oRunnable=new MyThread(TestClass,oGbl);	
		Thread TestsThread =new Thread(oRunnable);
		TestsThread.start();
		return TestsThread;
	}

	int GetItreationCount(ArrayList<String> AL,String TestName) throws IOException {
		Multiset<String> multiset = HashMultiset.create(AL);
		return multiset.count(TestName);
	}
	
	public String GetCounter(String Desc,Lib_Global oGbl){
		try{
			String value = "";
			if(oGbl.Pflag){
				value = Client.getCounter("cpu");
				int intValue = Integer.parseInt(value);
				if(Desc.toLowerCase().contains("end iteration")){
					oRep.Addto_ChartFinal(intValue,Desc,oGbl);
				}else if(intValue > 0){				
					oRep.Addto_Chart(intValue,Desc,oGbl);							
				}
				return value;
			}else{
				return "";
			}
		}catch(Exception e){			
			e.printStackTrace();
			oGbl.Pflag = false;
			return "";			
		}
		
	}
	
	public String GetCounterMemory(String Desc, Lib_Global oGbl){
		try{
			String value = "";
			if(oGbl.Pflag){
				value = Client.getCounter("memory");
				int intValue = Integer.parseInt(value);
				if(Desc.toLowerCase().contains("end iteration")){
					oRep.Addto_MChartFinal(intValue,Desc,oGbl);
				}else if(intValue > 0){				
					oRep.Addto_MChart(intValue,Desc,oGbl);							
				}
				return value;
			}else{
				return "";
			}
		}catch(Exception e){			
			e.printStackTrace();
			oGbl.Pflag = false;
			return "";			
		}
		
	}
	
	public void StartCounter(Lib_Global oGbl){
		try{
			oGbl.Client.startMonitor("cpu");
			oGbl.Client.startMonitor("memory");
		}catch(Exception e){
			e.printStackTrace();
			oGbl.Pflag = false;
		}		
	}
	
	public void PReport(Lib_Global oGbl,String path){
		try{	
			Client.getMonitorsData(path+"\\Performance.csv");	
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
