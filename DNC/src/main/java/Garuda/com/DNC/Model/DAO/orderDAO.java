package Garuda.com.DNC.Model.DAO;

import Garuda.com.DNC.Model.LocalAddress;
import Garuda.com.DNC.Model.LocalUser;
import Garuda.com.DNC.Model.order.Orders;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface orderDAO extends ListCrudRepository <Orders,Long> {
    List<Orders> findByLocalAddress_Orderses_LocalAddress(LocalAddress localAddress);

    List<Orders> findByLocalUser_Orderses_LocalUser(LocalUser localUser);


}
