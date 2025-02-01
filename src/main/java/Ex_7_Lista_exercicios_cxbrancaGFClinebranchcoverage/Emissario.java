package Ex_7_Lista_exercicios_cxbrancaGFClinebranchcoverage;

import java.util.ArrayList;

public class Emissario {
    private UsuarioDAO usuarioDAO;
    private ServidorDeEmail servidorEmail;
    private Criptografia criptografia;

    public Emissario(UsuarioDAO usuarioDAO, ServidorDeEmail servidorEmail) {
        this.usuarioDAO = usuarioDAO;
        this.servidorEmail = servidorEmail;
    }
    public void setCriptografia(Criptografia criptografia) {
        this.criptografia = criptografia;
    }
    public String enviarPara(ArrayList<String> nomes) {
        if(nomes == null)
            return "nomes nao informados";

        ArrayList<Usuario> usuarios = usuarioDAO.getAllUsuarios();
        if(usuarios == null || usuarios.size() == 0)
            return "nao ha usuarios";

        boolean msgsEnviadas = false;
        for(String nome : nomes) {
            for (Usuario usuario : usuarios) {
                if(usuario.getNome().equals(nome)) {
                    String mensagem = criptografia.criptografar("mensagem secreta");
                    boolean foiEnviado = servidorEmail.enviar("TO: " + usuario.getEmail() +
                            " " + mensagem);
                    if(foiEnviado) {
                        msgsEnviadas = true;
                        break;
                    }
                    else
                        return "servidor de email offline";
                }
            }
        }

        if(msgsEnviadas)
            return "mensagens enviadas";
        else
            return "usuarios nao encontrados";
    }
}

