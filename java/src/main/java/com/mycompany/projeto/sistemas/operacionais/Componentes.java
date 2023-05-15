package com.mycompany.projeto.sistemas.operacionais;

public class Componentes {
    //SistemaOperacional varchar(45), ModeloProcessador varchar(45), HostName varchar(45)), MemoriaTotal varchar(45), MemoriaArmazenamento varchar(45));"
    private Integer id;
    private String SistemaOperacional;
    private String ModeloProcessador;
    private String HostName;
    private String MemoriaTotal;
    private String MemoriaArmazenamento;
    private String EmailUsuario;

    public String getEmailUsuario() {
        return EmailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        EmailUsuario = emailUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Componentes(int id,String sistemaOperacional, String modeloProcessador, String hostName, String memoriaTotal, String memoriaArmazenamento) {
        id = id;
        SistemaOperacional = sistemaOperacional;
        ModeloProcessador = modeloProcessador;
        HostName = hostName;
        MemoriaTotal = memoriaTotal;
        MemoriaArmazenamento = memoriaArmazenamento;
    }
    public Componentes(){

    }

    public String getSistemaOperacional() {
        return SistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        SistemaOperacional = sistemaOperacional;
    }

    public String getModeloProcessador() {
        return ModeloProcessador;
    }

    public void setModeloProcessador(String modeloProcessador) {
        ModeloProcessador = modeloProcessador;
    }

    public String getHostName() {
        return HostName;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    public String getMemoriaTotal() {
        return MemoriaTotal;
    }

    public void setMemoriaTotal(String memoriaTotal) {
        MemoriaTotal = memoriaTotal;
    }

    public String getMemoriaArmazenamento() {
        return MemoriaArmazenamento;
    }

    public void setMemoriaArmazenamento(String memoriaArmazenamento) {
        MemoriaArmazenamento = memoriaArmazenamento;
    }

    @Override
    public String toString() {
        return "Componentes{" +
                "id=" + id +
                ", SistemaOperacional='" + SistemaOperacional + '\'' +
                ", ModeloProcessador='" + ModeloProcessador + '\'' +
                ", HostName='" + HostName + '\'' +
                ", MemoriaTotal='" + MemoriaTotal + '\'' +
                ", MemoriaArmazenamento='" + MemoriaArmazenamento + '\'' +
                ", EmailUsuario='" + EmailUsuario + '\'' +
                '}';
    }
}
