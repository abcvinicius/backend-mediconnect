
# backend-mediconnect

### 1. O que é o Mediconnect?
Mediconnect é um sistema de agendamento e gerenciamento de consultas médicas. Ele permite que clientes agendem consultas, médicos registrem informações, e haja controle sobre as finanças e medicamentos prescritos. O projeto foi desenvolvido usando a arquitetura **MVC (Model-View-Controller)**, que organiza o código de uma forma que separa a lógica dos dados e da interface, tornando o código mais limpo e fácil de manter.

## 2. O que é MVC?
O **MVC** é um padrão de arquitetura que divide a aplicação em três partes principais:
- **Model (Modelo)**: É onde ficam os dados da aplicação. Pense nos "Modelos" como sendo a parte da sua aplicação que define **o que é** cada coisa. Por exemplo, um modelo de "Usuário" diz que um usuário tem nome, CPF, email, etc. Ele define **como os dados são armazenados e organizados**.
  
- **View (Visualização)**: É a parte que mostra os dados para o usuário. Na nossa API, como estamos falando de um backend, a "View" é basicamente a resposta que enviamos ao cliente, como JSONs que o sistema da pessoa que está acessando irá consumir.

- **Controller (Controlador)**: É a parte que decide **o que fazer** com os dados. Ele recebe a requisição do usuário (por exemplo, "quero ver a lista de médicos"), pede ao **Model** para buscar os dados no banco de dados e então envia esses dados como resposta.

## 3. Explicando cada parte como se todo mundo fosse MUITO burro:

### 3.1. Model (Modelo)
Imagine o **Model** como uma "receita de bolo". Ele define tudo que faz parte de uma entidade no sistema, como um **cliente** ou um **médico**. Por exemplo, pense no **Cliente** como um formulário que você deve preencher para criar um cadastro no sistema. Esse formulário pede informações como:
- Nome
- CPF
- Data de nascimento
- Endereço

Então o **Model** vai ser algo como um formulário que diz o que um cliente ou médico deve ter.

### 3.2. Controller (Controlador)
Agora, vamos falar do **Controller**. Pense no **Controller** como sendo a "pessoa" que leva os dados de um lado para outro. Quando você pede alguma coisa no sistema (por exemplo, "quero ver todos os médicos"), o **Controller** recebe esse pedido e fala: "Ok, vamos ver o que eu faço com isso." Então ele pega essa informação, vai até o **Model** para perguntar pelos dados e depois traz a resposta para você.

Resumindo: o **Controller** faz a ponte entre o seu pedido e os dados do sistema. Se o usuário pede algo, o **Controller** busca no lugar certo (geralmente no banco de dados).

### 3.3. Repository (Repositório)
O **Repository** é como se fosse o "armário" onde os dados ficam guardados. Quando o **Controller** pede algo, é o **Repository** que realmente vai "abrir o armário" e pegar a informação. Ele faz as operações com o banco de dados, como **inserir**, **atualizar**, **deletar** ou **buscar** dados.

### 3.4. Service (Serviço)
O **Service** é a "inteligência" do sistema. Ele contém as **regras de negócio**, ou seja, o que pode e o que não pode ser feito. Por exemplo, se alguém quiser marcar uma consulta em uma data já ocupada, o **Service** vai ser responsável por dizer: "Espera aí, essa data já está ocupada!". Então, ele ajuda a garantir que tudo no sistema funcione corretamente.

## 4. Estrutura de Pastas do Projeto Mediconnect

