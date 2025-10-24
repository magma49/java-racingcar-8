package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.Vector;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        System.out.println("시도할 횟수는 몇 회인가요?");
        String numstr = Console.readLine();

        String[] cars = input.split(",");
        for (String car : cars) {
            if (car.length() == 0 || car.length() > 5)
                throw new IllegalArgumentException("잘못된 입력");
        }

        int num;
        try {
            num = Integer.parseInt(numstr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력");
        }

        int len = cars.length;
        int[] count = new int[len];
        for (int i = 0; i < len; ++i) {
            count[i] = 0;
        }
        Vector<String> winners = new Vector<>();

        System.out.println("실행 결과");
        boolean check = true;
        while (check) {
            for (int i = 0; i < len; ++i) {
                System.out.print(cars[i] + " : ");
                for (int j = 0; j < count[i]; ++j)
                    System.out.print("-");
                if (Randoms.pickNumberInRange(0, 9) >= 4) {
                    System.out.print("-");
                    if (++count[i] == num) {
                        check = false;
                        winners.add(cars[i]);
                    }
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }

    }
}
