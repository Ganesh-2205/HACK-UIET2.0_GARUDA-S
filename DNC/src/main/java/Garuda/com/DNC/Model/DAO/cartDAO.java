package Garuda.com.DNC.Model.DAO;

import Garuda.com.DNC.Model.Cart;
import Garuda.com.DNC.Model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface cartDAO extends ListCrudRepository<Cart,Long> {
    List<Cart> findByInventory_Carts_LocalUser(LocalUser localUser);

    List<Cart> findByLocalUser_Carts_LocalUser(LocalUser localUser);


}
