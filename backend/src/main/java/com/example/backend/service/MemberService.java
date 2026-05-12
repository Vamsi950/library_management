package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.model.Member;
import com.example.backend.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(Member member) {
        if (member.getActive() == null) {
            member.setActive(Boolean.TRUE);
        }
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member updateMember(Long id, Member member) {
        Member existingMember = memberRepository.findById(id).orElse(null);
        if (existingMember == null) {
            return null;
        }

        existingMember.setName(member.getName());
        existingMember.setEmail(member.getEmail());
        if (member.getActive() != null) {
            existingMember.setActive(member.getActive());
        }
        return memberRepository.save(existingMember);
    }

    public boolean deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            return false;
        }

        memberRepository.deleteById(id);
        return true;
    }
}