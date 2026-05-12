package com.example.backend.controller;

import com.example.backend.model.Member;
import com.example.backend.service.MemberService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	public ResponseEntity<Member> registerMember(@Valid @RequestBody Member member) {
		return ResponseEntity.status(HttpStatus.CREATED).body(memberService.registerMember(member));
	}

	@GetMapping
	public ResponseEntity<List<Member>> getAllMembers() {
		return ResponseEntity.ok(memberService.getAllMembers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
		Member member = memberService.getMemberById(id);
		return member == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(member);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable Long id, @Valid @RequestBody Member member) {
		Member updatedMember = memberService.updateMember(id, member);
		return updatedMember == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updatedMember);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
		return memberService.deleteMember(id)
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}
}
