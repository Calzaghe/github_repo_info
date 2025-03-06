package dev.pbania.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class GithubRepo {
    public String name;
    public GithubOwner owner;
    public boolean fork;
}
