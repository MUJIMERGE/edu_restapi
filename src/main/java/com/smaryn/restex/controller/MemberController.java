package com.smaryn.restex.controller;

import com.smaryn.restex.domain.Member;
import com.smaryn.restex.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public HashMap<String, Object> createMember(@RequestBody Member requestMember) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long uid = memberService.join(requestMember);

        if (uid != -1) {
            hashMap.put("result", "ok");
            hashMap.put("uid", uid);
        } else {
            hashMap.put("result", "error");
        }

        return hashMap;
    }

    @GetMapping()
    public List<Member> getAllMember() {
        return memberService.findMembers();
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> getMember(@PathVariable Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Member> result = memberService.findOne(id);

        if (result.isPresent()) {
            hashMap.put("id", result.get().getId());
            hashMap.put("name", result.get().getName());
        } else {
            hashMap.put("result", "error");
        }

        return hashMap;
    }

    @DeleteMapping("/{id}")
    public HashMap<String, Object> deleteMember(@PathVariable Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Member> result = memberService.findOne(id);

        if (result.isPresent()) {
            memberService.delete(result.get());
            hashMap.put("id", result.get().getId());
            hashMap.put("name", result.get().getName());
        } else {
            hashMap.put("result", "error");
        }

        return hashMap;
    }
}
