package com.souche.salt_common_01.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScriptsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScriptsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserIsNull() {
            addCriterion("lastMofityUser is null");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserIsNotNull() {
            addCriterion("lastMofityUser is not null");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserEqualTo(String value) {
            addCriterion("lastMofityUser =", value, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserNotEqualTo(String value) {
            addCriterion("lastMofityUser <>", value, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserGreaterThan(String value) {
            addCriterion("lastMofityUser >", value, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserGreaterThanOrEqualTo(String value) {
            addCriterion("lastMofityUser >=", value, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserLessThan(String value) {
            addCriterion("lastMofityUser <", value, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserLessThanOrEqualTo(String value) {
            addCriterion("lastMofityUser <=", value, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserLike(String value) {
            addCriterion("lastMofityUser like", value, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserNotLike(String value) {
            addCriterion("lastMofityUser not like", value, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserIn(List<String> values) {
            addCriterion("lastMofityUser in", values, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserNotIn(List<String> values) {
            addCriterion("lastMofityUser not in", values, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserBetween(String value1, String value2) {
            addCriterion("lastMofityUser between", value1, value2, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastMofityUserNotBetween(String value1, String value2) {
            addCriterion("lastMofityUser not between", value1, value2, "lastMofityUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIsNull() {
            addCriterion("lastModifyTime is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIsNotNull() {
            addCriterion("lastModifyTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeEqualTo(Date value) {
            addCriterion("lastModifyTime =", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotEqualTo(Date value) {
            addCriterion("lastModifyTime <>", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeGreaterThan(Date value) {
            addCriterion("lastModifyTime >", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastModifyTime >=", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeLessThan(Date value) {
            addCriterion("lastModifyTime <", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("lastModifyTime <=", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIn(List<Date> values) {
            addCriterion("lastModifyTime in", values, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotIn(List<Date> values) {
            addCriterion("lastModifyTime not in", values, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeBetween(Date value1, Date value2) {
            addCriterion("lastModifyTime between", value1, value2, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("lastModifyTime not between", value1, value2, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andIsCommonIsNull() {
            addCriterion("isCommon is null");
            return (Criteria) this;
        }

        public Criteria andIsCommonIsNotNull() {
            addCriterion("isCommon is not null");
            return (Criteria) this;
        }

        public Criteria andIsCommonEqualTo(String value) {
            addCriterion("isCommon =", value, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonNotEqualTo(String value) {
            addCriterion("isCommon <>", value, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonGreaterThan(String value) {
            addCriterion("isCommon >", value, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonGreaterThanOrEqualTo(String value) {
            addCriterion("isCommon >=", value, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonLessThan(String value) {
            addCriterion("isCommon <", value, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonLessThanOrEqualTo(String value) {
            addCriterion("isCommon <=", value, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonLike(String value) {
            addCriterion("isCommon like", value, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonNotLike(String value) {
            addCriterion("isCommon not like", value, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonIn(List<String> values) {
            addCriterion("isCommon in", values, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonNotIn(List<String> values) {
            addCriterion("isCommon not in", values, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonBetween(String value1, String value2) {
            addCriterion("isCommon between", value1, value2, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIsCommonNotBetween(String value1, String value2) {
            addCriterion("isCommon not between", value1, value2, "isCommon");
            return (Criteria) this;
        }

        public Criteria andIdDeletedIsNull() {
            addCriterion("idDeleted is null");
            return (Criteria) this;
        }

        public Criteria andIdDeletedIsNotNull() {
            addCriterion("idDeleted is not null");
            return (Criteria) this;
        }

        public Criteria andIdDeletedEqualTo(String value) {
            addCriterion("idDeleted =", value, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedNotEqualTo(String value) {
            addCriterion("idDeleted <>", value, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedGreaterThan(String value) {
            addCriterion("idDeleted >", value, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedGreaterThanOrEqualTo(String value) {
            addCriterion("idDeleted >=", value, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedLessThan(String value) {
            addCriterion("idDeleted <", value, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedLessThanOrEqualTo(String value) {
            addCriterion("idDeleted <=", value, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedLike(String value) {
            addCriterion("idDeleted like", value, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedNotLike(String value) {
            addCriterion("idDeleted not like", value, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedIn(List<String> values) {
            addCriterion("idDeleted in", values, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedNotIn(List<String> values) {
            addCriterion("idDeleted not in", values, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedBetween(String value1, String value2) {
            addCriterion("idDeleted between", value1, value2, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andIdDeletedNotBetween(String value1, String value2) {
            addCriterion("idDeleted not between", value1, value2, "idDeleted");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("orderNum is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("orderNum is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(Integer value) {
            addCriterion("orderNum =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(Integer value) {
            addCriterion("orderNum <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(Integer value) {
            addCriterion("orderNum >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderNum >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(Integer value) {
            addCriterion("orderNum <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("orderNum <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<Integer> values) {
            addCriterion("orderNum in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<Integer> values) {
            addCriterion("orderNum not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("orderNum between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("orderNum not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andPauseIsNull() {
            addCriterion("pause is null");
            return (Criteria) this;
        }

        public Criteria andPauseIsNotNull() {
            addCriterion("pause is not null");
            return (Criteria) this;
        }

        public Criteria andPauseEqualTo(String value) {
            addCriterion("pause =", value, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseNotEqualTo(String value) {
            addCriterion("pause <>", value, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseGreaterThan(String value) {
            addCriterion("pause >", value, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseGreaterThanOrEqualTo(String value) {
            addCriterion("pause >=", value, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseLessThan(String value) {
            addCriterion("pause <", value, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseLessThanOrEqualTo(String value) {
            addCriterion("pause <=", value, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseLike(String value) {
            addCriterion("pause like", value, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseNotLike(String value) {
            addCriterion("pause not like", value, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseIn(List<String> values) {
            addCriterion("pause in", values, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseNotIn(List<String> values) {
            addCriterion("pause not in", values, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseBetween(String value1, String value2) {
            addCriterion("pause between", value1, value2, "pause");
            return (Criteria) this;
        }

        public Criteria andPauseNotBetween(String value1, String value2) {
            addCriterion("pause not between", value1, value2, "pause");
            return (Criteria) this;
        }

        public Criteria andSaltSnameIsNull() {
            addCriterion("saltSname is null");
            return (Criteria) this;
        }

        public Criteria andSaltSnameIsNotNull() {
            addCriterion("saltSname is not null");
            return (Criteria) this;
        }

        public Criteria andSaltSnameEqualTo(String value) {
            addCriterion("saltSname =", value, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameNotEqualTo(String value) {
            addCriterion("saltSname <>", value, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameGreaterThan(String value) {
            addCriterion("saltSname >", value, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameGreaterThanOrEqualTo(String value) {
            addCriterion("saltSname >=", value, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameLessThan(String value) {
            addCriterion("saltSname <", value, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameLessThanOrEqualTo(String value) {
            addCriterion("saltSname <=", value, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameLike(String value) {
            addCriterion("saltSname like", value, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameNotLike(String value) {
            addCriterion("saltSname not like", value, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameIn(List<String> values) {
            addCriterion("saltSname in", values, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameNotIn(List<String> values) {
            addCriterion("saltSname not in", values, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameBetween(String value1, String value2) {
            addCriterion("saltSname between", value1, value2, "saltSname");
            return (Criteria) this;
        }

        public Criteria andSaltSnameNotBetween(String value1, String value2) {
            addCriterion("saltSname not between", value1, value2, "saltSname");
            return (Criteria) this;
        }

        public Criteria andData1IsNull() {
            addCriterion("data1 is null");
            return (Criteria) this;
        }

        public Criteria andData1IsNotNull() {
            addCriterion("data1 is not null");
            return (Criteria) this;
        }

        public Criteria andData1EqualTo(String value) {
            addCriterion("data1 =", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1NotEqualTo(String value) {
            addCriterion("data1 <>", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1GreaterThan(String value) {
            addCriterion("data1 >", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1GreaterThanOrEqualTo(String value) {
            addCriterion("data1 >=", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1LessThan(String value) {
            addCriterion("data1 <", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1LessThanOrEqualTo(String value) {
            addCriterion("data1 <=", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1Like(String value) {
            addCriterion("data1 like", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1NotLike(String value) {
            addCriterion("data1 not like", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1In(List<String> values) {
            addCriterion("data1 in", values, "data1");
            return (Criteria) this;
        }

        public Criteria andData1NotIn(List<String> values) {
            addCriterion("data1 not in", values, "data1");
            return (Criteria) this;
        }

        public Criteria andData1Between(String value1, String value2) {
            addCriterion("data1 between", value1, value2, "data1");
            return (Criteria) this;
        }

        public Criteria andData1NotBetween(String value1, String value2) {
            addCriterion("data1 not between", value1, value2, "data1");
            return (Criteria) this;
        }

        public Criteria andData2IsNull() {
            addCriterion("data2 is null");
            return (Criteria) this;
        }

        public Criteria andData2IsNotNull() {
            addCriterion("data2 is not null");
            return (Criteria) this;
        }

        public Criteria andData2EqualTo(String value) {
            addCriterion("data2 =", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2NotEqualTo(String value) {
            addCriterion("data2 <>", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2GreaterThan(String value) {
            addCriterion("data2 >", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2GreaterThanOrEqualTo(String value) {
            addCriterion("data2 >=", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2LessThan(String value) {
            addCriterion("data2 <", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2LessThanOrEqualTo(String value) {
            addCriterion("data2 <=", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2Like(String value) {
            addCriterion("data2 like", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2NotLike(String value) {
            addCriterion("data2 not like", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2In(List<String> values) {
            addCriterion("data2 in", values, "data2");
            return (Criteria) this;
        }

        public Criteria andData2NotIn(List<String> values) {
            addCriterion("data2 not in", values, "data2");
            return (Criteria) this;
        }

        public Criteria andData2Between(String value1, String value2) {
            addCriterion("data2 between", value1, value2, "data2");
            return (Criteria) this;
        }

        public Criteria andData2NotBetween(String value1, String value2) {
            addCriterion("data2 not between", value1, value2, "data2");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}