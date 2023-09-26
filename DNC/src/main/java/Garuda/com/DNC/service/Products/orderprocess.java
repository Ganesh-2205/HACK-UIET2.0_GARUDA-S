package Garuda.com.DNC.service.Products;

import Garuda.com.DNC.Model.Cart;
import Garuda.com.DNC.Model.DAO.*;
import Garuda.com.DNC.Model.Inventory;
import Garuda.com.DNC.Model.LocalAddress;
import Garuda.com.DNC.Model.LocalUser;
import Garuda.com.DNC.Model.order.Orders;
import Garuda.com.DNC.api.models.orderbase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class orderprocess {




    public orderprocess(InventoryDAO inv, AddressDAO add, orderDAO order, cartDAO cart, userDAO user) {
        this.inv = inv;
        this.add = add;
        this.order = order;
        this.cart = cart;
        this.user = user;
    }

    InventoryDAO inv;
    AddressDAO add;
    orderDAO order;
    cartDAO cart;

    userDAO user;



    public Orders post(String data,orderbase base){
        Optional<Inventory> product=inv.findById(Long.valueOf(base.getProduct_id()));
        Inventory pro= product.get();
        if(pro.getQuantity()< base.getQuantity()){
            throw new RuntimeException();
        }

        Optional<LocalAddress> dress=add.findByLocalUser_LocalAddresses_Id(Long.valueOf(base.getId()));
        LocalAddress address= dress.get();

        Optional<LocalUser> user1=user.findByUserIgnoreCase(data);
        Orders ord=new Orders();
        ord.setLocalAddress(address);
        ord.setInventory(pro);
        ord.setProduct(pro.getProduct_name());
        ord.setPrice(pro.getPrice()*base.getQuantity());
        ord.setQuantity(base.getQuantity());
        pro.setQuantity(pro.getQuantity()- base.getQuantity());
        ord.setPrice(pro.getPrice());
        ord.setStatus(false);
        ord.setLocalUser(user1.get());
        return order.save(ord);
    }
    public Cart cart(String data,orderbase base){
        Optional<Inventory> product=inv.findByVendoradd_Inventories_Id(Long.valueOf(base.getProduct_id()));

        Inventory pro= product.get();
        if(pro.getQuantity()< base.getQuantity()){
            throw new RuntimeException();
        }
        Optional<LocalUser> dress=user.findByUserIgnoreCase(data);
        LocalUser address= dress.get();

        Cart ord=new Cart();
        ord.setLocalUser(address);
        ord.setInventory(pro);
        ord.setPrice(pro.getPrice()*base.getQuantity());
        ord.setPrice(pro.getPrice());
        ord.setQuantity(base.getQuantity());
        pro.setQuantity(pro.getQuantity()-base.getQuantity());
        inv.save(pro);
        return cart.save(ord);


    }
    public void bucket(String data,orderbase base){

        Optional<Cart> product=cart.findById(Long.valueOf(base.getProduct_id()));
        Cart cart1= product.get();
        Inventory pro=cart1.getInventory();
        if(pro.getQuantity()< base.getQuantity()){
            throw new RuntimeException();
        }

        Optional<LocalAddress> dress=add.findByLocalUser_LocalAddresses_Id(Long.valueOf(base.getId()));
        LocalAddress address= dress.get();

        Optional<LocalUser> user1=user.findByUserIgnoreCase(data);
        Orders ord=new Orders();
        ord.setLocalAddress(address);
        ord.setInventory(pro);
        ord.setProduct(pro.getProduct_name());
        ord.setPrice(pro.getPrice()*base.getQuantity());
        ord.setQuantity(base.getQuantity());
        pro.setQuantity(pro.getQuantity()- base.getQuantity());
        ord.setPrice(pro.getPrice());
        ord.setStatus(false);
        ord.setLocalUser(user1.get());
    }


    }


