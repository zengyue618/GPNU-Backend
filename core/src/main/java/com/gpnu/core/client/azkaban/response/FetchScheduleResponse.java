package com.gpnu.core.client.azkaban.response;

import com.gpnu.core.client.azkaban.model.Schedule;
import lombok.Data;

@Data
public class FetchScheduleResponse extends BaseResponse {
    private Schedule schedule;

}