package Garuda.com.DNC.Model.DAO;

import Garuda.com.DNC.Model.Inventory;
import Garuda.com.DNC.Model.Vendor;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryDAO extends ListCrudRepository<Inventory,Long> {
    List<Inventory> findByVendoradd_Inventories_CategoryIgnoreCase(String category);

    List<Inventory> findByVendor_Inventories_Vendor(Vendor vendor);

  Optional< Inventory> findByVendoradd_Inventories_Id(Long id);


}
