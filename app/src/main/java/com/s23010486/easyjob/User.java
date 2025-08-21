package com.s23010486.easyjob;

public class User {
    private String username;
    private String email;
    private String name;
    private String dob;
    private String gender;
    private String mobile;
    private String address;
    private String bio;

    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(String username, String email, String name, String dob,
                String gender, String mobile, String address, String bio) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.mobile = mobile;
        this.address = address;
        this.bio = bio;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}