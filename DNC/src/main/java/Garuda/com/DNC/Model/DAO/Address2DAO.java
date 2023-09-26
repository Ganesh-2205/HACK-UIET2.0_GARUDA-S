package Garuda.com.DNC.Model.DAO;

import Garuda.com.DNC.Model.Vendor;
import Garuda.com.DNC.Model.Vendoradd;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface Address2DAO  extends ListCrudRepository<Vendoradd,Long> {
    List<Vendoradd> findByVendor_Address_Vendor(Vendor vendor);


    Optional<Vendoradd> findById(Long aLong);
}
