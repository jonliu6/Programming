package org.freecode.demo.model;

import javax.validation.constraints.*;

public class RegistrationFormObject {
    @NotNull
    @Min(value=1000, message="User ID must be >= 1000")
    private Long id;

    @NotNull
    @Size(min=5, max=50)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    private String gender;

    @NotNull
    private String profession;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]{2,255}", message = "please only enter letters and numbers and white spaces")
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
