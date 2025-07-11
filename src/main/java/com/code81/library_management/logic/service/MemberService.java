package com.code81.library_management.logic.service;

import com.code81.library_management.data.entity.Member;

import java.util.List;

public interface MemberService {
    Member createMember(Member member);
    Member updateMember(Long id, Member updatedMember);
    void deleteMember(Long id);
    Member getMemberById(Long id);
    List<Member> getAllMembers();
}
