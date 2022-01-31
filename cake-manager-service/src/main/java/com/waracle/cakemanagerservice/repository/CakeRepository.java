package com.waracle.cakemanagerservice.repository;

import com.waracle.cakemanagerservice.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<Cake, Long> {

}
