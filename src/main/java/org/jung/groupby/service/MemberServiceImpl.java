package org.jung.groupby.service;

import lombok.RequiredArgsConstructor;
import org.jung.groupby.domain.Member;
import org.jung.groupby.dto.MemberDTO;
import org.jung.groupby.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public void registerMember(MemberDTO memberDTO) {
        memberRepository.save(modelMapper.map(memberDTO, Member.class));
    }

    @Override
    public boolean login(MemberDTO memberDTO) {
        Member member = memberRepository.findByUsername(memberDTO.getUsername()).orElseThrow();
        return member.getPassword().equals(memberDTO.getPassword());
    }

    @Override
    public MemberDTO getMember(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow();
        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        Member member = memberRepository.findByUsername(username).orElseThrow();
        member.changePassword(newPassword);
        memberRepository.save(member);
    }

    @Override
    public void deleteMember(String username) {
        memberRepository.deleteByUsername(username);
    }

}