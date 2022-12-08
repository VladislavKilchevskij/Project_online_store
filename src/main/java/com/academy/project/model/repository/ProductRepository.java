package com.academy.project.model.repository;

import com.academy.project.model.entity.Category;
import com.academy.project.model.entity.Manufacturer;
import com.academy.project.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAllByCategoryAndManufacturer(Category category, Manufacturer manufacturer, Pageable pageable);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
    Page<Product> findAllByManufacturer(Manufacturer manufacturer, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Product p " +
            "set p.productName= :productName, " +
            "p.modelName= :modelName, " +
            "p.interfaceName= :interfaceName, " +
            "p.frequency= :frequency, " +
            "p.memorySize= :memorySize, " +
            "p.memoryType= :memoryType, " +
            "p.memorySlotNumber= :memorySlotNumber, " +
            "p.coreNumber= :coreNumber, " +
            "p.maxThreadNumber= :maxThreadNumber, " +
            "p.timingNymber= :timingNymber, " +
            "p.formFactor= :formFactor, " +
            "p.versionPciExpress= :versionPciExpress, " +
            "p.price= :price " +
            "where p.id = :id")
    void update(@Param("id") Integer id,
                @Param("productName") String productName,
                @Param("modelName") String modelName,
                @Param("interfaceName") String interfaceName,
                @Param("frequency") Integer frequency,
                @Param("memorySize") Integer memorySize,
                @Param("memoryType") String memoryType,
                @Param("memorySlotNumber") Integer memorySlotNumber,
                @Param("coreNumber") Integer coreNumber,
                @Param("maxThreadNumber") Integer maxThreadNumber,
                @Param("timingNymber") String timingNymber,
                @Param("formFactor") String formFactor,
                @Param("versionPciExpress") Integer versionPciExpress,
                @Param("price") BigDecimal price
    );
}
