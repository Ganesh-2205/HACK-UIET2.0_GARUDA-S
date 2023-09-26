package Garuda.com.DNC.api.models;

public class inventory_base { private String product;

    public String getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getCategory() {
        return category;
    }

    public long getAdress() {
        return Long.valueOf(adress);
    }

    private String description;
    private Integer price;
    private String discount;
    private String category;
    private Integer adress;

    public Integer getQuantity() {
        return quantity;
    }

    private Integer quantity;


}
