package com.ayush.databaseConn.Repository;

import com.ayush.databaseConn.Model.Address;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    @Query(value = "SELECT * FROM address u WHERE u.user_id = ?1",nativeQuery = true)
    List<Address> findByUserId(int userId);
    @Modifying
    @Query(value = "UPDATE product_variant e SET e.is_deleted = true WHERE e.user_id = ?1",nativeQuery = true)
    void setIsDeletedTrue(int userId);

    @Modifying
    @Query(value = "UPDATE address e SET e.is_deleted = false WHERE e.address_id = ?1",nativeQuery = true)
    void setIsDeletedFalse(int address_id);

    @Modifying
    @Query(value = "DELETE FROM address e WHERE e.is_deleted = true",nativeQuery = true)
    void deleteUnused();
}
