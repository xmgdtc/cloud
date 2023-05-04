package org.xmgdtc.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderEnum implements IEnumIsValid<String> {

    ASC("asc"),
    DESC("desc");

    String value;

    @Override
    public Boolean isValid(String value) {
        for (OrderEnum item : OrderEnum.values()) {
            if (item.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
