package com.gpnu.server.var.model;

import com.gpnu.entity.var.VariableType;
import com.gpnu.entity.var.VariableValue;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class AbstractVariableValue implements VariableValue {

  protected VariableType variableType;
}
