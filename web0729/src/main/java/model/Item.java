package model;

public class Item {    
    private String sku;
    private String name;
    private double price;

    public Item() {
    }
    
    public Item(String sku, String name, double price) {		
		this.sku = sku;
		this.name = name;
		this.price = price;
	}

	public Item(String sku) {
        this.sku = sku;
    }
    
    public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return sku.equals(item.sku);

    }

    @Override
    public int hashCode() {
        return sku.hashCode();
    }

    @Override
    public String toString() {
        return "Item{" +
                  "sku='" + sku + '\'' +
                  ", name='" + name + '\'' +
                  ", price=" + price +
                  '}';
    }
}


