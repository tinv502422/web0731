
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.*;

@Path("/itemsupdate")
public class ItemRSApp2 {
	static ItemDAO dataService = ItemDAO.getInstance();
	static {
		dataService.createItem(new Item("S001","iPhone",25000.0));
    	dataService.createItem(new Item("S002","iPhone",25000.0));
    	dataService.createItem(new Item("S003","iPhone",25000.0));
	}
    public ItemRSApp2() {
    	
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItems() {
        return dataService.getItemList();
    }
    
    
	@Path("/{sku}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createItem(@PathParam("sku") String sku, Item item) {
		List<Item> data=dataService.getItemList();
		Item d=data.stream().filter(i->i.getSku().equals(sku)).findAny().orElse(null);
		if(d==null)
			return Response.status(Response.Status.NOT_FOUND).build();
		else {
			d.setName(item.getName());
			d.setPrice(item.getPrice());
			return Response.ok().entity(d).build();
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
