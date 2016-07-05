Set autECLPS = CreateObject("PCOMM.autECLPS")
autECLPS.SetConnectionByName ("A")

Set autECLSession = CreateObject("PCOMM.autECLSession")
autECLSession.SetConnectionByName ("A")

Set autECLOIA = CreateObject("PCOMM.autECLOIA")
autECLOIA.SetConnectionByName ("A")

Set sysObj = CreateObject("PCOMM.autSystem")

dim fso1: set fso1 = CreateObject("Scripting.FileSystemObject")
Dim CurrentDirectory
'CurrentDirectory = fso1.GetAbsolutePathName(".")

CurrentDirectory=CreateObject("Scripting.FileSystemObject").GetParentFolderName(WScript.ScriptFullName)

set WshShell = WScript.CreateObject("WScript.Shell")
'WshShell.Run "mspaint"
'WScript.Sleep 1500
'AppActive

Directory = fso1.BuildPath(CurrentDirectory, "ACMainframeTerminal.WS")

WshShell.Run(Directory)

Dim vScreenShotCount

vScreenShotCount=1

Dim	BookingRefNo,FirstName,LastName,OneWay,Return,Mobile,Email,Address,City,PostalCode,DepFlightNo,DepDate,DepCity

' How to write file
outFile = fso1.BuildPath(CurrentDirectory, "MainframeResults.txt")
Set objFSO = CreateObject("Scripting.FileSystemObject")
Set objFile = objFSO.CreateTextFile(outFile, True)


Dim vFlightNo
Dim vSrc
Dim vDest


'REM This line calls the macro subroutine
'VerifyPNR
'VerifyDCS
'BookTicket_GenratePNR
'DeleteFlight "AC480","20AUG","YYZ"
'CreateFlight "AC480","20AUG","YYZ"

a=WScript.Arguments(0)
'MsgBox("Wrote"&a)
If a="CREATE" Then
	LoginAndOpenACT10
	GetDateAndCreateFlight
ElseIf a="CLOSE_PCOMM" Then
	ClosePCOMM

ElseIf a="VERIFY" Then
	LoginAndOpenACT10

	VerifyPNR

	ClosePCOMM



End If

Set objautECLPS = Nothing
Set objautECLSession = Nothing
Set objautECLOIA = Nothing
Set sysObj = Nothing
set WshShell = Nothing
objFile.Close

Sub LoginAndOpenACT10()

On Error Resume Next

    autECLSession.autECLOIA.WaitForAppAvailable
    autECLSession.autECLPS.WaitForCursor 24, 30, 10000
    TypeAndEnterAndWait "vm", 20, 17
    'TypeAndEnterAndWait "mAbdulk", 23, 1
    
    TypeOnly "pkandas"
    TypeOnly "[tab]"
    TypeAndEnterAndWait "jan18jan", 23, 1
    Wait "5000"
    TypeAndEnterAndWait "act 10", 23, 1
    Wait "3000"
    TypeAndEnter "[clear]"
	 Wait "5000"
    TypeAndEnter "UR"
	TypeAndEnter "UR"
	TypeAndEnter "i"  
	  
On Error goto 0
	
End Sub

Sub GetDateAndCreateFlight()

On Error Resume Next

	vFlightNo = WScript.Arguments(1)
	vSrc = WScript.Arguments(2)
	vDest= WScript.Arguments(3)
	vDate= UCase(Left(Replace(WScript.Arguments(4)," ",""),5))	

	TypeAndEnter "uc"
	TypeAndEnter "ssa/pd"
	TypeAndEnter "bmqxd"
	'TypeAndEnter "dc/daft"
	'TypeAndEnter "mb"
	
	'=16
	'o While GetText(i,4,5)=""
	'	i=i-1
    'oop
    
    'Date=GetText(i,4,5)
    
    TypeAndEnter "*"&vFlightNo&"/"&vDate&"/"&vSrc
    
    If Trim(GetText(3,66,3))<>"FO1" Then
	    		DeleteFlight vFlightNo,vDate,vSrc
    			EditScript "detail input",vFlightNo,vDate,vSrc,vDest
    			CreateFlight vFlightNo,vDate,vSrc
    End If
    
On Error goto 0
    
End Sub

