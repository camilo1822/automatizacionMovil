package nequi_appium;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;

public class Flujos {
	static Funciones funciones = new Funciones();
	static File file1;
	static File file2;
	public static int startx = 0;
	public static int endx = 0;
	public static int starty = 0;
	
	public void paseo(AndroidDriver<WebElement> Obj_Android_Driver) throws InterruptedException, IOException
	{
		System.out.println(" **** INICIO PASEO ************** OK");
		Dimension Obj_Tamano = Obj_Android_Driver.manage().window().getSize();
		startx = (int) (Obj_Tamano.width * 0.98);
		endx = (int) (Obj_Tamano.width * 0.05);
		starty = Obj_Tamano.height / 2;
		try 
		{
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			Thread.sleep(15000);
			Obj_Android_Driver.swipe(startx, starty, endx, starty, 100);
			System.out.println(" **** SIN ATADURAS ************** OK");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Hazlo')]");
			Obj_Android_Driver.swipe(startx, starty, endx, starty, 100);
			System.out.println(" **** HAZLO ************** OK");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Así que relájate')]");
			Obj_Android_Driver.swipe(startx, starty, endx, starty, 100);
			System.out.println(" ****  Así que relajate  ************** OK");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			Obj_Android_Driver.swipe(startx, starty, endx, starty, 100);
		}
		catch (Exception Obj_Error)
		{
			funciones.toma_Evidencias(Obj_Android_Driver);
			System.out.println(" **** PASEO ************** FAIL");
		}
	}
	
	public void entrarAppCuenta(AndroidDriver<WebElement> Obj_Android_Driver,String Str_Contrasena,String Str_OTP,String Str_Numero_Telefono) throws InterruptedException, IOException{	
		System.out.println("**** Entra App Inicio **************");
		try 
		{
			Thread.sleep(4000);			
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.buscaElemento("//*[contains(@content-desc,'Entra') and @clickable='true']",Obj_Android_Driver);		
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			file1 = funciones.esperarCambio(Obj_Android_Driver);			
			System.out.println(" Busca el mero de celular --------------------------------");
			Thread.sleep(4000);	
			Obj_Android_Driver.findElementByXPath("//*/android.widget.EditText").sendKeys(Str_Numero_Telefono);
			Thread.sleep(4000);	
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);	
			System.out.println(" **** Ingresa el Número ************** OK");
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.buscaElemento("//*[contains(@content-desc,'Entra') and @clickable='true']",Obj_Android_Driver);		
			System.out.println(" **** Entra ************** OK");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);			
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.contrasena(Str_Contrasena,Obj_Android_Driver);
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			Thread.sleep(5000);
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.buscaElemento("//*[contains(@content-desc,'Listo!') and @clickable='true' and @focusable='true']",Obj_Android_Driver);
			System.out.println(" **** Clic en Listo ************** OK");	
			Thread.sleep(12000);
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			//** OTP
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.ingresoOTP(Str_OTP,Obj_Android_Driver);
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			System.out.println(" **** Ingreso OTP ************** OK");
			Thread.sleep(10000);
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.buscaElemento("//*[contains(@content-desc,'Reconocimiento facial') and @clickable='true' and @focusable='true']",Obj_Android_Driver);
			System.out.println(" **** Se Evidencia mensaje de Reconocimiento Fasial ************** OK");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			
			Thread.sleep(5000);
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.buscaElemento("//*[contains(@content-desc,'Ahora no')]",Obj_Android_Driver);
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
	
			System.out.println(" **** Click - Ahora no ************** OK");
			Thread.sleep(5000);
			
			System.out.println(" **** Click Listo ************** OK");
			Thread.sleep(2000);
			
			int anch_pant = Obj_Android_Driver.manage().window().getSize().getWidth();
			int alt_pant = Obj_Android_Driver.manage().window().getSize().getHeight();
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			Obj_Android_Driver.tap(4, anch_pant/2, alt_pant/2, 1000);
	
