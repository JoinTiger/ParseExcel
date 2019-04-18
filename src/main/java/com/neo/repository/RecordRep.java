package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.bean.Record;

public interface RecordRep extends JpaRepository<Record, Long> {

}
