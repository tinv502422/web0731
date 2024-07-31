import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import model.*;

@Path("/items")
public class ItemRSApp {   

    private ItemDAO dataService = ItemDAO.getInstance();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItems() {
        return dataService.getItemList();
    }

    @Path("/{sku}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(@PathParam("sku") String sku ,Item item) {
        if (dataService.itemExists(sku)) {
            //status code 204
            return Response.noContent()
                      .build();
        } else {
            dataService.createItem(item);
            return Response.created(URI.create("/api/items/"+sku)).build();
        }
    }

    @Path("/{sku}/{name}/{price}")
    @PUT     
    public Response createItem(@PathParam("sku") String sku , @PathParam("name") String name ,@PathParam("price") double price) {
        if (dataService.itemExists(sku)) {
            //status code 204
            return Response.noContent()
                      .build();
        } else {
            dataService.createItem(sku,name,price);             
            //status code 201
            //sends back new URI in header key = 'LOCATION'
            return Response.created(URI.create("/api/items/"+sku)).build();
        }
    }

    @GET
    @Path("/{sku}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("sku") String sku) {
        Item item = dataService.getItemBySku(sku);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND)
                      .build();
        } else {
            return Response.ok()
                      .entity(item)
                      .build();
        }
    }

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteItem(Item item) {
		Item d=dataService.remove(item);		
		if(d==null)
			return Response.status(Response.Status.NOT_MODIFIED).build();
		else {			
			return Response.ok().entity(d).build();
		}
	}
}

