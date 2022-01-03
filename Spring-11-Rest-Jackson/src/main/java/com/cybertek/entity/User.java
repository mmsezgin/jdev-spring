package com.cybertek.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_account")
// Whenever FetchType.Lazy Spring automatically adds hibernateLazyInitializer
// Developers use below line when they used hibernateLazyInitializer
@JsonIgnoreProperties(value={"hibernateLazyInitializer"},ignoreUnknown = true)
public class User extends BaseEntity {

    private String email;
    // When I post (setter) a user, I should write password
    // When retrieving (getter) user, do not show me the password
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String username;

    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "account_details_id")
    @JsonManagedReference //when I call user, you do NOT need to show account. We can NOT use JsonIgnore here,it will break.
    // @JsonBackReference ve @JsonManagedReference comment out, it will enter into loop, account inside user, user inside account...gives stack over flow error.
    // Having @JsonManagedReference and @JsonManagedReference will break the loop
    private Account account;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
