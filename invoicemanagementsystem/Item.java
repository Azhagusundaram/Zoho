package invoicemanagementsystem;

public class Item {
    private int itemId;
    private String name;
    private int price;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String toString(){
        return "Item id : "+itemId+"\t"+name+" - "+price;
    }
}
//5
//A
//10
//B
//20
//C
//30
//D
//40
//E
//50