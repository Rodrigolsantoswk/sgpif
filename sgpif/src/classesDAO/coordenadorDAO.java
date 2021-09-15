package classesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import Interfaces.InterfaceCoordenadorDAO;
import classes.coordenador;
import classes.area;
import classes.categoria;

public class coordenadorDAO implements InterfaceCoordenadorDAO {

    Connection con;

    @Override
    public List<coordenador> selecionarCoordenadores() {
        StringBuilder SqlBuilder = new StringBuilder();
        SqlBuilder.append("select SIAPE, nome, endereco, a.idArea, a.nomeArea, ca.idCategoria, ca.nomeCategoria from coordenador c")
                .append("left join area a")
                .append("on c.idArea = a.idArea")
                .append("left join categoria ca")
                .append("on c.idCategoria = ca.idCategoria");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<coordenador> cord = new ArrayList<>();
        
        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement(SqlBuilder.toString());
            rs = stmt.executeQuery();

            while (rs.next()) {
                coordenador c = new coordenador();
                area a = new area();
                categoria cat = new categoria();

                c.setSIAPE(rs.getInt("SIAPE"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));

                a.setIdArea(rs.getInt("idAra"));
                a.setNomeArea(rs.getString("nomeArea"));

                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setNomeCategoria(rs.getString("nomeCategoria"));

                c.setCategoria(cat);
                c.setArea(a);

                cord.add(c);

            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return cord;
    }

}
