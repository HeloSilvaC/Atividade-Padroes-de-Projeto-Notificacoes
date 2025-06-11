// -----------------------------------------------------------------
// PARTE 2: REATORAÇÃO PARA FACTORY METHOD
// -----------------------------------------------------------------

/**
 * Creator (Abstrato): Declara o Factory Method, que retorna um objeto do tipo Product.
 */
abstract class MessageCreator {
    public abstract Message createMessage(String content);
}

/**
 * Concrete Creator 1: Sobrescreve o Factory Method para retornar um SimpleMessage.
 */
class SimpleMessageCreator extends MessageCreator {
    @Override
    public Message createMessage(String content) {
        return new SimpleMessage(content);
    }
}

/**
 * Concrete Creator 2: Sobrescreve o Factory Method para retornar um UrgentMessage.
 */
class UrgentMessageCreator extends MessageCreator {
    @Override
    public Message createMessage(String content) {
        return new UrgentMessage(content);
    }
}

/**
 * Concrete Creator 3: Sobrescreve o Factory Method para retornar um PromotionalMessage.
 */
class PromotionalMessageCreator extends MessageCreator {
    @Override
    public Message createMessage(String content) {
        return new PromotionalMessage(content);
    }
}

/**
 * Client (Refatorado): Agora usa os Creators para instanciar as mensagens.
 * As outras classes (Message, NotificationStrategy, NotificationService) permanecem as mesmas.
 */
public class SistemaNotificacaoFactoryMethod {
    public static void main(String[] args) {
        MessageCreator simpleCreator = new SimpleMessageCreator();
        MessageCreator urgentCreator = new UrgentMessageCreator();
        MessageCreator promoCreator = new PromotionalMessageCreator();

        Message welcomeMessage = simpleCreator.createMessage("Bem-vindo(a) ao nosso sistema!");
        Message alertMessage = urgentCreator.createMessage("Falha crítica detectada no servidor XYZ.");
        Message promoMessage = promoCreator.createMessage("Desconto de 20% em todos os produtos.");

        NotificationService notificationService = new NotificationService();

        // --- Teste com a Estratégia de Email ---
        System.out.println("--- [Factory Method] Usando Estratégia de Email ---");
        notificationService.setStrategy(new EmailNotificationStrategy());
        notificationService.sendNotification(welcomeMessage, "aluno@exemplo.com");
        notificationService.sendNotification(alertMessage, "admin@exemplo.com");
        notificationService.sendNotification(promoMessage, "cliente@exemplo.com");

        // --- Teste com a Estratégia de SMS ---
        System.out.println("--- [Factory Method] Mudando para Estratégia de SMS ---");
        notificationService.setStrategy(new SMSNotificationStrategy());
        notificationService.sendNotification(welcomeMessage, "+5511912345678");
        notificationService.sendNotification(alertMessage, "+5521987654321");
        notificationService.sendNotification(promoMessage, "+5544998765432");
    }
}