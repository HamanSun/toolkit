package org.relaxation.common.enums;

/**
 * 说明：枚举类型
 */
public enum MessageTypeEnum {

	ACT_LABEL("ACT_LABEL", "公共标签修改"),
	ACT_MEMBER_LABEL("ACT_MEMBER_LABEL", "会员标签修改"),
	DISTRIBUTE("DISTRIBUTE", "任务派发");

	private String type;
	private String name;

	private MessageTypeEnum(String type, String name) {
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
		MessageTypeEnum[] enums = MessageTypeEnum.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getType().equals(type)) {
				return enums[i].getName();
			}
		}
		return "";
	}
}
