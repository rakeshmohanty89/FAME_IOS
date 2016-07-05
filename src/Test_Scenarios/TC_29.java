package Test_Scenarios;

import Libraries.*;

public class TC_29 extends Lib_SeeTest{
	public void Test_TC_29(Lib_Global ogbl) throws Exception {
		try {
			SetGlobalObject(ogbl);
			oAppLib.Verify_AppOpened();

			if(oAppLib.Functionality_Select("Book Flight")){			
				oAppLib.SearchFlightRound_Trip(TD("Origin_airport"),TD("Destination_airport"),TD("Departure_dt"),TD("Arrival_dt"), TD("Adult_no"),TD("Child_no") ,TD("Youth_no"),"no");
				oAppLib.SelectFlight(TD("Origin_airport"),TD("Destination_airport"),TD("Sort_by"),TD("Flight_type"),0,TD("Flight_no"));
				oAppLib.SelectFligthclass(TD("Class"),TD("Class_type"),1);
				oAppLib.SelectFlight(TD("Origin_airport"),TD("Destination_airport"),TD("ASort_by"),TD("AFlight_type"),0,TD("AFlight_no"));
				oAppLib.SelectFligthclass(TD("AClass"),TD("AClass_type"),0);
				oAppLib.TravelOption(TD("Traveloptn"),true,true,1);			
				if(oGbl.Failureflag == 0){
					SwipeWhileNotFound(OR.FSRT_BTN_Continue,SwipeDown,5,true);
					Sleep(5000);
					oAppLib.SignIn("Yes",TD("Uname"),TD("Pwd"));
					Sleep(5000);						
				}			
				oAppLib.ValidateBooKSummary(TD("Origin_airport"),TD("Destination_airport"), "Roundtrip", "4");
				oAppLib.selectPassenger("1","no",true);
				oAppLib.AddPassengerDetails("1");
				oAppLib.selectPassenger("2","no",true);
				oAppLib.AddPassengerDetails("1");
				oAppLib.selectPassenger("3","no",true);
				oAppLib.AddPassengerDetails("1");
				oAppLib.selectPassenger("4","no",true);
				oAppLib.AddPassengerDetails("1");
				oAppLib.SelectPayment();
				oAppLib.paymentmethod("credit");
				oAppLib.CreditCardPayment(TD("Cardno"),TD("Month"), TD("year"), TD("name"), TD("Country"), TD("province"), TD("street"), TD("city"), TD("postalcode"));
				if(oGbl.Failureflag == 0){
					if(SwipeWhileNotFound(OR.BS_BTN_StartNewSearch,SwipeDown,5,false)){
						Click(OR.BS_BTN_Paynow);
					}else{
						Report("FAIL", "Pay now button", "Pay now button is not displayed",true);
						oGbl.Failureflag = 1;
					}
					Sleep(3000);

					String PNRno = oAppLib.ValidateConfirmation();
					if(!PNRno.equalsIgnoreCase(""))
					{
						String Parameters[] = {"VERIFY",PNRno,TD("Passenger1_Fname"),TD("Passenger1_Lname"),TD("Origin_airport"),"N/A",TD("Passenger1_Mobile"),TD("Passenger1_Email").toUpperCase().replace("@", "//"),TD("street").toUpperCase(),TD("city").toUpperCase(),TD("postalcode").toUpperCase(),TD("Flight_no"),TD("Departure_dt").substring(0,6).toUpperCase().replace(" ", "").replace("-", ""),TD("Origin_airport"),"SeatNo","N/A","N/A"};
						//String Parameters[] = {"VERIFY",PNRno,TD("Passenger1_Fname"),TD("Passenger1_Lname"),TD("Origin_airport")+TD("Destination_airport"),TD("Destination_airport")+TD("Origin_airport"),TD("Passenger1_Mobile"),TD("Passenger1_Email").toUpperCase().replace("@", "//"),TD("street").toUpperCase(),TD("city").toUpperCase(),TD("postalcode").toUpperCase(),TD("Flight_no"),TD("Departure_dt").substring(0,6).toUpperCase().replace(" ", "").replace("-", ""),TD("Origin_airport"),"SeatNo",TD("Arrival_dt").substring(0,6).toUpperCase().replace(" ", "").replace("-", ""),TD("AFlight_no")};
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
