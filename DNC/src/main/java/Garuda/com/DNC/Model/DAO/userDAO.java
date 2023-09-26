package Garuda.com.DNC.Model.DAO;

import Garuda.com.DNC.Model.LocalAddress;
import Garuda.com.DNC.Model.LocalUser;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
public interface userDAO extends ListCrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByLocalAddresses_LocalUser_LocalAddresses(LocalAddress localAddresses);
    Optional<LocalUser> findByEmailIgnoreCase(String email);

    Optional<LocalUser> findByUserIgnoreCase(String user);


}
