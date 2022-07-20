package com.thomsonreuters.employcontrol.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.dto.EmployeeLeaveDTO;
import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;
import com.thomsonreuters.employcontrol.api.repository.EmployeeLeaveRepository;
import com.thomsonreuters.employcontrol.api.repository.filter.EmployeeLeaveFilter;
import com.thomsonreuters.employcontrol.api.service.exception.EmployeeLeaveTypeLeaveException;
import java.util.List;

@Service
public class EmployeeLeaveService {

  public EmployeeLeaveService(
      EmployeeLeaveRepository employeeLeaveRepository, ModelMapper modelMapper) {
    this.employeeLeaveRepository = employeeLeaveRepository;
    this.modelMapper = modelMapper;
  }

  private final EmployeeLeaveRepository employeeLeaveRepository;

  private final ModelMapper modelMapper;

  private EmployeeLeave convertToEmployeeLeaveDTO(EmployeeLeaveDTO employeeLeaveDTO) {
    return modelMapper.map(employeeLeaveDTO, EmployeeLeave.class);
  }

  public List<EmployeeLeave> research(EmployeeLeaveFilter employeeLeaveFilter) {
   return employeeLeaveRepository.filter(employeeLeaveFilter);
  }


  public EmployeeLeave create(EmployeeLeaveDTO employeeLeaveDTO) {
    EmployeeLeave employeeLeave = convertToEmployeeLeaveDTO(employeeLeaveDTO);
    if (employeeLeave.getLeaveType().equals("MATERNITY_LEAVE")) {
      employeeLeave.setNumberDays(180);
      employeeLeave.setReturnDate(employeeLeave.getLeaveDate().plusDays(180));
    }
    if (employeeLeave.getLeaveType().equals("PATERNITY_LEAVE")) {
      employeeLeave.setNumberDays(20);
      employeeLeave.setReturnDate(employeeLeave.getLeaveDate().plusDays(20));
    }
    return employeeLeaveRepository.save(employeeLeave);
  }

  public EmployeeLeave edit(EmployeeLeaveDTO employeeLeaveDTO, Long id) {
    EmployeeLeave employeeLeaveSalvo =
        employeeLeaveRepository
            .findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
    if (employeeLeaveSalvo.getLeaveType().equals("CONTRIBUTORS_DEATH")
        || employeeLeaveSalvo.getLeaveType().equals("TERMINATION")) {
      throw new EmployeeLeaveTypeLeaveException();
    }
    BeanUtils.copyProperties(employeeLeaveDTO, employeeLeaveSalvo, "id");
    return employeeLeaveRepository.save(employeeLeaveSalvo);
  }
}
