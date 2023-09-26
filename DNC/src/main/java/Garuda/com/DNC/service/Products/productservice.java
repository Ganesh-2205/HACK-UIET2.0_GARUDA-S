package Garuda.com.DNC.service.Products;

import Garuda.com.DNC.Model.DAO.InventoryDAO;
import Garuda.com.DNC.Model.Inventory;
import Garuda.com.DNC.api.models.category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productservice {
    public productservice(InventoryDAO inv) {
        this.inv = inv;
    }

    InventoryDAO inv;

    public List<Inventory> inven(){
        return inv.findAll();
    }
    public List<Inventory> invent(category cat){
        return inv.findByVendoradd_Inventories_CategoryIgnoreCase(cat.getCat());
    }

}

