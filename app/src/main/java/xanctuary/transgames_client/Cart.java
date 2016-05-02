package xanctuary.transgames_client;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Carts")
public class Cart extends Model{

    @Column(name = "No")
    public int no;

    @Column(name = "Barang")
    public String barang;

    @Column(name = "Nominal")
    public int nominal;

    @Column(name = "Harga")
    public double harga;

    @Column(name = "Qty")
    public int qty;

    public Cart() {
        super();
    }

}
