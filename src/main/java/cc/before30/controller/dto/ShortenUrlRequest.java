package cc.before30.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by before30 on 03/07/2017.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShortenUrlRequest {
    @NotNull
    @Size(min = 5, max = 1024)
    private  String url;
}
