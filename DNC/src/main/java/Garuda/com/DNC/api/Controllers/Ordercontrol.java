package Garuda.com.DNC.api.Controllers;


import Garuda.com.DNC.Model.Cart;
import Garuda.com.DNC.Model.DAO.*;
import Garuda.com.DNC.Model.Inventory;
import Garuda.com.DNC.Model.LocalUser;
import Garuda.com.DNC.api.exceptions.useralreadyexists;
import Garuda.com.DNC.api.models.category;
import Garuda.com.DNC.api.models.orderbase;
import Garuda.com.DNC.service.Products.orderprocess;
import Garuda.com.DNC.service.Products.productservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/con")
public class Ordercontrol {

Address2DAO add1;
AddressDAO add2;

    public Ordercontrol(Address2DAO add1, AddressDAO add2, InventoryDAO inv, orderDAO or, userDAO user, productservice pservice, orderprocess orderservice) {
        this.add1 = add1;
        this.add2 = add2;
        this.inv = inv;
        this.or = or;
        this.user = user;
        this.pservice = pservice;
        this.orderservice = orderservice;
    }

    InventoryDAO inv;

orderDAO or;
userDAO user;

    productservice pservice;
    orderprocess orderservice;
     @GetMapping("/catalogue")
    public List<Inventory> catalogue(){
         return pservice.inven();

     }

     @PostMapping("/order")
    public ResponseEntity orders(@AuthenticationPrincipal LocalUser user,@RequestBody orderbase base)throws RuntimeException{
         try {
            orderservice.post(user.getUser(),base);
             return ResponseEntity.ok().build();
         } catch (RuntimeException e) {
             return ResponseEntity.status(HttpStatus.CONFLICT).build();
         }


     }
     @PostMapping("/cart")
    public ResponseEntity cart(@AuthenticationPrincipal LocalUser user,@RequestBody orderbase base)throws RuntimeException{
        try {
            orderservice.cart(user.getUser(),base);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }
    @GetMapping("/cat")
    public List<Inventory> cat(@RequestBody category cat){
        return pservice.invent(cat);

    }
    @PostMapping("/bucket")
    public ResponseEntity bucket(@AuthenticationPrincipal LocalUser user, @RequestBody orderbase base){
        try {
            orderservice.bucket(user.getUser(),base);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

}
