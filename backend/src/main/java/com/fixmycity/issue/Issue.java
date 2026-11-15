package com.fixmycity.issue;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Issue {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, length=120) private String title;
  @Column(nullable=false, length=1000) private String description;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private IssueCategory category;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private IssueStatus status = IssueStatus.SUBMITTED;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private Priority priority = Priority.MEDIUM;
  @Column(nullable=false) private Double latitude;
  @Column(nullable=false) private Double longitude;
  @Column(nullable=false) private String address;
  @Column(nullable=false) private String reporterName;
  private String clusterKey;
  private Instant createdAt = Instant.now();
  private Instant updatedAt = Instant.now();
  protected Issue() {}
  public Issue(String title, String description, IssueCategory category, Double latitude, Double longitude, String address, String reporterName) { this.title=title; this.description=description; this.category=category; this.latitude=latitude; this.longitude=longitude; this.address=address; this.reporterName=reporterName; }
  @PreUpdate void touch(){ updatedAt=Instant.now(); }
  public Long getId(){return id;} public String getTitle(){return title;} public String getDescription(){return description;} public IssueCategory getCategory(){return category;} public IssueStatus getStatus(){return status;} public Priority getPriority(){return priority;} public Double getLatitude(){return latitude;} public Double getLongitude(){return longitude;} public String getAddress(){return address;} public String getReporterName(){return reporterName;} public String getClusterKey(){return clusterKey;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
  public void setStatus(IssueStatus status){this.status=status;} public void setPriority(Priority priority){this.priority=priority;} public void setClusterKey(String key){this.clusterKey=key;}
}
