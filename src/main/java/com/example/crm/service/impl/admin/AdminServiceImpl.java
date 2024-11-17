package com.example.crm.service.impl.admin;

import com.example.crm.entity.users.Member;
import com.example.crm.repository.MemberRepository;
import com.example.crm.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final MemberRepository memberRepository;

    @Override
    public List<Member> showAllMember() {
        return memberRepository.findAll();
    }
}
