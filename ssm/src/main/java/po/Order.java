package po;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private int id;
    private String customer;
    private String cook;
    private int isdone;
    private double total;
    private List<Menu> items;
}
