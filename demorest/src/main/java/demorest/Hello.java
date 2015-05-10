package demorest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.annotation.security.RolesAllowed;

@Path("/hello2")
@RolesAllowed({"admin"})
public class Hello {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{name}")
	public String hola( @PathParam("name") String nombre) {
		return "Hola"+nombre+"huevonaso";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("{name}")
	public String holaXML(@PathParam("name") String nombre){
		return "<?xml version=\"1.0\"?>"+"<hello>Hola "+nombre+" webonaso </hello>";
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("{name}")
	public Saludo holaJSON(@PathParam("name") String nombre){
		Saludo saludo= new Saludo();
		saludo.setNombre(nombre);
		return saludo;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("{name}")
	public String holaHTML(@PathParam("name") String nombre){
		return "<html><body>Hola "+nombre+" sidofjsoifjsoifdj poto </body></html>";
	}

}
