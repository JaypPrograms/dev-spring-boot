package com.luv2code.springdemo.mvc.model;

import com.luv2code.springdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;
    @NotNull(message="Is required")
    @Size(min = 1, message="is required")
    private String lastName;

    @Min(value=0, message="Must be greater than or equal to zero")
    @Max(value=10, message="Must be greater than or equal to ten")
    @NotNull(message="Is required")
    private Integer freePasses;

    @Pattern(regexp="^[a-zA-Z0-9]{5}", message="only 5 chars/digit")
    private String postalCode;

    @CourseCode(value = "abc")
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public Customer(){

    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
