package com.ravilyahya.blogapp.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {


    private Long categoryId;
    @NotBlank
    @Size(min = 4,message = "Minimum size of category must be 4 characters")
    private String categoryTitle;

    @Size(min = 15,message = "Minimum size of category description must be 15 characters")
    private String categoryDescription;

}
