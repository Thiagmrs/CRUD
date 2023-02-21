package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD {
    private String sql = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con;
    private Usuario usuario;

    public CRUD(){
        con = new Conexao().getConexao();

    }
    public boolean Create(Usuario usuario) throws SQLException{
        sql = "insert intro usuario(usuario,cpf,edereco,telefone,email,senha,cidade) values(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.GetCpf());
            ps.setString(3, usuario.getEndereco());
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getSenha());
            ps.setString(7, usuario.getCidade());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            con.close();
            ps.close();
        }
        return false;
    }

    public Usuario Read(String consulta)throws SQLException{
        sql = "select *from usuario where usuario=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, consulta);
            rs = ps.executeQuery();
            Usuario usuario = new Usuario();
            while (rs.next()){
                usuario.setNome(rs.getString("usuario"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenhha(rs.getString("senha"));
                usuario.setCidade(rs.getString("cidade"));
            }
            return usuario;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            con.close();
            ps.close();
        }
        return null;
    }


    public boolean Update(String consulta, Usuario usuario)throws SQLException{
        sql = "update usuario set usuario=?, endereco=?, telefone=?, senha=?, cidade=? where usuario=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.GetCpf());
            ps.setString(3, usuario.getEndereco());
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getSenha());
            ps.setString(7, usuario.getCidade());
            ps.setString(8, consulta);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            con.close();
            ps.close();
        }
        return false;
    }


    public boolean delete(String cpf) throws SQLException{
        sql = "delete from usuario where cpf=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            ps.close();
            con.close();
        }
          return false;
    }

}
