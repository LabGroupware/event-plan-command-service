package org.cresplanex.api.event.plancommandservice.filter.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DueDatetimeFilter {

    private boolean earlierInfinity;
    private boolean laterInfinity;
    private String earlierThan;
    private String laterThan;
}
