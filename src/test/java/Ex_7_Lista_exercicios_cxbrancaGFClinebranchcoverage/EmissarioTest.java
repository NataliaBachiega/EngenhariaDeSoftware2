package Ex_7_Lista_exercicios_cxbrancaGFClinebranchcoverage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmissarioTest {

    Emissario emissario;
    UsuarioDAO usuarioDAOMock = mock(UsuarioDAO.class);
    ServidorDeEmail servidorDeEmailMock = mock(ServidorDeEmail.class);

    @BeforeEach
    void setUp() {

        emissario = new Emissario(usuarioDAOMock, servidorDeEmailMock);

    }

    @Test
    void nomesNaoInformados() {

        assertEquals("nomes nao informados", emissario.enviarPara(null));

    }
    @Test
    void semUsuariosNulo(){

        when(usuarioDAOMock.getAllUsuarios()).thenReturn(null);

        var nomes = new ArrayList<String>();

        nomes.add("José");

        assertEquals("nao ha usuarios", emissario.enviarPara(nomes));
    }

    @Test
    void semUsuariosListaVazia(){

        when(usuarioDAOMock.getAllUsuarios()).thenReturn(new ArrayList<>());

        var nomes = new ArrayList<String>();
        nomes.add("José");

        assertEquals("nao ha usuarios", emissario.enviarPara(nomes));
    }

    @Test
    void servidorOffline(){

        var nomes = new ArrayList<String>();

        nomes.add("Maria");
        nomes.add("Paulo");

        Usuario usuarioMock = mock(Usuario.class);
        Criptografia criptografiaMock = mock(Criptografia.class);
        emissario.setCriptografia(criptografiaMock);

        var usuarios = new ArrayList<Usuario>();
        usuarios.add(usuarioMock);

        when(usuarioMock.getEmail()).thenReturn("maria@gmail.com");
        when(usuarioMock.getNome()).thenReturn("Maria");

        when(usuarioDAOMock.getAllUsuarios()).thenReturn(usuarios);
        when(criptografiaMock.criptografar("mensagem secreta")).thenReturn("mensagem a ser enviada");
        when(servidorDeEmailMock.enviar("TO: @gmail.com mensagem a ser enviada")).thenReturn(true);


        var resultado = emissario.enviarPara(nomes);

        assertEquals("servidor de email offline", resultado);
    }

    @Test
    void mensagemEnviada(){
        var nomes = new ArrayList<String>();

        nomes.add("Maria");
        nomes.add("Paulo");

        Usuario usuarioMock = mock(Usuario.class);
        Criptografia criptografiaMock = mock(Criptografia.class);
        emissario.setCriptografia(criptografiaMock);

        var usuarios = new ArrayList<Usuario>();
        usuarios.add(usuarioMock);

        when(usuarioMock.getEmail()).thenReturn("maria@gmail.com");
        when(usuarioMock.getNome()).thenReturn("Maria");

        when(usuarioDAOMock.getAllUsuarios()).thenReturn(usuarios);
        when(criptografiaMock.criptografar("mensagem secreta")).thenReturn("mensagem a ser enviada");
        when(servidorDeEmailMock.enviar("TO: maria@gmail.com mensagem a ser enviada")).thenReturn(true);


        var resultado = emissario.enviarPara(nomes);

        assertEquals("mensagens enviadas", resultado);
    }

    @Test
    void mensagemNaoEnviada(){

        var nomes = new ArrayList<String>();

        nomes.add("João");
        nomes.add("Paulo");

        Usuario usuarioMock = mock(Usuario.class);
        Criptografia criptografiaMock = mock(Criptografia.class);
        emissario.setCriptografia(criptografiaMock);

        var usuarios = new ArrayList<Usuario>();
        usuarios.add(usuarioMock);

        when(usuarioMock.getEmail()).thenReturn("maria@gmail.com");
        when(usuarioMock.getNome()).thenReturn("Maria");

        when(usuarioDAOMock.getAllUsuarios()).thenReturn(usuarios);
        when(criptografiaMock.criptografar("mensagem secreta")).thenReturn("mensagem a ser enviada");
        when(servidorDeEmailMock.enviar("TO: maria@gmail.com mensagem a ser enviada")).thenReturn(true);


        var resultado = emissario.enviarPara(nomes);

        assertEquals("usuarios nao encontrados", resultado);
    }
}