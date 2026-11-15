package com.fixmycity.issue;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface IssueUpdateRepository extends JpaRepository<IssueUpdate, Long> { List<IssueUpdate> findByIssueIdOrderByCreatedAtDesc(Long issueId); }
