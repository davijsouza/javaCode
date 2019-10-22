package com.souche.salt_common_01.entity;

import java.util.ArrayList;
import java.util.List;

public class ConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConfigExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInstanceIdIsNull() {
            addCriterion("instanceId is null");
            return (Criteria) this;
        }

        public Criteria andInstanceIdIsNotNull() {
            addCriterion("instanceId is not null");
            return (Criteria) this;
        }

        public Criteria andInstanceIdEqualTo(String value) {
            addCriterion("instanceId =", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotEqualTo(String value) {
            addCriterion("instanceId <>", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdGreaterThan(String value) {
            addCriterion("instanceId >", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("instanceId >=", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdLessThan(String value) {
            addCriterion("instanceId <", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("instanceId <=", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdLike(String value) {
            addCriterion("instanceId like", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotLike(String value) {
            addCriterion("instanceId not like", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdIn(List<String> values) {
            addCriterion("instanceId in", values, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotIn(List<String> values) {
            addCriterion("instanceId not in", values, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdBetween(String value1, String value2) {
            addCriterion("instanceId between", value1, value2, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotBetween(String value1, String value2) {
            addCriterion("instanceId not between", value1, value2, "instanceId");
            return (Criteria) this;
        }

        public Criteria andConfigPathIsNull() {
            addCriterion("configPath is null");
            return (Criteria) this;
        }

        public Criteria andConfigPathIsNotNull() {
            addCriterion("configPath is not null");
            return (Criteria) this;
        }

        public Criteria andConfigPathEqualTo(String value) {
            addCriterion("configPath =", value, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathNotEqualTo(String value) {
            addCriterion("configPath <>", value, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathGreaterThan(String value) {
            addCriterion("configPath >", value, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathGreaterThanOrEqualTo(String value) {
            addCriterion("configPath >=", value, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathLessThan(String value) {
            addCriterion("configPath <", value, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathLessThanOrEqualTo(String value) {
            addCriterion("configPath <=", value, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathLike(String value) {
            addCriterion("configPath like", value, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathNotLike(String value) {
            addCriterion("configPath not like", value, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathIn(List<String> values) {
            addCriterion("configPath in", values, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathNotIn(List<String> values) {
            addCriterion("configPath not in", values, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathBetween(String value1, String value2) {
            addCriterion("configPath between", value1, value2, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigPathNotBetween(String value1, String value2) {
            addCriterion("configPath not between", value1, value2, "configPath");
            return (Criteria) this;
        }

        public Criteria andConfigNameIsNull() {
            addCriterion("configName is null");
            return (Criteria) this;
        }

        public Criteria andConfigNameIsNotNull() {
            addCriterion("configName is not null");
            return (Criteria) this;
        }

        public Criteria andConfigNameEqualTo(String value) {
            addCriterion("configName =", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameNotEqualTo(String value) {
            addCriterion("configName <>", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameGreaterThan(String value) {
            addCriterion("configName >", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameGreaterThanOrEqualTo(String value) {
            addCriterion("configName >=", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameLessThan(String value) {
            addCriterion("configName <", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameLessThanOrEqualTo(String value) {
            addCriterion("configName <=", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameLike(String value) {
            addCriterion("configName like", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameNotLike(String value) {
            addCriterion("configName not like", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameIn(List<String> values) {
            addCriterion("configName in", values, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameNotIn(List<String> values) {
            addCriterion("configName not in", values, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameBetween(String value1, String value2) {
            addCriterion("configName between", value1, value2, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameNotBetween(String value1, String value2) {
            addCriterion("configName not between", value1, value2, "configName");
            return (Criteria) this;
        }

        public Criteria andVerTime1IsNull() {
            addCriterion("verTime1 is null");
            return (Criteria) this;
        }

        public Criteria andVerTime1IsNotNull() {
            addCriterion("verTime1 is not null");
            return (Criteria) this;
        }

        public Criteria andVerTime1EqualTo(String value) {
            addCriterion("verTime1 =", value, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1NotEqualTo(String value) {
            addCriterion("verTime1 <>", value, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1GreaterThan(String value) {
            addCriterion("verTime1 >", value, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1GreaterThanOrEqualTo(String value) {
            addCriterion("verTime1 >=", value, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1LessThan(String value) {
            addCriterion("verTime1 <", value, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1LessThanOrEqualTo(String value) {
            addCriterion("verTime1 <=", value, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1Like(String value) {
            addCriterion("verTime1 like", value, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1NotLike(String value) {
            addCriterion("verTime1 not like", value, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1In(List<String> values) {
            addCriterion("verTime1 in", values, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1NotIn(List<String> values) {
            addCriterion("verTime1 not in", values, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1Between(String value1, String value2) {
            addCriterion("verTime1 between", value1, value2, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime1NotBetween(String value1, String value2) {
            addCriterion("verTime1 not between", value1, value2, "verTime1");
            return (Criteria) this;
        }

        public Criteria andVerTime2IsNull() {
            addCriterion("verTime2 is null");
            return (Criteria) this;
        }

        public Criteria andVerTime2IsNotNull() {
            addCriterion("verTime2 is not null");
            return (Criteria) this;
        }

        public Criteria andVerTime2EqualTo(String value) {
            addCriterion("verTime2 =", value, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2NotEqualTo(String value) {
            addCriterion("verTime2 <>", value, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2GreaterThan(String value) {
            addCriterion("verTime2 >", value, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2GreaterThanOrEqualTo(String value) {
            addCriterion("verTime2 >=", value, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2LessThan(String value) {
            addCriterion("verTime2 <", value, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2LessThanOrEqualTo(String value) {
            addCriterion("verTime2 <=", value, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2Like(String value) {
            addCriterion("verTime2 like", value, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2NotLike(String value) {
            addCriterion("verTime2 not like", value, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2In(List<String> values) {
            addCriterion("verTime2 in", values, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2NotIn(List<String> values) {
            addCriterion("verTime2 not in", values, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2Between(String value1, String value2) {
            addCriterion("verTime2 between", value1, value2, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime2NotBetween(String value1, String value2) {
            addCriterion("verTime2 not between", value1, value2, "verTime2");
            return (Criteria) this;
        }

        public Criteria andVerTime3IsNull() {
            addCriterion("verTime3 is null");
            return (Criteria) this;
        }

        public Criteria andVerTime3IsNotNull() {
            addCriterion("verTime3 is not null");
            return (Criteria) this;
        }

        public Criteria andVerTime3EqualTo(String value) {
            addCriterion("verTime3 =", value, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3NotEqualTo(String value) {
            addCriterion("verTime3 <>", value, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3GreaterThan(String value) {
            addCriterion("verTime3 >", value, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3GreaterThanOrEqualTo(String value) {
            addCriterion("verTime3 >=", value, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3LessThan(String value) {
            addCriterion("verTime3 <", value, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3LessThanOrEqualTo(String value) {
            addCriterion("verTime3 <=", value, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3Like(String value) {
            addCriterion("verTime3 like", value, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3NotLike(String value) {
            addCriterion("verTime3 not like", value, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3In(List<String> values) {
            addCriterion("verTime3 in", values, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3NotIn(List<String> values) {
            addCriterion("verTime3 not in", values, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3Between(String value1, String value2) {
            addCriterion("verTime3 between", value1, value2, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime3NotBetween(String value1, String value2) {
            addCriterion("verTime3 not between", value1, value2, "verTime3");
            return (Criteria) this;
        }

        public Criteria andVerTime4IsNull() {
            addCriterion("verTime4 is null");
            return (Criteria) this;
        }

        public Criteria andVerTime4IsNotNull() {
            addCriterion("verTime4 is not null");
            return (Criteria) this;
        }

        public Criteria andVerTime4EqualTo(String value) {
            addCriterion("verTime4 =", value, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4NotEqualTo(String value) {
            addCriterion("verTime4 <>", value, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4GreaterThan(String value) {
            addCriterion("verTime4 >", value, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4GreaterThanOrEqualTo(String value) {
            addCriterion("verTime4 >=", value, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4LessThan(String value) {
            addCriterion("verTime4 <", value, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4LessThanOrEqualTo(String value) {
            addCriterion("verTime4 <=", value, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4Like(String value) {
            addCriterion("verTime4 like", value, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4NotLike(String value) {
            addCriterion("verTime4 not like", value, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4In(List<String> values) {
            addCriterion("verTime4 in", values, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4NotIn(List<String> values) {
            addCriterion("verTime4 not in", values, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4Between(String value1, String value2) {
            addCriterion("verTime4 between", value1, value2, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime4NotBetween(String value1, String value2) {
            addCriterion("verTime4 not between", value1, value2, "verTime4");
            return (Criteria) this;
        }

        public Criteria andVerTime5IsNull() {
            addCriterion("verTime5 is null");
            return (Criteria) this;
        }

        public Criteria andVerTime5IsNotNull() {
            addCriterion("verTime5 is not null");
            return (Criteria) this;
        }

        public Criteria andVerTime5EqualTo(String value) {
            addCriterion("verTime5 =", value, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5NotEqualTo(String value) {
            addCriterion("verTime5 <>", value, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5GreaterThan(String value) {
            addCriterion("verTime5 >", value, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5GreaterThanOrEqualTo(String value) {
            addCriterion("verTime5 >=", value, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5LessThan(String value) {
            addCriterion("verTime5 <", value, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5LessThanOrEqualTo(String value) {
            addCriterion("verTime5 <=", value, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5Like(String value) {
            addCriterion("verTime5 like", value, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5NotLike(String value) {
            addCriterion("verTime5 not like", value, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5In(List<String> values) {
            addCriterion("verTime5 in", values, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5NotIn(List<String> values) {
            addCriterion("verTime5 not in", values, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5Between(String value1, String value2) {
            addCriterion("verTime5 between", value1, value2, "verTime5");
            return (Criteria) this;
        }

        public Criteria andVerTime5NotBetween(String value1, String value2) {
            addCriterion("verTime5 not between", value1, value2, "verTime5");
            return (Criteria) this;
        }

        public Criteria andCrearerIsNull() {
            addCriterion("crearer is null");
            return (Criteria) this;
        }

        public Criteria andCrearerIsNotNull() {
            addCriterion("crearer is not null");
            return (Criteria) this;
        }

        public Criteria andCrearerEqualTo(String value) {
            addCriterion("crearer =", value, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerNotEqualTo(String value) {
            addCriterion("crearer <>", value, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerGreaterThan(String value) {
            addCriterion("crearer >", value, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerGreaterThanOrEqualTo(String value) {
            addCriterion("crearer >=", value, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerLessThan(String value) {
            addCriterion("crearer <", value, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerLessThanOrEqualTo(String value) {
            addCriterion("crearer <=", value, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerLike(String value) {
            addCriterion("crearer like", value, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerNotLike(String value) {
            addCriterion("crearer not like", value, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerIn(List<String> values) {
            addCriterion("crearer in", values, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerNotIn(List<String> values) {
            addCriterion("crearer not in", values, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerBetween(String value1, String value2) {
            addCriterion("crearer between", value1, value2, "crearer");
            return (Criteria) this;
        }

        public Criteria andCrearerNotBetween(String value1, String value2) {
            addCriterion("crearer not between", value1, value2, "crearer");
            return (Criteria) this;
        }

        public Criteria andModify1IsNull() {
            addCriterion("modify1 is null");
            return (Criteria) this;
        }

        public Criteria andModify1IsNotNull() {
            addCriterion("modify1 is not null");
            return (Criteria) this;
        }

        public Criteria andModify1EqualTo(String value) {
            addCriterion("modify1 =", value, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1NotEqualTo(String value) {
            addCriterion("modify1 <>", value, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1GreaterThan(String value) {
            addCriterion("modify1 >", value, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1GreaterThanOrEqualTo(String value) {
            addCriterion("modify1 >=", value, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1LessThan(String value) {
            addCriterion("modify1 <", value, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1LessThanOrEqualTo(String value) {
            addCriterion("modify1 <=", value, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1Like(String value) {
            addCriterion("modify1 like", value, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1NotLike(String value) {
            addCriterion("modify1 not like", value, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1In(List<String> values) {
            addCriterion("modify1 in", values, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1NotIn(List<String> values) {
            addCriterion("modify1 not in", values, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1Between(String value1, String value2) {
            addCriterion("modify1 between", value1, value2, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify1NotBetween(String value1, String value2) {
            addCriterion("modify1 not between", value1, value2, "modify1");
            return (Criteria) this;
        }

        public Criteria andModify2IsNull() {
            addCriterion("modify2 is null");
            return (Criteria) this;
        }

        public Criteria andModify2IsNotNull() {
            addCriterion("modify2 is not null");
            return (Criteria) this;
        }

        public Criteria andModify2EqualTo(String value) {
            addCriterion("modify2 =", value, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2NotEqualTo(String value) {
            addCriterion("modify2 <>", value, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2GreaterThan(String value) {
            addCriterion("modify2 >", value, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2GreaterThanOrEqualTo(String value) {
            addCriterion("modify2 >=", value, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2LessThan(String value) {
            addCriterion("modify2 <", value, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2LessThanOrEqualTo(String value) {
            addCriterion("modify2 <=", value, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2Like(String value) {
            addCriterion("modify2 like", value, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2NotLike(String value) {
            addCriterion("modify2 not like", value, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2In(List<String> values) {
            addCriterion("modify2 in", values, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2NotIn(List<String> values) {
            addCriterion("modify2 not in", values, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2Between(String value1, String value2) {
            addCriterion("modify2 between", value1, value2, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify2NotBetween(String value1, String value2) {
            addCriterion("modify2 not between", value1, value2, "modify2");
            return (Criteria) this;
        }

        public Criteria andModify3IsNull() {
            addCriterion("modify3 is null");
            return (Criteria) this;
        }

        public Criteria andModify3IsNotNull() {
            addCriterion("modify3 is not null");
            return (Criteria) this;
        }

        public Criteria andModify3EqualTo(String value) {
            addCriterion("modify3 =", value, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3NotEqualTo(String value) {
            addCriterion("modify3 <>", value, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3GreaterThan(String value) {
            addCriterion("modify3 >", value, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3GreaterThanOrEqualTo(String value) {
            addCriterion("modify3 >=", value, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3LessThan(String value) {
            addCriterion("modify3 <", value, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3LessThanOrEqualTo(String value) {
            addCriterion("modify3 <=", value, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3Like(String value) {
            addCriterion("modify3 like", value, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3NotLike(String value) {
            addCriterion("modify3 not like", value, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3In(List<String> values) {
            addCriterion("modify3 in", values, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3NotIn(List<String> values) {
            addCriterion("modify3 not in", values, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3Between(String value1, String value2) {
            addCriterion("modify3 between", value1, value2, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify3NotBetween(String value1, String value2) {
            addCriterion("modify3 not between", value1, value2, "modify3");
            return (Criteria) this;
        }

        public Criteria andModify4IsNull() {
            addCriterion("modify4 is null");
            return (Criteria) this;
        }

        public Criteria andModify4IsNotNull() {
            addCriterion("modify4 is not null");
            return (Criteria) this;
        }

        public Criteria andModify4EqualTo(String value) {
            addCriterion("modify4 =", value, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4NotEqualTo(String value) {
            addCriterion("modify4 <>", value, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4GreaterThan(String value) {
            addCriterion("modify4 >", value, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4GreaterThanOrEqualTo(String value) {
            addCriterion("modify4 >=", value, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4LessThan(String value) {
            addCriterion("modify4 <", value, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4LessThanOrEqualTo(String value) {
            addCriterion("modify4 <=", value, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4Like(String value) {
            addCriterion("modify4 like", value, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4NotLike(String value) {
            addCriterion("modify4 not like", value, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4In(List<String> values) {
            addCriterion("modify4 in", values, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4NotIn(List<String> values) {
            addCriterion("modify4 not in", values, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4Between(String value1, String value2) {
            addCriterion("modify4 between", value1, value2, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify4NotBetween(String value1, String value2) {
            addCriterion("modify4 not between", value1, value2, "modify4");
            return (Criteria) this;
        }

        public Criteria andModify5IsNull() {
            addCriterion("modify5 is null");
            return (Criteria) this;
        }

        public Criteria andModify5IsNotNull() {
            addCriterion("modify5 is not null");
            return (Criteria) this;
        }

        public Criteria andModify5EqualTo(String value) {
            addCriterion("modify5 =", value, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5NotEqualTo(String value) {
            addCriterion("modify5 <>", value, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5GreaterThan(String value) {
            addCriterion("modify5 >", value, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5GreaterThanOrEqualTo(String value) {
            addCriterion("modify5 >=", value, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5LessThan(String value) {
            addCriterion("modify5 <", value, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5LessThanOrEqualTo(String value) {
            addCriterion("modify5 <=", value, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5Like(String value) {
            addCriterion("modify5 like", value, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5NotLike(String value) {
            addCriterion("modify5 not like", value, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5In(List<String> values) {
            addCriterion("modify5 in", values, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5NotIn(List<String> values) {
            addCriterion("modify5 not in", values, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5Between(String value1, String value2) {
            addCriterion("modify5 between", value1, value2, "modify5");
            return (Criteria) this;
        }

        public Criteria andModify5NotBetween(String value1, String value2) {
            addCriterion("modify5 not between", value1, value2, "modify5");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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