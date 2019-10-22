package com.souche.salt_common_01.entity;

import java.util.ArrayList;
import java.util.List;

public class ScriptServerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScriptServerExample() {
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

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andScriptIdIsNull() {
            addCriterion("scriptId is null");
            return (Criteria) this;
        }

        public Criteria andScriptIdIsNotNull() {
            addCriterion("scriptId is not null");
            return (Criteria) this;
        }

        public Criteria andScriptIdEqualTo(String value) {
            addCriterion("scriptId =", value, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdNotEqualTo(String value) {
            addCriterion("scriptId <>", value, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdGreaterThan(String value) {
            addCriterion("scriptId >", value, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdGreaterThanOrEqualTo(String value) {
            addCriterion("scriptId >=", value, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdLessThan(String value) {
            addCriterion("scriptId <", value, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdLessThanOrEqualTo(String value) {
            addCriterion("scriptId <=", value, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdLike(String value) {
            addCriterion("scriptId like", value, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdNotLike(String value) {
            addCriterion("scriptId not like", value, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdIn(List<String> values) {
            addCriterion("scriptId in", values, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdNotIn(List<String> values) {
            addCriterion("scriptId not in", values, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdBetween(String value1, String value2) {
            addCriterion("scriptId between", value1, value2, "scriptId");
            return (Criteria) this;
        }

        public Criteria andScriptIdNotBetween(String value1, String value2) {
            addCriterion("scriptId not between", value1, value2, "scriptId");
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

        public Criteria andSzoneNameIsNull() {
            addCriterion("szoneName is null");
            return (Criteria) this;
        }

        public Criteria andSzoneNameIsNotNull() {
            addCriterion("szoneName is not null");
            return (Criteria) this;
        }

        public Criteria andSzoneNameEqualTo(String value) {
            addCriterion("szoneName =", value, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameNotEqualTo(String value) {
            addCriterion("szoneName <>", value, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameGreaterThan(String value) {
            addCriterion("szoneName >", value, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameGreaterThanOrEqualTo(String value) {
            addCriterion("szoneName >=", value, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameLessThan(String value) {
            addCriterion("szoneName <", value, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameLessThanOrEqualTo(String value) {
            addCriterion("szoneName <=", value, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameLike(String value) {
            addCriterion("szoneName like", value, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameNotLike(String value) {
            addCriterion("szoneName not like", value, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameIn(List<String> values) {
            addCriterion("szoneName in", values, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameNotIn(List<String> values) {
            addCriterion("szoneName not in", values, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameBetween(String value1, String value2) {
            addCriterion("szoneName between", value1, value2, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSzoneNameNotBetween(String value1, String value2) {
            addCriterion("szoneName not between", value1, value2, "szoneName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameIsNull() {
            addCriterion("sdoMainName is null");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameIsNotNull() {
            addCriterion("sdoMainName is not null");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameEqualTo(String value) {
            addCriterion("sdoMainName =", value, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameNotEqualTo(String value) {
            addCriterion("sdoMainName <>", value, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameGreaterThan(String value) {
            addCriterion("sdoMainName >", value, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameGreaterThanOrEqualTo(String value) {
            addCriterion("sdoMainName >=", value, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameLessThan(String value) {
            addCriterion("sdoMainName <", value, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameLessThanOrEqualTo(String value) {
            addCriterion("sdoMainName <=", value, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameLike(String value) {
            addCriterion("sdoMainName like", value, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameNotLike(String value) {
            addCriterion("sdoMainName not like", value, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameIn(List<String> values) {
            addCriterion("sdoMainName in", values, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameNotIn(List<String> values) {
            addCriterion("sdoMainName not in", values, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameBetween(String value1, String value2) {
            addCriterion("sdoMainName between", value1, value2, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andSdoMainNameNotBetween(String value1, String value2) {
            addCriterion("sdoMainName not between", value1, value2, "sdoMainName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameIsNull() {
            addCriterion("productLineName is null");
            return (Criteria) this;
        }

        public Criteria andProductLineNameIsNotNull() {
            addCriterion("productLineName is not null");
            return (Criteria) this;
        }

        public Criteria andProductLineNameEqualTo(String value) {
            addCriterion("productLineName =", value, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameNotEqualTo(String value) {
            addCriterion("productLineName <>", value, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameGreaterThan(String value) {
            addCriterion("productLineName >", value, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameGreaterThanOrEqualTo(String value) {
            addCriterion("productLineName >=", value, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameLessThan(String value) {
            addCriterion("productLineName <", value, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameLessThanOrEqualTo(String value) {
            addCriterion("productLineName <=", value, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameLike(String value) {
            addCriterion("productLineName like", value, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameNotLike(String value) {
            addCriterion("productLineName not like", value, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameIn(List<String> values) {
            addCriterion("productLineName in", values, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameNotIn(List<String> values) {
            addCriterion("productLineName not in", values, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameBetween(String value1, String value2) {
            addCriterion("productLineName between", value1, value2, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductLineNameNotBetween(String value1, String value2) {
            addCriterion("productLineName not between", value1, value2, "productLineName");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("productName is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("productName is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("productName =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("productName <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("productName >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("productName >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("productName <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("productName <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("productName like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("productName not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("productName in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("productName not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("productName between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("productName not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIsNull() {
            addCriterion("applicationName is null");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIsNotNull() {
            addCriterion("applicationName is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationNameEqualTo(String value) {
            addCriterion("applicationName =", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotEqualTo(String value) {
            addCriterion("applicationName <>", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThan(String value) {
            addCriterion("applicationName >", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThanOrEqualTo(String value) {
            addCriterion("applicationName >=", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThan(String value) {
            addCriterion("applicationName <", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThanOrEqualTo(String value) {
            addCriterion("applicationName <=", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLike(String value) {
            addCriterion("applicationName like", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotLike(String value) {
            addCriterion("applicationName not like", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIn(List<String> values) {
            addCriterion("applicationName in", values, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotIn(List<String> values) {
            addCriterion("applicationName not in", values, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameBetween(String value1, String value2) {
            addCriterion("applicationName between", value1, value2, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotBetween(String value1, String value2) {
            addCriterion("applicationName not between", value1, value2, "applicationName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameIsNull() {
            addCriterion("instanceName is null");
            return (Criteria) this;
        }

        public Criteria andInstanceNameIsNotNull() {
            addCriterion("instanceName is not null");
            return (Criteria) this;
        }

        public Criteria andInstanceNameEqualTo(String value) {
            addCriterion("instanceName =", value, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameNotEqualTo(String value) {
            addCriterion("instanceName <>", value, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameGreaterThan(String value) {
            addCriterion("instanceName >", value, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameGreaterThanOrEqualTo(String value) {
            addCriterion("instanceName >=", value, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameLessThan(String value) {
            addCriterion("instanceName <", value, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameLessThanOrEqualTo(String value) {
            addCriterion("instanceName <=", value, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameLike(String value) {
            addCriterion("instanceName like", value, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameNotLike(String value) {
            addCriterion("instanceName not like", value, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameIn(List<String> values) {
            addCriterion("instanceName in", values, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameNotIn(List<String> values) {
            addCriterion("instanceName not in", values, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameBetween(String value1, String value2) {
            addCriterion("instanceName between", value1, value2, "instanceName");
            return (Criteria) this;
        }

        public Criteria andInstanceNameNotBetween(String value1, String value2) {
            addCriterion("instanceName not between", value1, value2, "instanceName");
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