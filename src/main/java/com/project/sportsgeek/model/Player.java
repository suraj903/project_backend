package com.project.sportsgeek.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.sportsgeek.annotations.QueryHelperColumnName;
import com.project.sportsgeek.annotations.QueryHelperPrimaryKey;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Venue Model")
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player implements Serializable {
    @QueryHelperColumnName(name = "PlayerId")
    @QueryHelperPrimaryKey
    private int playerId;
    @NotNull
    private int teamId;
    @NotNull
    private String name;
    @NotNull
    private int typeId;
    private String profilePicture;
}
