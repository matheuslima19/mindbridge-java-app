package Classes;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class Telegram {
    public static String token = "6790194532:AAF7E-jpTB9DMPLcxEbHNfZHZdFxBRz3ct8";



    public static String getToken() {
        return token;
    }
    public  void enviarAlerta(String conteudo){
        TelegramBot bot = new TelegramBot("6790194532:AAF7E-jpTB9DMPLcxEbHNfZHZdFxBRz3ct8");
        SendResponse response = bot.execute(new SendMessage("6923776271", conteudo));
        SendResponse response2 = bot.execute(new SendMessage("1987877750", conteudo));
    }
}
