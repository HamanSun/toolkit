package org.relaxation.common.enums;

/**
 * 说明：枚举类型
 */
public enum SysDictEnum {

	memberLevel("memberLevel", "是否继续跟进"),
	valueAdded("valueAdded", "增值业务"),
	companyType("companyType", "所属资源池"),
	financingStage("financingStage", "融资阶段"),
	ipo("ipo", "上市情况"),
	branch("branch", "外地分支机构"),
	ipr("ipr", "知识产权"),
	locationType("locationType", "跟进方式"),
	teamPosition("teamPosition", "会员公司团队岗位"),
	memberFee("memberFee", "会费"),
	iprType("iprType", "知识产权类型"),
	workType("workType", "工作类型");

	private String type;
	private String name;

	private SysDictEnum(String type, String name) {
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
		SysDictEnum[] enums = SysDictEnum.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getType().equals(type)) {
				return enums[i].getName();
			}
		}
		return "";
	}
}
