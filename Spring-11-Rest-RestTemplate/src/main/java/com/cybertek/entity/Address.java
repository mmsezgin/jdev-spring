package com.cybertek.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;

    private String street;
    private String suite;
    private String city;
    private String zipcode;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private User user;
}

// SAMPLE USER from https://jsonplaceholder.typicode.com/users
//        {
//        "id": 1,
//        "name": "Leanne Graham",
//        "username": "Bret",
//        "email": "Sincere@april.biz",
//        "address": {
//        "street": "Kulas Light",
//        "suite": "Apt. 556",
//        "city": "Gwenborough",
//        "zipcode": "92998-3874",
//        "geo": {
//        "lat": "-37.3159",
//        "lng": "81.1496"
//        }
//        },
//        "phone": "1-770-736-8031 x56442",
//        "website": "hildegard.org",
//        "company": {
//        "name": "Romaguera-Crona",
//        "catchPhrase": "Multi-layered client-server neural-net",
//        "bs": "harness real-time e-markets"
//        }
//        },
