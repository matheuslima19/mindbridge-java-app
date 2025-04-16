# 📢 Pay Attention

**Pay Attention** é uma aplicação desenvolvida em **Java** com interface gráfica utilizando `JFrame`, com o objetivo de **monitorar o uso e funcionamento de computadores de alunos em ambientes escolares**. A aplicação fornece ao corpo docente a possibilidade de acompanhar em tempo real dados relevantes de cada máquina, ajudando a garantir o foco dos alunos durante o uso dos computadores.

O repositório original do projeto está hospedado na organização **MindBridge-INC** no GitHub: [https://github.com/MindBridge-INC](https://github.com/MindBridge-INC)

---

## 💡 Funcionalidades

- 📊 **Coleta de dados da máquina** (utilizando a biblioteca **Looca**):
  - Temperatura da CPU
  - Aplicativos abertos
  - Velocidade da rede
  - Uso de memória e processador

- 🔔 **Alerta de atenção**:
  - Exibe uma notificação ao aluno solicitando interação
  - Caso o aluno clique no botão, registra-se como **presente/atento**
  - Caso não haja resposta em tempo hábil, registra-se como **ausente**

- 🔐 **Login individual**:
  - Cada aluno possui um login único vinculado à sua matrícula

- 🛠️ **Captura de logs de erro**
- 📂 **Persistência de dados** em banco de dados
- ☁️ **Deploy em ambiente de produção na AWS com Docker**
  - Cluster de máquinas configurado
  - Script de instalação personalizado

---

## 🧰 Tecnologias utilizadas

- Java (Swing / JFrame)
- API Looca (https://github.com/Britooo/looca-api.git)
- MySQL (ou outro banco de dados relacional, se quiser especificar)
- Docker
- AWS (EC2)
- Script de instalação em shell script

---

## 🚀 Como executar o projeto

> Requisitos:
> - Java 8+
> - Docker
> - MySQL
> - Git

```bash
# Clone o repositório
git clone https://github.com/matheuslima19/mindbridge-java-app.git
cd pay-attention

# (Opcional) Execute o script de instalação
./instalador.sh

# Execute a aplicação
java -jar pay-attention.jar
```

---

## 👨‍💻 Desenvolvedores

Projeto desenvolvido por estudantes de Análise e Desenvolvimento de Sistemas da [nome da faculdade]:

- Matheus Santos de Lima - @matheuslima19
- Thaisa Nobrega Costa - @nobregathsa
- Luma Souza - @lumasouza
- Luiz Paulo Frutuoso - @lUIZ-PAULO-FRUTUOSO
- Julia Matos - @JuliaMatos09

---

## 📜 Licença

Este projeto é de uso acadêmico. Uso comercial e reprodução devem ser autorizados pelos autores.