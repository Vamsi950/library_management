package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.entity.IssueRecord;
import com.example.backend.entity.Member;
import com.example.backend.repository.BookRepository;
import com.example.backend.repository.IssueRecordRepository;
import com.example.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Issue Book
    public String issueBook(Long bookId, Long memberId) {

        Book book = bookRepository.findById(bookId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);

        if (book == null) {
            return "Book not found";
        }

        if (member == null) {
            return "Member not found";
        }

        // Check availability
        if (!book.isAvailable()) {
            return "Book is not available";
        }

        // Check active issues count
        List<IssueRecord> activeIssues =
                issueRecordRepository.findByMemberAndReturnDateIsNull(member);

        if (activeIssues.size() >= 3) {
            return "Member cannot issue more than 3 books";
        }

        // Create issue record
        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setBook(book);
        issueRecord.setMember(member);
        issueRecord.setIssueDate(LocalDate.now());

        issueRecordRepository.save(issueRecord);

        // Mark book unavailable
        book.setAvailable(false);
        bookRepository.save(book);

        return "Book issued successfully";
    }

    // Return Book
    public String returnBook(Long issueId) {

        IssueRecord issueRecord =
                issueRecordRepository.findById(issueId).orElse(null);

        if (issueRecord == null) {
            return "Issue record not found";
        }

        // Already returned
        if (issueRecord.getReturnDate() != null) {
            return "Book already returned";
        }

        // Update return date
        issueRecord.setReturnDate(LocalDate.now());
        issueRecordRepository.save(issueRecord);

        // Make book available again
        Book book = issueRecord.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return "Book returned successfully";
    }
}