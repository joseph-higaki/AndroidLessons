package demorest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Saludo {
	
	private String salidoHola;
	private String nombre;
	
	public String getSalidoHola() {
		return "Hola ";
	}
	public void setSalidoHola(String salidoHola) {
		this.salidoHola = salidoHola;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