```
[ src/main/java/com.mediconnect ]
   |
   |-- [ Controller ]
   |       |
   |       |-- [ EndPoints ]
   |       |       |-- ClienteController.java
   |       |       |-- MedicoController.java
   |       |       |-- AgendamentoController.java
   |       |       |-- ConsultaController.java
   |       |       |-- FinanceiroController.java
   |       |
   |       |-- [ Request ]
   |       |       |-- ClienteRequest.java
   |       |       |-- MedicoRequest.java
   |       |       |-- AgendamentoRequest.java
   |       |       |-- ConsultaRequest.java
   |       |       |-- FinanceiroRequest.java
   |       |
   |       |-- [ Response ]
   |               |-- ClienteResponse.java
   |               |-- MedicoResponse.java
   |               |-- AgendamentoResponse.java
   |               |-- ConsultaResponse.java
   |               |-- FinanceiroResponse.java
   |
   |-- [ Model ]
   |       |-- Cliente.java
   |       |-- Medico.java
   |       |-- Agendamento.java
   |       |-- Consulta.java
   |       |-- Financeiro.java
   |
   |-- [ Repository ]
   |       |-- ClienteRepository.java
   |       |-- MedicoRepository.java
   |       |-- AgendamentoRepository.java
   |       |-- ConsultaRepository.java
   |       |-- FinanceiroRepository.java
   |
   |-- [ Service ]
   |       |-- ClienteService.java
   |       |-- MedicoService.java
   |       |-- AgendamentoService.java
   |       |-- ConsultaService.java
   |       |-- FinanceiroService.java
   |
   |-- [ Security ]
   |       |-- JWTAuthenticationFilter.java
   |       |-- UsuarioService.java
   |
   |-- [ Utils ]
   |       |-- ClienteBuilder.java
   |       |-- MedicoBuilder.java
   |       |-- AgendamentoBuilder.java
   |       |-- ConsultaBuilder.java
   |       |-- FinanceiroBuilder.java
   |       
   |-- [ ProjetoMedicoApplication.java ]
```

## 5. Explicando a Estrutura de Pastas

1. **Controller**:
    - Os **Controllers** são como "os chefes". Eles recebem os pedidos dos usuários (como: "me mostre todos os médicos"), e então eles sabem a quem perguntar. Eles passam a pergunta para o **Service** ou para o **Repository**.

2. **Request e Response**:
    - O **Request** é o pedido que você manda para o sistema. É como uma lista de informações que o sistema precisa receber (por exemplo: nome, email, CPF). 
    - O **Response** é o que o sistema te devolve depois que ele processa o pedido. Como quando você pede para ver um cliente, ele te devolve as informações desse cliente.

3. **Model**:
    - Aqui é onde estão definidos todos os dados do sistema. Cada **Model** diz o que cada coisa tem. Por exemplo, o **Model** de "Cliente" diz que um cliente tem nome, CPF, data de nascimento, etc.

4. **Repository**:
    - O **Repository** é onde estão guardadas as informações, e ele sabe como acessar o banco de dados para **buscar, salvar, ou deletar** os dados.

5. **Service**:
    - O **Service** é quem sabe as regras do sistema. Ele verifica coisas como: "Você pode marcar consulta nesse horário?" ou "Esse cliente já existe?". Ele garante que as regras do negócio sejam seguidas corretamente.

6. **Security**:
    - A parte de **Security** cuida da autenticação e autorização. Ela garante que apenas usuários autorizados possam acessar certas partes do sistema. Usamos **JWT** (JSON Web Token) para autenticação, e as classes como **JWTAuthenticationFilter** e **UsuarioService** cuidam de proteger as rotas da aplicação. O **UsuarioService** é responsável por validar e gerenciar os usuários que estão tentando se autenticar.

7. **Utils**:
    - A pasta **Utils** contém arquivos auxiliares, como os **Builders**. Eles ajudam a construir objetos de forma mais limpa e estruturada, facilitando a criação de instâncias de **Cliente**, **Medico**, entre outros, a partir de parâmetros e valores predefinidos.

## 6. Fluxo do Pedido na API (Explicação Simples)

Imagine que você quer agendar uma consulta. O que acontece?

1. **Você faz o pedido** para agendar a consulta (esse pedido vai para o **Controller**).
2. O **Controller** pega esse pedido e fala com o **Service**: "Esse horário está disponível?"
3. O **Service** checa as regras e diz: "Sim, pode marcar!" (ou "Não, esse horário já está ocupado").
4. O **Service** pede ao **Repository** para salvar os dados da consulta no banco de dados.
5. O **Repository** salva tudo e retorna a confirmação para o **Service**.
6. O **Service** informa o **Controller**, e o **Controller** devolve a resposta para você: "Consulta marcada com sucesso!"

## Conclusão
Esse guia do **Mediconnect** explica cada parte do projeto, desde o **Model** (que define os dados), até o **Controller** (que coordena as ações). Mesmo se alguém não souber nada de programação, a ideia aqui é entender como as partes do sistema se conectam e interagem. O **Mediconnect** segue o padrão **MVC**, que organiza o código de forma lógica e eficiente, tornando o desenvolvimento e a manutenção muito mais simples!
