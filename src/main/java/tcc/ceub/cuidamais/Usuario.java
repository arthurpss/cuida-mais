package tcc.ceub.cuidamais;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tcc.ceub.cuidamais.dto.LoginDTO;

import java.util.Collection;
import java.util.List;

public class Usuario implements UserDetails {

    private String username;
    private String senha;
    private boolean ativo;

    public Usuario(LoginDTO login) {
        this.username = login.getCpf_cnpj();
        this.senha = login.getSenha();
        this.ativo = login.isAtivo();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}