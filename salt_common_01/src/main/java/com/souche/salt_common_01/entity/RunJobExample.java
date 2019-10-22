package com.souche.salt_common_01.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunJobExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunJobExample() {
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

        public Criteria andJobIdIsNull() {
            addCriterion("jobId is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("jobId is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(String value) {
            addCriterion("jobId =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(String value) {
            addCriterion("jobId <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(String value) {
            addCriterion("jobId >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(String value) {
            addCriterion("jobId >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(String value) {
            addCriterion("jobId <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(String value) {
            addCriterion("jobId <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLike(String value) {
            addCriterion("jobId like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotLike(String value) {
            addCriterion("jobId not like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<String> values) {
            addCriterion("jobId in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<String> values) {
            addCriterion("jobId not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(String value1, String value2) {
            addCriterion("jobId between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(String value1, String value2) {
            addCriterion("jobId not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdIsNull() {
            addCriterion("jobLogId is null");
            return (Criteria) this;
        }

        public Criteria andJobLogIdIsNotNull() {
            addCriterion("jobLogId is not null");
            return (Criteria) this;
        }

        public Criteria andJobLogIdEqualTo(String value) {
            addCriterion("jobLogId =", value, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdNotEqualTo(String value) {
            addCriterion("jobLogId <>", value, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdGreaterThan(String value) {
            addCriterion("jobLogId >", value, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdGreaterThanOrEqualTo(String value) {
            addCriterion("jobLogId >=", value, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdLessThan(String value) {
            addCriterion("jobLogId <", value, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdLessThanOrEqualTo(String value) {
            addCriterion("jobLogId <=", value, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdLike(String value) {
            addCriterion("jobLogId like", value, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdNotLike(String value) {
            addCriterion("jobLogId not like", value, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdIn(List<String> values) {
            addCriterion("jobLogId in", values, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdNotIn(List<String> values) {
            addCriterion("jobLogId not in", values, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdBetween(String value1, String value2) {
            addCriterion("jobLogId between", value1, value2, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andJobLogIdNotBetween(String value1, String value2) {
            addCriterion("jobLogId not between", value1, value2, "jobLogId");
            return (Criteria) this;
        }

        public Criteria andStepIsNull() {
            addCriterion("step is null");
            return (Criteria) this;
        }

        public Criteria andStepIsNotNull() {
            addCriterion("step is not null");
            return (Criteria) this;
        }

        public Criteria andStepEqualTo(Integer value) {
            addCriterion("step =", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepNotEqualTo(Integer value) {
            addCriterion("step <>", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepGreaterThan(Integer value) {
            addCriterion("step >", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepGreaterThanOrEqualTo(Integer value) {
            addCriterion("step >=", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepLessThan(Integer value) {
            addCriterion("step <", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepLessThanOrEqualTo(Integer value) {
            addCriterion("step <=", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepIn(List<Integer> values) {
            addCriterion("step in", values, "step");
            return (Criteria) this;
        }

        public Criteria andStepNotIn(List<Integer> values) {
            addCriterion("step not in", values, "step");
            return (Criteria) this;
        }

        public Criteria andStepBetween(Integer value1, Integer value2) {
            addCriterion("step between", value1, value2, "step");
            return (Criteria) this;
        }

        public Criteria andStepNotBetween(Integer value1, Integer value2) {
            addCriterion("step not between", value1, value2, "step");
            return (Criteria) this;
        }

        public Criteria andTotalStepIsNull() {
            addCriterion("totalStep is null");
            return (Criteria) this;
        }

        public Criteria andTotalStepIsNotNull() {
            addCriterion("totalStep is not null");
            return (Criteria) this;
        }

        public Criteria andTotalStepEqualTo(String value) {
            addCriterion("totalStep =", value, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepNotEqualTo(String value) {
            addCriterion("totalStep <>", value, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepGreaterThan(String value) {
            addCriterion("totalStep >", value, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepGreaterThanOrEqualTo(String value) {
            addCriterion("totalStep >=", value, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepLessThan(String value) {
            addCriterion("totalStep <", value, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepLessThanOrEqualTo(String value) {
            addCriterion("totalStep <=", value, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepLike(String value) {
            addCriterion("totalStep like", value, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepNotLike(String value) {
            addCriterion("totalStep not like", value, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepIn(List<String> values) {
            addCriterion("totalStep in", values, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepNotIn(List<String> values) {
            addCriterion("totalStep not in", values, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepBetween(String value1, String value2) {
            addCriterion("totalStep between", value1, value2, "totalStep");
            return (Criteria) this;
        }

        public Criteria andTotalStepNotBetween(String value1, String value2) {
            addCriterion("totalStep not between", value1, value2, "totalStep");
            return (Criteria) this;
        }

        public Criteria andStepStatusIsNull() {
            addCriterion("stepStatus is null");
            return (Criteria) this;
        }

        public Criteria andStepStatusIsNotNull() {
            addCriterion("stepStatus is not null");
            return (Criteria) this;
        }

        public Criteria andStepStatusEqualTo(String value) {
            addCriterion("stepStatus =", value, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusNotEqualTo(String value) {
            addCriterion("stepStatus <>", value, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusGreaterThan(String value) {
            addCriterion("stepStatus >", value, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusGreaterThanOrEqualTo(String value) {
            addCriterion("stepStatus >=", value, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusLessThan(String value) {
            addCriterion("stepStatus <", value, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusLessThanOrEqualTo(String value) {
            addCriterion("stepStatus <=", value, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusLike(String value) {
            addCriterion("stepStatus like", value, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusNotLike(String value) {
            addCriterion("stepStatus not like", value, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusIn(List<String> values) {
            addCriterion("stepStatus in", values, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusNotIn(List<String> values) {
            addCriterion("stepStatus not in", values, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusBetween(String value1, String value2) {
            addCriterion("stepStatus between", value1, value2, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andStepStatusNotBetween(String value1, String value2) {
            addCriterion("stepStatus not between", value1, value2, "stepStatus");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("jobName is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("jobName is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("jobName =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("jobName <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("jobName >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("jobName >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("jobName <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("jobName <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("jobName like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("jobName not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("jobName in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("jobName not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("jobName between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("jobName not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("startTime is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("startTime is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("startTime =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("startTime <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("startTime >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("startTime >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("startTime <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("startTime <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("startTime in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("startTime not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("startTime between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("startTime not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andUserIsNull() {
            addCriterion("user is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("user is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(String value) {
            addCriterion("user =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(String value) {
            addCriterion("user <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(String value) {
            addCriterion("user >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(String value) {
            addCriterion("user >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(String value) {
            addCriterion("user <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(String value) {
            addCriterion("user <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLike(String value) {
            addCriterion("user like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotLike(String value) {
            addCriterion("user not like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<String> values) {
            addCriterion("user in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<String> values) {
            addCriterion("user not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(String value1, String value2) {
            addCriterion("user between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(String value1, String value2) {
            addCriterion("user not between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andFinishIsNull() {
            addCriterion("finish is null");
            return (Criteria) this;
        }

        public Criteria andFinishIsNotNull() {
            addCriterion("finish is not null");
            return (Criteria) this;
        }

        public Criteria andFinishEqualTo(String value) {
            addCriterion("finish =", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishNotEqualTo(String value) {
            addCriterion("finish <>", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishGreaterThan(String value) {
            addCriterion("finish >", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishGreaterThanOrEqualTo(String value) {
            addCriterion("finish >=", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishLessThan(String value) {
            addCriterion("finish <", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishLessThanOrEqualTo(String value) {
            addCriterion("finish <=", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishLike(String value) {
            addCriterion("finish like", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishNotLike(String value) {
            addCriterion("finish not like", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishIn(List<String> values) {
            addCriterion("finish in", values, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishNotIn(List<String> values) {
            addCriterion("finish not in", values, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishBetween(String value1, String value2) {
            addCriterion("finish between", value1, value2, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishNotBetween(String value1, String value2) {
            addCriterion("finish not between", value1, value2, "finish");
            return (Criteria) this;
        }

        public Criteria andModeIsNull() {
            addCriterion("mode is null");
            return (Criteria) this;
        }

        public Criteria andModeIsNotNull() {
            addCriterion("mode is not null");
            return (Criteria) this;
        }

        public Criteria andModeEqualTo(String value) {
            addCriterion("mode =", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotEqualTo(String value) {
            addCriterion("mode <>", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThan(String value) {
            addCriterion("mode >", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThanOrEqualTo(String value) {
            addCriterion("mode >=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThan(String value) {
            addCriterion("mode <", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThanOrEqualTo(String value) {
            addCriterion("mode <=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLike(String value) {
            addCriterion("mode like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotLike(String value) {
            addCriterion("mode not like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeIn(List<String> values) {
            addCriterion("mode in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotIn(List<String> values) {
            addCriterion("mode not in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeBetween(String value1, String value2) {
            addCriterion("mode between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotBetween(String value1, String value2) {
            addCriterion("mode not between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andStep1IsNull() {
            addCriterion("step1 is null");
            return (Criteria) this;
        }

        public Criteria andStep1IsNotNull() {
            addCriterion("step1 is not null");
            return (Criteria) this;
        }

        public Criteria andStep1EqualTo(String value) {
            addCriterion("step1 =", value, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1NotEqualTo(String value) {
            addCriterion("step1 <>", value, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1GreaterThan(String value) {
            addCriterion("step1 >", value, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1GreaterThanOrEqualTo(String value) {
            addCriterion("step1 >=", value, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1LessThan(String value) {
            addCriterion("step1 <", value, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1LessThanOrEqualTo(String value) {
            addCriterion("step1 <=", value, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1Like(String value) {
            addCriterion("step1 like", value, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1NotLike(String value) {
            addCriterion("step1 not like", value, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1In(List<String> values) {
            addCriterion("step1 in", values, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1NotIn(List<String> values) {
            addCriterion("step1 not in", values, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1Between(String value1, String value2) {
            addCriterion("step1 between", value1, value2, "step1");
            return (Criteria) this;
        }

        public Criteria andStep1NotBetween(String value1, String value2) {
            addCriterion("step1 not between", value1, value2, "step1");
            return (Criteria) this;
        }

        public Criteria andStep2IsNull() {
            addCriterion("step2 is null");
            return (Criteria) this;
        }

        public Criteria andStep2IsNotNull() {
            addCriterion("step2 is not null");
            return (Criteria) this;
        }

        public Criteria andStep2EqualTo(String value) {
            addCriterion("step2 =", value, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2NotEqualTo(String value) {
            addCriterion("step2 <>", value, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2GreaterThan(String value) {
            addCriterion("step2 >", value, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2GreaterThanOrEqualTo(String value) {
            addCriterion("step2 >=", value, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2LessThan(String value) {
            addCriterion("step2 <", value, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2LessThanOrEqualTo(String value) {
            addCriterion("step2 <=", value, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2Like(String value) {
            addCriterion("step2 like", value, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2NotLike(String value) {
            addCriterion("step2 not like", value, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2In(List<String> values) {
            addCriterion("step2 in", values, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2NotIn(List<String> values) {
            addCriterion("step2 not in", values, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2Between(String value1, String value2) {
            addCriterion("step2 between", value1, value2, "step2");
            return (Criteria) this;
        }

        public Criteria andStep2NotBetween(String value1, String value2) {
            addCriterion("step2 not between", value1, value2, "step2");
            return (Criteria) this;
        }

        public Criteria andStep3IsNull() {
            addCriterion("step3 is null");
            return (Criteria) this;
        }

        public Criteria andStep3IsNotNull() {
            addCriterion("step3 is not null");
            return (Criteria) this;
        }

        public Criteria andStep3EqualTo(String value) {
            addCriterion("step3 =", value, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3NotEqualTo(String value) {
            addCriterion("step3 <>", value, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3GreaterThan(String value) {
            addCriterion("step3 >", value, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3GreaterThanOrEqualTo(String value) {
            addCriterion("step3 >=", value, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3LessThan(String value) {
            addCriterion("step3 <", value, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3LessThanOrEqualTo(String value) {
            addCriterion("step3 <=", value, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3Like(String value) {
            addCriterion("step3 like", value, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3NotLike(String value) {
            addCriterion("step3 not like", value, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3In(List<String> values) {
            addCriterion("step3 in", values, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3NotIn(List<String> values) {
            addCriterion("step3 not in", values, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3Between(String value1, String value2) {
            addCriterion("step3 between", value1, value2, "step3");
            return (Criteria) this;
        }

        public Criteria andStep3NotBetween(String value1, String value2) {
            addCriterion("step3 not between", value1, value2, "step3");
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