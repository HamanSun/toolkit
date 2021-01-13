package org.relaxation.common.enums;

/**
 * 说明：枚举类型
 */
public enum ActModelCategoryEnum {

	LABEL("00101", "公共标签流程"),
	MEMBER_LABEL("00102", "会员标签流程"),
	DISTRIBUTE("00103", "派发流程");

	private String type;
	private String name;

	private ActModelCategoryEnum(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}


	public String getName() {
		return name;
	}


	public static String getNameByType(String type) {
		ActModelCategoryEnum[] enums = ActModelCategoryEnum.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getType().equals(type)) {
				return enums[i].getName();
			}
		}
		return "";
	}
}
