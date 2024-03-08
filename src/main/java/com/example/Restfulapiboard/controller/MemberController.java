package com.example.Restfulapiboard.controller;

import com.example.Restfulapiboard.dto.request.MemberRequest;
import com.example.Restfulapiboard.dto.response.MemberResponse;
import com.example.Restfulapiboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long memberId) {
        MemberResponse memberResponse = MemberResponse.from(memberService.findById(memberId));
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberRequest memberRequest) {
        Long memberId = memberService.createMember(memberRequest.toDto());
        return ResponseEntity.created(URI.create("/api/members/" + memberId)).build();
    }
}
