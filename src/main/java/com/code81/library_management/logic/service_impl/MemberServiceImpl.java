package com.code81.library_management.logic.service_impl;


import com.code81.library_management.data.entity.Member;
import com.code81.library_management.data.repository.MemberRepository;
import com.code81.library_management.logic.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        return memberRepository.save(member);
    }


    @Override
    public Member updateMember(Long id, Member updatedMember) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Check if email is used by another member
        if (memberRepository.existsByEmailAndIdNot(updatedMember.getEmail(), id)) {
            throw new RuntimeException("Email already in use by another member");
        }

        existing.setFullName(updatedMember.getFullName());
        existing.setEmail(updatedMember.getEmail());
        existing.setPhone(updatedMember.getPhone());
        existing.setAddress(updatedMember.getAddress());

        return memberRepository.save(existing);
    }


    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
