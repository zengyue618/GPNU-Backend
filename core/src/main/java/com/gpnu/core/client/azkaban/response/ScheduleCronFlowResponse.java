package com.gpnu.core.client.azkaban.response;

import lombok.Data;

@Data
public class ScheduleCronFlowResponse extends BaseResponse {
    private String scheduleId;
}
