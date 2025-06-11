# Atividade Prática: Sistema de Notificações com Padrões de Projeto

## Descrição da Atividade

Este projeto, desenvolvido como atividade para a disciplina de Engenharia de Software do curso de Ciência da Computação (UTFPR), implementa um sistema de envio de notificações flexível e extensível em Java. O objetivo principal é demonstrar a aplicação prática dos padrões de projeto **Strategy**, **Simple Factory** e **Factory Method** para promover a alta coesão, o baixo acoplamento e a manutenibilidade do código.

O sistema permite:
* A fácil adição de novos **canais de envio** (Email, SMS).
* A criação simplificada de diferentes **tipos de mensagem** (Simples, Urgente, Promocional).
* A troca dinâmica da estratégia de envio em tempo de execução.

---

## Padrões de Projeto Utilizados

* **Strategy**: Utilizado para encapsular os diferentes algoritmos de envio de notificação (Email, SMS). A classe `NotificationService` (Contexto) utiliza uma `NotificationStrategy` para realizar o envio, permitindo que o cliente altere o método de envio dinamicamente.

* **Simple Factory**: Na primeira versão (`SistemaNotificacaoSimpleFactory.java`), este padrão é usado para centralizar e simplificar a criação de objetos `Message`, desacoplando o cliente da lógica de instanciação.

* **Factory Method**: Na segunda versão (`SistemaNotificacaoFactoryMethod.java`), o Simple Factory é substituído por este padrão GoF. Uma hierarquia de "Creators" (`MessageCreator`) permite que as subclasses decidam qual tipo de `Message` instanciar, tornando o sistema ainda mais extensível.

---

##  Estrutura dos Arquivos

* `SistemaNotificacaoSimpleFactory.java`: Contém a implementação inicial completa com os padrões **Strategy** e **Simple Factory**.
* `SistemaNotificacaoFactoryMethod.java`: Contém a implementação refatorada, substituindo o Simple Factory pelo padrão **Factory Method**.
