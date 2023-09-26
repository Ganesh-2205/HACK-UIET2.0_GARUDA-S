package Garuda.com.DNC.service;

import Garuda.com.DNC.Model.DAO.VendorDAO;
import Garuda.com.DNC.Model.LocalUser;
import Garuda.com.DNC.Model.Vendor;
import Garuda.com.DNC.api.exceptions.useralreadyexists;
import Garuda.com.DNC.api.models.Registereduser;
import Garuda.com.DNC.api.models.loginuser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService {
    private VendorDAO userdao;

    public VendorService(VendorDAO userdao, JWTservice jwTservice, EncryptionService encryptionService) {
        this.userdao = userdao;
        this.jwTservice = jwTservice;
        this.encryptionService = encryptionService;
    }

    private JWTservice jwTservice;


    EncryptionService encryptionService;




    public Vendor Regista(Registereduser Data)throws useralreadyexists {
        if(userdao.findByEmailIgnoreCase(Data.getEmail()).isPresent()||(userdao.findByUserIgnoreCase(Data.getUser()).isPresent())){
            throw new useralreadyexists();
        };
        Vendor user=new Vendor();

        user.setEmail(Data.getEmail());
        user.setUser(Data.getUser());
        user.setMobile( Data.getMobile());
        user.setStatus("");

        user.setPassword(encryptionService.encryptpswrd(Data.getPassword()));
        return userdao.save(user);
    }
    public String loginuser(loginuser user){
        Optional<Vendor> opuser=userdao.findByUserIgnoreCase(user.getUser());
        if(opuser.isPresent()){
            Vendor Data= opuser.get();
            if(encryptionService.verifypswrd(user.getPassword(),Data.getPassword()))
                return jwTservice.jwtgenerator2(Data);
        }
        return null;
    }}


