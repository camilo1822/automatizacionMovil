package nequi_appium;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class Funciones {
	public String Str_Ruta_Evidencias = "C:\\evidencias_nequi\\";
	public int Dbl_X_1 = 0;
	public int Dbl_Y_1 = 0;
	public int[] conx = new int[4];
	public int[] cony = new int[4];
	public int startx = 0;
	public int endx = 0;
	public int starty = 0;
	public File pantalla;
	public String textoPantalla="";
	
	/*Toma pantallazo de ventana actual*/
	public void toma_Evidencias(AndroidDriver<WebElement> Obj_Android_Driver) throws IOException
	{
			SimpleDateFormat Dt_Fecha = new SimpleDateFormat("dd-M-yyyy hh:mm:ss mmm");	
			String Str_Nombre_Archivo = Dt_Fecha.format(new Date());
			Str_Nombre_Archivo = Str_Nombre_Archivo.replaceAll(":", "_");
			Str_Nombre_Archivo = Str_Nombre_Archivo.replaceAll("-", "_");
			File Obj_Archivo = ((TakesScreenshot) Obj_Android_Driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Obj_Archivo, new File(Str_Ruta_Evidencias + "Screenshot_" + Str_Nombre_Archivo +".jpg"));
	}
	
	/*Busca elemento en pantalla*/
	public void buscaElemento(String Str_Elemento,AndroidDriver<WebElement> Obj_Android_Driver)
	{
		try
		{	
			System.out.println(" **** Entra a metodo busca ************** OK");
			System.out.println(Str_Elemento);
			WebElement Obj_Elemento;
			Obj_Elemento = Obj_Android_Driver.findElement(By.xpath(Str_Elemento));				
			int loc_X = Obj_Elemento.getLocation().getX();
			int loc_Y = Obj_Elemento.getLocation().getY();
			int tam_x = Obj_Android_Driver.manage().window().getSize().getWidth();
			int tam_y = Obj_Android_Driver.manage().window().getSize().getHeight();
			if(loc_X > tam_x){
				Dbl_X_1 = loc_X - Obj_Android_Driver.manage().window().getSize().getWidth();
			}else{
				Dbl_X_1 = loc_X;
			}
			if(loc_Y > tam_y){
				Dbl_Y_1 = loc_Y - Obj_Android_Driver.manage().window().getSize().getHeight();
			}else{
				Dbl_Y_1 = loc_Y;
			}
			Obj_Android_Driver.tap(1, Dbl_X_1+30, Dbl_Y_1+30, 50);
			System.out.println(" Tap en el elemento encontrado"+Str_Elemento);
		}
		catch (Exception Obj_error) 
		{
			System.out.println(" **** Error buscando elemento **** ");
		}
	}
	
	/*Ingresa contraseña rosada*/
	public void contrasena(String Str_Elemento,AndroidDriver<WebElement> Obj_Android_Driver)
	{
		System.out.println(" **** Entra al metodo contraseña ****");
		for (int int_Contador = 0 ; int_Contador < Str_Elemento.length();int_Contador++)
		{
			WebElement Obj_Elemento;
			Obj_Elemento = Obj_Android_Driver.findElement(By.xpath("//*/android.widget.Button[contains(@content-desc,'" + Str_Elemento.charAt(int_Contador) +"')]"));	
			int loc_X = Obj_Elemento.getLocation().getX();
			int loc_Y = Obj_Elemento.getLocation().getY();
			int tam_x = Obj_Android_Driver.manage().window().getSize().getWidth();
			int tam_y = Obj_Android_Driver.manage().window().getSize().getHeight();
			if(loc_X > tam_x){
				Dbl_X_1 = loc_X - Obj_Android_Driver.manage().window().getSize().getWidth();
			}else{
				Dbl_X_1 = loc_X;
			}
			if(loc_Y > tam_y){
				Dbl_Y_1 = loc_Y - Obj_Android_Driver.manage().window().getSize().getHeight();
			}else{
				Dbl_Y_1 = loc_Y;
			}
			conx[int_Contador] = Dbl_X_1+30;
			cony[int_Contador] = Dbl_Y_1+30;
			Obj_Android_Driver.tap(1, Dbl_X_1+30, Dbl_Y_1+30, 50);
		}
	}
	
	public void escogerOrigen(String origen,AndroidDriver<WebElement> Obj_Android_Driver) throws InterruptedException{
		System.out.println(" **** Ingreso Busca Origen ************** OK");
		List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*/android.widget.ListView/android.view.View/android.view.View"));
		int swipes = 0;
		Dimension Obj_Tamano = Obj_Android_Driver.manage().window().getSize();
		startx = (int) (Obj_Tamano.width * 0.98);
		endx = (int) (Obj_Tamano.width * 0.05);
		starty = Obj_Tamano.height / 2;
		for(int i=0;i<=list.size();i++){
			String nombre=list.get(i).getAttribute("name");
			System.out.println(nombre);
			if(nombre.indexOf(origen)>0){
				swipes=i;
				break;
			}
		}
		if(swipes>0){
		for(int j=1;j<=swipes;j++){
			Thread.sleep(4000);
			Obj_Android_Driver.swipe(startx, starty, endx, starty, 100);
		}
		}
		Thread.sleep(4000);
		Obj_Android_Driver.tap(1, Obj_Tamano.width/2,Obj_Tamano.height/2, 0);
	}
	
	public File esperarCambio(AndroidDriver<WebElement> Obj_Android_Driver) throws InterruptedException
	{
		Thread.sleep(1000);
		pantalla = ((TakesScreenshot) Obj_Android_Driver).getScreenshotAs(OutputType.FILE);
		textoPantalla = pantalla.toString();
		File cambios = new File(textoPantalla);
		return cambios;
	}

	public void compara(File file1, File file2,AndroidDriver<WebElement> Obj_Android_Driver) throws IOException
	{
		while(IsImgEquals(file1, file2)==true){
			pantalla = ((TakesScreenshot) Obj_Android_Driver).getScreenshotAs(OutputType.FILE);
			textoPantalla = pantalla.toString();
			file2 = new File(textoPantalla);
			System.out.println("igual");
		}
		System.out.println("diferente");
	}
		
	public boolean IsImgEquals(File pngFile, File file) throws IOException {	
		BufferedImage imageA = ImageIO.read(pngFile);
	    BufferedImage imageB = ImageIO.read(file);
	    
	    BufferedImage recorte = ((BufferedImage) imageA).getSubimage(0,100,imageA.getWidth(),imageA.getHeight()-100);
	    File img1 = new File("C:\\comparar_nequi\\punto.jpg");
	    ImageIO.write(recorte, "jpg", img1);
	    BufferedImage recorte2 = ((BufferedImage) imageB).getSubimage(0,100,imageB.getWidth(),imageB.getHeight()-100);
	    File img2 = new File("C:\\comparar_nequi\\punto2.jpg");
	    ImageIO.write(recorte2, "jpg", img2);
	    File file1=new File("C:\\comparar_nequi\\punto.jpg");
		File file2=new File("C:\\comparar_nequi\\punto2.jpg");
		BufferedImage imageC = ImageIO.read(file1);
	    BufferedImage imageD = ImageIO.read(file2);
 
	    DataBufferInt dataBufferA = (DataBufferInt) imageC.getRaster().getDataBuffer();
	    DataBufferInt dataBufferB = (DataBufferInt) imageD.getRaster().getDataBuffer();
 
	    if (dataBufferA.getNumBanks() != dataBufferB.getNumBanks()) {
	        return false;
	    }
 
	    for (int bank = 0; bank < dataBufferA.getNumBanks(); bank++) {
	        if (!Arrays.equals(dataBufferA.getData(bank), dataBufferB.getData(bank))) {
	            return false;
	        }
	    }
 
	    return true;
	}
	
	public void ingresoOTP(String Str_Elemento,AndroidDriver<WebElement> Obj_Android_Driver)
	{
		for (int int_Contador = 0 ; int_Contador < Str_Elemento.length();int_Contador++)
		{
			buscaElemento("//*/android.widget.Button[contains(@content-desc,'" + Str_Elemento.charAt(int_Contador) +"')]",Obj_Android_Driver);
		}
	}				

	public void opEditarBol(String origen,AndroidDriver<WebElement> Obj_Android_Driver) throws InterruptedException{
		System.out.println(" **** Entra en el metodo ************** OK");
		List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*/android.view.View/android.view.View"));
		int editar=0;
		for(int i=0;i<list.size();i++){
			String nombre=list.get(i).getAttribute("name");
			if(nombre.indexOf(origen)>=0){
				editar=i;
				break;
			}
		}
		int posicionx=list.get(editar+2).getLocation().getX();
		int posiciony=list.get(editar+2).getLocation().getY();	
		Thread.sleep(4000);
		Obj_Android_Driver.tap(1, posicionx,posiciony, 0);	
	}
	
	public String opSelMeta(String origen,AndroidDriver<WebElement> Obj_Android_Driver) throws InterruptedException{
		System.out.println(" **** Entra en el metodo ************** OK");
		List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*/android.view.View"));
		int editar=0;
		for(int i=0;i<list.size();i++){
			String nombre=list.get(i).getAttribute("name");
			if(nombre.indexOf(origen)>=0){
				editar=i;
				break;
			}
		}		
		String saldo=list.get(editar+3).getAttribute("name");
		return saldo;
	}
	
	public String opRecarga(String origen,AndroidDriver<WebElement> Obj_Android_Driver) throws InterruptedException{
		System.out.println(" **** Entra en el metodo ************** OK");
		List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*/android.view.View"));
		int editar=0;
		for(int i=0;i<list.size();i++){
			String nombre=list.get(i).getAttribute("name");
			if(nombre.indexOf(origen)>=0){
				editar=i;
				break;
			}
		}		
		String saldo=list.get(editar+2).getAttribute("name");
		return saldo;
	}
	
	public void abrirVelo(AndroidDriver<WebElement> Obj_Android_Driver) throws IOException
	{	
		System.out.println("********** Abrir velo de Transacciones ***********");
		try 
		{			
			Thread.sleep(3000);
			double x = Obj_Android_Driver.manage().window().getSize().getWidth();
			double y = Obj_Android_Driver.manage().window().getSize().getHeight();
			x=x-x*0.05;
			y=y-y*0.05;
			Obj_Android_Driver.tap(1, (int)x-35, (int)y-20, 0);			
			System.out.println(" **** Validacion elemento $ Velo de Transacciones ************** OK");
		}
			catch (Exception Obj_Error)
		{
			System.out.println(" **** Validacion elemento $ Velo de Transacciones ************** FAIL");
			toma_Evidencias(Obj_Android_Driver);
		}			
	}
	
	public void editaBolsillo(AndroidDriver<WebElement> Obj_Android_Driver,String Str_Nombre2_Bolsillo) throws IOException{
		System.out.println("***** Editar Bolsillo **********");
		String mensaje="";
		try {
			WebElement Obj_Elemento;
			int anch_pant = Obj_Android_Driver.manage().window().getSize().getWidth();
			Thread.sleep(4000);
//			WebElement camp_cel = Obj_Android_Driver.findElementByXPath("//*/android.widget.EditText");
			
//			int x = camp_cel.getLocation().getX();
//			int y = camp_cel.getLocation().getY();
//			Obj_Android_Driver.tap(1, x+30,y+30, 3000);
//			System.out.println(" **** Campo Nombre del Bolsillo ************** OK");
//			Thread.sleep(500);
//			Obj_Android_Driver.findElement(By.xpath("//*/android.widget.EditText")).sendKeys("");
//			Thread.sleep(2000);	
			//Obj_Android_Driver.findElement(By.xpath("//*/android.widget.EditText")).sendKeys(Str_Nombre2_Bolsillo);
			//Thread.sleep(2000);	
			//buscaElemento("//*[contains(@content-desc,'Editar tu bolsillo')]",Obj_Android_Driver);

			for(int j = 0;j<10;j++){
				buscaElemento("//*[contains(@content-desc,'+')]",Obj_Android_Driver);
			}
			Thread.sleep(4000);
			Obj_Elemento = Obj_Android_Driver.findElement(By.xpath("//*[contains(@content-desc,'Editar tu bolsillo')]"));
			int loc_Y2 = Obj_Elemento.getLocation().getY();
			Obj_Android_Driver.tap(1, anch_pant-60, loc_Y2+30, 50);
			Thread.sleep(8000);
			mensaje="Fallo";
			toma_Evidencias(Obj_Android_Driver);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'bolsillo_icono_bolsillonuevo')]");

		}
		catch (Exception Obj_Error)
		{
			System.out.println(" ****  Editar Bolsillo ************** FAIL "+mensaje);
			toma_Evidencias(Obj_Android_Driver);
		}	
	}
		
	public void eliminaBolsillo(AndroidDriver<WebElement> Obj_Android_Driver,String Str_Nombre_Bolsillo){
		System.out.println("***** Eliminar Bolsillo *****");
		String mensaje="";
		try {
			Thread.sleep(5000);
			List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*/android.view.View"));
			for(int i = 0;i<list.size();i++){
				String name = list.get(i).getAttribute("name");
				if(name.indexOf(Str_Nombre_Bolsillo)>=0){
					list.get(i+2).click();
					break;
				}
			}			
			Thread.sleep(4000);
			buscaElemento("//*[contains(@content-desc,'Acepta')]",Obj_Android_Driver);		
			System.out.println("***** Eliminar Bolsillo OK *****");
			Thread.sleep(8000);
			mensaje="fallo Eliminar";
			toma_Evidencias(Obj_Android_Driver);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'bolsillo_icono_bolsillonuevo')]");
		} catch (Exception e) {
			System.out.println("***** Eliminar Bolsillo FAIL ***** " + mensaje);
		}
	}
	

}
