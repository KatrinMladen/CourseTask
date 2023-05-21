package org.chi.example.data;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("goods")
public class Goods {
    private String name;
    private String price;
    private String availability;
    private String code;

public Goods (String name, String price, String availability, String code){
    this.name = name;
    this.price=price;
    this.availability=availability;
    this.code=code;
    }

    public Goods() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", availability='" + availability + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
