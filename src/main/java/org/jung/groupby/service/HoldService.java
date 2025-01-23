package org.jung.groupby.service;

import org.jung.groupby.dto.HoldDTO;

import java.util.List;

public interface HoldService {
    public List<HoldDTO> getHolds(String username);
}
