package testing;

/**
 * Chinese Index Generator
 *
 * Created by Alexander on 2016/6/17.
 */
public class ChineseIndexGenerator {
    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println(new ChineseGenerator().getChinese(Integer.parseInt(args[0])));
        } else {
            for (int i = 0; i < 50; i++) {
                System.out.println(new ChineseGenerator().getChinese(i));
            }
        }
    }
}

class ChineseGenerator{
    private static String[] g = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static String ten = "十";
    private static String bai = "百";
    private static String qian = "千";

    public String getChinese(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int current = 0;
        int length = String.valueOf(num).length();
        boolean flag = false; // 连续拼零的标志位
        boolean needBreak = false; // 是否继续的标志位
        switch (length) {
            case 4:
                // 获取千位的数字
                current = num / 1000;
                // 拼接第四位
                stringBuilder.append(g[current]).append(qian);
            case 3:
                // 更新剩余数字
                num -= current * 1000;
                if (num == 0) {
                    // 如果剩余为0
                    break;
                }
                if (num % 100 == 0) {
                    needBreak = true;
                }
                // 获取百位的数字
                current = num / 100;
                // 如果为零，拼“零”，不为零则拼数字
                if (current != 0) {
                    stringBuilder.append(g[current]).append(bai);
                    flag = false;
                } else {
                    stringBuilder.append(g[0]);
                    flag = true;
                }
                if (needBreak) {
                    break;
                }
            case 2:
                // 更新剩余数字
                num -= current * 100;
                if (num == 0) {
                    // 如果剩余为0
                    break;
                }
                if (num % 10 == 0) {
                    needBreak = true;
                }
                // 获取十位数字
                current = num / 10;
                // 如果为零，拼“零”，不为零则拼数字
                if (length == 2 && current == 1) {
                    stringBuilder.append(ten);
                } else if (current != 0 ) {
                    stringBuilder.append(g[current]).append(ten);
                } else if (!flag) {
                    stringBuilder.append(g[0]);
                }
                if (needBreak) {
                    break;
                }
            case 1:
                num -= current * 10;
                current = num;
                if (num != 0) {
                    stringBuilder.append(g[current]);
                }
        }
        return stringBuilder.toString();
    }
}