package uz.weyx.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }
}
