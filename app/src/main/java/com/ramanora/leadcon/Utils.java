package com.ramanora.leadcon;

public class Utils {
	
	//Email Validation pattern
	//public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
	public static final String regEx = "\\b[+]?[0-9]{10,13}\\b";
	//Fragments Tags
	public static final String Login_Fragment = "Login_Fragment";
	public static final String SignUp_Fragment = "SignUp_Fragment";
	public static final String FragmentCallRList = "FragmentCallRList";
	public static final String URL_CALLR_LIST= "https://exhibitionz.com/DataCollectionApp/api/v1/index.php/GetCompanyWiseData?CompanyTableName=Table_Test";
	public static final String POST_LIVE_LOCATION = "https://leadcon.co/POST/currentlocation.php";


	//public static final String GET_CSV_lIST = "https://leadcon.co/GET/csvdata.php?CompanyKey=";  //old
	public static final String GET_CSV_lIST = "https://leadcon.co/GET/NewGetCsvName.php?CompanyKey=";  //new

	public static final String GET_DC_lIST ="https://leadcon.co/GET/dcdata.php?CompanyKey=";

	//old public static final String GET_DC_FORM = "https://callcenter.exhibitionz.com/GET/FormNamelistInCallr.php?CompanyName=";
	public static final String GET_DC_FORM = "https://leadcon.co/GET/FormNamelistInCallr.php?CompanyName="; //new leadcon

	public static final String GET_SMS = "https://leadcon.co/GET/compose.php?CompanyName=";

	// public static final String POST_UPDATE_STATUS_DC = "https://leadcon.co/POST/callstatus.php"; //old
	//public static final String POST_UPDATE_STATUS_DC = "https://leadcon.co/POST/newcallstatus.php"; //new
   // public static final String POST_UPDATE_STATUS_DC = "https://callcenter.exhibitionz.com/POST/FormWiseCallStatusUpdate.php"; // very new
    public static final String POST_UPDATE_STATUS_DC = "https://leadcon.co/POST/FormWiseCallStatusUpdate.php";

	//public static final String POST_UPDATE_STATUS_CSV = "https://leadcon.co/POST/updatecsvdata.php";//old

	public static final String POST_UPDATE_STATUS_CSV = "https://leadcon.co/POST/newupdatecsvdata.php";//new


	public static final String GET_PDF = "https://leadcon.co/GET/pdfdata.php?CompanyName=";
	//public static final String POST_NAVIGATION = "https://leadcon.co/POST/mapmarker.php";//old

	public static final String POST_NAVIGATION = "https://leadcon.co/POST/SendLocation.php";




	//public static final String POST_UPDATE_DC_DATA = "https://leadcon.co/POST/editdcdata.php";
//old	public static final String POST_UPDATE_DC_DATA = "https://callcenter.exhibitionz.com/POST/EditDcDataFormWise.php"; //ew form wise
	public static final String POST_UPDATE_DC_DATA = "https://leadcon.co/POST/EditDcDataFormWise.php"; //new leadcon
	public static final String DCFORMWISEDARA = "https://leadcon.co/GET/FormViseData.php?CompanyKey=";

	public static final String POST_UPDATE_CSV_DATA = "https://leadcon.co/POST/editcsvleaddata.php";


	public static final String POST_UPDATE_TRACK_DATA =  "https://leadcon.co/POST/trackofnewupdate.php";

	//String url = "http://leadcon.co/GET/csvdataforkey.php?CompanyKey="+Company_key +"&Csvname="+DataName;  //new leadcon url

	public static final String GET_CSV_DATA_AGAINST_KEY ="https://leadcon.co/GET/csvdataforkey.php?CompanyKey=";


	//   String url = "https://callcenter.exhibitionz.com/GET/csvdata.php?id="+temp_idcsv;   //callcenter old url

	public static final String POST_ANALYTICS ="https://leadcon.co/POST/DcandCsvAnalytics.php";

	//meeting invite

	public static final String POST_MEETING_INVITE ="https://leadcon.co/POST/MeetingInvite.php";

//	public static final String POST_ENTER_LEAD = "https://leadcon.co/POST/enterlead.php"; //old

	public static final String POST_ENTER_LEAD = "https://leadcon.co/POST/newenteralead.php"; // bday anniversary

	public static final String POST_ENQUIRY_DATA = "https://leadcon.co/POST/EnquiryFormForCallR.php";// bday anniversary
	public static final String POST_VERIFICATION_CONFIRMATION = "https://leadcon.co/GET/GenerateOtpForCallR.php?Verification=vcvc&Id=1";

	public static final String POST_SHARE_LEAD_TRACK = "https://leadcon.co/POST/AddSharedLeadData.php";
	public static final String GET_SHARE_LEAD_LIST  = "https://leadcon.co/GET/UserListInCallR.php?companyname=";


	public static final String URL_SOCIAL_FACEBOOK = "https://www.facebook.com/ramanoraglobal/";
	public static final String URL_SOCIAL_LINKEDIN = "https://in.linkedin.com/showcase/ramanora-global-pvt-ltd-";
	public static final String URL_SOCIAL_TWITTER = "https://twitter.com/hashtag/ramanora";

	public static final String URL_NOTIFICATION = "https://leadcon.co/GET/Notificationlist.php?CompanyKey=";
	public static final String LOCAL_ANDROID_VERSION = "4.6.7";
	public static final String LOCAL_ANDROID_VERSION_DAILOG_TITLE = "CallR";
	public static final String LOCAL_ANDROID_VERSION_MESSAGE = "An App UPDATE is required! "+ "\n"+"We have an update of the CallR App while we encourage you to update to our latest version to avoid any interruption.";


}
