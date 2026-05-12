package com.example.backend.controller;

import com.example.backend.model.IssuesRecord;
import com.example.backend.service.IssueRecordService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssuesRecordController {

	private final IssueRecordService issueRecordService;

	public IssuesRecordController(IssueRecordService issueRecordService) {
		this.issueRecordService = issueRecordService;
	}

	@PostMapping("/issue/{bookId}/{memberId}")
	public ResponseEntity<String> issueBook(@PathVariable Long bookId, @PathVariable Long memberId) {
		return ResponseEntity.ok(issueRecordService.issueBook(bookId, memberId));
	}

	@PostMapping("/return/{issueId}")
	public ResponseEntity<String> returnBook(@PathVariable Long issueId) {
		return ResponseEntity.ok(issueRecordService.returnBook(issueId));
	}

	@PutMapping("/return/{issueId}")
	public ResponseEntity<String> returnBookPut(@PathVariable Long issueId) {
		return ResponseEntity.ok(issueRecordService.returnBook(issueId));
	}

	@GetMapping
	public ResponseEntity<List<IssuesRecord>> getAllIssueRecords() {
		return ResponseEntity.ok(issueRecordService.getAllIssueRecords());
	}

	@GetMapping("/{id}")
	public ResponseEntity<IssuesRecord> getIssueRecordById(@PathVariable Long id) {
		IssuesRecord issueRecord = issueRecordService.getIssueRecordById(id);
		return issueRecord == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(issueRecord);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteIssueRecord(@PathVariable Long id) {
		return issueRecordService.deleteIssueRecord(id)
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}

	@GetMapping("/member/{memberId}")
	public ResponseEntity<List<IssuesRecord>> getIssuesByMember(@PathVariable Long memberId) {
		return ResponseEntity.ok(issueRecordService.getIssuesByMember(memberId));
	}
}
