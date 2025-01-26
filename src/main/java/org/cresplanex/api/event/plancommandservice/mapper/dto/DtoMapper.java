package org.cresplanex.api.event.plancommandservice.mapper.dto;

import org.cresplanex.api.event.plancommandservice.entity.TaskAttachmentEntity;
import org.cresplanex.api.event.plancommandservice.entity.TaskEntity;
import org.cresplanex.api.state.common.dto.plan.FileObjectOnTaskDto;
import org.cresplanex.api.state.common.dto.plan.TaskDto;
import org.cresplanex.api.state.common.dto.plan.TaskWithAttachmentsDto;

import java.util.List;

public class DtoMapper {

    public static TaskDto convert(TaskEntity taskEntity) {
        return TaskDto.builder()
                .taskId(taskEntity.getTaskId())
                .teamId(taskEntity.getTeamId())
                .chargeUserId(taskEntity.getChargeUserId())
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .status(taskEntity.getStatus())
                .startDateTime(taskEntity.getStartDatetime().toString())
                .dueDateTime(taskEntity.getDueDatetime().toString())
                .build();
    }

    public static FileObjectOnTaskDto convert(TaskAttachmentEntity taskAttachmentEntity) {
        return FileObjectOnTaskDto.builder()
                .taskAttachmentId(taskAttachmentEntity.getTaskAttachmentId())
                .fileObjectId(taskAttachmentEntity.getFileObjectId())
                .build();
    }

    public static List<FileObjectOnTaskDto> convert(List<TaskAttachmentEntity> organizationUserEntities) {
        return organizationUserEntities.stream()
                .map(DtoMapper::convert)
                .toList();
    }

    public static TaskWithAttachmentsDto convert(TaskEntity taskEntity, List<TaskAttachmentEntity> organizationUserEntities) {
        return TaskWithAttachmentsDto.builder()
                .task(DtoMapper.convert(taskEntity))
                .attachments(convert(organizationUserEntities))
                .build();
    }
}
