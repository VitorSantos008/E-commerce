# Sistema de E-commerce - CP4
## integrantes:

Vitor Julião Diogo dos Santos 
Guilherme Trajane da Silva
João Gustavo Sanches Silva
Fernando Paiva Coelho
Leandro do Nascimento Lemes

## link video
https://www.youtube.com/watch?v=EQCAoWMEBmI


## Descrição do Projeto

Este projeto consiste em um sistema de E-commerce desenvolvido em Java utilizando Programação Orientada a Objetos (POO), JDBC e PostgreSQL.

O sistema foi criado com o objetivo de aplicar conceitos fundamentais de desenvolvimento orientado a objetos e integração com banco de dados relacional, permitindo o gerenciamento de clientes, categorias, produtos e pedidos.

---

# Objetivos do Projeto

- Aplicar Programação Orientada a Objetos;
- Utilizar herança, abstração e polimorfismo;
- Implementar CRUD completo;
- Integrar Java com PostgreSQL utilizando JDBC;
- Aplicar o padrão DAO;
- Organizar o projeto em pacotes profissionais.

---

# Tecnologias Utilizadas

- Java
- PostgreSQL
- JDBC
- IntelliJ IDEA

---

# Conceitos Aplicados

## Abstração
A classe `Usuario` foi transformada em abstrata para servir como modelo genérico do sistema.

## Herança
As classes `Cliente` e `Administrador` herdam atributos e métodos da classe `Usuario`.

## Polimorfismo
O método `exibirTipo()` possui implementações diferentes em cada subclasse.

## Encapsulamento
Os atributos das classes utilizam modificadores `private` e `protected`, com acesso controlado por getters e setters.

## DAO
O padrão DAO foi utilizado para separar a lógica do banco de dados da lógica principal do sistema.

## CRUD
O sistema realiza:
- Create
- Read
- Update
- Delete

---

# Estrutura do Projeto

```text
src/
 ├─ dao/
 ├─ model/
 ├─ service/
 └─ util/
