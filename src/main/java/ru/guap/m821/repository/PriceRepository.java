package ru.guap.m821.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guap.m821.model.dao.Price;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
}
