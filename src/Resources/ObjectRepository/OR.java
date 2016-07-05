package Resources.ObjectRepository;

import java.util.Hashtable;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import Libraries.Lib_Common;


public class OR extends OR_Helper{

	public Hashtable<String, String> HTCurrent=GetValue(new Lib_Common().GetCommonTD(System.getProperty("user.dir")+"\\Organizer.xls").get("Local"));

	private String GetLocalText(String string) {return HTCurrent.get(string);}

	//OR Converter1
	private String[] OC(String value1,String value2){
		String[] Values=new String[4];
		Values[0]=value1;
		Values[1]="Native";
		Values[2]=value2;
		Values[3]="0";

		return Values;
	}

	//OR Converter2
	private String[] OC(String value1,String value2,String value3){
		String[] Values=new String[5];
		Values[0]=value1;
		Values[1]="Native";
		Values[2]=value2;
		Values[3]="0";
		Values[4]=value3;

		return Values;
	}

	//OR Converter3
	private String[] OC(String value1,String value2,String Obj1Index, String value3, String Obj2Index){
		String[] Values=new String[6];
		Values[0]=value1;
		Values[1]="Native";
		Values[2]=value2;
		Values[3]=Obj1Index;
		Values[4]=value3;
		Values[5]=Obj2Index;

		return Values;
	}

	//"xpath=//*[@id='text_email_address']"
	//"xpath=//*[@id='" + ID_text_email_address + "']"

	//xpath=//*[@text='Air Canada mobile+']
	//xpath=//*[@text='" + GetLocalText(TEXT_air_canada_mobile_plus) + "']





	public String[] AC_Text_Dynamic = OC("AC app Dynamic Text","xpath=//*[contains(@text,'????')]");
	public String[] AC_Text_Dynamictop = OC("AC app Dynamic Text","xpath=//*[contains(@text,'????') and@top='true']");
	

	//xpath=//*[@accessibilityIdentifier='loaderLabel']
	public String[] AC_ScreenLoader = OC("AC Screen Loader","xpath=//*[@id='"+ID_marker_progress+"' and @class='android.widget.ProgressBar']", "xpath=//*[@accessibilityIdentifier='loaderLabel']");
	public String[] AC_ScreenLoaderName = OC("AC Screen Loader Name","xpath=//*[@id='"+ID_marker_progress+"' and @class='android.widget.ProgressBar']//following-sibling::*", "xpath=//*[@accessibilityIdentifier='loaderLabel']");

	public String[] AC_ErrorPopUp = OC("AC Error Pop Up","xpath=//*[@text='"+GetLocalText(TEXT_error)+"' and @class='com.aircanada.view.FontTextView' and @hidden='false' and @onScreen='true']");
	public String[] AC_ErrorPopUpText = OC("AC Error Pop Up Text","xpath=//*[@text='"+GetLocalText(TEXT_error)+"' and @class='com.aircanada.view.FontTextView' and @hidden='false' and @onScreen='true']//../following-sibling::*");
	public String[] AC_ErrorPopUpOkButton = OC("AC Error Pop Up Ok Button","xpath=//*[@id='"+ID_button_close+"']");

	public String[] AC_ErrorMsgImg = OC("AC Error message image","xpath=//*[@id='"+ID_error_image+"' and @hidden='false' and @onScreen='true']");
	public String[] AC_ErrorMsgText = OC("AC Error message Text","xpath=//*[@id='"+ID_error_image+"' and @hidden='false' and @onScreen='true']//following-sibling::*");
	public String[] AC_NetworkNotAvailableErrorMsg = OC("Network Not Available Error message","xpath=//*[@text='"+GetLocalText(TEXT_network_not_available_title)+"' and @hidden='false' and @onScreen='true']");
	public String[] AC_NetworkNotAvailableErrorMsgText = OC("Network Not Available Error message text","xpath=//*[@text='"+GetLocalText(TEXT_network_not_available_title)+"' and @hidden='false' and @onScreen='true']//..//following-sibling::*");

	public String[] LP_Text_Skip = OC("Skip text","xpath=//*[@text='"+GetLocalText(TEXT_skip)+"']");
	public String[] LP_Text_Terms_Condition = OC("Terms and Condition","xpath=//*[@text='"+GetLocalText(TEXT_skip)+"']","xpath=//*[@text='Terms & conditions']");
	public String[] LP_BTN_Agree_Terms_Condition = OC("Terms and Condition Agree button","xpath=//*[@text='"+GetLocalText(TEXT_skip)+"']","xpath=//*[@text='Agree']");
	public String[] LP_BTN_Agree = OC("Terms and Condition Agree button","xpath=//*[@text='"+GetLocalText(TEXT_skip)+"']","xpath=//*[@text='Agree' and @class='UILabel']");
	
	public String[] LP_Text_tutorial = OC("Tutorial page","xpath=//*[@text='"+GetLocalText(TEXT_welcome_ex)+"']");
	public String[] LP_BTN_Skip = OC("Push Notification","xpath=//*[@id='"+ID_action_skip+"']");
	public String[] LP_BTN_Iagree = OC("I agree button","xpath=//*[@id='"+ID_button_call+"']");
	//	public String[] LP_BTN_MenuNavUp = OC("Menu button","xpath=//*[@contentDescription='Navigate up']");
	public String[] LP_BTN_Menu = OC("Menu button","xpath=//*[@contentDescription='main menu']","xpath=//*[@accessibilityIdentifier='icon-navbar-hamburger']");	
	public String[] LP_Text_Welcome = OC("Welcome text","xpath=//*[@id='"+ID_text_welcome+"']");
	public String[] LP_Text_Welcome1 = OC("Welcome text","xpath=//*[@text='"+GetLocalText(TEXT_hello_second_line)+"']");
	public String[] LP_Text_Welcome2 = OC("Welcome text","xpath=//*[@text='"+GetLocalText(TEXT_hello_third_line)+"']");
	public String[] LP_Text_Tile = OC("Tile","xpath=//*[@text='"+GetLocalText(TEXT_hello_third_line)+"']","xpath=//*[@class='ACApp.TileCell']");
	public String[] LP_BTN_Book_flight = OC("Book flight button","xpath=//*[@id='"+ID_button_book_flight+"']","xpath=//*[@accessibilityLabel='Book a trip']");
	public String[] LP_BTN_FlightStatus = OC("Flight Status button","xpath=//*[@id='"+ID_button_search_flight+"']","xpath=//*[@accessibilityLabel='Flight Status']");
	public String[] LP_BTN_Checkin = OC("Checkin button","xpath=//*[@id='"+ID_button_checkin+"']","xpath=//*[@accessibilityLabel='Check in']");
	public String[] LP_Text_signin = OC("Sign in Text","xpath=//*[@text='"+GetLocalText(TEXT_login)+"' and @onScreen='true']","xpath=//*[@text='Sign in']");
	public String[] LP_Text_signIn = OC("Sign in Text","xpath=//*[@text='"+GetLocalText(TEXT_login)+"' and @onScreen='true']");
	public String[] LP_Text_LoggedInAs = OC("Sign in Text","xpath=//*[@text='"+GetLocalText(TEXT_logged_in_as)+"']");
	public String[] LP_Text_UserName = OC("User name","xpath=//*[@id='"+ID_name_text+"']");


	public String[] LP_BTN_OK = OC("OK button on ACM+ update popup ","xpath=//*[@id='button_close']");
	public String[] LP_Text_Update = OC("ACM popup update text","xpath=//*[contains(@text,'update')]");
	public String[] LP_Text_UpgradeCRT = OC("Please upgrade (CRT)","xpath=//*[@text='Please upgrade (CRT)']");
	public String[] LP_BTN_UpgradeCRT = OC("Please upgrade (CRT) Skip Button","xpath=//*[@id='"+ID_button_negative+"']","xpath=//*[@accessibilityLabel='Skip']");


	//Menuoption_MO done with IOS
	public String[] MO_BTN_Signin = OC("Sign in button","xpath=//*[@id='button_sign_in']","xpath=//*[@text='Sign in' and @onScreen='true']");
	public String[] MO_Text_Loggedin = OC("Sign in button","xpath=//*[@id='text_hello']","xpath=//*[@accessibilityLabel='Link Aeroplan']");
	public String[] MO_BTN_ScanCard = OC("Scan Areoplan card button","xpath=//*[@id='menu_scan_card']");
	public String[] MO_BTN_Home = OC("home button","xpath=//*[@id='"+ID_menu_home+"']","xpath=//*[@accessibilityLabel='Home']");
	public String[] MO_BTN_YourFlight = OC("Your Flight button","xpath=//*[@text='"+GetLocalText(TEXT_your_flights_menu)+"']","xpath=//*[@accessibilityLabel='Your flights']");
	public String[] MO_BTN_Trip = OC("Book a trip button","xpath=//*[@id='"+ID_menu_book_trip+"']","xpath=//*[@accessibilityLabel='Book a trip']");
	public String[] MO_BTN_Status = OC("Flight Status button","xpath=//*[@id='"+ID_menu_flight_status+"']","xpath=//*[@text='Flight Status' and @accessibilityIdentifier='headerLabel1']");
	public String[] MO_BTN_Checkin = OC("Checkin button","xpath=//*[@id='"+ID_menu_checkin+"']","xpath=//*[@accessibilityLabel='Check in']");
	public String[] MO_BTN_MyTrips = OC("My Trips button","xpath=//*[@id='"+ID_menu_my_trips+"']");
	public String[] MO_BTN_ProfileSetting = OC("Profile settings button","xpath=//*[@id='"+ID_menu_profile_settings+"' and @onScreen='true' and @hidden='false']","xpath=//*[@accessibilityLabel='Settings']");
	//SignIn
	public String[] SignIn_Text_signin = OC("Sign in Text","xpath=//*[@text='"+GetLocalText(TEXT_login)+"']");
	//	public String[] SignIn_BTN_Backbutton = OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	public String[] SignIn_Text_AirCanada = OC("Air Canada Text ","xpath=//*[@text='"+GetLocalText(TEXT_mobile_plus)+"']");
	public String[] SignIn_Label_Email = OC("Email Text","xpath=//*[@text='"+GetLocalText(TEXT_email)+"']");
	public String[] SignIn_Textbox_Email = OC("Email Textbox","xpath=//*[@id='"+ID_text_email_address+"']", "0", "xpath=//*[@class='UITextField']", "0");
	public String[] SignIn_Label_Password = OC("Password Text","xpath=//*[@text='"+GetLocalText(TEXT_hint_password)+"']","xpath=//*[@accessibilityLabel='Choose password']");
	public String[] SignIn_Textbox_Password = OC("Password Textbox","xpath=//*[@id='"+ID_text_password_validation+"']",  "0", "xpath=//*[@accessibilityIdentifier='passwordTextField']", "0");
	public String[] SignIn_Link_ForgotPassword = OC("Forgot Password Link","xpath=//*[@text='"+GetLocalText(TEXT_forgot_my_password)+"']");
	public String[] SignIn_Link_ForgotPassword_arrow = OC("Forgot Password Arrow button","xpath=//*[@class='android.widget.ImageView']");
	public String[] SignIn_BTN_Signin = OC("SignIn button","xpath=//*[@id='"+ID_button_login+"']", "xpath=//*[@accessibilityIdentifier='signInButton' and @onScreen='true']");
	public String[] SignIn_BTN_continue_As_Guest = OC("Continue as guest option","xpath=//*[@id='"+ID_button_continue_as_guest+"']","xpath=//*[@text='"+GetLocalText(TEXT_continue_as_guest)+"']");
	public String[] SignIn_Text_InvalidCredentials = OC("SignIn_Text_InvalidCredentials","xpath=//*[@text='"+GetLocalText(TEXT_not_logged_in_invalid_credentials)+"']");
	public String[] SignIn_Text_InvalidEmaiId = OC("SignIn_Text_InvalidEmaiId","xpath=//*[@text='"+GetLocalText(TEXT_profile_not_created)+"']");
	public String[] SignIn_Text_InvalidEmail = OC("Invalid Email id","xpath=//*[@text='"+GetLocalText(TEXT_email_is_incorrect)+"']");
	public String[] SignIn_Text_InstructionAlert = OC("SignIn_Text_InstructionAlert for account activation","xpath=//*[@id='"+ID_text_snackbar_description+"']","xpath=//*[@accessibilityIdentifier='toastSubtitleLabel']");

	//
	//Forget Password Screen
	public String[] SignIn_BTN_RequestPassword = OC("Request Password Reset","xpath=//*[@id='"+ID_button_request_reset+"']","xpath=//*[@class='ACToolkit.ACButton' and @accessibilityLabel='Email password']");

	//BookaTrip_BAT done with IOS
	//public String[] BAT_Text_title = OC("Page title","xpath=//*[@text='"+GetLocalText(TEXT_book_trip)+"']","xpath=//*[@accessibilityLabel='Book a trip']");
	public String[] BAT_Text_title = OC("Page title","xpath=//*[@text='"+GetLocalText(TEXT_book_trip)+"']");
	public String[] BAT_BTN_RoundTrip = OC("Round trip option button","xpath=//*[@id='"+ID_button_round+"']","xpath=//*[@text='"+GetLocalText(TEXT_roundtrip)+"']");
	public String[] BAT_BTN_OneWay = OC("One way option button","xpath=//*[@id='"+ID_button_one+"']","xpath=//*[@text='"+GetLocalText(TEXT_one_way_trip)+"']");
	public String[] BAT_Label_from = OC("Source Label","xpath=//*[@text='"+GetLocalText(TEXT_from)+"']");
	//public String[] BAT_TextBox_Origin = OC("Source search textbox","xpath=//*[@id='"+ID_text_view_origin+"']","xpath=//*[@accessibilityLabel='Select origin']");
	public String[] BAT_TextBox_Origin = OC("Source search textbox","xpath=//*[@id='"+ID_text_view_origin+"']","xpath=//*[contains(@text,'????')]");
	public String[] BAT_Label_Destination = OC("Destination Label","xpath=//*[@text='"+GetLocalText(TEXT_to)+"']");
	//public String[] BAT_TextBox_Destination = OC("Destination search textbox","xpath=//*[@id='"+ID_text_destination+"']","xpath=//*[@accessibilityLabel='Select destination']");
	public String[] BAT_TextBox_Destination = OC("Destination search textbox","xpath=//*[@id='"+ID_text_destination+"']","xpath=//*[contains(@accessibilityLabel,'????')]");
	public String[] BAT_TextBox_SelectDate = OC("Select travel date","xpath=//*[@id='"+ID_dates_image+"']","xpath=//*[@accessibilityLabel='Select dates']");
	public String[] BAT_Label_NoOfPassenger = OC("No of passenger Label","xpath=//*[@text='"+GetLocalText(TEXT_passengers)+"']");
	//public String[] BAT_TextBox_NoOfPassenger = OC("No of Passenger textbox","xpath=//*[@id='"+ID_text_passengers+"']","xpath=//*[@accessibilityLabel='1 adult']");
	public String[] BAT_TextBox_NoOfPassenger = OC("No of Passenger textbox","xpath=//*[@id='"+ID_text_passengers+"']", "0", "xpath=//*[@class='ACToolkit.ACUILabel']", "7");
	public String[] BAT_TextBox_Promocode = OC("Promocode textbox","xpath=//*[@hint='Optional']","xpath=//*[@accessibilityLabel='Optional']");
	public String[] BAT_BTN_Search = OC("Search Button","xpath=//*[@text='"+GetLocalText(TEXT_search)+"']");
	public String[] BAT_BTN_RecentSearch = OC("Recent Search","xpath=//*[@id='"+ID_action_recent+"']","xpath=//*[@accessibilityLabel='Recents']");
	public String[] BAT_List_Option = OC("Suggestion item","xpath=//*[@id='"+ID_shortName+"']","xpath=//*[@accessibilityIdentifier='airport_code_????']");
	public String[] BAT_BTN_RoundTripSelected = OC("Round trip option button","xpath=//*[@id='"+ID_button_round+"' and @textColor='0xFFFFFF']","xpath=//*[@accessibilityLabel='Round trip' and @textColor='0x4B4E54']");
	public String[] BAT_BTN_OneWaySelected = OC("One way option button","xpath=//*[@id='"+ID_button_one+"' and @textColor='0xFFFFFF']","xpath=//*[@accessibilityLabel='One way' and @textColor='0x4B4E54']");
	public String[] BAT_Label_Departuredt = OC("Departure Date","xpath=//*[@id='"+ID_departureDate+"']", "0", "xpath=//*[@class='ACToolkit.ACUILabel']", "5");
	public String[] BAT_Label_Returndt = OC("Return Date","xpath=//*[@id='"+ID_text_returnedDate+"']", "0", "xpath=//*[@class='ACToolkit.ACUILabel']", "5");


	//Source_Airport_SA -- IOS updated

