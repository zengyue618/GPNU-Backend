package com.gpnu.server.service;

import com.gpnu.entity.system.OperationLog;
import org.springframework.data.domain.Page;

public interface OperationLogService {
  void recordOperation(OperationLog operationLog);

  Page<OperationLog> listOperationLogOrderByTime(int page, int size);

  Page<OperationLog> findOperationLogWithUserAndObjOrderByTime(String user, String obj, int page, int size);
}
