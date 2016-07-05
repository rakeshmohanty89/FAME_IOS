package Libraries;

//New Packages
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;








import jxl.*;


public class Lib_Common {
	

	public int RandNum() {
		int i = 0 + (int) (Math.random() * ((9 - 0) + 1));
		return i;
	}
	
	public String[] Array_Merge(String[] first, String[] second) {
	    List<String> both = new ArrayList<String>(first.length + second.length);
	    Collections.addAll(both, first);
	    Collections.addAll(both, second);
	    return both.toArray(new String[both.size()]);
	}
	
	public String RandomString( int len ) 
	{
	   final String AB = "abcdefghijklmnopqrstuvwxyz";
	   Random rnd = new Random();
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	private void recursiveDelete(File file) {
		if (!file.exists()) return;
		if (file.isDirectory()) {
			for (File f : file.listFiles()) recursiveDelete(f);
			file.delete();
		} else {
			file.delete();
		}
	} 



	public  void DeleteFileOrFolder(String Path)   {
		File directory = new File(Path);
		recursiveDelete(directory);
	}

	public String GetNumbersFromString(String valueToextract) {

		String str = valueToextract;
		StringBuilder myNumbers = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				myNumbers.append(str.charAt(i));
			}
		}
		valueToextract = myNumbers.toString();
		return valueToextract;
	}	

	public String RemoveCharsFromString(String s, char c) {

		System.out.println("Inside Remove");
		String r = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i)!= c)
				r += s.charAt(i);
		}


		return r;

	}	
		
	public void ReduceImageSize(String IMAGE_PATH) throws IOException{
		BufferedImage originalImage = ImageIO.read(new File(IMAGE_PATH));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);	
		ImageIO.write(resizeImageHintJpg, "jpg", new File(IMAGE_PATH.replace(".PNG", "")));	
		new File(IMAGE_PATH).delete();
	}

	public BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){


		int IMG_WIDTH = 768;
		int IMG_HEIGHT = 1024;

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}	

	public void addFileToZip(String path, String srcFile, ZipOutputStream zip)
			throws Exception {

		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
			in.close();
		}
	}

	public void zipFolder(String srcFolder, String destZipFile) throws Exception {
		ZipOutputStream zip = null;
		FileOutputStream fileWriter = null;

		fileWriter = new FileOutputStream(destZipFile+".zip");
		zip = new ZipOutputStream(fileWriter);

		addFileToZip("", srcFolder, zip);
		zip.flush();
		zip.close();
	}

	public void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
			throws Exception {
		File folder = new File(srcFolder);

		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "//" + fileName, zip);
			} else {
				addFileToZip(path +"//" + folder.getName(), srcFolder + "//" + fileName, zip);
			}
		}
	}
	
	public  String WebImageScreencapture(String imageName,Lib_Global oGbl) throws Exception {

		try {
			String ImageFileName = "";
			String ImageFolderPathLink = "";


			String ImageFolderPath =oGbl.gReportDetailsFolderPath;
			ImageFolderPathLink = "." +"\\Image\\";
			File dir = new File(ImageFolderPath);
			if (!dir.exists()) {

				dir.mkdirs();

			}

			String imageFileName= ImageFolderPath+ imageName+".png" ;
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			Robot robot = new Robot();
			BufferedImage img = robot.createScreenCapture(new Rectangle(size));
			File save_path=new File(imageFileName);
			ImageIO.write(img, "png", save_path);		
			ImageFileName = ImageFolderPathLink + imageName+".png";
			return ImageFileName;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageName;

	}	

	public ArrayList<String> GetClassesForExecution(String xlFilePath,String sheetName) throws Exception{
		int startRow, startCol, endRow,TestClassCol,TestDataCol,TestDescriptionCol;
		ArrayList<String> AL= new ArrayList<String>();
		
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);		
		Cell tableStart = sheet.findCell("Execution");
		startCol = tableStart.getColumn();
		TestClassCol=startCol-2;
		TestDataCol=startCol+1;
		TestDescriptionCol=startCol-1;
		startRow = tableStart.getRow()+2;
		endRow   = sheet.getRows();
		
		for (int i = startRow; i < endRow; i++){
			if(sheet.getCell(startCol,i).getContents().equalsIgnoreCase("Y")){
				AL.add(sheet.getCell(TestClassCol,i).getContents()+"=>"+sheet.getCell(TestDataCol,i).getContents()+"=>"+sheet.getCell(TestDescriptionCol,i).getContents());
			}
		}
		
		return AL;
	}
	
	@SuppressWarnings("unchecked")
	public Hashtable<String, String>[] GetTestDataHashTableArray(String xlFilePath,
			String sheetName, String tableName) throws Exception {
		try {

			Hashtable<String, String>[] HashArray = null;
                     
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));

			Sheet sheet = workbook.getSheet(sheetName);
			int startRow, startCol, endRow, endCol, ci;
			System.out.println("TabelName-->"+tableName);
			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, startCol+1,
					startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();
			System.out.println("startRow=" + startRow + ",endRow=" + endRow
					+ ", " + "startCol=" + startCol + ", endCol=" + endCol);
			ci = 0;
			HashArray = new Hashtable[endRow - (startRow + 1)];
			for (int i = startRow + 1; i < endRow; i++, ci++) {
				HashArray[ci] = new Hashtable<String, String>();
				for (int j = startCol + 1; j < endCol; j++) {
					HashArray[ci].put(sheet.getCell(j, startRow).getContents(),
							sheet.getCell(j, i).getContents());
				}
			}

			return (HashArray);

		} catch (Exception e) {
			System.out.println("Problem with test data table please check");
			e.printStackTrace();
			throw e;
		}

	}
	
	public Hashtable<String,String> GetCommonTD(String xlFilePath){
		Hashtable<String,String>  HTO=null;
		try{
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));

		
		int startRow, startCol, endRow;
		HTO=new Hashtable<String,String>();
		
		Sheet sheet = workbook.getSheet("Common");			
		Cell tableStart = sheet.findCell("Name");
		startRow = tableStart.getRow()+1;
		startCol = tableStart.getColumn();
		endRow=sheet.getRows();

		
		for (int j = startRow; j < endRow; j++) {
			if(sheet.getCell(startCol, startRow)!=null){
				HTO.put(sheet.getCell(startCol, j).getContents(),
						sheet.getCell(startCol+1,j).getContents());
			}

		}	
		}catch(Exception e){
			e.printStackTrace();
		}

		return (HTO);
	}
	
	public Hashtable<String,String> GetLocalTD(String xlFilePath,String Local){
		Hashtable<String,String>  HTO=null;
		try{
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));

		
		int startRow, startCol, endRow;
		HTO=new Hashtable<String,String>();
		
		Sheet sheet = workbook.getSheet("Local");			
		Cell tableStart = sheet.findCell(Local);
		startRow = tableStart.getRow()+1;
		startCol = tableStart.getColumn();
		endRow=sheet.getRows();

		
		for (int j = startRow; j < endRow; j++) {
			if(sheet.getCell(startCol, startRow)!=null){
				HTO.put(sheet.getCell(0, j).getContents(),
						sheet.getCell(startCol,j).getContents());
			}

		}	
		}catch(Exception e){
			e.printStackTrace();
		}

		return (HTO);
	}

	
	public String GetLocalName(String ShortLocal){
		String LocalName = null;
		
		if(ShortLocal.equals("fr")){
			LocalName="French";
		}
		else if(ShortLocal.equals("en")){
			LocalName="English";
		}
		else{
			
		}
		return LocalName;
	}

	public String CT(String string) {

		return string.replace("$$", "\"");

	}
		
	public void StopStartService(String serviceName,String StopStart) throws Exception
	{
		String executeCmd = "cmd /c net "+StopStart+" \"" + serviceName + "\"";
		Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		int processComplete = runtimeProcess.waitFor();

		System.out.println("processComplete: " + processComplete);

		if (processComplete == 1) {
			System.out.println("Service failed");
		}

		else if (processComplete == 0) {
			System.out.println("Service Success");
		}
	}
	
	public String CheckResult(Lib_Global oGbl) throws Exception {
		try {
			String result = "Pass";
			if (oGbl.errFlag > 0) {
				result = "Fail";
			}
			return result;

		} catch (Exception e) {
			throw e;
		}

	}
	
	public File InputStreamToFile(InputStream IS,String FilePath) throws Exception { 
		OutputStream os = null;		
		os = new FileOutputStream(FilePath);
		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = IS.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		IS.close();
		os.close();		
		os.flush();
		
		return new File(FilePath);
	}
	
	public void GetAndCreateFolderFromJAR(String Source,String Destination) throws Exception{
		URL url = this.getClass().getResource(Source);
		new File(Destination).mkdirs();
		File dir = new File(url.toURI());
		for (File nextFile : dir.listFiles()) {
			nextFile=new File(Destination+"/"+nextFile.getName());
			nextFile.createNewFile();
			Thread.sleep(2000);
		}

	}
	

	public void SendMail(){
	      
	    /*  // Recipient's email ID needs to be mentioned.
	      String to = "abcd@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "web@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText("This is message body");
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "file.txt";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart );

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }*/
	   
	
	}

}