	public String[] SA_Text_Heading = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_from)+"']","xpath=//*[@accessibilityIdentifier='screen-header-From']");
	

	
	

	
	public String[] SA_Textbox_Searchbox = OC("source airport searchbox","xpath=//*[@id='"+ID_search_edit_text+"']","xpath=//*[@accessibilityIdentifier='searchTextField']");

	public String[] SA_Label_Recent = OC("Recent search label","xpath=//*[@text='"+GetLocalText(TEXT_recent_airports)+"']");
	public String[] SA_Text_airportselection = OC("Select from listed airport","xpath=//*[@id='"+ID_shortName+"']","xpath=//*[@accessibilityIdentifier='airport_code_????']");


	//Destination_Airports_DA --- IOS updated
	public String[] DA_Text_Heading = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_to)+"']");
	public String[] DA_Textbox_Searchbox = OC("source airport searchbox","xpath=//*[@id='"+ID_search_edit_text+"']","xpath=//*[@accessibilityIdentifier='searchTextField']");
	public String[] DA_Label_Recent = OC("Recent search label","xpath=//*[@text='"+GetLocalText(TEXT_recent_airports)+"']");
	public String[] DA_Text_airportselection = OC("Select from listed airport","xpath=//*[@id='"+ID_shortName+"']","xpath=//*[@accessibilityIdentifier='a_airport_name_????']");

	//No_Of_Pasenger_NOP
	public String[] NOP_Title_Header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"']","xpath=//*[@accessibilityLabel='Passengers']");
	public String[] NOP_Text_Passenger = OC("Page Header Text","xpath=//*[@text='"+GetLocalText(TEXT_passengers)+"']");
	public String[] NOP_Text_noOfPassengertoselect = OC("no of passenger to select","xpath=//*[@text='"+GetLocalText(TEXT_select_passengers_number)+"']");
	public String[] NOP_Text_Adult = OC("No of adult passenger","xpath=//*[@id='"+ID_title+"']");
	public String[] NOP_Text_youth = OC("No of Youth passenger","xpath=//*[@id='"+ID_title+"']","1", "", "");
	public String[] NOP_Text_Children = OC("No of Child passenger","xpath=//*[@id='"+ID_title+"']","2", "", "");
	public String[] NOP_BTN_Decradultcount = OC("Decrease adult count","xpath=//*[@id='"+ID_image_minus+"']","xpath=//*[@accessibilityIdentifier='removePassengerButton0']");
	public String[] NOP_BTN_Incradultcount = OC("Increase adult count","xpath=//*[@id='"+ID_image_plus+"']","xpath=//*[@accessibilityIdentifier='addPassengerButton0']");
	public String[] NOP_BTN_Decryouthcount = OC("Decrease youths count","xpath=//*[@id='"+ID_image_minus+"']","xpath=//*[@accessibilityIdentifier='removePassengerButton1']");
	public String[] NOP_BTN_Incryouthcount = OC("Increase youths count","xpath=//*[@id='"+ID_image_plus+"']","xpath=//*[@accessibilityIdentifier='addPassengerButton1']");
	public String[] NOP_BTN_DecrChildcount = OC("Decrease Children count","xpath=//*[@id='"+ID_image_minus+"']","xpath=//*[@accessibilityIdentifier='removePassengerButton2']");
	public String[] NOP_BTN_IncrChildcount = OC("Increase Children count","xpath=//*[@id='"+ID_image_plus+"']","xpath=//*[@accessibilityIdentifier='addPassengerButton2']");
	public String[] NOP_Text_Booking_more_people = OC("Info on booking for infant and more people","xpath=//*[@text='"+GetLocalText(TEXT_passengers_info)+"']");
	public String[] NOP_Link_More = OC("More info link","xpath=//*[@id='"+ID_button_more+"']");
	public String[] NOP_BTN_confirm = OC("Confirm button","xpath=//*[@id='"+ID_button_confirm+"']","xpath=//*[@accessibilityLabel='Confirm']");
	//No_Of_Pasenger_MoreInfo_NOPM
	public String[] NOPM_BTN_Close = OC("Close button","xpath=//*[@id='"+ID_button_close+"']");
	public String[] NOPM_BTN_Call = OC("Call button","xpath=//*[@id='"+ID_button_call+"']");


	//Dates
	public String[] FindFlightDate_TXT_Box = OC("FindFlightDate_TXT_Box","xpath=//*[@id='"+ID_label_flight_date+"']","xpath=//*[@accessibilityIdentifier='icon-calendar']");
	public String[] Dates_Title_Header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"']","xpath=//*[@accessibilityLabel='Flight date']");

	public String[] Dates_Text_DepatureDate = OC("Depature date text","xpath=//*[@id='"+ID_text_departure_date+"']");
	public String[] Dates_Text_ReturnDate = OC("Return date text","xpath=//*[@id='"+ID_text_return_date+"']");
	public String[] Dates_Text_Monthandyear = OC("Month and Year displayed","xpath=//*[@id='"+ID_text_month+"']");
	public String[] Dates_Text_Date = OC("Date text","xpath=//*[@text='????'and @textColor='0x727780' and @onScreen='true']", "xpath=//*[@text='????'and @textColor='0x4B4E54' and @onScreen='true']");
	public String[] Dates_Text_SelectedDate = OC("Selected Date text","xpath=//*[@text='????'and @textColor='0xFFFFFF' and @onScreen='true' and @hidden = 'false']");
	public String[] Dates_Text_SelectedMonth = OC("Month text","xpath=//*[contains(@text,'????') and @onScreen='true' and @hidden='false']");
	public String[] Dates_Text_Month = OC("month name","xpath=//*[@id='weekday_month' and @hidden='false' and @top='true']");
	public String[] Dates_BTN_ClearDate = OC("Clear dates button","xpath=//*[@id='"+ID_button_clear_dates+"']");
	public String[] Dates_BTN_Confirm = OC("Confirm button","xpath=//*[@id='"+ID_button_confirm_dates+"' and  @top='true']", "0", "xpath=//*[@accessibilityLabel='Confirm' and  @top='true']", "0");
	//	public String[] Dates_BTN_Back = OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	//Depature_Flight_DF
	public String[] DF_BTN_RefineSearch = OC("Refine search button","xpath=//*[@id='"+ID_action_refine_search+"']", "xpath=//*[@text='"+GetLocalText(TEXT_options)+"']");
	public String[] DF_Title_Header = OC("Page Headers","xpath=//*[@id='"+ID_toolbar_title+"']");
	//	public String[] DF_BTN_Back = OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	public String[] DF_Text_Tripdescription = OC("Trip description","xpath=//*[@id='"+ID_trip_description+"']", "xpath=//*[contains(@accessibilityLabel,'????')]");
	public String[] DF_Text_Sortby = OC("Sort by text","xpath=//*[@text='"+GetLocalText(TEXT_sort_by)+"']");
	public String[] DF_BTN_time = OC("Sort by Time","xpath=//*[@text='"+GetLocalText(TEXT_time_of_day)+"']");
	public String[] DF_BTN_Price = OC("Sort by Price","xpath=//*[@text='"+GetLocalText(TEXT_price)+"']");
	public String[] DF_BTN_Duration = OC("Sort by Duration","xpath=//*[@text='"+GetLocalText(TEXT_duration)+"']");
	public String[] DF_Text_Directflights = OC("Direct flights text","xpath=//*[@text='"+GetLocalText(TEXT_direct_flights)+"']");
	public String[] DF_Text_DatenotSelected = OC("Date not Selected","xpath=//*[contains(@text,'????') and @textColor='0x494E54' and @onScreen='true']", "xpath=//*[contains(@text,'????') and @onScreen='true' and @textColor='0x4B4E54']");
	public String[] DF_Text_date = OC("Date","xpath=//*[contains(@text,'????') and @onScreen='true']");

	public String[] DF_Text_DateSelected = OC("Date Selected","xpath=//*[contains(@text,'????') and @textColor='0xFFFFFF' and @onScreen='true']", "xpath=//*[contains(@text,'????') and @onScreen='true' and @textColor='0xFFFFFF']");
	public String[] DF_Text_Depaturetime = OC("Departure time of the flight","xpath=//*[@id='"+ID_fare_time+"']", "xpath=//*[contains(@text,':') and contains(@text,'–')]");
	public String[] DF_Text_fareprice = OC("Fare price of the flight","xpath=//*[@id='"+ID_fare_price+"']","0", "xpath=//*[starts-with(@text,'$') and @onScreen='true' and @top='true']","0");
	public String[] DF_Text_connectiontime = OC("duration of the flight travel","xpath=//*[@id='"+ID_connection_time+"']", "xpath=//*[contains(@text,'h ') and contains(@text,'m')]");
	public String[] DF_Text_SegmentCode =OC("Segmentcode","xpath=//*[@id='"+ID_segment_code+"']");
	public String[] DF_Text_FlightNumber = OC("Flight number","xpath=//*[@id='"+ID_flight_number_textView+"']", "xpath=//*[starts-with(@accessibilityLabel,'AC')]");
	public String[] DF_Text_FlightNo = OC("Flight number text","xpath=//*[@text='????' and @hidden='false']","xpath=//*[contains(@text,'????') and @hidden='false']");
	public String[] DF_Text_contains = OC("Text","xpath=//*[contains(@text,'????') and @hidden='false' and @onScreen='true']");
	public String[] DF_Text_SortSummary = OC("Sort Summary Text","xpath=//*[@id='sort_summary']");
	public String[] DF_Text_connectioFlights = OC("Connection flights text","xpath=//*[@text='"+GetLocalText(TEXT_connecting_flights)+"']");
	public String[] DF_Text_noconnectioFlights = OC("Number of Connection flights text","xpath=//*[@id='"+ID_number_collections+"' and @ onScreen='true']", "xpath=//*[starts-with(@text,'+') and contains(@text,'connection') and @onScreen='true']");


	//Refine_Search_RS
	public String[] RS_Title_Header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"']");
	public String[] RS_Text_Cabin = OC("Cabin text","xpath=//*[@text='"+GetLocalText(TEXT_cabin)+"']");
	public String[] RS_BTN_EconomyClass = OC("Economy class option","xpath=//*[@text='"+GetLocalText(TEXT_economy)+"']");
	public String[] RS_BTN_PremiumEconomyClass = OC("Premium Economy class option","xpath=//*[@text='"+GetLocalText(TEXT_premium_economy)+"']");
	public String[] RS_BTN_BusinessEconomyClass = OC("Business class option","xpath=//*[@text='"+GetLocalText(TEXT_business)+"']");
	public String[] RS_Text_timeofDay = OC("Time of day text","xpath=//*[@text='"+GetLocalText(TEXT_time_of_day)+"']");
	public String[] RS_BTN_MorningFlight = OC("Morning option","xpath=//*[@text='"+GetLocalText(TEXT_morning)+"']");
	public String[] RS_Text_Morning_time_interval = OC("Time interval for morning","xpath=//*[@text='"+GetLocalText(TEXT_morning_hours)+"']");
	public String[] RS_BTN_AfternoonFlight = OC("Afternoon option","xpath=//*[@text='"+GetLocalText(TEXT_afternoon)+"']");
	public String[] RS_Text_Afternoon_time_interval = OC("Time interval for Afternoon","xpath=//*[@text='"+GetLocalText(TEXT_afternoon_hours)+"']");
	public String[] RS_BTN_EveningFlight = OC("Evening option","xpath=//*[@text='"+GetLocalText(TEXT_evening)+"']");
	public String[] RS_Text_Evening_time_interval = OC("Time interval for Evening","xpath=//*[@text='"+GetLocalText(TEXT_evening_hours)+"']");
	public String[] Rs_BTN_Checkbox = OC("Checkbox","xpath=//*[@class='android.widget.CheckBox']");
	//Flight_Status_FS
	public String[] FS_Title_Header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_find_flights)+"']","xpath=//*[@accessibilityIdentifier='screen-header-Find a flight']");
	//	public String[] FS_BTN_Back = OC("Back Button","xpath=//*[@contentDescription='back']");
	public String[] FS_BTN_FligthNo = OC("Search by Flight number option","xpath=//*[@id='"+ID_button_by_flight_number+"']","xpath=//*[@text='Flight number']");
	public String[] FS_BTN_FligthNoSelected = OC("Search by Flight number option is selected","xpath=//*[@id='"+ID_button_by_flight_number+"' and @textColor='0xFFFFFF']","xpath=//*[@text='Flight number' and @textColor='0x4B4E54']");
	public String[] FS_BTN_City = OC("Search by City option","xpath=//*[@id='"+ID_button_by_city+"']","xpath=//*[@text='Route']");
	public String[] FS_BTN_PNR = OC("Search by PNR option","xpath=//*[@id='"+ID_button_by_pnr+"']","xpath=//*[@text='Booking reference']");
	public String[] FS_BTN_Search = OC("Search button","xpath=//*[@id='"+ID_button_search+"']","xpath=//*[@text='Search']");
	public String[] FS_TextBox_PNRNO = OC("FS_TextBox_PNRNO","xpath=//*[@id='"+ID_booking_reference_editText+"']");
	public String[] FS_TextBox_PassengerName = OC("FS_TextBox_PassengerName","xpath=//*[@id='"+ID_passenger_editText+"']");
	//Flight_Status_By_FlighNo_FSBFN
	public String[] FSBFN_Title_Header = OC("Find a Flight title","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_find_flights)+"']","xpath=//*[@text='Find a flight']");
	public String[] FSBFN_TextBox_Flightnumber = OC("FlightnumberTextbox","xpath=//*[@text='"+GetLocalText(TEXT_flight_number)+"']","FSBFN_TextBox_Flightnumber");
	public String[] FSBFN_TextBox_Prefix = OC("FlightnumberTextbox prefix","xpath=//*[@id='"+ID_label_ac_prefix+"']","xpath=//*[@text='Flight number' and @class='UILabel']");
	public String[] FSBFN_TextBox_Flightno = OC("Fligth number textbox","xpath=//*[@id='"+ID_flight_number_editText+"']","xpath=//*[@class='UITextField']");
	public String[] FSBFN_TextBox_Calendar = OC("Calendar textbox","xpath=//*[@id='"+ID_flight_date_textView+"']","xpath=//*[@accessibilityIdentifier='icon-calendar']");
	/*public String[] FSBFN_Text_Month = OC("Month of the date","xpath=//*[@id='"+ID_month+"']");
	public String[] FSBFN_Text_Day = OC("Day of the date","xpath=//*[@id='"+ID_day+"']");
	public String[] FSBFN_Text_Year = OC("Year of the date","xpath=//*[@id='"+ID_year+"']");

	public String[] FSBFN_Text_datePicker = OC("Date picker","xpath=//*[@id='"+ID_numberpicker_input+"']");*/
	public String[] FSBFN_BTN_Search = OC("Search Button","xpath=//*[@text='"+GetLocalText(TEXT_abc_searchview_description_search)+"']","xpath=//*[@text='Search']");
	//Results
	public String[] Result_Title_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_flight_results)+"']","xpath=//*[@accessibilityIdentifier='screen-header-Results']");
	public String[] Result_page_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_upcoming)+"']");
	public String[] Result_Text_FlightNumber = OC("Flight number text","xpath=//*[@id='"+ID_textview_flight_number+"']","xpath=//*[@class='UIView' and @width>0 and ./*[@class='UIView']  and ./*[@class='ACToolkit.ACHeaderIconImageView']]");
	public String[] Result_Text_FlightNumber1 = OC("Flight number text","xpath=//*[@id='"+ID_textview_flight_number+"']","1", "xpath=//*[@class='UIView' and @width>0 and ./*[@class='UIView']  and ./*[@class='ACToolkit.ACHeaderIconImageView']]", "1");
	public String[] Result_Text_Date = OC("Date","xpath=//*[@id='"+ID_departure_date_textView+"']");
	public String[] Result_BTN_AddtoTrackedFlight = OC("Add to tracked flight button","xpath=//*[@id='"+ID_add_to_tracked_flights_button+"' and @onScreen='true' and @hidden='false']","xpath=//*[@text='Add to tracked flights' and @onScreen='true']");
	//	public String[] Result_BTN_Back = OC("Back Button","xpath=//*[@contentDescription='Navigate up']");
	public String[] Result_Text_Flight = OC("Flight details","xpath=//*[@class='android.support.v7.widget.CardView']");
	public String[] Result_Text_DepAirport = OC("Flight details","xpath=//*[@id='"+ID_textview_departure_airport+"' and @onScreen='true']");
	public String[] Result_Text_NoOfPassenger = OC("Result_Text_NoOfPassenger","xpath=//*[@id='"+ID_text_passengers_number+"' and @onScreen='true']", "xpath=//*[contains(@text,'Passenger') and@onScreen='true']");
	public String[] Result_BTN_SelectSeat = OC("Result_BTN_SelectSeat","xpath=//*[@id='"+ID_select_seats_button+"' and @onScreen='true']", "xpath=//*[@text='Select seats' and @onScreen='true']");
	public String[] Result_BTN_ManageFlight = OC("Result_BTN_ManageFlight","xpath=//*[@id='"+ID_select_seats_button+"' and @onScreen='true']", "xpath=//*[@text='Manage booking' and @ onScreen='true']");
	public String[] Result_BTN_AddtravelOption = OC("Result_BTN_AddtravelOption","xpath=//*[@id='"+ID_add_travel_options+"' and @onScreen='true']", "xpath=//*[contains(@text,'Travel option') and @onScreen='true']");
	public String[] Result_BTN_FareSummary = OC("Result_BTN_FareSummary","xpath=//*[@text='"+GetLocalText(TEXT_fare_summary)+"' and @onScreen='true']");
	public String[] Result_BTN_FareInfo = OC("Result_BTN_FareInfo","xpath=//*[@id='"+ID_fare_information+"' and @onScreen='true']");
	//Flights
	public String[] Flights_Title_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_flights)+"']", "xpath=//*[@accessibilityIdentifier='screen-header-Flights']");
	public String[] Flights_BTN_Booked = OC("Booked Button","xpath=//*[@id='"+ID_button_booked+"']","xpath=//*[@text='Booked']");
	public String[] Flights_BTN_Tracked = OC("Tracked Button","xpath=//*[@id='"+ID_button_tracked+"']","xpath=//*[@text='Tracked']");
	public String[] Flights_Text_Date = OC("Date","xpath=//*[contains(@text,'????')]");
	public String[] Flights_Text_FlightNo = OC("Flight no","xpath=//*[contains(@text,'????')]");
	public String[] Flights_BTN_Flight = OC("Flight List","xpath=//*[contains(@text,'????' )and ./parent::*[./following-sibling::*[./*[@text='????']]]and @onScreen='true']");
	public String[] Flights_BTN_AddaFlight = OC("Add a Flight Button","xpath=//*[@id='"+ID_button_add_flight_big+"']","xpath=//*[@text='Track a flight']");
	public String[] Flights_Text_FlightDetails = OC("Flight List","xpath=//*[@class='android.support.v7.widget.CardView']");
	public String[] Flights_BTN_Remove = OC("Flight List","xpath=//*[@text='"+GetLocalText(TEXT_remove)+"']","xpath=//*[@text='Remove']");
	public String[] Flights_BTN_Email = OC("Flight Email","xpath=//*[@text='"+GetLocalText(TEXT_email)+"']","xpath=//*[@text='Email address']");	
	public String[] Flights_Label_PNR = OC("Flight PNR Label","xpath=//*[contains(@text,'????')]");
	public String[] Flights_BTN_BookedSelected = OC("Booked button selected","xpath=//*[@text='"+GetLocalText(TEXT_booked)+"' and @textColor='0xFFFFFF']","xpath=//*[@text='Booked' and @textColor='0x4B4E54']");
	public String[] Flights_BTN_RetrieveAnotherBooking = OC("Flights_BTN_RetrieveAnotherBooking","xpath=//*[@id='"+ID_button_retrieve_flight+"' and @onScreen='true']","xpath=//*[@text='Retrieve a booking']");
	public String[] Flights_TXT_NoFlightFound = OC("Flights_TXT_NoFlightFound","xpath=//*[@text='"+GetLocalText(TEXT_error_no_flights_found)+"']","xpath=//*[@text='Booking reference not found']");
	//Booked
	public String[] Booked_BTN_Retrievebooking = OC("Retrieve booking button","xpath=//*[@id='"+ID_button_retrieve_flight+"']", "xpath=//*[@text='Retrieve a booking']");
	public String[] Booked_Text_BookingReference = OC("PNR number","xpath=//*[contains(@text,'????') and@top='true']");


	//Details Page
	public String[] Details_Title_Header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_details)+"']","xpath=//*[@accessibilityIdentifier='screen-header-Details']");
	//	public String[] Details_BTN_Back = OC("Back Button","xpath=//*[@contentDescription='back']");
	public String[] Details_BTN_Menu = OC("Menu Button","xpath=//*[@id='"+ID_action_show_menu+"']","xpath=//*[@text='Options']");
	public String[] Details_BTN_Share_Itinerary = OC("Itinerary Button","xpath=//*[@id='"+ID_button_share_itinerary+"']");
	public String[] Details_BTN_Remove_tracked = OC("Remove Tracked flight","xpath=//*[@id='"+ID_button_remove_from_tracked+"']","xpath=//*[@text='Remove from tracked flights']");
	public String[] Details_Menu_Option = OC("Menu Option","xpath=//*[@id='"+ID_action_show_menu+"']","xpath=//*[@text='Options']");
	public String[] Details_Menu_ShareItinerary = OC("ShareItinerary Menu Option","xpath=//*[@id='"+ID_button_share_itinerary+"']","xpath=//*[@text='Share flight']");
	public String[] Details_Menu_RemoveSegment = OC("RemoveSegment Menu Option","xpath=//*[@id='"+ID_button_remove_segment_from_tracked+"']","xpath=//*[@text='Remove all segments']");
	public String[] Details_Menu_RemoveTracked = OC("RemoveTracked Menu Option","xpath=//*[@id='"+ID_button_remove_from_tracked+"']","xpath=//*[@text='Remove from tracked flights']");
	public String[] Details_BTN_Cancel = OC("Cancel Button","xpath=//*[@text='"+GetLocalText(TEXT_profile_not_activated_cancel)+"']","xpath=//*[@text='Cancel']");
	public String[] Details_BTN_Yes = OC("Yes Button","xpath=//*[@text='"+GetLocalText(TEXT_yes)+"']","xpath=//*[@text='Yes']");
	public String[] Details_BTN_AddtoTrackedFlight=OC("Yes Button","xpath=//*[@id='"+ID_button_add_to_tracked_flights+"' and @hidden='false' and @onScreen='true']","xpath=//*[@text='Add to tracked flights' and @onScreen='true']");
	public String[] Details_BTN_Removebooked=OC("Remove booked history","xpath=//*[@text='Remove flight']","xpath=//*[@text='Remove flight']");

	//Remove tracked flight
	public String[] RTF_Title_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_are_you_sure)+"']","xpath=//*[@text='Are you sure you want to do this?']");
	public String[] RTF_BTN_Yes = OC("Yes Button","xpath=//*[@text='"+GetLocalText(TEXT_yes)+"']","xpath=//*[@text='Yes']");
	public String[] RTF_BTN_Cancel = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_profile_not_activated_cancel)+"']");
	//Remove Booking ref
	public String[] RBR_Title_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_removing_this_trip_doesnt_cancel)+"']","xpath=//*[@text='Removing this trip from your list doesn’t cancel your trip']");
	public String[] RBR_BTN_Remove = OC("Yes Button","xpath=//*[@id='"+ID_button_call+"']","xpath=//*[@text='Yes']");
	public String[] RBR_BTN_Close = OC("Page Header","xpath=//*[@id='"+ID_button_close+"']","xpath=//*[@text='Cancel']");
	public String[] RBR_BTN_RemoveTrip = OC("Yes Button","xpath=//*[@id='"+ID_button_call+"']","xpath=//*[@text='Remove trip']");
	//Flight_Status_By_City_FSBC
	public String[] FSBC_Text_From = OC("From Text","xpath=//*[@text='"+GetLocalText(TEXT_from)+"']","xpath=//*[@text='From']");
	public String[] FSBC_Textbox_Fromairport = OC("Search box for from airport ","xpath=//*[@id='"+ID_text_view_origin+"']","xpath=//*[@text='Select origin']");
	public String[] FSBC_Text_FromairportList = OC("Airports listed","xpath=//*[@id='"+ID_shortName+"']");
	public String[] FSBC_Text_To = OC("To Text","xpath=//*[@text='"+GetLocalText(TEXT_to)+"']","xpath=//*[@text='To']");
	public String[] FSBC_Textbox_Toairport = OC("Search box for to airport ","xpath=//*[@id='"+ID_text_destination+"']","xpath=//*[@text='Select Destination']");
	public String[] FSBC_Text_ToairportList = OC("Airports listed","xpath=//*[@id='"+ID_shortName+"']");
	public String[] FSBC_Textbox_date = OC("Date Textbox","xpath=//*[@id='"+ID_flight_date_textView+"']","xpath=//*[@accessibilityIdentifier='icon-calendar']");
	public String[] FSBC_Text_Year = OC("Year of the date","xpath=//*[@id='year' and @hidden='false']");
	public String[] FSBC_Text_Day = OC("Day of the date","xpath=//*[@id='day' and @ hidden='false']");
	public String[] FSBC_Text_Month = OC("Month of the date","xpath=//*[@id='month' and @ hidden='false']");
	public String[] FSBC_BTN_Search = OC("Search Button","xpath=//*[@id='"+ID_button_search+"']","xpath=//*[@text='Search']");
	public String[] FSBC_text_datePicker = OC("Date picker","xpath=//*[@id='numberpicker_input' and @ hidden='false']");
	//Flight_Status_By_PNR_FSBPNR
	public String[] FSBPNR_Text_BookingRefernce = OC("Booking Refernce Text","xpath=//*[@text='"+GetLocalText(TEXT_by_pnr)+"']");
	public String[] FSBPNR_Text_Passenger = OC("Passenger Text","xpath=//*[@text='"+GetLocalText(TEXT_passenger)+"']");
	public String[] FSBPNR_Textbox_BookingRefernce = OC("Booking Refernce Textbox","xpath=//*[@id='"+ID_booking_reference_editText+"']","0","xpath=//*[@placeholder='ABC123']","1");
	public String[] FSBPNR_Textbox_Passenger = OC("Passenger Textbox","xpath=//*[@id='"+ID_passenger_editText+"']","0","xpath=//*[@placeholder='Last name']","1");
	public String[] FSBPNR_BTN_Search = OC("Search Button","xpath=//*[@id='"+ID_button_search+"']","xpath=//*[@text='Search']");
	//Search by Booking reference Trip Itinerary BFTR
	public String[] BFTR_Title_Header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_title_itinerary)+"']", "xpath=//*[@accessibilityIdentifier='screen-header-Itinerary']");
	//	public String[] BFTR_BTN_Back = OC("Back Button","xpath=//*[@contentDescription='back']");
	public String[] BFTR_Text_PNR = OC("PNR number","xpath=//*[@text='????']");
	public String[] BFTR_Text_Booking_Reference= OC(" Booking Reference text","xpath=//*[@text='"+GetLocalText(TEXT_by_pnr)+"']","xpath=//*[contains(@accessibilityLabel,'Booking reference')]");
	public String[] BFTR_Text_Aiports= OC("Source and destination aitports","xpath=//*[@id='"+ID_itinerary_departure_header+"']");
	public String[] BFTR_Text_Flight_number= OC("Fligth number","xpath=//*[@id='"+ID_textview_flight_number+"']");
	public String[] BFTR_BTN_AddtobookedFlight= OC("Add to booked flight button","xpath=//*[@id='"+ID_button_add_to_booked_flights+"' and @onScreen='true' and @hidden='false']","xpath=//*[@text='Add to booked flights' and @top='true']");
	public String[] BFTR_Text_ManageBooking = OC("Manage Booking Text","xpath=//*[@text='"+GetLocalText(TEXT_manage_booking)+"' and @onScreen='true']");

	//Change Flight
	public String[] CF_Title_ChangeFlight= OC("Change Fligth Title","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_change_flights)+"']");
	public String[] CF_Text_Changeinternationflight= OC("Change InternationalText","xpath=//*[@text='"+GetLocalText(TEXT_change_international_flights_message)+"']");




	//Change Trip
	public String[] CT_Title_ChangeTrip= OC("Change Trip Title","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_change_trip)+"']");
	public String[] CT_BTN_Arrow= OC("Toggle Arrow","xpath=//*[@id='"+ID_booking_header_toggle_icon+"']");
	public String[] CT_Text_flightNO= OC("Flight number","xpath=//*[@id='"+ID_textview_flight_number+"']");
	public String[] CT_BTN_Search= OC("Search Button","xpath=//*[@id='"+ID_button_search+"']");
	public String[] CT_Text_Updated_Reference= OC("Updated Text","xpath=//*[@text='"+GetLocalText(TEXT_updated)+"']");
	public String[] CT_BTN_Cancel= OC("Cancel Changes Button","xpath=//*[@id='"+ID_cancel_button+"']");
	public String[] CT_BTN_Continue= OC("Continue Changes Button","xpath=//*[@id='"+ID_continue_button+"']");
	public String[] CT_Text_ChangeNotAvailable= OC("Text Change not availabe","xpath=//*[@text='"+GetLocalText(TEXT_change_and_cancel_not_available)+"']");



	//Change summary
	public String[] CS_Title_ChangeSummary= OC("Change summary title","xpath=//*[@text='"+GetLocalText(TEXT_title_activity_change_flights_summary)+"']");
	public String[] CS_Text_currentBooking= OC("Current Booking text","xpath=//*[@text='"+GetLocalText(TEXT_current_booking)+"']");
	public String[] CS_Text_PNR= OC("PNR","xpath=//*[@id='"+ID_booking_header_pnr+"']");
	public String[] CS_Text_FlightNo= OC("Flight number","xpath=//*[@id='"+ID_textview_flight_number+"']");
	public String[] CS_Text_Payment= OC("Payment Text","xpath=//*[@text='"+TEXT_payment+"']");
	public String[] CS_Text_AdditionalCharges= OC("Additional Charges","xpath=//*[@text='"+TEXT_additional_charges+"']");
	public String[] CS_Link_fareDetails= OC("Fare Details","xpath=//*[@id='"+ID_btn_fare_details_taxes+"']");
	public String[] CS_Link_TermsandCondition= OC("Terms and Condition","xpath=//*[@id='"+ID_btn_terms_conditions+"']");
	public String[] CS_BTN_Confirm= OC("Confirm button","xpath=//*[@text='"+TEXT_accept_confirm+"']");
	public String[] CS_BTN_Cancel= OC("Cancel changes","xpath=//*[@text='"+TEXT_cancel_changes+"']");

	//Change confirmation

	public String[] CC_Title_Changeconfirmation= OC("Change confirmation title","xpath=//*[@text='"+TEXT_flight_change_confirmed+"']");
	public String[] CC_Text_Changeconfirmation= OC("Change confirmation Text","xpath=//*[@text='"+TEXT_your_flight_change_is_confirmed+"']");
	//

	//Manage Booking Page MB
	public String[] MB_Title_Header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_manage_booking)+"']", "xpath=//*[@text='"+GetLocalText(TEXT_cancel_booking_positive)+"' and @onScreen='true']");
	public String[] MB_BTN_ChangeBooking = OC("Change booking Button","xpath=//*[@id='"+ID_btn_change_flights+"']");
	public String[] MB_Text_CancelBooking = OC("Cancel booking text","xpath=//*[@id='"+ID_btn_cancel_booking+"' and @text='"+GetLocalText(TEXT_cancel_booking_positive)+"' and @onScreen='true']", "xpath=//*[@text='"+GetLocalText(TEXT_cancel_booking_positive)+"' and @onScreen='true']");
	public String[] MB_Text_AuthenticationWarning = OC("Authentication Warning Text","xpath=//*[@text='"+GetLocalText(TEXT_manage_flight_header)+"']");
	public String[] MB_TextBox_CreditCardLast4Digit = OC("TextBox_CreditCard Last 4Digit","xpath=//*[@id='"+ID_card_number_editText+"' and @hint='Last 4 digits' and @onScreen='true']", "xpath=//*[@text='"+GetLocalText(TEXT_last_four_digits)+"' and @onScreen='true']");
	public String[] MB_TextBox_CreditCardMonth = OC("TextBox_CreditCard Month","xpath=//*[@id='"+ID_months_clearable+"' and @onScreen='true']", "xpath=//*[@text='MM']");
	public String[] MB_TextBox_CreditCardyear = OC("TextBox_CreditCard Year","xpath=//*[@id='"+ID_years_clearable+"' and @onScreen='true']", "xpath=//*[@text='YY']");
	public String[] MB_BTN_Continue = OC("Continue Button","xpath=//*[@id='"+ID_button_auth+"' and @text='"+GetLocalText(TEXT_continue_continue)+"' and @onScreen='true']", "xpath=//*[@class='ACToolkit.ACButton' and @onScreen='true']");
	public String[] MB_Text_CancelationPoilicy = OC("Cancelation Poilicy Text","xpath=//*[@text='"+GetLocalText(TEXT_cancellation_policy_header)+"']");
	public String[] MB_TextBox_Email = OC("TextBox Email","xpath=//*[@id='"+ID_text_email_address+"']");
	public String[] MB_BTN_CancelEntireBooking = OC("Continue Button","xpath=//*[@id='"+ID_button_auth+"']", "xpath=//*[@class='ACToolkit.ACButton' and @onScreen='true']");
	public String[] MB_Text_CancelConfirmationWarning = OC("Cancel Confirmation Text","xpath=//*[@text='"+GetLocalText(TEXT_cancel_confirmation)+"']");
	public String[] MB_BTN_CancelBooking = OC("Cancel booking Button","xpath=//*[@id='"+ID_button_call+"' and @text='"+GetLocalText(TEXT_cancel_booking_positive)+"']", "0", "xpath=//*[@text='"+GetLocalText(TEXT_cancel_booking_positive)+"']", "2");
	public String[] MB_BTN_KeepChanges = OC("Continue Button","xpath=//*[@id='"+ID_button_call+"' and @text='"+GetLocalText(TEXT_keep_changes)+"']");
	public String[] MB_Text_CancelationSuccesfullMSG = OC("Cancelation Succesfull MSG Text","xpath=//*[@text='"+GetLocalText(TEXT_your_booking_has_been_cancelled)+"']");
	public String[] MB_BTN_Done = OC("Done Button","xpath=//*[@id='"+ID_button_done+"' and @text='"+GetLocalText(TEXT_abc_action_mode_done)+"' and @onScreen='true']");
	//

	//Departure_Flight_Selection_DFS
	public String[] DFS_Text_Title = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_departure_flight)+"']","xpath=//*[@text='Departure Flight']");
	//	public String[] DFS_BTN_Back = OC("Back Button","xpath=//*[@contentDescription='Navigate up' and @hidden='false']");
	public String[] DFS_Text_ScheduledTime = OC("Flight scheduled time","xpath=//*[@id='segment_time_small' and @hidden='false']");
	public String[] DFS_Text_FlightNo = OC("Flight number","xpath=//*[@id='"+ID_segment_flight_number+"' and @hidden='false']", "xpath=//*[starts-with(@accessibilityLabel,'AC')]");
	public String[] DFS_Text_FlightOperatorLogo = OC("Flight operator logo","xpath=//*[@id='"+ID_segment_number_logo+"' and @hidden='false']","xpath=//*[@accessibilityIdentifier='logo-redmaple']");
	public String[] DFS_Text_FlightTravelDuration = OC("Total travel duration","xpath=//*[@id='"+ID_total_flight_duration+"' and @hidden='false']");
	public String[] DFS_Text_FlightInfo = OC("More info on fligth","xpath=//*[@id='"+ID_expand_button+"' and @hidden='false']");
	public String[] DFS_Text_PEconomytitle = OC("Premium economy title","xpath=//*[@id='"+ID_group_title+"' and @text='"+GetLocalText(TEXT_premium_economy)+"']", "xpath=//*[starts-with(@text,'Premium')]");
	public String[] DFS_Text_PEconomyPrice = OC("Premium economy from Price range","xpath=//*[@id='"+ID_group_from+"']");
	public String[] DFS_Text_Economytitle = OC("Economy title","xpath=//*[@id='"+ID_group_title+"' and @text='"+GetLocalText(TEXT_economy)+"']", "xpath=//*[@text='"+GetLocalText(TEXT_economy)+"']");
	public String[] DFS_Text_EconomyPrice = OC("Economy from Price range","xpath=//*[@id='"+ID_group_from+"']","1", "", "");
	public String[] DFS_Text_Businesstitle = OC("Business title","xpath=//*[@id='"+ID_group_title+"' and @text='"+GetLocalText(TEXT_business)+"']", "xpath=//*[starts-with(@text,'Business')]");
	public String[] DFS_Text_BusinessPrice = OC("Businees Price range","xpath=//*[@id='"+ID_group_from+"']","2", "", "");
	public String[] DFS_Text_Class = OC("Class name","xpath=//*[@id='"+ID_text+"' and @hidden='false']");
	public String[] DFS_Text_TicketPrice = OC("Ticket "+ID_price+" for class","xpath=//*[@id='"+ID_price+"' and @top='false']");
	public String[] DFS_Text_Message = OC("Message ","xpath=//*[@id='"+ID_messages+"' and @hidden ='false']");
	public String[] DFS_Link_Fare = OC("Fare details","xpath=//*[@text='"+GetLocalText(TEXT_title_activity_change_flights_details)+"' and @onScreen='true']");
	public String[] DFS_BTN_ClassSelection = OC("Class selection","xpath=//*[@id='"+ID_button_select+"']", "xpath=//*[starts-with(@text,'Choose')]");
	public String[] DFS_BTN_ClassType = OC("Class Type selection","xpath=//*[(contains(@text,'????')) and @ hidden='false']");
	public String[] DFS_BTN_TangoClassType = OC("Tango Class Type selection","xpath=//*[@text='Tango']");
	public String[] DFS_BTN_FlexClassType = OC("Flex Class Type selection","xpath=//*[@text='Flex']");
	public String[] DFS_BTN_LatitudeClassType = OC("Latitude Class Type selection","xpath=//*[@text='Latitude']");



	//Full_Fare_Detail_FFD
	public String[] FFD_Text_Title = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"']");
	public String[] FFD_Text_Message = OC("Message on flight operation","xpath=//*[@id='"+ID_message_text+"']");
	public String[] FFD_Text_FareBreakdown = OC("Fare Breakdown text","xpath=//*[@text='"+GetLocalText(TEXT_fare_breakdown)+"']");
	public String[] FFD_Text_BaseFare = OC("Base Fare Text","xpath=//*[@text='"+GetLocalText(TEXT_base_fare)+"']");
	public String[] FFD_Text_Taxes = OC("Taxes Text","xpath=//*[@text='"+GetLocalText(TEXT_taxes_and_fees)+"']");
	public String[] FFD_Text_TotalAmt = OC("Total per passenger text","xpath=//*[@text='"+GetLocalText(TEXT_total_per_passenger)+"']");
	//Return_Flight_RF
	public String[] RF_Title_Header = OC("Page Headers","xpath=//*[@id='"+ID_toolbar_title+"']");
	//	public String[] RF_BTN_Back = OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	public String[] RF_BTN_RefineSearch = OC("Refine search button","xpath=//*[@id='"+ID_action_refine_search+"']");
	//public String[] RF_Text_Tripdescription_Plus= OC("Trip description","xpath=//*[@id='"+ID_trip_description+"']");
	public String[] RF_Text_Sortby = OC("Sort by text","xpath=//*[@text='"+GetLocalText(TEXT_sort_by)+"']");
	public String[] RF_BTN_time = OC("Sort by Time","xpath=//*[@text='"+GetLocalText(TEXT_time_of_day)+"']");
	public String[] RF_BTN_Price = OC("Sort by Price","xpath=//*[@text='"+GetLocalText(TEXT_price)+"']");
	public String[] RF_BTN_Duration = OC("Sort by Duration","xpath=//*[@text='"+GetLocalText(TEXT_duration)+"']");
	public String[] RF_Text_Directflights = OC("Direct flights text","xpath=//*[@text='"+GetLocalText(TEXT_direct_flights)+"']");
	public String[] RF_Text_SortSummary = OC("Sort Summary Text","xpath=//*[@id='sort_summary']");
	public String[] RF_Text_connectioFlights = OC("Connection flights text","xpath=//*[@text='"+GetLocalText(TEXT_connecting_flights)+"']");
	//Return_Flight_Selection_RFS
	public String[] RFS_Text_Title = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"']", "xpath=//*[contains(@accessibilityLabel,'Flight')]");
	//	public String[] RFS_BTN_Back = OC("Back Button","xpath=//*[@contentDescription='Navigate up' and @hidden='false']");
	public String[] RFS_Text_ScheduledTime = OC("Flight scheduled time","xpath=//*[@id='segment_time_small' and @hidden='false']");
	public String[] RFS_Text_FlightNo = OC("Flight number","xpath=//*[@id='"+ID_segment_flight_number+"' and @hidden='false']");
	public String[] RFS_Text_FlightOperatorLogo = OC("Flight operator logo","xpath=//*[@id='"+ID_segment_number_logo+"' and @hidden='false']");
	public String[] RFS_Text_FlightTravelDuration = OC("Total travel duration","xpath=//*[@id='"+ID_total_flight_duration+"' and @hidden='false']");
	public String[] RFS_Text_FlightInfo = OC("More info on fligth","xpath=//*[@id='"+ID_expand_button+"' and @hidden='false']");
	public String[] RFS_Text_PEconomytitle = OC("Premium economy title","xpath=//*[@id='"+ID_group_title+"' and @text='"+GetLocalText(TEXT_premium_economy)+"']");
	public String[] RFS_Text_PEconomyPrice = OC("Premium economy from Price range","xpath=//*[@id='"+ID_group_from+"']");
	public String[] RFS_Text_Economytitle = OC("Economy title","xpath=//*[@id='"+ID_group_title+"' and @text='"+GetLocalText(TEXT_economy)+"']");
	public String[] RFS_Text_EconomyPrice = OC("Economy from Price range","xpath=//*[@id='"+ID_group_from+"']","1", "", "");
	public String[] RFS_Text_Businesstitle = OC("Business title","xpath=//*[@id='"+ID_group_title+"' and @text='"+GetLocalText(TEXT_price_group_business)+"']");
	public String[] RFS_Text_BusinessPrice = OC("Businees Price range","xpath=//*[@id='"+ID_group_from+"']","2", "", "");
	public String[] RFS_Text_Class = OC("Class name","xpath=//*[@id='"+ID_text+"' and @hidden='false']");
	public String[] RFS_Text_TicketPrice = OC("Ticket "+ID_price+" for class","xpath=//*[@id='"+ID_price+"' and @hidden='false']");
	public String[] RFS_Text_Message = OC("Message ","xpath=//*[@id='"+ID_messages+"' and @hidden ='false']");
	public String[] RFS_Link_Fare = OC("Fare details","xpath=//*[@id='"+ID_seat_preview+"']");
	public String[] RFS_BTN_ClassSelection = OC("Class selection","xpath=//*[@id='"+ID_button_select+"']");
	//Travel_Option_TO
	public String[] TO_Text_Title = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_travel_options)+"' and @onScreen='true']");
	//	public String[] TO_BTN_Back = OC("Back Button","xpath=//*[@contentDescription='Navigate up']");
	public String[] TO_Text_MealOption = OC("Meal option","xpath=//*[@text='"+GetLocalText(TEXT_meal)+"']");
	public String[] TO_Text_MealPerpersonRate = OC("per person rate text for meal option","xpath=//*[@text='per person' and ./parent::*[./preceding-sibling::*[@text='"+GetLocalText(TEXT_meal)+"']]]");
	public String[] TO_BTN_MealExpandbutton = OC("More info on meal option","xpath=//*[@class='android.widget.ToggleButton' and ./preceding-sibling::*[./*[@text='"+GetLocalText(TEXT_meal)+"']]]");
	public String[] TO_BTN_AddtoDepartureFlight = OC("Add the option to departure flight","xpath=//*[@text='"+GetLocalText(TEXT_add_travel_option_to_dep_flight)+"']");
	public String[] TO_BTN_DepartureFlight = OC("Add the option to departure flight","xpath=//*[@id='"+ID_button_addToDepartureFlight+"']");
	public String[] TO_BTN_DepartureFlightNA= OC("Add the option to departure flight Not available","xpath=//*[@id='"+ID_button_addToDepartureFlight+"' and @text='"+GetLocalText(TEXT_not_available)+"']");
	public String[] TO_BTN_MealAddtoReturnFlight = OC("Add the option to Return flight","xpath=//*[@id='"+ID_button_addToReturningFlight+"' and @top='true']");
	public String[] TO_BTN_MealAddtoReturnFlightNA= OC("Add the option to Return flight Not available","xpath=//*[@id='"+ID_button_addToReturningFlight+"' and @text='"+GetLocalText(TEXT_not_available)+"']");
	public String[] TO_Text_AddtoFlight = OC("Text to check for Add the option to fligth","xpath=//*[@text='"+GetLocalText(TEXT_add_travel_option)+"']");
	public String[] TO_Text_AddedtoReturnFlight = OC("Return flight added","xpath=//*[@text='"+GetLocalText(TEXT_added_travel_option_to_ret_flight)+"']");
	public String[] TO_Text_OnwardOption = OC("Onward Travel option text","xpath=//*[(contains(@text,'????')) and @onScreen='true']");
	public String[] TO_Text_ReturnOption = OC("Return Travel option text","xpath=//*[@id='"+ID_checkbox+"' and ./parent::*[./preceding-sibling::*[./*[contains(@text,'????')]]]]");	
	public String[] TO_Text_OnMyWayPerpersonRate = OC("per person rate text for On my Way option","xpath=//*[@text='"+GetLocalText(TEXT_per_person)+"' and ./parent::*[./preceding-sibling::*[@text='On My Way']]]");
	public String[] TO_BTN_OnMyWayExpandbutton = OC("More info on On My way option","xpath=//*[@class='android.widget.ToggleButton' and ./preceding-sibling::*[./*[@text='On My Way']]]");
	public String[] TO_Text_MapleLeafPerpersonRate = OC("per person rate text for My Leaf option","xpath=//*[@text='"+GetLocalText(TEXT_per_person)+"' and ./parent::*[./preceding-sibling::*[@text='Maple Leaf Lounge']]]");
	public String[] TO_BTN_MapleLeafExpandbutton = OC("More info on My Leaf option","xpath=//*[@class='android.widget.ToggleButton' and ./preceding-sibling::*[./*[@text='Maple Leaf Lounge']]]");
	public String[] TO_Text_Remarktext = OC("Remark text on the travel option","xpath=//*[@id='"+ID_remark_text+"']");
	public String[] TO_BTN_Skip = OC("Skipt button","xpath=//*[@id='"+ID_button_skip+"' and @onScreen='true' and @ hidden='false']","xpath=//*[@text='Skip']");
	public String[] TO_BTN_confirm = OC("Confirm button","xpath=//*[@id='"+ID_button_confirm+"']", "xpath=//*[@text='"+GetLocalText(TEXT_confirm)+"']");
	public String[] TO_BTN_DynamicSelection = OC("Tarvel Option","xpath=//*[contains(@text,'????') and ./parent::*[./parent::*[./preceding-sibling::*[./*[./*[./*[contains(@text,'????')]]]]]]]");
	//Fare_Summary_Roundway_trip_FSRT
	public String[] FSRT_Title_header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"']");
	//	public String[] FSRT_BTN_Backbutton = OC("Back Button","xpath=//*[@contentDescription='Navigate up']");
	public String[] FSRT_Text_DepartingFlight = OC("Deprating Flight text","xpath=//*[@text='"+GetLocalText(TEXT_departing_flight)+"' and @onScreen='true']");
	public String[] FSRT_Text_FlightSummary = OC("FlightSummary text","xpath=//*[@text='"+GetLocalText(TEXT_flight_summary)+"' and @onScreen='true']", "xpath=//*[@accessibilityIdentifier='screen-header-Flight summary']");
	public String[] FSRT_Text_DepartingFlightTime = OC("Deprating Flight Time ","xpath=//*[@id='segment_time_small' and @ hidden='false']");
	public String[] FSRT_Text_DepartingFlightNumber = OC("Deprating Flight Number","xpath=//*[@id='"+ID_segment_flight_number+"' and @ hidden='false']");
	public String[] FSRT_Text_DepartingFlightlogo = OC("Deprating Flight operator logo","xpath=//*[@id='"+ID_segment_number_logo+"' and @ hidden='false']");
	public String[] FSRT_Text_DepartingFlighttravelDuration = OC("Deprating Flight Travel Duration","xpath=//*[@id='"+ID_total_flight_duration+"' and @hidden='false']");
	public String[] FSRT_BTN_MoreinfoOnDepartingFlight = OC("Expandbutton","xpath=//*[@id='"+ID_expand_button+"' and @ hidden='false']");
	public String[] FSRT_Text_ReturnFlight = OC("Return Flight text","xpath=//*[@text='"+GetLocalText(TEXT_returning_flight)+"']");	
	public String[] FSRT_Text_ReturnFlightTime = OC("Return Flight Time ","xpath=//*[@id='segment_time_small' and @ hidden='false']","1", "", "");
	public String[] FSRT_Text_ReturnFlightNumber = OC("Return Flight Number","xpath=//*[@id='"+ID_segment_flight_number+"' and @ hidden='false']","1", "", "");
	public String[] FSRT_Text_ReturnFlightlogo = OC("Return Flight operator logo","xpath=//*[@id='"+ID_segment_number_logo+"' and @ hidden='false']","1", "", "");
	public String[] FSRT_Text_ReturnFlighttravelDuration = OC("Return Flight Travel Duration","xpath=//*[@id='"+ID_total_flight_duration+"' and @hidden='false']","1", "", "");
	public String[] FSRT_BTN_MoreinfoOnReturnFlight = OC("Expandbutton","xpath=//*[@id='"+ID_expand_button+"' and @ hidden='false']","1", "", "");
	public String[] FSRT_Text_FareSummary = OC("FareSummarry text","xpath=//*[@text='"+GetLocalText(TEXT_fare_summary)+"']");
	public String[] FSRT_Text_TravelOption = OC("Travel option text","xpath=//*[@text='"+GetLocalText(TEXT_travel_options)+"']");
	public String[] FSRT_Text_Charges = OC("Charges text","xpath=//*[@text='"+GetLocalText(TEXT_taxes_fees_and_charges)+"']");
	public String[] FSRT_Text_GrandTotal = OC("Grand total text","xpath=//*[@text='"+GetLocalText(TEXT_grand_total)+"']");
	public String[] FSRT_Link_FullFare = OC("Fare details link","xpath=//*[@text='"+GetLocalText(TEXT_title_activity_change_flights_details)+"']");
	public String[] FSRT_BTN_Continue = OC("Continue button","xpath=//*[@text='"+GetLocalText(TEXT_accept)+"' and @hidden='false' and @onScreen='true']");

	//Full_Fare_details_link_FFDL
	public String[] FFDL_Title_Header = OC("Page Header","xpath=//*[@id='"+ID_toolbar_title+"']");
	//	public String[] FFDL_BTN_BackButton = OC("Back Button","xpath=//*[@contentDescription='Navigate up']");
	public String[] FFDL_Text_DepartingFlight = OC("Departing flight text","xpath=//*[@text='"+GetLocalText(TEXT_departing_flight)+"']");
	public String[] FFDL_Text_DepartingFlightAdultcount = OC("Departing flight adult count","xpath=//*[@text='"+GetLocalText(TEXT_adult)+"'-]");
	public String[] FFDL_Text_ReturningFlight = OC("Returning flight text","xpath=//*[@text='"+GetLocalText(TEXT_returning_flight)+"']");
	public String[] FFDL_Text_ReturningFlightAdultCount = OC("Returning flight adult count","xpath=//*[@text='"+GetLocalText(TEXT_adult)+"'-]","1", "", "");
	public String[] FFDL_Text_Taxes = OC("Taxes, fees, charges text","xpath=//*[@text='"+GetLocalText(TEXT_taxes_fees_and_charges)+"']");
	public String[] FFDL_Text_Total = OC("Grand total","xpath=//*[@text='"+GetLocalText(TEXT_grand_total)+"']");

	//CheckIn
	public String[] CheckIn_Label_Title = OC("Checkin Title","xpath=//*[@id='"+ID_toolbar_title+"']");
	public String[] CheckIn_Img_Logo = OC("Aircanada Logo","Web","xpath=//*[@alt='Air Canada']");
	public String[] CheckIn_Textbox_Firstname = OC("First Name textbox","Web","xpath=//*[@id='firstName']");
	public String[] CheckIn_Textbox_Lastname = OC("Last Name textbox","Web","xpath=//*[@id='lastName']");
	public String[] CheckIn_Dropdown_identificationnumber = OC("Choose identification number dropdown","Web","xpath=//*[@id='id_cardType']");
	public String[] CheckIn_Textbox_GeneralNumber = OC("General number textbox","Web","xpath=//*[@id='generalNumber']");
	public String[] CheckIn_Textbox_DepartureCity = OC("Departure City textbox","Web","xpath=//*[@id='departureCity']");
	public String[] CheckIn_Text_DepartureCountry = OC("Country text","Web","xpath=//*[@text='"+GetLocalText(TEXT_countries)+"']");
	public String[] CheckIn_Text_DepartureCity = OC("City text","Web","xpath=//*[@text='"+GetLocalText(TEXT_city)+"']");
	public String[] CheckIn_Checkbox_RememberMyInfo = OC("Remember my info checkbox checkbox","Web","xpath=//*[@name='remember']");
	public String[] CheckIn_BTN_Continue = OC("continue button","Web","xpath=//*[@name='_eventId_continue']");
	public String[] CheckIn_BTN_skip = OC("Skip button","Web","xpath=//*[@name='_eventId_quit']");
	public String[] CheckIn_Text_MobileCheckininfo = OC("Check in details info","Web","xpath=//*[@nodeName='UL']");
	//Booking_Summary_BS
	public String[] BS_Title_Heading = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_booking_summary)+"']");
	//	public String[] BS_BTN_BackButton = OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	public String[] BS_Text_DepartingFlight = OC("Departing Fligth Text","xpath=//*[@text='"+GetLocalText(TEXT_departing_flight)+"' and @onScreen='true']");
	public String[] BS_Text_DepartingFlightTime = OC("Departing Fligth Time","xpath=//*[@id='segment_time_small' and @hidden='false']");
	public String[] BS_Text_DepartingFlightExpand = OC("Departing Fligth Expand","xpath=//*[@id='"+ID_expand_button+"' and @hidden='false']");
	public String[] BS_Text_DepartingFlightnumber = OC("Departing FligthtNumber","xpath=//*[@id='"+ID_segment_flight_number+"' and @hidden='false']", "xpath=//*[starts-with(@accessibilityLabel,'AC')]");
	public String[] BS_Text_DepartingFlightDurationTime = OC("Departing FligthtDurationTime","xpath=//*[@id='"+ID_total_flight_duration+"' and @hidden='false']");
	public String[] BS_Text_ReturnFlight = OC("Return Fligth Text","xpath=//*[@text='"+GetLocalText(TEXT_returning_flight)+"']");
	public String[] BS_Text_ReturnFlightTime = OC("Departing Fligth Time","xpath=//*[@text='"+GetLocalText(TEXT_returning_flight)+"']","1", "", "");
	public String[] BS_Text_ReturnFlightExpand = OC("Departing Fligth Expand","xpath=//*[@id='"+ID_expand_button+"' and @hidden='false']","1", "", "");
	public String[] BS_Text_ReturnFlightnumber = OC("Departing FligthtNumber","xpath=//*[@id='"+ID_segment_flight_number+"' and @hidden='false']","1", "", "");
	public String[] BS_Text_ReturnFlightDurationTime = OC("Departing FligthtDurationTime","xpath=//*[@id='"+ID_total_flight_duration+"' and @hidden='false']","1", "", "");
	public String[] BS_Text_Passengertitle = OC("PassengerTitle","xpath=//*[@id='"+ID_passenger_title+"' and @hidden='false' or  @text='"+GetLocalText(TEXT_select_passenger)+"']");
	public String[] BS_Text_PassengerCount_Dynamic = OC("Passenger Count Dynamic","xpath=//*[@text='???? "+GetLocalText(TEXT_plural_passengers_other)+"' or @text='???? "+GetLocalText(TEXT_plural_passengers_one)+"']");
	public String[] BS_Text_SelectPassengerNumber = OC("Select passengers Number", "xpath=//*[@id='"+ID_passenger_number+"' and @text='????' and @onScreen='true']");
	public String[] BS_Text_NoofPassengerOnScreen = OC("No of passengers on screen", "xpath=//*[@id='"+ID_passenger_number+"' and @hidden='false' and @onScreen='true']", "xpath=//*[@text='"+GetLocalText(TEXT_select_passenger)+"' and @onScreen='true']");
	public String[] BS_Text_NoofPassenger = OC("No of passengers", "xpath=//*[@id='"+ID_passenger_number+"' and @hidden='false' and @onScreen='true']", "xpath=//*[@class='ACApp.BookingSummaryPassengersViewCell']");
	public String[] BS_Text_SelectNoofPassenger = OC("Select No of passengers", "xpath=//*[@id='"+ID_passenger_number+"' and @hidden='false' and @onScreen='true']", "xpath=//*[@text='????' and @onScreen='true']");
	
	public String[] BS_Text_SelectPassenger = OC("Select passengers", "xpath=//*[@text='"+GetLocalText(TEXT_select_passenger)+"' and @onScreen='true']");


	public String[] BS_Label_HeaderPassenger = OC("Passenger Header", "xpath=//*[@id='"+ID_passenger_header+"' and @hidden='false']");	
	public String[] BS_Label_Payment = OC("Payment Label","xpath=//*[@text='"+GetLocalText(TEXT_payment)+"']");
	public String[] BS_Text_FareSummary = OC("Fare Summary","xpath=//*[@text='"+GetLocalText(TEXT_fare_summary)+"']");
	public String[] BS_Text_TravelFare = OC("Travel Option Fare Details","xpath=//*[@text='"+GetLocalText(TEXT_travel_options)+"']");
	public String[] BS_Text_TaxFare = OC("Tax Fare Details","xpath=//*[@text='"+GetLocalText(TEXT_taxes_fees_and_charges)+"']");
	public String[] BS_Text_GrandTotal = OC("Grand total","xpath=//*[@text='"+GetLocalText(TEXT_grand_total)+"']");
	public String[] BS_Link_Fare = OC("Fare details Link","xpath=//*[@text='"+GetLocalText(TEXT_title_activity_change_flights_details)+"']");
	public String[] BS_BTN_Paynow = OC("Pay now button","xpath=//*[@id='"+ID_button_pay_now+"']", "xpath=//*[@accessibilityIdentifier='payNowButton']");
	public String[] BS_BTN_StartNewSearch = OC("Start new search button","xpath=//*[@text='"+GetLocalText(TEXT_start_new_search)+"']");
	public String[] BS_Label_Airport = OC("Airport Label","xpath=//*[@class='com.aircanada.view.FontTextView' and @textColor='0xFFFFFF']", "xpath=//*[contains(@text,'????')]");
	public String[] BS_BTN_Replace = OC("Replace button","xpath=//*[@text='"+GetLocalText(TEXT_replace)+"' and @hidden='false']");
	public String[] BS_Label_ExistingPassenger = OC("Existing Passenger","xpath=//*[@id='"+ID_passenger_linear_layout+"']");

	//Trip Itinerary
	public String[] TI_Text_TripItinerary = OC("Trip Itinerary Screen","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_booking_confirmed)+"']", "xpath=//*[@text='"+GetLocalText(TEXT_booking_confirmed)+"']");
	public String[] TI_Text_BookingReference = OC("Trip Itinerary Screen BookingReference","xpath=//*[@text='"+GetLocalText(TEXT_by_pnr)+"']");
	public String[] TI_Text_PNRno = OC("Trip Itinerary Screen PNRno","xpath=//*[@id='"+ID_booking_reference+"']", "xpath=//*[contains(@text,'Booking reference:')]");
	public String[] TI_Text_AddTravelOption = OC("TI_Text_AddTravelOption","xpath=//*[@text='"+GetLocalText(TEXT_add_travel_options_after_confirmation)+"' and @onScreen='true']");
	public String[] TI_ConformationCopy = OC("Trip Itinerary Screen ConformationCopy","xpath=//*[@text='"+GetLocalText(TEXT_your_booking_is_confirmed)+"']");
	public String[] TI_Text_ConformationEmail = OC("Trip Itinerary Screen ConformationEmail","xpath=//*[@id='"+ID_email+"']", "xpath=//*[contains(@text,'Confirmation has been sent to')]");
	public String[] TI_BTN_SignUp = OC("Trip Itinerary Screen Sigh Up Button","xpath=//*[@text='"+GetLocalText(TEXT_sign_up)+"']");
	public String[] TI_Link_SeatSelection = OC("Trip Itinerary Screen","xpath=//*[@text='"+GetLocalText(TEXT_select_seats_after_confirmation)+"' and @hidden='false' and @onScreen='true']");
	public String[] TI_Link_ViewTrip = OC("Trip Itinerary Screen vie trip Itinerary Button","xpath=//*[@text='"+GetLocalText(TEXT_view_trip_itinerary)+"' and @onScreen='true']");
	public String[] TI_BTN_Done = OC("Trip Itinerary Screen Done Button","xpath=//*[@id='"+ID_button_add_to_booked_flights+"']");
	public String[] TI_Menu_Option = OC("Menu Option","xpath=//*[@id='"+ID_action_show_menu+"']");
	public String[] TI_Menu_BookSimilarTrip = OC("BookSimilarTrip Menu Option","xpath=//*[@id='"+ID_button_book_similar_trip+"']");
	public String[] TI_Menu_EmailIrtinerary = OC("EmailIrtinerary Menu Option","xpath=//*[@id='"+ID_button_email_itinerary+"']");
	public String[] TI_Menu_RemoveFlight = OC("RemoveFlight Menu Option","xpath=//*[@id='"+ID_button_remove_flight+"']");
	public String[] TI_BTN_Remove = OC("Remove Button","xpath=//*[@id='"+ID_button_call+"']");
	public String[] TI_BTN_Close = OC("Close Button","xpath=//*[@id='"+ID_button_close+"']");
	public String[] TI_Text_PNR = OC("TI_Text_PNR","xpath=//*[@id='"+ID_itinerary_pnr+"' and @onScreen='true']");
	public String[] TI_Text_TripDate = OC("TI_Text_TripDate","xpath=//*[@id='"+ID_trip_dates+"']");
	public String[] TI_BTN_AddTravelOption = OC("TI_BTN_AddTravelOption","xpath=//*[@id='"+ID_button_cta1+"']", "xpath=//*[@text='Add travel options']");
	public String[] TI_BTN_BoardinfPass = OC("TI_BTN_BoardinfPass","xpath=//*[@id='"+ID_button_cta2+"']", "xpath=//*[contains(@accessibilityLabel,'Booking reference')]");
	//Passenger
	public String[] Passenger_Title_Header = OC("Page header","xpath=//*[@id='"+ID_toolbar_title+"']");
	public String[] BackButton = OC("Back button","xpath=//*[@contentDescription='back']","xpath=//*[@accessibilityIdentifier='icon-navbar-back']");
	public String[] Passenger_Label_Passenger = OC("Enter Passenger details label","xpath=//*[@text='"+GetLocalText(TEXT_enter_passenger_details)+"']");
	public String[] Passenger_Label_title = OC("Title Label","xpath=//*[@text='"+GetLocalText(TEXT_title)+"']");
	public String[] Passenger_Textbox_Salutation = OC("Salutation Textbox","xpath=//*[@text='"+GetLocalText(TEXT_title)+"']//following-sibling::*", "xpath=//*[@text='"+GetLocalText(TEXT_title)+"']");
	public String[] Passenger_DropDown_Salutation = OC("Salutation dropdown","xpath=//*[@id='"+ID_search_edit_text+"']", "xpath=//*[@accessibilityIdentifier='Mr']");
	public String[] Passenger_Text_Salutation = OC("Salutation item","xpath=//*[@id='"+ID_item_text+"' and @text='????']", "xpath=//*[@text='????']");	
	public String[] Passenger_Label_FirstName = OC("First name Label","xpath=//*[@text='"+GetLocalText(TEXT_first_name)+"']");
	public String[] Passenger_Textbox_FirstName = OC("First name Textbox","xpath=//*[@id='"+ID_first_name+"']","xpath=//*[contains(@accessibilityIdentifier,'First name')]");
	public String[] Passenger_Textbox_MiddleName = OC("Middle name Textbox","xpath=//*[@id='"+ID_middle_name+"']","xpath=//*[contains(@accessibilityIdentifier,'Middle name')]");
	public String[] Passenger_Label_LastName = OC("Last name Label","xpath=//*[@text='"+GetLocalText(TEXT_last_name)+"']");
	public String[] Passenger_Textbox_LastName = OC("Last name Textbox","xpath=//*[@id='"+ID_last_name+"']","xpath=//*[contains(@accessibilityIdentifier,'Last name')]");
	public String[] Passenger_Label_MobileNumber = OC("Mobile number Label","xpath=//*[@text='"+GetLocalText(TEXT_mobile_number_optional)+"']");
	public String[] Passenger_Text_SearchCountryCode = OC("Search Country Code Text Box","xpath=//*[@id='"+ID_country_area_code+"']", "xpath=//*[contains(@text,'+')]");


	public String[] Passenger_Textbox_AreaCode = OC("Area codeTextbox","xpath=//*[@id='"+ID_search_edit_text+"']","xpath=//*[@accessibilityIdentifier='searchTextField']");
	public String[] Passenger_Text_ListedCountry = OC("Listed country","xpath=//*[@id='"+ID_country_name_text+"']", "xpath=//*[@class='ACToolkit.ACSearchBar']");
	public String[] Passenger_Text_ListedCountryTxt = OC("Listed country","xpath=//*[@id='"+ID_country_name_text+"']", "xpath=//*[@class='_UIFieldEditorContentView']");
	public String[] Passenger_Textbox_MobileNumber = OC("Mobile number Textbox","xpath=//*[@id='"+ID_phone_number_edit_text+"']", "0", "xpath=//*[contains(@accessibilityIdentifier,'textFieldOptional')]", "4");
	public String[] Passenger_Label_EmailAddress = OC("Email address Label","xpath=//*[@text='"+GetLocalText(TEXT_enter_email_address)+"']");
	public String[] Passenger_Textbox_EmailAddress = OC("Email address Textbox","xpath=//*[@id='"+ID_email_edit_text+"']","xpath=//*[contains(@accessibilityIdentifier,'Email address')]");
	public String[] Passenger_Text_SecureFlightdata = OC("Secure fligth data text","xpath=//*[@text='"+GetLocalText(TEXT_secure_flight_data)+"']");
	public String[] Passenger_Label_Gender = OC("Gender Label","xpath=//*[@text='"+GetLocalText(TEXT_gender)+"']","xpath=//*[@text='Gender']");
	public String[] Passenger_BTN_Female = OC("Female option","xpath=//*[@text='"+GetLocalText(TEXT_female)+"']");
	public String[] Passenger_BTN_Male = OC("Male option","xpath=//*[@text='"+GetLocalText(TEXT_male)+"']");
	public String[] Passenger_Label_DOB = OC("DOB label","xpath=//*[@text='"+GetLocalText(TEXT_date_of_birth)+"']");
	public String[] Passenger_Label_DayofDOB = OC("Day of Dob label","xpath=//*[@text='"+GetLocalText(TEXT_day)+"']");
	public String[] Passenger_Label_MonthofDOB = OC("Month of DOB label","xpath=//*[@text='"+GetLocalText(TEXT_month)+"']");
	public String[] Passenger_Label_YearofDOB = OC("Year of DOB label","xpath=//*[@text='"+GetLocalText(TEXT_year)+"']");
	public String[] Passenger_Textbox_DayofDOB = OC("Day of Dob textbox","xpath=//*[@id='"+ID_days_clearable+"']","xpath=//*[@text='DD']");
	public String[] Passenger_TextBox_MonthofDOB = OC("Month of DOB textbox","xpath=//*[@id='"+ID_months_clearable+"']","xpath=//*[@text='MM']");
	public String[] Passenger_Textbox_YearofDOB = OC("Year of DOB textbox","xpath=//*[@id='"+ID_years_clearable+"']","xpath=//*[starts-with(@text,'YY')]");
	public String[] Passenger_Label_RedressName = OC("Redress name label","xpath=//*[@text='"+GetLocalText(TEXT_redress_number_if_you_have)+"' and @onScreen='true']");
	public String[] Passenger_Textbox_RedressName = OC("Redress name textbox ","xpath=//*[@id='"+ID_redress_number+"']","xpath=//*[contains(@accessibilityIdentifier,'Redress Number')]");
	public String[] Passenger_Textbox_KnownTravelnumber = OC("Known travel number textbox ","xpath=//*[@id='"+ID_known_traveler+"']","xpath=//*[contains(@accessibilityIdentifier,'Known traveler')]");
	public String[] Passenger_Label_MealPreference = OC("MealPreference Label","xpath=//*[@text='"+GetLocalText(TEXT_meal_preference)+"' and @onScreen='true']");
	public String[] Passenger_Text_aisle = OC("Asile option","xpath=//*[@id='"+ID_seat_aisle+"']");
	public String[] Passenger_Text_NoPreference = OC("No preference","xpath=//*[@id='"+ID_seat_no_preference+"']");
	public String[] Passenger_Text_Window = OC("Window seat preference","xpath=//*[@id='"+ID_seat_window+"']");
	public String[] Passenger_Text_Frequent_Flyer_program = OC("Frequent Flyer program","xpath=//*[@text='"+GetLocalText(TEXT_frequent_flyer_program)+"' and @onScreen='true']","xpath=//*[@class='UIImageView' and @height>0 and ./parent::*[@class='UIView'] and ./following-sibling::*[@class='ACToolkit.ACStackControl' and ./*[@accessibilityLabel='Frequent flyer program']]]");
	public String[] Passenger_Text_FrequentFlyer = OC("FrequentFlyertext","xpath=//*[contains(@text,'????')]");
	public String[] Passenger_Label_MealPreferenceOption = OC("MealPreference Option","xpath=//*[@id='"+ID_selected_meal_preference+"']", "xpath=//*[@accessibilityIdentifier='Meal preferenceLabel']");
	public String[] Passenger_Text_MealPreferenceOption = OC("MealPreference Item","xpath=//*[(contains(@text,'????')) and @onScreen='true']");
	public String[] Passenger_Text_SeatPreferenceOption = OC("MealPreference Item","xpath=//*[contains(@text,'????')]");	
	public String[] Passenger_BTN_Save = OC("Save button","xpath=//*[@text='"+GetLocalText(TEXT_save)+"' and @onScreen='true']","xpath=//*[@text='Save']");
	public String[] Passenger_Chkbx_Save = OC("Save Checkbox","xpath=//*[@class='com.aircanada.view.CircleAnimatedCheckBox']");
	public String[] Passenger_Chkbx_enable = OC("Save Checkbox selected","xpath=//*[@contentDescription='null checked' and @hidden='false' and @onScreen='true']");
	public String[] Passenger_Chkbx_disable = OC("Save Checkbox not selected","xpath=//*[@contentDescription='null not checked' and @hidden='false' and @onScreen='true']");
	public String[] Passenger_Error_Text = OC("Error message in the Save passenger","xpath=//*[@text='Please complete the missing information']","xpath=//*[@text='Please provide the missing information']");
	public String[] Passenger_Error_Title = OC("Title cannot be empty","xpath=//*[@text='Title cannot be empty' and @textColor='0xD22630']","xpath=//*[@text='Title cannot be empty' and @textColor='0xD7282E']");
	public String[] Passenger_Error_FName = OC("First name cannot be empty","xpath=//*[@text='First name cannot be empty' and @textColor='0xD22630']","xpath=//*[@text='First name cannot be empty' and @textColor='0xD7282E']");
	public String[] Passenger_Error_LName = OC("Last name cannot be empty","xpath=//*[@text='Last name cannot be empty' and @textColor='0xD22630']","xpath=//*[@text='Last name cannot be empty' and @textColor='0xD7282E']");
	
	
	
	//Payment
	public String[] Payment_Title_Heading = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_payment)+"']","xpath=//*[@accessibilityLabel='Payment']");
	//	public String[] Payment_BTN_BackButton = OC("Back button","xpath=//*[@contentDescription='back']");
	public String[] Payment_Label_SelectPayment = OC("Select Payment option label","xpath=//*[(@text='Credit card' or @text='"+GetLocalText(TEXT_credit_card)+"' or @text='"+GetLocalText(TEXT_payment_method)+"') and @hidden='false' and @onScreen='true']","xpath=//*[@text='Credit card']");
	public String[] Payment_Link_CreditCard = OC("Credit card option","xpath=//*[@id='"+ID_new_credit_card_button+"']","xpath=//*[@text='Add new credit card']");
	public String[] Payment_Link_GiftCard = OC("Gift card option","xpath=//*[@text='"+GetLocalText(TEXT_gift_card)+"']");
	public String[] Payment_Text_CreditCard = OC("Credit card text","xpath=//*[@text='"+GetLocalText(TEXT_credit_card)+"']","xpath=//*[@text='Credit card']");
	//CreditCard_Payment_CCP
	public String[] CCP_TextContains_Dynamic = OC("Sign up text","xpath=//*[contains(@text,'????')]");
	public String[] CCP_Text_Dynamic = OC("Dynamic Text","xpath=//*[@text='????']");
	public String[] CCP_Title_Heading = OC("Page header","xpath=//*[@id='"+ID_toolbar_title+"']");
	//	public String[] CCP_BTN_BackButton = OC("Back button","xpath=//*[@contentDescription='back']");
	public String[] CCP_Label_CCDetails = OC("Enter credit card details label","xpath=//*[@text='"+GetLocalText(TEXT_enter_credit_card_details)+"']","xpath=//*[@accessibilityLabel='New credit card']");
	public String[] CCP_Label_CCNumber = OC("Credit card number Label","xpath=//*[@text='"+GetLocalText(TEXT_credit_card_number)+"']","xpath=//*[@accessibilityLabel='Credit card number']");
	public String[] CCP_BTN_ScanCC = OC("Scan credit card button","xpath=//*[@id='"+ID_button_scan_credit_card+"']","xpath=//*[@accessibilityIdentifier='icon-camera-black']");
	public String[] CCP_Textbox_Ccnumber = OC("Credit card number textbox","xpath=//*[@id='"+ID_card_number_editText+"']", "xpath=//*[contains(@accessibilityIdentifier,'Credit card number')]");
	public String[] CCP_Label_ExpirationMonth = OC("Month label","xpath=//*[@text='"+GetLocalText(TEXT_month)+"']");
	public String[] CCP_Label_ExpirationYear = OC("Year label","xpath=//*[@text='"+GetLocalText(TEXT_year)+"']");
	public String[] CCP_Textbox_ExpirationMonth = OC("Expiration Month text box","xpath=//*[@id='"+ID_months_clearable+"']","xpath=//*[@placeholder='MM']");
	public String[] CCP_Textbox_ExpirationYear = OC("Expiration Year text box","xpath=//*[@id='"+ID_years_clearable+"']","xpath=//*[@placeholder='YY']");
	public String[] CCP_Label_Nameoncard = OC("Name on card ","xpath=//*[@text='"+GetLocalText(TEXT_card_holder_name)+"']");
	public String[] CCP_Textbox_Nameoncard = OC("Name on card textbox ","xpath=//*[@id='"+ID_text_holder_name_on_card+"']","xpath=//*[contains(@accessibilityIdentifier,'Name on card')]");
	public String[] CCP_Label_billingAddress = OC("Billing address","xpath=//*[@text='"+GetLocalText(TEXT_billing_address)+"']");
	public String[] CCP_Label_Country = OC("Country Label","xpath=//*[@text='"+GetLocalText(TEXT_country)+"']","xpath=//*[@class='UIImageView' and @height>0 and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@text='Country'] and ./preceding-sibling::*[@class='UIView']]");
	public String[] CCP_Select_Country = OC("Country Label","xpath=//*[@id='"+ID_icon_select_country_chevron+"']", "xpath=//*[@accessibilityLabel='Country']");	
	public String[] CCP_Dropdown_Country = OC("Country Dropdown","xpath=//*[@id='"+ID_search_edit_text+"']", "xpath=//*[@accessibilityIdentifier='searchTextField']");
	public String[] CCP_Label_State = OC("State Label","xpath=//*[@text='"+GetLocalText(TEXT_state)+"']");
	public String[] CCP_textbox_state = OC("State Textbox","xpath=//*[@id='"+ID_search_edit_text+"']","CCP_textbox_state");
	public String[] CCP_Country_Suggestion_item = OC("Country Suggestion item","xpath=//*[@id='"+ID_item_text+"']", "0", "xpath=//*[@accessibilityLabel='????']", "1");
	public String[] CCP_Label_Province = OC("Province label","xpath=//*[@text='"+GetLocalText(TEXT_province)+"']");
	public String[] CCP_List_Province = OC("Province label","xpath=//*[@id='"+ID_item_text+"' and @text='????']", "xpath=//*[@text='????' and @onScreen='true']");
	public String[] CCP_Dropdown_Province = OC("Province Dropdown","xpath=//*[@knownSuperClass='android.widget.Spinner' and ./preceding-sibling::*[@text='"+GetLocalText(TEXT_province)+"']]");
	public String[] CCP_Label_City = OC("Citylabel","xpath=//*[@text='"+GetLocalText(TEXT_city)+"']");
	public String[] CCP_Textbox_City = OC("City Textbox","xpath=//*[@id='"+ID_text_cityname+"' and @onScreen='true']","0", "xpath=//*[contains(@accessibilityIdentifier,'City')]", "1");
	public String[] CCP_Label_Street = OC("Street Address Label","xpath=//*[@text='"+GetLocalText(TEXT_street_address)+"' and @ onScreen='true']");
	public String[] CCP_Textbox_Street = OC("Street Address Textbox","xpath=//*[@id='"+ID_text_address+"']","xpath=//*[contains(@accessibilityIdentifier,'Street address')]");
	public String[] CCP_Label_PostalCode = OC("Postal code ","xpath=//*[@text='"+GetLocalText(TEXT_postal_code)+"']");
	public String[] CCP_Textbox_PostalCode = OC("Postal code textbox","xpath=//*[@id='"+ID_text_postal+"']","xpath=//*[contains(@accessibilityIdentifier,'Postal code')]");
	public String[] CCP_BTN_Save = OC("Save button","xpath=//*[@text='"+GetLocalText(TEXT_save)+"' and @onScreen='true']","xpath=//*[@text='Save']");
	public String[] CCP_BTN_PopYes = OC("POP yes button","xpath=//*[@text='"+GetLocalText(TEXT_yes)+"' and @onScreen='true']");
	public String[] CCP_Chkbx_Save = OC("Save checkbox","xpath=//*[@class='com.aircanada.view.CircleAnimatedCheckBox']");
	public String[] CCP_Textbox_NickName = OC("NickName Text box","id=text_nick_name","xpath=//*[contains(@accessibilityIdentifier,'Credit card nickname')]");
	public String[] CCP_Text_MissingInformationWarning = OC("Missing Information Warning","xpath=//*[@text='"+GetLocalText(TEXT_missing_information_credit_card)+"' and @onScreen='true']");
	//Create_Account_CA
	public String[] CA_Title_header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_create_account)+"' and @onScreen='true']");
	public String[] CA_Textbox_Email = OC("User email textbox","xpath=//*[@id='"+ID_text_email_address+"']","0","xpath=//*[contains(@accessibilityIdentifier,'Email address')]","0");
	public String[] CA_Label_Password = OC("User password label","xpath=//*[@text='"+GetLocalText(TEXT_choose_password)+"']");
	public String[] CA_Textbox_Password = OC("User password textbox","xpath=//*[@id='"+ID_text_password_validation+"']","0","xpath=//*[@accessibilityIdentifier='textFieldnil']","0");
	public String[] CA_Text_Passwordvalidation1 = OC("Password validation text ","xpath=//*[@text='"+GetLocalText(TEXT_minimum_8_characters)+"']");
	public String[] CA_Text_Passwordvalidation2 = OC("Password validation text ","xpath=//*[@text='"+GetLocalText(TEXT_one_upper_case)+"']");
	public String[] CA_Text_Passwordvalidation3 = OC("Password validation text ","xpath=//*[@text='"+GetLocalText(TEXT_one_lower_case)+"']");
	public String[] CA_BTN_Continue = OC("Continue button","xpath=//*[@text='"+GetLocalText(TEXT_continue_continue)+"']");
	public String[] CA_Label_name = OC("Name Label","xpath=//*[@text='"+GetLocalText(TEXT_name)+"' and @onScreen='true']");
	public String[] CA_Label_Salutation = OC("Salutation label","xpath=//*[@text='"+GetLocalText(TEXT_title)+"']");
	public String[] CA_Dropdown_Salutation = OC("Salutation Dropdown","xpath=//*[@hint='Select one']","xpath=//*[@accessibilityIdentifier='Select oneLabel']");
	public String[] CA_Text_Salutation = OC("Salutation Item","xpath=//*[@id='"+ID_item_text+"' and @text='????']");	
	public String[] CA_Label_Fname = OC("Frist name Label","xpath=//*[@text='"+GetLocalText(TEXT_first)+"']","xpath=//*[@accessibilityLabel='First name']");
	public String[] CA_Label_Fname_error = OC("Frist name Label","xpath=//*[@text='"+GetLocalText(TEXT_hint_first_name)+"' and @textColor='0xD22630']");
	public String[] CA_Label_Mname = OC("Middle name Label","xpath=//*[@text='"+GetLocalText(TEXT_middle)+"']","xpath=//*[@accessibilityLabel='Middle name (if used)']");
	public String[] CA_Label_Lname = OC("Last name Label","xpath=//*[@text='"+GetLocalText(TEXT_last)+"']","xpath=//*[@accessibilityLabel='Last name']");
	public String[] CA_Label_Lname_error = OC("Last name Label","xpath=//*[@text='"+GetLocalText(TEXT_hint_last_name)+"' and @textColor='0xD22630']");
	public String[] CA_Textbox_Fname = OC("Frist name Text box ","xpath=//*[@id='"+ID_text_first_name+"' and @onScreen='true']","xpath=//*[@accessibilityIdentifier='FirstNameTextField']");
	public String[] CA_Label_Mobile_error = OC("Mobile number label","xpath=//*[@text='"+GetLocalText(TEXT_mobile_number)+"' and   //*[@textColor='0xD22630']]");
	public String[] CA_Textbox_Mname = OC("Middle name Text box ","xpath=//*[@id='"+ID_text_middle_name+"' and @onScreen='true']","xpath=//*[@accessibilityIdentifier='MiddleNameTextField']");
	public String[] CA_Textbox_Lname = OC("Last name Text box ","xpath=//*[@id='"+ID_text_last_name+"' and @onScreen='true']","xpath=//*[@accessibilityIdentifier='LastNameTextField']");
	public String[] CA_Label_Contact = OC("contact Label","xpath=//*[@text='"+GetLocalText(TEXT_contact)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='CA +1']");
	public String[] CA_Textbox_UserEmail = OC("Email address textbox","xpath=//*[ @class='com.aircanada.view.ClearableEditTextView' and ./preceding-sibling::*[@text='"+GetLocalText(TEXT_enter_email_address)+"']]","CA_Textbox_UserEmail");
	public String[] CA_Label_Mobile = OC("Mobile number label","xpath=//*[@text='"+GetLocalText(TEXT_mobile_number)+"']","xpath=//*[@accessibilityLabel='Mobile number']");
	public String[] CA_Textbox_Areacode = OC("Are code textbox","xpath=//*[@id='"+ID_country_area_code+"' and @onScreen='true']","xpath=//*[contains(@text,' +')]");
	public String[] CA_Textbox_Phonenumber = OC("Phone number textbox","xpath=//*[@id='"+ID_phone_number_edit_text+"' and @onScreen='true']","xpath=//*[@placeholder='' and @class='UITextField' and ./parent::*[@placeholder='']]");
	public String[] CA_Label_Aeroplane = OC("Aeroplan label","xpath=//*[@text='"+GetLocalText(TEXT_frequent_flyer_short_AC)+"' and @onScreen='true']","xpath=//*[@text='Aeroplan' and @onScreen='true']");
	public String[] CA_Textbox_AeroplaneNumber = OC("Aeroplan number text","xpath=//*[@id='"+ID_edit_text_aeroplan_number+"' and @onScreen='true']","xpath=//*[@accessibilityIdentifier='aeroplanTextField']");
	public String[] CA_Title_headerAccountExsists = OC("Account Exists","xpath=//*[@text='"+GetLocalText(TEXT_account_exists_title)+"']");
	public String[] CA_BTN_Update = OC("Update button","xpath=//*[@text='"+GetLocalText(TEXT_update)+"']");
	public String[] CA_Text_UsedEmaiId = OC("Create Account Screen","xpath=//*[@text='"+GetLocalText(TEXT_create_account_error_existing)+"' and @onScreen='true']");
	public String[] CA_Text_Contact = OC("Create Account Screen","xpath=//*[@text='"+GetLocalText(TEXT_contact)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Contact info']");
	public String[] CA_BTN_CreateAccount = OC("Create Account Screen","xpath=//*[@text='"+GetLocalText(TEXT_create_account)+"' and @textColor='0xFFFFFF' and @onScreen='true']","xpath=//*[@class='ACToolkit.ACButton' and @onScreen='true']");
	public String[] CA_Text_AccountExists = OC("Create Account Screen","xpath=//*[@text='"+GetLocalText(TEXT_account_exists_alert)+"' and @onScreen='true']");
	public String[] CA_BTN_SignIn = OC("Sign in Text","xpath=//*[@text='"+GetLocalText(TEXT_login)+"' and @onScreen='true']");
	//Profile_Setting_PS
	public String[] PS_Title_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_profile_and_settings)+"']","xpath=//*[@accessibilityLabel='Settings & profile']");
	//	public String[] PS_BTN_Backbutton=OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	public String[] PS_Text_Aircanada = OC("Title of the page","xpath=//*[@text='"+GetLocalText(TEXT_mobile_plus)+"']");
	public String[] PS_BTN_Signup = OC("Create account button","xpath=//*[@id='"+ID_create_account_button+"']","0","xpath=//*[@accessibilityLabel='Create account']","1");
	public String[] PS_BTN_Login = OC("Login Button","xpath=//*[@id='"+ID_login_button+"']","xpath=//*[@text='Sign in']");
	public String[] PS_BTN_Profile = OC("Edit user profile button","xpath=//*[@id='"+ID_edit_user_profile_empty_button+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Personal profile']");
	public String[] PS_BTN_Preference = OC("Preference button","xpath=//*[@id='"+ID_preferences_button+"' and @onScreen='true']","xpath=//*[@text='Preferences']");
	public String[] PS_BTN_Notification = OC("Notification button","xpath=//*[@id='"+ID_notifications_button+"' and @onScreen='true']","xpath=//*[@text='Notifications']");
	public String[] PS_Text_Username = OC("Logged in User email","xpath=//*[@id='"+ID_user_name+"']","0","xpath=//*[@class='ACToolkit.ACStackControl']","1");
	public String[] PS_Text_UserEmail = OC("Logged in User email","xpath=//*[@id='"+ID_user_email+"']");
	public String[] PS_BTN_Notificati = OC("Logged in change password","xpath=//*[@id='"+ID_change_password_button+"']");
	public String[] PS_BTN_LinkWithAeroplane = OC("Link with Aeroplane option","xpath=//*[@id='"+ID_link_aeroplan_button+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Link Aeroplan account']");
	public String[] PS_BTN_Altitudecard = OC("Altitude Digital Card","xpath=//*[@id='"+ID_altitude_card_button+"']","xpath=//*[@text='Altitude card']");
	public String[] PS_BTN_SavePassenger = OC("Save passenger details button","xpath=//*[@id='"+ID_saved_passengers_button+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Your passengers']");
	public String[] PS_BTN_DeletePassenger = OC("Delete passenger button","xpath=//*[@text='"+GetLocalText(TEXT_delete)+"' and @onScreen='true']");
	public String[] PS_BTN_SavedCreditcard = OC("Save Credit card details button","xpath=//*[@id='"+ID_saved_creditcards_button+"' and @onScreen='true']","xpath=//*[@text='Your credit cards']");
	public String[] PS_BTN_Logout = OC("Logout Button","xpath=//*[@id='"+ID_logout_button+"' and @onScreen='true']","xpath=//*[@text='Sign out' and @onScreen='true' and @top='true']");
	public String[] PS_BTN_LinkedWithAeroplane = OC("Linked with Aeroplane option","xpath=//*[@id='"+ID_aeroplan_name+"' and @onScreen='true']");
	public String[] PS_TXT_Unauthenticated = OC("PS_TXT_Unauthenticated","xpath=//*[@text='"+GetLocalText(TEXT_not_logged_in_invalid_credentials)+"']");
	public String[] PS_BTN_RemovePassenger = OC("PS_BTN_RemovePassenger","xpath=//*[@text='"+GetLocalText(TEXT_delete_passenger)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Delete passenger' and @onScreen='true']");
	public String[] PS_TXT_SavedCards = OC("PS_TXT_SavedCards","xpath=//*[@text='"+GetLocalText(TEXT_saved_credit_cards)+"']","xpath=//*[@accessibilityIdentifier='screen-header-Credit cards']");
	public String[] PS_TXT_SavedCardsNumber = OC("PS_TXT_SavedCardsNumber","xpath=//*[@id='"+ID_credit_card_number+"']","1", "xpath=//*[@class='ACApp.CurrentPaymentView']", "0");
	public String[] PS_TXT_EditCardsNumber = OC("PS_TXT_EditCardsNumber","xpath=//*[@text='"+GetLocalText(TEXT_edit_credit_card_title)+"']","xpath=//*[@text='Edit credit card']");
	public String[] PS_TXT_RemoveCardsNumber = OC("PS_TXT_RemoveCardsNumber","xpath=//*[@id='"+ID_button_delete+"' and @onScreen='true']","xpath=//*[@text='Delete card']");

	//Altitude status AS
	public String[] AS_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_altitude_digital_card)+"']","xpath=//*[@text='Altitude card']");
	public String[] AS_Textbox_Aeroplannumber= OC("Aeroplan Number Textbox","xpath=//*[@id='"+ID_card_number_editText+"']","xpath=//*[@accessibilityIdentifier='aeroplanTextField']");
	public String[] AS_Textbox_Password= OC("Password Textbox","xpath=//*[@id='"+ID_text_password_validation+"' and @onScreen='true']","xpath=//*[@accessibilityIdentifier='textFieldnil']");
	public String[] AS_BTN_Confirm= OC("Confirm Button","xpath=//*[@id='"+ID_button_link_aeroplan_continue+"' and @onScreen='true']","xpath=//*[@text='Sign in']");
	public String[] AS_Image_QR= OC("QR Code","xpath=//*[@id='"+ID_two_d_code_image+"']","xpath=//*[@class='UIImageView' and @height>0 and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@accessibilityLabel='Progress halted']]");
	public String[] AS_Imageinfo= OC("info","xpath=//*[@id='"+ID_two_d_code_image+"']","xpath=//*[@accessibilityIdentifier='info-icon']");
	public String[] AS_Title_info= OC("info title","xpath=//*[@id='"+ID_two_d_code_image+"']","xpath=//*[@text='Altitude information']");
	public String[] AS_BTN_Delete= OC("Delete button","xpath=//*[@id='"+ID_two_d_code_image+"']","xpath=//*[@text='Delete Altitude card']");
	public String[] AS_Title_Error= OC("Error Message","xpath=//*[@id='"+ID_two_d_code_image+"']","xpath=//*[@accessibilityLabel='Delete Altitude card']");
	public String[] AS_BTN_YError= OC("Error Messag Yes buttone","xpath=//*[@id='"+ID_two_d_code_image+"']","xpath=//*[@text='Delete']");
	public String[] AS_BTN_NError= OC("Error Message No Button","xpath=//*[@id='"+ID_two_d_code_image+"']","xpath=//*[@text='Cancel']");
	//Personal_inf_PI
	public String[] PI_Title_header = OC("page header","xpath=//*[@text='"+GetLocalText(TEXT_personal_information)+"']","xpath=//*[@accessibilityLabel='Personal information']");
	public String[] PI_Text_optionalText = OC("Optional text","xpath=//*[@text='"+GetLocalText(TEXT_all_fields_are_optional)+"']");
	public String[] PI_Label_Name = OC("Name Label","xpath=//*[@text='"+GetLocalText(TEXT_name)+"']");
	public String[] PI_Label_Salutation = OC("Salutation label","xpath=//*[@text='"+GetLocalText(TEXT_title)+"']");
	public String[] PI_Dropdown_Salutation = OC("Salutation dropdown","xpath=//*[@id='"+ID_select_title_field+"']","xpath=//*[@accessibilityIdentifier='Select oneLabel']");
	public String[] PI_TextBox_Salutation = OC("Salutation dropdown","xpath=//*[@id='"+ID_select_title_edit_text+"']");
	public String[] PI_Text_Salutation = OC("Salutation Item","xpath=//*[@id='"+ID_item_text+"' and @text='????']","xpath=//*[@class='ACApp.OptionChooserTableViewCell' and ./*[./*[@text='????']]]");
	public String[] PI_Label_Firstname = OC("First label","xpath=//*[@text='"+GetLocalText(TEXT_first)+"']","xpath=//*[@accessibilityLabel='First name']");
	public String[] PI_Textbox_Firstname = OC("First textbox","xpath=//*[@id='"+ID_first_name_edit_text+"' and @onScreen='true']","xpath=//*[@placeholder='' and @class='UITextField' and ./preceding-sibling::*[@text='First name'] and ./parent::*[@class='UIView']]");
	public String[] PI_BTN_CloseButton = OC("Delete Button","xpath=//*[@id='"+ID_first_name_edit_text+"' and @onScreen='true']","xpath=//*[@class='UIButton' and ./*[@class='UIImageView'] and ./parent::*[@placeholder='']]");
	
	//
	public String[] PI_Label_MiddleName = OC("Middle label","xpath=//*[@text='"+GetLocalText(TEXT_middle)+"']","xpath=//*[@accessibilityLabel='Middle name (if used)']");
	public String[] PI_Textbox_MiddleName = OC("Middle textbox","xpath=//*[@id='"+ID_middle_name_edit_text+"' and @onScreen='true']","xpath=//*[@placeholder='' and @class='UITextField' and ./preceding-sibling::*[@text='Middle name (if used)'] and ./parent::*[@class='UIView']]");
	public String[] PI_Label_LastName = OC("Last name label","xpath=//*[@text='"+GetLocalText(TEXT_last)+"']","xpath=//*[@accessibilityLabel='Last name']");
	public String[] PI_Textbox_LastName = OC("Last name textbox","xpath=//*[@id='"+ID_last_name_edit_text+"' and @onScreen='true']","xpath=//*[@placeholder='' and @class='UITextField' and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@text='Last name']]");
	public String[] PI_Label_Contact = OC("Contact label","xpath=//*[@text='"+GetLocalText(TEXT_contact)+"' and @onScreen='true']");
	public String[] PI_Label_Email = OC("Email address label","xpath=//*[@text='"+GetLocalText(TEXT_enter_email_address)+"']","xpath=//*[@accessibilityLabel='Email address']");
	public String[] PI_Textbox_Email = OC("Email address  textbox","xpath=//*[@id='"+ID_email_edit_text+"' and @onScreen='true']","xpath=//*[@placeholder='' and @class='UITextField' and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@text='Email address']]");
	public String[] PI_Label_Mobile = OC("Mobile number label","xpath=//*[@text='"+GetLocalText(TEXT_mobile_number)+"']","xpath=//*[@accessibilityLabel='Mobile number']");
	public String[] PI_Label_AreaCode = OC("Area Code","xpath=//*[@text='"+GetLocalText(TEXT_mobile_number)+"' and @onScreen='true']/..//following-sibling::*/*/*");
	public String[] PI_TextBox_countryAreaCode = OC("Country area code textbox","xpath=//*[@id='"+ID_search_edit_text+"']","xpath=//*[@class='ACToolkit.ACBottomBorderView' and ./*[@class='UIImageView']]");
	public String[] PI_TextBox_countryAreaCodesearch = OC("Country area code textbox","xpath=//*[@id='"+ID_search_edit_text+"']","xpath=//*[@placeholder='Select country code']");
	
	
	public String[] PI_Text_ListedCountry = OC("Listed country","xpath=//*[@id='"+ID_country_name_text+"']");
	public String[] PI_TextBox_Phonenumber = OC("Phone number textbox","xpath=//*[@id='"+ID_phone_number_edit_text+"']","xpath=//*[@placeholder='' and @class='UITextField' and ./parent::*[@placeholder='']]");
	public String[] PI_Label_Aeroplan = OC("Aeroplan label","xpath=//*[@text='"+GetLocalText(TEXT_frequent_flyer_short_AC)+"']");
	public String[] PI_Label_AeroplanNumber = OC("Aeroplan number label","xpath=//*[@text='"+GetLocalText(TEXT_aeroplan_number)+"']");
	public String[] PI_TextBox_AeroplanNumber = OC("Aeroplan number textbox","xpath=//*[@id='"+ID_card_number_editText+"' and @onScreen='true']","xpath=//*[@class='UITextField' and ./parent::*[@class='ACToolkit.ACBottomBorderView']]");
	public String[] PI_Label_PreferrredAirport = OC("Preferred airport label","xpath=//*[@text='"+GetLocalText(TEXT_preferred_airport)+"']","xpath=//*[@accessibilityLabel='Preferred airport']");
	public String[] PI_TextBox_PreferrredAirport = OC("Preferred airport textbox","xpath=//*[@id='"+ID_select_preferred_airport_edit_text+"' and @onScreen='true']","xpath=//*[@class='UIImageView' and @height>0 and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@text='Preferred airport'] and ./preceding-sibling::*[@class='UIView']]");
	public String[] PI_TextBox_PreferrredAirport1 = OC("Preferred airport textbox1","xpath=//*[@id='"+ID_search_edit_text+"']","xpath=//*[@accessibilityIdentifier='searchTextField']");
	public String[] PI_Text_PreferredAirport = OC("Preferred Airport","xpath=//*[@id='"+ID_code+"' and @text='????']","xpath=//*[@accessibilityIdentifier='????']");
	public String[] PI_BTN_Update = OC("Update button","xpath=//*[@id='"+ID_update_button+"' and @onScreen='true']","xpath=//*[@text='Update']");
	public String[] PI_Text_UserName = OC("User Name","xpath=//*[@id='"+ID_user_name+"' and @onScreen='true']","0","xpath=//*[@class='ACToolkit.ACStackControl']","2");
	//Preferences
	public String[] Preference_Title_Header = OC("Page header","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_preferences)+"' and @onScreen='true']","xpath=//*[@accessibilityIdentifier='screen-header-Preferences']");
	public String[] Preference_BTN_AppPasscode = OC("App Passcode button","xpath=//*[@text='"+GetLocalText(TEXT_app_passcode)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='App passcode']");
	public String[] Preference_BTN_passcodesync= OC("Calendar sync thumb","xpath=//*[@id='"+ID_thumb_frame+"' and @onScreen='true']","xpath=(//*[@class='_UISwitchInternalViewNeueStyle1' and ./parent::*[./preceding-sibling::*[@text='App passcode']]]/*[@class='UIView' and ./*[@class='UIView']])[1]");
	public String[] Preference_BTN_Label = OC("Labels button","xpath=//*[@text='"+GetLocalText(TEXT_labels)+"' and @onScreen='true']");
	public String[] Preference_Text_EditLabel = OC("Labels button","xpath=//*[@text='"+GetLocalText(TEXT_edit_labels)+"']");
	public String[] Preference_Title_EditLabel = OC("Labels Header","xpath=//*[@text='"+GetLocalText(TEXT_edit_labels)+"']","xpath=//*[@text='Labels']");
	public String[] Preference_Label_CalendarSync = OC("Calendar sync Label","xpath=//*[@text='"+GetLocalText(TEXT_calendar_sync)+"']","xpath=//*[@text='Calendar Sync']");
	public String[] Preference_BTN_CalendarSyncThumb = OC("Calendar sync thumb","xpath=//*[@id='"+ID_thumb_frame+"' and @onScreen='true']","xpath=(//*[@class='_UISwitchInternalViewNeueStyle1']/*[@class='UIView' and ./*[@class='UIView']])[1]");
	public String[] Preference_BTN_CalendarSyncbutton = OC("Calendar sync Button","xpath=//*[@id='"+ID_track_frame+"']");
	public String[] Preference_LAbel_Calendar = OC("Calendar label","xpath=//*[@text='"+GetLocalText(TEXT_default_calendar)+"']");
	public String[] Preference_Text_CalendarValue = OC("Calendar value","xpath=//*[@text='"+GetLocalText(TEXT_flights_alert_minutes_options_0)+"']");
	public String[] Preference_BTN_CalendarArrow = OC("Calendar arrow button","xpath=//*[@class='android.widget.ImageView' and ./parent::*[@id='show_default_calendar']]");
	public String[] Preference_LAbel_Alert = OC("Alert label","xpath=//*[@text='"+GetLocalText(TEXT_alert)+"']");
	public String[] Preference_Text_Alert = OC("Alert value","xpath=//*[@text='"+GetLocalText(TEXT_flights_alert_minutes_options_0)+"']");
	public String[] Preference_BTN_AlertArrow = OC("Alert arrow button","xpath=//*[@class='android.widget.ImageView' and ./parent::*[@id='"+ID_show_alert+"']]");
	public String[] Preference_Label_OfflineFlightSchedule = OC("Offline Flight Schedule Label","xpath=//*[@text='"+GetLocalText(TEXT_offline_flight_schedule)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Offline flight schedule']");
	public String[] Preference_Label_CurrentFlightScheduleVersion = OC("current Flight Schedule Label","xpath=//*[@text='"+GetLocalText(TEXT_current_flight_schedule_version)+"']");
	public String[] Preference_BTN_DownloadButton = OC("DownloadButton","xpath=//*[@id='"+ID_download_now_button+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Download now']");
	public String[] Preference_Label_AutomaticUpdate = OC("Automatic Update label","xpath=//*[@text='"+GetLocalText(TEXT_automatic_update)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Automatic update']");
	public String[] Preference_BTN_AutomaticUpdateoFF = OC("Automatic Update off  value","xpath=//*[@id='"+ID_button_off+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Off']");
	public String[] Preference_BTN_AutomaticUpdateWifi = OC("Automatic Update wifi value","xpath=//*[@id='"+ID_button_wifi+"' and @onScreen='true']","xpath=//*[@text='Wi-Fi only']");
	public String[] Preference_BTN_AutomaticUpdateon = OC("Automatic Update on value","xpath=//*[@id='"+ID_button_on+"' and @onScreen='true']");
	public String[] Preference_BTN_AutomaticUpdateOnSelected = OC("Automatic Update on value","xpath=//*[@id='"+ID_button_on+"' and @textColor='0xFFFFFF' and @onScreen='true']","xpath=//*[@text='On' and @textColor='0x4B4E54']");
	public String[] Preference_BTN_AutomaticUpdateOffSelected = OC("Automatic Update on value","xpath=//*[@id='"+ID_button_off+"' and @textColor='0xFFFFFF' and @onScreen='true']","xpath=//*[@text='Off' and @textColor='0x4B4E54']");
	public String[] Preference_Text_Units = OC("Preference_Text_Units Screen","xpath=//*[@text='"+GetLocalText(TEXT_units)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Units']");
	public String[] Preference_Text_Temp = OC("Preference_Text_Temp Screen","xpath=//*[@text='"+GetLocalText(TEXT_temperature)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Temperature']");
	public String[] Preference_Text_Celcius = OC("Preference_Text_Celcius Screen","xpath=//*[@id='"+ID_button_celcius+"' and @onScreen='true']","xpath=//*[@text='Celsius (°C)']");
	public String[] Preference_Text_Fahren = OC("Preference_Text_Fahren Screen","xpath=//*[@id='"+ID_button_fahrenheit+"' and @onScreen='true']","xpath=//*[@text='Fahrenheit (°F)']");
	public String[] Preference_Text_Distance = OC("Preference_Text_Distance Screen","xpath=//*[@text='"+GetLocalText(TEXT_distance)+"' and @onScreen='true']","xpath=//*[@text='Distance']");
	public String[] Preference_Text_Km = OC("Preference_Text_Km Screen","xpath=//*[@id='"+ID_button_kilometers+"' and @onScreen='true']","xpath=//*[@text='Kilometre (km)']");
	public String[] Preference_Text_Miles = OC("Preference_Text_Miles Screen","xpath=//*[@id='"+ID_button_miles+"' and @onScreen='true']","xpath=//*[@text='Mile (mi)']");
	public String[] Preference_Text_Weight = OC("Preference_Text_Weight Screen","xpath=//*[@text='"+GetLocalText(TEXT_weight)+"' and @onScreen='true']","xpath=//*[@text='Weight']");
	public String[] Preference_Text_Kg = OC("Preference_Text_Kg Screen","xpath=//*[@id='"+ID_button_kilogram+"' and @onScreen='true']","xpath=//*[@text='Kilogram (kg)']");
	public String[] Preference_Text_Pound = OC("Preference_Text_Pound Screen","xpath=//*[@id='"+ID_button_pound+"' and @onScreen='true']","xpath=//*[@text='Pound (lb)']");
	public String[] Preference_Text_FahrenSelected = OC("Preference_Text_FahrenSelected Screen","xpath=//*[@id='"+ID_button_fahrenheit+"' and @textColor='0xFFFFFF' and @onScreen='true']","xpath=//*[@text='Fahrenheit (°F)' and @textColor='0x4B4E54']");
	public String[] Preference_Text_MilesSeletced = OC("Preference_Text_MilesSeletced Screen","xpath=//*[@id='"+ID_button_miles+"' and @textColor='0xFFFFFF' and @onScreen='true']","xpath=//*[@text='Mile (mi)' and @textColor='0x4B4E54']");
	public String[] Preference_Text_PoundSelected = OC("Preference_Text_PoundSelected Screen","xpath=//*[@id='"+ID_button_pound+"' and @textColor='0xFFFFFF' and @onScreen='true']","xpath=//*[@text='Pound (lb)' and @textColor='0x4B4E54']");
	//Saved_Passenger_SP
	public String[] SP_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_passengers)+"']","xpath=//*[contains(@text,'Passenger')]");
	public String[] SP_Text_UserInfo = OC("User info","xpath=//*[@class='android.support.v7.widget.CardView']");
	public String[] SP_BTN_AddnewPassenger = OC("Add new passenger button","xpath=//*[@id='"+ID_add_new_passenger+"' and @text='"+GetLocalText(TEXT_add_passenger)+"' and @hidden='false']","xpath=//*[@text='Add new passenger' and @onScreen='true']");
	//	public String[] SP_BTN_Backbutton = OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	public String[] SP_Text_SavePassenger = OC("Saved Passenger","xpath=//*[@id='"+ID_saved_passenger+"']","xpath=//*[@class='UIView' and @height>0 and ./*[@accessibilityLabel='Passenger Number Collapsible Container']]");
	public String[] SP_Text_PassengerName = OC("SP_Text_PassengerName","xpath=//*[@id='"+ID_passenger_fullname+"']","1", "xpath=//*[@accessibilityIdentifier='sm-red-arrow' and @top='true']", "1");
	//Edit Passenger
	public String[] EP_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_edit_passenger)+"']","xpath=//*[contains(@accessibilityLabel,'passenger details')]");
	//New Passenger
	public String[] NP_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_new_passenger)+"']");
	//	public String[] NP_BTN_Backbutton = OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	public String[] NP_Label_Title = OC("Title label","xpath=//*[@text='"+GetLocalText(TEXT_title)+"']","xpath=//*[@accessibilityIdentifier='Select oneLabel']");
	public String[] NP_TextBox_Title = OC("Title text box","xpath=//*[@id='"+ID_select_title_edit_text+"']","xpath=//*[@accessibilityIdentifier='Select oneLabel']");
	public String[] NP_Text_Salutation = OC("Salutation item","xpath=//*[@id='"+ID_item_text+"' and @text='????']","xpath=//*[@text='????']");
	public String[] NP_Label_Firstname = OC("First Name label","xpath=//*[@text='"+GetLocalText(TEXT_first_name)+"']");
	public String[] NP_TextBox_Firstname = OC("First Name text box","xpath=//*[@id='"+ID_first_name+"']","xpath=//*[@placeholder='' and @class='UITextField' and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@accessibilityLabel='First name']]");
	public String[] NP_TextBox_Middlename = OC("Middle Name text box","xpath=//*[@id='"+ID_middle_name+"']","xpath=//*[contains(@accessibilityIdentifier,'Middle name')]");
	public String[] NP_Label_Lastname = OC("Middle Name label","xpath=//*[@text='"+GetLocalText(TEXT_last_name)+"']","xpath=//*[contains(@accessibilityIdentifier,'Last name')]");
	public String[] NP_TextBox_Lastname = OC("Last Name Name text box","xpath=//*[@id='"+ID_last_name+"']","xpath=//*[contains(@accessibilityIdentifier,'Last name')]");
	public String[] NP_Textbox_Aeracode = OC("Aera code textbox","xpath=//*[@id='"+ID_country_area_code+"']","xpath=//*[contains(@text,'+')]");
	public String[] NP_Text_ListedCountry = OC("Listed country","xpath=//*[@id='"+ID_item_text+"']","0","xpath=//*[@class='UILabel']","1");
	public String[] NP_Text_Listedareacode = OC("Listed Area code","xpath=//*[@id='"+ID_country_area_code+"']","xpath=//*[@class='UILabel']");
	public String[] NP_Textbox_Mobile = OC("Mobile number textbox","xpath=//*[@id='"+ID_phone_number_edit_text+"']");
	public String[] NP_Text_Mobile = OC("Mobile number textbox","xpath=//*[@id='"+ID_phone_number_edit_text+"']","0","xpath=//*[contains(@accessibilityIdentifier,'textFieldOptional')]","4");
	public String[] NP_Text_Email = OC("Email address textbox","xpath=//*[@id='"+ID_email_edit_text+"']","xpath=//*[contains(@accessibilityIdentifier,'Email address')]");
	public String[] NP_Label_SecureFlight = OC("Secure Flight data","xpath=//*[@text='"+GetLocalText(TEXT_secure_flight_data)+"']");
	public String[] NP_Label_GenderDetails = OC("Gender details","xpath=//*[@text='"+GetLocalText(TEXT_secure_flight_data_disclaimer)+"']");
	public String[] NP_BTN_Female = OC("Female option","xpath=//*[@text='"+GetLocalText(TEXT_female)+"']");
	public String[] NP_BTN_Femaleselected = OC("Female option selected","xpath=//*[@id='"+ID_female_gender+"' and @ textColor='0xFFFFFF']");
	public String[] NP_BTN_Maleselected = OC("Male option selected","xpath=//*[@id='"+ID_male_gender+"' and @ textColor='0xFFFFFF']");
	public String[] NP_BTN_Male = OC("Male option","xpath=//*[@text='"+GetLocalText(TEXT_male)+"']");
	public String[] NP_Label_DOB = OC("DOB label","xpath=//*[@text='"+GetLocalText(TEXT_date_of_birth)+"']");
	public String[] NP_Label_DayofDOB = OC("Day of Dob label","xpath=//*[@text='"+GetLocalText(TEXT_day)+"']");
	public String[] NP_Label_MonthofDOB = OC("Month of DOB label","xpath=//*[@text='"+GetLocalText(TEXT_month)+"']");
	public String[] NP_Label_YearofDOB = OC("Year of DOB label","xpath=//*[@text='"+GetLocalText(TEXT_year)+"']");
	public String[] NP_Textbox_DayofDOB = OC("Day of Dob textbox","xpath=//*[@id='"+ID_days_clearable+"']","xpath=//*[@accessibilityIdentifier='longDateDayTextField']");
	public String[] NP_TextBox_MonthofDOB = OC("Month of DOB textbox","xpath=//*[@id='"+ID_months_clearable+"']","xpath=//*[@accessibilityIdentifier='longDateMonthTextField']");
	public String[] NP_Textbox_YearofDOB = OC("Year of DOB textbox","xpath=//*[@id='"+ID_years_clearable+"']","xpath=//*[@accessibilityIdentifier='longDateYearTextField']");
	public String[] NP_Label_RedressName = OC("Redress name label","xpath=//*[@text='"+GetLocalText(TEXT_redress_number_if_you_have)+"' and @onScreen='true']","xpath=//*[@text='Redress Number (optional)']");
	public String[] NP_Textbox_RedressName = OC("Redress name textbox ","xpath=//*[aircanadamobile2@id='"+ID_redress_number+"']","xpath=//*[contains(@accessibilityIdentifier,'Redress')]");
	public String[] NP_Textbox_KnownTravelnumber = OC("Known travel number textbox ","xpath=//*[@id='"+ID_known_traveler+"' and @onScreen='true']","xpath=//*[contains(@accessibilityIdentifier,'Known traveler')]");
	public String[] NP_Label_MealPreference = OC("MealPreference Label","xpath=//*[@text='"+GetLocalText(TEXT_meal_preference)+"' and @onScreen='true']","xpath=//*[@accessibilityIdentifier='Meal preferenceLabel']");
	public String[] NP_Text_aisle = OC("Asile option","xpath=//*[@id='"+ID_seat_aisle+"']","xpath=//*[@text='Aisle']");
	public String[] NP_Text_aisleselected = OC("Asile option selected","xpath=//*[@text='"+GetLocalText(TEXT_aisle)+"'and @textColor='0xFFFFFF']");
	public String[] NP_Text_NoPreference = OC("No preference","xpath=//*[@id='"+ID_seat_no_preference+"']","xpath=//*[@text='No preference']");
	public String[] NP_Text_NoPreferenceselected = OC("No preference Selected","xpath=//*[@text='"+GetLocalText(TEXT_no_preference)+"' and @textColor='0xFFFFFF']");
	public String[] NP_Text_Window = OC("Window seat preference","xpath=//*[@id='"+ID_seat_window+"']");
	public String[] NP_Text_windowselected = OC("Window seat preference selected","xpath=//*[@text='"+GetLocalText(TEXT_window)+"'and @textColor='0xFFFFFF']");

	public String[] NP_Label_Frequent_Flyer_program = OC("Frequent Flyer program","xpath=//*[@text='"+GetLocalText(TEXT_frequent_flyer_program)+"']");

	public String[] NP_Text_Frequent_Flyer_program = OC("Frequent Flyer program","xpath=//*[@text='"+GetLocalText(TEXT_frequent_flyer_program)+"']//following-sibling::*[1]");
	public String[] NP_Label_MealPreferenceOption = OC("MealPreference Option","xpath=//*[@id='"+ID_selected_meal_preference+"']","xpath=//*[@class='UIImageView' and @height>0 and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@accessibilityLabel='Meal preference'] and ./preceding-sibling::*[@class='UIView']]");
	public String[] NP_Text_MealPreferenceOption = OC("MealPreference Item","xpath=//*[(contains(@text,'????')) and @onScreen='true']");
	public String[] NP_Text_SeatPreferenceOption = OC("Seat Peference Item","xpath=//*[contains(@text,'????') and @onScreen='true']");
	public String[] NP_Text_Nationality_Residence = OC("Nationality_Residence Text","xpath=//*[@text='"+GetLocalText(TEXT_nationality_preference_title)+"' and @onScreen='true']");
	public String[] NP_Text_Passport = OC("Passport Details","xpath=//*[@text='"+GetLocalText(TEXT_passport_preference_title)+"' and @onScreen='true']");
	public String[] NP_Text_Nexus = OC("Nexus Details","xpath=//*[@text='"+GetLocalText(TEXT_nexus_preference_title)+"' and @onScreen='true']");
	public String[] NP_BTN_save = OC("Save Button","xpath=//*[@text='"+GetLocalText(TEXT_save)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Save' and @onScreen='true']");
	public String[] NP_Text_MissingAlert = OC("Missing Alert","xpath=//*[@text='"+GetLocalText(TEXT_please_complete_passenger_details)+"' and @onScreen='true']");
	//Nationality_Residence
	public String[] NR_Title_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_nationality_and_residence)+"']");
	public String[] NR_Label_Residentcountry = OC("Residentcountry Label","xpath=//*[@text='"+GetLocalText(TEXT_country_of_residence_title)+"']");
	public String[] NR_Link_Residentcountry = OC("Residentcountry Link","xpath=//*[@id='"+ID_select_country_of_residence+"']","xpath=//*[@class='UIImageView' and @height>0 and ./preceding-sibling::*[@text='Country of residence (optional)'] and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@class='UIView']]");
	public String[] NR_Textbox_Residentcountry = OC("Residentcountry Textrbox","xpath=//*[@id='"+ID_select_country_of_residence+"']","xpath=//*[contains(@placeholder,'Select')]");
	public String[] NR_Link_Nationality = OC("Nationality Link","xpath=//*[@id='"+ID_select_nationality+"']","xpath=//*[@class='UIImageView' and @height>0 and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@text='Nationality (optional)'] and ./preceding-sibling::*[@class='UIView']]");
	public String[] NR_BTN_Save = OC("Save button","xpath=//*[@text='"+GetLocalText(TEXT_save)+"' and @onScreen='true']","xpath=//*[@text='Save']");
	//Passport
	public String[] Passport_Title_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_passport)+"']","xpath=//*[@accessibilityIdentifier='screen-header-Passport']");
	public String[] Passport_LAbel_Passport = OC("Passport Label","xpath=//*[@text='"+GetLocalText(TEXT_passport)+"']","xpath=//*[@accessibilityIdentifier='PassportLabel']");
	public String[] Passport_Textbox_Passport = OC("Passport Textbox","xpath=//*[@id='"+ID_selected_passport+"']","xpath=//*[@placeholder='' and @class='UITextField']");
	public String[] Passport_LAbel_Expiry = OC("Expiry LAbel","xpath=//*[@text='"+GetLocalText(TEXT_nexus_expiry)+"']","xpath=//*[@accessibilityLabel='Expiry']");
	public String[] Passport_LAbel_Day = OC("Day LAbel","xpath=//*[@text='"+GetLocalText(TEXT_day)+"']","xpath=//*[@accessibilityLabel='Day']");
	public String[] Passport_Textbox_Day = OC("Day Textbox","xpath=//*[@id='"+ID_days_clearable+"']","xpath=//*[@placeholder='DD']");
	public String[] Passport_LAbel_Month = OC("Month LAbel","xpath=//*[@text='"+GetLocalText(TEXT_month)+"']","xpath=//*[@accessibilityLabel='Month']");
	public String[] Passport_Textbox_Month = OC("Month Textbox","xpath=//*[@id='"+ID_months_clearable+"']","xpath=//*[@accessibilityIdentifier='longDateMonthTextField']");
	public String[] Passport_LAbel_Year = OC("Year LAbel","xpath=//*[@text='"+GetLocalText(TEXT_year)+"']","xpath=//*[@accessibilityLabel='Year']");
	public String[] Passport_Textbox_Year = OC("Year Textbox","xpath=//*[@id='"+ID_years_clearable+"']","xpath=//*[@placeholder='YYYY']");
	public String[] Passport_Select_Country = OC("Country Selection box","xpath=//*[@id='"+ID_select_country_of_issue+"']","xpath=//*[@class='UIImageView' and @height>0 and ./parent::*[@class='UIView'] and ./preceding-sibling::*[@class='UIView'] and ./preceding-sibling::*[@text='Country of issue']]");
	public String[] Passport_BTN_Save = OC("Save Button","xpath=//*[@text='"+GetLocalText(TEXT_save)+"' and @onScreen='true']","xpath=//*[@class='ACToolkit.ACButton']");
	//NEXUS
	public String[] Nexus_Title_Header = OC("Page Header","xpath=//*[@text='"+GetLocalText(TEXT_nexus)+"']","xpath=//*[@accessibilityIdentifier='screen-header-NEXUS']");
	public String[] Nexus_LAbel_Passport = OC("Number Label","xpath=//*[@text='"+GetLocalText(TEXT_nexus_number)+"']","xpath=//*[@accessibilityLabel='NEXUS Pass ID']");
	public String[] Nexus_Textbox_Nexusnumber = OC("Nexus number Textbox","xpath=//*[@id='"+ID_nexus_number+"']","xpath=//*[@placeholder='' and @class='UITextField']");
	public String[] Nexus_LAbel_Expiry = OC("Expiry LAbel","xpath=//*[@text='"+GetLocalText(TEXT_nexus_expiry)+"']","xpath=//*[@accessibilityLabel='Expiry']");
	public String[] Nexus_LAbel_Day = OC("Day LAbel","xpath=//*[@text='"+GetLocalText(TEXT_day)+"']","xpath=//*[@accessibilityLabel='Day']");
	public String[] Nexus_Textbox_Day = OC("Day Textbox","xpath=//*[@id='"+ID_days_clearable+"']","xpath=//*[@placeholder='DD']");
	public String[] Nexus_LAbel_Month = OC("Month LAbel","xpath=//*[@text='"+GetLocalText(TEXT_month)+"']","xpath=//*[@accessibilityLabel='Month']");
	public String[] Nexus_Textbox_Month = OC("Month Textbox","xpath=//*[@id='"+ID_months_clearable+"']","xpath=//*[@text='MM']");
	public String[] Nexus_LAbel_Year = OC("Year LAbel","xpath=//*[@text='"+GetLocalText(TEXT_year)+"']","xpath=//*[@accessibilityLabel='Year']");
	public String[] Nexus_Textbox_Year = OC("Year Textbox","xpath=//*[@id='"+ID_years_clearable+"']","xpath=//*[@placeholder='YYYY']");
	public String[] Nexus_Toggle_UScheckin = OC("Us Checkin","xpath=//*[@class='com.aircanada.view.SmallSwitch']","xpath=(//*[@class='_UISwitchInternalViewNeueStyle1']/*[@class='UIView' and ./*[@class='UIView']])[1]");
	public String[] Nexus_BTN_Save = OC("Save Button","xpath=//*[@text='"+GetLocalText(TEXT_save)+"' and @onScreen='true']","xpath=//*[@text='Save']");
	public String[] Nexus_Toggle_FalseSatus = OC("Toggle status","xpath=//*[@class='com.aircanada.view.SmallSwitch' and @checked='false']");
	public String[] Nexus_Toggle_TrueSatus = OC("Toggle status","xpath=//*[@class='com.aircanada.view.SmallSwitch' and @checked='true']");
	//Aeroplan
	public String[] Aeroplan_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_frequent_flyer_short_AC)+"']");
	//	public String[] Aeroplan_BTN_Back = OC("Back Button","xpath=//*[@contentDescription='Navigate up']");
	public String[] Aeroplan_img_Logo = OC("Aircanada Logo","xpath=(//*[@class='android.widget.LinearLayout' and ./parent::*[@id='gold_user_view']]/*/*[@class='android.widget.ImageView' and @width>0 and ./parent::*[@class='android.widget.LinearLayout']])[1]");
	public String[] Aeroplan_Label_Aeroplan = OC("Aeroplan label","xpath=//*[@text='"+GetLocalText(TEXT_aeroplan_number)+"']");
	public String[] Aeroplan_Label_Validuntil = OC("Valid until label","xpath=//*[@text='"+GetLocalText(TEXT_valid_until)+"']");
	public String[] Aeroplan_Link_UnlinkAeroplan = OC("Unlink Aeroplan link","xpath=//*[@id='"+ID_button_unlink_aeroplan+"' and @onScreen='true']","xpath=//*[@text='Unlink Aeroplan account']");
	public String[] Aeroplan_image_ScanImage = OC("scan image","xpath=//*[@id='"+ID_two_d_code_image+"']");
	public String[] Aeroplan_Link_contactInfo = OC("Contactinfo Link","xpath=//*[@text='"+GetLocalText(TEXT_contact_info)+"']");
	//Link Areoplan guest
	public String[] LAguest_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_in_order_to_link_your_aeroplan)+"']","xpath=//*[contains(@text,'To link your Aeroplan account please create')]");
	public String[] LAguest_BTN_Createaccount = OC("Create account button","xpath=//*[@id='"+ID_add_this_segment_button+"']","xpath=//*[@text='Create account']");
	public String[] LAguest_BTN_Signin = OC("Signin button","xpath=//*[@id='"+ID_add_all_segments_button+"']","xpath=//*[@text='Sign in']");
	public String[] LAguest_BTN_Cancel = OC("Cancel button","xpath=//*[@id='"+ID_cancel_button+"']","xpath=//*[@text='Cancel']");
	//Link Areoplan
	public String[] LA_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_link_aeroplan)+"' and @onScreen='true']","xpath=//*[@accessibilityIdentifier='screen-header-Link Aeroplan']");
	public String[] LA_Text_Benefit = OC("Benefit text","xpath=//*[@text='"+GetLocalText(TEXT_benefits)+"']");
	public String[] LA_Label_AeroplanNO = OC("Aeroplane number label","xpath=//*[@text='"+GetLocalText(TEXT_aeroplan_number)+"']");
	public String[] LA_TextBox_Aeroplanno = OC("Aeroplane number Textbox ","xpath=//*[@id='"+ID_card_number_editText+"']","xpath=//*[@accessibilityIdentifier='aeroplanTextField']");
	public String[] LA_Label_Password = OC("Password label","xpath=//*[@text='"+GetLocalText(TEXT_aeroplan_password)+"']");
	public String[] LA_Textbox_Password = OC("Password Textbox ","xpath=//*[@id='"+ID_text_password_validation+"']","xpath=//*[@accessibilityIdentifier='passwordTextField']");
	public String[] LA_BTN_Continue = OC("Continue button","xpath=//*[@id='"+ID_button_link_aeroplan_continue+"']","xpath=//*[@text='Link accounts']");
	public String[] LA_Text_Aeroplanname = OC("Continue button","xpath=//*[@class='android.widget.LinearLayout' and ./*[@text='"+GetLocalText(TEXT_use_aeroplan_name)+"']]");
	public String[] LA_Text_Aircanadaname = OC("Continue button","xpath=//*[@class='android.widget.LinearLayout' and ./*[@text='"+GetLocalText(TEXT_use_ac_mobileplus_name)+"']]");
	public String[] LA_Text_Name = OC("Continue button","xpath=//*[@class='com.aircanada.view.FontTextView']");
	public String[] LA_Text_Namevariation = OC("Name variation text","xpath=//*[@text='"+GetLocalText(TEXT_use_aeroplan_name)+"']");
	public String[] LA_BTN_UseAreoplan = OC("Use Aeroplan name button","xpath=//*[@text='"+GetLocalText(TEXT_use_aeroplan_name)+"' and ./following-sibling::*[@text='Air Canada mobile+ name']]","xpath=//*[@accessibilityLabel='Aeroplan name' and @class='ACToolkit.ACButton']");
	public String[] LA_BTN_Mobile_name = OC("Use Mobile name button","xpath=//*[@text='"+GetLocalText(TEXT_use_ac_mobileplus_name)+"']","xpath=//*[@accessibilityLabel='Air Canada mobile+ name' and @class='ACToolkit.ACButton']");
	public String[] LA_Text_Profile_name = OC("LA_Text_Profile_name","xpath=//*[@text='"+GetLocalText(TEXT_air_canada_mobile_plus_name)+"' and @onScreen='true']");
	public String[] LA_Text_Profilenameuodation = OC("Update profile name","xpath=//*[@text='"+GetLocalText(TEXT_profile_must_march_aeroplan)+"']");
	public String[] LA_BTN_Continuename = OC("Continue button","xpath=//*[@id='"+ID_button_close+"']","xpath=//*[@accessibilityLabel='Air Canada mobile+ name' and @class='ACToolkit.ACButton']");
	public String[] LA_BTN_UpdateName = OC("Update button","xpath=//*[@id='"+ID_button_call+"']","xpath=//*[@accessibilityLabel='Aeroplan name' and @class='ACToolkit.ACButton']");
	public String[] LA_Text_Success_message = OC("Link aeroplan success message","xpath=//*[@text='"+GetLocalText(TEXT_aeroplan_account_linked_successfully)+"']");
	public String[] LA_Text_error_message = OC("Link aeroplan Error message","xpath=//*[@text='"+GetLocalText(TEXT_this_card_is_already_linked)+"']");
	public String[] LA_Label_AeroplanNo = OC("Link aeroplan Label","xpath=//*[@id='"+ID_aeroplan_number+"' and @onScreen='true' and @hidden='false']","xpath=//*[@accessibilityLabel='Aeroplan number']");
	public String[] LA_Text_AeroplanNo = OC("Link aeroplan Error message","xpath=//*[@id='"+ID_aeroplan_number+"' and @onScreen='true' and @hidden='false']","xpath=//*[@accessibilityIdentifier='LabelLabel']");
			//"xpath=//*[@accessibilityLabel='Link Aeroplan account']");
	//Unlink Aeroplan UA
	public String[] UA_BTN_Continue = OC("Continue button","xpath=//*[@id='"+ID_button_close+"']","xpath=//*[@accessibilityLabel='Continue']");
	public String[] UA_BTN_cancel = OC("Cancel button","xpath=//*[@id='"+ID_button_call+"']","xpath=//*[@accessibilityLabel='Cancel']");
	//contact_Info_CI
	public String[] CI_Title_header = OC("Contact info header","xpath=//*[@text='"+GetLocalText(TEXT_contact_info)+"']");
	//	public String[] CI_BTN_Back = OC("Back button","xpath=//*[@contentDescription='Navigate up']");
	public String[] CI_Text_Aircanadareservation = OC("Air canada reservations text","xpath=//*[@text='"+GetLocalText(TEXT_air_canada_reservations)+"']");
	public String[] CI_Text_NA = OC("North America Text","xpath=//*[@text='"+GetLocalText(TEXT_north_america)+"']");
	public String[] CI_Text_OtherArea = OC("All other areas Text","xpath=//*[@text='"+GetLocalText(TEXT_all_other_areas)+"']");
	public String[] CI_Text_Aircanada_ContactCenter = OC("Air canada Contact center text","xpath=//*[@text='"+GetLocalText(TEXT_air_canada_contact_center)+"']");
	public String[] CI_Text_Aircanada_ContactCental_Baggage = OC("Air canada Contact central baggage office text","xpath=//*[@text='"+GetLocalText(TEXT_air_canada_central_baggage_office)+"']");
	public String[] CI_Text_Aircanada_Terms_condition = OC("Altitude card terms and conditions text","xpath=//*[@text='"+GetLocalText(TEXT_altitude_card_terms_and_condition)+"']");
	//Account_Created_AC
	public String[] AC_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_account_created)+"']");
	public String[] AC_Text_Username = OC("User Name","xpath=//*[@id='"+ID_user_name+"']");
	public String[] AC_Text_UserEmailID = OC("User Email id","xpath=//*[@id='"+ID_user_email+"']");
	public String[] AC_Text_UserPhonenumber = OC("User Phone number","xpath=//*[@id='"+ID_user_phone+"']");
	public String[] AC_BTN_Okay = OC("Okay button","xpath=//*[@text='"+GetLocalText(TEXT_ok)+"']");
	public String[] AC_BTN_OKbtn = OC("Okay button","xpath=//*[@text='"+GetLocalText(TEXT_ok)+"']","xpath=//*[@accessibilityLabel='OK']");
	//Activation_Pending_AP
	public String[] AP_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_activation_pending)+"']","xpath=//*[@accessibilityLabel='Activation pending']");
	public String[] AP_Text_Activation = OC("Text on Acitvation","xpath=//*[@text='"+GetLocalText(TEXT_mobileplus_activation_pending)+"']","xpath=//*[@accessibilityLabel='Your account needs to be activated. Please check your email']");
	public String[] AP_BTN_Resend = OC("Resend button","xpath=//*[@id='"+ID_resend_email_button+"']","xpath=//*[@class='ACToolkit.ACButton']");
	//Passcode_Protection_PP
	public String[] PP_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_passcode)+"' and @onScreen='true' and @id='toolbar_title']","xpath=//*[@accessibilityLabel='Passcode']");
	public String[] PP_Toggle_Passcode = OC("Passcode Enable Toggle","xpath=//*[@id='"+ID_enable_app_passcode+"']","xpath=(//*[@class='_UISwitchInternalViewNeueStyle1' and ./parent::*[./preceding-sibling::*[@text='App passcode']]]/*[@class='UIView' and ./*[@class='UIView']])[1]");
	public String[] PP_Text_NotEnabled = OC("Passcode Enable Toggle","xpath=//*[@text='"+GetLocalText(TEXT_not_enabled)+"' and @onScreen='true']","xpath=//*[@accessibilityLabel='Not enabled']");
	public String[] PP_BTN_Enabled = OC("Passcode Enabeld","xpath=//*[@id='"+ID_enable_app_passcode+"' and @checked='true']","xpath=//*[@accessibilityLabel='PIN enabled']");
	public String[] PP_BTN_PEnabled = OC("Password Enabeld","xpath=//*[@id='"+ID_enable_app_passcode+"' and @checked='true']","xpath=//*[@text='Password enabled']");
	public String[] PP_BTN_Disabled = OC("Passcode Disabled","xpath=//*[@id='"+ID_enable_app_passcode+"' and @checked='false']","xpath=//*[@accessibilityLabel='Not enabled']");
	public String[] PP_Text_passcodestatus = OC("Passcode Status","xpath=(//*[@class='android.support.v7.widget.GridLayout']/*[@text])[2]");
	public String[] PP_Label_Newpasscode = OC("New passcode Label","xpath=//*[@text='"+GetLocalText(TEXT_new_passcode)+"']");
	public String[] PP_Textbox_Newpasscode = OC("New passcode textbox","xpath=//*[@id='text_number_password']","PP_Textbox_Newpasscode");
	public String[] PP_Label_Confirmpasscode = OC("Confrim Passcode Label","xpath=//*[@text='"+GetLocalText(TEXT_confirm_new_passcode)+"']");
	public String[] PP_Textbox_confirmpasscode = OC("Confrim Passcode Textbox","xpath=//*[@id='text_number_password2']","PP_Textbox_confirmpasscode");
	public String[] PP_BTN_SaveButton = OC("Save button","xpath=//*[@id='"+ID_button_save+"']","xpath=//*[@text='Continue']");

	//Passcoe_Option_PO
	public String[] PO_Header_Passcode=OC("Passcode Header","xpath=//*[@text='"+GetLocalText(TEXT_passcode)+"']","xpath=//*[@accessibilityLabel='Passcode']");
	public String[] PO_BTN_Back=OC("Back Button","xpath=//*[@contentDescription='back']");
	public String[] PO_Link_UsePIN=OC("PIN option","xpath=//*[@text='"+GetLocalText(TEXT_use_pin)+"']","xpath=//*[@text='4-digit passcode']");
	public String[] PO_Link_UsePassword=OC("Password option","xpath=//*[@text='"+GetLocalText(TEXT_use_password)+"']","xpath=//*[@text='Alphanumeric passcode']");
	public String[] PO_Link_Change_Passcode=OC("Change Passcode","xpath=//*[@text='"+GetLocalText(TEXT_change_passcode)+"']","xpath=//*[@text='Change Passcode']");
	public String[] PO_Text_PinEnabled=OC("Change Passcode","xpath=//*[@text='"+GetLocalText(TEXT_pin_enabled)+"']");
	public String[] PP_Title_AlertHeader = OC("Alert title","xpath=//*[@text='"+GetLocalText(TEXT_success)+"']");
	public String[] PP_BTN_OkButton = OC("Ok button","xpath=//*[@text='"+GetLocalText(TEXT_ok)+"']");
	public String[] PP_Text_passcode = OC("Enter Passcode Text","xpath=//*[@text='"+GetLocalText(TEXT_enter_passcode)+"']","xpath=//*[@accessibilityLabel='Passcode']");
	public String[] PP_Textbox_passcode = OC("Enter Passcode Textbox","xpath=//*[@id='"+ID_text_password+"']","xpath=//*[@class='_UIFieldEditorContentView']");
	public String[] PP_BTN_Verify = OC("Verify Button","xpath=//*[@id='"+ID_button_verify+"']");
	//Choose Passcode
	public String[] CP_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_passcode)+"']");
	public String[] CP_Label_EnterPasscode = OC("Enter Passcode Label","xpath=//*[@text='"+GetLocalText(TEXT_enter_new_passcode)+"']");
	public String[] CP_Textbox_PIN_Number_1 = OC("first PIN number textbox","xpath=//*[@id='"+ID_pin_number_1+"']");
	public String[] CP_Textbox_PIN_Number_2 = OC("Second PIN number textbox","xpath=//*[@id='"+ID_pin_number_2+"']");
	public String[] CP_Textbox_PIN_Number_3 = OC("Third PIN number textbox","xpath=//*[@id='"+ID_pin_number_3+"']");
	public String[] CP_Textbox_PIN_Number_4 = OC("Fourth PIN number textbox","xpath=//*[@id='"+ID_pin_number_4+"']");
	//Retype Passcode
	public String[] RP_Label_RetypePasscode = OC("Retype passcode Label","xpath=//*[@text='"+GetLocalText(TEXT_retype_new_passcode)+"']");
	public String[] RP_Textbox_PIN_Number_1 = OC("first PIN number textbox","xpath=//*[@id='"+ID_pin_number_1+"']");
	public String[] RP_Textbox_PIN_Number_2 = OC("Second PIN number textbox","xpath=//*[@id='"+ID_pin_number_2+"']");
	public String[] RP_Textbox_PIN_Number_3 = OC("Third PIN number textbox","xpath=//*[@id='"+ID_pin_number_3+"']");
	public String[] RP_Textbox_PIN_Number_4 = OC("Fourth PIN number textbox","xpath=//*[@id='"+ID_pin_number_4+"']");
	//Change Passcode
	public String[] ChangeP_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_passcode)+"']","xpath=//*[@accessibilityLabel='Passcode']");
	public String[] ChangeP_Label_enter_passcode = OC("Enter Passcode Label","xpath=//*[@text='"+GetLocalText(TEXT_enter_your_pin)+"']");
	public String[] ChangeP_BTN_Continue = OC("Continue button","xpath=//*[@id='"+ID_button_continue_password+"']");
	//Enter_Passcode
	public String[] EP_Text_EnterPasscode = OC("Enter passcode message","xpath=//*[@text='"+GetLocalText(TEXT_enter_passcode)+"']");
	public String[] EP_Textbox_EnterPasscode = OC("Enter Passcode Textbox ","xpath=//*[@id='text_number_password']","EP_Textbox_EnterPasscode");
	public String[] EP_BTN_Verify = OC("Verify button","xpath=//*[@id='"+ID_button_verify+"']");
	//Enter Password_EPass
	public String[] EPass_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_passcode)+"']","xpath=//*[@accessibilityLabel='Enter your new passcode']");
	public String[] EPass_Label_EnterPassword = OC("Enter PAssword Label","xpath=//*[@text='"+GetLocalText(TEXT_enter_new_passcode)+"']");
	public String[] EPass_Textbox_EnterPassword = OC("Enter PAssword textbox","xpath=//*[@id='"+ID_text_new_password+"']","xpath=//*[@class='_UIFieldEditorContentView']");
	public String[] EPass_Text_PasswordReq = OC("Password Requirement","xpath=//*[@id='"+ID_message_text+"']");
	public String[] EPass_Checkbox_PasswordReq = OC("Password Requirement "+ID_checkbox+"","xpath=//*[@id='"+ID_checkbox+"' and @checked='true']");
	public String[] EPass_Checkbox_PasswordReqfalse = OC("Password Requirement checkbox false","xpath=//*[@checked='false']");
	public String[] EPass_BTN_continue = OC("Password Requirement checkbox","xpath=//*[@id='"+ID_button_continue_password+"']","xpath=//*[@text='Continue']");
	//Enter confirm Password ConfirmP
	public String[] ConfirmP_Title_Header = OC("Page header","xpath=//*[@text='"+GetLocalText(TEXT_enter_your_password)+"']");
	public String[] ConfirmP_Label_EnterPassword = OC("Confirm PAssword Label","xpath=//*[@text='"+GetLocalText(TEXT_please_confirm_your_new_password)+"']","xpath=//*[@accessibilityLabel='Please confirm your new password']");
	public String[] ConfirmP_Textbox_EnterPassword = OC("Confrimed PAssword textbox","xpath=//*[@id='"+ID_text_password_confirmed+"']","xpath=//*[@class='_UIFieldEditorContentView']");
	public String[] ConfirmP_Text_PasswordReq = OC("Password Requirement","xpath=//*[@id='"+ID_message_text+"']");
	public String[] ConfirmP_Checkbox_PasswordReq = OC("Password Requirement "+ID_checkbox+"","xpath=//*[@id='"+ID_checkbox+"' and @checked='true']");
	public String[] ConfirmP_BTN_continue = OC("Password Requirement checkbox","xpath=//*[@id='"+ID_button_confirm_password+"']","xpath=//*[@text='Continue']");
	//Warning Pop up
	public String[] WP_BTN_Close = OC("Warning Close button","xpath=//*[@id='"+ID_button_close+"']");
	//Change Password
	public String[] CP_Title_ChangePassword = OC("Change Password title","xpath=//*[@text='"+GetLocalText(TEXT_change_password)+"']","xpath=//*[@accessibilityIdentifier='screen-header-Change password']");
	public String[] CP_BTN_ChangePassword = OC("Change Password button","xpath=//*[@id='"+ID_change_password_button+"']","xpath=//*[@text='Change password']");
	public String[] CP_Text_CurrentPassword = OC("Current Password textbox","xpath=//*[@id='"+ID_text_old_password+"']","0","xpath=//*[@accessibilityIdentifier='textFieldnil']","0");
	public String[] CP_Label_CurrentPassword = OC("Current Password Label","xpath=//*[@text='"+GetLocalText(TEXT_enter_your_current_password)+"']","xpath=//*[@accessibilityLabel='Current password']");
	public String[] CP_Text_NewPassword = OC("New Password textbox","xpath=//*[@id='"+ID_text_new_password+"']","0","xpath=//*[@accessibilityIdentifier='textFieldnil']","1");
	public String[] CP_Label_NewPassword = OC("New Password Label","xpath=//*[@text='"+GetLocalText(TEXT_choose_a_new_password)+"']","xpath=//*[@accessibilityLabel='New password']");
	public String[] CP_BTN_ChangePasswordCTA = OC("Change Password button CTA","xpath=//*[@id='"+ID_button_change_password+"']","xpath=//*[@accessibilityIdentifier='changePasswordButton']");
	public String[] CP_Label_AlertMsg = OC("Alert Message Text","xpath=//*[@id='"+ID_text_alert_message+"' and @hidden='false']","xpath=//*[@accessibilityIdentifier='toastSubtitleLabel']");
	public String[] CP_Label_Msg = OC("Alert Message Text","xpath=//*[@id='"+ID_text_alert_message+"' and @hidden='false']","xpath=//*[@accessibilityIdentifier='messageLabel' and@onScreen='true']");
	public String[] CP_Label_Hint1 = OC("Hint Message Text1","xpath=//*[@text='"+GetLocalText(TEXT_minimum_8_characters)+"']","xpath=//*[@accessibilityLabel='Minimum 8 characters']");
	public String[] CP_Label_Hint2 = OC("Hint Message Text2","xpath=//*[@text='"+GetLocalText(TEXT_one_upper_case)+"']","xpath=//*[@accessibilityLabel='1 upper case letter']");
	public String[] CP_Label_Hint3 = OC("Hint Message Text3","xpath=//*[@text='"+GetLocalText(TEXT_one_lower_case)+"']","xpath=//*[@accessibilityLabel='1 lower case letter']");
	public String[] CP_Checkbox_Hint = OC("Hint Message Checkbox","xpath=//*[@id='"+ID_checkbox+"']","xpath=//*[@accessibilityIdentifier='check']");
	public String[] CP_icon_Hint = OC("Hint Message Icon","xpath=//*[@id='"+ID_checkbox+"']","xpath=//*[@accessibilityIdentifier='icon-bullet']");

	//Recent Searches
	public String[] RS_Title_RecentSearches = OC("Recent Searches title","xpath=//*[@text='"+GetLocalText(TEXT_recent_searches)+"']");
	public String[] RS_Label_SearchResult = OC("Recent Search result","xpath=//*[@id='"+ID_recent_search_result+"']", "xpath=//*[@accessibilityIdentifier='destinationLabel0']");

	public String[] Home_Btn = OC("Home_Btn","xpath=//*[@id='"+ID_toolbar_home+"']", "xpath=//*[@accessibilityIdentifier='icon-navbar-home']");
	public String[] Dynamic_Object = OC("Dynamic_Object","xpath=//*[contains(@text,'????') and @onScreen='true']");
	public String[] ProgressLoading = OC("ProgressLoading","xpath=//*[@id='progress']");

	public String[] StartnewSearch = OC("Start new Search button","xpath=//*[@id='"+ID_button_close+"']", "xpath=//*[contains(@text,'Close')]");

	// //Manage Booking Page MB -- 
	public String[] MB_BTN_SeatSelection = OC("Seat Selection Button","xpath=//*[@id='"+ID_btn_seat_selection+"']");
	public String[] MB_BTN_TravelOptions = OC("Travel Options Button","xpath=//*[@id='"+ID_btn_travel_options+"']");

	//Seat Summary page SS--
	public String[] SS_Title_Header = OC("Seat Summary screen Page Header","xpath=//*[@id='"+ID_toolbar_title+"' and @text='"+GetLocalText(TEXT_seat_summary)+"']");

	//Travel Option Summary screen TOS--
	public String[] TOS_Text_TripAirports = OC("Trip Airport Text","xpath=//*[@id='"+ID_text_trip_airports+"']");
	public String[] TOS_Text_TripDates = OC("Trip Dates Text","xpath=//*[@id='"+ID_text_trip_dates+"']");

	public String[] TOS_Text_SummaryOfCharges = OC("Summary of Charges","xpath=//*[@text='"+GetLocalText(TEXT_summary_of_charges)+"']");
	public String[] TOS_Text_NoOfPassenger = OC("No Of Passengers","xpath=//*[@id='"+ID_text_passengers+"']");
	public String[] TOS_Text_TravelOptionSelected = OC("Text of Travel option selected","xpath=//*[@id='"+ID_title_text_view+"']");
	public String[] TOS_Text_TravelOptionPrice = OC("Text of Travel option Price","xpath=//*[@id='"+ID_price_text_view+"']");
	public String[] TOS_Text_Taxes = OC("Taxes Text","xpath=//*[@text='"+GetLocalText(TEXT_taxes)+"']");

	public String[] TOS_Text_MealSelected = OC("Text of Travel option selected","xpath=//*[@id='"+ID_title_text_view+"'and @text='"+GetLocalText(TEXT_seat_summary)+"']");

	public String[] TOS_Text_GrandTotal = OC("Grand Total Text","xpath=//*[@text='"+GetLocalText(TEXT_grand_total)+"']");
	public String[] TOS_Text_Payment= OC("Payment Text","xpath=//*[@text='"+TEXT_payment+"']");
	public String[] TOS_Text_TravelOptionDisclaimer= OC("Travel Option Disclaimer Text","xpath=//*[@text='"+TEXT_travel_options_payment_disclaimer+"']");
	public String[] TOS_Link_TermsAndCondition = OC("Terms and condition button","xpath=//*[@id='"+ID_btn_terms_conditions+"']");
	public String[] TOS_BTN_Paynow = OC("Pay now button","xpath=//*[@id='"+ID_button_pay_now+"']");

	//Travel_Option_Confirmation_TOC
	public String[] TOC_Text_TravelOptionsConfirmationTitle = OC("Travel option confirmation title","xpath=//*[@text='"+GetLocalText(TEXT_travel_options_confirmed)+"']");
	public String[] TOC_Text_TravelOptionsConfirmationText = OC("Travel option confirmation text","xpath=//*[@text='"+GetLocalText(TEXT_your_travel_options_are_confirmed)+"']");
	public String[] TOC_Link_SeatSelection = OC("Travel option screen-Seat selection link","xpath=//*[@text='"+GetLocalText(TEXT_select_seats_after_confirmation)+"' and @hidden='false' and @onScreen='true']");
	public String[] TOC_Link_ViewTrip = OC("Travel option screen-View Itinerary link","xpath=//*[@text='"+GetLocalText(TEXT_view_trip_itinerary)+"' and @onScreen='true']");


	public String[] TechnicalIssuePopUp = OC("Technical Issue PopUp","xpath=//*[@text='Technical issue']");
	public String[] OpenFormPopUp = OC("Open Form PopUp","xpath=//*[@text='Open form']");
	public String[] CannotGetMail = OC("Cannot Get Mail","xpath=//*[@text='Cannot Get Mail']");
	public String[] OKBtn = OC("OK Btn","xpath=//*[@text='OK']");
	public String[] SendBtn = OC("Send Btn","xpath=//*[@text='Send']");

	
	

	/*------------------IOS specific objects --------------------------*/

	// Launch Page


	public String[] LP_Text_OK_AllowNotification = OC("OK button on Allow Notification popup ","xpath=//*[@text='OK']");
	public String[] LP_Text_OK_Allow = OC("OK button on Allow","xpath=//*[@text='Allow']");
	public String[] LP_Text_Cancel = OC("Cancel button on Location","xpath=//*[@text='Cancel']");
	
	
	public String[] LP_Text_AllowNotification = OC("Allow Notification popup ","xpath=//*[@text='“Air Canada” Would Like to Send You Notifications']");
	public String[] LP_Text_AllowLocation = OC("Allow location popup ","xpath=//*[@text='Allow “Air Canada” to access your location while you use the app?']");
	
	












	//Blank Object
	public String[] Blank_Object = {"","","",""};

	public String CT(String string) {
		return string.replace("$$", "\"");
	}
	public static Hashtable<String, String> GetValue(String Local){
		Hashtable<String, String> HT= new Hashtable<String, String>();
		try{		

			//File fXmlFile =new File(OR.class.getResource("LocalFiles/"+Local+".xml"));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse((OR.class.getResourceAsStream("LocalFiles/"+Local.split("_")[1]+".xml")));


			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("string");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;		
				String Key=eElement.getAttribute("name");	
				String Value=nNode.getTextContent().replace("%d ", "").replace("<b>", "").replace("</b>", "").replace("&amp;", "&").replace("\n", "\\n").replace("\"", "");

				if(Key.equals("select_seats_after_confirmation")){
					System.out.println("");
					if(Value.contains("\n")){
						System.out.println("");
					}
				}
				HT.put(Key, Value);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return HT;
	}
}
