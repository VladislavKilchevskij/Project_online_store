package com.academy.project.model.entity;

import com.academy.project.model.enums.BlockStatusEnum;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class BlockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Enumerated(EnumType.STRING)
    private BlockStatusEnum blockStatus;
    @Column
    private Timestamp blockDate;
    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private User userAdministrator;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User userCustomer;

}
