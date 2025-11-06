package com.gokulrajan.server.service;

import com.gokulrajan.server.model.SpringtutorialDTO;

import java.util.List;
import java.util.Optional;

public interface SpringTutorialService {

    List<SpringtutorialDTO> getAllDatas();

    Optional<SpringtutorialDTO> getDataById(Long id);

    SpringtutorialDTO saveData(SpringtutorialDTO springtutorialDTO);

    SpringtutorialDTO updateData(Long id, SpringtutorialDTO springtutorialDTO);

    void deleteData(Long id);

}
