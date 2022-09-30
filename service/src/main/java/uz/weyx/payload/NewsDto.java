package uz.weyx.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private String authorName;

    private Set<Integer> tag;
}
