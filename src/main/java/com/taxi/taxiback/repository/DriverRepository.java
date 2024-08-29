package com.taxi.taxiback.repository;

import com.taxi.taxiback.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
}
