package uz.weyx.payload;

import javax.validation.constraints.NotBlank;


public class TagDto {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }
}
