package com.fixmycity.issue;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface IssueRepository extends JpaRepository<Issue, Long> { List<Issue> findByCategory(IssueCategory category); List<Issue> findByStatus(IssueStatus status); }
