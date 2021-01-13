package org.relaxation.common.enums;

/**
 * 说明：枚举类型
 */
public enum ActKeyEnum {

	ACT_LABEL("ACT_LABEL", "公共标签修改流程"),
	ACT_MEMBER_LABEL("ACT_MEMBER_LABEL", "会员标签修改流程"),
	DISTRIBUTE("DISTRIBUTE", "派发流程"),
	ACT_LEAVE("ACT_LEAVE", "请假流程");

	private String type;
	private String name;

	private ActKeyEnum(String type, String name) {
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
		ActKeyEnum[] enums = ActKeyEnum.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getType().equals(type)) {
				return enums[i].getName();
			}
		}
		return "";
	}
}
