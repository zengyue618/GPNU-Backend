package com.gpnu.entity.var;


import com.gpnu.entity.plugin.ParamType;

public interface VariableValue {

  VariableType getVariableType();

  Object getValue();

  void setValue(Object valueString);

  ParamType getParamType();

  void applyUpdate(Object value);

}
