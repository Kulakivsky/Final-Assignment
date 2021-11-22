package com.example.demo.entity;

import com.example.demo.entity.services.InternetServiceDTO;
import com.example.demo.entity.services.PhoneServiceDTO;
import com.example.demo.entity.services.TvServiceDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

import static com.example.demo.security.ApplicationUserRole.STUDENT;

public class ApplicationUserDto implements UserDetails {


    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max =30, message = "Name should be between 2 and 30 characters")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    private String password;


    private int balanceId;
    //    TODO change default number later and delete DTO;
//    private InternetServiceDTO internetServiceDTO;
//    private PhoneServiceDTO phoneServiceDTO;
//    private TvServiceDTO tvServiceDTO;

    private int internetServiceId = 0;
    private int phoneServiceId = 0;
    private int tvServiceId = 0;

    private Set<? extends GrantedAuthority> grantedAuthorities = STUDENT.getGrantedAuthority(); //ADMIN.getGrantedAuthority()
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public ApplicationUserDto() {}

    public ApplicationUserDto(int id, String username,
                              String password, int internetServiceId, int phoneServiceId,
                              int tvServiceId, int balanceId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.internetServiceId = internetServiceId;
        this.phoneServiceId = phoneServiceId;
        this.tvServiceId = tvServiceId;
        this.balanceId = balanceId;
    }

    public int getInternetServiceId() {
        return internetServiceId;
    }

    public void setInternetServiceId(int internetServiceId) {
        this.internetServiceId = internetServiceId;
    }

    public int getPhoneServiceId() {
        return phoneServiceId;
    }

    public void setPhoneServiceId(int phoneServiceId) {
        this.phoneServiceId = phoneServiceId;
    }

    public int getTvServiceId() {
        return tvServiceId;
    }

    public void setTvServiceId(int tvServiceId) {
        this.tvServiceId = tvServiceId;
    }

    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balance_id) {
        this.balanceId = balance_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGrantedAuthorities(Set<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
