package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.Vector;
import java.util.HashSet;
import java.util.Set;

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
        checkDuplicate(cars);

        int num;
        try {
            num = Integer.parseInt(numstr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력");
        }
        if (num <= 0)
            throw new IllegalArgumentException("잘못된 입력");

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
                printRace(count[i]);
                checkRace(count, i);
                check = checkWinner(winners, cars, num, count, i, check);
                System.out.print("\n");
            }
            System.out.print("\n");
        }

        System.out.print("최종 우승자 : ");
        int i = 0;
        for (String winner : winners) {
            if (i++ != 0)
                System.out.print(", ");
            System.out.print(winner);
        }
        System.out.print("\n");

    }

    public static void checkDuplicate(String[] cars) {
        Set<String> seen = new HashSet<>();
        for (String car : cars) {
            if (!seen.add(car)) {
                throw new IllegalArgumentException("잘못된 입력");
            }
        }
    }

    public static void printRace(int count) {
        for (int i = 0; i < count; ++i)
            System.out.print("-");
    }

    public static void checkRace(int[] count, int i) {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            ++count[i];
            System.out.print("-");
        }
    }

    public static boolean checkWinner(Vector<String> winners, String[] cars, int num, int[] count, int i,
            boolean check) {
        if (count[i] == num) {
            check = false;
            winners.add(cars[i]);
        }
        return check;
    }
}
