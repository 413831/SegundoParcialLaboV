package utn.sistema.segundo_parcial.POJOs;

import java.util.Objects;

public class User
{
    private Integer id;
    private String username;
    private String rol;
    private Boolean admin;

    public User(){}

    public User(Integer id, String username, String rol, Boolean admin)
    {
        this.id = id;
        this.username = username;
        this.rol = rol;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("'id'=").append(id);
        sb.append(", \"username\"='").append(username).append('\'');
        sb.append(", \"rol\"='").append(rol).append('\'');
        sb.append(", \"admin\"=").append(admin);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getRol(), user.getRol()) && Objects.equals(getAdmin(), user.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getRol(), getAdmin());
    }
}
