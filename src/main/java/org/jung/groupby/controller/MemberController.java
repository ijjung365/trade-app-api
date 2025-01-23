package org.jung.groupby.controller;

import lombok.RequiredArgsConstructor;
import org.jung.groupby.domain.Member;
import org.jung.groupby.dto.MemberDTO;
import org.jung.groupby.repository.MemberRepository;
import org.jung.groupby.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody MemberDTO memberDTO) {
        memberService.registerMember(memberDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody MemberDTO memberDTO) {
        memberService.login(memberDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable String username) {
        MemberDTO memberDTO = memberService.getMember(username);
        return ResponseEntity.ok().body(memberDTO);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody String username, @RequestBody String password) {
        memberService.changePassword(username, password);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        memberService.deleteMember(username);
        return ResponseEntity.ok().build();
    }
}
