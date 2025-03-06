package dev.pbania.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class GithubBranch {
    public String name;
    public Commit commit;
}
