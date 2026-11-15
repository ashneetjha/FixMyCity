package com.fixmycity;

import com.fixmycity.issue.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FixMyCityApplication {
  public static void main(String[] args) { SpringApplication.run(FixMyCityApplication.class, args); }

  @Bean CommandLineRunner seed(IssueRepository issues, IssueUpdateRepository updates) {
    return args -> {
      if (issues.count() > 0) return;
      List<Issue> sample = List.of(
        new Issue("Crater-sized pothole near the bus stop", "A deep pothole is causing two-wheelers to swerve into traffic.", IssueCategory.POTHOLE, 12.9718, 77.5944, "MG Road, Ward 71", "Aarav"),
        new Issue("Overflowing community bin", "Garbage has remained uncollected for three days beside the playground.", IssueCategory.GARBAGE, 12.9731, 77.5960, "Church Street, Ward 71", "Meera"),
        new Issue("Water leaking from broken pipeline", "Continuous leak is flooding the footpath and wasting water.", IssueCategory.WATER_LEAK, 12.9688, 77.5918, "Residency Road, Ward 70", "Kabir"),
        new Issue("Damaged streetlight pole", "The pole is tilted and the light has been off for a week.", IssueCategory.INFRASTRUCTURE, 12.9750, 77.5901, "Cubbon Road, Ward 71", "Nisha")
      );
      sample.forEach(issue -> { issue.setPriority(issue.getCategory() == IssueCategory.WATER_LEAK ? Priority.HIGH : Priority.MEDIUM); issues.save(issue); });
      issues.findAll().forEach(issue -> updates.save(new IssueUpdate(issue, IssueStatus.SUBMITTED, "Report received and assigned to the ward review queue.", "FixMyCity system")));
    };
  }
}
