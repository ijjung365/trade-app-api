package org.jung.groupby.service;

import lombok.RequiredArgsConstructor;
import org.jung.groupby.domain.Hold;
import org.jung.groupby.domain.Member;
import org.jung.groupby.dto.HoldDTO;
import org.jung.groupby.repository.HoldRepository;
import org.jung.groupby.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HoldServiceImpl implements HoldService {
    private final HoldRepository holdRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<HoldDTO> getHolds(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow();
        List<Hold> holds = holdRepository.findByMember(member);
        return holds.stream()
                .map(hold -> modelMapper.map(hold, HoldDTO.class))
                .collect(Collectors.toList());
    }
}
