package Test_Scenarios;

import Libraries.*;

public class TC_28 extends Lib_SeeTest{
	public void Test_TC_28(Lib_Global ogbl) throws Exception {
		try {
			SetGlobalObject(ogbl);
			oAppLib.Verify_AppOpened();

			if(oAppLib.Functionality_Select("Book Flight")){			
				oAppLib.searchFlightoneway(TD("Origin_airport"),TD("Destination_airport"),TD("Departure_dt"),TD("Adult_no"),TD("Child_no") ,TD("Youth_no"),"Yes");
				oAppLib.SelectFlight(TD("Origin_airport"),TD("Destination_airport"),TD("Sort_by"),TD("Flight_type"),1,TD("Flight_no"));
				oAppLib.SelectFligthclass(TD("Class"),TD("Class_type"),0);
				oAppLib.TravelOption(TD("Traveloptn"),false,false,1);			
				if(oGbl.Failureflag == 0){
					SwipeWhileNotFound(OR.FSRT_BTN_Continue,SwipeDown,5,true);
					Sleep(5000);
					oAppLib.SignIn("No",TD("Uname"),TD("Pwd"));
					Sleep(5000);						
				}			
				oAppLib.ValidateBooKSummary(TD("Origin_airport"),TD("Destination_airport"), "Oneway", "4");
				oAppLib.selectPassenger("1","No",false);
				oAppLib.AddPassengerDetails("1");
				oAppLib.selectPassenger("2","No",false);
				oAppLib.AddPassengerDetails("2");
				oAppLib.selectPassenger("3","No",false);
				oAppLib.AddPassengerDetails("3");
				oAppLib.selectPassenger("4","No",false);
				oAppLib.AddPassengerDetails("3");
				oAppLib.SelectPayment();
				oAppLib.paymentmethod("credit");
				oAppLib.CreditCardPayment(TD("Cardno"),TD("Month"), TD("year"), TD("name"), TD("Country"), TD("province"), TD("street"), TD("city"), TD("postalcode"));
				if(oGbl.Failureflag == 0){
					if(SwipeWhileNotFound(OR.BS_BTN_Paynow,SwipeDown,5,true)){
						Report("PASS", "Pay now button", "Pay now button is displayed and clicked",true);
					}else{
						Report("FAIL", "Pay now button", "Pay now button is not displayed",true);
					}
					Sleep(3000);

					String PNRno = oAppLib.ValidateConfirmation();
					if(!PNRno.equalsIgnoreCase(""))
					{
						String Parameters[] = {"VERIFY",PNRno,TD("Passenger1_Fname"),TD("Passenger1_Lname"),TD("Origin_airport"),"N/A",TD("Passenger1_Mobile"),TD("Passenger1_Email").toUpperCase().replace("@", "//"),TD("street").toUpperCase(),TD("city").toUpperCase(),TD("postalcode").toUpperCase(),TD("Flight_no"),TD("Departure_dt").substring(0,6).toUpperCase().replace(" ", "").replace("-", ""),TD("Origin_airport"),"SeatNo","N/A","N/A"};
						//String Parameters[] = {"VERIFY",PNRno,TD("Passenger1_Fname"),TD("Passenger1_Lname"),TD("Origin_airport")+TD("Destination_airport"),"N/A",TD("Passenger1_Mobile"),TD("Passenger1_Email").toUpperCase().replace("@", "//"),TD("street").toUpperCase(),TD("city").toUpperCase(),TD("postalcode").toUpperCase(),TD("Flight_no"),TD("Departure_dt").substring(0,6).toUpperCase().replace(" ", "").replace("-", ""),TD("Origin_airport"),"SeatNo","N/A","N/A"};
						oAppLib.triggerMainFrameValidation(Parameters);
						oAppLib.reportMainFrameValidationResults();
						Report("PASS", "PNR Mainframe Validation", "PNR Mainframe Validation was successful",false);
						oAppLib.CancelBooking(PNRno);
					}
					else
					{
						Report("FAIL", "PNR Mainframe Validation", "Not able to perform PNR Mainframe Validation, as PNR is not generated",false);
					}
				}			
			}
		} catch (Exception e) {
			Report("FAIL","Error Occured",e.toString(), false);
			oGbl.errFlag = 1;
			e.printStackTrace();
		} 

	}


}
