package Garuda.com.DNC.service;

import Garuda.com.DNC.Model.DAO.Address2DAO;
import Garuda.com.DNC.Model.DAO.InventoryDAO;
import Garuda.com.DNC.Model.DAO.VendorDAO;
import Garuda.com.DNC.Model.Inventory;
import Garuda.com.DNC.Model.LocalUser;
import Garuda.com.DNC.Model.Vendor;
import Garuda.com.DNC.Model.Vendoradd;
import Garuda.com.DNC.Model.order.Orders;
import Garuda.com.DNC.api.models.inventory_base;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class sellerservice {
    Address2DAO add;
    VendorDAO user;

    public sellerservice(Address2DAO add, VendorDAO user, InventoryDAO inv) {
        this.add = add;
        this.user = user;
        this.inv = inv;
    }

    InventoryDAO inv;

    public Inventory inventory(String data,inventory_base base){
        Optional<Vendoradd> opuser=add.findById(Long.valueOf(base.getAdress()));
        if(opuser.isEmpty())
            throw new RuntimeException();
        Optional<Vendor> frame=user.findByUserIgnoreCase(data);


            Vendoradd user= opuser.get();
            Inventory ins=new Inventory();
            ins.setCategory(base.getCategory());
            ins.setVendoradd(user);
            ins.setDescription((base.getDescription()));
            ins.setDiscount(base.getDiscount());
            ins.setPrice(base.getPrice());
            ins.setQuantity(base.getQuantity());
            ins.setProduct_name(base.getProduct());
            ins.setVendor(frame.get());


        return inv.save(ins);
    }
    public List<Inventory> getorders(String User){
        Optional<Vendor> base=user.findByUserIgnoreCase(User);

        return inv.findByVendor_Inventories_Vendor(base.get());


    }
}
