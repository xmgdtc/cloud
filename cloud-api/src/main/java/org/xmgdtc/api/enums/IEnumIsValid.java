package org.xmgdtc.api.enums;

public interface IEnumIsValid<T> {


    //想着实现这个接口 形成统一的验证 还没想好怎么实现
    Boolean isValid(T value);

}
