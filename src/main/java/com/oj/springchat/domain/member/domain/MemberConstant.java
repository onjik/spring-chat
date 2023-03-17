package com.oj.springchat.domain.member.domain;

public final class MemberConstant {

    public final class Constraint{
        public static final String EMAIL_UNIQUE_VIOLATION = "EMAIL_UNIQUE";
        public static final String NICKNAME_UNIQUE_VIOLATION = "NICK_NAME_UNIQUE";
    }
    public final class ColumnName {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String FIRST_NAME = "first_name";
        public static final String MIDDLE_NAME = "middle_name";
        public static final String LAST_NAME = "last_name";
        public static final String NICKNAME = "nickname";
        public static final String ROLE = "role";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
    }
}
