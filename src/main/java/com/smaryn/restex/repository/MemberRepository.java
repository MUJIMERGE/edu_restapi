package com.smaryn.restex.repository;

import com.smaryn.restex.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional 은 JDK8 부터 지원되는 null 처리 기법
    Optional<Member> findByName(String name);
    List<Member> findAll();
    void delete(Member member);
}
