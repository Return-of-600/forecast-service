package com.stock.forecast_service.domain.member.repository;

import com.stock.forecast_service.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {



}