Sub EditScript(vName,vFlightNo,vDate,vSrc,vDest)

	TypeOnly "[PF12]"
	Wait "5000"
	TypeAndEnter "x "&vName
	Wait "5000"
	SetText "0"&vFlightNo&"Y"& vDate & vSrc & vDest,7,7
	SetText "VC"&vFlightNo&"/"&vDate,22,7
	SetText "BM"&vSrc,23,7
	TypeOnly "[PF8]"
	Wait "2000"
	SetText "*"&vFlightNo&"/"&vDate&"/"&vSrc,5,7
	TypeOnly "[home]"
	TypeAndEnter "ff"
	Wait "2000"
	TypeAndEnter "act 10"
	Wait "2000"
	
End Sub

Sub CreateFlight(vFlightNo,vDate,vSrc)

	TypeAndEnter "bmqxd"
	TypeAndEnter "ur"
	TypeAndEnter "ssa/rc"
	TypeAndEnter "ug"
	TypeAndEnter "sr"&vFlightNo&"/"&vDate&"/all"
	TypeAndEnter "ur"
	TypeAndEnter "uc"
	TypeAndEnter "ssa/pd"
	TypeAndEnter "bmqxd"
	
	TypeAndEnter "*"&vFlightNo&"/"&vDate&"/"&vSrc&"*cr/l"
	TypeAndEnter "bm"&vSrc
	TypeAndEnter "fi"
	TypeAndEnter "fi/6/AC_Mobile_Ibbu"
	TypeAndEnter "mc"
	TypeOnly "[PF9]"
	Wait "5000"
	TypeAndEnter "detail input"
	Wait "10000"
	TypeAndEnter "mc/s/1"
	Wait "3000"
	TypeAndEnter "mc/s/2"
	Wait "3000"
	TypeAndEnter "mc"
	Wait "8000"	

End Sub

Sub DeleteFlight(vFlightNo,vDate,vSrc)
	TypeAndEnter "uc"
	TypeAndEnter "bmqxd"
	TypeAndEnter "ssa/pd"
	TypeAndEnter "*"&vFlightNo&"/"&vDate&"/"&vSrc
	TypeAndEnter "@z/all"
	TypeAndEnter "y"
	TypeAndEnter "**cx"
	TypeAndEnter "fh"
	TypeAndEnter "**cx"
	TypeAndEnter "y"
End Sub


