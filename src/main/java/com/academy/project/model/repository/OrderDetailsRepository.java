package com.academy.project.model.repository;

import com.academy.project.model.entity.OrderDetails;
import com.academy.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

    OrderDetails getByUserAndOrderStatus(User user, String orderStatus);
    List<OrderDetails> getAllByUserAndOrderStatusIsNot(User user, String orderStatus);
    @Transactional
    @Modifying
    @Query("update OrderDetails o " +
            "set o.totalPrice= :#{#orderDetails.totalPrice} " +
            "where o.id= :#{#orderDetails.id}")
    void updateTotalPrice(@Param("orderDetails") OrderDetails orderDetails);


    @Transactional
    @Modifying
    @Query("update OrderDetails o " +
            "set o.orderStatus= :#{#orderDetails.orderStatus}, " +
            "o.orderDate= :#{#orderDetails.orderDate}, " +
            "o.deliveryDate= :#{#orderDetails.deliveryDate}, " +
            "o.address= :#{#orderDetails.address} " +
            "where o.id= :#{#orderDetails.id}")
    void updateOrderStatus(@Param("orderDetails") OrderDetails orderDetails);
}
