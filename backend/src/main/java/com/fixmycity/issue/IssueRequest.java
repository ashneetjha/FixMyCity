package com.fixmycity.issue;
import jakarta.validation.constraints.*;
public record IssueRequest(@NotBlank @Size(max=120) String title, @NotBlank @Size(max=1000) String description, @NotNull IssueCategory category, @NotNull @DecimalMin("-90.0") @DecimalMax("90.0") Double latitude, @NotNull @DecimalMin("-180.0") @DecimalMax("180.0") Double longitude, @NotBlank @Size(max=180) String address, @NotBlank @Size(max=80) String reporterName) {}
