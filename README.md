# Estacionamento
Aplicação web de gerenciamento de estacionamento.

## Instalação Rápida

**1. Instalar Git**

**2. Instalar Docker**

**3. Instalar Docker compose**

**4. Clonar a aplicação**
```bash
git clone https://github.com/meloafc/estacionamento.git
```
**5. Navegar para a aplicação clonada**
```bash
cd estacionamento
```
**6. Executar o docker compose**
```bash
docker-compose up --build
```

O docker irá instalar e configurar todas as dependências necessárias para executar a aplicação, depois de alguns minutos a aplicação estará execultando em:

+ backend: <http://localhost:8080/swagger-ui.html>
+ frontend: <http://localhost:4200>

## Instalação Manual
### Requisitos backend

1. Java - 1.8.x
2. Maven - 3.x.x
3. Mysql - 5.x.x

### Etapas para a instalação

**1. Clonar a aplicação**

```bash
git clone https://github.com/meloafc/estacionamento.git
```

**2. Configurar o Mysql**
```bash
create database estacionamento
```

**3. Configurar usuário e senha do Mysql**

+ Abrir `src/main/resources/application.properties`

+ Trocar `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` por sua configuração.

**4. Executar o backend**

```bash
cd estacionamento/backend
mvn spring-boot:run
```

O backend começará a ser executado em <http://localhost:8080>.

### Requisitos frontend

1. Nodejs - 8.x.x
2. NPM - 5.x.x
3. Angular cli - 1.7.x

**1. Instalar as dependências do frontend**
```bash
cd ../frontend
npm install
```
**2. Executar o frontend**
```bash
ng serve
```

O frontend começará a ser executado em http://localhost:4200.

### Tecnologias utilizadas
+ Spring boot
+ Mysql
+ Angular
+ Bootstrap
+ Docker