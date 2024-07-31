package model;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private List<Item> itemList = new ArrayList<>();

    private static ItemDAO ourInstance = new ItemDAO();

    public static ItemDAO getInstance() {
    	
        return ourInstance;
    }
    
    public void createItem(Item i) {
        if(!itemExists(i.getSku()))
           itemList.add(i);
        
    }


    public void createItem(String sku,String n,double p) {
        Item itemToStore = new Item(sku);    
        itemToStore.setPrice(p);
        itemToStore.setName(n);
        itemList.add(itemToStore);
    }

    public boolean itemExists(String sku) {
        for (Item item : itemList) {
            if (item.getSku().equals(sku)) {
                return true;
            }
        }
        return false;
    }


    public List<Item> getItemList() {
        return itemList;
    }

    public Item getItemBySku(String sku) {
        for (Item item : itemList) {
            if (item.getSku().equals(sku)) {
                return item;
            }
        }

        return null;
    }
    public Item remove(Item i) {
    	boolean b=itemList.remove(i);
    	if(b)
    	  return i;
    	else
    	  return null;	
    }
}