			System.out.println(" **** Clic en pantalla Tutorial ************** OK \n");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			}
			catch (Exception Obj_Error)
			{
				System.out.println(" **** Entra App ************** FAIL"+Obj_Error);
				funciones.toma_Evidencias(Obj_Android_Driver);
	
			}
			System.out.println(" **************************  Llegamos al HOME *******************************\n");
	}
	
	public void pedirVinculado(AndroidDriver<WebElement> Obj_Android_Driver,String Str_Numero_Amigo) throws IOException{
		System.out.println("********* Pedir plata vinculado ***********");
		String mensaje="";
		try 
		{
			funciones.abrirVelo(Obj_Android_Driver);
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.buscaElemento("//*[contains(@content-desc,'Pide') and @clickable='true']",Obj_Android_Driver);
			System.out.println(" **** Clic en Pide ************** OK");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			file1 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.buscaElemento("//*[contains(@content-desc,'Entendido') and @clickable='true']",Obj_Android_Driver);
			System.out.println(" **** Entendido ************** OK");
			file2 = funciones.esperarCambio(Obj_Android_Driver);
			funciones.compara(file1,file2,Obj_Android_Driver);
			file1 = funciones.esperarCambio(Obj_Android_Driver);			
			WebElement Obj_Elemento;
			Obj_Elemento = Obj_Android_Driver.findElement(By.xpath("//*[contains(@content-desc,'Datos')]"));				
			int loc_X = Obj_Elemento.getLocation().getX();
			int loc_Y = Obj_Elemento.getLocation().getY();
			int tamX = Obj_Elemento.getSize().getWidth();
			int tamY = Obj_Elemento.getSize().getHeight();
			Obj_Android_Driver.tap(1, loc_X+tamX, loc_Y+(tamY*3), 50);	
			Thread.sleep(5000);
			int tam_x = Obj_Android_Driver.manage().window().getSize().getWidth();
			int tam_y = Obj_Android_Driver.manage().window().getSize().getHeight();
			Obj_Android_Driver.tap(1, tam_x-200, tam_y-200, 50);
			Thread.sleep(4000);
			List<WebElement> list= Obj_Android_Driver.findElements(By.xpath("//*/android.widget.EditText"));
			list.get(list.size()-1).sendKeys(Str_Numero_Amigo);
			Thread.sleep(3000);
			funciones.buscaElemento("//*[contains(@content-desc,'+')]",Obj_Android_Driver);
			Thread.sleep(3000);
			Obj_Elemento = Obj_Android_Driver.findElement(By.xpath("//*[contains(@content-desc,'Contactos')]"));
			int loc_Y2 = Obj_Elemento.getLocation().getY();
			Obj_Android_Driver.tap(1, tam_x-60, loc_Y2+30, 50);
			Thread.sleep(3000);
			Obj_Android_Driver.findElement(By.xpath("//*[contains(@text,'$ 0')]")).sendKeys("1000");
			Thread.sleep(3000);
			funciones.buscaElemento("//*[contains(@content-desc,'Sigue')]",Obj_Android_Driver);
			Thread.sleep(3000);
			funciones.buscaElemento("//*[contains(@content-desc,'Pide')]",Obj_Android_Driver);
			Thread.sleep(8000);
			mensaje="fallo pedir";
			funciones.toma_Evidencias(Obj_Android_Driver);
			Obj_Elemento = Obj_Android_Driver.findElement(By.xpath("//*[contains(@content-desc,'Total')]"));
		}
		catch (Exception Obj_Error)
		{
			System.out.println(" ****  Pedir plata vinculado ************** FAIL " + mensaje);
			funciones.toma_Evidencias(Obj_Android_Driver);
		}	
	}
	
	public void pagarVinculado(String origen,AndroidDriver<WebElement> Obj_Android_Driver) throws IOException{
		System.out.println("****** Pagar *************");
		try 
		{
			funciones.abrirVelo(Obj_Android_Driver);
			Thread.sleep(10000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Paga') and @clickable='true']").click();
			System.out.println(" **** Clic en Pagar ************** OK");
			Thread.sleep(5000);
			funciones.escogerOrigen(origen,Obj_Android_Driver);
			Thread.sleep(4000);
		}
		catch (Exception Obj_Error)
		{
			System.out.println(" **** Pagar ************** FAIL");
			funciones.toma_Evidencias(Obj_Android_Driver);
		}
	}
	
	public void enviarVinculado(String origen,String Str_Contrasena,String Str_Numero_Amigo,AndroidDriver<WebElement> Obj_Android_Driver) throws IOException{
		System.out.println("********* Enviar vinculado ***********");
		String mensaje="";
		try 
		{
			// ****************** Abre Velo de Transacciones *********************
			int tam_x = Obj_Android_Driver.manage().window().getSize().getWidth();
			funciones.abrirVelo(Obj_Android_Driver);
			Thread.sleep(10000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Envía') and @clickable='true']").click();			
			Thread.sleep(5000);
			funciones.escogerOrigen(origen,Obj_Android_Driver);
			Thread.sleep(6000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Entendido') and @clickable='true']").click();
			System.out.println(" **** Entendido ************** OK");
			Thread.sleep(3000);
			Obj_Android_Driver.findElement(By.xpath("//*/android.widget.EditText")).sendKeys(Str_Numero_Amigo);		
			Thread.sleep(3000);
			Obj_Android_Driver.tap(1,tam_x/2,90, 0);
			Obj_Android_Driver.findElement(By.xpath("//*[contains(@text,'$ 0')]")).sendKeys("1000");
			Thread.sleep(3000);
			Obj_Android_Driver.tap(1,tam_x/2,90, 0);
			Thread.sleep(3000);			
			Obj_Android_Driver.findElement(By.xpath("//*[contains(@content-desc,'Env')]")).click();
			Thread.sleep(3000);			
			funciones.buscaElemento("//*[contains(@content-desc,'Env') and @class='android.widget.Button']",Obj_Android_Driver);
			Thread.sleep(6000);			
			//********************************** Segunda Clave ********************
			for(int i=0; i<4; i++){
				Thread.sleep(1000);
				Obj_Android_Driver.tap(1, funciones.conx[i], funciones.cony[i], 0);
			}
			Thread.sleep(8000);
			mensaje="fallo Enviar";
			funciones.toma_Evidencias(Obj_Android_Driver);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Tu cuenta')]");
		}
		catch (Exception Obj_Error)
		{
			System.out.println(" **** Enviar vinculado ************** FAIL "+mensaje);
			funciones.toma_Evidencias(Obj_Android_Driver);
		}
	
	}
	
	public void sacarVinculado(String origen,AndroidDriver<WebElement> Obj_Android_Driver) throws IOException{
		String mensaje="";
		try 
		{
			funciones.abrirVelo(Obj_Android_Driver);
			Thread.sleep(5000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Saca') and @clickable='true']").click();
			Thread.sleep(5000);
			funciones.escogerOrigen(origen,Obj_Android_Driver);
			Thread.sleep(6000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Cajero') and @clickable='true']").click();
			Thread.sleep(4000);
			//********************************** Segunda Clave ********************
			for(int i=0; i<4; i++){
				Thread.sleep(1000);
				Obj_Android_Driver.tap(1, funciones.conx[i], funciones.cony[i], 0);
			}
			Thread.sleep(8000);
			mensaje="fallo Enviar";
			funciones.toma_Evidencias(Obj_Android_Driver);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'Vence')]");
			System.out.println(" **** Flujo Sacar ************** OK");

		}
		catch (Exception Obj_Error)
		{
			System.out.println(" ****  Flujo Sacar ************** FAIL "+mensaje);
			funciones.toma_Evidencias(Obj_Android_Driver);
		}	
	}
	
	public void creaBolsillo(AndroidDriver<WebElement> Obj_Android_Driver,String Str_Nombre_Bolsillo) throws IOException{
		String mensaje="";
		try {
			Thread.sleep(2000);
			WebElement Obj_Elemento;
			funciones.buscaElemento("//*[@content-desc='Bolsillos']",Obj_Android_Driver);	
			Thread.sleep(5000);	
			funciones.buscaElemento("//*[@content-desc='bolsillo_icono_bolsillonuevo']",Obj_Android_Driver);
			System.out.println(" **** Clic Crear Bolsillo ************** OK");	
			Thread.sleep(1000);			
			Obj_Android_Driver.findElement(By.xpath("//*/android.widget.EditText")).sendKeys(Str_Nombre_Bolsillo);
			Thread.sleep(1000);
			Obj_Android_Driver.findElementByXPath("//*[contains(@text,'$ 0')]").sendKeys("1000");							
			Thread.sleep(2000);
			int tam_x = Obj_Android_Driver.manage().window().getSize().getWidth();
			Obj_Android_Driver.tap(1,tam_x/2,90, 0);
			Thread.sleep(2000);
			Obj_Elemento = Obj_Android_Driver.findElement(By.xpath("//*[contains(@content-desc,'Crea un bolsillo')]"));
			int loc_Y2 = Obj_Elemento.getLocation().getY();
			Obj_Android_Driver.tap(1, tam_x-60, loc_Y2+30, 50);
			Thread.sleep(8000);
			mensaje="fallo Enviar";
			funciones.toma_Evidencias(Obj_Android_Driver);
			Obj_Android_Driver.findElementByXPath("//*[contains(@content-desc,'bolsillo_icono_bolsillonuevo')]");

		}
		catch (Exception Obj_Error)
		{
			System.out.println(" ****  Crea Bolsillo ************** FAIL "+mensaje);
			funciones.toma_Evidencias(Obj_Android_Driver);
		}	
	}
	
	//*************************************** CRUD BOLSILLOS **************************
	public void crudBolsillo(String accion,AndroidDriver<WebElement> Obj_Android_Driver,String Str_Nombre_Bolsillo,String Str_Nombre2_Bolsillo) throws IOException{
			try {
				funciones.buscaElemento("//*[@content-desc='Bolsillos']",Obj_Android_Driver);	
				Thread.sleep(7000);				
				funciones.opEditarBol(Str_Nombre_Bolsillo,Obj_Android_Driver);
				switch (accion) 
				{ 			
						case "editar": funciones.editaBolsillo(Obj_Android_Driver,Str_Nombre2_Bolsillo); 
						break; 
						case "eliminar": funciones.eliminaBolsillo(Obj_Android_Driver,Str_Nombre_Bolsillo); 
						break;
				}
			} catch (Exception e) {
				System.out.println("**** CRUD BOLSILLO FAIL *******" + e);
				funciones.toma_Evidencias(Obj_Android_Driver);
			}	
		}
		
	
}
