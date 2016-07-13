package com.emcsthai.builderpatten;

/**
 * Created by nakarin on 7/13/2016 AD.
 */
public class User {

    private final String firstName; // required
    private final String lastName; // required
    private final int age; // optional
    private final String phone; // optional
    private final String address; // optional

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Inner Builder Class
     */

    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;
        private String phone;
        private String address;

        public Builder() {
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(String age) {
            try {
                this.age = Integer.parseInt(age);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot Convert to Integer", new Throwable(EnumException.AGE));
            }
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder clear() {
            this.firstName("");
            this.lastName("");
            this.age("0");
            this.phone("");
            this.address("");
            return this;
        }

        public User build() {
            User user = new User(this);

            if (user.getAge() > 120) {
                throw new IllegalStateException("Age out of range", new Throwable(EnumException.AGE)); // thread-safe
            }

            if (user.getPhone() != null && !user.getPhone().startsWith("08")) {
                throw new IllegalStateException("Phone number format incorrect", new Throwable(EnumException.PHONE)); // thread-safe
            }

            return user;
        }
    }

    public static class EnumException {
        public static final String AGE = "age";
        public static final String PHONE = "phone";
    }

//    public static class UserException extends RuntimeException {
//        public UserException() {
//            super();
//        }
//
//        public UserException(String msg) {
//            super(msg);
//        }
//
//        public UserException(Throwable throwable) {
//            super(throwable);
//        }
//
//        public UserException(String detailMessage, Throwable throwable) {
//            super(detailMessage, throwable);
//        }
//    }

//    public enum EnumUser {
//        AGE("Age", 0), PHONE("Phone", 1);
//
//        private String stringValue;
//        private int intValue;
//
//        private EnumUser(String toString, int value) {
//            stringValue = toString;
//            intValue = value;
//        }
//
//        @Override
//        public String toString() {
//            return stringValue;
//        }
//    }
}
