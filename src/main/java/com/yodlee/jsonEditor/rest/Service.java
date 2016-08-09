package com.yodlee.jsonEditor.rest;


import com.yodlee.jsonEditor.Utils.SearchKey;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Service")
public class Service {

   @GET
   @Produces(MediaType.TEXT_PLAIN)
   public String getMessage() {
       return "Hello Anshu!";
   }

   /*@POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getExactMatch(@FormParam("specificPath") String Path, @FormParam("key") String Key){
       SearchKey sk =new SearchKey();
       ArrayList list= sk.getFilesExactMatch(Key,Path);   //"SecurityData[0].Description"
       return Response.status(200).entity(list).build();
      //return list;

   }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getGenericMatch(@FormParam("genericPath") String Path, @FormParam("key") String Key){
        SearchKey sk =new SearchKey();
        ArrayList list= sk.getFilesGeneric(Key,Path);    //"Description"
        return Response.status(200).entity(list).build();
        //return list;

    }*/

    @Path("{x}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getFiles(@PathParam("x") String x, @FormParam("path") String Path, @FormParam("key") String Key){
        SearchKey sk =new SearchKey();
        ArrayList list=null;
        if(x.equals("GenericMatch"))
                list= sk.getFilesGeneric(Key,Path);    //"Description"
        else if(x.equals("SpecificMatch"))
            list= sk.getFilesExactMatch(Key,Path);     //"SecurityData[0].Description"
        return Response.status(200).entity(list).build();
        //return list;

    }

}


