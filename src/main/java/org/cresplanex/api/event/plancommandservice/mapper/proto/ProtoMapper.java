package org.cresplanex.api.event.plancommandservice.mapper.proto;

import build.buf.gen.plan.v1.FileObjectOnTask;
import build.buf.gen.plan.v1.Task;
import build.buf.gen.plan.v1.TaskOnFileObject;
import build.buf.gen.plan.v1.TaskWithAttachments;

import java.util.List;

import org.cresplanex.api.event.plancommandservice.entity.TaskAttachmentEntity;
import org.cresplanex.api.event.plancommandservice.entity.TaskEntity;

public class ProtoMapper {

    public static Task convert(TaskEntity organizationEntity) {

        return Task.newBuilder()
                .setTaskId(organizationEntity.getTaskId())
                .setTeamId(organizationEntity.getTeamId())
                .setChargeUserId(organizationEntity.getChargeUserId())
                .setTitle(organizationEntity.getTitle())
                .setDescription(organizationEntity.getDescription())
                .setStatus(organizationEntity.getStatus())
                .setStartDatetime(String.valueOf(organizationEntity.getStartDatetime()))
                .setDueDatetime(String.valueOf(organizationEntity.getDueDatetime()))
                .build();
    }

    public static FileObjectOnTask convert(TaskAttachmentEntity fileObjectOnTaskEntity) {
        return FileObjectOnTask.newBuilder()
                .setFileObjectId(fileObjectOnTaskEntity.getFileObjectId())
                .build();
    }

    public static List<FileObjectOnTask> convert(List<TaskAttachmentEntity> fileObjectOnTaskEntities) {
        return fileObjectOnTaskEntities.stream()
                .map(ProtoMapper::convert)
                .toList();
    }

    public static TaskWithAttachments convert(TaskEntity taskEntity, List<TaskAttachmentEntity> fileObjectOnTaskEntities) {
        return TaskWithAttachments.newBuilder()
                .setTask(convert(taskEntity))
                .addAllAttachments(convert(fileObjectOnTaskEntities))
                .build();
    }

    public static TaskOnFileObject convertOnFileObject(TaskAttachmentEntity taskAttachmentEntity) {
        return TaskOnFileObject.newBuilder()
                .setTask(convert(taskAttachmentEntity.getTask()))
                .build();
    }

    public static TaskWithAttachments convertWithFileObjects(TaskEntity taskEntity) {
        return TaskWithAttachments.newBuilder()
                .setTask(convert(taskEntity))
                .addAllAttachments(convert(taskEntity.getTaskAttachments()))
                .build();
    }
}
