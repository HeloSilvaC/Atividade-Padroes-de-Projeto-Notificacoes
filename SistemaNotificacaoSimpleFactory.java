// -----------------------------------------------------------------
// PARTE 1: IMPLEMENTAÇÃO COM SIMPLE FACTORY E STRATEGY
// -----------------------------------------------------------------

/**
 * Strategy Interface: Define a interface para os algoritmos de envio.
 */
interface NotificationStrategy {
    void send(Message message, String recipient);
}

/**
 * Concrete Strategy 1: Implementa o envio de notificação por Email.
 */
class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(Message message, String recipient) {
        System.out.println("Enviando Email para: " + recipient);
        System.out.println("Mensagem: " + message.format());
        System.out.println("Email enviado com sucesso.\n");
    }
}

/**
 * Concrete Strategy 2: Implementa o envio de notificação por SMS.
 */
class SMSNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(Message message, String recipient) {
        System.out.println("Enviando SMS para: " + recipient);
        System.out.println("Mensagem: " + message.format());
        System.out.println("SMS enviado com sucesso.\n");
    }
}

/**
 * Abstract Product: Classe base para os diferentes tipos de mensagem.
 */
abstract class Message {
    protected String content;

    public Message(String content) {
        this.content = content;
    }

    public abstract String format();

    public String getContent() {
        return content;
    }
}

/**
 * Concrete Product 1: Mensagem Simples.
 */
class SimpleMessage extends Message {
    public SimpleMessage(String content) {
        super(content);
    }

    @Override
    public String format() {
        return getContent();
    }
}

/**
 * Concrete Product 2: Mensagem Urgente.
 */
class UrgentMessage extends Message {
    public UrgentMessage(String content) {
        super(content);
    }

    @Override
    public String format() {
        return "[URGENTE] " + getContent();
    }
}

/**
 * Concrete Product 3: Mensagem Promocional.
 */
class PromotionalMessage extends Message {
    public PromotionalMessage(String content) {
        super(content);
    }

    @Override
    public String format() {
        return "[PROMOÇÃO] " + getContent() + " Aproveite!";
    }
}


/**
 * Simple Factory: Centraliza a criação de objetos de Mensagem.
 */
class MessageFactory {
    public static Message createMessage(String type, String content) {
        if ("SIMPLE".equalsIgnoreCase(type)) {
            return new SimpleMessage(content);
        } else if ("URGENT".equalsIgnoreCase(type)) {
            return new UrgentMessage(content);
        } else if ("PROMOTIONAL".equalsIgnoreCase(type)) {
            return new PromotionalMessage(content);
        }
        throw new IllegalArgumentException("Tipo de mensagem desconhecido: " + type);
    }
}

/**
 * Context: Utiliza um objeto Strategy para realizar uma operação.
 */
class NotificationService {
    private NotificationStrategy strategy;

    public NotificationService() {}

    public NotificationService(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void sendNotification(Message message, String recipient) {
        if (strategy == null) {
            System.out.println("Erro: Nenhuma estratégia de notificação foi definida.\n");
            return;
        }
        if (message == null) {
            System.out.println("Erro: Mensagem não pode ser nula.\n");
            return;
        }
        strategy.send(message, recipient);
    }
}


/**
 * Client: Utiliza o Contexto e as Estratégias.
 */
public class SistemaNotificacaoSimpleFactory {
    public static void main(String[] args) {
        Message welcomeMessage = MessageFactory.createMessage("SIMPLE", "Bem-vindo(a) ao nosso sistema!");
        Message alertMessage = MessageFactory.createMessage("URGENT", "Falha crítica detectada no servidor XYZ.");
        Message promoMessage = MessageFactory.createMessage("PROMOTIONAL", "Desconto de 20% em todos os produtos.");

        NotificationService notificationService = new NotificationService();

        // --- Teste com a Estratégia de Email ---
        System.out.println("--- Usando Estratégia de Email ---");
        notificationService.setStrategy(new EmailNotificationStrategy());
        notificationService.sendNotification(welcomeMessage, "aluno@exemplo.com");
        notificationService.sendNotification(alertMessage, "admin@exemplo.com");
        notificationService.sendNotification(promoMessage, "cliente@exemplo.com");

        // --- Teste com a Estratégia de SMS ---
        System.out.println("--- Mudando para Estratégia de SMS ---");
        notificationService.setStrategy(new SMSNotificationStrategy());
        notificationService.sendNotification(welcomeMessage, "+5511912345678");
        notificationService.sendNotification(alertMessage, "+5521987654321");
        notificationService.sendNotification(promoMessage, "+5544998765432");
    }
}