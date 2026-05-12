package com.example.backend.model;


public class IssuesRecord {
   
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "issue_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"book", "member"})
@EqualsAndHashCode(exclude = {"book", "member"})
public class IssuesRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long issueId;

	private LocalDate issueDate;

	private LocalDate returnDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

}

