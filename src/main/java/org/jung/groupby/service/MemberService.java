package org.jung.groupby.service;

import org.jung.groupby.dto.MemberDTO;

public interface MemberService {
    public void registerMember(MemberDTO memberDTO);
    public boolean login(MemberDTO memberDTO);
    public MemberDTO getMember(String username);
    public void changePassword(String username, String newPassword);
    public void deleteMember(String username);

}