Sub VerifyPNR()
On Error resume next

			BookingRefNo=UCase(WScript.Arguments(1))
			FirstName = UCase(WScript.Arguments(2))
			LastName = UCase(WScript.Arguments(3))
			OneWay=UCase(WScript.Arguments(4))
						
			Return=UCase(WScript.Arguments(5))
			
			Mobile=UCase(WScript.Arguments(6))
			Email=UCase(WScript.Arguments(7))
			Address=UCase(WScript.Arguments(8))
			City=UCase(WScript.Arguments(9))
			PostalCode=UCase(WScript.Arguments(10))
			
			DepFlightNo=UCase(WScript.Arguments(11))
			
			If Len(DepFlightNo) = 5 Then
				If Mid(DepFlightNo, 3, 1) = "0" Then
					DepFlightNo = Mid(DepFlightNo, 1, 2) & Mid(DepFlightNo, 4, 2)
				End If
			End If
			
			DepDate=UCase(WScript.Arguments(12))
			ReturnDate=UCase(WScript.Arguments(15))
			ReturnFlightNo=UCase(WScript.Arguments(16))
			
			If Len(ReturnFlightNo) = 5 Then
				If Mid(ReturnFlightNo, 3, 1) = "0" Then
					ReturnFlightNo = Mid(ReturnFlightNo, 1, 2) & Mid(ReturnFlightNo, 4, 2)
				End If
			End If
			
			
	TypeAndEnter "UR"
	TypeAndEnter "ssa/pd"		
			
	TypeAndEnter "*" & BookingRefNo	
	Wait "5000"
	
	'Call VerifyText (3,4,30,"*** ELECTRONIC TICKET ***","ELECTRONIC TICKET Success msg")
	Call VerifyText (3,4,30,"ELECTRONIC TICKET","ELECTRONIC TICKET Success message")
	Call VerifyText (4,4,50,FirstName,"FirstName [" & FirstName &"]")
	Call VerifyText (4,4,50,LastName,"LastName [" & LastName &"]")
	
	mainFrameScreenNo = 1
	emailFieldRowNo = 1
	addressFieldRowNo = 1
	mobileFieldRowNo = 1
	oneWayFlightRowNo = 1
	returnWayFlightRowNo = 1
	For screenRow = 3 to 16
		screenText = GetText(screenRow,4,50)
		If Instr(screenText, "1 AC") > 0 And oneWayFlightRowNo = 1 Then
			oneWayFlightRowNo = screenRow
			
			actDepFlightNo=Trim(Replace(GetText(oneWayFlightRowNo,7,7)," ",""))	
			actDepDate=Trim(GetText(oneWayFlightRowNo,20,5))
			actDepCity=Trim(GetText(oneWayFlightRowNo,27,3))	
			Call CompareText (actDepFlightNo,DepFlightNo,"DepFlightNo [" & DepFlightNo &"]")
			Call CompareText (actDepDate,DepDate,"DepDate [" & DepDate &"]")
			Call VerifyText (oneWayFlightRowNo,4,50,OneWay,"OneWay [" & OneWay &"]")
		End If
		
		If Instr(screenText, "2 AC") > 0 And returnWayFlightRowNo = 1 Then
			returnWayFlightRowNo = screenRow
			
			Call VerifyText (returnWayFlightRowNo,4,50,Return,"Return [" & Return &"]")
			Call VerifyText (returnWayFlightRowNo,20,5,ReturnDate,"ReturnDate [" & ReturnDate &"]")
			actReturnFlightNo=Trim(Replace(GetText(returnWayFlightRowNo,7,7)," ",""))	
			Call CompareText (actReturnFlightNo,ReturnFlightNo,"ReturnFlightNo [" & ReturnFlightNo &"]")
		End If
		
		If InStr(screenText, "MB1-C") > 0 And mobileFieldRowNo = 1 Then
			mobileFieldRowNo = screenRow
			
			Call VerifyText (mobileFieldRowNo,4,50,Mobile,"Mobile [" & Mobile &"]")
		End If
		
		If InStr(screenText, ".COM") > 0 And emailFieldRowNo = 1 Then
			emailFieldRowNo = screenRow
			
			Call VerifyText (emailFieldRowNo,4,50,Email,"Email [" & Email &"]")
		End If
		
		If InStr(screenText, "MB1-A") > 0 And addressFieldRowNo = 1 Then
			addressFieldRowNo = screenRow
			
			Call VerifyText (addressFieldRowNo,4,50,Address,"Address [" & Address &"]")
			Call VerifyText (addressFieldRowNo,4,50,City,"City [" & City &"]")
			'Call VerifyText (addressFieldRowNo,4,50,PostalCode,"PostalCode [" & PostalCode &"]")
		End If
		
		If mainFrameScreenNo = 1 And screenRow = 16 Then
			If emailFieldRowNo = 1 Or addressFieldRowNo = 1 Then
				TypeAndEnterAndWait "mdr", 17, 6
				mainFrameScreenNo = mainFrameScreenNo + 1
				screenRow = 2
			End If
		End If
	Next
		
	On Error goto 0
	
End Sub

Sub VerifyDCS()

	DepFlightNo=WScript.Arguments(11)
	DepDate=WScript.Arguments(12)
	DepCity=WScript.Arguments(13)
	SeatNo=WScript.Arguments(14)

	TypeAndEnter "UC"
	TypeAndEnter "BMQXD"
	TypeAndEnter "ssa/su"
	TypeAndEnter "*"&DepFlightNo&"/"&DepDate&"/"&DepCity
	TypeAndEnter "-"&LastName
	'TypeAndEnter "1"
	Call VerifyText (4,4,50,FirstName,"FirstName[" & FirstName &"]")
	Call VerifyText (4,4,50,LastName,"LastName[" & LastName &"]")
	Call VerifyText (5,50,30,BookingRefNo,"BookingRefNo[" & BookingRefNo &"]")
	Call VerifyText (6,4,20,"CHECKED IN","CHECKED IN MESSAGE")
	Call VerifyText (5,4,50,SeatNo,"Seat Number [" & SeatNo &"]")
	Call VerifyText (7,4,20,"MOBILE CHECKIN'","MOBILE CHECKIN MESSAGE")

