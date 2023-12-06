public class product {
    private String id;
    private String name;
    private double price;
    private  double discount;
    private  double salePrice;

    public product() {}
    public product(String id, String name, double price, double discount, double salePrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.salePrice = salePrice;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void calculatePriceOut() {
        this.salePrice = this.price - (this.price * this.discount / 100);
    }
}
