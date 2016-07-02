package testing;

/**
 * 设置Email隐藏规则
 * <p/>
 * Created by Alexander on 2016/6/28.
 */
public class EmailHidden {
    public static void main(String[] args) {
        System.out.println("Mail alexander.lwz@vip.html.gmail.com is hidden to " + hideEmail
                ("alexander.lwz@vip.html.gmail.com"));
        System.out.println("Mail alexander.lwz@gmail.com is hidden to " + hideEmail("alexander" +
                ".lwz@gmail.com"));
        System.out.println("Mail alex@gmail.com is hidden to " + hideEmail("alex@gmail.com"));
        System.out.println("Mail al@gmail.com is hidden to " + hideEmail("al@gmail.com"));
        System.out.println("Mail a@gmail.com is hidden to " + hideEmail("a@gmail.com"));
    }

    public static String hideEmail(String mail) {
        StringBuilder stringBuilder = new StringBuilder();
        if (mail.lastIndexOf("@") > 0) {
            int index = mail.lastIndexOf("@");
            if (index == 1) {
                stringBuilder.append(mail.substring(0, 1));
                stringBuilder.append("***");
                stringBuilder.append(mail.substring(index));
            } else {
                stringBuilder.append(mail.substring(0, 1));
                stringBuilder.append("***");
                stringBuilder.append(mail.substring(index - 1));
            }
        } else {
            return mail;
        }
        String mailHidden = stringBuilder.toString();
        if (mailHidden.length() > 20) {
            return mailHidden.substring(0, 20) + "...";
        } else {
            return mailHidden;
        }
    }
}
