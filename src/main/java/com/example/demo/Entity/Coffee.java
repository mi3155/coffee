package com.example.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="tbl_coffee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(unique = true)
    private String id;

    @Column
    private String password;

    @Column
    private String address;

    @Column
    private int phone;

    @Column
    private String auth;

    public Coffee(String id, String password, String address, int phone, String auth){
        this.id=id;
        this.password=password;
        this.address=address;
        this.phone=phone;
        this.auth=auth;
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet();
        for(String role : auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;
    }

    @Override
    public String getUsername() {
        return id;
    }

    public String getPassword(){
        return password;
    }
    @Override
    public boolean isAccountNonExpired() { //만료안되었냐
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //안잠겼냐
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //패스워드가 만료안됐냐
        return true;
    }

    @Override
    public boolean isEnabled() { // 사용가능하냐
        return true;
    }

}
