package Garuda.com.DNC.service.ADress;

import Garuda.com.DNC.Model.Cart;
import Garuda.com.DNC.Model.DAO.*;
import Garuda.com.DNC.Model.LocalAddress;
import Garuda.com.DNC.Model.LocalUser;

import Garuda.com.DNC.Model.order.Orders;
import Garuda.com.DNC.api.exceptions.useralreadyexists;
import Garuda.com.DNC.api.models.AddressBody;

import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class uadressservice {

    private userDAO userdao;

    public uadressservice(userDAO userdao, adresslist list, AddressDAO add, orderDAO or) {
        this.userdao = userdao;
        this.list = list;
        this.add = add;
        this.or = or;
    }

    adresslist list;

    private AddressDAO add;
    private orderDAO or;
    private cartDAO car;


    public LocalAddress register(String user,AddressBody body)throws useralreadyexists {

        Optional<LocalUser> opuser= userdao.findByUserIgnoreCase(user);
        if(!opuser.isPresent()){
            throw new useralreadyexists();
        };
        LocalUser ven= opuser.get();

        LocalAddress dr=new LocalAddress();
        dr.setMobile(body.getMobile());
        dr.setCity(body.getCity());
        dr.setAdress(body.getAdress());
        dr.setState(body.getState());
        dr.setPin(body.getPincode());
        dr.setLocalUser(ven);
        dr.setCountry(body.getCountry());
        return add.save(dr);




    }

    public List<Orders> getorders(String User){
        Optional<LocalUser> user=userdao.findByUserIgnoreCase(User);

     return or.findByLocalUser_Orderses_LocalUser(user.get());


        }

    public List<Cart> getcart(String User){
        Optional<LocalUser> user=userdao.findByUserIgnoreCase(User);

        return car.findByInventory_Carts_LocalUser(user.get());


    }
    }


