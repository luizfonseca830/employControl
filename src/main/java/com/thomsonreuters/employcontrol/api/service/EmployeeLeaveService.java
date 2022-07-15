package com.thomsonreuters.employcontrol.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.dto.EmployeeLeaveDTO;
import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;
import com.thomsonreuters.employcontrol.api.repository.EmployeeLeaveRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeLeaveService {

  public EmployeeLeaveService(
      EmployeeLeaveRepository employeeLeaveRepository, ModelMapper modelMapper) {
    this.employeeLeaveRepository = employeeLeaveRepository;
    this.modelMapper = modelMapper;
  }

  private final EmployeeLeaveRepository employeeLeaveRepository;

  private final ModelMapper modelMapper;

  private EmployeeLeaveDTO convertToEmployee(EmployeeLeave employeeLeave) {
    return modelMapper.map(employeeLeave, EmployeeLeaveDTO.class);
  }

  private EmployeeLeave convertToEmployeeLeaveDTO(EmployeeLeaveDTO employeeLeaveDTO) {
    return modelMapper.map(employeeLeaveDTO, EmployeeLeave.class);
  }

  public List<EmployeeLeaveDTO> listAll(){
    return employeeLeaveRepository.findAll().stream().map(this::convertToEmployee).collect(Collectors.toList());
  }

  public EmployeeLeave create(EmployeeLeaveDTO employeeLeaveDTO){
    EmployeeLeave employeeLeave = convertToEmployeeLeaveDTO(employeeLeaveDTO);
    return employeeLeaveRepository.save(employeeLeave);
  }
}
