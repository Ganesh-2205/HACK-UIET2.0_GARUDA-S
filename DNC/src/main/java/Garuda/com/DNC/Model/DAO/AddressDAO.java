package Garuda.com.DNC.Model.DAO;

import Garuda.com.DNC.Model.LocalAddress;
import Garuda.com.DNC.Model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressDAO extends ListCrudRepository<LocalAddress,Long> {
    Optional<LocalAddress> findByLocalUser_LocalAddresses_Id(Long id);
    List<LocalAddress> findByLocalUser_LocalAddresses_LocalUser(LocalUser localUser);
}
