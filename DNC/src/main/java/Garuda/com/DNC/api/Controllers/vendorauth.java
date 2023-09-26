package Garuda.com.DNC.api.Controllers;

import Garuda.com.DNC.Model.DAO.VendorDAO;

import Garuda.com.DNC.Model.Inventory;
import Garuda.com.DNC.Model.Vendor;
import Garuda.com.DNC.Model.Vendoradd;
import Garuda.com.DNC.api.exceptions.useralreadyexists;
import Garuda.com.DNC.api.models.*;

import Garuda.com.DNC.service.ADress.adresslist;
import Garuda.com.DNC.service.ADress.vadressservice;
import Garuda.com.DNC.service.VendorService;
import Garuda.com.DNC.service.sellerservice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/vend")
public class vendorauth {

     VendorDAO userdao;
    private VendorService uservice;
    private adresslist listt;

    private vadressservice adservice;
    private sellerservice sservice;

    public vendorauth(VendorDAO userdao, VendorService uservice, adresslist listt, vadressservice adservice, sellerservice sservice) {
        this.userdao = userdao;
        this.uservice = uservice;
        this.listt = listt;
        this.adservice = adservice;
        this.sservice = sservice;
    }



    @PostMapping("/register")
    public ResponseEntity Registeruser(@Valid @RequestBody Registereduser user)  {
        try {
            uservice.Regista(user);
            return ResponseEntity.ok().build();
        } catch (useralreadyexists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<loginbody> loginuser(@Valid @RequestBody loginuser Data){
        String jwt= uservice.loginuser(Data);
        if(jwt==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            loginbody body=new loginbody();
            body.setJwt(jwt);
            return ResponseEntity.ok(body);
        }
    }
    @PostMapping("/regadd")
    public ResponseEntity regadd(@AuthenticationPrincipal Vendor user,@RequestBody AddressBody adress){
        try {
            adservice.register(user.getUser(),adress);
            return ResponseEntity.ok().build();
        } catch (useralreadyexists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PostMapping("/post")
    public ResponseEntity propost(@AuthenticationPrincipal Vendor user,@RequestBody inventory_base base){
        try {
            sservice.inventory(user.getUser(),base);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @GetMapping("/addresses")
    public List<Vendoradd> addresses(@AuthenticationPrincipal Vendor data){
        return listt.listing(data.getUser());
    }
    @GetMapping("/products")
    public List<Inventory> products(@AuthenticationPrincipal Vendor data){
        return sservice.getorders(data.getUser());
    }
    }





