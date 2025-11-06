package com.gokulrajan.server.controller;

import com.gokulrajan.server.model.SpringtutorialDTO;
import com.gokulrajan.server.service.SpringTutorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spring-rest-demo")
public class StController {

  private final SpringTutorialService stService;

  public StController(SpringTutorialService stService) {
    this.stService = stService;
  }

  @GetMapping
  public List<SpringtutorialDTO> getAllDatas() {
    return stService.getAllDatas();
  }

  @GetMapping("/{id}")
  public ResponseEntity<SpringtutorialDTO> getDataById(@PathVariable Long id) {
    Optional<SpringtutorialDTO> dataById = stService.getDataById(id);
    return dataById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public SpringtutorialDTO createData(@RequestBody SpringtutorialDTO springtutorialDTO) {
    return stService.saveData(springtutorialDTO);
  }

  // @PutMapping("/{id}")
  @PatchMapping("/{id}")
  public ResponseEntity<SpringtutorialDTO> updateData(@PathVariable Long id,
      @RequestBody SpringtutorialDTO springtutorialDTO) {
    try {
      SpringtutorialDTO updateData = stService.updateData(id, springtutorialDTO);
      return ResponseEntity.ok(updateData);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteData(@PathVariable Long id) {
    stService.deleteData(id);
    return ResponseEntity.noContent().build();
  }

}
