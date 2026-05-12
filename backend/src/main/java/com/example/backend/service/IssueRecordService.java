package com.example.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.model.Book;
import com.example.backend.model.IssuesRecord;
import com.example.backend.model.Member;
import com.example.backend.repository.BookRepository;
import com.example.backend.repository.IssuesRecordRepository;
import com.example.backend.repository.MemberRepository;

@Service
public class IssueRecordService {

    private final IssuesRecordRepository issueRecordRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public IssueRecordService(
            IssuesRecordRepository issueRecordRepository,
            BookRepository bookRepository,
            MemberRepository memberRepository) {
        this.issueRecordRepository = issueRecordRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public String issueBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);

        if(book == null) {
            return "Book not found";
        }

        if (member == null) {
            return "Member not found";
        }

        if (Boolean.FALSE.equals(book.getAvailable())) {
            return "Book is not available";
        }

        List<IssuesRecord> activeIssues = issueRecordRepository.findByMemberAndReturnDateIsNull(member);
        if (activeIssues.size() >= 3) {
            return "Member cannot issue more than 3 books";
        }

        IssuesRecord issueRecord = new IssuesRecord();
        issueRecord.setBook(book);
        issueRecord.setMember(member);
        issueRecord.setIssueDate(LocalDate.now());
        issueRecordRepository.save(issueRecord);

        book.setAvailable(Boolean.FALSE);
        bookRepository.save(book);

        return "Book issued successfully";
    }

    public String returnBook(Long issueId) {
        IssuesRecord issueRecord = issueRecordRepository.findById(issueId).orElse(null);

        if (issueRecord == null) {
            return "Issue record not found";
        }

        if (issueRecord.getReturnDate() != null) {
            return "Book already returned";
        }

        issueRecord.setReturnDate(LocalDate.now());
        issueRecordRepository.save(issueRecord);

        Book book = issueRecord.getBook();
        book.setAvailable(Boolean.TRUE);
        bookRepository.save(book);

        return "Book returned successfully";
    }

    public List<IssuesRecord> getAllIssueRecords() {
        return issueRecordRepository.findAll();
    }

    public IssuesRecord getIssueRecordById(Long id) {
        return issueRecordRepository.findById(id).orElse(null);
    }

    public boolean deleteIssueRecord(Long id) {
        if (!issueRecordRepository.existsById(id)) {
            return false;
        }

        issueRecordRepository.deleteById(id);
        return true;
    }

    public List<IssuesRecord> getIssuesByMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return List.of();
        }

        return issueRecordRepository.findByMember(member);
    }
}