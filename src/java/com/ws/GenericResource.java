/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Diana
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of com.ws.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("Get")
    public String Get(String request) throws SQLException{
       
        JSONObject ob = new JSONObject();
        ResultSet Consultar = null;
        JSONObject response = new JSONObject();
        try {
            Conexion con = new Conexion();
            JSONObject jRequest = new JSONObject(request);
            JSONArray consulta = new JSONArray();
            Consultar = con.Consultar1();
            while (Consultar.next()){
            
                jRequest.put("nombre", Consultar.getString("nombre"));
                jRequest.put("cedula", Consultar.getString("cedula"));
                jRequest.put("huella_imagen", Consultar.getString("huella_imagen"));
                jRequest.put("huella_template_iso19794", Consultar.getString("huella_template_iso19794"));
                
                consulta.put(jRequest);
            }
            response.put("cliente", consulta);
        } catch (JSONException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.toString();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("Api")
    public String Api(String request) throws SQLException{
       
        JSONObject ob = new JSONObject();

        try {
            Conexion con = new Conexion();
            JSONObject jRequest = new JSONObject(request);
            ob.put("nombre", jRequest.getString("nombre"));
            ob.put("cedula", jRequest.getString("cedula"));
            ob.put("huella_imagen", jRequest.getString("huella_imagen"));
            ob.put("huella_template_iso19794", jRequest.getString("huella_template_iso19794"));
            con.insertUpdate(jRequest.getString("nombre"),jRequest.getString("cedula"), jRequest.getString("huella_imagen"), jRequest.getString("huella_template_iso19794"));
        } catch (JSONException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob.toString();
    }
    
    
}