End Sub


Sub Wait(vTime)

    autECLSession.autECLPS.Wait vTime

End Sub

Function GetText(vR, vC, vLength)

    GetText = autECLPS.GetText(vR, vC, vLength)

End Function

Function VerifyText(vR, vC, vLength,vVerifyText,vMsg)
	If vVerifyText <> "N/A" Then
		actText = Trim(GetText(vR, vC, vLength))
		'MsgBox ("act: " & actText & " exp: " & vVerifyText)
		If InStr(actText, vVerifyText) > 0 Then
			LogResult "PASS", "Verify Actual [" & actText & "] vs Expected [" & vVerifyText & "]", "Verified "&vMsg, "false"	
		Else: LogResult "FAIL", "Verify Actual [" & actText & "] vs Expected [" & vVerifyText & "]", vMsg & " didn't matched", "false"	
		End If
	End If

End Function

Function CompareText(actText,vVerifyText,vMsg)
	If vVerifyText <> "N/A" Then
		'MsgBox ("act: " & actText & " exp: " & vVerifyText)
		If InStr(actText, vVerifyText) > 0 Then
			LogResult "PASS", "Verify Actual [" & actText & "] vs Expected [" & vVerifyText & "]", "Verified "&vMsg, "false"	
		Else: LogResult "FAIL", "Verify Actual [" & actText & "] vs Expected [" & vVerifyText & "]", vMsg & " didn't matched", "false"	
		End If
	End If

End Function

Sub TypeOnly(vValue)

    autECLSession.autECLOIA.WaitForAppAvailable
    autECLSession.autECLOIA.WaitForInputReady
    autECLSession.autECLPS.SendKeys vValue
    autECLSession.autECLOIA.WaitForInputReady

End Sub

Sub SetText(vText,vR,vC)
	autECLSession.autECLPS.SetText vText,vR,vC
End Sub

Sub TypeAndEnter(vValue)

    autECLSession.autECLOIA.WaitForAppAvailable
    autECLSession.autECLOIA.WaitForInputReady
    autECLSession.autECLPS.SendKeys vValue
    autECLSession.autECLOIA.WaitForInputReady
    autECLSession.autECLPS.SendKeys "[enter]"
    autECLSession.autECLOIA.WaitForInputReady
    'TakeScreenShot
    
    LogResult "INFO", "Performe [" & vValue & "] Command", "Performed [" & vValue & "] Command", "false"
	Wait "1000"
End Sub

Sub TypeAndEnterAndWait(vValue, vR, vC)

    autECLSession.autECLOIA.WaitForAppAvailable
    autECLSession.autECLOIA.WaitForInputReady
    autECLSession.autECLPS.SendKeys vValue
    autECLSession.autECLOIA.WaitForInputReady
    autECLSession.autECLPS.SendKeys "[enter]"
    autECLSession.autECLPS.WaitForCursor vR, vC, 20000
    'TakeScreenShot

    LogResult "INFO", "Performe [" & vValue & "] Command", "Performed [" & vValue & "] Command", "false"
	Wait "1000"
End Sub

'autECLSession.autECLPS.WaitForAttrib 22,80,"00","3c",3,20000


Function LogResult(STEP_SUMMARY, STEP_DESCRIPTION, STEP_RESULT, Screenshot)
    objFile.WriteLine STEP_SUMMARY & "," & STEP_DESCRIPTION & "," & STEP_RESULT & "," & Screenshot
End Function

Function PrintScreen

Set Wshshell1=CreateObject("Word.Basic")
WshShell1.sendkeys"%{prtsc}"
WScript.Sleep 1500
Set Wshshell1= Nothing

End Function

Function TakeScreenShot()

    PrintScreen
    WScript.Sleep 1500
    WshShell.AppActivate "Paint"
    WScript.Sleep 1500
    WshShell.SendKeys "^(v)"
    WScript.Sleep 1500
    WshShell.SendKeys "{F12}"
    WScript.Sleep 1500
    WshShell.SendKeys "C:\Users\24246\Desktop\JARS\" & vScreenShotCount & ".jpg"
    WScript.Sleep 1500
    WshShell.SendKeys "%(s)"
    WScript.Sleep 1500
    AppActive


    vScreenShotCount=vScreenShotCount+1

