package com.example.backend.service;

import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Register Member
    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

    
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
}