package Garuda.com.DNC.service.ADress;

import Garuda.com.DNC.Model.DAO.Address2DAO;
import Garuda.com.DNC.Model.DAO.VendorDAO;
import Garuda.com.DNC.Model.Vendor;
import Garuda.com.DNC.Model.Vendoradd;
import Garuda.com.DNC.api.exceptions.useralreadyexists;
import Garuda.com.DNC.api.models.AddressBody;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class vadressservice {


    private VendorDAO userdao;

    public vadressservice(VendorDAO userdao, Address2DAO add) {
        this.userdao = userdao;
        this.add = add;
    }

    private Address2DAO add;

    public Vendoradd register( String user,AddressBody body)throws useralreadyexists{

        Optional<Vendor> opuser= userdao.findByUserIgnoreCase(user);
        if(!opuser.isPresent()){
            throw new useralreadyexists();
        };
         Vendor ven= opuser.get();

        Vendoradd dr=new Vendoradd();
        dr.setMobile(body.getMobile());
        dr.setCity(body.getCity());
        dr.setAddress(body.getAdress());
        dr.setState(body.getState());
        dr.setPincode(body.getPincode());
        dr.setVendor(ven);
        dr.setCountry(body.getCountry());
        return add.save(dr);




    }




}
