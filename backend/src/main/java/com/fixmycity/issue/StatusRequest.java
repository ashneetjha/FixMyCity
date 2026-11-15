package com.fixmycity.issue;
import jakarta.validation.constraints.*;
public record StatusRequest(@NotNull IssueStatus status, @NotBlank @Size(max=600) String note, @NotBlank @Size(max=80) String author) {}
