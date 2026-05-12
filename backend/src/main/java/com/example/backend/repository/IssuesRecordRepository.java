package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.IssuesRecord;
import com.example.backend.model.Member;

public interface IssuesRecordRepository extends JpaRepository<IssuesRecord, Long> {

    List<IssuesRecord> findByMemberAndReturnDateIsNull(Member member);
    List<IssuesRecord> findByMember(Member member);
}