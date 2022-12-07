package com.academy.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String orderStatus;
    @Column
    private BigDecimal totalPrice;
    @Column
    private Date orderDate;
    @Column
    private Date deliveryDate;
    @OneToMany(mappedBy = "orderDetails", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Cart> carts;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    private Address address;

}
