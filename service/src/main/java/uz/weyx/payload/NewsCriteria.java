package uz.weyx.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsCriteria {
    private String key;
    private Object value;

}
