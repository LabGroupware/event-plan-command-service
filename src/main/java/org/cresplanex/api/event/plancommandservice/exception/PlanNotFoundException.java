package org.cresplanex.api.event.plancommandservice.exception;

import build.buf.gen.plan.v1.PlanServiceErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlanNotFoundException extends ServiceException {

    private final FindType findType;
    private final String aggregateId;

    public PlanNotFoundException(FindType findType, String aggregateId) {
        this(findType, aggregateId, "Model not found: " + findType.name() + " with id " + aggregateId);
    }

    public PlanNotFoundException(FindType findType, String aggregateId, String message) {
        super(message);
        this.findType = findType;
        this.aggregateId = aggregateId;
    }

    public PlanNotFoundException(FindType findType, String aggregateId, String message, Throwable cause) {
        super(message, cause);
        this.findType = findType;
        this.aggregateId = aggregateId;
    }

    public enum FindType {
        BY_ID
    }

    @Override
    public PlanServiceErrorCode getServiceErrorCode() {
        return PlanServiceErrorCode.PLAN_SERVICE_ERROR_CODE_TASK_NOT_FOUND;
    }

    @Override
    public String getErrorCaption() {
        return switch (findType) {
            case BY_ID -> "Plan not found (ID = %s)".formatted(aggregateId);
            default -> "Plan not found";
        };
    }
}
