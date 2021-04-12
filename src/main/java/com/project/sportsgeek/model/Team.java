package com.project.sportsgeek.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.sportsgeek.annotations.QueryHelperColumnName;
import com.project.sportsgeek.annotations.QueryHelperPrimaryKey;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Team Model")
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {
    @QueryHelperColumnName(name = "TeamId")
    @QueryHelperPrimaryKey
    private int teamId;
    @NotNull
    private String name;
    @NotNull
    private String teamShortName;
    private String teamLogo;
}
