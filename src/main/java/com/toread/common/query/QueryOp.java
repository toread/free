/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-30
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.common.query;

/**
 * @author lizhibing
 * @ClassName: QueryOperator
 * @Description:
 * @date 2015-04-30 17:00
 */
public enum QueryOp{
    and("and"),or("or"),equal("equal"),notEqual("notEqual"),greaterThan("greaterThan"),
    greaterThanOrEqualTo("greaterThanOrEqualTo"),lessThan("lessThan"),lessThanOrEqualTo("lessThanOrEqualTo"),
    gt("gt"),ge("ge"),lt("lt"),le("le"),isEmpty("isEmpty"),isNotEmpty("isNotEmpty"),isMember("isMember"),
    isNotMember("isNotMember"),like("like"),notLike("notLike"),isNull("isNull"),isNotNull("isNotNull"),
    between("between"),
    ;
    private String opMethod;

    QueryOp(String opMethod) {
        this.opMethod = opMethod;
    }

    public String getOpMethod() {
        return opMethod;
    }

    public static String getKey(QueryOp op,String property){
        return op.getOpMethod()+"$"+property;
    }


    public void setOpMethod(String opMethod) {
        this.opMethod = opMethod;
    }
}
