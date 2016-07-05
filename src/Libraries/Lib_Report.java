package Libraries;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Set;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.io.Files;

/*import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;*/





import Libraries.Lib_Global;

public class Lib_Report {
	
	String DetailsFolderPath;
	String FinalReportFilePath;
	String SummaryReportFilePath;
	String DevicesSummaryReportFilePath;
	String HomeFilePath;
	String SuiteDetailsFilePath;
	String SuiteDetailsChartFilePath;
	String CarrosalFilePath;
		
	public void SetReportPaths(String Path){
		DetailsFolderPath=Path;
		FinalReportFilePath=DetailsFolderPath.replace("Details", "FinalReport.html");
		SummaryReportFilePath=DetailsFolderPath+"/SummaryReport.html";
		DevicesSummaryReportFilePath=DetailsFolderPath+"/DevicesSummaryReport.html";
		HomeFilePath=DetailsFolderPath+"/Home.html";
		SuiteDetailsFilePath=DetailsFolderPath+"/SuiteDetailsReport_DeviceName.html";
		CarrosalFilePath=DetailsFolderPath+"/Carrosal_DeviceName.html";
	}
	
	public void CreateCarousel(String DeviceName,String Images[]) throws IOException{
		File CarrosalFile = new File(CarrosalFilePath.replace("_DeviceName", DeviceName));
		
		DeleteAndCreateFile(CarrosalFile);	

		FileWriter fwFinalRep = new FileWriter(CarrosalFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		
		out.write("<!DOCTYPE html>"+"\n");
		out.write(CT("<html lang=$$en$$>"+"\n"));
		out.write("<head>"+"\n");
		out.write("  <title>Verification Points Carousal</title>"+"\n");
		out.write(CT("  <meta charset=$$utf-8$$>"+"\n"));
		out.write(CT("  <meta name=$$viewport$$ content=$$width=device-width, initial-scale=1$$>"+"\n"));
		out.write(CT("  <link rel=$$stylesheet$$ href=$$http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css$$>"+"\n"));
		out.write(CT("  <script src=$$https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js$$></script>"+"\n"));
		out.write(CT("  <script src=$$http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js$$></script>"+"\n"));
		
		out.write("  <style>"+"\n");
		out.write("  "+"\n");
		out.write("	h3 {"+"\n");
		out.write("	font-family: Verdana;"+"\n");
		out.write("	color: Green;"+"\n");
		out.write("	}"+"\n");
		
		for(int i=0;i<Images.length;i++){
			if(Images[i].contains("FAIL")){
				out.write(" #failed"+i+" h3 {\n");
				out.write("   color:red;\n");
				out.write("	}\n");
			}
		}
		
		out.write("	p{"+"\n");
		out.write("	font-family:Courier;"+"\n");
		out.write("	}"+"\n");
		out.write("	span{"+"\n");
		out.write("	background-color:grey;"+"\n");
		out.write("	}"+"\n");
		out.write("	"+"\n");
		out.write("  .carousel-inner > .item > img,"+"\n");
		out.write("  .carousel-inner > .item > a > img {"+"\n");
		out.write("      width: 50%;"+"\n");
		out.write("      margin: auto;"+"\n");
		out.write("  }"+"\n");
		out.write("  .carousel-caption {"+"\n");
		out.write("    position: relative;"+"\n");
		out.write("    left: auto;"+"\n");
		out.write("    right: auto;"+"\n");
		out.write("  }"+"\n");
		out.write("  </style>"+"\n");
		out.write("</head>"+"\n");
		out.write("<body>"+"\n");
		out.write(CT("<div class=$$container$$>"+"\n"));
		out.write("  <br>"+"\n");
		out.write(CT("<div id=$$myCarousel$$ class=$$carousel slide$$ data-ride=$$carousel$$>"+"\n"));
		
		out.write("  <!-- Indicators -->"+"\n");
		out.write(CT("  <ol class=$$carousel-indicators$$>"+"\n"));
		out.write(CT("    <li data-target=$$#myCarousel$$ data-slide-to=$$0$$ class=$$active$$></li>"+"\n"));		
		for(int i=1;i<Images.length;i++){
			out.write(CT("    <li data-target=$$#myCarousel$$ data-slide-to=$$"+i+"$$></li>"+"\n"));
		}
		out.write("  </ol>"+"\n");
		
		String FirstContents[]=Images[0].split("-->");
		out.write("  <!-- Wrapper for slides -->"+"\n");
		out.write(CT("  <div class=$$carousel-inner$$ role=$$listbox$$>"+"\n"));
		out.write(CT("    <div class=$$item active$$>"+"\n"));
		out.write(CT("      <img src=$$"+FirstContents[0]+"$$ alt=$$Chania$$ width=$$260$$ height=$$145$$>"+"\n"));
		out.write(CT("      <div class=$$carousel-caption$$>"+"\n"));
		out.write("        <h3>"+FirstContents[1]+"</h3>"+"\n");
		out.write("      </div>"+"\n");
		out.write("    </div>"+"\n");
		
		for(int i=1;i<Images.length;i++){
			
			String Contents[]=Images[i].split("-->");
			out.write(CT("    <div class=$$item$$>"+"\n"));
			out.write(CT("      <img src=$$"+Contents[0]+"$$ alt=$$Chania$$ width=$$260$$ height=$$145$$>"+"\n"));
			if(Images[i].contains("FAIL")){
				out.write(CT("      <div class=$$carousel-caption$$ id=$$failed"+i+"$$>"+"\n"));
			}else{out.write(CT("      <div class=$$carousel-caption$$>"+"\n"));}			
			out.write("        <h3>"+Contents[1]+"</h3>"+"\n");
			out.write("      </div>"+"\n");
			out.write("    </div>"+"\n");
		}

		out.write("  </div>"+"\n");
		out.write("  <!-- Left and right controls -->"+"\n");
		out.write(CT("  <a class=$$left carousel-control$$ href=$$#myCarousel$$ role=$$button$$ data-slide=$$prev$$>"+"\n"));
		out.write(CT("    <span class=$$glyphicon glyphicon-chevron-left$$ aria-hidden=$$true$$></span>"+"\n"));
		out.write(CT("    <span class=$$sr-only$$>Previous</span>"+"\n"));
		out.write("  </a>"+"\n");
		out.write(CT("  <a class=$$right carousel-control$$ href=$$#myCarousel$$ role=$$button$$ data-slide=$$next$$>"+"\n"));
		out.write(CT("    <span class=$$glyphicon glyphicon-chevron-right$$ aria-hidden=$$true$$></span>"+"\n"));
		out.write(CT("    <span class=$$sr-only$$>Next</span>"+"\n"));
		out.write("  </a>"+"\n");
		out.write("</div>"+"\n");
		out.write("</body>"+"\n");
		out.write("</html>"+"\n");
		
		out.close();

	}
	

	public void CreateFinalReport() throws IOException{
		
		File FinalReportFile = new File(FinalReportFilePath);
		
		DeleteAndCreateFile(FinalReportFile);	

		FileWriter fwFinalRep = new FileWriter(FinalReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("<html>\n");
		out.write("<head>\n");
		out.write("<title>\n");
		out.write("Final Report");
		out.write("</title>\n");
		out.write("</head>\n");
		
		out.write("<FRAMESET cols="+"\""+"30%, 70%"+"\"" +">\n");
		out.write("<FRAMESET rows="+"\""+"30%, 70%"+"\"" +">\n");			
		out.write("<FRAME src=.\\Details\\DevicesSummaryReport.html>\n");
		out.write(CT("<FRAME src=$$$$ name=Summary noresize scrolling=Auto>\n"));
		out.write("</FRAMESET>\n");
		out.write("<FRAMESET rows="+"\""+"30%, 70%"+"\"" +">\n");			
		out.write("<FRAME src=.\\Details\\SummaryReport.html>\n");
		out.write("<FRAME src=.\\Details\\Home.html name=DetailReport noresize scrolling=Auto>\n");
		out.write("</FRAMESET>\n");
		out.write("</FRAMESET>\n");
		out.write("</html>\n");
		
		out.close();	
		
	}

	public void CreateHomePageWithLogo(Lib_Global oGbl) throws Exception{
		File HomeReportFile = new File(HomeFilePath);
		
		DeleteAndCreateFile(HomeReportFile);
		System.out.println(oGbl.gUtilitiesPath);
		System.out.println(oGbl.gReportDetailsFolderPath);
		Files.copy(new File(oGbl.gUtilitiesPath+"/ClientLogo.jpg"),new File(oGbl.gReportDetailsFolderPath+"/ClientLogo.jpg"));
				
		FileWriter fwFinalRep = new FileWriter(HomeReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("<html>\n");
		out.write("<head>\n");
		out.write("<title>\n");
		out.write("Final Report");
		out.write("</title>\n");
		out.write("</head>\n");
		
		out.write("<body align=center>\n");
		out.write("<p align=center>\n");
		out.write("<img src=ClientLogo.jpg align=center width=530 height=480></img>\n");
		out.write("</p>\n");
		out.write("</body>\n");
		
		
		out.write("</html>\n");
		
		out.close();
	}

	public void Begin_DetailedReport(Lib_Global oGbl) throws Exception{
		File DetailedReportFile = new File(oGbl.gTestCaseDetailedReportPath);
	
		DeleteAndCreateFile(DetailedReportFile);	

		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("<html>\n");
		out.write("<head>\n");
		out.write("<title>\n");
		out.write("Detailed Report");
		out.write("</title>\n");
		out.write("</head>\n");   
		out.write("<body>\n");
		
		out.write("<table  border=1 cellspacing=1 cellpadding=1 align=center>\n");
		out.write("<tr>\n");
		out.write("<td  align=left bgcolor=#707070><FONT COLOR=#FFFFFF FACE=Arial SIZE=2.75><b>Test Script Name</b></td>\n");
		out.write("<td  align=left bgcolor=#FFFFFF><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+oGbl.gTestCaseName+"_"+oGbl.gTestDescription+"</b></td>\n");
		out.write("</tr>\n");
		
		out.write("<tr>\n");
		out.write("<td  align=left bgcolor=#707070><FONT COLOR=#FFFFFF FACE=Arial SIZE=2.75><b>Duration Of Execution</b></td>\n");
		out.write("<td  align=left bgcolor=#FFFFFF><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>???Duration Of Execution???</b></td>\n");
		out.write("</tr>\n");
		
		out.write("<tr>\n");
		out.write("<td  align=left bgcolor=#FFFFFF colspan=2><FACE=Arial SIZE=2.75><b><a href=Carrosal"+oGbl.gDeviceName+".html onclick="+"\""+"window.open('Carrosal"+oGbl.gDeviceName+".html', 'popup',height=1000, width=1000); return false;"+"\""+">Click Here For Verification Points Carousal</a><b></td>");
		out.write("</tr>\n");
		
		out.write("</table>\n");
		
		out.write("<h4> <FONT COLOR=000000 FACE= Arial  SIZE=4.5> <u></u></h4>\n");
		out.write("<h4> <FONT COLOR=000000 FACE= Arial  SIZE=4.5> <u></u></h4>\n");
				
		out.write("<table  border=1 cellspacing=1 cellpadding=1 width=100%>\n");

		out.write("<tr>\n");		
		out.write("<td width=05% align= center  bgcolor=#707070><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>STEP NO</b></td>");
		out.write("<td width=05% align= center  bgcolor=#707070><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>DURATION</b></td>");
		out.write("<td width=10% align= center  bgcolor=#707070><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>STEP SUMMARY</b></td>");
		out.write("<td width=48% align= center  bgcolor=#707070><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>STEP DESCRIPTION</b></td>");
		out.write("<td width=25% align= center  bgcolor=#707070><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>STEP RESULT</b></td>");
		if(oGbl.Pflag){
			out.write("<td width=25% align= center  bgcolor=#707070><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>CPU usage</b></td>");
			out.write("<td width=25% align= center  bgcolor=#707070><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Memory</b></td>");			
		}		
		out.write("<td width=07% align= center  bgcolor=#707070><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>SCREENSHOT</b></td>");

		out.write("</tr>\n");
		out.close();

	}
	
	public void Begin_Chart(Lib_Global oGbl) throws Exception{
		File Chart = new File(oGbl.gTestCaseChartPath);	
		DeleteAndCreateFile(Chart);
		FileWriter fwFinalRep = new FileWriter(Chart,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("<!DOCTYPE HTML>\n");
		out.write("<html>\n");
		out.write("<head>\n");
		out.write("<script type=\"text/javascript\">\n");
		out.write("window.onload = function () {\n");
		out.write("var chart = new CanvasJS.Chart(\"chartContainer\",\n");   
		out.write("{\n");		
		out.write("theme: \"theme3\",\n");
		out.write("title:{\n");
		out.write("text: \"Device Performance\"\n");
		out.write("},\n");
		out.write("animationEnabled: true,\n");		
		out.write("axisX: {\n");
		out.write("title: \"Action based performance\",\n");
		out.write("labelFontColor: \"white\"\n");
		out.write("},\n");
		out.write("axisY:{\n");		
		out.write("includeZero: false\n");		
		out.write("},\n");
		out.write("data: [\n");				
		out.write("{\n");
		out.write("type: \"line\",\n");	
		out.write("dataPoints: [\n");	
		out.close();

	}
	
	public void Begin_MChart(Lib_Global oGbl) throws Exception{
		File Chart = new File(oGbl.gTestCaseMChartPath);	
		DeleteAndCreateFile(Chart);
		FileWriter fwFinalRep = new FileWriter(Chart,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("<!DOCTYPE HTML>\n");
		out.write("<html>\n");
		out.write("<head>\n");
		out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
		out.write("<title>Memory Usage Chart</title>\n");
		out.write("<script src=\"amcharts.js\" type=\"text/javascript\"></script>\n");   
		out.write("<script src=\"serial.js\" type=\"text/javascript\"></script>\n");		
		out.write("<script>\n");		
		out.write("var chart;\n");
		out.write("var graph;\n");	
		out.write("var chartData = [\n");		
		out.close();

	}
	
	public void Addto_Chart(int value,String Text,Lib_Global oGbl) throws Exception{
		String Path=oGbl.gTestCaseChartPath;
		File DetailedReportFile = new File(Path);
		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		/*if(value >= 5){
			out.write("{ y: "+value+",label: \"Screen Name: "+oGbl.ScreenName+" | Action: "+ Text +"\", indexLabel: \"highest\",markerColor: \"red\", markerType: \"triangle\"},");
		}else{*/
			out.write("{ y: "+value+",label: \"Screen Name: "+oGbl.ScreenName+" | Action: "+ Text +"\" },\n");
		//}
		out.close();
	}
	
	public void Addto_MChart(int value,String Text,Lib_Global oGbl) throws Exception{
		String Path=oGbl.gTestCaseMChartPath;
		File DetailedReportFile = new File(Path);
		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		/*if(value >= 5){
			out.write("{ y: "+value+",label: \"Screen Name: "+oGbl.ScreenName+" | Action: "+ Text +"\", indexLabel: \"highest\",markerColor: \"red\", markerType: \"triangle\"},");
		}else{*/
			out.write("{ \"value\": "+value+",\"Text\": \"Screen Name: "+oGbl.ScreenName+" | Action: "+ Text +"\" },\n");
		//}
		out.close();
	}
	
	
	public void Addto_ChartFinal(int value,String Text,Lib_Global oGbl) throws Exception{
		String Path=oGbl.gTestCaseChartPath;
		File DetailedReportFile = new File(Path);
		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("{ y: "+value+", label: \""+ Text +"\" }\n");
		out.close();
	}
	
	public void Addto_MChartFinal(int value,String Text,Lib_Global oGbl) throws Exception{
		String Path=oGbl.gTestCaseMChartPath;
		File DetailedReportFile = new File(Path);
		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("{ \"value\": "+value+",\"Text\": \""+ Text +"\" }\n");
		out.close();
	}
	
	
	public void End_Chart(Lib_Global oGbl) throws Exception{
		Files.copy(new File(oGbl.gUtilitiesPath+"\\canvasjs.min.js"),new File(oGbl.gReportDetailsFolderPath+"\\canvasjs.min.js"));
		Files.copy(new File(oGbl.gUtilitiesPath+"\\amcharts.js"),new File(oGbl.gReportDetailsFolderPath+"\\amcharts.js"));
		Files.copy(new File(oGbl.gUtilitiesPath+"\\serial.js"),new File(oGbl.gReportDetailsFolderPath+"\\serial.js"));
		
		String Path=oGbl.gTestCaseChartPath;
		File DetailedReportFile = new File(Path);
		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("]\n");
		out.write("}\n");
		out.write("]\n");
		out.write("});\n");
		out.write("chart.render();\n");
		out.write("}\n");
		out.write("</script>\n");
		out.write("<script type=\"text/javascript\" src=\"canvasjs.min.js\"></script></head>\n");		
		out.write("<body>\n");
		out.write("<div id=\"chartContainer\" style=\"height: 300px; width: 100%;\">\n");
		out.write("</div>\n");
		out.write("</body>\n");
		out.write("</html>\n");
		out.close();
		
		
	}
	
	public void End_MChart(Lib_Global oGbl) throws Exception{
		String Path=oGbl.gTestCaseMChartPath;
		File DetailedReportFile = new File(Path);
		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("];\n");
		out.write("AmCharts.ready(function () {\n");
		out.write("chart = new AmCharts.AmSerialChart();\n");
		out.write("chart.dataProvider = chartData;\n");
		out.write("chart.marginLeft = 10;\n");
		out.write("chart.categoryField = \"Text\";\n");
		out.write("chart.addListener(\"dataUpdated\", zoomChart);\n");
		out.write("var categoryAxis = chart.categoryAxis;\n");		
		out.write("categoryAxis.dashLength = 3;\n");
		out.write("categoryAxis.minorGridEnabled = true;\n");
		out.write("categoryAxis.minorGridAlpha = 0.1;\n");
		out.write("categoryAxis.color = \"white\";\n");
		out.write("var valueAxis = new AmCharts.ValueAxis();\n");		
		out.write("valueAxis.axisAlpha = 0;\n");
		out.write("valueAxis.inside = true;\n");
		out.write("valueAxis.reversed = true;\n");
		out.write("valueAxis.dashLength = 3;\n");
		out.write("chart.addValueAxis(valueAxis);\n");
		out.write("graph = new AmCharts.AmGraph();\n");
		out.write(" graph.type = \"smoothedLine\"; \n");
		out.write(" graph.lineColor = \"#d1655d\";\n");
		out.write("graph.negativeLineColor = \"#637bb6\"; \n");
		out.write("graph.bullet = \"round\";\n");
		out.write("graph.bulletSize = 8;\n");
		out.write("graph.bulletBorderColor = \"#FFFFFF\";\n");
		out.write("graph.bulletBorderAlpha = 1;\n");
		out.write("graph.bulletBorderThickness = 2;\n");
		out.write("graph.lineThickness = 2;\n");
		out.write("graph.valueField = \"value\";\n");
		out.write("graph.balloonText = \"[[category]]<br><b><span style='font-size:14px;'>[[value]]</span></b>\";\n");
		out.write("chart.addGraph(graph);\n");
		out.write("var chartCursor = new AmCharts.ChartCursor();\n");
		out.write("chartCursor.cursorAlpha = 0;\n");
		out.write("chartCursor.cursorPosition = \"mouse\";\n");
		out.write("chartCursor.categoryBalloonDateFormat = \"YYYY\";\n");
		out.write("chart.addChartCursor(chartCursor);\n");
		out.write("var chartScrollbar = new AmCharts.ChartScrollbar();\n");
		out.write("chart.addChartScrollbar(chartScrollbar);\n");
		out.write("chart.creditsPosition = \"bottom-right\";\n");
		out.write("chart.write(\"chartdiv\");\n");
		out.write("});\n");
		out.write("function zoomChart() {\n");
		out.write("chart.zoomToDates(new Date(1972, 0), new Date(1984, 0));\n");
		out.write("}\n");
		out.write("</script>\n");
		out.write("</head>\n");
		out.write("<body>\n");
		out.write("<div id=\"chartdiv\" style=\"width:100%; height:400px;\"></div>\n");
		out.write("</body>\n");
		out.write("</html>\n");
		out.close();
	}

	public void Add_TestStep_InDetailedReport(String STEP_SUMMARY, String STEP_DESCRIPTION,
			String STEP_RESULT,String CPU_DATA,String Memory, String IMAGE_PATH, Lib_Global oGbl) throws Exception{
		

		oGbl.gTS_Count=oGbl.gTS_Count+1;
		
		oGbl.gTestStepEndTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		
		String TotalTime=TimeDifference(oGbl.gTestStepStartTime,oGbl.gTestStepEndTime);
		
		String Path=oGbl.gTestCaseDetailedReportPath;
		File DetailedReportFile = new File(Path);	

		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		
		String FontColor=colorCodeGenerator(STEP_SUMMARY);
		
		out.write("<tr>\n");
		out.write("<td align= center width=05% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b>"+oGbl.gTS_Count+"</b></td>\n");
		out.write("<td align= center width=05% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b>"+TotalTime+"</b></td>\n");
		out.write("<td align= center width=10% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b>"+STEP_SUMMARY+"</b></td>\n");
		out.write("<td align= left width=48% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b>"+STEP_DESCRIPTION+"</b></td>\n");
		out.write("<td align= left width=25% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b>"+STEP_RESULT+"</b></td>\n");
		if(oGbl.Pflag){
			out.write("<td align= left width=25% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b>"+CPU_DATA+"</b></td>\n");
			out.write("<td align= left width=25% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b>"+Memory+"</b></td>\n");
		}		
		if(IMAGE_PATH.toUpperCase().contains(".PNG")){	
			BufferedImage bimg = ImageIO.read(new File(Path.substring(0,Path.indexOf("Details\\"))+"Details\\"+IMAGE_PATH));
			int width          = bimg.getWidth();
			int height         = bimg.getHeight();
			out.write("<td align= center width=07% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b><a href="+IMAGE_PATH+" onclick="+"\""+"window.open('"+IMAGE_PATH+"', 'popup', 'height="+height+", width="+width+"'); return false;"+"\""+">Click</a><b></td>");
			oGbl.gCarrosalData=oGbl.gCarrosalData+"==>"+IMAGE_PATH+"-->"+STEP_SUMMARY+"-"+STEP_RESULT;
		}else out.write("<td align= center width=10% align= left bgcolor=#FFFFFF><FONT COLOR="+FontColor+" FACE= Arial  SIZE=2><b>No Image</b></td>\n");
		out.write("</tr>\n");
		out.close();
		oGbl.gTestStepStartTime=oGbl.gTestStepEndTime;
		
	}
	
	public void End_DetailedReport(Lib_Global oGbl) throws Exception{

		File DetailedReportFile = new File(oGbl.gTestCaseDetailedReportPath);	

		FileWriter fwFinalRep = new FileWriter(DetailedReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		
		out.write("</table>\n");

		out.write("</body>\n");
		out.write("</html>");
		out.close();
		
		BufferedReader br = new BufferedReader(new FileReader(DetailedReportFile));

		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append("\n");			
			line = br.readLine();			
		}
		
		oGbl.gTestCaseEndTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		
		String Total_Duration=TimeDifference(oGbl.gTestCaseStartTime, oGbl.gTestCaseEndTime);
		sb.replace(sb.indexOf("???Duration Of Execution???"),sb.indexOf("???Duration Of Execution???")+("???Duration Of Execution???").length(),Total_Duration);
        
		FileWriter writer = new FileWriter(DetailedReportFile);
        writer.write(sb.toString());
        writer.close();
        br.close();
        
        CreateCarousel(oGbl.gDeviceName, oGbl.gCarrosalData.replace("Temp==>", "").split("==>"));
	}
	
	public void CreateSuiteReport(String DeviceName)throws IOException{
		
		File SuiteDetailsFile = new File(SuiteDetailsFilePath.replace("_DeviceName", DeviceName));
		
		DeleteAndCreateFile(SuiteDetailsFile);	

		FileWriter fwFinalRep = new FileWriter(SuiteDetailsFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("<html>\n");
		out.write("<head>\n");

		out.write("<title>\n");
		out.write("Details Report");
		out.write("</title>\n");
		out.write("</head>\n");   
		out.write("<body>\n");
		
		out.write("<h4> <FONT COLOR=000000 FACE= Arial  SIZE=4.5> <u></u></h4>\n");
		out.write("<h4> <FONT COLOR=000000 FACE= Arial  SIZE=4.5> <u></u></h4>\n");

		out.write("<table width=385 border=1 cellspacing=1 cellpadding=1 align=center>\n");
		out.write("<td width=600 align=center bgcolor=#707070  colspan=3><FONT COLOR=#FFFFFF FACE=Arial SIZE=3><b>"+DeviceName+" Suite Details</</b></td>");
		out.write("<tr>\n");
		out.write("<td  width=15 align= left  bgcolor=#707070><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b>No</b></td>\n");
		out.write("<td  width=370 align= left  bgcolor=#707070><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b>TestCases</b></td>\n");
		out.write("<td  width=370 align= left  bgcolor=#707070><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b>Performance Report</b></td>\n");

		out.write("</tr>\n");
			
		out.close();
	}
 
	public void Add_TestCase_InSuiteReport(int TC_Count,Lib_Global oGbl) throws IOException{
		oGbl.gTestCaseEndTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		

		File SummaryReportFile = new File(SuiteDetailsFilePath.replace("_DeviceName", oGbl.gDeviceName));	

		FileWriter fwFinalRep = new FileWriter(SummaryReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		
		out.write("<tr>\n");
		out.write("<td width=15 align= left  bgcolor=#E9E3EA><FONT COLOR=#000000 FACE= Arial  SIZE=2.75><b>"+TC_Count+"</b></td>\n");	
		
		String TestResulLink=oGbl.gTestCaseName+"_Iteration"+oGbl.gTestCaseIterationCount+"_"+oGbl.gDeviceName+".html";
		String TestChartLink=oGbl.gTestCaseName+"_Iteration"+oGbl.gTestCaseIterationCount+"_"+oGbl.gDeviceName+"_Chart.html";	
		String TestMChartLink=oGbl.gTestCaseName+"_Iteration"+oGbl.gTestCaseIterationCount+"_"+oGbl.gDeviceName+"_MChart.html";
		if(oGbl.gTC_Status.toUpperCase().equals("PASS"))
		{	
			out.write("<td width=370 align= left  bgcolor=#00FF00 ><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b><a style=color:Black href="+TestResulLink+" target=DetailReport>"+oGbl.gTestCaseName+"_"+oGbl.gTestDescription+"</b></td>\n");
			if(oGbl.Pflag){
				out.write("<td width=370 align= left  bgcolor=#00FF00 ><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b><a style=color:Black href="+TestChartLink+" target=DetailReport>Click here</b><br><b><a style=color:Black href="+TestMChartLink+" target=DetailReport>Click here</b></td>\n");	
			}else{
				out.write("<td width=370 align= left  bgcolor=#00FF00 ><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b><a style=color:Black target=DetailReport>Not Enabled</b></td>\n");
			}
			
		}
		else{
			out.write("<td width=370 align= left  bgcolor=#FF0033 ><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b><a style=color:Black href="+TestResulLink+" target=DetailReport>"+oGbl.gTestCaseName+"_"+oGbl.gTestDescription+"</b></td>\n");
			if(oGbl.Pflag){
				out.write("<td width=370 align= left  bgcolor=#FF0033 ><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b><a style=color:Black href="+TestChartLink+" target=DetailReport>Click here</b><br><b><a style=color:Black href="+TestMChartLink+" target=DetailReport>Click here</b></td>\n");
			}else{
				out.write("<td width=370 align= left  bgcolor=#FF0033 ><FONT COLOR=#FFFFFF FACE= Arial  SIZE=2.75><b><a style=color:Black target=DetailReport>Not Enabled</b></td>\n");
			}
		}		
			
		out.write("</tr>\n");
		out.close();
	}
	
	public void End_SuiteReport(Lib_Global oGbl) throws IOException{

		FileWriter fwFinalRep = new FileWriter(new File(SuiteDetailsFilePath.replace("_DeviceName",oGbl.gDeviceName)),true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		
		out.write("</table>\n");

		out.write("</body>\n");
		out.write("</html>");
		out.close();
		
                    
	}
	
	public void CreateDevices_SummaryReport() throws IOException{
		File DevicesSummaryReportFile = new File(DevicesSummaryReportFilePath);
		
		DeleteAndCreateFile(DevicesSummaryReportFile);	

		FileWriter fwFinalRep = new FileWriter(DevicesSummaryReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("<html>\n");
		out.write("<head>\n");
		out.write("<title>\n");
		out.write("Devices Summary Report");
		out.write("</title>\n");
		out.write("</head>\n");   
		out.write("<body>\n");			

	
				
		out.write("<table  border=1 cellspacing=1 cellpadding=1 align=center>");	
		
		out.write("<td width=800 align=center bgcolor=#707070  colspan=6><FONT COLOR=#FFFFFF FACE=Arial SIZE=3><b>Devices Summary Report</</b></td>");
	
		out.write("<tr>\n");		
		out.write("<td width=75 align=left bgcolor=#707070 ><FONT COLOR=#FFFFFF FACE=Arial SIZE=2.75><b>No</b></td>\n");
		out.write("<td width=150 align=left bgcolor=#707070 ><FONT COLOR=#FFFFFF FACE=Arial SIZE=2.75><b>Platform</b></td>\n");
		out.write("<td width=450 align=left bgcolor=#707070 ><FONT COLOR=#FFFFFF FACE=Arial SIZE=2.75><b>Name</b></td>\n");
		out.write("<td width=75 align=left bgcolor=#707070 ><FONT COLOR=#FFFFFF FACE=Arial SIZE=2.75><b>Total</b></td>\n");
		out.write("<td width=75 align=left bgcolor=#707070 ><FONT COLOR=#FFFFFF FACE=Arial SIZE=2.75><b>Passed</b></td>\n");
		out.write("<td width=75 align=left bgcolor=#707070 ><FONT COLOR=#FFFFFF FACE=Arial SIZE=2.75><b>Failed</b></td>\n");
		out.write("</tr>\n");
		out.close();
	}
	
	public void Add_TestDetails_InDevicesSummaryReport(int DeviceCount,String DeviceName,int TotalTestCount) throws IOException{
		
		File DevicesSummaryReportFile = new File(DevicesSummaryReportFilePath);	

		FileWriter fwFinalRep = new FileWriter(DevicesSummaryReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		
		out.write("<tr>\n");		
		out.write("<td width=75 align=left bgcolor=#E9E3EA ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+DeviceCount+"</b></td>\n");
		out.write("<td width=150 align=left bgcolor=#FFFFFF ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>Android</b></td>\n");
		out.write(CT("<td width=450 align=left bgcolor=#FFFFFF ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b><a style=color:Black href=SuiteDetailsReport"+DeviceName+".html target=$$Summary$$>"+DeviceName+"</b></td>\n"));
		out.write("<td width=75 align=left bgcolor=#FFFFFF ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+TotalTestCount+"</b></td>\n");
		out.write("<td width=75 align=left bgcolor=#FFFFFF ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+DeviceName+"_P"+"</b></td>\n");
		out.write("<td width=75 align=left bgcolor=#FFFFFF ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+DeviceName+"_F"+"</b></td>\n");
		out.write("</tr>\n");
		out.close();
	}
	
	public void EndDevicesSummaryReport(Hashtable<String, Integer> DevicePassedTests,Hashtable<String, Integer> DeviceFailedTests)throws IOException{
		File DevicesSummaryReportFile = new File(DevicesSummaryReportFilePath);	

		FileWriter fwFinalRep = new FileWriter(DevicesSummaryReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		
		out.write("</table>\n");
		out.write("</body>\n");
		out.write("</html>");	
		out.close();
		
		BufferedReader br = new BufferedReader(new FileReader(DevicesSummaryReportFilePath));

		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append("\n");			
			line = br.readLine();			
		}
		
	    Set<String> keys1 = DevicePassedTests.keySet();
	    Set<String> keys2 = DeviceFailedTests.keySet();
        for(String key: keys1){
        	
        	sb.replace(sb.indexOf(key+"_P"),sb.indexOf(key+"_P")+(key+"_P").length(),String.valueOf(DevicePassedTests.get(key)));
        }
        
        for(String key: keys2){
        	
        	sb.replace(sb.indexOf(key+"_F"),sb.indexOf(key+"_F")+(key+"_F").length(),String.valueOf(DeviceFailedTests.get(key)));
        }  		
        
		FileWriter writer = new FileWriter(DevicesSummaryReportFilePath);
        writer.write(sb.toString());
        writer.close();
        br.close();
	}
	
	public void Create_SummaryReport(Lib_Global oGbl) throws IOException{				
				
		File SummaryReportFile = new File(SummaryReportFilePath);
			
		DeleteAndCreateFile(SummaryReportFile);	

		FileWriter fwFinalRep = new FileWriter(SummaryReportFile,true);
		BufferedWriter out =new BufferedWriter(fwFinalRep);
		out.write("<html>\n");
		out.write("<head>\n");
		out.write("<title>\n");
		out.write("Details Report");
		out.write("</title>\n");
		out.write("</head>\n");   
		out.write("<body>\n");			

	
		out.write("<p>");
		
		out.write("<table  border=1 cellspacing=1 cellpadding=1 align=center>");
		out.write("<td width=600 align=center bgcolor=#707070  colspan=3><FONT COLOR=#FFFFFF FACE=Arial SIZE=3><b>Air Canada Automation TestSuite Result</</b></td>\n");
	
		out.write("<tr>\n");	
		
		out.write("<td width=200 rowspan=6 align=center><img src=ClientLogo.jpg align=center width=180 height=150></img></td>\n");
		
		out.write("<td width=200 align=left bgcolor=#E9E3EA ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>Devices Used</b></td>\n");
		out.write("<td width=200 align=left bgcolor=#FFFFFF><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+Arrays.toString(oGbl.Devices).replace("adb:", "").replace("ios_app:","").replace(" ","")+"</b></td>\n");
		out.write("</tr>\n");

		out.write("<tr>\n");			
		out.write("<td width=200 align=left bgcolor=#E9E3EA ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>Environment</b></td>\n");
		out.write("<td width=200 align=left bgcolor=#FFFFFF><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+oGbl.TD.get("Environment")+"</b></td>\n");
		out.write("</tr>\n");

		out.write("<tr>\n");		
		out.write("<td width=200 align=left bgcolor=#E9E3EA ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>Release</b></td>\n");
		out.write("<td width=200 align=left bgcolor=#FFFFFF><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+oGbl.TD.get("Release")+"</b></td>\n");
		out.write("</tr>\n");

		out.write("<tr>\n");
		out.write("<td width=200 align=left bgcolor=#E9E3EA ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>Run Start Time</b></td>\n");		
		out.write("<td width=200 align=left bgcolor=#FFFFFF><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+oGbl.gTestSuiteStartTime+"</b></td>\n");
		out.write("</tr>\n");
		
		out.write("<tr>\n");
		out.write("<td width=200 align=left bgcolor=#E9E3EA ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>Run End Time</b></td>\n");		
		out.write("<td width=200 align=left bgcolor=#FFFFFF><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+oGbl.gTestSuiteEndTime+"</b></td>\n");
		out.write("</tr>\n");
		
		out.write("<tr>\n");
		out.write("<td width=200 align=left bgcolor=#E9E3EA ><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>Total Duration</b></td>\n");		
		out.write("<td width=200 align=left bgcolor=#FFFFFF><FONT COLOR=#000000 FACE=Arial SIZE=2.75><b>"+TimeDifference(oGbl.gTestSuiteStartTime, oGbl.gTestSuiteEndTime)+"</b></td>\n");
		out.write("</tr>\n");

		out.write("</table>\n");		
		
		out.write("</p>\n");

		out.write("</body>\n");
		out.write("</html>");
		out.close();

	}
		
	public void DeleteAndCreateFile(File File) throws IOException
	{		
		if(File.exists())
		{
			File.delete();
			File.createNewFile();
		}
		else{
			File.getParentFile().mkdirs();
			File.createNewFile();
			}
	}
	
	public String TimeDifference(String T1,String T2){
		final DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm:ss");
		final DateTime dt1 = format.parseDateTime(T1);
		final DateTime dt2 = format.parseDateTime(T2);

		return Hours.hoursBetween(dt1, dt2).getHours() % 24 + ":"+Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + ":"+Seconds.secondsBetween(dt1, dt2).getSeconds() % 60;

	}
	
	public String colorCodeGenerator(String STEP_SUMMARY) throws Exception {
		try {
			String Colorcode = null;
			STEP_SUMMARY=STEP_SUMMARY.toUpperCase();
			if (STEP_SUMMARY.equalsIgnoreCase("INFO")) {
				Colorcode = "#10160F";
			}
			if (STEP_SUMMARY.equalsIgnoreCase("FAIL")) {
				Colorcode = "#DF0101";
			}
			if (STEP_SUMMARY.equalsIgnoreCase("PASS")) {
				Colorcode = "#56A357";
			}
			if (STEP_SUMMARY.equalsIgnoreCase("WARNING")) {
				Colorcode = "#F87F1C";
			}
			if (STEP_SUMMARY.equalsIgnoreCase("TESTDATA_INFO")) {
				Colorcode = "#FF1493";
			}

			return Colorcode;

		} catch (Exception e) {
			throw e;
		}

	}		
	
	public String CT(String string) {

		return string.replace("$$", "\"");

	}
}
