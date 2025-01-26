package org.cresplanex.api.event.plancommandservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.cresplanex.api.event.plancommandservice.repository.TaskRepository;
import org.cresplanex.api.state.common.saga.local.plan.InvalidDueDateTimeException;
import org.cresplanex.api.state.common.saga.local.plan.InvalidStartDateTimeException;
import org.cresplanex.api.state.common.saga.local.plan.StartTimeMustBeEarlierDueTimeException;
import org.cresplanex.api.state.common.saga.local.plan.WillAddedTaskAttachmentsDuplicatedException;
import org.cresplanex.api.state.common.service.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskLocalValidateService extends BaseService {

    private final TaskRepository taskRepository;

    public void validateCreatedTask(
            String teamId,
            String chargeUserId,
            String title,
            String description,
            String startDatetime,
            String dueDatetime,
            List<String> taskAttachmentIds
    )
            throws InvalidStartDateTimeException, InvalidDueDateTimeException, StartTimeMustBeEarlierDueTimeException {
        LocalDateTime startTimestamp;
        try {
            startTimestamp = LocalDateTime.parse(startDatetime);
        } catch (Exception e) {
            throw new InvalidStartDateTimeException(startDatetime);
        }
        LocalDateTime dueTimestamp;
        try {
            dueTimestamp = LocalDateTime.parse(dueDatetime);
        } catch (Exception e) {
            throw new InvalidDueDateTimeException(dueDatetime);
        }
        if (startTimestamp.isAfter(dueTimestamp)) {
            throw new StartTimeMustBeEarlierDueTimeException(startTimestamp, dueTimestamp);
        }

        if (taskAttachmentIds.size() != taskAttachmentIds.stream().distinct().count()) {
            throw new WillAddedTaskAttachmentsDuplicatedException(taskAttachmentIds);
        }
    }

    public void validateTasks(List<String> taskIds)
            throws org.cresplanex.api.state.common.saga.local.plan.NotFoundTaskException {
        taskRepository.countByTaskIdIn(taskIds)
                .ifPresent(count -> {
                    if (count != taskIds.size()) {
                        throw new org.cresplanex.api.state.common.saga.local.plan.NotFoundTaskException(taskIds);
                    }
                });
    }
}
