package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.tracing.dtrace.FunctionName;

import tm.AlohandesTransactionManager;
import vos.Persona;
import vos.Propuesta;
import vos.Reserva;
import vos.ReservaColectiva;

@Path("funcionalesConsulta")
public class RequerimientosService {
	
	//atributos
	
	/**
	 * 
	 */
	@Context
	private ServletContext context;

	
	//inicializaciones
	
	/**
	 * 
	 * @return
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	private String doErrorMessage(Exception e) {
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	/**
	 * 
	 * @param reserva
	 * @return
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON)
	@Path("RFC1")
	public Response darDineroRecibido() {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<String> dinero = tm.dineroRecibido();
			return Response.status(200).entity(dinero).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}
	
	/**
	 * 
	 * @param reserva
	 * @return
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON)
	@Path("RFC2")
	public Response ofertasPopulares() {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<String> ofertas = tm.PropuestasPopulares();
			return Response.status(200).entity(ofertas).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}
	
	/**
	 * Requerimiento funcional de consulta 3, que saca el indice de ocupación de cada propuesta
	 * @param reserva 
	 * @return
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON)
	@Path("RFC3")
	public Response indiceOcupacion() {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<String> ofertas = tm.IndiceOcupacion();
			return Response.status(200).entity(ofertas).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}
	
	/**
	 * Requerimiento funcional de consulta 3, que saca el indice de ocupación de cada propuesta
	 * @param reserva 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("RFC4")
	public Response alojamientosDisponibles(@QueryParam("in") String in,@QueryParam("fin") String fin,@QueryParam("servs") String servs) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Long> ofertas = tm.darPropuestasDisponibles(in, fin, servs);
			return Response.status(200).entity(ofertas).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}
	
	/**
	 * Requerimiento funcional de consulta 3, que saca el indice de ocupación de cada propuesta
	 * @param reserva 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("RFC5")
	public Response darUsoTotalAlohAndes() {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<String> uso = tm.darUsoAlohAndes();
			return Response.status(200).entity(uso).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}
	
	/**
	 * Requerimiento funcional de consulta 3, que saca el indice de ocupación de cada propuesta
	 * @param reserva 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("RFC6")
	public Response darUsoDeUnUsuario(@QueryParam ("id") Long id) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<String> uso = tm.darUsoIndividualAlohAndes(id);
			return Response.status(200).entity(uso).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("RFC7")
	public Response darOperacionAlohAndes(@QueryParam ("filtro") String filtro,
			@QueryParam ("tiempo") String tiempo,
			@QueryParam ("tipo_alojamiento") String tipo_alojamiento) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<String> uso = tm.darAnálisisProp(filtro, tiempo, tipo_alojamiento);
			return Response.status(200).entity(uso).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}

	/**
	 * 
	 * @param reserva
	 * @return
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON)
	@Path("RFC8")
	public Response darFrecuentes(@QueryParam("tipo")String tipo) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<String> clien = tm.ClientesFrecuentes(tipo);
			return Response.status(200).entity(clien).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}
	
	/**
	 * Requerimiento funcional de consulta 3, que saca el indice de ocupación de cada propuesta
	 * @param reserva 
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc9")
	public Response ofertasSinDemanda() {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Propuesta> uso = tm.propuestasSinDemandas();
			return Response.status(200).entity(uso).build();
			}catch( Exception e ){
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
	}
	
	/**
	 * RFC10 sin parametros
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc10/{fechaInicio: .+}/{fechaFin: .+}")
	public Response consumoQueSeHizoSinAgrupar(@PathParam("fechaInicio") String fechaInicio, @PathParam("fechaFin")String fechaFin) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> personas= tm.consumoQueSeHizoSinAgrupar(fechaInicio, fechaFin);
			return Response.status(200).entity(personas).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	/**
	 * RFC10
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idOperador
	 * @param tipoInmueble
	 * @param tipoPersona
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc10/{fechaInicio: .+}/{fechaFin: .+}/{idOperador: \\d+}/{tipoInmueble: .+}/{tipoPersona: .+}")
	public Response consumoQueSeHizo(@PathParam("fechaInicio") String fechaInicio, @PathParam("fechaFin") String fechaFin, 
			@PathParam("idOperador") Long idOperador, @PathParam("tipoInmueble") String tipoInmueble, @PathParam("tipoPersona") String tipoPersona) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> personas= tm.consumoQueSeHizo(fechaInicio, fechaFin, idOperador, tipoInmueble, tipoPersona);
			return Response.status(200).entity(personas).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * RFC10 ordenado
	 * @param fechaInicio
	 * @param fechaFin
	 * @param pOrdenar
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc10/{fechaInicio: .+}/{fechaFin: .+}/{pOrdenar: .+}")
	public Response consumoQueSeHizoOrdenado(@PathParam("fechaInicio")String fechaInicio, @PathParam("fechaFin") String fechaFin,@PathParam("pOrdenar") String pOrdenar) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> personas= tm.consultaConsumoQueSeHizoOrdenado(fechaInicio, fechaFin, pOrdenar);
			return Response.status(200).entity(personas).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * rfc11 sin parametros
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc11/{fechaInicio: .+}/{fechaFin: .+}")
	public Response consumoQueNoSeHizoSinAgrupar(@PathParam("fechaInicio")String fechaInicio,@PathParam("fechaFin") String fechaFin) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> personas= tm.consumoQueNoSeHizoSinAgrupar(fechaInicio, fechaFin);
			return Response.status(200).entity(personas).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * rfc11 agrupado
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idPropuesta
	 * @param tipoInmueble
	 * @param tipoPersona
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc11/{fechaInicio: .+}/{fechaFin: .+}/{idPropuesta: \\d+}/{tipoInmueble: .+}/{tipoPersona: .+}")
	public Response consumoQueNoSeHizoAgrupado(@PathParam("fechaInicio")String fechaInicio,@PathParam("fechaFin") String fechaFin,
			@PathParam("idPropuesta")Long idPropuesta,@PathParam("tipoInmueble") String tipoInmueble, @PathParam("tipoPersona") String tipoPersona) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> personas= tm.consumoQueSeHizo(fechaInicio, fechaFin, idPropuesta, tipoInmueble, tipoPersona);
			return Response.status(200).entity(personas).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * rfc11 ordenado
	 * @param fechaInicio
	 * @param fechaFin
	 * @param pOrdenar
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc11/{fechaInicio: .+}/{fechaFin: .+}/{pOrdenar: .+}")
	public Response consumoQueNoSeHizoOrdenado(@PathParam("fechaInicio")String fechaInicio,@PathParam("fechaFin") String fechaFin,@PathParam("pOrdenar") String pOrdenar) {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> personas= tm.consultaConsumoQueNoSeHizoOrdendo(fechaInicio, fechaFin, pOrdenar);
			return Response.status(200).entity(personas).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	/**
	 * rfc12 (1)
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc12/masOcupacion")
	public Response funcionamientoPropuestas() {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Propuesta> props= tm.funcionamientoPropuestas();
			return Response.status(200).entity(props).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc12/menosOcupacion")
	public Response noFuncionamientoPropuesta() {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Propuesta> props= tm.noFuncionamientoPropuesta();
			return Response.status(200).entity(props).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc12/masDemandados")
	public Response operadoresMasSolicitados() {
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> props= tm.operadoresMasSolicitados();
			return Response.status(200).entity(props).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc12/menosDemandados")
	public Response operadoresMenosSolicitados() {
		
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> props= tm.operadoresMenosSolicitados();
			return Response.status(200).entity(props).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * RFC13
	 * @param cantidadMes
	 * @param cantidadMes1
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rfc13/{cantMes: \\d+}")
	public Response buenosClientes(@PathParam("cantMes")Long cantidad0Mes) {
		
		System.out.println("hola .|.");
		Integer cantMes= cantidad0Mes.intValue();
		try {
			AlohandesTransactionManager tm= new AlohandesTransactionManager(getPath());
			List<Persona> personas= tm.buenosClientes(cantMes);
			return Response.status(200).entity(personas).build();
		}catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	

	
	
}
