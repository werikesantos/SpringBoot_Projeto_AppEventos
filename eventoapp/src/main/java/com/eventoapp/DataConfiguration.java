package com.eventoapp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

//CLASSE RESPONSÁVEL PELA CONFIGURAÇÃO DE CONEXÃO COM O BANCO DE DADOS E MAPEAMENTO DO HIBERNATE 

@Configuration
public class DataConfiguration {
	
	@Bean
    public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/eventoapp?useTimezone=true&serverTimezone=UTC");
	    dataSource.setUsername("root");
	    dataSource.setPassword("wms@wms");
	    return dataSource;
	}
	
	//CONFIGURAÇÃO DO HIBERNATE
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL); // Database.MYSQL => DEFINI QUAL BANCO DE DADOS SERÁ UTILIZADO
		adapter.setShowSql(true); //setShowSql(true) => TODA EXECUÇÃO DO BANCO SERÁ EXIBIDA NO CONSOLE
		adapter.setGenerateDdl(true); //setGenerateDdl(true) => O HIBERNETE CRIA AS TABELAS AUTOMATICAMENTE
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		adapter.setPrepareConnection(true); //setGenerateDdl(true) => HIBERNETE PREPARA A CONEXÃO AUTOMATICAMENTE
		return adapter;
	}
}