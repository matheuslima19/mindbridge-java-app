package LogErro;


import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;




public class Log {

    public void exibirLog(String s) {

        // criando um objeto Logger com o nome "MyLog".
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh = null;

        try {
            // Criando a pasta "logs" se ela não existir
            File logsFolder = new File("logs");
            if (!logsFolder.exists()) {
                if (logsFolder.mkdirs()) {
                    logger.info("Pasta 'logs' criada com sucesso");
                } else {
                    logger.warning("Falha ao criar a pasta 'logs'");
                }
            }

            // esta redirecionando o texto para a pasta
            fh = new FileHandler("logs\\log.txt", true);
            logger.addHandler(fh);

            // formatando a mensagem
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // Registrando a mensagem de log
            logger.info(s);


        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fh != null){
                fh.close();
            }
        }

    }
}