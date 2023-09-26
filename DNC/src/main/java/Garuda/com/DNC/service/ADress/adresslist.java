package Garuda.com.DNC.service.ADress;

import Garuda.com.DNC.Model.*;
import Garuda.com.DNC.Model.DAO.Address2DAO;
import Garuda.com.DNC.Model.DAO.AddressDAO;
import Garuda.com.DNC.Model.DAO.VendorDAO;
import Garuda.com.DNC.Model.DAO.userDAO;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class adresslist {


    public adresslist(VendorDAO vdao, AddressDAO adao, Address2DAO adao2,userDAO udao) {
        this.vdao = vdao;
        this.adao = adao;
        this.adao2 = adao2;
        this.udao =udao;
    }
    userDAO udao;

    VendorDAO vdao;
    AddressDAO adao;
    Address2DAO adao2;

    public List<Vendoradd> listing(String Data){
        Optional<Vendor> user=vdao.findByUserIgnoreCase(Data);

        return adao2.findByVendor_Address_Vendor(user.get());
    }
    public List<LocalAddress> listing2(String Data){
        Optional<LocalUser> vuser=udao.findByUserIgnoreCase(Data);

        return adao.findByLocalUser_LocalAddresses_LocalUser(vuser.get());
    }
}
