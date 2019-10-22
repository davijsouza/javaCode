package com.souche.salt_common_01.entity;

import java.util.ArrayList;
import java.util.List;

public class SaltMcheExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SaltMcheExample() {
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

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andStay1IsNull() {
            addCriterion("stay1 is null");
            return (Criteria) this;
        }

        public Criteria andStay1IsNotNull() {
            addCriterion("stay1 is not null");
            return (Criteria) this;
        }

        public Criteria andStay1EqualTo(String value) {
            addCriterion("stay1 =", value, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1NotEqualTo(String value) {
            addCriterion("stay1 <>", value, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1GreaterThan(String value) {
            addCriterion("stay1 >", value, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1GreaterThanOrEqualTo(String value) {
            addCriterion("stay1 >=", value, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1LessThan(String value) {
            addCriterion("stay1 <", value, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1LessThanOrEqualTo(String value) {
            addCriterion("stay1 <=", value, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1Like(String value) {
            addCriterion("stay1 like", value, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1NotLike(String value) {
            addCriterion("stay1 not like", value, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1In(List<String> values) {
            addCriterion("stay1 in", values, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1NotIn(List<String> values) {
            addCriterion("stay1 not in", values, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1Between(String value1, String value2) {
            addCriterion("stay1 between", value1, value2, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay1NotBetween(String value1, String value2) {
            addCriterion("stay1 not between", value1, value2, "stay1");
            return (Criteria) this;
        }

        public Criteria andStay2IsNull() {
            addCriterion("stay2 is null");
            return (Criteria) this;
        }

        public Criteria andStay2IsNotNull() {
            addCriterion("stay2 is not null");
            return (Criteria) this;
        }

        public Criteria andStay2EqualTo(String value) {
            addCriterion("stay2 =", value, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2NotEqualTo(String value) {
            addCriterion("stay2 <>", value, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2GreaterThan(String value) {
            addCriterion("stay2 >", value, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2GreaterThanOrEqualTo(String value) {
            addCriterion("stay2 >=", value, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2LessThan(String value) {
            addCriterion("stay2 <", value, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2LessThanOrEqualTo(String value) {
            addCriterion("stay2 <=", value, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2Like(String value) {
            addCriterion("stay2 like", value, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2NotLike(String value) {
            addCriterion("stay2 not like", value, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2In(List<String> values) {
            addCriterion("stay2 in", values, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2NotIn(List<String> values) {
            addCriterion("stay2 not in", values, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2Between(String value1, String value2) {
            addCriterion("stay2 between", value1, value2, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay2NotBetween(String value1, String value2) {
            addCriterion("stay2 not between", value1, value2, "stay2");
            return (Criteria) this;
        }

        public Criteria andStay3IsNull() {
            addCriterion("stay3 is null");
            return (Criteria) this;
        }

        public Criteria andStay3IsNotNull() {
            addCriterion("stay3 is not null");
            return (Criteria) this;
        }

        public Criteria andStay3EqualTo(String value) {
            addCriterion("stay3 =", value, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3NotEqualTo(String value) {
            addCriterion("stay3 <>", value, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3GreaterThan(String value) {
            addCriterion("stay3 >", value, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3GreaterThanOrEqualTo(String value) {
            addCriterion("stay3 >=", value, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3LessThan(String value) {
            addCriterion("stay3 <", value, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3LessThanOrEqualTo(String value) {
            addCriterion("stay3 <=", value, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3Like(String value) {
            addCriterion("stay3 like", value, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3NotLike(String value) {
            addCriterion("stay3 not like", value, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3In(List<String> values) {
            addCriterion("stay3 in", values, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3NotIn(List<String> values) {
            addCriterion("stay3 not in", values, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3Between(String value1, String value2) {
            addCriterion("stay3 between", value1, value2, "stay3");
            return (Criteria) this;
        }

        public Criteria andStay3NotBetween(String value1, String value2) {
            addCriterion("stay3 not between", value1, value2, "stay3");
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