End Function

Function Genrate_RandomNo()

Dim max, min, myrandomnumber
max = 27
min = 1
Randomize
Genrate_RandomNo = (Int((max - min + 1) * Rnd + min))

End Function


Function RandomString(StrLen)

Dim str

 LETTERS = UCase("abcdefghijklmnopqrstuvwxyz")
    
    
    For i = 1 To StrLen

        str = str & Mid(LETTERS, Genrate_RandomNo(), 1)

    Next

    RandomString = str

End Function


Function AppActive()

Set Processes = GetObject("winmgmts:").InstancesOf("Win32_Process")

intProcessId = ""
For Each Process In Processes
    If StrComp(Process.Name, "pcsws.exe", vbTextCompare) = 0 Then
        intProcessId = Process.ProcessId
        Exit For
    End If
Next

If Len(intProcessId) > 0 Then
    With CreateObject("WScript.Shell")
        .AppActivate intProcessId
    End With
End If


 WshShell.SendKeys "% x"

End Function





Function BookTicket_GenratePNR()

vTravel_Date = "21APR"
vLastName = "LASTNAME" & RandomString(3)
vFirstName = "FIRSTNAME" & RandomString(3)
VPhoneNo = "1234567891"
vDep_City = "YYZ"
vArr_City = "YUL"
    
    TypeAndEnter "ssa/pd"
    TypeAndEnter "ssa/pd"
    TypeAndEnter "bmyyz"
    TypeAndEnter "ssa/gs"
    TypeAndEnter "A" & vTravel_Date & vDep_City & vArr_City
    TypeAndEnter "N1M2"
    TypeAndEnter "-" & vLastName & "/" & vFirstName & " MR"
    TypeAndEnter "9h* " & VPhoneNo
    TypeAndEnter "e*r"
    Wait "5000"

    If InStr(GetText(3, 5, 30), vLastName) <= 0 Then

       'MsgBox "TICKET IS NOT CREATED"
	   LogResult "FAIL", "Verify TICKET IS CREATED", "Verified TICKET IS NOT CREATED", "false"
	   Exit Function

    End If

    vTemp1 = Split(GetText(4, 4, 30), " ")
    vPNR_No = Replace(vTemp1(2), "'", "")

    vFlightNO= Replace(GetText(5, 7,7)," ","")
   

    TypeAndEnter "fq"
    TypeAndEnter "mm"
    TypeAndEnter "ezt"

    If InStr(GetText(15, 8, 50), "ELECTRONIC TICKET ISSUED") > 0 Then

        LogResult "PASS", "Verify ELECTRONIC TICKET IS ISSUED", "Verified ELECTRONIC TICKET IS ISSUED", "false"
    
    Else: LogResult "FAIL", "Verify ELECTRONIC TICKET IS ISSUED", "Verified ELECTRONIC TICKET IS NOT ISSUED", "false"
         Exit Function

    End If

    TypeAndEnter "*" & vPNR_No
    TypeAndEnter "g*" & vFlightNO & "/" & vTravel_Date & "/" & vDep_City
    Wait "3000"
    TypeAndEnter "g-" & vLastName

    i = 4
    r = 0

    Do While InStr(GetText(i, 4, 15), "END NAMES") <= 0 And i <> 17
    
        If InStr(GetText(i, 4, 30), vLastName) > 0 Then
    
            r = 1
            LogResult "PASS", "Verify LastName " & vLastName ,"LastName " & vLastName & " Is Present", "false"
            Exit Do

        End If

        i = i + 1

    Loop

    If r = 0 Then
    	    LogResult "FAIL", "Verify LastName " & vLastName , "LastName " & vLastName & " Is not Present", "false"
    End If

End Function

Sub ClosePCOMM
On Error Resume Next
	autECLSession.autECLOIA.StopCommunication 
	autECLSession.autECLPS.StopCommunication 
	autECLSession.StopCommunication
	
	WshShell.Run "taskkill /f /im pcsws.exe", , True
	'WshShell.Run "taskkill /f /im pcscm.exe", , True
On Error goto 0
	
End Sub




