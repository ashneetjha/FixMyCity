package com.fixmycity.issue;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api") @CrossOrigin(origins="${CORS_ORIGINS:*}") public class IssueController {
  private final IssueService service; public IssueController(IssueService service){this.service=service;}
  @GetMapping("/issues") List<Issue> list(@RequestParam(required=false) IssueCategory category,@RequestParam(required=false) IssueStatus status){return service.list(category,status);}
  @PostMapping("/issues") ResponseEntity<Map<String,Object>> create(@Valid @RequestBody IssueRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));}
  @GetMapping("/issues/{id}") Map<String,Object> detail(@PathVariable Long id){return Map.of("issue",service.get(id),"timeline",service.timeline(id));}
  @PatchMapping("/issues/{id}/status") Issue status(@PathVariable Long id,@Valid @RequestBody StatusRequest request){return service.changeStatus(id,request);}
  @GetMapping("/issues/nearby") List<Issue> nearby(@RequestParam double lat,@RequestParam double lon,@RequestParam(defaultValue="0.3") double radiusKm){return service.nearby(lat,lon,radiusKm);}
  @GetMapping("/dashboard") Map<String,Object> dashboard(){return service.dashboard();}
  @GetMapping("/clusters") List<Map<String,Object>> clusters(){return service.clusters();}
  @ExceptionHandler(NoSuchElementException.class) ResponseEntity<Map<String,String>> missing(NoSuchElementException e){return ResponseEntity.status(404).body(Map.of("message",e.getMessage()));}
}
