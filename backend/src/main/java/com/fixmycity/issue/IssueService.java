package com.fixmycity.issue;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IssueService {
  private final IssueRepository issues; private final IssueUpdateRepository updates;
  public IssueService(IssueRepository issues, IssueUpdateRepository updates){this.issues=issues;this.updates=updates;}
  public List<Issue> list(IssueCategory category, IssueStatus status){ return issues.findAll().stream().filter(i->category==null||i.getCategory()==category).filter(i->status==null||i.getStatus()==status).sorted(Comparator.comparing(Issue::getCreatedAt).reversed()).toList(); }
  public Issue get(Long id){return issues.findById(id).orElseThrow(()->new NoSuchElementException("Issue not found"));}
  @Transactional public Map<String,Object> create(IssueRequest request){
    List<Issue> nearby = nearby(request.latitude(), request.longitude(), 0.25).stream().filter(i->i.getCategory()==request.category()).toList();
    Issue issue=new Issue(request.title(),request.description(),request.category(),request.latitude(),request.longitude(),request.address(),request.reporterName());
    Priority priority=priorityFor(request.category(), nearby.size()); issue.setPriority(priority); issue.setClusterKey(clusterKey(request.latitude(),request.longitude())); issues.save(issue);
    updates.save(new IssueUpdate(issue,IssueStatus.SUBMITTED,"Report received. Our duplicate and priority engine has reviewed the location.","FixMyCity system"));
    return Map.of("issue",issue,"possibleDuplicates",nearby,"duplicateDetected",!nearby.isEmpty(),"message",nearby.isEmpty()?"Report submitted successfully.":"Similar reports were found nearby. Your report has been added as supporting evidence.");
  }
  @Transactional public Issue changeStatus(Long id, StatusRequest request){Issue issue=get(id); issue.setStatus(request.status()); updates.save(new IssueUpdate(issue,request.status(),request.note(),request.author())); return issue;}
  public List<IssueUpdate> timeline(Long id){get(id);return updates.findByIssueIdOrderByCreatedAtDesc(id);}
  public List<Issue> nearby(double lat,double lon,double radiusKm){return issues.findAll().stream().filter(i->distance(lat,lon,i.getLatitude(),i.getLongitude())<=radiusKm).toList();}
  public Map<String,Object> dashboard(){List<Issue> all=issues.findAll(); Map<String,Long> byCategory=all.stream().collect(Collectors.groupingBy(i->i.getCategory().name(),Collectors.counting())); long open=all.stream().filter(i->i.getStatus()!=IssueStatus.RESOLVED&&i.getStatus()!=IssueStatus.REJECTED).count(); return Map.of("total",all.size(),"open",open,"resolved",all.stream().filter(i->i.getStatus()==IssueStatus.RESOLVED).count(),"critical",all.stream().filter(i->i.getPriority()==Priority.CRITICAL||i.getPriority()==Priority.HIGH).count(),"byCategory",byCategory);}
  public List<Map<String,Object>> clusters(){return issues.findAll().stream().collect(Collectors.groupingBy(Issue::getClusterKey)).entrySet().stream().filter(e->e.getValue().size()>0).map(e->Map.<String,Object>of("key",e.getKey(),"count",e.getValue().size(),"latitude",e.getValue().stream().mapToDouble(Issue::getLatitude).average().orElse(0),"longitude",e.getValue().stream().mapToDouble(Issue::getLongitude).average().orElse(0),"issues",e.getValue())).sorted((a,b)->Integer.compare((Integer)b.get("count"),(Integer)a.get("count"))).toList();}
  private Priority priorityFor(IssueCategory c,int duplicates){if(c==IssueCategory.WATER_LEAK&&duplicates>0)return Priority.CRITICAL; if(c==IssueCategory.WATER_LEAK||duplicates>=2)return Priority.HIGH; if(c==IssueCategory.POTHOLE||c==IssueCategory.INFRASTRUCTURE)return Priority.MEDIUM;return Priority.LOW;}
  private String clusterKey(double lat,double lon){return String.format(Locale.US,"%.2f,%.2f",lat,lon);}
  private double distance(double a,double b,double c,double d){double r=6371, x=Math.toRadians(c-a),y=Math.toRadians(d-b),h=Math.sin(x/2)*Math.sin(x/2)+Math.cos(Math.toRadians(a))*Math.cos(Math.toRadians(c))*Math.sin(y/2)*Math.sin(y/2);return 2*r*Math.atan2(Math.sqrt(h),Math.sqrt(1-h));}
}
