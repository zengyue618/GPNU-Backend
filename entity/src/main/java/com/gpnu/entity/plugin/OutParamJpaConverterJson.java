package com.gpnu.entity.plugin;

import com.gpnu.core.utils.JsonUtil;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class OutParamJpaConverterJson implements AttributeConverter<List, String> {

  @SneakyThrows
  @Override
  public String convertToDatabaseColumn(List stringObjectMap) {
    return JsonUtil.toJson(stringObjectMap);
  }

  @SneakyThrows
  @Override
  public List<PackageOutParam> convertToEntityAttribute(String s) {
    return JsonUtil.fromJsonList(PackageOutParam.class,s);
  }
}
