package com.ravilyahya.blogapp.payloads;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {



    private Long id;

    @NotEmpty
    @Size(min = 4,message = "Username must contain at least 4 characters")
    private String name;
    @Email(message = "Email address is not valid!")
    private String email;
    @NotEmpty
    @Size(min = 4,max = 12,message = "Password must contain at least 4 and maximum 12 characters!")
    private String password;
    @NotNull
    private String about;
}
