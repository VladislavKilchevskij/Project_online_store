package com.academy.project.model.repository;

import com.academy.project.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {


    @Transactional
    @Modifying
    @Query("update Address a " +
            "set a.country= :#{#address.country}, " +
            "a.region= :#{#address.region}, " +
            "a.district= :#{#address.district}, " +
            "a.city= :#{#address.city}, " +
            "a.street= :#{#address.street}, " +
            "a.house= :#{#address.house}, " +
            "a.building= :#{#address.building}, " +
            "a.flat= :#{#address.flat}, " +
            "a.postCode= :#{#address.postCode} " +
            "where a.id = :#{#address.id}")
    void update(@Param("address") Address address);
}
