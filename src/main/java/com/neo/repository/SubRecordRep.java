package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.bean.SubRecord;

public interface SubRecordRep extends JpaRepository<SubRecord, Long> {

}
