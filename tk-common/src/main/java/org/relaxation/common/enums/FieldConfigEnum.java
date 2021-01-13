package org.relaxation.common.enums;

/**
 * FieldConfigEnum class
 *
 * @date 2019-11-25
 * 类功能描述 (alt + enter -> add to custom tags)
 */
public enum FieldConfigEnum {

    MemberEntity("MemberEntity"),
    MemberRdInputEntity("MemberRdInputEntity"),
    MemberIncomeEntity("MemberIncomeEntity"),;

    private String value;

    private FieldConfigEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
