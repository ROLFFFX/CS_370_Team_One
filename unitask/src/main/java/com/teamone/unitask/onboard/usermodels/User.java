package com.teamone.unitask.onboard.usermodels;

import com.teamone.unitask.onboard.confirmationtoken.ConfirmationToken;
import com.teamone.unitask.onboard.usermodels.Role;
import com.teamone.unitask.projects.Project;
import com.teamone.unitask.tasks.Task;
import com.teamone.unitask.timeslots.TimeSlot;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    /**
     * fields
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private boolean enabled;

    /**
     * foreign keys
     */

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_projects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projectsJoined = new ArrayList<>();

    /**
     * mapped by
     */
    @OneToMany(mappedBy = "user")
    private Collection<ConfirmationToken> confirmationTokens;

    @OneToMany(mappedBy = "masterUserId")
    private Collection<Project> mastered_projects;

    @OneToMany(mappedBy = "userAssigned")
    private Collection<TimeSlot> has_timeslots;

    @OneToMany(mappedBy = "taskMemberAssigned")
    private Collection<Task> tasks;


    /**
     * methods
     */

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Project> getProjectsJoined() {
        return projectsJoined;
    }

    public void setProjectsJoined(List<Project> projectsJoined) {
        this.projectsJoined = projectsJoined;
    }

    public Collection<ConfirmationToken> getConfirmationTokens() {
        return confirmationTokens;
    }

    public void setConfirmationTokens(Collection<ConfirmationToken> confirmationTokens) {
        this.confirmationTokens = confirmationTokens;
    }

    public Collection<Project> getMastered_projects() {
        return mastered_projects;
    }

    public void setMastered_projects(Collection<Project> mastered_projects) {
        this.mastered_projects = mastered_projects;
    }

    public Collection<TimeSlot> getHas_timeslots() {
        return has_timeslots;
    }

    public void setHas_timeslots(Collection<TimeSlot> has_timeslots) {
        this.has_timeslots = has_timeslots;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }
}
