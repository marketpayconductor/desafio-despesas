package com.jarddel.desafio.api.application.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("desafio")
public class ApplicationProperty {

    private Seguranca seguranca = new Seguranca();
    private ClientOauth clientOauthAngular = new ClientOauth();
    private ClientOauth clientOauthMobile = new ClientOauth();

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(Seguranca seguranca) {
        this.seguranca = seguranca;
    }

    public ClientOauth getClientOauthAngular() {
        return clientOauthAngular;
    }

    public void setClientOauthAngular(ClientOauth clientOauthAngular) {
        this.clientOauthAngular = clientOauthAngular;
    }

    public ClientOauth getClientOauthMobile() {
        return clientOauthMobile;
    }

    public void setClientOauthMobile(ClientOauth clientOauthMobile) {
        this.clientOauthMobile = clientOauthMobile;
    }

    public static class Seguranca {

        private String origemPermitida;
        private boolean habilitarHttps;

        public String getOrigemPermitida() {
            return origemPermitida;
        }

        public void setOrigemPermitida(String origemPermitida) {
            this.origemPermitida = origemPermitida;
        }

        public Boolean isHabilitarHttps() {
            return habilitarHttps;
        }

        public void setHabilitarHttps(Boolean habilitarHttps) {
            this.habilitarHttps = habilitarHttps;
        }

    }

    public static class ClientOauth {
        private String cliente;
        private String segredo;
        private String[] escopos;
        private String[] concessoes;
        
        public String getCliente() {
            return cliente;
        }

        public void setCliente(String cliente) {
            this.cliente = cliente;
        }

        public String getSegredo() {
            return segredo;
        }

        public void setSegredo(String segredo) {
            this.segredo = segredo;
        }

        public String[] getEscopos() {
            return escopos;
        }

        public void setEscopos(String[] escopos) {
            this.escopos = escopos;
        }

        public String[] getConcessoes() {
            return concessoes;
        }

        public void setConcessoes(String[] concessoes) {
            this.concessoes = concessoes;
        }
    }
}
