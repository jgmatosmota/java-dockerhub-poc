package com.mycompany.projeto.sistemas.operacionais;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeInterfaceGroup;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.util.*;

public class ComponentesMaquina {
    Looca looca = new Looca();
    Sistema objtSistema = new Sistema();
    Processador objtProcessador = new Processador();
    Memoria objtMemoria = new Memoria();
    DiscoGrupo objtDiscoGrupo = looca.getGrupoDeDiscos();
    List<Disco> discos = objtDiscoGrupo.getDiscos();
    Disco discoA = discos.get(0);
    Long memoriaArmazenamento = discoA.getTamanho();

    Rede rede = looca.getRede();
    RedeInterfaceGroup gruposDeInterface = rede.getGrupoDeInterfaces();
    List<RedeInterface> interfaces = gruposDeInterface.getInterfaces();
    List<RedeInterface> ListRedesComDados = interfaces.stream().filter(
            rede -> rede.getBytesEnviados() > 0 && rede.getBytesRecebidos() > 0).toList();
    RedeInterface redeDaVez = ListRedesComDados.get(0);
    String hostName = redeDaVez.getEnderecoMac();

    String sistemaOperacional = objtSistema.getSistemaOperacional();
    String modeloProcessador = objtProcessador.getNome();


    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public String getModeloProcessador() {
        return modeloProcessador;
    }

    public String getHostName() {
        return hostName;
    }
    //SistemaOperacional varchar(45),
    // ModeloProcessador varchar(45),
    // HostName varchar(45)),
    // MemoriaTotal varchar(45),
    // MemoriaArmazenamento varchar(45));"
    private static Double byteConverter(long bytes){
        return (double) bytes /(1024 * 1024 * 1024);
    }
    public String getMemoriaTotal() {
        Long memoriaTotal = objtMemoria.getTotal();
        Double memoriaTotalDouble = byteConverter(memoriaTotal);
        return Double.toString(memoriaTotalDouble);
    }

    public String getMemoriaArmazenamento() {
        Double memoriaSsd = byteConverter(memoriaArmazenamento);
        return Double.toString(memoriaSsd);

    }
}
