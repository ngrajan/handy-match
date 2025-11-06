package com.gokulrajan.server.service;

import com.gokulrajan.server.model.Springtutorial;
import com.gokulrajan.server.model.SpringtutorialDTO;
import com.gokulrajan.server.repository.SpringtutorailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StServiceImplementation implements SpringTutorialService {

  private final SpringtutorailRepository springtutorialRepository;

  // ✅ Constructor (remove extra 'class' keyword and fix naming)
  public StServiceImplementation(SpringtutorailRepository springtutorialRepository) {
    this.springtutorialRepository = springtutorialRepository;
  }

  @Override
  public List<SpringtutorialDTO> getAllDatas() {
    return springtutorialRepository.findAll()
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  @SuppressWarnings("null")
  @Override
  public Optional<SpringtutorialDTO> getDataById(Long id) {
    return springtutorialRepository.findById(id)
        .map(this::convertToDTO);
  }

  @Override
  public SpringtutorialDTO saveData(SpringtutorialDTO springtutorialDTO) {
    Springtutorial springtutorial = convertToEntity(springtutorialDTO);
    @SuppressWarnings("null")
    Springtutorial savedSpringtutorial = springtutorialRepository.save(springtutorial);
    return convertToDTO(savedSpringtutorial);
  }

  @Override
  public SpringtutorialDTO updateData(Long id, SpringtutorialDTO springtutorialDTO) {
    @SuppressWarnings("null")
    Springtutorial springtutorial = springtutorialRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Springtutorial not found with id " + id));

    springtutorial.setName(springtutorialDTO.name());
    springtutorial.setDescription(springtutorialDTO.description());
    springtutorial.setPrice(springtutorialDTO.price());

    Springtutorial updatedSpringtutorial = springtutorialRepository.save(springtutorial);
    return convertToDTO(updatedSpringtutorial);
  }

  @SuppressWarnings("null")
  @Override
  public void deleteData(Long id) {
    springtutorialRepository.deleteById(id);
  }

  // ✅ Convert Entity → DTO
  private SpringtutorialDTO convertToDTO(Springtutorial springtutorial) {
    return new SpringtutorialDTO(
        springtutorial.getId(),
        springtutorial.getName(),
        springtutorial.getDescription(),
        springtutorial.getPrice());
  }

  // ✅ Convert DTO → Entity
  private Springtutorial convertToEntity(SpringtutorialDTO springtutorialDTO) {
    Springtutorial springtutorial = new Springtutorial();
    springtutorial.setName(springtutorialDTO.name());
    springtutorial.setDescription(springtutorialDTO.description());
    springtutorial.setPrice(springtutorialDTO.price());
    return springtutorial;
  }
}
