package Garuda.com.DNC.Model.DAO;

import Garuda.com.DNC.Model.Vendor;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface VendorDAO extends ListCrudRepository<Vendor,Long> {
    Optional<Vendor> findByUserIgnoreCase(String user);



    Optional<Vendor> findByEmailIgnoreCase(String email);

}
