import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.*;
import java.util.*;

@Path("/users")
public class UserRSApp {
   UserDAO  dao=new UserDAO();
   
//   @GET
//   @Produces(MediaType.APPLICATION_JSON)
//   public List<User> findAll(){
//	   return dao.findAll();
//   }
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getUsers() {

       List <User> users = dao.findAll();

       if (!users.isEmpty()) {
           return Response.ok(users).build();
       } else {
           return Response.status(Response.Status.NOT_FOUND).build();
       }
   }
   @Path("/{id}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getUserById(@PathParam("id") Long id) {

       User user = dao.fetchBy(id);

       if (user!= null) {
           return Response.ok(user).build();
       } else {
           return Response.status(Response.Status.NOT_FOUND).build();
       }
   }

   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createUser(User user) {
       boolean result = dao.create(user);
       if (result) {
           return Response.ok().entity(user).build();
       } else {
           return Response.notModified().build();
       }
   }

   @PUT
   @Path("/{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateUser(@PathParam("id") long id, User user) {
       boolean result = dao.update(id,user);

       if (result) {
           return Response.ok().entity(user).build();
       } else {
           return Response.notModified().build();
       }
   }


   @Path("/{id}")
   @DELETE
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteUser(@PathParam("id") Long id) {
       boolean result = dao.delete(id);

       if (result) {
           return Response.ok().status(Response.Status.NO_CONTENT).build();
       } else {
           return Response.notModified().build();
       }
   }

   
}
