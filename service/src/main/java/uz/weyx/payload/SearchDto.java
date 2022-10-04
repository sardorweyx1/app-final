package uz.weyx.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDto {

    Set<Integer> tagsId;

    Set<String> tagNames;

    String authorName;

    String partOfTitle;

    String partOfContent;
}
