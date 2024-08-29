package com.taxi.taxiback.repository;

import com.taxi.taxiback.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
