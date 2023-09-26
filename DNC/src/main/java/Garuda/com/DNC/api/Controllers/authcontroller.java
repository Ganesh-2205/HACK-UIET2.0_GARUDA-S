package Garuda.com.DNC.api.Controllers;

import Garuda.com.DNC.Model.Cart;
import Garuda.com.DNC.Model.LocalAddress;
import Garuda.com.DNC.Model.LocalUser;

import Garuda.com.DNC.Model.order.Orders;
import Garuda.com.DNC.api.models.*;
import Garuda.com.DNC.api.exceptions.useralreadyexists;
import Garuda.com.DNC.service.ADress.adresslist;
import Garuda.com.DNC.service.ADress.uadressservice;
import Garuda.com.DNC.service.service;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/auth")
public class authcontroller {
    public authcontroller(adresslist listt,service uservice,uadressservice adservice) {
        this.uservice = uservice;
        this.adservice=adservice;
        this.listt=listt;
    }
    uadressservice adservice;
    adresslist listt;
    private service uservice;
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
 @GetMapping("/jwt")
    public LocalUser loggeduser(@AuthenticationPrincipal LocalUser user){
     return user;
 }
    @PostMapping("/regadd")
    public ResponseEntity regadd(@AuthenticationPrincipal LocalUser user, @RequestBody AddressBody adress){
        try {
            adservice.register(user.getUser(), adress);
            return ResponseEntity.ok().build();
        } catch (useralreadyexists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @GetMapping("/addresses")
    public List<LocalAddress> addresses(@AuthenticationPrincipal LocalUser user){
        return listt.listing2(user.getUser());
    }

    @GetMapping("/getord")
    public List<Orders> getord(@AuthenticationPrincipal LocalUser user){
     return adservice.getorders(user.getUser());
    }
    @GetMapping("/getcart")
    public List<Cart> getcart(@AuthenticationPrincipal LocalUser user){
        return adservice.getcart(user.getUser());
    }
}
