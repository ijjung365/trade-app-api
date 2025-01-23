package org.jung.groupby.controller;

import lombok.RequiredArgsConstructor;
import org.jung.groupby.domain.Hold;
import org.jung.groupby.dto.HoldDTO;
import org.jung.groupby.service.HoldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hold")
@RequiredArgsConstructor
public class HoldController {
    private final HoldService holdService;

    @GetMapping("/{username}")
    public ResponseEntity<List<HoldDTO>> getHold(@PathVariable String username) {
        List<HoldDTO> holdDTOS = holdService.getHolds(username);
        return ResponseEntity.ok(holdDTOS);
    }
}
