package com.community.web.vo;

/**
 * @Author:王喜
 * @Description :  存储项目成员的name,url的Vo
 * @Date: 2017/11/15 0015 15:22
 */
public class MembersVo {

    //项目成员的名字
    private String memberName;

    //项目成员的url
    private String memberUrl;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberUrl() {
        return memberUrl;
    }

    public void setMemberUrl(String memberUrl) {
        this.memberUrl = memberUrl;
    }


    public MembersVo() {
    }


    public MembersVo(String memberName, String memberUrl) {
        this.memberName = memberName;
        this.memberUrl = memberUrl;
    }
}
