package unit.com.softserve.academy.tmw.service;

import com.softserve.academy.tmw.dao.impl.TaskDao;
import com.softserve.academy.tmw.dto.TaskDTO;
import com.softserve.academy.tmw.entity.Task;
import com.softserve.academy.tmw.service.api.TaskServiceInterface;
import com.softserve.academy.tmw.service.impl.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskServiceInterface taskService = new TaskService();

    @Mock
    private TaskDao taskDao;

    private static Validator validator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUserMockCreation() {
        assertNotNull(taskService);
        assertNotNull(taskDao);
    }

    @Test
    public void shouldBeNotBlankNameOfTaskAndStartDateAndEndDateAndEstimateTime() {
        int countError = 4;
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("");
        taskDTO.setStartDate("");
        taskDTO.setEndDate("");
        taskDTO.setEstimateTime("");

        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(taskDTO);

        assertThat(violations.size() == countError);

        violations.forEach(error->{
            if(error.getPropertyPath().toString().equals("name")) {
                assertThat(error.getPropertyPath().toString()).isEqualTo("name");
                assertThat(error.getInvalidValue().toString()).isEqualTo("");
            }
            if(error.getPropertyPath().toString().equals("startDate")) {
                assertThat(error.getPropertyPath().toString()).isEqualTo("startDate");
                assertThat(error.getInvalidValue().toString()).isEqualTo("");
            }
            if(error.getPropertyPath().toString().equals("endDate")) {
                assertThat(error.getPropertyPath().toString()).isEqualTo("endDate");
                assertThat(error.getInvalidValue().toString()).isEqualTo("");
            }
            if(error.getPropertyPath().toString().equals("estimateTime")) {
                assertThat(error.getPropertyPath().toString()).isEqualTo("estimateTime");
                assertThat(error.getInvalidValue().toString()).isEqualTo("");
            }

        });
    }
//
//    @Test
//    public void shouldBeNotNullNameOfTaskAndStartDateAndEndDateAndEstimateTime() {
//        int countError = 4;
//        TaskDTO taskDTO = new TaskDTO();
//        taskDTO.setName(null);
//        taskDTO.setStartDate(null);
//        taskDTO.setEndDate(null);
//        taskDTO.setEstimateTime(null);
//
//        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(taskDTO);
//
//        assertThat(violations.size() == countError);
//
//        violations.forEach(error->{
//            if(error.getPropertyPath().toString().equals("name")) {
//                assertThat(error.getPropertyPath().toString()).isEqualTo("name");
//                assertThat(error.getInvalidValue()).isEqualTo(null);
//            }
//            if(error.getPropertyPath().toString().equals("startDate")) {
//                assertThat(error.getPropertyPath().toString()).isEqualTo("startDate");
//                assertThat(error.getInvalidValue()).isEqualTo(null);
//            }
//            if(error.getPropertyPath().toString().equals("endDate")) {
//                assertThat(error.getPropertyPath().toString()).isEqualTo("endDate");
//                assertThat(error.getInvalidValue()).isEqualTo(null);
//            }
//            if(error.getPropertyPath().toString().equals("estimateTime")) {
//                assertThat(error.getPropertyPath().toString()).isEqualTo("estimateTime");
//                assertThat(error.getInvalidValue()).isEqualTo(null);
//            }
//
//        });
//    }
//
//    @Test
//    public void shouldBeNotValidFromatStartDateAndEndDateAndEstimateTimeOfTask() {
//        int countError = 3;
//        TaskDTO taskDTO = new TaskDTO();
//        taskDTO.setStartDate("badFormat");
//        taskDTO.setEndDate("badFormat");
//        taskDTO.setEstimateTime("badFormat");
//
//        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(taskDTO);
//
//        assertThat(violations.size() == countError);
//
//        violations.forEach(error->{
//            if(error.getPropertyPath().toString().equals("startDate")) {
//                assertThat(error.getPropertyPath().toString()).isEqualTo("startDate");
//                assertThat(error.getInvalidValue().toString()).isEqualTo("badFormat");
//            }
//            if(error.getPropertyPath().toString().equals("endDate")) {
//                assertThat(error.getPropertyPath().toString()).isEqualTo("endDate");
//                assertThat(error.getInvalidValue().toString()).isEqualTo("badFormat");
//            }
//            if(error.getPropertyPath().toString().equals("estimateTime")) {
//                assertThat(error.getPropertyPath().toString()).isEqualTo("estimateTime");
//                assertThat(error.getInvalidValue().toString()).isEqualTo("badFormat");
//            }
//
//        });
//    }
//
//    @Test
//    public void shouldSaveIfPassedValidation(){
//        int countError = 0;
//        TaskDTO taskDTO = new TaskDTO();
//        taskDTO.setName("newTask");
//        taskDTO.setStartDate("2017-12-01");
//        taskDTO.setEndDate("2017-12-02");
//        taskDTO.setEstimateTime("08:00:30");
//        taskDTO.setStatusId(1);
//        taskDTO.setPriorityId(1);
//        taskDTO.setAssignTo(1);
//
//        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(taskDTO);
//
//        assertThat(violations.size() == countError);
//
//        Task persistedTask = taskService.createTaskByDTO(taskDTO);
//
//        when(taskDao.create(any(Task.class))).thenReturn(persistedTask);
//
//        Task newTask = taskService.create(persistedTask);
//
//        verify(taskDao).create(newTask);
//    }
}
