package com.mycompany.doctorservice.service;

import com.mycompany.doctorservice.dto.VoivodeshipDto;
import com.mycompany.doctorservice.respository.VoivodeshipRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoivodeshipService {

    private final VoivodeshipRepository voivodeshipRepository;
    private final ModelMapper modelMapper;

    public List<VoivodeshipDto> getAllVoivodeships() {
        var voivodeshipDtoListType = new TypeToken<List<VoivodeshipDto>>() {}.getType();
        return modelMapper.map(voivodeshipRepository.findAll(), voivodeshipDtoListType);
    }
}
